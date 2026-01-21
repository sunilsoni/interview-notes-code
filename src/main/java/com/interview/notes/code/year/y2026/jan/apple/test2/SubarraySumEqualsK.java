package com.interview.notes.code.year.y2026.jan.apple.test2;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class SubarraySumEqualsK { // Class wrapper so we can run from command line.

    static long countSubarraysSumK(int[] nums, int k) { // Method returns long because count can be huge.
        var freq = new HashMap<Long, Integer>(Math.max(16, nums.length * 2)); // Map: prefixSum -> how many times seen; pre-size for speed.
        long prefix = 0L; // Running prefix sum; long avoids overflow when values are big.
        long ans = 0L; // Total count of valid subarrays; long avoids overflow for large n.
        freq.put(0L, 1); // Prefix sum 0 occurs once before we start, allowing subarrays starting at index 0.
        for (int x : nums) { // Scan each number once for O(n) time.
            prefix += x; // Update prefix sum to include current element.
            long need = prefix - (long) k; // We need an earlier prefix sum equal to (currentPrefix - k).
            ans += freq.getOrDefault(need, 0); // Add how many times that needed prefix occurred (each gives a valid subarray).
            freq.merge(prefix, 1, Integer::sum); // Record current prefix sum occurrence count (increment by 1).
        }
        return ans; // Return total number of subarrays whose sum equals k.
    }

    static boolean runTest(TestCase t) { // Runs one test and returns true if it passes.
        long got = countSubarraysSumK(t.nums(), t.k()); // Compute result using our solution.
        boolean ok = got == t.expected(); // Compare computed result with expected.
        System.out.println(t.name() + " => " + (ok ? "PASS" : "FAIL") + " | expected=" + t.expected() + ", got=" + got); // Print pass/fail.
        return ok; // Return status so caller can count passes.
    }

    static int[] makeLargeArray(int n) { // Builds a large input to test performance and big-data behavior.
        var rnd = new Random(1); // Fixed seed so the test is repeatable.
        return rnd.ints(n, -2, 3).toArray(); // Stream API: generate ints in [-2..2] and convert to int[].
    }

    public static void main(String[] args) { // Entry point; runs all tests without JUnit.
        var tests = List.of( // Store multiple test cases in a list for easy iteration.
                new TestCase("Example1 [1,1,1], k=2", new int[]{1, 1, 1}, 2, 2), // Known expected result is 2.
                new TestCase("Example2 [1,2,3], k=3", new int[]{1, 2, 3}, 3, 2), // Subarrays: [1,2] and [3].
                new TestCase("Negatives [1,-1,0], k=0", new int[]{1, -1, 0}, 0, 3), // [1,-1], [0], [1,-1,0].
                new TestCase("All zeros [0,0,0], k=0", new int[]{0, 0, 0}, 0, 6), // n=3 => 3*4/2 = 6 zero-sum subarrays.
                new TestCase("Empty array, k=0", new int[]{}, 0, 0), // No subarrays exist, answer is 0.
                new TestCase("Single hit [5], k=5", new int[]{5}, 5, 1), // Only one subarray equals k.
                new TestCase("Single miss [5], k=0", new int[]{5}, 0, 0) // No subarray equals 0.
        ); // End of fixed tests list.

        long pass = tests.stream().filter(SubarraySumEqualsK::runTest).count(); // Stream API: run tests, count passes.
        System.out.println("Passed " + pass + "/" + tests.size() + " fixed tests."); // Summary for fixed tests.

        int n = 200_000; // Large size to validate speed and memory behavior (big-data case).
        int[] big = makeLargeArray(n); // Generate big array quickly using streams.
        int k = 3; // Choose some k for the large run (any value is fine for stress testing).
        long start = System.nanoTime(); // Start time measurement for performance check.
        long result = countSubarraysSumK(big, k); // Run solution on large input to ensure it completes fast.
        long ms = (System.nanoTime() - start) / 1_000_000; // Convert elapsed time to milliseconds.
        System.out.println("Large test n=" + n + ", k=" + k + " => result=" + result + ", time=" + ms + "ms"); // Print performance info.
    }

    record TestCase(String name, int[] nums, int k, long expected) {
    } // Record keeps test data compact (Java 21 feature).
}
