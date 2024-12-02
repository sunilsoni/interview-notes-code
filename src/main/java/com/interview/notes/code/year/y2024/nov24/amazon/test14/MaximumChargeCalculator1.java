package com.interview.notes.code.year.y2024.nov24.amazon.test14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MaximumChargeCalculator1 {

    public static long getMaximumCharge(List<Integer> charge) {
        if (charge == null || charge.isEmpty()) {
            return 0;
        }

        int n = charge.size();
        if (n == 1) {
            return charge.get(0);
        }

        // Convert to array for faster access
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = charge.get(i);
        }

        // dp[i][j] represents the maximum charge possible 
        // for subarray from index i to j
        long[][] dp = new long[n][n];

        // Initialize diagonals (single elements)
        for (int i = 0; i < n; i++) {
            dp[i][i] = arr[i];
        }

        // For pairs of elements
        for (int i = 0; i < n - 1; i++) {
            dp[i][i + 1] = Math.max(arr[i], arr[i + 1]);
        }

        // For subarrays of length 3 or more
        for (int len = 3; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;

                // Consider each possible last element to remove
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j],
                            Math.max(dp[i][k - 1], dp[k + 1][j]));
                }

                // Consider removing elements at ends
                dp[i][j] = Math.max(dp[i][j],
                        Math.max(dp[i + 1][j], dp[i][j - 1]));
            }
        }

        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        runAllTests();
        runPerformanceTest();
    }

    private static void runAllTests() {
        // Test Case 1
        testCase(Arrays.asList(-2, 4, 9, 1, -1), 9, "Test 1");

        // Test Case 2
        testCase(Arrays.asList(-1, 3, 2), 3, "Test 2");

        // Test Case 3
        testCase(Arrays.asList(-3, 1, 4, -1, 5, -9), 5, "Test 3");

        // Edge Cases
        testCase(Arrays.asList(1), 1, "Single Element");
        testCase(Arrays.asList(-1, -1), -1, "Two Negative");
        testCase(Arrays.asList(5, 4), 5, "Two Elements");
        testCase(Arrays.asList(-5, -4, -3), -3, "All Negative");
        testCase(Arrays.asList(0, 0, 0), 0, "All Zeros");
    }

    private static void runPerformanceTest() {
        System.out.println("\nRunning Performance Test...");
        List<Integer> largeInput = new ArrayList<>();
        Random rand = new Random(42);
        // Testing with a smaller size for performance
        int testSize = 1000;
        for (int i = 0; i < testSize; i++) {
            largeInput.add(rand.nextInt(2000) - 1000);
        }

        long startTime = System.nanoTime();
        long result = getMaximumCharge(largeInput);
        long endTime = System.nanoTime();

        double executionTimeMs = (endTime - startTime) / 1_000_000.0;
        System.out.printf("Large input (n=%d) processed in %.2f ms\n",
                testSize, executionTimeMs);
        System.out.println("Result: " + result);
    }

    private static void testCase(List<Integer> input, long expected, String testName) {
        try {
            long startTime = System.nanoTime();
            long result = getMaximumCharge(input);
            long endTime = System.nanoTime();

            boolean passed = result == expected;
            System.out.printf("Test '%s': %s (%.3f ms)\n",
                    testName,
                    passed ? "PASSED" : "FAILED",
                    (endTime - startTime) / 1_000_000.0);

            if (!passed) {
                System.out.printf("  Expected: %d\n  Got: %d\n", expected, result);
                System.out.println("  Input: " + input);
            }
        } catch (Exception e) {
            System.out.printf("Test '%s': FAILED with exception\n", testName);
            e.printStackTrace();
        }
    }
}