package com.interview.notes.code.year.y2024.feb24.test5;

public class DiagonalMatrix {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };

        // Print values along diagonals from top-right to bottom-left
        for (int i = 0; i < matrix.length; i++) {
            int row = i;
            int col = 0;

            while (row >= 0 && col < matrix[0].length) {
                System.out.print(matrix[row][col] + " ");
                row--;
                col++;
            }
            System.out.println();
        }

        // Print values along diagonals from top-left to bottom-right
        for (int j = 1; j < matrix[0].length; j++) {
            int row = matrix.length - 1;
            int col = j;

            while (row >= 0 && col < matrix[0].length) {
                System.out.print(matrix[row][col] + " ");
                row--;
                col++;
            }
            System.out.println();
        }
    }
}
