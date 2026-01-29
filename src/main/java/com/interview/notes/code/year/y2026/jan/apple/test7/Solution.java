package com.interview.notes.code.year.y2026.jan.apple.test7;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * ScheduleSlot - Enum defining the 3 fixed processing windows
 * Each slot has a day of week and time
 */
enum ScheduleSlot {
    // Three fixed schedule times as per requirement
    MONDAY_8AM(DayOfWeek.MONDAY, LocalTime.of(8, 0)),
    WEDNESDAY_12PM(DayOfWeek.WEDNESDAY, LocalTime.of(12, 0)),
    FRIDAY_10AM(DayOfWeek.FRIDAY, LocalTime.of(10, 0));

    private final DayOfWeek day;    // Day of week for this slot
    private final LocalTime time;   // Time of day for this slot

    // Constructor to initialize day and time
    ScheduleSlot(DayOfWeek day, LocalTime time) {
        this.day = day;
        this.time = time;
    }

    /**
     * Finds which schedule slot is NEXT from the given time
     * Returns the ScheduleSlot enum value
     */
    public static ScheduleSlot getNextSlot(LocalDateTime from) {
        return Arrays.stream(values())                        // Stream all 3 slots
                .min(Comparator.comparing(                        // Find the one with minimum
                        slot -> slot.getNextOccurrence(from)))        // next occurrence time
                .orElse(MONDAY_8AM);                              // Default fallback
    }

    /**
     * Gets the EXACT LocalDateTime of next scheduled processing
     * This becomes the KEY in our storage map
     */
    public static LocalDateTime getNextScheduledTime(LocalDateTime from) {
        return getNextSlot(from).getNextOccurrence(from);
    }

    // Getter for day
    public DayOfWeek getDay() {
        return day;
    }

    // Getter for time
    public LocalTime getTime() {
        return time;
    }

    /**
     * Calculates the EXACT next occurrence of this slot from given time
     * Returns specific LocalDateTime (e.g., 2024-01-15 08:00:00)
     */
    public LocalDateTime getNextOccurrence(LocalDateTime from) {
        // Get target day-time in current week
        LocalDateTime target = from
                .with(TemporalAdjusters.nextOrSame(day))  // Move to this day (or same if already)
                .with(time);                               // Set the time

        // If we're past this slot today, or it's a different day scenario
        // We need to check if target is actually after 'from'
        if (!target.isAfter(from)) {
            // Move to next week's occurrence
            target = from
                    .with(TemporalAdjusters.next(day))    // Next occurrence of this day
                    .with(time);                           // Set the time
        }

        return target;
    }
}

/**
 * EventProcessor - Strategy interface for processing events
 * Allows different processing implementations
 */
@FunctionalInterface
interface EventProcessor {
    /**
     * Process a batch of events
     *
     * @param events List of events to process in FIFO order
     * @return true if processing succeeded
     */
    boolean process(List<Event> events);
}

/**
 * Event - Immutable record representing a single event
 * Using Java 21 record for conciseness and immutability
 */
record Event(
        String id,                  // Unique identifier for the event
        String payload,             // Event data/content
        LocalDateTime arrivedAt     // Timestamp when event was received
) {
    // Compact constructor for validation
    public Event {
        Objects.requireNonNull(id, "Event ID cannot be null");
        Objects.requireNonNull(payload, "Payload cannot be null");
        Objects.requireNonNull(arrivedAt, "Arrival time cannot be null");
    }

    // Convenience constructor - auto-assigns current time
    public Event(String id, String payload) {
        this(id, payload, LocalDateTime.now());
    }
}

/**
 * DefaultEventProcessor - Simple implementation that logs events
 */
class DefaultEventProcessor implements EventProcessor {

    @Override
    public boolean process(List<Event> events) {
        // Process each event in order using Stream
        events.forEach(event -> {
            System.out.println("    Processing: " + event.id() +
                    " | Arrived: " + event.arrivedAt() +
                    " | Payload: " + event.payload());
        });
        return true;  // Return success
    }
}

/**
 * ProcessingResult - Immutable record capturing batch processing outcome
 */
record ProcessingResult(
        LocalDateTime scheduledTime,     // Which exact schedule time
        LocalDateTime processedAt,       // When processing actually occurred
        int eventCount,                  // Number of events processed
        List<String> processedEventIds,  // IDs of processed events (for verification)
        boolean success                  // Whether processing succeeded
) {
}

