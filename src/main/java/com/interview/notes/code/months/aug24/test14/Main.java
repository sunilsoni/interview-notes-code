package com.interview.notes.code.months.aug24.test14;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] parts = input.split(" ");
        int steps = Integer.parseInt(parts[0]);
        int arrLen = Integer.parseInt(parts[1]);

        System.out.println(numWays(steps, arrLen));
    }

    public static int numWays(int steps, int arrLen) {
        int mod = (int) 1e9 + 7;
        int maxColumn = Math.min(arrLen - 1, steps);
        int[][] dp = new int[steps + 1][maxColumn + 1];
        dp[0][0] = 1;

        for (int i = 1; i <= steps; i++) {
            for (int j = 0; j <= maxColumn; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j > 0) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j - 1]) % mod;
                }
                if (j < maxColumn) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j + 1]) % mod;
                }
            }
        }

        return dp[steps][0];
    }
}
