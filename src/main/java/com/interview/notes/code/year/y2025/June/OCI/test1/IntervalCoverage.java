package com.interview.notes.code.year.y2025.June.OCI.test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class IntervalCoverage {

    public static List<int[]> removeCoveredIntervals(int[][] intervals) {
        // Sort intervals by start time, if equal then by larger end time
        // This helps identify coverage more easily
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) return b[1] - a[1];
            return a[0] - b[0];
        });

        List<int[]> result = new ArrayList<>();
        int currentEnd = 0;

        // Process each interval
        for (int[] interval : intervals) {
            // If current interval's end is greater than tracked end
            // it means this interval is not fully covered
            if (interval[1] > currentEnd) {
                result.add(interval);
                currentEnd = interval[1];
            }
            // If end is smaller or equal, it means this interval
            // is covered by previous intervals
        }

        return result;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        int[][] test1 = {{1, 4}, {3, 6}, {2, 8}};
        testAndPrint("Test 1", test1);

        // Test Case 2: No overlapping intervals
        int[][] test2 = {{1, 2}, {3, 4}, {5, 6}};
        testAndPrint("Test 2", test2);

        // Test Case 3: Complete overlap
        int[][] test3 = {{1, 4}, {1, 3}};
        testAndPrint("Test 3", test3);

        // Test Case 4: Large input test
        int[][] test4 = generateLargeInput(1000);
        testAndPrint("Test 4 (Large Input)", test4);
    }

    private static void testAndPrint(String testName, int[][] intervals) {
        System.out.println("\n" + testName + ":");
        System.out.println("Input: " + Arrays.deepToString(intervals));
        List<int[]> result = removeCoveredIntervals(intervals);
        System.out.println("Output: " + Arrays.deepToString(result.toArray()));
    }

    private static int[][] generateLargeInput(int size) {
        int[][] large = new int[size][2];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            int start = rand.nextInt(1000);
            large[i] = new int[]{start, start + rand.nextInt(100) + 1};
        }
        return large;
    }
}
