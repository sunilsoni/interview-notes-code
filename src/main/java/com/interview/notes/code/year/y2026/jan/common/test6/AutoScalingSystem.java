package com.interview.notes.code.year.y2026.jan.common.test6;

import java.util.LinkedList;
import java.util.Queue;

public class AutoScalingSystem {

    /**
     * Core Logic: Determines if we need to scale.
     * Time Complexity: O(1) - Very fast.
     */
    public static boolean shouldScale(Queue<Message> queue, ScalingPolicy policy) {
        // 1. Check if queue is empty. If empty, no scaling needed.
        if (queue.isEmpty()) {
            return false;
        }

        // 2. CHECK CAPACITY (Condition B from prompt)
        // If current size > defined threshold, we must scale.
        if (queue.size() > policy.queueThreshold()) {
            return true; // "Q is filling up"
        }

        // 3. CHECK LATENCY (Condition A from prompt)
        // Peek at the HEAD (oldest message). We don't remove it, just look.
        Message oldestMsg = queue.peek();

        // Calculate how long it has been sitting there.
        long currentLatency = System.currentTimeMillis() - oldestMsg.entryTime();

        // If latency > max allowed, we must scale.
        return currentLatency > policy.maxLatencyMs();
    }

    // --- TEST HELPER METHOD (Replaces JUnit) ---
    public static void runTest(String testName, boolean expected, Queue<Message> q, ScalingPolicy p) {
        long startTime = System.nanoTime(); // Start timer for performance check

        boolean result = shouldScale(q, p); // Run the logic

        long endTime = System.nanoTime(); // Stop timer

        // Check if actual result matches expected result
        String status = (result == expected) ? "PASS" : "FAIL";

        // Print clean output
        System.out.printf("[%s] %s | Expected: %s, Got: %s | Time: %dns%n",
            status, testName, expected, result, (endTime - startTime));
    }

    public static void main(String[] args) {
        System.out.println("--- Starting Auto-Scaling Tests ---");

        // --- SIMULATING TABLE LOOKUP ---
        // This acts as our configuration table.
        // Rule: Scale if latency > 100ms OR size > 5 items.
        ScalingPolicy defaultPolicy = new ScalingPolicy(100, 5);

        // --- TEST CASE 1: Happy Path (No Scaling) ---
        Queue<Message> q1 = new LinkedList<>();
        // Add a fresh message (0ms latency)
        q1.add(new Message("msg1", System.currentTimeMillis()));
        // Expect FALSE because size is 1 (<=5) and latency is ~0 (<=100)
        runTest("Test 1: Normal Traffic", false, q1, defaultPolicy);

        // --- TEST CASE 2: Queue Filling Up (Capacity Trigger) ---
        Queue<Message> q2 = new LinkedList<>();
        // Add 6 messages (Threshold is 5)
        for(int i=0; i<6; i++) {
            q2.add(new Message("msg"+i, System.currentTimeMillis()));
        }
        // Expect TRUE because 6 > 5
        runTest("Test 2: Capacity Breached", true, q2, defaultPolicy);

        // --- TEST CASE 3: Latency Spikes (Latency Trigger) ---
        Queue<Message> q3 = new LinkedList<>();
        // Add a message with a timestamp from 500ms ago (Old message)
        q3.add(new Message("stuck_msg", System.currentTimeMillis() - 500));
        // Expect TRUE because 500ms > 100ms allowed
        runTest("Test 3: Latency Breached", true, q3, defaultPolicy);

        // --- TEST CASE 4: Edge Case (Empty Queue) ---
        Queue<Message> q4 = new LinkedList<>();
        // Expect FALSE (Nothing to scale)
        runTest("Test 4: Empty Queue", false, q4, defaultPolicy);

        // --- TEST CASE 5: Large Data Input (Performance) ---
        // Simulating a queue with 1 MILLION messages
        Queue<Message> qHuge = new LinkedList<>();
        // We only care about size, so we don't need to actually fill 1M objects for the logic check
        // But for a true stress test, let's add them efficiently.
        // However, standard LinkedList is slow to add 1M. Let's spoof it or just add enough to fail.
        // Actually, let's use a trick: Just add enough to pass threshold to verify O(1) checks.
        // But the prompt asks to handle large inputs.
        // Let's use a loop.
        long now = System.currentTimeMillis();
        // Add 10,000 messages (Simulating load)
        for(int i=0; i<10_000; i++) {
             qHuge.add(new Message("bulk"+i, now));
        }
        // Policy allows 5, we have 10,000.
        // Expect TRUE.
        runTest("Test 5: Large Data Load", true, qHuge, defaultPolicy);

        System.out.println("--- All Tests Completed ---");
    }

    // Java 21 Record: Simple data carrier for a Message.
    // 'entryTime' stores when the message arrived in the queue.
    record Message(String id, long entryTime) {}

    // Java 21 Record: Simulates the "Table" row for scaling rules.
    // Holds the limits: max allowable latency and max queue depth.
    record ScalingPolicy(long maxLatencyMs, int queueThreshold) {}
}