package com.interview.notes.code.year.y2024.aug24.test9;

import java.util.Arrays;

public class SortMatrixColumns {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {7, 6, 4},
                {8, 9, 5}
        };

        int rows = matrix.length;
        int cols = matrix[0].length;

        // Create a new matrix to store the sorted result
        int[][] sortedMatrix = new int[rows][cols];

        // Sort each column
        for (int j = 0; j < cols; j++) {
            int[] column = new int[rows];
            for (int i = 0; i < rows; i++) {
                column[i] = matrix[i][j];
            }
            Arrays.sort(column);
            for (int i = 0; i < rows; i++) {
                sortedMatrix[i][j] = column[i];
            }
        }

        // Arrays.toString(matrix);

        // Print the sorted matrix
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(sortedMatrix[i][j]);
                if (j < cols - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
