package com.interview.notes.code.year.y2025.april.goldman_sachs.test4;

class Solution {

    /**
     * Find the optimal path from So_Cal (bottom left) to New_York (top right)
     * by collecting the maximum number of rare rocks. Movement allowed is North or East.
     *
     * @param grid a 2D array representing rocks in each city.
     * @return maximum number of rocks collected along the optimal path.
     */
    public static int optimalPath(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;

        int m = grid.length;      // number of rows
        int n = grid[0].length;   // number of columns

        // dp[i][j] will store the maximum rocks from cell (i,j) to finish.
        int[][] dp = new int[m][n];

        // Starting cell is So_Cal at bottom-left (last row, first column).
        dp[m - 1][0] = grid[m - 1][0];

        // Fill the bottom row (only east moves allowed).
        for (int j = 1; j < n; j++) {
            dp[m - 1][j] = grid[m - 1][j] + dp[m - 1][j - 1];
        }

        // Fill the leftmost column (only north moves allowed).
        for (int i = m - 2; i >= 0; i--) {
            dp[i][0] = grid[i][0] + dp[i + 1][0];
        }

        // Fill the remaining cells.
        // Since allowed moves are north (up) and east (right), we process rows from bottom to top and columns from left to right.
        for (int i = m - 2; i >= 0; i--) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = grid[i][j] + Math.max(dp[i][j - 1], dp[i + 1][j]);
            }
        }

        // The finish cell is New_York at top-right.
        return dp[0][n - 1];
    }

    /**
     * Runs several test cases and prints pass/fail for each.
     */
    public static void main(String[] args) {
        boolean allTestsPass = true;

        // Test case 1: Provided example.
        int[][] grid1 = {
                {0, 0, 0, 0, 5},
                {0, 1, 1, 1, 0},
                {2, 0, 0, 0, 0}
        };
        allTestsPass &= (optimalPath(grid1) == 10);

        // Test case 2: Single cell grid.
        int[][] grid2 = {
                {7}
        };
        allTestsPass &= (optimalPath(grid2) == 7);

        // Test case 3: Grid with one row.
        int[][] grid3 = {
                {1, 2, 3, 4}
        };
        // Only east moves are possible.
        allTestsPass &= (optimalPath(grid3) == 1 + 2 + 3 + 4);

        // Test case 4: Grid with one column.
        int[][] grid4 = {
                {4},
                {5},
                {6}
        };
        // Only north moves are possible (from bottom to top).
        allTestsPass &= (optimalPath(grid4) == 4 + 5 + 6);

        // Test case 5: Larger grid test (for performance).
        int m = 1000, n = 1000;
        int[][] largeGrid = new int[m][n];
        // Fill grid with 1's.
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                largeGrid[i][j] = 1;
            }
        }
        // Expected: path sum = (m + n - 1) since we add one cell per step.
        allTestsPass &= (optimalPath(largeGrid) == (m + n - 1));

        // Print final result.
        if (allTestsPass) {
            System.out.println("All tests pass.");
        } else {
            System.out.println("Tests fail.");
        }
    }
}
