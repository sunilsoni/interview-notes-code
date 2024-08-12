package com.interview.notes.code.months.aug24.test15;

import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        // Example 1 (from the original problem statement)
        System.out.println("Example 1:");
        System.out.println("Input: steps = 3, arrLen = 2");
        System.out.println("Output: " + numWays(3, 2));
        System.out.println("Expected: 4");
        System.out.println();

        // Example 2 (from the original problem statement)
        System.out.println("Example 2:");
        System.out.println("Input: steps = 2, arrLen = 4");
        System.out.println("Output: " + numWays(2, 4));
        System.out.println("Expected: 2");
        System.out.println();

        // New Example 3
        System.out.println("Example 3:");
        System.out.println("Input: steps = 4, arrLen = 3");
        System.out.println("Output: " + numWays(4, 3));
        System.out.println("Expected: 8");
        System.out.println();

        // Allow user input
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your own input (steps arrLen):");
        String input = scanner.nextLine();
        String[] parts = input.split(" ");
        int steps = Integer.parseInt(parts[0]);
        int arrLen = Integer.parseInt(parts[1]);
        System.out.println("Output: " + numWays(steps, arrLen));
    }

    public static int numWays(int steps, int arrLen) {
        final int MOD = 1000000007;

        int maxPosition = Math.min(arrLen - 1, steps / 2);
        long[][] dp = new long[steps + 1][maxPosition + 1];

        dp[0][0] = 1;

        for (int i = 1; i <= steps; i++) {
            for (int j = 0; j <= maxPosition; j++) {
                dp[i][j] = dp[i - 1][j];

                if (j > 0) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j - 1]) % MOD;
                }

                if (j < maxPosition) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j + 1]) % MOD;
                }
            }
        }

        return (int) dp[steps][0];
    }
}
