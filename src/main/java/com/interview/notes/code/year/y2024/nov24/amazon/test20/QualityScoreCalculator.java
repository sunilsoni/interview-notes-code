package com.interview.notes.code.year.y2024.nov24.amazon.test20;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QualityScoreCalculator {

    /**
     * Calculates the maximum possible qualityScore by applying exactly one strategy.
     *
     * @param impactFactor The integer used in the strategies.
     * @param ratings      A list of integers representing the ratings.
     * @return The maximum possible qualityScore.
     */
    public static long calculateMaxQualityScore(int impactFactor, List<Integer> ratings) {
        int n = ratings.size();
        if (n == 0) return 0;

        // Initialize DP variables
        long dp0 = ratings.get(0); // No modification
        long amplified = ratings.get(0) * (long) impactFactor; // Amplify
        long adjusted;
        if (ratings.get(0) >= 0) {
            adjusted = Math.floorDiv(ratings.get(0), impactFactor);
        } else {
            adjusted = (long) Math.ceil((double) ratings.get(0) / impactFactor);
        }

        // Initialize the maximum quality score
        long maxScore = Math.max(dp0, Math.max(amplified, adjusted));

        for (int i = 1; i < n; i++) {
            int rating = ratings.get(i);

            // Calculate adjusted rating based on the strategy
            long currentAdjusted;
            if (rating >= 0) {
                currentAdjusted = Math.floorDiv(rating, impactFactor);
            } else {
                currentAdjusted = (long) Math.ceil((double) rating / impactFactor);
            }

            // Update dp0: Maximum subarray sum ending at i without any modification
            dp0 = Math.max(rating, dp0 + rating);

            // Update amplified: Maximum subarray sum ending at i with Amplify
            amplified = Math.max(
                    amplified + rating * (long) impactFactor,
                    Math.max(dp0 + rating * (long) impactFactor, (long) rating * impactFactor)
            );

            // Update adjusted: Maximum subarray sum ending at i with Adjust
            adjusted = Math.max(
                    adjusted + currentAdjusted,
                    Math.max(dp0 + currentAdjusted, currentAdjusted)
            );

            // Update the maximum quality score
            maxScore = Math.max(maxScore, Math.max(dp0, Math.max(amplified, adjusted)));
        }

        return maxScore;
    }

    /**
     * Runs predefined test cases to verify the correctness of the calculateMaxQualityScore method.
     */
    public static void main(String[] args) {
        // Define test cases
        List<TestCase> testCases = new ArrayList<>();

        // Test Case 1
        // impactFactor = 1
        // ratings = [-2, -3, -3, -1]
        // Since impactFactor = 1, Amplify and Adjust do not change the array.
        // Maximum subarray sum is the maximum single element: -1
        testCases.add(new TestCase(
                1,
                Arrays.asList(-2, -3, -3, -1),
                -1
        ));

        // Test Case 2
        // impactFactor = 3
        // ratings = [-4]
        // Adjusting: ceil(-4 /3) = -1
        // Amplifying: -4 *3 = -12
        // Maximum subarray sum after Adjust: -1
        testCases.add(new TestCase(
                3,
                Arrays.asList(-4),
                -1
        ));

        // Test Case 3 (Problem's Example)
        // impactFactor = 3
        // ratings = [5, -3, -3, 2, 4]
        // Amplify [4,5]: [5, -3, -3, 6, 12] => max subarray sum = 6 + 12 = 18
        testCases.add(new TestCase(
                3,
                Arrays.asList(5, -3, -3, 2, 4),
                18
        ));

        // Additional Test Case 4: All positive ratings
        // impactFactor = 2
        // ratings = [1, 2, 3, 4, 5]
        // Amplify entire array: [2,4,6,8,10] => sum = 30
        testCases.add(new TestCase(
                2,
                Arrays.asList(1, 2, 3, 4, 5),
                30
        ));

        // Additional Test Case 5: Mixed ratings with zero
        // impactFactor = 2
        // ratings = [-1, 0, 3, -2, 5]
        // Amplify [3,5]: [-1, 0, 6, -4, 10] => max subarray sum = 6 + (-4) + 10 = 12
        testCases.add(new TestCase(
                2,
                Arrays.asList(-1, 0, 3, -2, 5),
                12
        ));

        // Additional Test Case 6: Single element
        // impactFactor = 5
        // ratings = [10]
        // Amplify: 10 *5 =50
        // Adjust: floor(10/5) =2
        // Maximum is 50
        testCases.add(new TestCase(
                5,
                Arrays.asList(10),
                50
        ));

        // Additional Test Case 7: Large Input
        // impactFactor = 10000
        // ratings = [1] * 200000
        // Amplify entire array: [10000] *200000 => sum = 2,000,000,000
        List<Integer> largeRatings = new ArrayList<>();
        for (int i = 0; i < 200000; i++) {
            largeRatings.add(1);
        }
        testCases.add(new TestCase(
                10000,
                largeRatings,
                200000L * 10000 // 2,000,000,000
        ));

        // Run all test cases
        int passed = 0;
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            long result = calculateMaxQualityScore(tc.impactFactor, tc.ratings);
            if (result == tc.expectedOutput) {
                System.out.println("Test Case " + (i + 1) + ": PASS");
                passed++;
            } else {
                System.out.println("Test Case " + (i + 1) + ": FAIL");
                System.out.println("Expected: " + tc.expectedOutput + ", Got: " + result);
            }
        }

        System.out.println(passed + " out of " + testCases.size() + " test cases passed.");
    }

    /**
     * A helper class to represent a test case.
     */
    static class TestCase {
        int impactFactor;
        List<Integer> ratings;
        long expectedOutput;

        TestCase(int impactFactor, List<Integer> ratings, long expectedOutput) {
            this.impactFactor = impactFactor;
            this.ratings = ratings;
            this.expectedOutput = expectedOutput;
        }
    }
}
