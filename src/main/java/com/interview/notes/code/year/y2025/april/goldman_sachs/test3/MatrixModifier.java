package com.interview.notes.code.year.y2025.april.goldman_sachs.test3;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MatrixModifier {

    /**
     * This function modifies the given matrix such that if any cell is 0,
     * then its entire row and column are set to 0.
     *
     * @param matrix 2D integer array representing the matrix.
     * @return The modified matrix with the required zeros.
     */
    public static int[][] changeMatrix(int[][] matrix) {
        // Handle null or empty matrix edge case.
        if (matrix == null || matrix.length == 0) {
            return matrix;
        }
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        Set<Integer> rowsToZero = new HashSet<>();
        Set<Integer> colsToZero = new HashSet<>();
        
        // Record the rows and columns that need to be zeroed.
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    rowsToZero.add(i);
                    colsToZero.add(j);
                }
            }
        }
        
        // Modify the matrix: if either the row or column index is in the set, set that cell to 0.
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (rowsToZero.contains(i) || colsToZero.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }
        return matrix;
    }

    /**
     * Helper method to compare two matrices.
     *
     * @param matrix1 First 2D int array.
     * @param matrix2 Second 2D int array.
     * @return true if both matrices are equal element-wise.
     */
    public static boolean isMatrixEqual(int[][] matrix1, int[][] matrix2) {
        if ((matrix1 == null && matrix2 != null) || (matrix1 != null && matrix2 == null)) {
            return false;
        }
        if (matrix1.length != matrix2.length) {
            return false;
        }
        for (int i = 0; i < matrix1.length; i++) {
            if (matrix1[i].length != matrix2[i].length) {
                return false;
            }
            for (int j = 0; j < matrix1[i].length; j++) {
                if (matrix1[i][j] != matrix2[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Helper method to print a matrix.
     * Uses Java 8 streams to join elements for cleaner output.
     *
     * @param matrix 2D int array to print.
     */
    public static void printMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            System.out.println("[]");
            return;
        }
        for (int[] row : matrix) {
            String rowString = Arrays.stream(row)
                                     .mapToObj(String::valueOf)
                                     .collect(Collectors.joining(", "));
            System.out.println("[" + rowString + "]");
        }
    }

    public static void main(String[] args) {
        // Test 1: Provided test case
        int[][] input1 = {
            {1, 1, 0, 1, 1},
            {1, 1, 1, 1, 1},
            {1, 1, 1, 0, 1},
            {1, 1, 1, 1, 1},
            {0, 1, 1, 1, 1}
        };
        int[][] expected1 = {
            {0, 0, 0, 0, 0},
            {0, 1, 0, 0, 1},
            {0, 0, 0, 0, 0},
            {0, 1, 0, 0, 1},
            {0, 0, 0, 0, 0}
        };
        runTest(input1, expected1, "Test 1");

        // Test 2: Matrix without any zeros should remain the same.
        int[][] input2 = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        int[][] expected2 = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        runTest(input2, expected2, "Test 2");

        // Test 3: Matrix with a zero in the first element.
        int[][] input3 = {
            {0, 2},
            {3, 4}
        };
        int[][] expected3 = {
            {0, 0},
            {0, 4}
        };
        runTest(input3, expected3, "Test 3");

        // Test 4: Edge case with an empty matrix.
        int[][] input4 = {};
        int[][] expected4 = {};
        runTest(input4, expected4, "Test 4");

        // Test 5: Single-row matrix.
        int[][] input5 = {
            {1, 0, 3, 4}
        };
        int[][] expected5 = {
            {0, 0, 0, 0}
        };
        runTest(input5, expected5, "Test 5");

        // Test 6: Single-column matrix.
        int[][] input6 = {
            {1},
            {0},
            {3}
        };
        int[][] expected6 = {
            {0},
            {0},
            {0}
        };
        runTest(input6, expected6, "Test 6");

        // Test 7: Large Data Input test.
        // Create a 1000x1000 matrix with no zeros, then set a few random zeros.
        int size = 1000;
        int[][] largeInput = new int[size][size];
        for (int i = 0; i < size; i++) {
            Arrays.fill(largeInput[i], 1);
        }
        // Set several zeros
        largeInput[100][200] = 0;
        largeInput[500][500] = 0;
        largeInput[700][300] = 0;
        // Instead of building a complete expected matrix, we can check if the required rows & columns are zero.
        int[][] modifiedLargeMatrix = changeMatrix(largeInput);
        boolean largeTestPass = 
            isRowZero(modifiedLargeMatrix, 100) &&
            isColumnZero(modifiedLargeMatrix, 200) &&
            isRowZero(modifiedLargeMatrix, 500) &&
            isColumnZero(modifiedLargeMatrix, 500) &&
            isRowZero(modifiedLargeMatrix, 700) &&
            isColumnZero(modifiedLargeMatrix, 300);
        System.out.println("Test 7 (Large Matrix): " + (largeTestPass ? "PASS" : "FAIL"));
    }
    
    /**
     * Helper method to run an individual test case.
     *
     * @param input The input matrix.
     * @param expected The expected output matrix.
     * @param testName Name or identifier of the test.
     */
    public static void runTest(int[][] input, int[][] expected, String testName) {
        // Create a deep copy of input for testing so original is not mutated during testing.
        int[][] inputCopy = Arrays.stream(input)
                                  .map(arr -> Arrays.copyOf(arr, arr.length))
                                  .toArray(int[][]::new);
        int[][] output = changeMatrix(inputCopy);
        boolean passed = isMatrixEqual(output, expected);
        System.out.println(testName + ": " + (passed ? "PASS" : "FAIL"));
        if (!passed) {
            System.out.println("Expected:");
            printMatrix(expected);
            System.out.println("Got:");
            printMatrix(output);
        }
    }
    
    /**
     * Helper method to check if all elements in a specified row are zero.
     * 
     * @param matrix The matrix to check.
     * @param row The row index to verify.
     * @return true if all elements are zero.
     */
    public static boolean isRowZero(int[][] matrix, int row) {
        if(row >= matrix.length) return false;
        return IntStream.of(matrix[row]).allMatch(x -> x == 0);
    }
    
    /**
     * Helper method to check if all elements in a specified column are zero.
     * 
     * @param matrix The matrix to check.
     * @param col The column index to verify.
     * @return true if all elements in the column are zero.
     */
    public static boolean isColumnZero(int[][] matrix, int col) {
        for (int i = 0; i < matrix.length; i++) {
            if (col < matrix[i].length && matrix[i][col] != 0) {
                return false;
            }
        }
        return true;
    }
}
