package com.interview.notes.code.year.y2026.august.common.test6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main { // Main class

    public static int[][] merge(int[][] intervals) { // Method returns merged intervals

        if (intervals == null || intervals.length == 0) // Handle null or empty input
            return new int[0][]; // Nothing to merge

        int[][] sorted = Arrays.stream(intervals) // Convert input array into a Stream
                .map(int[]::clone) // Copy each interval so original input is not changed
                .sorted(Comparator.comparingInt(a -> a[0])) // Sort using starting value
                .toArray(int[][]::new); // Convert Stream back to int[][]

        List<int[]> result = new ArrayList<>(); // Store final merged intervals

        result.add(sorted[0]); // First interval is our starting interval

        for (int i = 1; i < sorted.length; i++) { // Check remaining intervals

            int[] last = result.getLast(); // Get last interval already added

            int[] current = sorted[i]; // Get current interval

            if (current[0] <= last[1]) { // Check whether current interval overlaps last

                last[1] = Math.max(last[1], current[1]); // Extend end to the larger value

            } else { // No overlap

                result.add(current); // Add current interval separately
            }
        }

        return result.toArray(int[][]::new); // Convert List back into int[][]
    }


    public static void main(String[] args) { // Simple testing without JUnit

        runTest( // Test overlapping intervals
                "Test case 1",
                new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}},
                new int[][]{{1, 6}, {8, 10}, {15, 18}}
        );

        runTest( // Test intervals touching at same point
                "Test case 2",
                new int[][]{{1, 4}, {4, 5}},
                new int[][]{{1, 5}}
        );

        runTest( // Test intervals with no overlap
                "Test case 3",
                new int[][]{{1, 2}, {5, 6}},
                new int[][]{{1, 2}, {5, 6}}
        );

        runTest( // Test single interval
                "Test case 4",
                new int[][]{{1, 10}},
                new int[][]{{1, 10}}
        );

        runTest( // Test interval completely inside another
                "Test case 5",
                new int[][]{{1, 10}, {2, 5}},
                new int[][]{{1, 10}}
        );

        runTest( // Test unsorted input
                "Unsorted input",
                new int[][]{{8, 10}, {1, 3}, {2, 6}},
                new int[][]{{1, 6}, {8, 10}}
        );

        runTest( // Test empty input
                "Empty input",
                new int[][]{},
                new int[][]{}
        );

        int[][] large = new int[100_000][2]; // Create large input with 100,000 intervals

        for (int i = 0; i < large.length; i++) // Fill large test input
            large[i] = new int[]{i, i + 1}; // Every interval touches the next one

        runTest( // All 100,000 intervals should merge into one
                "Large data",
                large,
                new int[][]{{0, 100_000}}
        );

        System.out.println("\nDone"); // All tests completed
    }


    private static void runTest( // Helper method to display PASS or FAIL
            String name, // Name of test
            int[][] input, // Input intervals
            int[][] expected) { // Expected output

        int[][] actual = merge(input); // Run merge method

        boolean passed = Arrays.deepEquals(actual, expected); // Compare nested arrays

        System.out.println( // Print result
                (passed ? "PASS" : "FAIL") + " - " + name
        );

        if (!passed) { // Print details only if test fails

            System.out.println( // Show expected result
                    "Expected: " + Arrays.deepToString(expected)
            );

            System.out.println( // Show actual result
                    "Actual  : " + Arrays.deepToString(actual)
            );
        }
    }
}