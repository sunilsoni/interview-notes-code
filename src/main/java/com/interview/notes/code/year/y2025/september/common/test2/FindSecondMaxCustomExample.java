package com.interview.notes.code.year.y2025.september.common.test2;

import java.util.NoSuchElementException;
import java.util.Random;

public class FindSecondMaxCustomExample {

    /**
     * Find the second largest value in an array of positive ints by one-pass iteration.
     * Throws if fewer than 2 distinct values.
     */
    public static int findSecondMaxCustom(int[] arr) {
        // If array is null or has fewer than 2 elements, we cannot find a runner-up
        if (arr == null || arr.length < 2) {
            throw new NoSuchElementException("Need at least 2 elements for second largest");
        }

        // Initialize max and secondMax to the smallest possible so any positive int will exceed them
        int max = Integer.MIN_VALUE;           // holds the largest seen so far
        int secondMax = Integer.MIN_VALUE;     // holds the second largest seen so far

        // Iterate over every element in the array
        for (int i = 0; i < arr.length; i++) {
            int v = arr[i];                     // current element

            // New maximum found?
            if (v > max) {
                secondMax = max;                // old max becomes secondMax
                max = v;                        // current v becomes new max
            }
            // If v is not the max but bigger than secondMax, and distinct from max
            else if (v > secondMax && v < max) {
                secondMax = v;                  // promote v to secondMax
            }
            // Otherwise ignore v (either it's equal to max or too small)
        }

        // If secondMax was never updated, all values were equal or there was only one distinct value
        if (secondMax == Integer.MIN_VALUE) {
            throw new NoSuchElementException("No second largest distinct element");
        }

        return secondMax;                      // return the runner-up
    }

    /**
     * Main method to exercise PASS/FAIL tests, including edge and large-data cases.
     */
    public static void main(String[] args) {
        // --- 1) Small fixed tests ---
        int[][] tests = {
                {1, 3, 2},         // expect 2
                {5, 5, 5},         // expect exception (no runner-up)
                {10, 10, 9},       // expect 9
                {2, 8, 8, 3},      // expect 3      // expect 9
                {1, 1, 1, 1},
        };
        Integer[] expected = {2, null, 9, 3, 1};

        for (int i = 0; i < tests.length; i++) {
            try {
                int result = findSecondMaxCustom(tests[i]);  // compute runner-up
                if (expected[i] != null && result == expected[i]) {
                    System.out.println("Test " + (i + 1) + ": PASS");
                } else {
                    System.out.println("Test " + (i + 1) +
                            ": FAIL (expected " + expected[i] + ", got " + result + ")");
                }
            } catch (NoSuchElementException ex) {
                // expected null means exception is PASS
                if (expected[i] == null) {
                    System.out.println("Test " + (i + 1) + ": PASS (caught exception)");
                } else {
                    System.out.println("Test " + (i + 1) +
                            ": FAIL (unexpected exception: " + ex.getMessage() + ")");
                }
            }
        }

        // --- 2) Large-data test ---
        final int N = 1_000_000;               // one million elements
        int[] large = new int[N];
        Random rnd = new Random(42);           // reproducible random

        // fill with random 1…1_000_000
        for (int i = 0; i < N; i++) {
            large[i] = rnd.nextInt(1_000_000) + 1;
        }
        // force known max and second-max
        int forcedMax = 2_000_000;
        int forcedSecond = 1_500_000;
        large[N / 2] = forcedMax;
        large[N / 2 + 1] = forcedSecond;

        // verify
        try {
            int sec = findSecondMaxCustom(large);
            if (sec == forcedSecond) {
                System.out.println("Large-data test: PASS");
            } else {
                System.out.println("Large-data test: FAIL (expected "
                        + forcedSecond + ", got " + sec + ")");
            }
        } catch (NoSuchElementException ex) {
            System.out.println("Large-data test: FAIL (exception: " + ex.getMessage() + ")");
        }

        // --- 3) Too-small array test ---
        try {
            findSecondMaxCustom(new int[]{42});  // only one element
            System.out.println("Too-small test: FAIL (no exception)");
        } catch (NoSuchElementException ex) {
            System.out.println("Too-small test: PASS (caught exception)");
        }
    }
}