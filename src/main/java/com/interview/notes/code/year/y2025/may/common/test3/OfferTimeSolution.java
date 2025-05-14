package com.interview.notes.code.year.y2025.may.common.test3;

import java.util.Arrays;

public class OfferTimeSolution {
    
    public static int offerTime(int N, int[] A) {
        // Input validation
        if (N < 1 || N > 1000 || A == null || A.length != N) {
            return -404;
        }
        
        // Sort array in ascending order using Stream API
        int[] sortedPrices = Arrays.stream(A)
                                  .sorted()
                                  .toArray();
        
        // Calculate minimum cost
        int totalCost = 0;
        for (int i = N - 1; i >= 0; i -= 2) {
            totalCost += sortedPrices[i];
        }
        
        return totalCost;
    }

    // Test method
    public static void main(String[] args) {
        // Test Case 1: Given example
        testCase(new int[]{8, 2, 2, 9, 1, 9}, 19, "Basic Test Case");

        // Test Case 2: Minimum input
        testCase(new int[]{1}, 1, "Single Element");

        // Test Case 3: Even number of same prices
        testCase(new int[]{5, 5, 5, 5}, 10, "Same Prices");

        // Test Case 4: Maximum constraints
        int[] largeArray = new int[1000];
        Arrays.fill(largeArray, 10000);
        testCase(largeArray, 5000000, "Large Input");

        // Test Case 5: Invalid input
        testCase(new int[]{}, -404, "Empty Array");

        // Test Case 6: Mixed prices
        testCase(new int[]{100, 50, 30, 20, 10}, 130, "Mixed Prices");
    }

    private static void testCase(int[] input, int expectedOutput, String testName) {
        int result = offerTime(input.length, input);
        String status = result == expectedOutput ? "PASS" : "FAIL";
        
        System.out.println("Test: " + testName);
        System.out.println("Input: " + Arrays.toString(input));
        System.out.println("Expected: " + expectedOutput);
        System.out.println("Got: " + result);
        System.out.println("Status: " + status);
        System.out.println("------------------------");
    }
}
