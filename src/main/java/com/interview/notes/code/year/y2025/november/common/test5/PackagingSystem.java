package com.interview.notes.code.year.y2025.november.common.test5;

import java.util.Arrays;
import java.util.List;

public class PackagingSystem {

    // High Priority: Comments explaining logic for every line
    public static int ways(int total, int k) {
        // Define the modulo constant as specified in the requirements (10^9 + 7)
        int MOD = 1000000007;

        // Create a DP array. Index 'i' represents the number of ways to get sum 'i'.
        // Size is total + 1 to accommodate index 0 up to index 'total'.
        int[] dp = new int[total + 1];

        // Base Case: There is 1 way to make a sum of 0 (by choosing no items).
        // This is crucial to start the addition logic for subsequent numbers.
        dp[0] = 1;

        // Loop through each available integer item from 1 up to k (inclusive).
        // We process one item at a time to ensure we count combinations, not permutations.
        for (int item = 1; item <= k; item++) {

            // Loop through the sums starting from the current item value up to the target total.
            // We cannot make a sum smaller than the item itself, so we start at 'j = item'.
            for (int j = item; j <= total; j++) {

                // Current ways to get sum 'j' = (existing ways) + (ways to get sum 'j - item').
                // This adds the possibilities introduced by using the current 'item'.
                dp[j] = (dp[j] + dp[j - item]) % MOD;
            }
        }

        // Return the value at index 'total', which now holds the total calculated ways.
        return dp[total];
    }

    // Simple main method for testing without JUnit
    public static void main(String[] args) {

        // Define a simple Test Case class structure locally for holding data
        /**
         * @param total    The target sum required
         * @param k        The max integer value allowed
         * @param expected The expected result for validation
         */
        record TestCase(int total, int k, int expected) {
            // Constructor to initialize test case
        }

        // List of test cases based on screenshots and edge scenarios
        List<TestCase> tests = Arrays.asList(
                new TestCase(5, 3, 5),      // Sample Case 0 from screenshot
                new TestCase(4, 2, 3),      // Sample Case 1 from screenshot
                new TestCase(8, 2, 5),      // Example from description
                new TestCase(10, 10, 42),   // Generic partition case
                new TestCase(1, 1, 1),      // Minimum constraint edge case
                new TestCase(1000, 1, 1)    // Large data: sum 1000 using only 1s (only 1 way)
        );

        System.out.println("Starting Custom Tests...\n");

        // Use Java 8 Stream to iterate and process tests cleanly
        tests.forEach(test -> {

            // Capture start time for performance check (optional but good for large data)
            long startTime = System.nanoTime();

            // Execute the logic
            int actual = ways(test.total, test.k);

            // Check if actual result matches expected result
            boolean passed = (actual == test.expected);

            // Print formatted output
            System.out.println("Input: total=" + test.total + ", k=" + test.k);
            System.out.println("Expected: " + test.expected + " | Actual: " + actual);
            System.out.println("Result: " + (passed ? "PASS" : "FAIL"));
            System.out.println("------------------------------------------------");
        });

        // Large Data Logic Check (Stress Test)
        // We won't hardcode 'expected' here because it's huge, but we ensure it runs without error.
        System.out.println("Running Stress Test (Total=1000, K=100)...");
        try {
            int stressResult = ways(1000, 100);
            System.out.println("Stress Test Result: " + stressResult);
            System.out.println("Stress Test Status: PASS (Execution successful)");
        } catch (Exception e) {
            System.out.println("Stress Test Status: FAIL (Error: " + e.getMessage() + ")");
        }
    }
}