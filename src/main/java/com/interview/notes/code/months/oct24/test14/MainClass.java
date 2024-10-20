package com.interview.notes.code.months.oct24.test14;

import java.util.*;

public class MainClass {

    public static void main(String[] args) {
        // Test cases
        int[][] testQueries = {
            {2, 1, 3},
            {1, 3, 0, 4},
            {1},
            {1, 3, 5, 7, 9}, // Non-contiguous houses
            {1, 2, 3, 4, 5}, // Build contiguous houses
            // Large test case
            generateLargeTestCase(100000)
        };

        int[][] expectedOutputs = {
            {1, 2, 3},
            {1, 1, 2, 2},
            {1},
            {1, 1, 1, 1, 1},
            {1, 2, 3, 4, 5},
            // Expected output for the large test case will be tested by checking if the code runs without error
            null
        };

        for (int i = 0; i < testQueries.length; i++) {
            int[] queries = testQueries[i];
            int[] expected = expectedOutputs[i];
            int[] result = solution(queries);

            if (expected != null) {
                if (arraysEqual(result, expected)) {
                    System.out.println("Test case " + (i + 1) + " PASSED.");
                } else {
                    System.out.println("Test case " + (i + 1) + " FAILED.");
                    System.out.println("Expected: " + arrayToString(expected));
                    System.out.println("Got: " + arrayToString(result));
                }
            } else {
                // For large test case, we just check if the code runs without error
                System.out.println("Test case " + (i + 1) + " completed.");
            }
        }
    }

    public static int[] solution(int[] queries) {
        // Maps to store intervals: start position to length and end position to length
        Map<Integer, Integer> startMap = new HashMap<>();
        Map<Integer, Integer> endMap = new HashMap<>();

        // Set to keep track of occupied positions
        Set<Integer> occupied = new HashSet<>();

        int n = queries.length;
        int[] result = new int[n];

        int maxSegmentLength = 0;

        for (int i = 0; i < n; i++) {
            int pos = queries[i];
            occupied.add(pos);

            boolean hasLeft = occupied.contains(pos - 1);
            boolean hasRight = occupied.contains(pos + 1);

            int newStart = pos;
            int newEnd = pos;
            int newLength = 1;

            if (!hasLeft && !hasRight) {
                // No adjacent occupied positions, create a new interval
                startMap.put(pos, 1);
                endMap.put(pos, 1);
            } else if (hasLeft && !hasRight) {
                // Extend the interval ending at pos - 1
                int leftIntervalLength = endMap.get(pos - 1);
                int leftStart = pos - leftIntervalLength;
                newStart = leftStart;
                newEnd = pos;
                newLength = leftIntervalLength + 1;

                // Update maps
                startMap.put(newStart, newLength);
                endMap.remove(pos - 1);
                endMap.put(newEnd, newLength);
            } else if (!hasLeft && hasRight) {
                // Extend the interval starting at pos + 1
                int rightIntervalLength = startMap.get(pos + 1);
                int rightEnd = pos + rightIntervalLength - 1;
                newStart = pos;
                newEnd = rightEnd;
                newLength = rightIntervalLength + 1;

                // Update maps
                startMap.remove(pos + 1);
                startMap.put(newStart, newLength);
                endMap.put(newEnd, newLength);
            } else {
                // Merge two intervals
                int leftIntervalLength = endMap.get(pos - 1);
                int leftStart = pos - leftIntervalLength;
                int rightIntervalLength = startMap.get(pos + 1);
                int rightEnd = pos + rightIntervalLength - 1;
                newStart = leftStart;
                newEnd = rightEnd;
                newLength = leftIntervalLength + rightIntervalLength + 1;

                // Update maps
                startMap.remove(pos + 1);
                endMap.remove(pos - 1);
                startMap.put(newStart, newLength);
                endMap.put(newEnd, newLength);
            }

            // Update the maximum segment length and result array
            maxSegmentLength = Math.max(maxSegmentLength, newLength);
            result[i] = maxSegmentLength;
        }

        return result;
    }

    // Helper method to compare two arrays
    private static boolean arraysEqual(int[] a, int[] b) {
        if (a.length != b.length) return false;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) return false;
        }
        return true;
    }

    // Helper method to convert array to string for display
    private static String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        if (arr.length > 0) {
            sb.append(arr[0]);
            for (int i = 1; i < arr.length; i++) {
                sb.append(", ").append(arr[i]);
            }
        }
        sb.append(']');
        return sb.toString();
    }

    // Method to generate a large test case
    private static int[] generateLargeTestCase(int size) {
        int[] queries = new int[size];
        for (int i = 0; i < size; i++) {
            queries[i] = i;
        }
        return queries;
    }
}
