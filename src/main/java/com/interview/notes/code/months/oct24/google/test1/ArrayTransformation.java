package com.interview.notes.code.months.oct24.google.test1;

public class ArrayTransformation {

    public static int solution(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }

        long moves = A[0]; // Use long to prevent overflow
        int currentMax = A[0];

        for (int i = 1; i < A.length; i++) {
            if (A[i] > currentMax) {
                moves += A[i] - currentMax;
                currentMax = A[i];
            }
        }

        return (int) moves; // Safe to cast back to int as per problem constraints
    }

    public static void main(String[] args) {
        // Test cases
        int[][] testCases = {
                {2, 1, 3},
                {2, 2, 0, 0, 1},
                {5, 4, 2, 4, 1},
                {0},
                {1000000000, 1000000000},
                new int[100000] // Large case with all zeros
        };

        int[] expectedResults = {4, 3, 7, 0, 1000000000, 0};

        for (int i = 0; i < testCases.length; i++) {
            int result = solution(testCases[i]);
            boolean passed = result == expectedResults[i];
            System.out.println("Test case " + (i + 1) + ": " + (passed ? "PASS" : "FAIL"));
            if (!passed) {
                System.out.println("Expected: " + expectedResults[i] + ", Got: " + result);
            }
        }
    }
}
