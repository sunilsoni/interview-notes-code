package com.interview.notes.code.year.y2025.march.tiktok.test2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RiskAwarePartition {

    /**
     * findTotalCost:
     * Computes the minimum possible sum of maximum risk scores
     * when dividing videoRiskScores into subarrays of size at most k.
     *
     * @param videoRiskScores list of integer risk scores
     * @param k               maximum allowed subarray size
     * @return the minimum total cost
     */
    public static int findTotalCost(List<Integer> videoRiskScores, int k) {
        // n is the total number of videos
        int n = videoRiskScores.size();

        // dp[i] will hold the minimum cost for partitioning
        // the first i videos (1-based for convenience)
        int[] dp = new int[n + 1];

        // Base case: no videos means cost = 0
        dp[0] = 0;

        // Precompute risk scores in an array for quick access
        int[] arr = videoRiskScores.stream().mapToInt(Integer::intValue).toArray();

        // Fill dp from 1 to n
        for (int i = 1; i <= n; i++) {
            // Set dp[i] to a large number initially
            dp[i] = Integer.MAX_VALUE;
            int currentMax = Integer.MIN_VALUE;

            // We look back up to k videos to form a subarray
            // that ends at position i
            for (int j = i; j > Math.max(0, i - k); j--) {
                // Update the currentMax for the subarray [j..i]
                currentMax = Math.max(currentMax, arr[j - 1]);

                // dp[j - 1] is the cost before this subarray
                // currentMax is the cost of this subarray
                dp[i] = Math.min(dp[i], dp[j - 1] + currentMax);
            }
        }

        // dp[n] holds the final answer
        return dp[n];
    }

    /**
     * main:
     * Demonstrates how to run multiple test cases and
     * prints PASS/FAIL for each.
     */
    public static void main(String[] args) {
        // List of test cases: each entry has input + expected output
        List<TestCase> testCases = Arrays.asList(
                new TestCase(Arrays.asList(1, 2, 3, 4, 5, 6), 3, 9),
                new TestCase(Arrays.asList(2, 3, 4), 2, 5),
                new TestCase(Arrays.asList(2, 3, 4), 1, 9),
                new TestCase(List.of(5), 1, 5),
                new TestCase(Arrays.asList(5, 5, 5, 5), 2, 10),
                // Additional edge case: k >= n
                new TestCase(Arrays.asList(1, 2, 3, 4), 4, 4),
                // Another check
                new TestCase(Arrays.asList(3, 1, 2, 5, 6), 2, 11)
        );

        // Run each test
        for (TestCase tc : testCases) {
            int result = findTotalCost(tc.scores, tc.k);
            // Compare result with expected
            boolean pass = (result == tc.expected);
            System.out.printf("Input: %s, k=%d | Output: %d | Expected: %d | %s\n",
                    tc.scores, tc.k, result, tc.expected, pass ? "PASS" : "FAIL");
        }

        // Large data test (optional demonstration)
        // Generate a large random input
        int largeSize = 100000;
        List<Integer> largeScores = new ArrayList<>(largeSize);
        Random rand = new Random();
        for (int i = 0; i < largeSize; i++) {
            largeScores.add(rand.nextInt(1000)); // random risk 0..999
        }
        // We won't have an "expected" here, but we can time it
        long start = System.currentTimeMillis();
        int largeResult = findTotalCost(largeScores, 50);
        long end = System.currentTimeMillis();
        System.out.println("Large input test done, result=" + largeResult
                + ", timeTakenMs=" + (end - start));
    }

    // Helper class to store test case data
    static class TestCase {
        List<Integer> scores;
        int k;
        int expected;

        TestCase(List<Integer> scores, int k, int expected) {
            this.scores = scores;
            this.k = k;
            this.expected = expected;
        }
    }
}
