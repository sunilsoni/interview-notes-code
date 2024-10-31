package com.interview.notes.code.months.oct24.amazon.test20;

import java.util.Arrays;

public class AmazonDatabaseErrorSolver {
    private static final int MOD = 1_000_000_007;

    public static int getMinErrors(String errorString, int x, int y) {
        int n = errorString.length();
        long[][][] dp = new long[n + 1][2][2];

        // Initialize dp array
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < 2; j++) {
                Arrays.fill(dp[i][j], Long.MAX_VALUE / 2);
            }
        }

        // Base case
        dp[0][0][0] = dp[0][1][0] = 0;

        for (int i = 1; i <= n; i++) {
            char c = errorString.charAt(i - 1);
            for (int curr = 0; curr < 2; curr++) {
                if (c != '!' && c - '0' != curr) continue;

                for (int prev = 0; prev < 2; prev++) {
                    long errors = 0;
                    if (prev == 0 && curr == 1) errors = x;
                    if (prev == 1 && curr == 0) errors = y;

                    dp[i][curr][0] = Math.min(dp[i][curr][0], dp[i - 1][prev][0] + errors);
                    dp[i][curr][1] = Math.min(dp[i][curr][1], dp[i - 1][prev][1] + errors);
                }

                // Consider this as a potential end of subsequence
                dp[i][curr][1] = Math.min(dp[i][curr][1], dp[i][curr][0]);
            }
        }

        long minErrors = Math.min(dp[n][0][1], dp[n][1][1]);
        return (int) (minErrors % MOD);
    }

    public static void main(String[] args) {
        // Test cases
        testCase("101!1", 2, 3, 9);
        testCase("1!0!1", 2, 3, 5);
        testCase("!!!", 1, 1, 0);
        testCase("0000", 5, 5, 0);
        testCase("1111", 5, 5, 0);
        testCase("01010101", 1, 1, 7);
        testCase("!0!0!0!0!", 2, 3, 0);
        testCase("!1!1!1!1!", 2, 3, 0);

        // Large input test case
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            sb.append("!");
        }
        testCase(sb.toString(), 1000000, 1000000, 0);
    }

    private static void testCase(String errorString, int x, int y, int expected) {
        int result = getMinErrors(errorString, x, y);
        System.out.println("Input: " + errorString + ", x=" + x + ", y=" + y);
        System.out.println("Expected: " + expected + ", Got: " + result);
        System.out.println(result == expected ? "PASS" : "FAIL");
        System.out.println();
    }
}
