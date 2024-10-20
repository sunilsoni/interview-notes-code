package com.interview.notes.code.months.oct24.test16;

import java.util.*;
/*
You are monitoring the building density in a district of houses. The district is represented as a number line, where a house can be built at each numbered point on the line if at least one of the neighboring points is not occupied. Initially, there are no houses in the district.
You are given queries, an array of integers representing the locations of new houses in the order in which they will be built. After each house is built, your task is to find the longest segment of contiguous houses in the district.
Return an array of integers representing the longest segment of contiguous houses after each respective house from queries is built.
NOTE: It's guaranteed that all of the house locations in queries are unique and no house was built at a point with existing houses on both left and right adjacent points.
Assume that array indices are 0-based.
Example
• For queries = [2, 1, 3], the output should be solution (queries) = [1, 2, 31 •
• Expand to see the example video.
Let's look at what happens at each step:
• After queries [0] = 2, there is only one house in the district (at 2 ), so the answer is 1 .
• After queries (11 = 1, there are two houses in the district (at 1 and 2 ). This metins there is a segment of 2 contiguous houses, so the answer is 2 .
• After queries [2] = 3, there are three houses in the district (at 1, 2, and 3 ). This means there is a segment of 3 contiguous houses, so the answer is 3 .
So the final answer is [1, 2, 31 .
• For queries = [1, 3, 0, 4] , the output should be solution (queries) = [1, 1, 2, 2] .
• Expand to see the example video.
Let's look at what happens at each step:
• After queries [0] = 1, there is only one house in the district (at 1), so the answer is 1 .
• After queries [1] = 3, there are two houses in the district (at 1 and 3 ). These two houses are not contiguous, so the answer is still 1 .
• After queries (2] = 0, there are three houses in the district (at o, 1, and 3 ). This means there is a segment of 2 contiguous houses,

• For queries = [1, 3, 0, 4] , the output should be solution (queries) = [1, 1, 2, 21 .
• Expand to see the example video.
Let's look at what happens at each step:
• After queries [0] = 1, there is only one house in the district (at 1 ), so the answer is 1 .
• After queries [1] = 3, there are two houses in the district (at 1 and 3 ). These two houses are not contiguous, so the answer is still 1 .
• After queries |21 = 0, there are three houses in the district (at o, 1, and 3 ). This means there is a segment of 2 contiguous houses, so the answer is 2 .
• Ater queries|2] = 4, there are two segments of 2 contiguous houses, so the answer is still
So the final answer is [1, 1, 2, 2] .
Input/Output
• [execution time limit] 3 seconds (java)
• [memory limit] 1 GB
• [input] array.integer queries
An array of integers representing the requested house building coordinates.
Guaranteed constraints:
1 ≤ queries. length ≤ 105,
-10° ≤ queries lil ≤ 109
• [output] array.integer
An array of integers representing the longest segment of contiguous houses after each build.
 */
public class MainClass {

    public static void main(String[] args) {
        // Test cases
        int[][] testQueries = {
            {2, 1, 3},
            {1, 3, 0, 4},
            {1},
            {1, 3, 5, 7, 9}, // Non-contiguous houses
            {1, 2, 3, 4, 5}, // Build contiguous houses
            // Additional test case to reproduce the issue
            {1, 2, 3, 4, 5, 50, 51},
            // Large test case
            generateLargeTestCase(100000)
        };

        int[][] expectedOutputs = {
            {1, 2, 3},
            {1, 1, 2, 2},
            {1},
            {1, 1, 1, 1, 1},
            {1, 2, 3, 4, 5},
            {1, 2, 3, 4, 5, 5, 5},
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

            int newLength = 1;

            if (!hasLeft && !hasRight) {
                // No adjacent occupied positions, create a new interval
                startMap.put(pos, 1);
                endMap.put(pos, 1);
            } else if (hasLeft && !hasRight) {
                // Extend the interval ending at pos - 1
                int leftIntervalLength = endMap.get(pos - 1);
                int newStart = pos - leftIntervalLength;
                newLength = leftIntervalLength + 1;

                // Update maps
                endMap.remove(pos - 1);
                endMap.put(pos, newLength);
                startMap.put(newStart, newLength);
            } else if (!hasLeft && hasRight) {
                // Extend the interval starting at pos + 1
                int rightIntervalLength = startMap.get(pos + 1);
                int newEnd = pos + rightIntervalLength;
                newLength = rightIntervalLength + 1;

                // Update maps
                startMap.remove(pos + 1);
                startMap.put(pos, newLength);
                endMap.put(newEnd, newLength);
            } else {
                // Both neighbors occupied: Should not happen per problem constraints
                throw new IllegalStateException("Both neighbors occupied, invalid query per problem constraints.");
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
