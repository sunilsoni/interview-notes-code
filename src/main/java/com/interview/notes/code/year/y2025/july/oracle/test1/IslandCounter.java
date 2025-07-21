package com.interview.notes.code.year.y2025.july.oracle.test1;

public class IslandCounter {

    public static void main(String[] args) {
        // Test Case 1: Diagonal connections count as one island
        int[][] grid1 = {
                {1, 0, 1},
                {0, 1, 0},
                {1, 0, 1}
        }; // Should be 1 island because all 1's are diagonally connected

        // Test Case 2: Multiple islands with diagonal connections
        int[][] grid2 = {
                {1, 1, 0, 0},
                {0, 1, 0, 1},
                {1, 0, 1, 0}
        }; // Should be 2 islands

        // Test Case 3: Single cell islands
        int[][] grid3 = {
                {1, 0, 1},
                {0, 0, 0},
                {1, 0, 1}
        }; // Should be 4 separate islands

        // Test Case 4: Large grid with diagonal patterns
        int[][] grid4 = new int[100][100];
        for (int i = 0; i < 100; i++) {
            grid4[i][i] = 1; // Diagonal line of 1's
        }

        // Run all test cases
        runTest("Test 1 (Diagonal Connected)", grid1, 1);
        runTest("Test 2 (Multiple Islands)", grid2, 2);
        runTest("Test 3 (Separate Islands)", grid3, 4);
        runTest("Test 4 (Large Diagonal)", grid4, 1);
    }

    // Helper method to run tests and verify results
    private static void runTest(String testName, int[][] grid, int expectedIslands) {
        // Create a copy of grid to preserve original for display
        int[][] gridCopy = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            gridCopy[i] = grid[i].clone();
        }

        long startTime = System.currentTimeMillis();
        int result = numIslands(gridCopy);
        long endTime = System.currentTimeMillis();

        System.out.println(testName + ": " +
                (result == expectedIslands ? "PASS" : "FAIL") +
                " (Expected: " + expectedIslands +
                ", Got: " + result +
                ", Time: " + (endTime - startTime) + "ms)");

        // Print the original grid for visualization
        System.out.println("Grid:");
        printGrid(grid);
        System.out.println();
    }

    // Helper method to print grid
    private static void printGrid(int[][] grid) {
        for (int[] row : grid) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    // Main solution method to count islands
    public static int numIslands(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int islands = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        // Define all 8 directions (including diagonals)
        int[][] directions = {
                {-1, -1}, // Top-left
                {-1, 0},  // Top
                {-1, 1},  // Top-right
                {0, -1},  // Left
                {0, 1},   // Right
                {1, -1},  // Bottom-left
                {1, 0},   // Bottom
                {1, 1}    // Bottom-right
        };

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    islands++;
                    exploreIsland(grid, i, j, directions);
                }
            }
        }

        return islands;
    }

    // Modified exploreIsland to handle diagonal neighbors
    private static void exploreIsland(int[][] grid, int i, int j, int[][] directions) {
        if (i < 0 || i >= grid.length ||
                j < 0 || j >= grid[0].length ||
                grid[i][j] != 1) {
            return;
        }

        // Mark current cell as visited
        grid[i][j] = 2;

        // Explore all 8 directions
        for (int[] dir : directions) {
            int newRow = i + dir[0];
            int newCol = j + dir[1];
            exploreIsland(grid, newRow, newCol, directions);
        }
    }
}
