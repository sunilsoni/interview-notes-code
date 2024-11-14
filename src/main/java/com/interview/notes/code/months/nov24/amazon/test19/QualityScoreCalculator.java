package com.interview.notes.code.months.nov24.amazon.test19;

import java.util.*;

public class QualityScoreCalculator {
    public static void main(String[] args) {
        runAllTests();
    }

    public static long calculateMaxQualityScore(int impactFactor, List<Integer> ratings) {
        if (ratings == null || ratings.isEmpty()) {
            return 0;
        }

        int n = ratings.size();
        long maxScore = calculateMaxSubArraySum(ratings);

        // Try Strategy 1: Amplify
        for (int i = 0; i < n; i++) {
            List<Integer> modifiedRatings = new ArrayList<>(ratings);
            for (int j = i; j < n; j++) {
                // Apply multiplication to current element
                modifiedRatings.set(j, ratings.get(j) * impactFactor);
                maxScore = Math.max(maxScore, calculateMaxSubArraySum(modifiedRatings));
            }
        }

        // Try Strategy 2: Adjust
        for (int i = 0; i < n; i++) {
            List<Integer> modifiedRatings = new ArrayList<>(ratings);
            for (int j = i; j < n; j++) {
                // Apply division to current element
                int rating = ratings.get(j);
                if (rating > 0) {
                    modifiedRatings.set(j, rating / impactFactor);
                } else {
                    modifiedRatings.set(j, (int) Math.ceil((double) rating / impactFactor));
                }
                maxScore = Math.max(maxScore, calculateMaxSubArraySum(modifiedRatings));
            }
        }

        return maxScore;
    }

    private static long calculateMaxSubArraySum(List<Integer> arr) {
        long maxSoFar = arr.get(0);
        long currMax = arr.get(0);

        for (int i = 1; i < arr.size(); i++) {
            currMax = Math.max(arr.get(i), currMax + arr.get(i));
            maxSoFar = Math.max(maxSoFar, currMax);
        }
        return maxSoFar;
    }

    private static void runAllTests() {
        // Test case 1
        testCase(
            Arrays.asList(5, -3, -3, 2, 4),
            3,
            20,
            "Basic test case"
        );

        // Test case 2
        testCase(
            Arrays.asList(-2, -3, -3, -1),
            1,
            -1,
            "All negative numbers"
        );

        // Test case 3
        testCase(
            Arrays.asList(1, -4),
            3,
            3,
            "Small array test"
        );

        // Test case 4: Large data test
        List<Integer> largeInput = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            largeInput.add(i % 2 == 0 ? 100 : -100);
        }
        testCase(
            largeInput,
            2,
            200,
            "Large data test"
        );

        // Edge case: Single element
        testCase(
            Arrays.asList(5),
            2,
            10,
            "Single element test"
        );

        // Edge case: Zero elements
        testCase(
            Arrays.asList(0),
            5,
            0,
            "Zero element test"
        );
    }

    private static void testCase(List<Integer> ratings, int impactFactor, long expected, String testName) {
        long result = calculateMaxQualityScore(impactFactor, ratings);
        boolean passed = result == expected;
        
        System.out.println("Test: " + testName);
        System.out.println("Input: ratings=" + (ratings.size() > 10 ? 
            ratings.subList(0, 5) + "..." : ratings) + ", impactFactor=" + impactFactor);
        System.out.println("Expected: " + expected);
        System.out.println("Got: " + result);
        System.out.println("Status: " + (passed ? "PASS" : "FAIL"));
        System.out.println("--------------------");
    }
}