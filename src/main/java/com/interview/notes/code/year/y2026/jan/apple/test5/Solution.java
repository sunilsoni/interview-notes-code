package com.interview.notes.code.year.y2026.jan.apple.test5;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * ScheduleSlot - defines the 3 processing windows per week
 * Uses DayOfWeek and LocalTime to represent schedule
 */
enum ScheduleSlot {
    // Three fixed schedule times as per requirement
    MONDAY_8AM(DayOfWeek.MONDAY, LocalTime.of(8, 0)),
    WEDNESDAY_12PM(DayOfWeek.WEDNESDAY, LocalTime.of(12, 0)),
    FRIDAY_10AM(DayOfWeek.FRIDAY, LocalTime.of(10, 0));

    private final DayOfWeek day;    // Day of week for this slot
    private final LocalTime time;   // Time of day for this slot

    ScheduleSlot(DayOfWeek day, LocalTime time) {
        this.day = day;
        this.time = time;
    }

    /**
     * Finds the next schedule slot from current time across all slots
     * Static method to determine which slot fires next
     */
    public static ScheduleSlot getNextSlot(LocalDateTime from) {
        return Arrays.stream(values())                    // Stream all 3 slots
                .min(Comparator.comparing(                    // Find minimum (earliest)
                        slot -> slot.getNextOccurrence(from)))    // Compare by next occurrence time
                .orElse(MONDAY_8AM);                          // Default fallback
    }

    /**
     * Gets the DateTime of next scheduled processing time
     */
    public static LocalDateTime getNextScheduledTime(LocalDateTime from) {
        return getNextSlot(from).getNextOccurrence(from);
    }

    // Getters for day and time
    public DayOfWeek getDay() {
        return day;
    }

    public LocalTime getTime() {
        return time;
    }

    /**
     * Calculates the next occurrence of this schedule slot from given time
     * Returns LocalDateTime of when this slot will next occur
     */
    public LocalDateTime getNextOccurrence(LocalDateTime from) {
        // Get the target day-time in current week
        LocalDateTime target = from.with(day).with(time);

        // If target already passed this week, move to next week
        if (!target.isAfter(from)) {
            target = target.plusWeeks(1); // Shift to next week occurrence
        }
        return target;
    }
}

/**
 * EventProcessor - handles actual processing of event batches
 * Strategy pattern allows different processing implementations
 */
interface EventProcessor {
    /**
     * Process a batch of events
     *
     * @param events List of events to process in order
     * @return true if all processed successfully
     */
    boolean process(List<Event> events);
}

/**
 * Event class - represents a single event with arrival timestamp
 * Immutable record for thread safety and conciseness
 */
record Event(
        String id,              // Unique identifier for the event
        String payload,         // Event data/content
        LocalDateTime arrivedAt // Timestamp when event was received
) {
    // Compact constructor validates non-null fields
    public Event {
        Objects.requireNonNull(id, "Event ID cannot be null");
        Objects.requireNonNull(payload, "Payload cannot be null");
        if (arrivedAt == null) arrivedAt = LocalDateTime.now(); // Default to now
    }
}

/**
 * EventQueue - Thread-safe queue for storing incoming events
 * Uses ConcurrentLinkedQueue for lock-free concurrent access
 */
class EventQueue {
    // Thread-safe queue - supports concurrent producers adding events
    private final ConcurrentLinkedQueue<Event> pendingEvents = new ConcurrentLinkedQueue<>();

    // Track count for monitoring purposes
    private final java.util.concurrent.atomic.AtomicInteger totalReceived =
            new java.util.concurrent.atomic.AtomicInteger(0);

    /**
     * Adds new event to the queue - called when event arrives
     * Thread-safe, can be called from multiple threads
     */
    public void addEvent(Event event) {
        pendingEvents.offer(event);              // Add to tail of queue (FIFO)
        totalReceived.incrementAndGet();         // Track total count atomically
    }

    /**
     * Drains all pending events for batch processing
     * Returns list of events and clears queue atomically
     * Ensures no event is processed twice
     */
    public List<Event> drainEvents() {
        List<Event> batch = new ArrayList<>();   // Batch to return
        Event event;
        // Poll removes and returns head, returns null when empty
        while ((event = pendingEvents.poll()) != null) {
            batch.add(event);                    // Add each event to batch
        }
        return batch;                            // Return all drained events
    }

    /**
     * Gets current pending count without removing
     */
    public int getPendingCount() {
        return pendingEvents.size();
    }

    /**
     * Gets total events ever received
     */
    public int getTotalReceived() {
        return totalReceived.get();
    }
}

