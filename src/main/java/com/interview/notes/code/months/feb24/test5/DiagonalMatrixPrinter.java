package com.interview.notes.code.months.feb24.test5;

public class DiagonalMatrixPrinter {

    // Main method to execute and test the diagonal printing
    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12}
        };

        printDiagonals(matrix);
    }

    // Method to print the diagonals of the matrix
    public static void printDiagonals(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Loop to iterate over the starting points of the diagonals
        for (int col = cols - 1; col >= 0; col--) {
            int startCol = col;
            int startRow = 0;

            // Loop to go through each diagonal
            while (startRow < rows && startCol < cols) {
                System.out.print(matrix[startRow][startCol] + " ");
                startRow++;
                startCol++;
            }
            System.out.println(); // Move to the next line after printing a diagonal
        }

        // Loop for the bottom left half of the matrix
        for (int row = 1; row < rows; row++) {
            int startCol = 0;
            int startRow = row;

            // Loop to go through each diagonal
            while (startRow < rows && startCol < cols) {
                System.out.print(matrix[startRow][startCol] + " ");
                startRow++;
                startCol++;
            }
            System.out.println(); // Move to the next line after printing a diagonal
        }
    }
}
