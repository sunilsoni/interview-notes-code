package com.interview.notes.code.year.y2026.feb.microsoft.test2;

import java.util.*;

public class DDoSDefense {

    public static void main(String[] args) {
        System.out.println("Running Real-Time Stream Tests (Unsorted Support)...");

        // --- Test 1: Unsorted / Out-of-Order Arrival ---
        // Scenario: A "late" packet (time 8) arrives after time 10.
        // Window: 5s. Current Time: 10. (Window is 5 to 10).
        // 8 is inside [5-10], so it should count!
        var unsortedLogs = List.of(
            new Log(10, "UserA"), // Sets global time to 10
            new Log(8, "UserA"),  // Late packet! Should be accepted.
            new Log(9, "UserA")   // Another packet. Total 3 in window.
        );
        // Window 5, Limit 2. UserA has 3 valid packets (8, 9, 10). Should Fail.
        runTest("Unsorted Late Packet", unsortedLogs, 5, 2, Map.of("UserA", 9));

        // --- Test 2: Expired Late Packet ---
        // Scenario: Packet time 2 arrives when Global Time is 10.
        // Window 5. Valid range: 5-10.
        // Packet 2 is < 5. It should be discarded immediately.
        var expiredLogs = List.of(
            new Log(10, "UserB"), // Global time = 10
            new Log(2, "UserB"),  // Ancient packet. Ignored.
            new Log(9, "UserB")   // Valid. Count = 2 (10, 9).
        );
        // Limit 2. Count is 2. No Attack.
        runTest("Expired Late Packet", expiredLogs, 5, 2, Map.of());

        // --- Test 3: Large Scale Performance ---
        // 1 Million logs, mixed order.
        System.out.print("Test: Large Data Performance... ");
        var hugeLogs = new ArrayList<Log>();
        for (int i = 0; i < 1000000; i++) {
            // Simulate slight jitter (random unsorted-ness)
            hugeLogs.add(new Log(i + (i % 5 == 0 ? -2 : 0), "StreamUser"));
        }
        long start = System.currentTimeMillis();
        detectDDoS(hugeLogs, 10, 2000000); // Should run fast
        System.out.println("DONE (" + (System.currentTimeMillis() - start) + "ms)");
    }

    /**
     * Real-time DDoS detection handling unsorted streams.
     * Complexity: O(N log K) where K is the number of packets inside a window.
     */
    static Map<String, Integer> detectDDoS(List<Log> stream, int window, int limit) {
        var results = new HashMap<String, Integer>();

        // PriorityQueue (Min-Heap): Keeps oldest timestamp at the top (HEAD).
        // Auto-sorts insertions (O(log K)), handling out-of-order packets.
        var history = new HashMap<String, PriorityQueue<Integer>>();

        // Tracks the absolute latest time seen in the entire stream (The "Wall Clock")
        int maxTimeSeen = 0;

        for (var log : stream) {
            // Optimization: Skip already flagged users
            if (results.containsKey(log.id)) continue;

            // 1. Update Global Clock
            // Even if this packet is old, we assume time generally moves forward.
            maxTimeSeen = Math.max(maxTimeSeen, log.time);

            // 2. Get/Create User's Min-Heap
            var pq = history.computeIfAbsent(log.id, k -> new PriorityQueue<>());

            // 3. Add current timestamp (Auto-sorted into correct position)
            pq.add(log.time);

            // 4. Cleanup Logic (The "Sliding Window")
            // We remove packets relative to the GLOBAL Max Time, not the packet's time.
            // Formula: If (CurrentGlobalTime - PacketTime) >= Window, it's expired.
            while (!pq.isEmpty() && (maxTimeSeen - pq.peek()) >= window) {
                pq.poll(); // Efficiently remove oldest (O(log K))
            }

            // 5. Detection
            if (pq.size() > limit) {
                results.put(log.id, log.time); // Record detection time
                history.remove(log.id); // Free memory
            }
        }
        return results;
    }

    // Helper for Clean Output
    static void runTest(String name, List<Log> logs, int w, int l, Map<String, Integer> expected) {
        var actual = detectDDoS(logs, w, l);
        System.out.printf("Test [%-20s]: %s\n", name, actual.equals(expected) ? "PASS" : "FAIL");
        if (!actual.equals(expected)) System.out.println("   Exp: " + expected + " Got: " + actual);
    }

    // Java 21 Record: Concise data carrier for stream entries
    record Log(int time, String id) {}
}