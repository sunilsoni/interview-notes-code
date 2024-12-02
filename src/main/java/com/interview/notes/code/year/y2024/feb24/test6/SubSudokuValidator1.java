package com.interview.notes.code.year.y2024.feb24.test6;

public class SubSudokuValidator1 {
    public static boolean validateGrid(int[][] grid) {
        // Step 1: Check if the grid is not null and has a proper NxN structure
        if (grid == null || grid.length == 0 || grid.length != grid[0].length) {
            return false;
        }

        int size = grid.length;

        // Step 2: Iterate over the grid to check rows and columns
        for (int i = 0; i < size; i++) {
            // Step 3 & 4: Validate rows and columns
            if (!hasAllNumbers(grid[i], size) || !hasAllNumbers(colToArray(grid, i), size)) {
                return false;
            }
        }

        // If all rows and columns are valid
        return true;
    }

    // Helper method to check if a list contains all numbers from 1 to N
    private static boolean hasAllNumbers(int[] array, int size) {
        boolean[] found = new boolean[size];
        for (int num : array) {
            if (num <= 0 || num > size || found[num - 1]) {
                return false;
            }
            found[num - 1] = true;
        }
        return true;
    }

    // Helper method to convert a column to an array
    private static int[] colToArray(int[][] grid, int col) {
        int[] colArray = new int[grid.length];
        for (int i = 0; i < grid.length; i++) {
            colArray[i] = grid[i][col];
        }
        return colArray;
    }

    // A "main" method for easy execution and testing
    public static void main(String[] args) {
        int[][] grid1 = {{2, 3, 1}, {1, 2, 3}, {3, 1, 2}};
        System.out.println(validateGrid(grid1)); // Should output true
    }
}
