package com.interview.notes.code.year.y2024.nov24.test9;

import java.util.Arrays;

//Your class has N students. You are given their heights. You want to form maximum number of groups of 2 students such that the difference of their heights is very huge. More precisely, the sum of height differences across all groups is maximum. Find that maximum sum. {1,7,2,5,6,23}

public class HeightDifferenceMaximizer {

    public static void main(String[] args) {
        // Define test cases
        int[][] testCases = {
                {1, 7, 2, 5, 6, 23},               // Test case 1
                {},                                 // Test case 2
                {5},                                // Test case 3
                {4, 4, 4, 4},                       // Test case 4
                {10, 1, 2, 9, 3, 8, 4, 7, 5, 6},   // Test case 5
                {1, 2},                             // Test case 6
                {1, 3, 5, 9, 11, 13, 17, 19},       // Test case 7
                generateLargeArray(1000000)          // Test case 8: Large test case
        };

        // Expected results corresponding to the test cases
        long[] expectedResults = {
                28,                // Test case 1
                0,                 // Test case 2
                0,                 // Test case 3
                0,                 // Test case 4
                25,                // Test case 5
                1,                 // Test case 6
                42,                // Test case 7 (Corrected from 18 to 42)
                // Test case 8: To be calculated dynamically
                calculateExpectedForLargeTest(1000000)
        };

        boolean allPassed = true;

        // Process each test case
        for (int i = 0; i < testCases.length; i++) {
            long result = maximizeHeightDifference(testCases[i]);
            long expected = expectedResults[i];
            boolean pass = result == expected;
            System.out.print("Test case " + (i + 1) + ": " + (pass ? "PASS" : "FAIL"));
            if (!pass) {
                // For large arrays, avoid printing the entire array
                String inputRepresentation = testCases[i].length > 50 ?
                        Arrays.toString(Arrays.copyOf(testCases[i], 50)) + "..." :
                        Arrays.toString(testCases[i]);
                System.out.println("\nInput: " + inputRepresentation);
                System.out.println("Expected: " + expected);
                System.out.println("Got:      " + result);
                allPassed = false;
            } else {
                System.out.println();
            }
        }

        if (allPassed) {
            System.out.println("All test cases passed.");
        } else {
            System.out.println("Some test cases failed.");
        }
    }

    /**
     * Maximizes the sum of height differences by pairing students optimally.
     *
     * @param heights Array of student heights.
     * @return The maximum sum of height differences.
     */
    public static long maximizeHeightDifference(int[] heights) {
        if (heights == null || heights.length < 2) {
            return 0;
        }

        // Sort the array in ascending order
        Arrays.sort(heights);

        long totalDifference = 0;
        int i = 0;
        int j = heights.length - 1;

        // Pair the smallest with the largest
        while (i < j) {
            totalDifference += (long) heights[j] - heights[i];
            i++;
            j--;
        }

        return totalDifference;
    }

    /**
     * Generates a large array of student heights for testing.
     * Heights are assigned such that pairing maximizes the difference.
     *
     * @param size The size of the array.
     * @return The generated array.
     */
    private static int[] generateLargeArray(int size) {
        int[] largeArray = new int[size];
        for (int i = 0; i < size; i++) {
            largeArray[i] = i + 1; // Heights from 1 to size
        }
        return largeArray;
    }

    /**
     * Calculates the expected result for the large test case.
     * For an array [1, 2, 3, ..., N], the optimal pairing is (1,N), (2,N-1), etc.
     * The sum is (N-1) + (N-3) + ... depending on whether N is even or odd.
     *
     * @param size The size of the array.
     * @return The expected maximum sum of height differences.
     */
    private static long calculateExpectedForLargeTest(int size) {
        long expected = 0;
        for (int i = 1; i <= size / 2; i++) {
            expected += (size - i + 1) - i;
        }
        return expected;
    }
}
