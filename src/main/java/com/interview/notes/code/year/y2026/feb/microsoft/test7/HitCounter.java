package com.interview.notes.code.year.y2026.feb.microsoft.test7;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.IntStream;

public class HitCounter {

    // Store timestamps in a Deque. We use Deque because we append to tail and remove from head.
    // Deque is faster than a standard List for removing from the front (O(1) vs O(N)).
    private final Deque<Integer> hitQueue;

    /*
     * Constructor initializes the data structure.
     */
    public HitCounter() {
        this.hitQueue = new ArrayDeque<>(); // ArrayDeque is a resizable-array implementation of Deque
    }

    public static void main(String[] args) {
        System.out.println("Running HitCounter Tests...");

        // ==========================================
        // TEST CASE 1: The New Screenshot Example
        // ==========================================
        // Scenario: Hit(1)x4 -> Get(2) -> Hit(61) -> Hit(62) -> Get(63)
        var counterNew = new HitCounter(); // Use 'var' for clean syntax

        // 1. Record 4 hits at timestamp 1
        counterNew.hit(1);
        counterNew.hit(1);
        counterNew.hit(1);
        counterNew.hit(1);

        // 2. Check hits at timestamp 2.
        // Window is [2-59, 2] -> timestamps <= 2 are valid.
        // All four '1's are valid. Expect: 4.
        int resNew1 = counterNew.getHits(2);
        printTestResult("New Example Part 1 (Hit 1x4 -> Get 2)", 4, resNew1);

        // 3. Add more hits
        counterNew.hit(61);
        counterNew.hit(62);

        // 4. Check hits at timestamp 63.
        // Window logic: Current=63. Cutoff is 63 - 60 = 3.
        // Any timestamp <= 3 is EXPIRED.
        // The '1's are expired (1 <= 3).
        // '61' and '62' are valid. Expect: 2.
        int resNew2 = counterNew.getHits(63);
        printTestResult("New Example Part 2 (-> Hit 61, 62 -> Get 63)", 2, resNew2);


        // ==========================================
        // TEST CASE 2: Original Basic Example
        // ==========================================
        // Scenario: Hit(1) -> Hit(2) -> Hit(3) -> Get(4). Expect 3.
        var counter1 = new HitCounter();
        counter1.hit(1); // Hit at 1
        counter1.hit(2); // Hit at 2
        counter1.hit(3); // Hit at 3
        int result1 = counter1.getHits(4);
        printTestResult("Basic Test (1,2,3 -> Get 4)", 3, result1);

        // ==========================================
        // TEST CASE 3: Large Data & Performance
        // ==========================================
        // Scenario: 100,000 sequential hits.
        // Logic: Queue should clean itself up and never grow infinitely.
        var counterLarge = new HitCounter();
        int largeInputSize = 100_000;

        // Simulate a stream of 100k hits
        IntStream.rangeClosed(1, largeInputSize).forEach(t -> {
            counterLarge.hit(t);
        });

        // At t=100,000, valid window is [99941 to 100000]. Size should be 60.
        int resultLarge = counterLarge.getHits(largeInputSize);
        printTestResult("Large Data (100k hits)", 60, resultLarge);
    }

    // Simple helper to print formatted PASS/FAIL results
    private static void printTestResult(String testName, int expected, int actual) {
        String status = (expected == actual) ? "PASS" : "FAIL";
        System.out.printf("[%s] %s: Expected %d, Got %d%n", status, testName, expected, actual);
    }

    /* -------------------------------------------------------------------------- */
    /* TESTING SECTION (MAIN METHOD)                                              */
    /* -------------------------------------------------------------------------- */

    /*
     * Records a hit at the given timestamp.
     * We simply add the timestamp to our queue.
     */
    public void hit(int timestamp) {
        // Add timestamp to the end of the queue.
        // Multiple hits at the same timestamp are allowed (e.g., Hit(1), Hit(1)).
        this.hitQueue.add(timestamp);
    }

    /*
     * Returns the number of hits in the past 60 seconds relative to the given timestamp.
     */
    public int getHits(int timestamp) {
        // We need to remove hits that are older than 60 seconds.
        // Logic: if (current_time - old_hit_time) >= 60, the hit is expired.
        // Loop checks the head (peek) without removing it first.
        while (!this.hitQueue.isEmpty() && timestamp - this.hitQueue.peek() >= 60) {

            // If the condition is true, the hit at the front is too old.
            // We permanently remove it from the queue using poll().
            this.hitQueue.poll();
        }

        // The queue now contains ONLY valid hits within the 60s window.
        // Return the count of remaining elements.
        return this.hitQueue.size();
    }
}