/**
 * ScheduledEventSystem - Main orchestrator class
 * <p>
 * KEY DESIGN: Events are routed to correct batch at INGESTION time
 * Storage: Map<LocalDateTime, Queue<Event>>
 * - Key = Exact scheduled processing time
 * - Value = Queue of events for that batch
 */
class ScheduledEventSystem {

    // Main storage: Maps scheduled time -> events for that batch
    // ConcurrentHashMap for thread-safe access from multiple producers
    private final ConcurrentHashMap<LocalDateTime, ConcurrentLinkedQueue<Event>> batchStorage;

    // Processor to handle event batches
    private final EventProcessor processor;

    // History of all processing runs
    private final List<ProcessingResult> processingHistory;

    // Clock for getting current time (allows testing with simulated time)
    private Clock clock;

    /**
     * Constructor with custom processor
     */
    public ScheduledEventSystem(EventProcessor processor) {
        this.batchStorage = new ConcurrentHashMap<>();      // Thread-safe map
        this.processor = processor;                          // Injected processor
        this.processingHistory = new CopyOnWriteArrayList<>(); // Thread-safe history
        this.clock = Clock.systemDefaultZone();              // Default system clock
    }

    /**
     * Default constructor - uses DefaultEventProcessor
     */
    public ScheduledEventSystem() {
        this(new DefaultEventProcessor());
    }

    /**
     * Set custom clock for testing time-based scenarios
     */
    public void setClock(Clock clock) {
        this.clock = clock;
    }

    /**
     * CORE METHOD: Receive and route event to correct batch
     * <p>
     * This is where the magic happens:
     * 1. Calculate which scheduled slot this event belongs to
     * 2. Put directly into that batch's queue
     * <p>
     * Thread-safe: Multiple threads can call this simultaneously
     */
    public void receiveEvent(Event event) {
        // Step 1: Determine which batch this event belongs to
        // Based on event's arrival time, find the NEXT scheduled processing time
        LocalDateTime targetBatch = ScheduleSlot.getNextScheduledTime(event.arrivedAt());

        // Step 2: Get or create the queue for this batch
        // computeIfAbsent is atomic - creates queue only if not exists
        ConcurrentLinkedQueue<Event> batchQueue = batchStorage.computeIfAbsent(
                targetBatch,                                    // Key = scheduled time
                k -> new ConcurrentLinkedQueue<>()              // Value = new queue if absent
        );

        // Step 3: Add event to the batch queue
        batchQueue.offer(event);                            // Add to tail (FIFO)
    }

    /**
     * Convenience method - creates event with given arrival time
     */
    public void receiveEvent(String id, String payload, LocalDateTime arrivedAt) {
        receiveEvent(new Event(id, payload, arrivedAt));
    }

    /**
     * Convenience method - creates event with current time
     */
    public void receiveEvent(String id, String payload) {
        receiveEvent(new Event(id, payload, LocalDateTime.now(clock)));
    }

    /**
     * CORE METHOD: Process a batch for given scheduled time
     * <p>
     * Called by scheduler when it's time to process
     * 1. Remove the entire batch from storage (atomic)
     * 2. Process all events in the batch
     * 3. Return result
     * <p>
     * No filtering needed - all events in batch belong there!
     */
    public ProcessingResult processBatch(LocalDateTime scheduledTime) {
        LocalDateTime now = LocalDateTime.now(clock);

        System.out.println("\n=== Processing Batch for: " + scheduledTime + " ===");
        System.out.println("Current time: " + now);

        // Step 1: ATOMICALLY remove the batch from storage
        // remove() returns the queue and deletes the key
        // This ensures no event can be reprocessed
        ConcurrentLinkedQueue<Event> batch = batchStorage.remove(scheduledTime);

        // Step 2: Handle case where no events exist for this batch
        if (batch == null || batch.isEmpty()) {
            System.out.println("No events in this batch");
            ProcessingResult result = new ProcessingResult(
                    scheduledTime, now, 0, List.of(), true
            );
            processingHistory.add(result);
            return result;
        }

        // Step 3: Convert queue to list for processing (maintains FIFO order)
        List<Event> eventList = new ArrayList<>(batch);
        System.out.println("Events to process: " + eventList.size());

        // Step 4: Process all events
        boolean success = processor.process(eventList);

        // Step 5: Collect processed event IDs for result
        List<String> processedIds = eventList.stream()
                .map(Event::id)                                 // Extract ID from each event
                .toList();                                      // Collect to immutable list

        // Step 6: Create and store result
        ProcessingResult result = new ProcessingResult(
                scheduledTime, now, eventList.size(), processedIds, success
        );
        processingHistory.add(result);

        System.out.println("Processing " + (success ? "COMPLETED" : "FAILED"));
        return result;
    }

