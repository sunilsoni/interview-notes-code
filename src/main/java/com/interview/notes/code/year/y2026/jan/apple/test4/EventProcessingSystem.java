package com.interview.notes.code.year.y2026.jan.apple.test4;

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

public class EventProcessingSystem {

    // 2. STORAGE: Thread-safe queue.
    // We use ConcurrentLinkedQueue because it is non-blocking and handles
    // high-concurrency writes (ingestion) very well.
    private final Queue<Event> eventQueue = new ConcurrentLinkedQueue<>();
    // 3. SCHEDULER: Handles the precise timing of batch runs.
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private volatile boolean isRunning = true;

    // =================================================================
    // TEST RUNNER (Main Method)
    // =================================================================
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== INITIALIZING SYSTEM ===");
        var app = new EventProcessingSystem();

        // -------------------------------------------------------------
        // TEST CASE 1: Standard Accumulation
        // Scenario: Events arrive BEFORE the schedule.
        // -------------------------------------------------------------
        System.out.print("Test 1: Standard Accumulation... ");

        app.sendEvent("Event_A_Mon_Morning");
        app.sendEvent("Event_B_Mon_Morning");

        // We manually trigger the batch to simulate the time arriving
        int count1 = app.runBatchJob("Manual_Trigger_Mon_8AM");

        if (count1 == 2) System.out.println("PASS");
        else System.out.println("FAIL (Expected 2, got " + count1 + ")");


        // -------------------------------------------------------------
        // TEST CASE 2: No Reprocessing / Empty Slot
        // Scenario: The batch runs again immediately. Should process nothing.
        // -------------------------------------------------------------
        System.out.print("Test 2: No Reprocessing Check... ");

        int count2 = app.runBatchJob("Manual_Trigger_Retry");

        if (count2 == 0) System.out.println("PASS");
        else System.out.println("FAIL (Expected 0, got " + count2 + ")");


        // -------------------------------------------------------------
        // TEST CASE 3: Large Volume Load
        // Scenario: 500,000 events flood in before Wed 12PM.
        // -------------------------------------------------------------
        System.out.print("Test 3: Large Volume (500k events)... ");

        long startLoad = System.currentTimeMillis();
        // Parallel stream to simulate high concurrency ingestion
        IntStream.range(0, 500_000).parallel().forEach(i -> app.sendEvent("Load_" + i));
        long endLoad = System.currentTimeMillis();

        System.out.print("Loaded in " + (endLoad - startLoad) + "ms... ");

        long startProcess = System.currentTimeMillis();
        int count3 = app.runBatchJob("Manual_Trigger_Wed_12PM");
        long endProcess = System.currentTimeMillis();

        if (count3 == 500_000) System.out.println("PASS (Processed in " + (endProcess - startProcess) + "ms)");
        else System.out.println("FAIL (Expected 500000, got " + count3 + ")");

        // -------------------------------------------------------------
        // TEST CASE 4: Scheduler Logic Validation
        // Scenario: Validate it correctly picks the next date.
        // -------------------------------------------------------------
        System.out.println("\nTest 4: Scheduler Visualization (Async)...");
        app.scheduleNextRun(); // Should print the calculation logic

        Thread.sleep(100); // Give scheduler thread a moment to print
        app.stop();
        System.out.println("=== TESTS COMPLETED ===");
    }

    /**
     * INGESTION: Accepts events at any time.
     * This is O(1) - very fast, just adds to the tail of the queue.
     */
    public void sendEvent(String payload) {
        var event = new Event(
                java.util.UUID.randomUUID().toString(),
                payload,
                LocalDateTime.now()
        );
        eventQueue.add(event);
    }

    /**
     * PROCESSING: The core batch logic.
     * It drains the queue completely.
     */
    public int runBatchJob(String batchName) {
        // Use a local list to hold the batch we are about to process
        var batch = new ArrayList<Event>();

        // POLL logic: Retrieves AND removes the head of the queue.
        // This guarantees we satisfy the "No Reprocessing" constraint.
        // We loop until the queue is empty.
        Event e;
        while ((e = eventQueue.poll()) != null) {
            batch.add(e);
        }

        if (batch.isEmpty()) return 0;

        // Simulate processing
        System.out.println("\n>>> STARTING BATCH: " + batchName);
        System.out.println(">>> Processing " + batch.size() + " events...");

        // Java Stream API for processing
        batch.forEach(ev -> {
            // In real life, save to DB or call API here
            // System.out.println("   Processed: " + ev.payload); 
        });

        System.out.println(">>> COMPLETED BATCH.\n");
        return batch.size();
    }

    /**
     * SCHEDULING LOGIC: Calculates delay to next slot.
     * Slots: Mon 8:00, Wed 12:00, Fri 10:00
     */
    public void scheduleNextRun() {
        if (!isRunning) return;

        var now = LocalDateTime.now();

        // Define target times for the CURRENT week
        var targets = List.of(
                now.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY)).withHour(8).withMinute(0).withSecond(0),
                now.with(TemporalAdjusters.nextOrSame(DayOfWeek.WEDNESDAY)).withHour(12).withMinute(0).withSecond(0),
                now.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY)).withHour(10).withMinute(0).withSecond(0)
        );

        // Logic: Find the earliest target that is in the future.
        // If a target (e.g., Mon 8am) is already past for this week, treat it as next week (plus 7 days).
        var nextRun = targets.stream()
                .map(t -> t.isAfter(now) ? t : t.plusWeeks(1))
                .min(Comparator.naturalOrder()) // Get the closest future time
                .orElseThrow();

        long delaySeconds = Duration.between(now, nextRun).getSeconds();

        System.out.println("[Scheduler] Next run at: " + nextRun + " (in " + delaySeconds + "s)");

        // Schedule the task
        scheduler.schedule(() -> {
            runBatchJob("Scheduled Run @ " + nextRun);
            scheduleNextRun(); // Recursive call to keep the cycle going
        }, delaySeconds, TimeUnit.SECONDS);
    }

    public void stop() {
        isRunning = false;
        scheduler.shutdownNow();
    }

    // 1. DATA MODEL: Immutable Event using 'record' to avoid boilerplate.
    public record Event(String id, String payload, LocalDateTime timestamp) {
    }
}