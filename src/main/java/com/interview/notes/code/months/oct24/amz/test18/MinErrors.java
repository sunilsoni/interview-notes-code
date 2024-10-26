package com.interview.notes.code.months.oct24.amz.test18;

public class MinErrors {
    private static final int MOD = 1000000007;

    public static int getMinErrors(String errorString, int x, int y) {
        int n = errorString.length();
        long[][] dp = new long[n + 1][2]; // dp[i][0] for '0', dp[i][1] for '1'

        // Initialize dp arrays
        for (int i = 0; i <= n; i++) {
            dp[i][0] = dp[i][1] = Long.MAX_VALUE;
        }
        dp[0][0] = dp[0][1] = 0;

        for (int i = 1; i <= n; i++) {
            char c = errorString.charAt(i - 1);
            if (c == '0' || c == '!') {
                dp[i][0] = Math.min(dp[i][0], dp[i - 1][0]);
                dp[i][0] = Math.min(dp[i][0], dp[i - 1][1] + y);
            }
            if (c == '1' || c == '!') {
                dp[i][1] = Math.min(dp[i][1], dp[i - 1][1]);
                dp[i][1] = Math.min(dp[i][1], dp[i - 1][0] + x);
            }
        }

        long result = Math.min(dp[n][0], dp[n][1]);
        return (int) (result % MOD);
    }

    public static void main(String[] args) {
        String errorString = "101!1";
        int x = 2;
        int y = 3;
        System.out.println(getMinErrors(errorString, x, y)); // Output: 9
    }
}
