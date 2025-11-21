package com.interview.notes.code.year.y2024.oct24.amazon.test22;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxNegativePnL {

    public static int getMaxNegativePnL(List<Integer> PnL) {
        int n = PnL.size();
        long totalSum = 0;
        for (int profit : PnL) {
            totalSum += profit;
        }

        long[] prefixSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + PnL.get(i);
        }

        int left = 0, right = n;
        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            if (canNegate(prefixSum, totalSum, mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        System.out.println("PnL: " + PnL + " Output: " + left);
        return left;
    }

    private static boolean canNegate(long[] prefixSum, long totalSum, int k) {
        int n = prefixSum.length - 1;
        long maxNegation = 0;
        for (int i = k; i <= n; i++) {
            maxNegation = Math.max(maxNegation, prefixSum[i] - prefixSum[i - k]);
        }
        return maxNegation <= totalSum / 2;
    }

    public static void main(String[] args) {
        // Test cases
        List<List<Integer>> testCases = new ArrayList<>();
        testCases.add(Arrays.asList(1, 1, 1, 1, 1));  // Sample Case 0
        testCases.add(Arrays.asList(5, 2, 3, 5, 2, 3));  // Sample Case 1
        testCases.add(Arrays.asList(5, 3, 1, 2));  // Example from previous question
        testCases.add(List.of(1));  // Edge case: single element
        testCases.add(Arrays.asList(1000000000, 1000000000));  // Large numbers

        // Large input test
        List<Integer> largeInput = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            largeInput.add(1 + (i % 1000));
        }
        testCases.add(largeInput);

        // Run test cases
        for (int i = 0; i < testCases.size(); i++) {
            List<Integer> testCase = testCases.get(i);
            long startTime = System.nanoTime();
            int result = getMaxNegativePnL(testCase);
            long endTime = System.nanoTime();
            double duration = (endTime - startTime) / 1e9; // Convert to seconds
            System.out.printf("Test Case %d: %s (%.6f seconds)%n",
                    i, (checkTestCase(testCase, result) ? "PASS" : "FAIL"), duration);
        }
    }

    private static boolean checkTestCase(List<Integer> PnL, int result) {
        int n = PnL.size();
        long totalSum = 0;
        for (int profit : PnL) {
            totalSum += profit;
        }

        if (result > n) return false;

        long[] prefixSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + PnL.get(i);
        }

        long maxNegation = 0;
        for (int i = result; i <= n; i++) {
            maxNegation = Math.max(maxNegation, prefixSum[i] - prefixSum[i - result]);
        }

        return maxNegation <= totalSum / 2;
    }
}
