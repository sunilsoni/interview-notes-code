package com.interview.notes.code.months.may24.test2;

import java.util.Arrays;

/**
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
class SpiralMatrix {

    public static int[] spiralMatrix(int N) {
        // Creating the matrix and the result array
        int[][] matrix = new int[N][N];
        int[] result = new int[N * N];

        // Initialize the boundaries and direction indicators
        int top = 0, bottom = N - 1, left = 0, right = N - 1;
        int index = 0;

        // Value to fill in the matrix
        int value = 1;

        while (value <= N * N) {
            // Fill top row
            for (int i = left; i <= right && value <= N * N; i++) {
                matrix[top][i] = value++;
            }
            top++;

            // Fill right column
            for (int i = top; i <= bottom && value <= N * N; i++) {
                matrix[i][right] = value++;
            }
            right--;

            // Fill bottom row
            for (int i = right; i >= left && value <= N * N; i--) {
                matrix[bottom][i] = value++;
            }
            bottom--;

            // Fill left column
            for (int i = bottom; i >= top && value <= N * N; i--) {
                matrix[i][left] = value++;
            }
            left++;
        }

        // Convert 2D matrix to 1D array in row-major order
        int k = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result[k++] = matrix[i][j];
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

/**
 * [1, 2, 4, 3]
 * [1, 2, 3, 4, 12, 13, 14, 5, 11, 16, 15, 6, 10, 9, 8, 7]
 */
