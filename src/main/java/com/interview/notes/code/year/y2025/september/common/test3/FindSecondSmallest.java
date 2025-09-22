package com.interview.notes.code.year.y2025.september.common.test3;

import java.util.NoSuchElementException;
import java.util.Random;

public class FindSecondSmallest {

    /**
     * Find the second smallest *distinct* value in an array of ints.
     * Throws if there are fewer than two distinct values.
     */
    public static int findSecondSmallest(int[] arr) {
        // 1) Guard against null or too-small arrays
        if (arr == null || arr.length < 2) {
            throw new NoSuchElementException("Need at least two elements for second smallest");
        }

        // 2) Use Integer wrappers so we can track “not yet set” via null
        Integer min = null;         // will hold the smallest seen so far
        Integer secondMin = null;   // will hold the second smallest distinct seen so far

        // 3) One-pass scan through every element
        for (int v : arr) {
            // 3a) If no min yet, or current value is smaller than min
            if (min == null || v < min) {
                secondMin = min;   // old min demotes to secondMin (may be null)
                min = v;           // update min to current
            }
            // 3b) Else if v is strictly larger than min (distinct) and
            //     either secondMin unset or v is smaller than secondMin
            else if (v > min && (secondMin == null || v < secondMin)) {
                secondMin = v;     // promote v to secondMin
            }
            // 3c) Otherwise (v == min, or v >= secondMin), ignore
        }

        // 4) If secondMin is still null, there's no distinct runner-up
        if (secondMin == null) {
            throw new NoSuchElementException("No second smallest distinct element");
        }

        // 5) Return the second smallest distinct value
        return secondMin;
    }

    /**
     * Main runs PASS/FAIL tests for:
     * - small fixed arrays
     * - all same elements
     * - negative numbers
     * - large random data
     * - too-small arrays
     */
    public static void main(String[] args) {
        // --- 1) Small fixed tests ---
        int[][] tests = {
                {1, 3, 2},         // expect 2
                {5, 5, 5},         // all same → exception
                {10, 10, 9},       // expect 10 second-smallest is 10? No: distinct lowest=9, second=10
                {-1, -2, -3},      // expect -2
                {-5, -5, -10},     // distinct values: -10, -5 → expect -5
        };
        // note for {10,10,9}: sorted distinct = [9,10] → second=10
        Integer[] expected = {2, null, 10, -2, -5};

        // run each small test
        for (int i = 0; i < tests.length; i++) {
            try {
                int result = findSecondSmallest(tests[i]);  // compute second-smallest
                // PASS if we expected a value and it matches
                if (expected[i] != null && result == expected[i]) {
                    System.out.println("Test " + (i + 1) + ": PASS");
                } else {
                    System.out.println("Test " + (i + 1) +
                            ": FAIL (expected " + expected[i] + ", got " + result + ")");
                }
            } catch (NoSuchElementException ex) {
                // PASS if we expected an exception (expected[i] == null)
                if (expected[i] == null) {
                    System.out.println("Test " + (i + 1) + ": PASS (caught exception)");
                } else {
                    System.out.println("Test " + (i + 1) +
                            ": FAIL (unexpected exception: " + ex.getMessage() + ")");
                }
            }
        }

        // --- 2) Large-data test ---
        final int N = 1_000_000;             // one million elements
        int[] large = new int[N];
        Random rnd = new Random(42);         // reproducible seed

        // fill with random values from -1_000_000 to +1_000_000
        for (int i = 0; i < N; i++) {
            large[i] = rnd.nextInt(2_000_001) - 1_000_000;
        }
        // force known min and secondMin
        int forcedMin = -2_000_000;
        int forcedSecondMin = -1_999_999;
        large[N / 2] = forcedMin;
        large[N / 2 + 1] = forcedSecondMin;

        // verify large random array
        try {
            int sec = findSecondSmallest(large);
            if (sec == forcedSecondMin) {
                System.out.println("Large-data test: PASS");
            } else {
                System.out.println("Large-data test: FAIL (expected "
                        + forcedSecondMin + ", got " + sec + ")");
            }
        } catch (NoSuchElementException ex) {
            System.out.println("Large-data test: FAIL (exception: " + ex.getMessage() + ")");
        }

        // --- 3) Too-small array test ---
        try {
            findSecondSmallest(new int[]{42});  // only one element
            System.out.println("Too-small test: FAIL (no exception)");
        } catch (NoSuchElementException ex) {
            System.out.println("Too-small test: PASS (caught exception)");
        }
    }
}