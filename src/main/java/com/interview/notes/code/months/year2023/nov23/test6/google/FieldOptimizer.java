package com.interview.notes.code.months.year2023.nov23.test6.google;

/**
 * The farmer owns an N by M meter field and is facing financial challenges.
 * He's considering selling most of the field but wants to keep
 * a 3x3 section while conserving water usage.
 * Each 1x1 plot needs a specific amount of water.
 * Your task is to identify the optimal 3x3 slot for him,
 * considering water efficiency.
 * (Given a NxM matrix, find the minimum sum of 3x3 submatrix.)
 * Example: Given matrix:
 * [
 * 1.2.3.4.5.4,
 * 3,1,2,1,0,4,
 * 2,6,7,0,1,1,
 * 4,6,7,0,0,1,
 * 1,2,4,6,2,3,
 * 2.7.9.8.1.5,
 * 1
 * for the sub-matrix [ xl = 3, x2 = 5, y1= 1, y2 = 3 ], the sum is 8 and this is the minimum 3x3 cells.
 * All the numbers in the matrix are between 0-255. 0 <= N ,M <= MAX_INTEGER
 * <p>
 * <p>
 * =======Follow up==========
 * Consider a situation where you are given matrix sizes NxM, but some plots have a value of zero.
 * Your task is to find the minimum 3x3 plot on this grid where all 9 plots have a non-zero value.'
 */
public class FieldOptimizer {

    public static void main(String[] args) {
        // Example matrix
        int[][] field = {
                {1, 2, 3, 4, 5, 4},
                {3, 1, 2, 1, 0, 4},
                {2, 6, 7, 0, 1, 1},
                {4, 6, 7, 0, 0, 1},
                {1, 2, 4, 6, 2, 3},
                {2, 7, 9, 8, 1, 5},
                {1, 0, 0, 0, 0, 0}
        };

        int[] result = findOptimal3x3NonZero(field);
        if (result == null || result[0] == -1) {
            System.out.println("No valid 3x3 non-zero submatrix found.");
        } else {
            System.out.println("Optimal 3x3 Submatrix Coordinates: (" + result[0] + ", " + result[1] + ")");
            System.out.println("Minimum Sum: " + result[2]);
        }
    }

    public static int[] findOptimal3x3NonZero(int[][] matrix) {
        // Check for null, empty matrix, or irregular matrix
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0 || !isRegularMatrix(matrix)) {
            return null;
        }

        // Check if the matrix is too small for a 3x3 submatrix
        if (matrix.length < 3 || matrix[0].length < 3) {
            return new int[]{-1, -1, -1};
        }

        int minSum = Integer.MAX_VALUE;
        int[] coordinates = new int[3]; // [x, y, sum]

        for (int i = 0; i <= matrix.length - 3; i++) {
            for (int j = 0; j <= matrix[0].length - 3; j++) {
                if (allNonZero(matrix, i, j)) {
                    int sum = calculateSum(matrix, i, j);
                    if (sum < minSum) {
                        minSum = sum;
                        coordinates[0] = i;
                        coordinates[1] = j;
                        coordinates[2] = sum;
                    }
                }
            }
        }
        return coordinates;
    }

    private static boolean allNonZero(int[][] matrix, int x, int y) {
        for (int i = x; i < x + 3; i++) {
            for (int j = y; j < y + 3; j++) {
                if (matrix[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private static int calculateSum(int[][] matrix, int x, int y) {
        int sum = 0;
        for (int i = x; i < x + 3; i++) {
            for (int j = y; j < y + 3; j++) {
                sum += matrix[i][j];
            }
        }
        return sum;
    }

    // Check if the matrix is regular (all rows have the same length)
    private static boolean isRegularMatrix(int[][] matrix) {
        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i].length != matrix[0].length) {
                return false;
            }
        }
        return true;
    }
}
