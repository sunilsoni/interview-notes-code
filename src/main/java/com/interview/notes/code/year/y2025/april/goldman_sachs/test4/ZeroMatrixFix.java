package com.interview.notes.code.year.y2025.april.goldman_sachs.test4;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//Write a function to **change all elements of row `i` and column `j` to 0 in a matrix** if the cell `(i, j)` is 0.
public class ZeroMatrixFix {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 1, 0, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 0, 1},
                {1, 1, 1, 1, 1},
                {0, 1, 1, 1, 1}
        };

        // Apply the fix.
        changeMatrix(matrix);

        // Print the updated matrix.
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    /**
     * Changes the given matrix so that if any cell is 0,
     * its entire row and column are set to 0.
     */
    public static void changeMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        Set<Integer> zeroRows = new HashSet<>();
        Set<Integer> zeroCols = new HashSet<>();

        // First pass: find rows and columns that must be zeroed.
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    zeroRows.add(i);
                    zeroCols.add(j);
                }
            }
        }

        // Second pass: update the matrix rows/columns to zero.
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (zeroRows.contains(i) || zeroCols.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
