package com.interview.notes.code.year.y2024.june24.test5;

public class MatrixMaxLocal {
    public static int[][] largestLocal(int[][] grid) {
        int n = grid.length;
        int[][] maxLocal = new int[n - 2][n - 2];

        for (int i = 0; i <= n - 3; i++) {
            for (int j = 0; j <= n - 3; j++) {
                int max = 0;  // Initialize max for this submatrix
                for (int k = i; k < i + 3; k++) {
                    for (int l = j; l < j + 3; l++) {
                        max = Math.max(max, grid[k][l]);  // Find the max in the 3x3 block
                    }
                }
                maxLocal[i][j] = max;  // Assign max to the result matrix
            }
        }

        return maxLocal;
    }

    public static void main(String[] args) {
        // Your sample matrix and a call to the method, plus printing the results.
    }
}