    /**
     * Gets count of events pending for a specific batch
     */
    public int getPendingCountForBatch(LocalDateTime scheduledTime) {
        ConcurrentLinkedQueue<Event> batch = batchStorage.get(scheduledTime);
        return batch == null ? 0 : batch.size();
    }

    /**
     * Gets all scheduled batch times that have pending events
     */
    public Set<LocalDateTime> getPendingBatchTimes() {
        return Collections.unmodifiableSet(batchStorage.keySet());
    }

    /**
     * Gets total pending events across all batches
     */
    public int getTotalPendingCount() {
        return batchStorage.values().stream()
                .mapToInt(ConcurrentLinkedQueue::size)          // Get size of each queue
                .sum();                                          // Sum all sizes
    }

    /**
     * Gets processing history
     */
    public List<ProcessingResult> getProcessingHistory() {
        return Collections.unmodifiableList(processingHistory);
    }

    /**
     * Gets the next scheduled processing time from now
     */
    public LocalDateTime getNextScheduledTime() {
        return ScheduleSlot.getNextScheduledTime(LocalDateTime.now(clock));
    }

    /**
     * Prints current state of all batches (for debugging)
     */
    public void printBatchStatus() {
        System.out.println("\n--- Current Batch Status ---");
        if (batchStorage.isEmpty()) {
            System.out.println("No pending batches");
            return;
        }
        batchStorage.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())             // Sort by scheduled time
                .forEach(entry -> {
                    System.out.println("Batch " + entry.getKey() +
                            ": " + entry.getValue().size() + " events");
                });
    }
}

