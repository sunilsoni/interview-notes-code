package com.interview.notes.code.months.year2023.nov23.test6.google;

public class FieldOptimizer1 {

    // Main method to execute an example
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
        int[] result = findOptimal3x3(field);
        System.out.println("Optimal 3x3 Submatrix Coordinates: (" + result[0] + ", " + result[1] + ")");
        System.out.println("Minimum Sum: " + result[2]);
    }

    // Method to find the optimal 3x3 submatrix
    public static int[] findOptimal3x3(int[][] matrix) {
        int minSum = Integer.MAX_VALUE;
        int[] coordinates = new int[3]; // [x, y, sum]

        for (int i = 0; i <= matrix.length - 3; i++) {
            for (int j = 0; j <= matrix[0].length - 3; j++) {
                int sum = calculateSum(matrix, i, j);
                if (sum < minSum) {
                    minSum = sum;
                    coordinates[0] = i;
                    coordinates[1] = j;
                    coordinates[2] = sum;
                }
            }
        }
        return coordinates;
    }

    // Helper method to calculate the sum of a 3x3 submatrix
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
