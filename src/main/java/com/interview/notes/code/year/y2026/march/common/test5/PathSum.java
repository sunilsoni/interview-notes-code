package com.interview.notes.code.year.y2026.march.common.test5;

import java.util.Arrays;

public class PathSum {

    public static int solve(int[][] grid) {
        // 1. Get grid dimensions using 'var' for less code words
        var rows = grid.length;
        var cols = grid[0].length;

        // 2. Loop through rows and columns to update costs in-place
        for (var i = 0; i < rows; i++) {
            for (var j = 0; j < cols; j++) {
                
                // Starting point: already has its own value, so skip it
                if (i == 0 && j == 0) continue;

                // Top row logic: can only arrive from the left cell
                if (i == 0) grid[i][j] += grid[i][j - 1];

                // Left column logic: can only arrive from the cell above
                else if (j == 0) grid[i][j] += grid[i - 1][j];

                // Middle cells: add the smaller of the two neighbors (Top vs Left)
                else grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
            }
        }
        // 3. The final answer is now stored in the bottom-right corner
        return grid[rows - 1][cols - 1];
    }

    public static void main(String[] args) {
        // --- Test Case 1: Example from Image ---
        int[][] input1 = {{1, 2, 3}, {4, 5, 6}};
        int result1 = solve(input1);
        System.out.println(result1 == 12 ? "PASS (12)" : "FAIL");

        // --- Test Case 2: Large Data (1000x1000) ---
        // Using Stream API to generate a large grid efficiently
        int[][] largeGrid = new int[1000][1000];
        for (int[] row : largeGrid) Arrays.fill(row, 1);
        
        // Logic: In a 1x1 grid, sum is 1. In a 2x2 of all 1s, sum is 3. 
        // Formula for grid of all 1s: (rows + cols - 1)
        int result2 = solve(largeGrid);
        System.out.println(result2 == 1999 ? "PASS (1999)" : "FAIL");
    }
}