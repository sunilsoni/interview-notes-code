package com.interview.notes.code.year.y2026.feb.microsoft.test1;

import java.util.stream.IntStream;

public class HitCounter {

    // Two arrays of fixed size 60 (one for every second in a minute)
    // 'times' stores the last timestamp recorded at that index.
    // 'hits' stores the count of hits for that specific timestamp.
    private final int[] times;
    private final int[] hits;

    /**
     * Initialize the fixed-size arrays.
     * Space Complexity: O(60) -> O(1) constant space.
     */
    public HitCounter() {
        this.times = new int[60];
        this.hits = new int[60];
    }

    /* -------------------------------------------------------------------------- */
    /* TESTING & VERIFICATION                                                     */
    /* -------------------------------------------------------------------------- */
    public static void main(String[] args) {
        System.out.println("Running Optimized Bucket Solution Tests...");

        // ---------------------------------------------------------
        // Example 1: Basic Sequential (From Prompt)
        // ---------------------------------------------------------
        var c1 = new HitCounter();
        c1.hit(1);
        c1.hit(2);
        c1.hit(3);
        // Window [4-60, 4]. All 1,2,3 are valid.
        printResult("Example 1 (Basic)", 3, c1.getHits(4));

        // ---------------------------------------------------------
        // Example 2: The Screenshot Scenario (Complex Logic)
        // ---------------------------------------------------------
        // Sequence: Hit(1)x4 -> Get(2) -> Hit(61) -> Hit(62) -> Get(63)
        var c2 = new HitCounter();

        // 1. Four hits at timestamp 1
        c2.hit(1); c2.hit(1); c2.hit(1); c2.hit(1);
        // Bucket status: index[1] has time=1, count=4.

        // 2. Get hits at timestamp 2
        // 2 - 1 < 60? Yes. Count = 4.
        printResult("Example 2a (Hit 1x4 -> Get 2)", 4, c2.getHits(2));

        // 3. Hit at 61
        // Index = 61 % 60 = 1.
        // Old data at index 1 (time=1) is overwritten because 61 != 1.
        // Bucket status: index[1] has time=61, count=1. (Old 4 hits are gone!)
        c2.hit(61);

        // 4. Hit at 62
        // Index = 62 % 60 = 2.
        c2.hit(62);

        // 5. Get hits at 63
        // Loop checks index 1: 63 - 61 < 60? Yes. (Add 1)
        // Loop checks index 2: 63 - 62 < 60? Yes. (Add 1)
        // Other indices are empty or old. Total = 2.
        printResult("Example 2b (-> Hit 61, 62 -> Get 63)", 2, c2.getHits(63));

        // ---------------------------------------------------------
        // Example 3: Large Data (Stress Test)
        // ---------------------------------------------------------
        var c3 = new HitCounter();
        // Simulate 100,000 hits arriving sequentially
        IntStream.rangeClosed(1, 100_000).forEach(c3::hit);

        // Only the last 60 seconds (99,941 to 100,000) are kept.
        // The array naturally overwrites old data.
        printResult("Large Data (100k items)", 60, c3.getHits(100_000));
    }

    private static void printResult(String testName, int expected, int actual) {
        String status = (expected == actual) ? "✅ PASS" : "❌ FAIL";
        System.out.printf("%-10s | %-35s | Expected: %-3d | Got: %-3d%n",
            status, testName, expected, actual);
    }

    /**
     * Records a hit.
     * Time Complexity: O(1) - Index access is instant.
     */
    public void hit(int timestamp) {
        // Find the bucket index (0 to 59)
        int index = timestamp % 60;

        // Check if the bucket is holding data for the current second
        if (this.times[index] != timestamp) {
            // If the time stored is different (e.g., from 60s ago), reset the bucket.
            this.times[index] = timestamp;
            this.hits[index] = 1; // Start count at 1
        } else {
            // Same second? Just increment the count.
            this.hits[index]++;
        }
    }

    /**
     * Returns total hits in the past 60 seconds.
     * Time Complexity: O(1) - Always loops exactly 60 times.
     */
    public int getHits(int timestamp) {
        int total = 0;

        // Iterate through all 60 buckets
        for (int i = 0; i < 60; i++) {
            // Check if the data in this bucket is within the 60s window.
            // Formula: current_timestamp - bucket_timestamp < 60
            if (timestamp - this.times[i] < 60) {
                total += this.hits[i];
            }
        }
        return total;
    }
}