/**
 * ProcessingResult - captures outcome of a batch processing run
 * Immutable record for clean result handling
 */
record ProcessingResult(
        ScheduleSlot slot,              // Which schedule slot triggered this
        LocalDateTime processedAt,       // When processing occurred
        int eventCount,                  // Number of events processed
        List<String> processedEventIds,  // IDs of processed events
        boolean success                  // Whether processing succeeded
) {
}

/**
 * Default processor implementation - logs and processes events
 */
class DefaultEventProcessor implements EventProcessor {

    @Override
    public boolean process(List<Event> events) {
        // Process each event in FIFO order using Stream API
        events.forEach(event -> {
            // Simulate processing - in real system would do actual work
            System.out.println("  Processing: " + event.id() +
                    " | Arrived: " + event.arrivedAt() +
                    " | Payload: " + event.payload());
        });
        return true; // Return success
    }
}

/**
 * ScheduledEventSystem - Main orchestrator class
 * Coordinates event collection and scheduled batch processing
 */
class ScheduledEventSystem {
    private final EventQueue eventQueue;           // Queue for pending events
    private final EventProcessor processor;        // Processor for batches
    private final List<ProcessingResult> history;  // Processing history log
    private final boolean running;              // System running flag
    private LocalDateTime lastProcessedTime;       // Track last run time

    /**
     * Constructor with dependency injection for processor
     */
    public ScheduledEventSystem(EventProcessor processor) {
        this.eventQueue = new EventQueue();
        this.processor = processor;
        this.history = new CopyOnWriteArrayList<>();  // Thread-safe list
        this.lastProcessedTime = LocalDateTime.now(); // Initialize to now
        this.running = false;
    }

    /**
     * Default constructor uses DefaultEventProcessor
     */
    public ScheduledEventSystem() {
        this(new DefaultEventProcessor());
    }

    /**
     * Receives incoming event - called by event producers
     * Thread-safe method for event ingestion
     */
    public void receiveEvent(Event event) {
        eventQueue.addEvent(event);  // Delegate to thread-safe queue
    }

    /**
     * Receives event with auto-generated timestamp
     */
    public void receiveEvent(String id, String payload) {
        receiveEvent(new Event(id, payload, LocalDateTime.now()));
    }

    /**
     * Triggers batch processing for a schedule slot
     * Called at scheduled times to process accumulated events
     */
    public ProcessingResult processBatch(ScheduleSlot slot) {
        LocalDateTime now = LocalDateTime.now();

        // Drain all pending events atomically
        List<Event> batch = eventQueue.drainEvents();

        System.out.println("\n=== Processing at " + slot + " ===");
        System.out.println("Time: " + now);
        System.out.println("Events in batch: " + batch.size());

        // Process the batch if not empty
        boolean success = batch.isEmpty() || processor.process(batch);

        // Extract event IDs for result tracking
        List<String> processedIds = batch.stream()
                .map(Event::id)                        // Get ID from each event
                .toList();                             // Collect to immutable list

        // Create result record
        ProcessingResult result = new ProcessingResult(
                slot, now, batch.size(), processedIds, success
        );

        // Update tracking state
        history.add(result);                       // Add to history
        lastProcessedTime = now;                   // Update last processed time

        System.out.println("Processing " + (success ? "COMPLETED" : "FAILED"));
        return result;
    }

    /**
     * Gets the next scheduled processing time from now
     */
    public LocalDateTime getNextScheduledTime() {
        return ScheduleSlot.getNextScheduledTime(LocalDateTime.now());
    }

    /**
     * Gets the next schedule slot from now
     */
    public ScheduleSlot getNextSlot() {
        return ScheduleSlot.getNextSlot(LocalDateTime.now());
    }

    /**
     * Returns count of pending events
     */
    public int getPendingCount() {
        return eventQueue.getPendingCount();
    }

    /**
     * Returns processing history
     */
    public List<ProcessingResult> getHistory() {
        return Collections.unmodifiableList(history);
    }

    /**
     * Simulates time-based scheduling for demonstration
     * In production, would use ScheduledExecutorService
     */
    public void simulateSchedule(LocalDateTime simulatedTime, ScheduleSlot slot) {
        System.out.println("\n>>> Simulating schedule trigger at: " + simulatedTime);
        processBatch(slot);
    }
}

