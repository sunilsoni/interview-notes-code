package com.interview.notes.code.year.y2026.jan.apple.test6;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * EventBatchSystem
 * Designed for Java 21.
 * Handles continuous ingestion and scheduled batch processing.
 */
public class EventBatchSystem {

    // 2. Thread-safe queue to hold incoming events.
    // 'ConcurrentLinkedQueue' is non-blocking and efficient for high-concurrency add operations.
    private final Queue<Event> eventQueue = new ConcurrentLinkedQueue<>();
    // 3. Scheduler for handling the time-based triggers (Mon 8am, Wed 12pm, Fri 10am).
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    // Flag to stop the system (useful for testing/shutdown)
    private volatile boolean running = true;

    // ==========================================
    // MAIN METHOD: TEST RUNNER
    // ==========================================
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting Event Processing System Tests (Java 21)...");

        // 1. Initialize System
        var system = new EventBatchSystem();

        // -------------------------------------------------
        // TEST CASE 1: Basic Ingestion & Processing Logic
        // -------------------------------------------------
        System.out.print("Test 1: Basic Ingestion... ");

        // Simulate events arriving BEFORE Monday 8:00 AM
        system.ingest("UserSignup");
        system.ingest("OrderPlaced");
        system.ingest("PaymentReceived");

        // Verify queue has data (internal check logic)
        // We manually trigger processBatch to simulate the time arriving
        int processedCount = system.processBatch("Monday 8:00 AM Simulation");

        if (processedCount == 3) {
            System.out.println("[PASS]");
        } else {
            System.out.println("[FAIL] Expected 3, got " + processedCount);
        }

        // -------------------------------------------------
        // TEST CASE 2: No Reprocessing (Queue Clearing)
        // -------------------------------------------------
        System.out.print("Test 2: Ensure Queue is Empty... ");

        // Run again immediately. Should be 0 as we haven't added new events.
        int reRunCount = system.processBatch("Retry Simulation");

        if (reRunCount == 0) {
            System.out.println("[PASS]");
        } else {
            System.out.println("[FAIL] Events were reprocessed!");
        }

        // -------------------------------------------------
        // TEST CASE 3: Large Data Volume (Performance)
        // -------------------------------------------------
        System.out.print("Test 3: Large Data (100,000 events)... ");

        // Use IntStream for clean loop generation
        // Simulating heavy burst of traffic
        long startLoad = System.currentTimeMillis();
        IntStream.range(0, 100_000).parallel().forEach(i -> system.ingest("LoadTest-" + i));
        long endLoad = System.currentTimeMillis();

        System.out.print("\n   -> Loaded 100k events in " + (endLoad - startLoad) + "ms. Processing... ");

        // Process all
        long startProc = System.currentTimeMillis();
        int largeBatchSize = system.processBatch("Wednesday 12:00 PM Simulation");
        long endProc = System.currentTimeMillis();

        if (largeBatchSize == 100_000) {
            System.out.println("[PASS] Processed in " + (endProc - startProc) + "ms");
        } else {
            System.out.println("[FAIL] Count mismatch: " + largeBatchSize);
        }

        // -------------------------------------------------
        // TEST CASE 4: Scheduler Logic Check (Visual)
        // -------------------------------------------------
        System.out.println("Test 4: Scheduler Logic verification...");
        // This won't run a full batch but will print the next scheduled time logic
        system.startScheduler();

        // Allow scheduler thread to print its calculation then exit
        Thread.sleep(100);
        system.stop();

        System.out.println("\nAll Tests Completed.");
    }

    /**
     * Ingests an event into the system.
     * accessible by multiple threads simultaneously.
     */
    public void ingest(String payload) {
        // Create new event with current time and unique ID (using UUID or simple random for this demo)
        var event = new Event(java.util.UUID.randomUUID().toString(), payload, LocalDateTime.now());

        // Add to queue immediately (O(1) operation)
        eventQueue.add(event);
    }

    /**
     * THE CORE PROCESSING LOGIC
     * Drains the queue and processes items.
     * In a real app, this is called by the scheduler. In tests, we call it manually.
     * Returns count of processed items for verification.
     */
    public int processBatch(String scheduleName) {
        // Use 'var' (Java 10+) to reduce verbosity.
        // We pull all currently available elements.
        var batch = new ArrayList<Event>();

        // Poll() retrieves and removes the head of the queue. 
        // We loop until queue is empty. This ensures we only process events arrived BEFORE this run started.
        Event e;
        while ((e = eventQueue.poll()) != null) {
            batch.add(e);
        }

        // If batch is empty, nothing to do
        if (batch.isEmpty()) {
            return 0;
        }

        // Process the batch (Simulation) using Stream API
        System.out.println("\n--- EXECUTION: " + scheduleName + " ---");
        System.out.println("Processing " + batch.size() + " events...");

        // Example processing: print IDs of first 3 to show work
        batch.stream().limit(3).forEach(ev ->
                System.out.println("Processed Event: " + ev.id() + " | Payload: " + ev.payload())
        );
        if (batch.size() > 3) System.out.println("... and " + (batch.size() - 3) + " more.");

        return batch.size();
    }

    /**
     * Calculates delay until the next schedule slot and starts the timer.
     * Slots: Mon 8:00, Wed 12:00, Fri 10:00
     */
    public void startScheduler() {
        if (!running) return;

        var now = LocalDateTime.now();

        // Define our target slots for THIS week
        var targets = List.of(
                now.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY)).withHour(8).withMinute(0).withSecond(0),
                now.with(TemporalAdjusters.nextOrSame(DayOfWeek.WEDNESDAY)).withHour(12).withMinute(0).withSecond(0),
                now.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY)).withHour(10).withMinute(0).withSecond(0)
        );

        // Find the earliest target that is essentially in the future (after 'now')
        // We map targets; if a target is before 'now', we add 1 week to it.
        var nextRunTime = targets.stream()
                .map(t -> t.isAfter(now) ? t : t.plusWeeks(1)) // Adjust for next week if time passed
                .min(Comparator.naturalOrder()) // Get the soonest one
                .orElseThrow(); // Should never happen

        // Calculate seconds until that time
        long delaySeconds = Duration.between(now, nextRunTime).getSeconds();

        System.out.println("Next Schedule: " + nextRunTime + " (in " + delaySeconds + " seconds)");

        // Schedule the task
        scheduler.schedule(() -> {
            processBatch("Scheduled Run @ " + nextRunTime);
            startScheduler(); // Recursively schedule the next run
        }, delaySeconds, TimeUnit.SECONDS);
    }

    public void stop() {
        running = false;
        scheduler.shutdownNow();
    }

    // 1. Define the Event using 'record' (Java 14+) for immutable, concise data carrying.
    // This removes the need for getters, setters, equals, hashCode, and toString methods.
    public record Event(String id, String payload, LocalDateTime arrivedAt) {
    }
}