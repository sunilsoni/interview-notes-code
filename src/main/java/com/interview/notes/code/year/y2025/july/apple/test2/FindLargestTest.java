package com.interview.notes.code.year.y2025.july.apple.test2;

import java.util.*;
import java.util.stream.*;

public class FindLargestTest {

    /**
     * @param numbers Some integer numbers.
     * @return The largest value among the numbers given in parameters.
     */
    public static int findLargest(List<Integer> numbers) {
        // Use a stream to find the max; if empty (shouldn't happen), fall back to MIN_VALUE
        return numbers.stream()
                      .mapToInt(Integer::intValue)
                      .max()
                      .orElse(Integer.MIN_VALUE);
    }

    /** Simple holder for a test case. */
    static class TestCase {
        String name;
        List<Integer> input;
        int expected;

        TestCase(String name, List<Integer> input, int expected) {
            this.name     = name;
            this.input    = input;
            this.expected = expected;
        }
    }

    public static void main(String[] args) {
        //--- small, fixed test cases ---
        List<TestCase> tests = Arrays.asList(
            new TestCase("Example",
                Arrays.asList(42, 10, 75, 3),
                75
            ),
            new TestCase("Single element",
                Collections.singletonList(-7),
                -7
            ),
            new TestCase("All negative",
                Arrays.asList(-5, -20, -1, -9),
                -1
            ),
            new TestCase("Mixed values",
                Arrays.asList(0, 100, 50, 1000, 999),
                1000
            )
        );

        for (TestCase tc : tests) {
            int actual = findLargest(tc.input);
            if (actual == tc.expected) {
                System.out.println(tc.name + ": PASS");
            } else {
                System.out.println(tc.name + ": FAIL"
                    + " — expected=" + tc.expected
                    + ", actual=" + actual);
            }
        }

        //--- large random-data performance test ---
        final int N = 1_000_000;
        Random rnd = new Random(123);
        List<Integer> large = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            large.add(rnd.nextInt());
        }

        long start = System.nanoTime();
        int maxValue = findLargest(large);
        long elapsedMs = (System.nanoTime() - start) / 1_000_000;

        System.out.println("\nLarge‐data test (" + N + " elements):");
        System.out.println("Max value = " + maxValue
                           + "  (computed in " + elapsedMs + " ms)");
    }
}