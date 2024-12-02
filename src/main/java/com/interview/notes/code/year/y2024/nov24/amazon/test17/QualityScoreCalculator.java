package com.interview.notes.code.year.y2024.nov24.amazon.test17;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QualityScoreCalculator {

    public static long calculateMaxQualityScore(int impactFactor, List<Integer> ratings) {
        int n = ratings.size();
        long[] dpNo = new long[n];       // Max subarray sum ending at i without any strategy
        long[] dpAmplify = new long[n];  // Max subarray sum ending at i with Amplify strategy
        long[] dpAdjust = new long[n];   // Max subarray sum ending at i with Adjust strategy

        long res = Long.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            int A = ratings.get(i);
            long adjustedA = adjustedValue(A, impactFactor);
            long amplifiedA = (long) A * impactFactor;

            // dpNo[i]: Max subarray sum ending at i without any strategy
            if (i == 0) {
                dpNo[i] = A;
            } else {
                dpNo[i] = Math.max(A, dpNo[i - 1] + A);
            }

            // dpAmplify[i]: Max subarray sum ending at i with Amplify strategy
            if (i == 0) {
                dpAmplify[i] = amplifiedA;
            } else {
                dpAmplify[i] = Math.max(
                        amplifiedA, // Start new Amplify segment
                        Math.max(dpNo[i - 1] + amplifiedA, dpAmplify[i - 1] + A)); // Extend previous
            }

            // dpAdjust[i]: Max subarray sum ending at i with Adjust strategy
            if (i == 0) {
                dpAdjust[i] = adjustedA;
            } else {
                dpAdjust[i] = Math.max(
                        adjustedA, // Start new Adjust segment
                        Math.max(dpNo[i - 1] + adjustedA, dpAdjust[i - 1] + A)); // Extend previous
            }

            // Update result
            res = Math.max(res, Math.max(dpNo[i], Math.max(dpAmplify[i], dpAdjust[i])));
        }

        return res;
    }

    private static long adjustedValue(int rating, int impactFactor) {
        if (rating >= 0) {
            // For positive numbers, use floor division
            return (long) Math.floor((double) rating / impactFactor);
        } else {
            // For negative numbers, use ceiling division
            return (long) Math.ceil((double) rating / impactFactor);
        }
    }

    public static void main(String[] args) {
        // Test cases
        List<TestCase> testCases = new ArrayList<>();

        // Sample Input 1
        testCases.add(new TestCase(1, Arrays.asList(-2, -3, -3, -1), 3L));

        // Sample Input 2
        testCases.add(new TestCase(3, Arrays.asList(-4), -1L));

        // Example from problem description
        testCases.add(new TestCase(3, Arrays.asList(5, -3, -3, 2, 4), 20L));

        // Custom Test Case
        testCases.add(new TestCase(2, Arrays.asList(4, -5, 5, -7, 1), 10L));

        // Edge Case: All negative ratings
        testCases.add(new TestCase(5, Arrays.asList(-1, -2, -3, -4, -5), -1L));

        // Edge Case: All positive ratings
        testCases.add(new TestCase(2, Arrays.asList(1, 2, 3, 4, 5), 30L));

        // Run test cases
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            long result = calculateMaxQualityScore(tc.impactFactor, tc.ratings);
            if (tc.expectedResult == null) {
                System.out.println("Test Case " + (i + 1) + ": Computed result = " + result);
            } else if (result == tc.expectedResult) {
                System.out.println("Test Case " + (i + 1) + ": PASS");
            } else {
                System.out.println("Test Case " + (i + 1) + ": FAIL (Expected " + tc.expectedResult + ", Got " + result + ")");
            }
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
