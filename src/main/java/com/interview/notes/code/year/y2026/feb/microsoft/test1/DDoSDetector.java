package com.interview.notes.code.year.y2026.feb.microsoft.test1;

import java.util.*;

public class DDoSDetector {

    /**
     * Detects DDoS attackers using a sliding window approach.
     * @param logs Stream of log entries sorted by time
     * @param window Time window in seconds (M)
     * @param limit Packet count threshold (N)
     * @return Map of {CustomerId -> Timestamp they first exceeded limit}
     */
    public static Map<String, Integer> detect(List<Log> logs, int window, int limit) {
        // Stores the final result: Offending Customer -> Timestamp of detection
        var results = new HashMap<String, Integer>();

        // Sliding window state: Customer -> Queue of timestamps within current window
        var windows = new HashMap<String, Deque<Integer>>();

        // Process each log one by one (Simulating stream processing)
        for (var log : logs) {
            // Optimization: If user already flagged, skip tracking to save CPU/Memory
            if (results.containsKey(log.id)) continue;

            // Get existing queue for user or create a new ArrayDeque (efficient resizing)
            var q = windows.computeIfAbsent(log.id, k -> new ArrayDeque<>());

            // Add the current packet's timestamp to the end of the sliding window
            q.add(log.time);

            // Maintenance: Remove timestamps from the head that are outside the time window
            // Logic: If (current_time - old_time) >= window, the old packet has expired
            while (!q.isEmpty() && log.time - q.peek() >= window) {
                q.poll(); // Efficiently removes the oldest element (O(1))
            }

            // Check Condition: If queue size > Limit, the user is flooding traffic
            if (q.size() > limit) {
                // Record the offender and the specific timestamp they crossed the line
                results.put(log.id, log.time);

                // Memory Cleanup: Remove user from tracking map to free heap space immediately
                windows.remove(log.id);
            }
        }

        // Return the identified attackers
        return results;
    }

    public static void main(String[] args) {
        System.out.println("Starting DDoS Detection Tests (Java 21)...\n");

        // --- Test Case 1: Normal Traffic (Should Pass - No Detection) ---
        var logs1 = List.of(
            new Log(1, "UserA"), new Log(2, "UserA"), new Log(5, "UserA")
        );
        // Window=10s, Threshold=5. UserA has 3. Should stay empty.
        test("Normal Traffic", logs1, 10, 5, Map.of());

        // --- Test Case 2: DDoS Detection (Should Pass - Detect UserA) ---
        var logs2 = List.of(
            new Log(1, "UserA"), new Log(2, "UserA"),
            new Log(3, "UserA"), new Log(4, "UserA")
        );
        // Window=10s, Threshold=3. UserA sends 4. Detect at t=4.
        test("Simple Attack", logs2, 10, 3, Map.of("UserA", 4));

        // --- Test Case 3: Mixed Users & Edge Logic (Should Pass) ---
        var logs3 = List.of(
            new Log(1, "GoodUser"), new Log(2, "BadUser"),
            new Log(2, "BadUser"), new Log(3, "BadUser") // BadUser hits 3 in 2s
        );
        // Window=5s, Threshold=2. BadUser exceeds at t=3. GoodUser safe.
        test("Mixed Traffic", logs3, 5, 2, Map.of("BadUser", 3));

        // --- Test Case 4: Sliding Window Expiration (Should Pass) ---
        // User sends packets at t=1, t=2... then waits... then t=20.
        // Old packets should expire.
        var logs4 = List.of(
            new Log(1, "UserB"), new Log(2, "UserB"),
            new Log(20, "UserB"), new Log(21, "UserB") // Window slides, counts reset effectively
        );
        // Window=5s, Threshold=3. Even with 4 total logs, never >3 inside 5s window.
        test("Window Expiration", logs4, 5, 3, Map.of());

        // --- Test Case 5: Large Data Performance (Should Pass) ---
        System.out.print("Test 5: Large Data (1 Million Logs)... ");
        var hugeLogs = new ArrayList<Log>();
        // Simulate "Attacker" sending 2000 packets at second 500
        for(int i=0; i<2000; i++) hugeLogs.add(new Log(500, "Attacker"));
        // Simulate "SafeUser" sending 1 packet every second for 1000 seconds
        for(int i=0; i<1000; i++) hugeLogs.add(new Log(i, "SafeUser"));

        // Sort by time to mimic stream
        hugeLogs.sort(Comparator.comparingInt(Log::time));

        long start = System.currentTimeMillis();
        var result = detect(hugeLogs, 10, 1000); // 10s window, 1000 limit
        long end = System.currentTimeMillis();

        boolean pass = result.containsKey("Attacker") && !result.containsKey("SafeUser");
        System.out.println((pass ? "PASS" : "FAIL") + " (" + (end - start) + "ms)");
    }

    // --- 5. Testing (Simple Main Method) ---

    // Helper method to validate and print PASS/FAIL clearly
    static void test(String name, List<Log> input, int win, int lim, Map<String, Integer> expected) {
        var actual = detect(input, win, lim);
        boolean passed = actual.equals(expected);
        System.out.printf("Test: %-20s -> %s\n", name, passed ? "PASS" : "FAIL");
        if (!passed) System.out.println("   Expected: " + expected + " Got: " + actual);
    }

    // Java 21 Record: Concise, immutable data carrier for Log entries
    record Log(int time, String id) {}
}