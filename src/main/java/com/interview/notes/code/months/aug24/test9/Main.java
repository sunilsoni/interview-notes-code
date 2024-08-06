package com.interview.notes.code.months.aug24.test9;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        int[][] matrix = {{1, 2, 3}, {7, 9, 4}, {8, 6, 5}};

        printSortedMatrix(matrix);
    }

    /**
     * Prints the input matrix in sorted order.
     *
     * @param matrix The input matrix
     */
    public static void printSortedMatrix(int[][] matrix) {
        // Find maximum number of columns across all rows
        int maxCols = Arrays.stream(matrix).mapToInt(row -> row.length).max().getAsInt();

        for (int colIdx = 0; colIdx < maxCols; colIdx++) {

            // Extract elements from each column and sort them together
            Integer[] sortedElements = new Integer[matrix.length];
            int idx = 0;
            for (int i = 0; i < matrix.length && i < maxCols; i++) {
                if (i >= matrix[0].length) break;

                // Check if current column index is within bounds of this row
                sortedElements[idx++] = matrix[i][colIdx];
            }

            Arrays.sort(sortedElements);

            for (int j : sortedElements)
                System.out.print(j + " ");
            System.out.println();
        }
    }
}