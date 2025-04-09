package com.interview.notes.code.year.y2025.march.GoldmanSachs.test6;

public class Solution {

    /**
     * Returns the second smallest element in array x.
     * If x has fewer than 2 elements or no distinct second
     * smallest, returns 0.
     */
    public static int secondSmallest(int[] x) {
        if (x == null || x.length < 2) {
            return 0;
        }

        int smallest = Integer.MAX_VALUE;
        int secondSmallest = Integer.MAX_VALUE;

        for (int value : x) {
            if (value < smallest) {
                secondSmallest = smallest;
                smallest = value;
            } else if (value < secondSmallest && value != smallest) {
                secondSmallest = value;
            }
        }

        // If secondSmallest is still max or never updated, return 0
        return (secondSmallest == Integer.MAX_VALUE) ? 0 : secondSmallest;
    }

    /**
     * A simple test method (no JUnit) to demonstrate pass/fail output.
     */
    public static void doTestsPass() {

        // Each test array, followed by the expected result
        int[][] testArrays = {
                {},               // no elements
                {5},              // single element
                {0, 1},           // simplest two
                {2, 2, 2},        // all duplicates
                {-1, -2, -3, -4}, // negative numbers
                {1, 2, 2, 3, 3},  // duplicates but distinct second
                {3, 2, 1},        // second smallest is 2
                {5, 5, 5, 6},     // second smallest is 6
                {10, -1},         // second smallest is 10
        };

        int[] expectedResults = {
                0,   // fewer than 2 elements => 0
                0,   // fewer than 2 elements => 0
                1,   // second smallest is 1
                0,   // no distinct second smallest
                -3,  // second smallest is -3
                2,   // second smallest is 2
                2,   // second smallest is 2
                6,   // second smallest is 6
                10   // second smallest is 10
        };

        System.out.println("Running tests...");
        boolean allPass = true;

        for (int i = 0; i < testArrays.length; i++) {
            int result = secondSmallest(testArrays[i]);
            boolean pass = (result == expectedResults[i]);
            System.out.printf("Test %d: Expect=%d, Got=%d => %s%n",
                    i, expectedResults[i], result, pass ? "PASS" : "FAIL");
            allPass &= pass;
        }

        if (allPass) {
            System.out.println("\nAll tests pass.");
        } else {
            System.out.println("\nSome tests failed.");
        }
    }

    /**
     * Main method to execute the tests.
     */
    public static void main(String[] args) {
        doTestsPass();

        // Example on large data
        // Construct a large array to ensure performance is acceptable
        int size = 1_000_000;
        int[] largeArray = new int[size];
        for (int i = 0; i < size; i++) {
            largeArray[i] = i - (size / 2);  // from negative to positive
        }
        // The second smallest after sorting or single pass should be -(size/2) + 1
        System.out.println("\nSecond smallest in large array: " + secondSmallest(largeArray));
    }
}