/**
 * Main class with comprehensive test cases
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println("=== Event Processing System - LLD ===\n");
        runAllTests();
    }

    /**
     * Runs all test cases and reports results
     */
    static void runAllTests() {
        int passed = 0, failed = 0;

        // Test 1: Basic event collection and processing
        if (testBasicEventFlow()) {
            passed++;
            System.out.println("Test 1 [Basic Flow]: PASS");
        } else {
            failed++;
            System.out.println("Test 1 [Basic Flow]: FAIL");
        }

        // Test 2: Multiple batches - no reprocessing
        if (testNoReprocessing()) {
            passed++;
            System.out.println("Test 2 [No Reprocessing]: PASS");
        } else {
            failed++;
            System.out.println("Test 2 [No Reprocessing]: FAIL");
        }

        // Test 3: Empty batch handling
        if (testEmptyBatch()) {
            passed++;
            System.out.println("Test 3 [Empty Batch]: PASS");
        } else {
            failed++;
            System.out.println("Test 3 [Empty Batch]: FAIL");
        }

        // Test 4: Schedule slot calculation
        if (testScheduleSlotCalculation()) {
            passed++;
            System.out.println("Test 4 [Schedule Calculation]: PASS");
        } else {
            failed++;
            System.out.println("Test 4 [Schedule Calculation]: FAIL");
        }

        // Test 5: Large data handling (100K events)
        if (testLargeDataHandling()) {
            passed++;
            System.out.println("Test 5 [Large Data - 100K]: PASS");
        } else {
            failed++;
            System.out.println("Test 5 [Large Data - 100K]: FAIL");
        }

        // Test 6: Concurrent event ingestion
        if (testConcurrentIngestion()) {
            passed++;
            System.out.println("Test 6 [Concurrent Ingestion]: PASS");
        } else {
            failed++;
            System.out.println("Test 6 [Concurrent Ingestion]: FAIL");
        }

        // Test 7: FIFO ordering
        if (testFIFOOrdering()) {
            passed++;
            System.out.println("Test 7 [FIFO Order]: PASS");
        } else {
            failed++;
            System.out.println("Test 7 [FIFO Order]: FAIL");
        }

        // Summary
        System.out.println("\n========== SUMMARY ==========");
        System.out.println("Total: " + (passed + failed));
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
        System.out.println("==============================");
    }

    /**
     * Test 1: Basic event flow - events added and processed in batch
     */
    static boolean testBasicEventFlow() {
        System.out.println("\n--- Test 1: Basic Event Flow ---");

        ScheduledEventSystem system = new ScheduledEventSystem();

        // Add 5 events before processing
        for (int i = 1; i <= 5; i++) {
            system.receiveEvent("EVT-" + i, "Payload-" + i);
        }

        // Verify events are pending
        if (system.getPendingCount() != 5) return false;

        // Process batch at Monday 8 AM slot
        ProcessingResult result = system.processBatch(ScheduleSlot.MONDAY_8AM);

        // Verify: 5 events processed, queue now empty
        return result.eventCount() == 5 &&
                result.success() &&
                system.getPendingCount() == 0;
    }

    /**
     * Test 2: Ensures events are not reprocessed in subsequent runs
     */
    static boolean testNoReprocessing() {
        System.out.println("\n--- Test 2: No Reprocessing ---");

        ScheduledEventSystem system = new ScheduledEventSystem();

        // Batch 1: 100 events before Monday 8 AM
        for (int i = 1; i <= 100; i++) {
            system.receiveEvent("BATCH1-" + i, "Data-" + i);
        }
        ProcessingResult result1 = system.processBatch(ScheduleSlot.MONDAY_8AM);

        // Batch 2: 200 events before Wednesday 12 PM
        for (int i = 1; i <= 200; i++) {
            system.receiveEvent("BATCH2-" + i, "Data-" + i);
        }
        ProcessingResult result2 = system.processBatch(ScheduleSlot.WEDNESDAY_12PM);

        // Verify: First batch = 100, Second batch = 200 (not 300)
        // This proves no reprocessing occurred
        return result1.eventCount() == 100 &&
                result2.eventCount() == 200 &&
                system.getHistory().size() == 2;
    }

    /**
     * Test 3: System handles empty batches gracefully
     */
    static boolean testEmptyBatch() {
        System.out.println("\n--- Test 3: Empty Batch ---");

        ScheduledEventSystem system = new ScheduledEventSystem();

        // Process with no events - should succeed with 0 count
        ProcessingResult result = system.processBatch(ScheduleSlot.FRIDAY_10AM);

        return result.eventCount() == 0 && result.success();
    }

    /**
     * Test 4: Verifies schedule slot calculation logic
     */
    static boolean testScheduleSlotCalculation() {
        System.out.println("\n--- Test 4: Schedule Slot Calculation ---");

        // Test: Sunday should point to Monday 8 AM as next slot
        LocalDateTime sunday = LocalDateTime.of(2024, 1, 7, 10, 0); // Sunday 10 AM
        ScheduleSlot nextFromSunday = ScheduleSlot.getNextSlot(sunday);

        // Test: Tuesday should point to Wednesday 12 PM
        LocalDateTime tuesday = LocalDateTime.of(2024, 1, 9, 10, 0); // Tuesday 10 AM
        ScheduleSlot nextFromTuesday = ScheduleSlot.getNextSlot(tuesday);

        // Test: Thursday should point to Friday 10 AM
        LocalDateTime thursday = LocalDateTime.of(2024, 1, 11, 10, 0); // Thursday 10 AM
        ScheduleSlot nextFromThursday = ScheduleSlot.getNextSlot(thursday);

        System.out.println("From Sunday: " + nextFromSunday);
        System.out.println("From Tuesday: " + nextFromTuesday);
        System.out.println("From Thursday: " + nextFromThursday);

        return nextFromSunday == ScheduleSlot.MONDAY_8AM &&
                nextFromTuesday == ScheduleSlot.WEDNESDAY_12PM &&
                nextFromThursday == ScheduleSlot.FRIDAY_10AM;
    }

    /**
     * Test 5: Large data handling - 100,000 events
     */
    static boolean testLargeDataHandling() {
        System.out.println("\n--- Test 5: Large Data (100K Events) ---");

        ScheduledEventSystem system = new ScheduledEventSystem(events -> true); // Fast processor

        long startIngest = System.currentTimeMillis();

        // Ingest 100,000 events
        IntStream.rangeClosed(1, 100_000)
                .forEach(i -> system.receiveEvent("LARGE-" + i, "BigPayload-" + i));

        long ingestTime = System.currentTimeMillis() - startIngest;
        System.out.println("Ingestion time for 100K events: " + ingestTime + "ms");

        // Process all events
        long startProcess = System.currentTimeMillis();
        ProcessingResult result = system.processBatch(ScheduleSlot.MONDAY_8AM);
        long processTime = System.currentTimeMillis() - startProcess;

        System.out.println("Processing time: " + processTime + "ms");
        System.out.println("Events processed: " + result.eventCount());

        // Should complete within reasonable time and process all events
        return result.eventCount() == 100_000 &&
                result.success() &&
                ingestTime < 5000 &&  // Ingestion under 5 seconds
                processTime < 5000;   // Processing under 5 seconds
    }

    /**
     * Test 6: Concurrent event ingestion from multiple threads
     */
    static boolean testConcurrentIngestion() {
        System.out.println("\n--- Test 6: Concurrent Ingestion ---");

        ScheduledEventSystem system = new ScheduledEventSystem(events -> true);
        int threadsCount = 10;
        int eventsPerThread = 1000;
        CountDownLatch latch = new CountDownLatch(threadsCount);

        // Spawn multiple threads adding events concurrently
        for (int t = 0; t < threadsCount; t++) {
            final int threadId = t;
            new Thread(() -> {
                for (int i = 0; i < eventsPerThread; i++) {
                    system.receiveEvent("T" + threadId + "-E" + i, "ConcurrentData");
                }
                latch.countDown();
            }).start();
        }

        try {
            latch.await(10, TimeUnit.SECONDS); // Wait for all threads
        } catch (InterruptedException e) {
            return false;
        }

        // Process and verify all events received
        ProcessingResult result = system.processBatch(ScheduleSlot.MONDAY_8AM);
        int expected = threadsCount * eventsPerThread;

        System.out.println("Expected: " + expected + ", Actual: " + result.eventCount());
        return result.eventCount() == expected;
    }

    /**
     * Test 7: Verifies FIFO ordering is maintained
     */
    static boolean testFIFOOrdering() {
        System.out.println("\n--- Test 7: FIFO Ordering ---");

        List<String> processedOrder = new ArrayList<>();

        // Custom processor that tracks order
        EventProcessor orderTracker = events -> {
            events.forEach(e -> processedOrder.add(e.id()));
            return true;
        };

        ScheduledEventSystem system = new ScheduledEventSystem(orderTracker);

        // Add events in specific order
        system.receiveEvent("FIRST", "1");
        system.receiveEvent("SECOND", "2");
        system.receiveEvent("THIRD", "3");
        system.receiveEvent("FOURTH", "4");
        system.receiveEvent("FIFTH", "5");

        system.processBatch(ScheduleSlot.MONDAY_8AM);

        // Verify order matches insertion order (FIFO)
        System.out.println("Processing order: " + processedOrder);

        return processedOrder.equals(List.of("FIRST", "SECOND", "THIRD", "FOURTH", "FIFTH"));
    }
}