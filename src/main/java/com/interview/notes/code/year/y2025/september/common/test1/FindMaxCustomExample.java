package com.interview.notes.code.year.y2025.september.common.test1;

import java.util.NoSuchElementException;
import java.util.Random;

public class FindMaxCustomExample {

    /**
     * Find the maximum value in a non‐empty array by manual iteration.
     */
    public static int findMaxCustom(int[] arr) {
        // Check for null or zero‐length input and fail fast
        if (arr == null || arr.length == 0) {
            throw new NoSuchElementException("Cannot find max of empty array");
        }

        // Start with the first element as the provisional max
        int max = arr[0];

        // Iterate from the second element through the end
        for (int i = 1; i < arr.length; i++) {
            // If the current element exceeds our provisional max, update it
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        // After scanning all elements, return the final max
        return max;
    }

    /**
     * Main method to exercise PASS/FAIL tests, including large‐data and empty‐array cases.
     */
    public static void main(String[] args) {
        // --- 1) Small fixed tests ---
        int[][] smallTests = {
                {1, 3, 2},        // expect 3
                {5, 5, 5, 5},     // expect 5
                {10}              // expect 10
        };
        int[] smallExpected = {3, 5, 10};

        // Run each small test
        for (int i = 0; i < smallTests.length; i++) {
            int[] test = smallTests[i];          // grab the test array
            int expected = smallExpected[i];     // grab the expected result
            int result = findMaxCustom(test);    // compute the max manually

            // Compare and print PASS/FAIL
            if (result == expected) {
                System.out.println("Test " + (i + 1) + ": PASS");
            } else {
                System.out.println("Test " + (i + 1) +
                        ": FAIL (expected " + expected + ", got " + result + ")");
            }
        }

        // --- 2) Large‐data test ---
        final int LARGE_SIZE = 1_000_000;        // one million elements
        int[] largeArray = new int[LARGE_SIZE];
        Random rnd = new Random(42);             // fixed seed for repeatability

        // Populate with random values 1…1,000,000
        for (int i = 0; i < LARGE_SIZE; i++) {
            largeArray[i] = rnd.nextInt(1_000_000) + 1;
        }
        // Inject a known max in the middle
        int forcedMax = 2_000_000;
        largeArray[LARGE_SIZE / 2] = forcedMax;

        // Compute and verify
        int largeResult = findMaxCustom(largeArray);
        if (largeResult == forcedMax) {
            System.out.println("Large‐data test: PASS");
        } else {
            System.out.println("Large‐data test: FAIL (expected " +
                    forcedMax + ", got " + largeResult + ")");
        }

        // --- 3) Empty‐array test ---
        try {
            findMaxCustom(new int[0]);          // should throw
            System.out.println("Empty‐array test: FAIL (no exception)");
        } catch (NoSuchElementException ex) {
            System.out.println("Empty‐array test: PASS (caught exception)");
        }
    }
}