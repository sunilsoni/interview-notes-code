package com.interview.notes.code.year.y2025.april.goldman_sachs.test4;

import java.util.Arrays;

//Write a function to **change all elements of row `i` and column `j` to 0 in a matrix** if the cell `(i, j)` is 0.
public class ZeroMatrixConstantSpace {

    /**
     * Modifies the given matrix so that if any cell is 0,
     * its entire row and column are set to 0.
     * This implementation uses the first row and first column
     * as markers, achieving constant extra space usage.
     *
     * @param matrix 2D integer array representing the matrix.
     */
    public static void changeMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }

        int m = matrix.length;
        int n = matrix[0].length;
        boolean firstRowZero = false;
        boolean firstColZero = false;

        // Check if the first row has a zero
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                firstRowZero = true;
                break;
            }
        }

        // Check if the first column has a zero
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                firstColZero = true;
                break;
            }
        }

        // Use first row and column as markers.
        // For every cell (i, j) starting from 1,1, if it's zero,
        // mark the corresponding first row and first column elements as zero.
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;  // mark the row
                    matrix[0][j] = 0;  // mark the column
                }
            }
        }

        // Use markers to zero out cells in the interior of the matrix.
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // Zero out the first row if needed.
        if (firstRowZero) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }

        // Zero out the first column if needed.
        if (firstColZero) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    /**
     * Helper method to print the matrix.
     *
     * @param matrix The 2D array to be printed.
     */
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 1, 0, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 0, 1},
                {1, 1, 1, 1, 1},
                {0, 1, 1, 1, 1}
        };

        // Apply the transformation.
        changeMatrix(matrix);

        // Print the transformed matrix.
        printMatrix(matrix);
    }
}
