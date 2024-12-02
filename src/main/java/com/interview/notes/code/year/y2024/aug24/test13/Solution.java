package com.interview.notes.code.year.y2024.aug24.test13;

import java.util.Arrays;

public class Solution {
    private static final int MOD = 1_000_000_007;

    public static int getMinErrors(String errorString, int x, int y) {
        int n = errorString.length();
        long[][] dp = new long[n + 1][4];

        // Initialize base cases
        Arrays.fill(dp[0], Long.MAX_VALUE / 2);
        dp[0][0] = 0; // No errors for empty string

        for (int i = 1; i <= n; i++) {
            char c = errorString.charAt(i - 1);
            Arrays.fill(dp[i], Long.MAX_VALUE / 2);

            for (int prev = 0; prev < 4; prev++) {
                if (c == '0' || c == '!') {
                    int next = prev & 2;
                    dp[i][next] = Math.min(dp[i][next], dp[i - 1][prev]);
                    if (prev % 2 == 1) { // If previous ended with 1
                        dp[i][next | 2] = Math.min(dp[i][next | 2], dp[i - 1][prev] + y);
                    }
                }
                if (c == '1' || c == '!') {
                    int next = prev | 1;
                    dp[i][next] = Math.min(dp[i][next], dp[i - 1][prev]);
                    if (prev < 2) { // If previous ended with 0
                        dp[i][next | 2] = Math.min(dp[i][next | 2], dp[i - 1][prev] + x);
                    }
                }
            }
        }

        // Calculate the minimum errors
        long minErrors = Math.min(Math.min(dp[n][0], dp[n][1]), Math.min(dp[n][2], dp[n][3]));
        return (int) (minErrors % MOD);
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(getMinErrors("101!1", 2, 3)); // Expected: 9
        System.out.println(getMinErrors("01!0", 2, 3)); // Expected: 8
        System.out.println(getMinErrors("!!!!!!!", 23, 47)); // Expected: 0
    }
}
