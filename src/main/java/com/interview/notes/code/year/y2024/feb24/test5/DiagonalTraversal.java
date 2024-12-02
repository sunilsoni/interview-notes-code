package com.interview.notes.code.year.y2024.feb24.test5;

public class DiagonalTraversal {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        printDiagonals(matrix);
    }

    public static void printDiagonals(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Print the first diagonal starting from the top left corner
        for (int i = 0; i < rows; i++) {
            int j = 0;
            while (i >= 0 && j < cols) {
                System.out.print(matrix[i][j] + " ");
                i--;
                j++;
            }
            System.out.println();
        }

        // Print the remaining diagonals starting from the rightmost column
        for (int j = 1; j < cols; j++) {
            int i = rows - 1;
            while (i >= 0 && j < cols) {
                System.out.print(matrix[i][j] + " ");
                i--;
                j++;
            }
            System.out.println();
        }
    }
}
