package com.interview.notes.code.months.nov23.test6.google;

public class FieldOptimizer3 {

    public static void main(String[] args) {
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
        if (result[0] == -1) {
            System.out.println("No valid 3x3 non-zero submatrix found.");
        } else {
            System.out.println("Optimal 3x3 Submatrix Coordinates: (" + result[0] + ", " + result[1] + ")");
            System.out.println("Minimum Sum: " + result[2]);
        }
    }

    public static int[] findOptimal3x3NonZero(int[][] matrix) {
        if (matrix.length < 3 || matrix[0].length < 3) {
            return new int[]{-1, -1, -1}; // Indicating that it's not possible to find a 3x3 submatrix
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
}
