package com.interview.notes.code.year.y2025.march.amazon.test6;

public class NumberOfIslands {

    public static int countIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        int islandCount = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    islandCount++;
                    dfs(grid, i, j, rows, cols);
                }
            }
        }

        return islandCount;
    }

    private static void dfs(char[][] grid, int row, int col, int rows, int cols) {
        // Check boundaries and if current cell is land
        if (row < 0 || col < 0 || row >= rows || col >= cols || grid[row][col] == '0') {
            return;
        }

        // Mark current cell as visited by changing it to '0'
        grid[row][col] = '0';

        // Explore all four directions
        dfs(grid, row + 1, col, rows, cols); // Down
        dfs(grid, row - 1, col, rows, cols); // Up
        dfs(grid, row, col + 1, rows, cols); // Right
        dfs(grid, row, col - 1, rows, cols); // Left
    }

    public static void main(String[] args) {
        // Test Case 1
        char[][] grid1 = {
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '1'},
                {'0', '0', '0', '1', '0'}
        };
        int expected1 = 3;
        int result1 = countIslands(grid1);
        System.out.println("Test Case 1: " + (result1 == expected1 ? "PASS" : "FAIL") +
                " (Expected: " + expected1 + ", Got: " + result1 + ")");

        // Test Case 2
        char[][] grid2 = {
                {'0', '0', '1'}
        };
        int expected2 = 1;
        int result2 = countIslands(grid2);
        System.out.println("Test Case 2: " + (result2 == expected2 ? "PASS" : "FAIL") +
                " (Expected: " + expected2 + ", Got: " + result2 + ")");

        // Test Case 3
        char[][] grid3 = {
                {'1', '0', '1'}
        };
        int expected3 = 2;
        int result3 = countIslands(grid3);
        System.out.println("Test Case 3: " + (result3 == expected3 ? "PASS" : "FAIL") +
                " (Expected: " + expected3 + ", Got: " + result3 + ")");

        // Test Case 4
        char[][] grid4 = {
                {'0', '0', '0'}
        };
        int expected4 = 0;
        int result4 = countIslands(grid4);
        System.out.println("Test Case 4: " + (result4 == expected4 ? "PASS" : "FAIL") +
                " (Expected: " + expected4 + ", Got: " + result4 + ")");

        // Test Case 5 - Large Grid
        char[][] grid5 = generateLargeGrid(1000, 1000, 0.3);
        long startTime = System.currentTimeMillis();
        int result5 = countIslands(grid5);
        long endTime = System.currentTimeMillis();
        System.out.println("Test Case 5 (Large Grid 1000x1000): Found " + result5 +
                " islands in " + (endTime - startTime) + "ms");
    }

    // Helper method to generate a large grid for performance testing
    private static char[][] generateLargeGrid(int rows, int cols, double landProbability) {
        char[][] grid = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = Math.random() < landProbability ? '1' : '0';
            }
        }
        return grid;
    }
}