package com.interview.notes.code.year.y2025.June.OCI.test2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RemoveCoveredIntervals {

    /**
     * Filters out intervals that are completely covered by another.
     *
     * @param intervals array of [start, end] intervals
     * @return list of intervals remaining after removal of covered ones
     */
    public static List<int[]> filterCoveredIntervals(int[][] intervals) {
        // Sort intervals by start ascending, then end descending
        int[][] sorted = Arrays.stream(intervals)
                .sorted(Comparator
                        // Compare by start
                        .comparingInt((int[] a) -> a[0])
                        // If starts equal, compare by end descending
                        .thenComparing((a, b) -> Integer.compare(b[1], a[1])))
                .toArray(int[][]::new);

        List<int[]> result = new ArrayList<>();  // To hold non-covered intervals
        int maxEnd = -1;                         // Tracks the largest end seen so far

        // Scan through sorted intervals
        for (int[] iv : sorted) {
            int start = iv[0];
            int end = iv[1];

            // If current end is ≤ maxEnd, this interval is covered by a previous one
            if (end <= maxEnd) {
                // Covered → skip it
                continue;
            }

            // Not covered → include in result
            result.add(iv);
            // Update maxEnd so future intervals know the new coverage boundary
            maxEnd = end;
        }

        return result;
    }

    /**
     * Helper to compare the algorithm's output against an expected array.
     */
    private static boolean matches(int[][] expected, List<int[]> actual) {
        if (expected.length != actual.size()) {
            return false;  // Different sizes → fail
        }
        // Compare each interval in order
        for (int i = 0; i < expected.length; i++) {
            if (expected[i][0] != actual.get(i)[0] ||
                    expected[i][1] != actual.get(i)[1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Formats a list of intervals as a string for reporting.
     */
    private static String format(int[][] arr) {
        return Arrays.stream(arr)
                .map(iv -> "[" + iv[0] + "," + iv[1] + "]")
                .collect(Collectors.joining(", ", "[", "]"));
    }

    private static String format(List<int[]> list) {
        return list.stream()
                .map(iv -> "[" + iv[0] + "," + iv[1] + "]")
                .collect(Collectors.joining(", ", "[", "]"));
    }

    /**
     * Simple main-method testing (no JUnit).
     */
    public static void main(String[] args) {
        // Test 1: Example from prompt
        int[][] input1 = {{1, 4}, {3, 6}, {2, 8}};
        int[][] expected1 = {{1, 4}, {2, 8}};
        List<int[]> out1 = filterCoveredIntervals(input1);
        System.out.println("Test 1: " +
                (matches(expected1, out1) ? "PASS" : "FAIL"));
        if (!matches(expected1, out1)) {
            System.out.println("  Expected: " + format(expected1));
            System.out.println("  Got     : " + format(out1));
        }

        // Test 2: No intervals
        int[][] input2 = {};
        List<int[]> out2 = filterCoveredIntervals(input2);
        System.out.println("Test 2 (empty): " +
                (out2.isEmpty() ? "PASS" : "FAIL"));

        // Test 3: Nested chain
        int[][] input3 = {{1, 10}, {2, 5}, {3, 4}, {6, 9}};
        int[][] expected3 = {{1, 10}};
        List<int[]> out3 = filterCoveredIntervals(input3);
        System.out.println("Test 3 (nested): " +
                (matches(expected3, out3) ? "PASS" : "FAIL"));

        // Test 4: No coverage at all
        int[][] input4 = {{1, 2}, {3, 4}, {5, 6}};
        int[][] expected4 = {{1, 2}, {3, 4}, {5, 6}};
        List<int[]> out4 = filterCoveredIntervals(input4);
        System.out.println("Test 4 (none covered): " +
                (matches(expected4, out4) ? "PASS" : "FAIL"));

        // TODO: For large-data stress testing, you could generate a big random
        // list of intervals here and simply ensure it runs quickly without OOM.
    }
}