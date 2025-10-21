package com.interview.notes.code.year.y2025.september.assesment.test2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MoveZeroesWithTests { // main class encapsulating solution and tests

    public static void moveZeroes(int[] nums) { // method that moves zeros to the end in-place
        int n = nums.length; // get array length
        int lastNonZero = 0; // pointer to place next non-zero element
        for (int i = 0; i < n; i++) { // iterate through all elements
            if (nums[i] != 0) { // when current element is non-zero
                nums[lastNonZero++] = nums[i]; // write it to the next position and advance pointer
            }
        }
        for (int i = lastNonZero; i < n; i++) { // fill remaining positions with zeros
            nums[i] = 0; // set zero
        }
    }

    private static void assertArrayEquals(int[] actual, int[] expected) { // assertion helper for arrays
        if (!Arrays.equals(actual, expected)) { // compare arrays
            throw new AssertionError("Expected " + Arrays.toString(expected) + " but got " + Arrays.toString(actual)); // throw on mismatch
        }
    }

    public static void main(String[] args) { // main method to run tests
        List<TestCase> tests = new ArrayList<>(); // collect tests

        tests.add(new TestCase("example-1", new int[]{0, 1, 0, 3, 12}, new int[]{1, 3, 12, 0, 0})); // example 1
        tests.add(new TestCase("example-2", new int[]{0}, new int[]{0})); // example 2
        tests.add(new TestCase("mixed", new int[]{1, 0, 2, 0, 3, 0, 4}, new int[]{1, 2, 3, 4, 0, 0, 0})); // mixed zeros
        tests.add(new TestCase("all-zeros", IntStream.range(0, 10).map(i -> 0).toArray(), IntStream.range(0, 10).map(i -> 0).toArray())); // all zeros small
        tests.add(new TestCase("no-zeros", IntStream.rangeClosed(1, 10).toArray(), IntStream.rangeClosed(1, 10).toArray())); // no zeros

        // large deterministic random test generation
        final int largeN = 200_000; // large size to stress performance
        Random rnd = new Random(123456); // seeded random for repeatability
        int[] largeInput = IntStream.range(0, largeN).map(i -> (rnd.nextInt(10) == 0) ? 0 : (i + 1)).toArray(); // about 10% zeros
        int[] largeExpected = computeExpectedMoveZeroes(largeInput); // compute expected result
        tests.add(new TestCase("large-random", largeInput, largeExpected)); // add large test

        // very large worst-case: alternating non-zero then zero to check performance
        final int veryLargeN = 300_000; // bigger stress test
        int[] veryLargeInput = IntStream.range(0, veryLargeN).map(i -> (i % 2 == 0) ? (i + 1) : 0).toArray(); // alternating
        int[] veryLargeExpected = computeExpectedMoveZeroes(veryLargeInput); // expected for alternating
        tests.add(new TestCase("very-large-alternating", veryLargeInput, veryLargeExpected)); // add very large

        List<String> results = tests.stream().map(tc -> { // run tests using stream
            int[] arrCopy = Arrays.copyOf(tc.input, tc.input.length); // copy input to preserve original for message
            long start = System.currentTimeMillis(); // time start
            try {
                moveZeroes(arrCopy); // perform in-place operation
                assertArrayEquals(arrCopy, tc.expected); // verify result
                long duration = System.currentTimeMillis() - start; // duration
                return String.format("%s: PASS (len=%d, timeMs=%d)", tc.name, tc.input.length, duration); // pass message
            } catch (Throwable t) {
                long duration = System.currentTimeMillis() - start; // duration on failure
                return String.format("%s: FAIL (len=%d, timeMs=%d) - %s", tc.name, tc.input.length, duration, t.getMessage()); // fail message
            }
        }).collect(Collectors.toList()); // collect results

        results.forEach(System.out::println); // print each result
        long passCount = results.stream().filter(s -> s.contains("PASS")).count(); // count passes
        System.out.printf("Summary: Passed %d out of %d tests%n", passCount, tests.size()); // print summary
    }

    private static int[] computeExpectedMoveZeroes(int[] input) { // helper to compute expected array without modifying original
        int n = input.length; // length
        int[] expected = new int[n]; // new array default zeros
        int idx = 0; // index to fill non-zeros
        for (int v : input) { // iterate input
            if (v != 0) { // when non-zero
                expected[idx++] = v; // place and advance
            }
        }
        return expected; // return expected result (rest are zeros by default)
    }

    /**
     * @param name     test name
     * @param input    input array (will be copied for in-place test)
     * @param expected expected result array after operation
     */
    record TestCase(String name, int[] input, int[] expected) { // simple test case holder
        // constructor
        // assign name
        // assign input
        // assign expected
    }
}