package com.interview.notes.code.year.y2025.september.common.test;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;

public class FindMaxExample {

    /**
     * Find the maximum value in a non-empty array using Java 8 streams.
     */
    public static int findMax(int[] arr) {
        // Convert the int[] to an IntStream
        return Arrays.stream(arr)
                // Find the maximum; returns OptionalInt
                .max()
                // If array is empty, throw exception with clear message
                .orElseThrow(() -> new NoSuchElementException("Cannot find max of empty array"));
    }

    /**
     * Simple main method to run PASS/FAIL tests, including a large-data test.
     */
    public static void main(String[] args) {
        // --------------------
        // 1) Small fixed tests
        // --------------------
        int[][] smallTests = {
                {1, 3, 2},           // max = 3
                {5, 5, 5, 5},        // all same → max = 5
                {10},                // single element → max = 10
        };
        int[] smallExpected = {3, 5, 10};

        // Run each small test
        for (int i = 0; i < smallTests.length; i++) {
            int[] test = smallTests[i];         // pick the test array
            int expected = smallExpected[i];    // expected max
            int result = findMax(test);         // compute actual max
            // Print PASS if matches, else FAIL with details
            if (result == expected) {
                System.out.println("Test " + (i + 1) + ": PASS");
            } else {
                System.out.println("Test " + (i + 1) + ": FAIL (expected "
                        + expected + ", got " + result + ")");
            }
        }

        // --------------------
        // 2) Large-data test
        // --------------------
        final int LARGE_SIZE = 1_000_000;      // one million elements
        int[] largeArray = new int[LARGE_SIZE];
        Random rnd = new Random(42);           // fixed seed for reproducibility

        // Fill with random values from 1 to 1_000_000
        for (int i = 0; i < LARGE_SIZE; i++) {
            largeArray[i] = rnd.nextInt(1_000_000) + 1;
        }
        // Inject a known maximum at a fixed position
        int forcedMax = 2_000_000;
        largeArray[LARGE_SIZE / 2] = forcedMax;

        // Compute and verify
        int largeResult = findMax(largeArray);
        if (largeResult == forcedMax) {
            System.out.println("Large-data test: PASS");
        } else {
            System.out.println("Large-data test: FAIL (expected "
                    + forcedMax + ", got " + largeResult + ")");
        }

        // --------------------
        // 3) Empty-array test
        // --------------------
        try {
            findMax(new int[0]);               // should throw
            System.out.println("Empty-array test: FAIL (no exception)");
        } catch (NoSuchElementException ex) {
            System.out.println("Empty-array test: PASS (caught exception)");
        }
    }
}