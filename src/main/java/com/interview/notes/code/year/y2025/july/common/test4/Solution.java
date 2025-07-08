package com.interview.notes.code.year.y2025.july.common.test4;
/*
# 💎 Problem Name: ***Optimal Path***
 */
class Solution {
    /**
     * Finds optimal path to collect maximum rocks from SoCal to NY
     * Using dynamic programming approach for optimal substructure
     */
    public static int optimalPath(int[][] grid) {
        // Handle edge cases
        if (grid == null || grid.length == 0) return 0;
        
        int rows = grid.length;
        int cols = grid[0].length;
        
        // Create DP table to store maximum rocks at each position
        int[][] dp = new int[rows][cols];
        
        // Initialize starting point (SoCal)
        dp[rows-1][0] = grid[rows-1][0];
        
        // Fill first column (can only move up)
        for (int i = rows-2; i >= 0; i--) {
            dp[i][0] = dp[i+1][0] + grid[i][0];
        }
        
        // Fill first row (can only move right)
        for (int j = 1; j < cols; j++) {
            dp[rows-1][j] = dp[rows-1][j-1] + grid[rows-1][j];
        }
        
        // Fill rest of the table using max of left and bottom cell
        for (int i = rows-2; i >= 0; i--) {
            for (int j = 1; j < cols; j++) {
                dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]) + grid[i][j];
            }
        }
        
        // Return value at destination (NY)
        return dp[0][cols-1];
    }

    /**
     * Main method with comprehensive test cases
     */
    public static void main(String[] args) {
        // Test Case 1: Given example
        int[][] test1 = {
            {0, 0, 0, 0, 5},
            {0, 1, 1, 1, 0},
            {2, 0, 0, 0, 0}
        };
        
        // Test Case 2: Single row
        int[][] test2 = {{1, 2, 3, 4}};
        
        // Test Case 3: Single column
        int[][] test3 = {{1}, {2}, {3}};
        
        // Test Case 4: Larger grid
        int[][] test4 = {
            {0, 0, 0, 0, 5, 0},
            {0, 1, 1, 1, 0, 2},
            {2, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1}
        };

        // Run all tests
        System.out.println("Test 1: " + (optimalPath(test1) == 10 ? "PASS" : "FAIL"));
        System.out.println("Test 2: " + (optimalPath(test2) == 10 ? "PASS" : "FAIL"));
        System.out.println("Test 3: " + (optimalPath(test3) == 6 ? "PASS" : "FAIL"));
        System.out.println("Test 4: " + (optimalPath(test4) == 12 ? "PASS" : "FAIL"));
    }
}
