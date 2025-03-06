package com.interview.notes.code.year.y2025.march.jpmc.test1;

import java.util.*;

// Class containing the solution logic and test cases
public class ArraySplitSum {

    public static int splitIntoTwo(List<Integer> arr) {
        long totalSum = 0;
        for (int num : arr) {
            totalSum += num;
        }

        long leftSum = 0;
        int ways = 0;

        // We only go up to arr.size() - 1 because
        // both subarrays must be non-empty.
        for (int i = 0; i < arr.size() - 1; i++) {
            leftSum += arr.get(i);
            // Check if left sum is strictly greater than the right sum.
            if (2 * leftSum > totalSum) {
                ways++;
            }
        }

        return ways;
    }


    // Main method to run test cases and verify if pass or fail
    public static void main(String[] args) {
        // Provided test cases
        runTest(Arrays.asList(10, -5, 6), 1);
        runTest(Arrays.asList(-3, -2, 1, 20, -30), 2);
        runTest(Arrays.asList(10, 4, -8, 7), 2);

        // Edge case: Large input
        List<Integer> largeTest = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            largeTest.add(i % 2 == 0 ? 1 : -1);  // Alternating positive and negative
        }
        runTest(largeTest, 49999);

        // Edge case: All negative numbers
        runTest(Arrays.asList(-10, -20, -30, -40, -50), 0);
    }

    // Helper method to execute test cases
    private static void runTest(List<Integer> arr, int expected) {
        int result = splitIntoTwo(arr);
        System.out.println("Test " + (result == expected ? "PASSED" : "FAILED") +
                ": Expected = " + expected + ", Actual = " + result);
    }
}