package com.interview.notes.code.months.nov24.test10;

import java.util.Arrays;
import java.util.stream.IntStream;

public class MultidimensionalArraySum {

    public static int calculateSum(int[][] array) {
        return Arrays.stream(array)
                .flatMapToInt(Arrays::stream)
                .filter(num -> num > 0) // Filter to sum only positive numbers
                .sum();
    }

    public static void main(String[] args) {
        // Test cases
        int[][] testCase1 = {{1, 2, 3}, {4}, {5, 6}};
        int[][] testCase2 = {{1, -1}, {1, -1}};
        int[][] testCase3 = {{100, -200}, {300, 400, -500}};
        int[][] testCase4 = {}; // Empty array
        int[][] testCase5 = generateLargeArray(1000, 1000);
        int[][] testCase6 = {{-1, -2}, {-3, -4}}; // All negative numbers

        // Run tests
        runTest("Test Case 1 (All Positive)", testCase1, 21);
        runTest("Test Case 2 (Mixed)", testCase2, 2);
        runTest("Test Case 3 (Mixed Large)", testCase3, 800);
        runTest("Test Case 4 (Empty Array)", testCase4, 0);
        runTest("Test Case 5 (Large Array)", testCase5, 1000000);
        runTest("Test Case 6 (All Negative)", testCase6, 0);
    }

    private static void runTest(String testName, int[][] input, int expectedSum) {
        long startTime = System.nanoTime();
        int actualSum = calculateSum(input);
        long endTime = System.nanoTime();
        boolean passed = actualSum == expectedSum;
        System.out.printf("%s: %s (Execution time: %.3f ms)%n",
                testName, (passed ? "PASS" : "FAIL"), (endTime - startTime) / 1e6);
        if (!passed) {
            System.out.println("  Expected: " + expectedSum);
            System.out.println("  Actual: " + actualSum);
        }
    }

    private static int[][] generateLargeArray(int rows, int cols) {
        return IntStream.range(0, rows)
                .mapToObj(i -> IntStream.range(0, cols)
                        .map(j -> (int) (Math.random() * 3) - 1) // Generate -1, 0, or 1
                        .toArray())
                .toArray(int[][]::new);
    }
}
