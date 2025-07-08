package com.interview.notes.code.year.y2025.july.common.test3;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class ReorderBinaryArray {

    /**
     * Reorders a binary array so that all 1s appear before all 0s.
     * Uses Java 8 streams: two filters and a concatenation.
     *
     * @param array input array of 0s and 1s
     * @return new array with all 1s first, then all 0s
     */
    public static int[] reorderArray(int[] array) {
        // Create a stream over the input, keeping only elements == 1
        IntStream onesStream = Arrays.stream(array)
                                     .filter(i -> i == 1);

        // Create a stream over the input, keeping only elements == 0
        IntStream zerosStream = Arrays.stream(array)
                                      .filter(i -> i == 0);

        // Join the 1s‐stream then the 0s‐stream, collect into a new array
        return IntStream.concat(onesStream, zerosStream)
                        .toArray();
    }

    /**
     * Simple main method to test reorderArray with multiple cases.
     * Prints PASS or FAIL for each, and measures performance on large data.
     */
    public static void main(String[] args) {
        // Define several test cases and their expected outputs
        int[][] testCases = {
            {},                         // empty array
            {1, 1, 1, 1},               // all ones
            {0, 0, 0, 0},               // all zeros
            {1, 0, 1, 1, 0, 0, 1, 0},   // mixed
            {0, 1, 0, 1, 1, 0}          // another mixed
        };
        int[][] expected = {
            {},
            {1, 1, 1, 1},
            {0, 0, 0, 0},
            {1, 1, 1, 1, 0, 0, 0, 0},
            {1, 1, 1, 0, 0, 0}
        };

        // Test each case and report PASS/FAIL
        for (int i = 0; i < testCases.length; i++) {
            int[] input = testCases[i];
            int[] exp   = expected[i];
            int[] out   = reorderArray(input);
            boolean pass = Arrays.equals(out, exp);
            System.out.printf("Test case %d: %s%n", i + 1, pass ? "PASS" : "FAIL");
        }

        // Now test performance on a large random input
        int size = 1_000_000;
        int[] large = new int[size];
        Random rand = new Random();

        // Fill with random 0s and 1s
        for (int i = 0; i < size; i++) {
            large[i] = rand.nextBoolean() ? 1 : 0;
        }

        // Measure execution time
        long start = System.currentTimeMillis();
        reorderArray(large);
        long end   = System.currentTimeMillis();
        System.out.printf("Large input (%d elements) reorder took: %d ms%n",
                          size, (end - start));
    }
}