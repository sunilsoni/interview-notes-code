package com.interview.notes.code.year.y2025.april.goldman_sachs.test1;

class Solution {
    public static int optimalPath(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int rows = grid.length;
        int cols = grid[0].length;
        int[][] dp = new int[rows][cols];

        // Initialize first cell
        dp[rows - 1][0] = grid[rows - 1][0];

        // Fill first column (can only move up)
        for (int i = rows - 2; i >= 0; i--) {
            dp[i][0] = dp[i + 1][0] + grid[i][0];
        }

        // Fill first row (can only move right)
        for (int j = 1; j < cols; j++) {
            dp[rows - 1][j] = dp[rows - 1][j - 1] + grid[rows - 1][j];
        }

        // Fill rest of the grid
        for (int i = rows - 2; i >= 0; i--) {
            for (int j = 1; j < cols; j++) {
                dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }

        return dp[0][cols - 1];
    }

    public static boolean doTestsPass() {
        // Test case 1: Original test
        int[][] test1 = {
                {0, 0, 0, 0, 5},
                {0, 1, 1, 1, 0},
                {2, 0, 0, 0, 0}
        };

        // Test case 2: Single row
        int[][] test2 = {{1, 2, 3}};

        // Test case 3: Single column
        int[][] test3 = {{1}, {2}, {3}};

        // Test case 4: Larger grid
        int[][] test4 = {
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1}
        };

        // Test case 5: Zero grid
        int[][] test5 = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };

        boolean result = true;
        result &= optimalPath(test1) == 10;
        result &= optimalPath(test2) == 6;
        result &= optimalPath(test3) == 6;
        result &= optimalPath(test4) == 8;
        result &= optimalPath(test5) == 0;

        return result;
    }

    public static void main(String[] args) {
        if (doTestsPass()) {
            System.out.println("All tests pass");
        } else {
            System.out.println("Tests fail.");
        }
    }
}
