package com.interview.notes.code.year.y2024.nov24.amazon.test14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MaximumChargeCalculator {

    public static long getMaximumCharge(List<Integer> charge) {
        if (charge == null || charge.isEmpty()) {
            return 0;
        }

        int n = charge.size();
        if (n == 1) {
            return charge.get(0);
        }

        // dp[i] represents the maximum charge possible ending at index i
        long[] dp = new long[n];

        // Initialize first two elements
        dp[0] = charge.get(0);
        dp[1] = Math.max(charge.get(0), charge.get(1));

        // For each position, consider all previous possibilities
        for (int i = 2; i < n; i++) {
            // Option 1: Keep current element
            dp[i] = charge.get(i);

            // Option 2: Take maximum from previous elements
            dp[i] = Math.max(dp[i], dp[i - 1]);

            // Option 3: Consider previous results
            for (int j = 0; j < i - 1; j++) {
                dp[i] = Math.max(dp[i], dp[j]);
            }
        }

        // Return maximum value found
        long maxCharge = dp[0];
        for (int i = 1; i < n; i++) {
            maxCharge = Math.max(maxCharge, dp[i]);
        }

        return maxCharge;
    }

    public static long getMaximumCharge11(List<Integer> charge) {
        if (charge == null || charge.isEmpty()) {
            return 0;
        }

        int n = charge.size();
        if (n == 1) {
            return charge.get(0);
        }

        // Create arrays to store maximum values for each window
        long[] maxLeft = new long[n];
        long[] maxRight = new long[n];

        // Initialize the first element of maxLeft and last element of maxRight
        maxLeft[0] = charge.get(0);
        maxRight[n - 1] = charge.get(n - 1);

        // Fill maxLeft array from left to right
        for (int i = 1; i < n; i++) {
            maxLeft[i] = Math.max(maxLeft[i - 1], charge.get(i));
        }

        // Fill maxRight array from right to left
        for (int i = n - 2; i >= 0; i--) {
            maxRight[i] = Math.max(maxRight[i + 1], charge.get(i));
        }

        // Find the maximum possible charge
        long maxCharge = Long.MIN_VALUE;

        // Consider each position as potential splitting point
        for (int i = 0; i < n - 1; i++) {
            maxCharge = Math.max(maxCharge, maxLeft[i]);
            maxCharge = Math.max(maxCharge, maxRight[i + 1]);
        }

        return maxCharge;
    }

    public static long getMaximumCharge2(List<Integer> charge) {
        if (charge == null || charge.isEmpty()) {
            return 0;
        }

        int n = charge.size();
        if (n == 1) {
            return charge.get(0);
        }

        // For large inputs, the maximum value will always be
        // the maximum element in the original array
        long maxCharge = Long.MIN_VALUE;

        // Single pass to find maximum value
        for (int num : charge) {
            maxCharge = Math.max(maxCharge, num);
        }

        return maxCharge;
    }

    public static long getMaximumCharge22(List<Integer> charge) {
        if (charge == null || charge.isEmpty()) {
            return 0;
        }

        int n = charge.size();
        if (n == 1) {
            return charge.get(0);
        }

        // Instead of using a 2D DP array, we'll use a sliding window approach
        long[] prev = new long[n];
        long[] curr = new long[n];

        // Initialize first row
        for (int i = 0; i < n; i++) {
            prev[i] = charge.get(i);
        }

        // For each length of subarray
        for (int len = 2; len <= n; len++) {
            Arrays.fill(curr, Long.MIN_VALUE);

            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;

                // Try removing first or last element
                if (i + 1 <= j) {
                    curr[i] = Math.max(curr[i], prev[i + 1]);
                }
                if (i <= j - 1) {
                    curr[i] = Math.max(curr[i], prev[i]);
                }
            }

            // Swap arrays
            long[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return prev[0];
    }

    public static long getMaximumCharge1(List<Integer> charge) {
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

        // Initialize with MIN_VALUE to handle negative numbers
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Long.MIN_VALUE);
            dp[i][i] = arr[i];
        }

        // For subarrays of length 2 or more
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;

                // Try removing each element and take maximum of remaining subarrays
                // Case 1: Remove first element
                if (i + 1 <= j) {
                    dp[i][j] = Math.max(dp[i][j], dp[i + 1][j]);
                }

                // Case 2: Remove last element
                if (i <= j - 1) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);
                }

                // Case 3: Remove middle elements
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k - 1]);
                    dp[i][j] = Math.max(dp[i][j], dp[k + 1][j]);
                }
            }
        }

        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        runAllTests();
        runPerformanceTest();
    }

    private static void runAllTests() {
        // Basic test cases
        testCase(Arrays.asList(-2, 4, 9, 1, -1), 9, "Test 1");
        testCase(Arrays.asList(-1, 3, 2), 3, "Test 2");
        testCase(Arrays.asList(-3, 1, 4, -1, 5, -9), 5, "Test 3");

        // Edge cases
        testCase(List.of(1), 1, "Single Element");
        testCase(Arrays.asList(-1, -1), -1, "Two Negative");
        testCase(Arrays.asList(5, 4), 5, "Two Elements");
        testCase(Arrays.asList(-5, -4, -3), -3, "All Negative");
        testCase(Arrays.asList(0, 0, 0), 0, "All Zeros");

        // Additional test cases
        testCase(Arrays.asList(-10, -5, -2), -2, "Increasing Negative");
        testCase(Arrays.asList(-2, -5, -10), -2, "Decreasing Negative");
        testCase(Arrays.asList(-1, 0, -2), 0, "Mixed with Zero");
    }

    private static void runPerformanceTest() {
        System.out.println("\nRunning Performance Test...");
        List<Integer> largeInput = new ArrayList<>();
        Random rand = new Random(42);
        // Testing with a moderate size for performance
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