/**
 * Main class with comprehensive test cases
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════════╗");
        System.out.println("║   Event Processing System - LLD Implementation    ║");
        System.out.println("╚═══════════════════════════════════════════════════╝\n");

        runAllTests();
    }

    /**
     * Runs all test cases and reports results
     */
    static void runAllTests() {
        int passed = 0;
        int failed = 0;

        // Test 1: Events route to correct batch based on arrival time
        System.out.println("\n" + "=".repeat(60));
        if (testEventRoutingByTimestamp()) {
            passed++;
            System.out.println("✓ Test 1 [Event Routing by Timestamp]: PASS");
        } else {
            failed++;
            System.out.println("✗ Test 1 [Event Routing by Timestamp]: FAIL");
        }

        // Test 2: No reprocessing - events processed exactly once
        System.out.println("\n" + "=".repeat(60));
        if (testNoReprocessing()) {
            passed++;
            System.out.println("✓ Test 2 [No Reprocessing]: PASS");
        } else {
            failed++;
            System.out.println("✗ Test 2 [No Reprocessing]: FAIL");
        }

        // Test 3: Example from problem statement
        System.out.println("\n" + "=".repeat(60));
        if (testProblemStatementExample()) {
            passed++;
            System.out.println("✓ Test 3 [Problem Statement Example]: PASS");
        } else {
            failed++;
            System.out.println("✗ Test 3 [Problem Statement Example]: FAIL");
        }

        // Test 4: Empty batch handling
        System.out.println("\n" + "=".repeat(60));
        if (testEmptyBatch()) {
            passed++;
            System.out.println("✓ Test 4 [Empty Batch Handling]: PASS");
        } else {
            failed++;
            System.out.println("✗ Test 4 [Empty Batch Handling]: FAIL");
        }

        // Test 5: FIFO ordering within batch
        System.out.println("\n" + "=".repeat(60));
        if (testFIFOOrdering()) {
            passed++;
            System.out.println("✓ Test 5 [FIFO Ordering]: PASS");
        } else {
            failed++;
            System.out.println("✗ Test 5 [FIFO Ordering]: FAIL");
        }


        // Print Summary
        System.out.println("\n" + "=".repeat(60));
        System.out.println("╔═══════════════════════════════════════════════════╗");
        System.out.println("║                  TEST SUMMARY                     ║");
        System.out.println("╠═══════════════════════════════════════════════════╣");
        System.out.printf("║   Total Tests: %-34d ║%n", (passed + failed));
        System.out.printf("║   Passed:      %-34d ║%n", passed);
        System.out.printf("║   Failed:      %-34d ║%n", failed);
        System.out.println("╚═══════════════════════════════════════════════════╝");
    }

    /**
     * Test 1: Events are routed to correct batch based on arrival timestamp
     */
    static boolean testEventRoutingByTimestamp() {
        System.out.println("Test 1: Event Routing by Timestamp");
        System.out.println("-".repeat(40));

        // Use fast processor for testing
        ScheduledEventSystem system = new ScheduledEventSystem(events -> true);

        // Define a specific week for testing
        // Monday Jan 15, 2024
        LocalDateTime mondayBeforeSlot = LocalDateTime.of(2024, 1, 15, 7, 30);  // Before 8 AM
        LocalDateTime mondayAfterSlot = LocalDateTime.of(2024, 1, 15, 9, 0);    // After 8 AM
        LocalDateTime tuesdayMorning = LocalDateTime.of(2024, 1, 16, 10, 0);    // Tuesday
        LocalDateTime wednesdayBeforeSlot = LocalDateTime.of(2024, 1, 17, 11, 0); // Before 12 PM Wed

        // Expected batch times
        LocalDateTime mondayBatch = LocalDateTime.of(2024, 1, 15, 8, 0);
        LocalDateTime wednesdayBatch = LocalDateTime.of(2024, 1, 17, 12, 0);

        // Event arriving Monday 7:30 AM → should go to Monday 8 AM batch
        system.receiveEvent("E1", "Payload1", mondayBeforeSlot);

        // Event arriving Monday 9:00 AM → should go to Wednesday 12 PM batch
        system.receiveEvent("E2", "Payload2", mondayAfterSlot);

        // Event arriving Tuesday 10 AM → should go to Wednesday 12 PM batch
        system.receiveEvent("E3", "Payload3", tuesdayMorning);

        // Event arriving Wednesday 11 AM → should go to Wednesday 12 PM batch
        system.receiveEvent("E4", "Payload4", wednesdayBeforeSlot);

        // Print batch status
        system.printBatchStatus();

        // Verify routing
        int mondayCount = system.getPendingCountForBatch(mondayBatch);
        int wednesdayCount = system.getPendingCountForBatch(wednesdayBatch);

        System.out.println("\nVerification:");
        System.out.println("Monday 8 AM batch: " + mondayCount + " events (expected: 1)");
        System.out.println("Wednesday 12 PM batch: " + wednesdayCount + " events (expected: 3)");

        return mondayCount == 1 && wednesdayCount == 3;
    }

    /**
     * Test 2: Events are not reprocessed - batch is removed after processing
     */
    static boolean testNoReprocessing() {
        System.out.println("Test 2: No Reprocessing");
        System.out.println("-".repeat(40));

        ScheduledEventSystem system = new ScheduledEventSystem(events -> true);

        LocalDateTime mondayBatch = LocalDateTime.of(2024, 1, 15, 8, 0);
        LocalDateTime beforeBatch = LocalDateTime.of(2024, 1, 15, 7, 0);

        // Add 50 events before Monday 8 AM
        for (int i = 1; i <= 50; i++) {
            system.receiveEvent("EVT-" + i, "Data", beforeBatch);
        }

        System.out.println("Events added: 50");
        System.out.println("Pending before first processing: " + system.getPendingCountForBatch(mondayBatch));

        // First processing - should get all 50
        ProcessingResult result1 = system.processBatch(mondayBatch);
        System.out.println("First processing count: " + result1.eventCount());

        // Second processing - should get 0 (batch was removed)
        ProcessingResult result2 = system.processBatch(mondayBatch);
        System.out.println("Second processing count: " + result2.eventCount());

        // Verify: First = 50, Second = 0
        return result1.eventCount() == 50 && result2.eventCount() == 0;
    }

    /**
     * Test 3: Problem statement example
     * - 100 events before Monday 8 AM → processed at Monday 8 AM
     * - 200 events after Monday 8 AM but before Wednesday 12 PM → processed at Wednesday
     */
    static boolean testProblemStatementExample() {
        System.out.println("Test 3: Problem Statement Example");
        System.out.println("-".repeat(40));

        ScheduledEventSystem system = new ScheduledEventSystem(events -> true);

        // Batch times
        LocalDateTime mondayBatch = LocalDateTime.of(2024, 1, 15, 8, 0);
        LocalDateTime wednesdayBatch = LocalDateTime.of(2024, 1, 17, 12, 0);

        // Simulate: 100 events arrive before Monday 8:00 AM
        LocalDateTime beforeMonday8AM = LocalDateTime.of(2024, 1, 15, 7, 0);
        for (int i = 1; i <= 100; i++) {
            system.receiveEvent("BEFORE-" + i, "Data", beforeMonday8AM);
        }
        System.out.println("Added 100 events before Monday 8 AM");

        // Simulate: 200 events arrive after Monday 8 AM but before Wednesday 12 PM
        LocalDateTime afterMonday8AM = LocalDateTime.of(2024, 1, 15, 10, 0);
        for (int i = 1; i <= 200; i++) {
            system.receiveEvent("AFTER-" + i, "Data", afterMonday8AM);
        }
        System.out.println("Added 200 events after Monday 8 AM");

        system.printBatchStatus();

        // Process Monday batch
        ProcessingResult mondayResult = system.processBatch(mondayBatch);
        System.out.println("\nMonday 8 AM processing: " + mondayResult.eventCount() + " events");

        // Process Wednesday batch
        ProcessingResult wednesdayResult = system.processBatch(wednesdayBatch);
        System.out.println("Wednesday 12 PM processing: " + wednesdayResult.eventCount() + " events");

        // Verify no events left
        System.out.println("Remaining events: " + system.getTotalPendingCount());

        return mondayResult.eventCount() == 100 &&
                wednesdayResult.eventCount() == 200 &&
                system.getTotalPendingCount() == 0;
    }

    /**
     * Test 4: Empty batch handling - should succeed with 0 events
     */
    static boolean testEmptyBatch() {
        System.out.println("Test 4: Empty Batch Handling");
        System.out.println("-".repeat(40));

        ScheduledEventSystem system = new ScheduledEventSystem(events -> true);

        // Process a batch that has no events
        LocalDateTime emptyBatch = LocalDateTime.of(2024, 1, 19, 10, 0); // Friday

        ProcessingResult result = system.processBatch(emptyBatch);

        System.out.println("Empty batch processed: " + result.eventCount() + " events");
        System.out.println("Success: " + result.success());

        return result.eventCount() == 0 && result.success();
    }

    /**
     * Test 5: FIFO ordering is maintained within batch
     */
    static boolean testFIFOOrdering() {
        System.out.println("Test 5: FIFO Ordering");
        System.out.println("-".repeat(40));

        List<String> processedOrder = new ArrayList<>();

        // Custom processor that tracks order
        EventProcessor orderTracker = events -> {
            events.forEach(e -> processedOrder.add(e.id()));
            return true;
        };

        ScheduledEventSystem system = new ScheduledEventSystem(orderTracker);

        LocalDateTime batchTime = LocalDateTime.of(2024, 1, 15, 8, 0);
        LocalDateTime baseTime = LocalDateTime.of(2024, 1, 15, 7, 0);

        // Add events with sequential IDs and timestamps
        system.receiveEvent("FIRST", "1", baseTime.plusMinutes(1));
        system.receiveEvent("SECOND", "2", baseTime.plusMinutes(2));
        system.receiveEvent("THIRD", "3", baseTime.plusMinutes(3));
        system.receiveEvent("FOURTH", "4", baseTime.plusMinutes(4));
        system.receiveEvent("FIFTH", "5", baseTime.plusMinutes(5));

        // Process batch
        system.processBatch(batchTime);

        System.out.println("Expected order: [FIRST, SECOND, THIRD, FOURTH, FIFTH]");
        System.out.println("Actual order:   " + processedOrder);

        List<String> expectedOrder = List.of("FIRST", "SECOND", "THIRD", "FOURTH", "FIFTH");
        return processedOrder.equals(expectedOrder);
    }

}