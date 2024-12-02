package com.interview.notes.code.year.y2024.oct24.test14;

import java.util.Arrays;
import java.util.TreeSet;

public class LongestContiguousHouses {

    // Testing method
    public static void main(String[] args) {
        LongestContiguousHouses solver = new LongestContiguousHouses();

        // Sample test cases
        int[][] testCases = {
                {1, 3, 0, 4},
                {2, 1, 3}
        };
        int[][] expectedResults = {
                {1, 1, 2, 2},
                {1, 2, 3}
        };

        boolean allTestsPassed = true;

        for (int i = 0; i < testCases.length; i++) {
            int[] result = solver.solution(testCases[i]);
            if (!Arrays.equals(result, expectedResults[i])) {
                allTestsPassed = false;
                System.out.println("Test case " + (i + 1) + " failed: Expected " +
                        Arrays.toString(expectedResults[i]) + ", but got " + Arrays.toString(result));
            } else {
                System.out.println("Test case " + (i + 1) + " passed.");
            }
        }

        // Additional test for large inputs
        int largeTestSize = 100000;
        int[] largeTest = new int[largeTestSize];
        for (int i = 0; i < largeTestSize; i++) {
            largeTest[i] = i;
        }
        int[] largeResult = solver.solution(largeTest);
        if (largeResult[largeTestSize - 1] == largeTestSize) {
            System.out.println("Large test case passed.");
        } else {
            allTestsPassed = false;
            System.out.println("Large test case failed.");
        }

        if (allTestsPassed) {
            System.out.println("All tests passed.");
        } else {
            System.out.println("Some tests failed.");
        }
    }

    public int[] solution(int[] queries) {
        int n = queries.length;
        int[] results = new int[n];

        // To store the sorted positions of houses
        TreeSet<Integer> houses = new TreeSet<>();
        int maxSegmentLength = 0;

        for (int i = 0; i < n; i++) {
            int position = queries[i];
            houses.add(position);

            // Find neighbors
            Integer lower = houses.lower(position);
            Integer higher = houses.higher(position);

            // Calculate current maximum contiguous length based on neighbors
            if (lower != null && higher != null && higher - lower == 2) {
                // Merge two segments
                maxSegmentLength = Math.max(maxSegmentLength, higher - lower);
            } else if (lower != null) {
                maxSegmentLength = Math.max(maxSegmentLength, position - lower);
            } else if (higher != null) {
                maxSegmentLength = Math.max(maxSegmentLength, higher - position);
            } else {
                // First house added
                maxSegmentLength = 1;
            }

            results[i] = maxSegmentLength;
        }

        return results;
    }
}
