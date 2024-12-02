package com.interview.notes.code.year.y2024.july24.Test1;

public class CountWays {
    public static void main(String[] args) {
        // Example 1
        int steps1 = 3;
        int arrLen1 = 2;
        System.out.println("Example 1 Output: " + countWays(steps1, arrLen1)); // Expected output: 4

        // Example 2
        int steps2 = 2;
        int arrLen2 = 4;
        System.out.println("Example 2 Output: " + countWays(steps2, arrLen2)); // Expected output: 2
    }

    private static int countWays(int steps, int arrLen) {
        final int MOD = 1000000007;
        int[][] dp = new int[steps + 1][arrLen];
        dp[0][0] = 1;

        for (int step = 1; step <= steps; step++) {
            for (int pos = 0; pos < arrLen; pos++) {
                dp[step][pos] = dp[step - 1][pos]; // Stay
                if (pos > 0) dp[step][pos] = (dp[step][pos] + dp[step - 1][pos - 1]) % MOD; // Left
                if (pos < arrLen - 1) dp[step][pos] = (dp[step][pos] + dp[step - 1][pos + 1]) % MOD; // Right
            }
        }
        return dp[steps][0];
    }
}
