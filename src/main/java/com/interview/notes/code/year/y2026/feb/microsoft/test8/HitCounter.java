package com.interview.notes.code.year.y2026.feb.microsoft.test8;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.IntStream;

public class HitCounter {

    // 1. Use a Deque (Double-Ended Queue) to store timestamps.
    // Why? It allows efficient O(1) addition to the tail and O(1) removal from the head.
    private final Deque<Integer> hits;

    /*
     * Constructor: Initializes the queue.
     */
    public HitCounter() {
        this.hits = new ArrayDeque<>();
    }

    public static void main(String[] args) {
        System.out.println("Running Final Interview Tests...");

        // ---------------------------------------------------------
        // Example 1: Basic Sequential
        // ---------------------------------------------------------
        var c1 = new HitCounter();
        c1.hit(1);
        c1.hit(2);
        c1.hit(3);
        // Window [4-60, 4] -> [-56, 4]. All 1,2,3 are valid.
        printResult("Example 1", 3, c1.getHits(4));

        // ---------------------------------------------------------
        // Example 2: Multiple Hits at Same Timestamp
        // ---------------------------------------------------------
        var c2 = new HitCounter();
        c2.hit(1);
        c2.hit(1);
        c2.hit(1);
        c2.hit(1);
        // Window [2-60, 2] -> [-58, 2]. All 1s are valid.
        printResult("Example 2", 4, c2.getHits(2));

        // ---------------------------------------------------------
        // Example 3: Boundary Inclusion (The "61" Case)
        // ---------------------------------------------------------
        var c3 = new HitCounter();
        c3.hit(1);
        c3.hit(1);
        c3.hit(1);
        c3.hit(1); // 4 hits at 1
        c3.getHits(2); // Intermediate call (ignore result for this test step)
        c3.hit(61);
        c3.hit(61);
        // Window [61-60, 61] -> [1, 61].
        // Hits at 1 are VALID (1 is not < 1).
        // Hits at 61 are VALID.
        // Total = 4 (from t=1) + 2 (from t=61) = 6.
        printResult("Example 3", 6, c3.getHits(61));

        // ---------------------------------------------------------
        // Example 4: Expiration (The "63" Case)
        // ---------------------------------------------------------
        var c4 = new HitCounter();
        c4.hit(1);
        c4.hit(1);
        c4.hit(1);
        c4.hit(1);
        c4.hit(61);
        c4.hit(62);
        // Window [63-60, 63] -> [3, 63].
        // Hits at 1 are EXPIRED (1 < 3). Remove them.
        // Hits at 61, 62 are VALID.
        // Total = 2.
        printResult("Example 4", 2, c4.getHits(63));

        // ---------------------------------------------------------
        // Additional: Large Data / Capacity Test
        // ---------------------------------------------------------
        // Simulating filling the queue to ensure it doesn't crash on loops
        var cLarge = new HitCounter();
        IntStream.rangeClosed(1, 1000).forEach(cLarge::hit);
        // Window [1000-60, 1000] -> [940, 1000].
        // Inclusive count: 1000 - 940 + 1 = 61 items (approx logic check).
        // Let's rely on the code logic: Remove if < 940.
        // Items 1 to 939 removed. Items 940 to 1000 kept.
        // Count should be 61.
        printResult("Large Data (1000 items)", 61, cLarge.getHits(1000));
    }

    // Helper to print PASS/FAIL status cleanly
    private static void printResult(String testName, int expected, int actual) {
        String status = (expected == actual) ? "✅ PASS" : "❌ FAIL";
        System.out.printf("%-10s | %-35s | Expected: %-3d | Got: %-3d%n",
                status, testName, expected, actual);
    }

    /* -------------------------------------------------------------------------- */
    /* TESTING SECTION (Main Method)                                              */
    /* -------------------------------------------------------------------------- */

    /*
     * Records a hit at the given timestamp.
     * Complexity: O(1)
     */
    public void hit(int timestamp) {
        // Simply append the new timestamp to the end of the queue.
        // Since input is chronological, the queue remains sorted naturally.
        this.hits.add(timestamp);
    }

    /*
     * Returns the number of hits in the past 60 seconds.
     * Logic: Window is [timestamp - 60, timestamp].
     * Complexity: Amortized O(1)
     */
    public int getHits(int timestamp) {
        // Define the cutoff. Any hit strictly LESS than this is expired.
        // Example: If timestamp is 61, cutoff is 1. Hit(1) is valid (1 is not < 1).
        // Example: If timestamp is 63, cutoff is 3. Hit(1) is expired (1 < 3).
        int cutoff = timestamp - 60;

        // Check the oldest hit (head of the queue).
        // If it is smaller than the cutoff, it is outside the window.
        while (!this.hits.isEmpty() && this.hits.peek() < cutoff) {
            this.hits.poll(); // Permanently remove the expired timestamp.
        }

        // The size of the queue is now exactly the count of valid hits.
        return this.hits.size();
    }
}