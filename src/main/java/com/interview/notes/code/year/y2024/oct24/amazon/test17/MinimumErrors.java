package com.interview.notes.code.year.y2024.oct24.amazon.test17;

public class MinimumErrors {

    private static final int MOD = 1000000007;

    public static int getMinErrors(String errorString, int x, int y) {
        int n = errorString.length();
        int[][] dp = new int[n][2];

        // Initialize the DP array
        dp[0][0] = errorString.charAt(0) == '0' ? 0 : Integer.MAX_VALUE / 2;
        dp[0][1] = errorString.charAt(0) == '1' ? 0 : Integer.MAX_VALUE / 2;

        for (int i = 1; i < n; i++) {
            char ch = errorString.charAt(i);

            // If the current character is '0' or '1'
            if (ch != '!') {
                dp[i][ch - '0'] = 0;
                dp[i][1 - ch + '0'] = Integer.MAX_VALUE / 2;
            } else {
                // If the current character is '!'
                dp[i][0] = Math.min(dp[i - 1][0] + y, dp[i - 1][1]);
                dp[i][1] = Math.min(dp[i - 1][1] + x, dp[i - 1][0]);
            }

            // Take modulo to avoid integer overflow
            dp[i][0] %= MOD;
            dp[i][1] %= MOD;
        }

        return Math.min(dp[n - 1][0], dp[n - 1][1]);
    }

    public static void main(String[] args) {
        // Test cases
        String[] errorStrings = {"101!1", "010!", "!1!0", "11111"};
        int[][] xyPairs = {{2, 3}, {1, 2}, {3, 4}, {5, 6}};
        int[] expectedResults = {9, 1, 6, 0};

        for (int i = 0; i < errorStrings.length; i++) {
            int result = getMinErrors(errorStrings[i], xyPairs[i][0], xyPairs[i][1]);
            if (result == expectedResults[i]) {
                System.out.println("Test case " + (i + 1) + " passed.");
            } else {
                System.out.println("Test case " + (i + 1) + " failed. Expected: " + expectedResults[i] + ", Actual: " + result);
            }
        }
    }
}