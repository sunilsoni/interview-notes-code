package com.interview.notes.code.year.y2023.dec23.test3;

public class Solution {
    public static int maxGameScore(int[] cell) {
        int n = cell.length;
        int[] dp = new int[n];
        dp[0] = 0; // First cell has value 0

        // Calculate dp[i] for each cell
        for (int i = 1; i < n; i++) {
            dp[i] = cell[i]; // Initialize with cell value
            for (int p = 3; p <= i; p += 10) { // Prime numbers ending in 3
                if (i - p >= 0) {
                    dp[i] = Math.max(dp[i], dp[i - p] + cell[i]);
                }
            }
        }

        return dp[n - 1]; // Maximum score achievable
    }

    public static void main(String[] args) {
        int[] cell = {0, -10, 100, -20}; // Example input
        System.out.println(maxGameScore(cell)); // Output: 70
    }
}