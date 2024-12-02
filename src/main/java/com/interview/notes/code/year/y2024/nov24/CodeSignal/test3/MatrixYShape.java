package com.interview.notes.code.year.y2024.nov24.CodeSignal.test3;

import java.util.Arrays;

public class MatrixYShape {
    /**
     * Main method for testing the solution with various test cases.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        MatrixYShape solver = new MatrixYShape();

        // Test case 1
        int[][] matrix1 = {
                {1, 0, 2},
                {1, 2, 0},
                {0, 2, 0}
        };
        int result1 = solver.solution(matrix1);
        System.out.println("Test Case 1 Output: " + result1); // Expected: 2

        // Test case 2
        int[][] matrix2 = {
                {2, 0, 0, 0, 2},
                {0, 2, 1, 2, 0},
                {0, 1, 2, 1, 0},
                {0, 0, 2, 1, 1},
                {1, 1, 2, 1, 1}
        };
        int result2 = solver.solution(matrix2);
        System.out.println("Test Case 2 Output: " + result2); // Expected: 8

        // Additional test cases
        // Test case 3: All cells already form a 'Y'
        int[][] matrix3 = {
                {0, 1, 0},
                {1, 0, 1},
                {1, 0, 1}
        };
        int result3 = solver.solution(matrix3);
        System.out.println("Test Case 3 Output: " + result3); // Expected: 0

        // Test case 4: Large matrix
        int n = 99;
        int[][] matrix4 = new int[n][n];
        for (int[] row : matrix4) {
            Arrays.fill(row, 0);
        }
        int result4 = solver.solution(matrix4);
        System.out.println("Test Case 4 Output: " + result4);

        // Test case 5: Random matrix
        int[][] matrix5 = {
                {2, 1, 0},
                {0, 2, 1},
                {1, 0, 2}
        };
        int result5 = solver.solution(matrix5);
        System.out.println("Test Case 5 Output: " + result5);
    }

    /**
     * Computes the minimum number of cells that need to change in the given matrix
     * to form the letter 'Y' with specific conditions.
     *
     * @param matrix The input square matrix containing only 0, 1, and 2.
     * @return The minimum number of changes required.
     */
    int solution(int[][] matrix) {
        int n = matrix.length;
        int minChanges = Integer.MAX_VALUE;

        // Possible values for 'Y' and background
        int[] values = {0, 1, 2};

        // Try all combinations of 'Y' character and background character
        for (int yChar : values) {
            for (int bgChar : values) {
                if (yChar == bgChar) {
                    continue; // Characters must be different
                }
                int changes = computeChanges(matrix, yChar, bgChar);
                minChanges = Math.min(minChanges, changes);
            }
        }

        return minChanges;
    }

    /**
     * Computes the number of changes needed to form the 'Y' shape with specified characters.
     *
     * @param matrix The input matrix.
     * @param yChar  The character representing the 'Y'.
     * @param bgChar The character representing the background.
     * @return The number of changes required.
     */
    private int computeChanges(int[][] matrix, int yChar, int bgChar) {
        int n = matrix.length;
        int changes = 0;
        int center = n / 2;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                boolean isYPart = false;

                // Check if cell is on left diagonal (upper half)
                if (i <= center && i == j) {
                    isYPart = true;
                }

                // Check if cell is on right diagonal (upper half)
                if (i <= center && i + j == n - 1) {
                    isYPart = true;
                }

                // Check if cell is on vertical line (from center downwards)
                if (i >= center && j == center) {
                    isYPart = true;
                }

                if (isYPart) {
                    if (matrix[i][j] != yChar) {
                        changes++;
                    }
                } else {
                    if (matrix[i][j] != bgChar) {
                        changes++;
                    }
                }
            }
        }
        return changes;
    }
}
