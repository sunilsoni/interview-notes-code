package com.interview.notes.code.year.y2025.april.goldman_sachs.test2;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MatrixZeroSetter {

    public static int[][] changeMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Identify rows and columns to zero
        Set<Integer> rowsToZero = IntStream.range(0, rows)
                .filter(i -> IntStream.range(0, cols).anyMatch(j -> matrix[i][j] == 0))
                .boxed()
                .collect(Collectors.toSet());

        Set<Integer> colsToZero = IntStream.range(0, cols)
                .filter(j -> IntStream.range(0, rows).anyMatch(i -> matrix[i][j] == 0))
                .boxed()
                .collect(Collectors.toSet());

        // Set identified rows and columns to zero
        IntStream.range(0, rows).forEach(i ->
                IntStream.range(0, cols).forEach(j -> {
                    if (rowsToZero.contains(i) || colsToZero.contains(j))
                        matrix[i][j] = 0;
                })
        );

        return matrix;
    }

    // Simple testing method (without JUnit)
    public static void main(String[] args) {
        testChangeMatrix();
    }

    private static void testChangeMatrix() {
        int[][] input = {
                {1, 1, 0, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 0, 1},
                {1, 1, 1, 1, 1},
                {0, 1, 1, 1, 1}
        };

        int[][] expectedOutput = {
                {0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1},
                {0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1},
                {0, 0, 0, 0, 0}
        };

        int[][] result = changeMatrix(input);

        boolean passed = Arrays.deepEquals(result, expectedOutput);
        System.out.println("Test Case 1: " + (passed ? "PASS" : "FAIL"));

        // Edge Case: Single row
        int[][] singleRow = {{1, 0, 1}};
        int[][] expectedSingleRow = {{0, 0, 0}};
        int[][] resultSingleRow = changeMatrix(singleRow);
        passed = Arrays.deepEquals(resultSingleRow, expectedSingleRow);
        System.out.println("Test Case 2 (Single Row): " + (passed ? "PASS" : "FAIL"));

        // Edge Case: Single Column
        int[][] singleCol = {{1}, {0}, {1}};
        int[][] expectedSingleCol = {{0}, {0}, {0}};
        int[][] resultSingleCol = changeMatrix(singleCol);
        passed = Arrays.deepEquals(resultSingleCol, expectedSingleCol);
        System.out.println("Test Case 3 (Single Column): " + (passed ? "PASS" : "FAIL"));

        // Large Data Test (1000x1000 matrix)
        int size = 1000;
        int[][] largeMatrix = new int[size][size];
        for (int[] row : largeMatrix)
            Arrays.fill(row, 1);
        largeMatrix[500][500] = 0;

        int[][] processedLargeMatrix = changeMatrix(largeMatrix);
        boolean largeTestPassed = IntStream.range(0, size).allMatch(i ->
                processedLargeMatrix[500][i] == 0 && processedLargeMatrix[i][500] == 0
        );

        System.out.println("Test Case 4 (Large Matrix): " + (largeTestPassed ? "PASS" : "FAIL"));
    }
}
