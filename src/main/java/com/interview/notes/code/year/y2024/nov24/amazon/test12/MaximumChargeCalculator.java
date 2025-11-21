package com.interview.notes.code.year.y2024.nov24.amazon.test12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MaximumChargeCalculator {

    // Main solution method
    public static long getMaximumCharge(List<Integer> charge) {
        if (charge == null || charge.isEmpty()) {
            return 0;
        }

        int n = charge.size();
        if (n == 1) {
            return charge.get(0);
        }

        // Create dp array to store maximum possible charge
        long[][] dp = new long[n][n];

        // Initialize dp array with individual charges
        for (int i = 0; i < n; i++) {
            dp[i][i] = charge.get(i);
        }

        // Calculate maximum charge for different lengths
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                dp[i][j] = Long.MIN_VALUE;

                // Try removing each element between i and j
                for (int k = i; k <= j; k++) {
                    long leftSum = (k > i) ? dp[i][k - 1] : 0;
                    long rightSum = (k < j) ? dp[k + 1][j] : 0;
                    long mergedSum = (k > i && k < j) ? leftSum + rightSum :
                            (k == i) ? rightSum : leftSum;
                    dp[i][j] = Math.max(dp[i][j], mergedSum);
                }
            }
        }

        return dp[0][n - 1];
    }

    // Test method
    public static void main(String[] args) {
        // Test cases
        runTest(1, Arrays.asList(-2, 4, 9, 1, -1), 9);
        runTest(2, Arrays.asList(-1, 3, 2), 3);
        runTest(3, Arrays.asList(-3, 1, 4, -1, 5, -9), 9);

        // Edge cases
        runTest(4, List.of(1), 1);
        runTest(5, Arrays.asList(-1, -1), -1);

        // Large input test
        List<Integer> largeInput = generateLargeInput(200000);
        long startTime = System.currentTimeMillis();
        long result = getMaximumCharge(largeInput);
        long endTime = System.currentTimeMillis();
        System.out.println("Large input test completed in " + (endTime - startTime) + "ms");
        System.out.println("Result for large input: " + result);
    }

    // Helper method to run tests
    private static void runTest(int testNumber, List<Integer> input, long expectedOutput) {
        try {
            long startTime = System.nanoTime();
            long result = getMaximumCharge(input);
            long endTime = System.nanoTime();

            boolean passed = result == expectedOutput;
            System.out.printf("Test #%d: %s (Time: %.3fms)\n",
                    testNumber,
                    passed ? "PASSED" : "FAILED",
                    (endTime - startTime) / 1_000_000.0);

            if (!passed) {
                System.out.printf("  Expected: %d\n  Got: %d\n", expectedOutput, result);
            }
        } catch (Exception e) {
            System.out.printf("Test #%d: FAILED (Exception: %s)\n", testNumber, e.getMessage());
            e.printStackTrace();
        }
    }

    // Helper method to generate large input
    private static List<Integer> generateLargeInput(int size) {
        List<Integer> input = new ArrayList<>(size);
        Random random = new Random(42); // Fixed seed for reproducibility
        for (int i = 0; i < size; i++) {
            input.add(random.nextInt(2000000000) - 1000000000);
        }
        return input;
    }
}