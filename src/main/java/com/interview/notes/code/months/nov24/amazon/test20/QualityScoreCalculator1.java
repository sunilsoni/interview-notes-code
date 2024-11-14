package com.interview.notes.code.months.nov24.amazon.test20;

import java.util.*;

public class QualityScoreCalculator1 {

    public static long calculateMaxQualityScore(int impactFactor, List<Integer> ratings) {
        int n = ratings.size();
        int[] originalRatings = new int[n];
        int[] adjustedRatings = new int[n];

        // Copy ratings to an array for faster access
        for (int i = 0; i < n; i++) {
            originalRatings[i] = ratings.get(i);
        }

        // Compute adjusted ratings for the Adjust Ratings strategy
        for (int i = 0; i < n; i++) {
            int rating = originalRatings[i];
            if (rating >= 0) {
                adjustedRatings[i] = (int) Math.floor((double) rating / impactFactor);
            } else {
                adjustedRatings[i] = (int) Math.ceil((double) rating / impactFactor);
            }
        }

        // Compute maximum subarray sum without any modification (no strategy applied)
        long maxNoMod = maxSubarraySum(originalRatings);

        // Compute maximum subarray sum for Amplify Ratings strategy
        long maxAmplify = maxSubarrayWithStrategy(originalRatings, impactFactor, true);

        // Compute maximum subarray sum for Adjust Ratings strategy
        long maxAdjust = maxSubarrayWithStrategy(originalRatings, impactFactor, false);

        // Return the maximum of all three
        return Math.max(maxNoMod, Math.max(maxAmplify, maxAdjust));
    }

    private static long maxSubarraySum(int[] arr) {
        long maxEndingHere = arr[0];
        long maxSoFar = arr[0];
        for (int i = 1; i < arr.length; i++) {
            maxEndingHere = Math.max(arr[i], maxEndingHere + arr[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        return maxSoFar;
    }

    private static long maxSubarrayWithStrategy(int[] ratings, int impactFactor, boolean isAmplify) {
        int n = ratings.length;
        long maxEndNoMod = ratings[0];
        long maxEndMod;
        if (isAmplify) {
            maxEndMod = ratings[0] * (long) impactFactor;
        } else {
            maxEndMod = adjustedValue(ratings[0], impactFactor);
        }
        long maxTotal = Math.max(maxEndNoMod, maxEndMod);

        for (int i = 1; i < n; i++) {
            long rating = ratings[i];
            long modifiedRating;
            if (isAmplify) {
                modifiedRating = rating * impactFactor;
            } else {
                modifiedRating = adjustedValue(rating, impactFactor);
            }

            // Update maxEndNoMod
            maxEndNoMod = Math.max(rating, maxEndNoMod + rating);

            // Update maxEndMod
            long tempMod = Math.max(modifiedRating,
                    Math.max(maxEndNoMod + modifiedRating, maxEndMod + modifiedRating));

            maxEndMod = tempMod;

            // Update maxTotal
            maxTotal = Math.max(maxTotal, Math.max(maxEndNoMod, maxEndMod));
        }
        return maxTotal;
    }

    private static long adjustedValue(long rating, int impactFactor) {
        if (rating >= 0) {
            return (long) Math.floor((double) rating / impactFactor);
        } else {
            return (long) Math.ceil((double) rating / impactFactor);
        }
    }

    public static void main(String[] args) {
        // Test cases
        List<TestCase> testCases = new ArrayList<>();

        // Sample Input 1
        testCases.add(new TestCase(1, Arrays.asList(4, -2, -3, -3, -1), 3L));

        // Sample Input 2
        testCases.add(new TestCase(3, Collections.singletonList(-4), -1L));

        // Custom Test Case
        testCases.add(new TestCase(3, Arrays.asList(5, -3, -3, 2, 4), 20L));

        // Large Input Test Case
        Random random = new Random();
        int largeN = 200000;
        List<Integer> largeRatings = new ArrayList<>(largeN);
        for (int i = 0; i < largeN; i++) {
            largeRatings.add(random.nextInt(200001) - 100000); // Random ratings between -1e5 and 1e5
        }
        testCases.add(new TestCase(10000, largeRatings, null)); // Expected result is not precomputed

        // Run test cases
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            long result = calculateMaxQualityScore(tc.impactFactor, tc.ratings);
            System.out.println("Test Case " + (i + 1) + ": " + (tc.expectedResult == null ? "Computed result = " + result
                    : (result == tc.expectedResult ? "PASS" : "FAIL (Expected " + tc.expectedResult + ", Got " + result + ")")));
        }
    }

    static class TestCase {
        int impactFactor;
        List<Integer> ratings;
        Long expectedResult;

        TestCase(int impactFactor, List<Integer> ratings, Long expectedResult) {
            this.impactFactor = impactFactor;
            this.ratings = ratings;
            this.expectedResult = expectedResult;
        }
    }
}
