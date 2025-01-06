package com.interview.notes.code.year.y2024.dec24.test9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals {

    /**
     * Merges overlapping intervals.
     *
     * @param intervals an array of intervals where each interval is represented as an array [start, end]
     * @return a list of merged intervals
     */
    public static List<int[]> merge(int[][] intervals) {
        // Sort intervals based on the start time
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        List<int[]> merged = new ArrayList<>();

        for (int[] interval : intervals) {
            // If the list of merged intervals is empty or if the current interval does not overlap with the previous
            // one, simply append it.
            if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < interval[0]) {
                merged.add(interval);
            } else {
                // Otherwise, there is overlap, so we merge the current and previous intervals.
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], interval[1]);
            }
        }

        return merged;
    }

    // Main method for testing
    public static void main(String[] args) {
        testMergeIntervals();
    }

    // Method to test merge function with various test cases
    private static void testMergeIntervals() {
        int[][] input1 = {{1, 3}, {2, 4}, {6, 8}, {9, 10}};
        int[][] expected1 = {{1, 4}, {6, 8}, {9, 10}};
        runTest(input1, expected1, "Test Case 1");

        int[][] input2 = {{7, 8}, {1, 5}, {2, 4}, {4, 6}};
        int[][] expected2 = {{1, 6}, {7, 8}};
        runTest(input2, expected2, "Test Case 2");

        // Add more test cases as needed
    }

    // Helper method to run individual test cases
    private static void runTest(int[][] input, int[][] expected, String testName) {
        List<int[]> result = merge(input);
        boolean passed = Arrays.deepEquals(result.toArray(new int[0][]), expected);

        if (passed) {
            System.out.println(testName + ": PASS");
        } else {
            System.out.println(testName + ": FAIL");
        }
    }
}