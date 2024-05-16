package com.interview.notes.code.months.may24.test2;

import java.util.Arrays;

//FINAL OCde Working

/**
 * /**
 * Spiral Matrix
 * This question accounts for 40% of the total test. Please do not forget to submit your answer, timed-out tests will result in 0 points awarded.
 * Given N, write a function to generate a Spiral Matrix of dimension Nx N.
 * Notes:
 * • A spiral matrix is defined as a matrix in which consecutive numbers (starting at
 * 1) are arranged in a descending clockwise spiral beginning from the matrix's
 * 1st row's 1st column(top left corner cell), as shown in the example below.
 * • The row-major format means that consecutive elements of a row are stored sequentially in the array.
 * Input Specifications:
 * • N is an integer such that:
 * • 0 < N<= 100
 * Output Specifications:
 * • Return the resulting N×N matrix as a 1-dimensional array in row-major format.
 * Performance Specifications:
 * • Time Complexity: O(N)
 * Example:
 * Input: N = 2
 * Mantrait, 11 9 1 21
 */

class SpiralMatrixMainCode {

    public static int[] spiralMatrix(int N) {
        int[][] matrix = new int[N][N];  // This will store the numbers in a 2D spiral form
        int[] result = new int[N * N];   // This will store the final 1D row-major result
        int num = 1;                     // Start filling matrix with 1

        int rowStart = 0;
        int rowEnd = N - 1;
        int colStart = 0;
        int colEnd = N - 1;

        while (rowStart <= rowEnd && colStart <= colEnd) {
            // Traverse Right
            for (int col = colStart; col <= colEnd; col++) {
                matrix[rowStart][col] = num++;
            }
            rowStart++;

            // Traverse Down
            for (int row = rowStart; row <= rowEnd; row++) {
                matrix[row][colEnd] = num++;
            }
            colEnd--;

            // Make sure we are now on a different row
            if (rowStart <= rowEnd) {
                // Traverse Left
                for (int col = colEnd; col >= colStart; col--) {
                    matrix[rowEnd][col] = num++;
                }
                rowEnd--;
            }

            // Make sure we are now in a different column
            if (colStart <= colEnd) {
                // Traverse Up
                for (int row = rowEnd; row >= rowStart; row--) {
                    matrix[row][colStart] = num++;
                }
                colStart++;
            }
        }

        // Convert matrix to 1D array in row-major order
        int index = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result[index++] = matrix[i][j];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // Example usage for N = 2 as given in the problem statement
        int N = 2;
        int[] output = spiralMatrix(N);
        System.out.println(Arrays.toString(output)); // Expected output: [1, 2, 4, 3]

        // Example usage for N = 4 to see more complex spiral
        N = 4;
        output = spiralMatrix(N);
        System.out.println(Arrays.toString(output)); // Outputs the 4x4 spiral in row-major format
    }
}
