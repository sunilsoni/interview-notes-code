package com.interview.notes.code.year.y2024.feb24.test6;

/**
 * Complexity Analysis
 * Time complexity: O(n^2), where n is the size of the grid. We iterate over each element once for checking rows and twice for converting and checking columns.
 * Space complexity: O(n), where n is the size of the grid. We create an auxiliary array to keep track of found numbers in rows and columns.
 */
public class SubSudokuValidator {

    // Main validation method
    public static boolean validateGrid(int[][] grid) {
        if (grid == null || grid.length == 0 || grid.length != grid[0].length) {
            return false;
        }

        int size = grid.length;

        for (int i = 0; i < size; i++) {
            if (!hasAllNumbers(grid[i], size) || !hasAllNumbers(colToArray(grid, i), size)) {
                return false;
            }
        }

        return true;
    }

    // Check if array contains all numbers from 1 to N
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

    // Convert column to array
    private static int[] colToArray(int[][] grid, int col) {
        int[] colArray = new int[grid.length];
        for (int i = 0; i < grid.length; i++) {
            colArray[i] = grid[i][col];
        }
        return colArray;
    }

    // Main method for execution and testing
    public static void main(String[] args) {
        // Example grids
        int[][] grid1 = {{2, 3, 1}, {1, 2, 3}, {3, 1, 2}};
        int[][] grid2 = {{1, 2, 3}, {3, 2, 1}, {2, 3, 1}};
        int[][] grid3 = {{2, 2, 3}, {3, 1, 2}, {2, 3, 1}};
        int[][] grid4 = {{1}};
        int[][] grid5 = {{-1, -2, -3}, {-2, -3, -1}, {-3, -1, -2}};

        // Validations
        System.out.println("grid1: " + validateGrid(grid1)); // True
        System.out.println("grid2: " + validateGrid(grid2)); // False
        System.out.println("grid3: " + validateGrid(grid3)); // False
        System.out.println("grid4: " + validateGrid(grid4)); // True
        System.out.println("grid5: " + validateGrid(grid5)); // False
    }
}
