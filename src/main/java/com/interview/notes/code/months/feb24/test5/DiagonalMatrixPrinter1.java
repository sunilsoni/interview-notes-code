package com.interview.notes.code.months.feb24.test5;

public class DiagonalMatrixPrinter1 {
    public static void printDiagonals(int[][] matrix) {
        int rows = matrix.length;    // Number of rows in the matrix
        int cols = matrix[0].length; // Number of columns in the matrix

        // Iterate over each diagonal starting point
        for (int start = 0; start < cols; start++) {
            int x = 0; // Start at the first row
            int y = start; // Column varies with the diagonal

            // Traverse the diagonal
            while (x < rows && y >= 0) {
                System.out.print(matrix[x][y] + " ");
                x++; // Move down
                y--; // Move left
            }
            System.out.println(); // New line for each diagonal
        }

        // For diagonals starting from the rightmost column but not the top row
        for (int start = 1; start < rows; start++) {
            int x = start; // Row varies with the diagonal
            int y = cols - 1; // Start at the last column

            // Traverse the diagonal
            while (x < rows && y >= 0) {
                System.out.print(matrix[x][y] + " ");
                x++; // Move down
                y--; // Move left
            }
            System.out.println(); // New line for each diagonal
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };

        printDiagonals(matrix); // Execute the method to print diagonals
    }
}
