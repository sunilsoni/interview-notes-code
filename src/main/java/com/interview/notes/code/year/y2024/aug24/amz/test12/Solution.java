package com.interview.notes.code.year.y2024.aug24.amz.test12;

public class Solution {
    private static final int MOD = 1000000007;

    public static int getMinErrors(String errorString, int x, int y) {
        int n = errorString.length();
        long[][] dp = new long[n + 1][2];
        dp[0][0] = dp[0][1] = 0;

        for (int i = 1; i <= n; i++) {
            char c = errorString.charAt(i - 1);
            if (c == '0' || c == '!') {
                dp[i][0] = Math.min(dp[i - 1][0], dp[i - 1][1] + y);
            }
            if (c == '1' || c == '!') {
                dp[i][1] = Math.min(dp[i - 1][1], dp[i - 1][0] + x);
            }
            if (c == '!') {
                dp[i][0] = Math.min(dp[i][0], dp[i - 1][0]);
                dp[i][1] = Math.min(dp[i][1], dp[i - 1][1]);
            }
            dp[i][0] %= MOD;
            dp[i][1] %= MOD;
        }

        return (int) Math.min(dp[n][0], dp[n][1]);
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(getMinErrors("101!1", 2, 3));  // Expected: 9
        System.out.println(getMinErrors("01!0", 2, 3));   // Expected: 8
        System.out.println(getMinErrors("!!!!!!!", 23, 47)); // Expected: 0
    }
}
