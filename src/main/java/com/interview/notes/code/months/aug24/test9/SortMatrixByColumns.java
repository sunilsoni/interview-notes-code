package com.interview.notes.code.months.aug24.test9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortMatrixByColumns {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {7, 9, 4},
                {8, 6, 5}
        };

        sortAndPrintMatrix(matrix);
    }

    public static void sortAndPrintMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Create a list to store column values
        List<List<Integer>> columns = new ArrayList<>();

        // Extract columns and store them in the list
        for (int j = 0; j < cols; j++) {
            List<Integer> column = new ArrayList<>();
            for (int i = 0; i < rows; i++) {
                column.add(matrix[i][j]);
            }
            columns.add(column);
        }

        // Sort each column
        columns.forEach(Collections::sort);

        // Print the sorted matrix
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(columns.get(j).get(i) + " ");
            }
            System.out.println();
        }
    }
}
