package com.interview.notes.code.year.y2025.may.codesignal.test6;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

//100% WORKING
public class RatingTracker {

    /**
     * Computes [highestRatingEver, currentRating] given a list of rating diffs.
     * Starts at 1500, applies each diff in order, and tracks the max seen.
     */
    public static int[] solution(int[] diffs) {
        // build prefix sums: prefix[i] = rating after i changes
        int[] prefix = new int[diffs.length + 1];
        prefix[0] = 1500;
        for (int i = 0; i < diffs.length; i++) {
            prefix[i + 1] = prefix[i] + diffs[i];
        }
        // highest rating ever is the max of all prefix sums
        int highest = Arrays.stream(prefix).max().orElse(1500);
        // current rating is the last prefix sum
        int current = prefix[prefix.length - 1];
        return new int[]{highest, current};
    }

    /**
     * Simple helper to compare two int arrays for equality.
     */
    private static boolean equals(int[] a, int[] b) {
        return Arrays.equals(a, b);
    }

    public static void main(String[] args) {
        class Test {
            final String name;
            final int[] diffs;
            final int[] expected;

            Test(String name, int[] diffs, int[] expected) {
                this.name = name;
                this.diffs = diffs;
                this.expected = expected;
            }
        }

        List<Test> tests = Arrays.asList(
                new Test("Example 1",
                        new int[]{100, -200, 350, 100, -600},
                        new int[]{1850, 1250}),
                new Test("Example 2 (no changes)",
                        new int[]{},
                        new int[]{1500, 1500}),
                new Test("All positive small",
                        new int[]{10, 20, 30},
                        new int[]{1560, 1560}),
                new Test("All negative small",
                        new int[]{-100, -200, -300},
                        new int[]{1500, 900}),
                new Test("Single change",
                        new int[]{500},
                        new int[]{2000, 2000}),
                new Test("Dip then rise",
                        new int[]{-1000, +2000},
                        new int[]{2500, 1500}),
                new Test("Max-size all +1",
                        // 1000 changes of +1
                        IntStream.range(0, 1000).map(i -> 1).toArray(),
                        new int[]{2500, 2500}),
                new Test("Max-size all -1",
                        // 1000 changes of -1
                        IntStream.range(0, 1000).map(i -> -1).toArray(),
                        new int[]{1500, 500})
        );

        for (Test t : tests) {
            int[] actual = solution(t.diffs);
            boolean pass = equals(actual, t.expected);
            System.out.printf("%-20s : %s  (expected=%s, got=%s)%n",
                    t.name,
                    pass ? "PASS" : "FAIL",
                    Arrays.toString(t.expected),
                    Arrays.toString(actual)
            );
        }
    }
}