package com.interview.notes.code.year.y2025.august.common.test1;

public class StaircaseClimber {

    public static int countWays(int n) {
        if (n <= 1) return 1;

        int[] dp = new int[n + 1];
        dp[0] = 1; // 1 way to stay at ground
        dp[1] = 1; // 1 way to climb 1 step

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    public static void main(String[] args) {
        int steps = 5;
        System.out.println("Distinct ways to climb " + steps + " steps: " + countWays(steps));
    }
}
