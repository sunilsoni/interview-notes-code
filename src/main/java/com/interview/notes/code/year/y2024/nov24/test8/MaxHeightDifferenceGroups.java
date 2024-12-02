package com.interview.notes.code.year.y2024.nov24.test8;

import java.util.Arrays;
import java.util.Random;

public class MaxHeightDifferenceGroups {

    public static int maxHeightDifferenceSum(int[] heights) {
        if (heights == null || heights.length < 2) {
            return 0;
        }

        // Sort the heights in ascending order
        Arrays.sort(heights);

        int sum = 0;
        // Pair the smallest with the largest, second smallest with second largest, and so on
        for (int i = 0; i < heights.length / 2; i++) {
            sum += heights[heights.length - 1 - i] - heights[i];
        }

        return sum;
    }

    public static void main(String[] args) {
        // Test cases
        int[][] testCases = {
                {1, 7, 2, 5, 6, 23},
                {1, 2, 3, 4},
                {5, 5, 5, 5},
                {1, 10},
                {1},
                {},
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
                {100, 1, 99, 2, 98, 3, 97, 4, 96, 5}
        };

        int[] expectedResults = {27, 4, 0, 9, 0, 0, 25, 294};

        for (int i = 0; i < testCases.length; i++) {
            int result = maxHeightDifferenceSum(testCases[i]);
            boolean passed = result == expectedResults[i];

            System.out.println("Test Case " + (i + 1) + ":");
            System.out.println("Input: " + Arrays.toString(testCases[i]));
            System.out.println("Output: " + result);
            System.out.println("Expected: " + expectedResults[i]);
            System.out.println("Result: " + (passed ? "PASS" : "FAIL"));
            System.out.println();
        }

        // Large input test
        int[] largeInput = new Random().ints(1_000_000, 1, 1_000_001).toArray();

        long startTime = System.nanoTime();
        int largeResult = maxHeightDifferenceSum(largeInput);
        long endTime = System.nanoTime();

        System.out.println("Large Input Test:");
        System.out.println("Input size: " + largeInput.length);
        System.out.println("Output: " + largeResult);
        System.out.println("Execution time: " + ((endTime - startTime) / 1_000_000) + " ms");
        System.out.println("Result: " + (largeResult > 0 ? "PASS" : "FAIL"));
    }
}
