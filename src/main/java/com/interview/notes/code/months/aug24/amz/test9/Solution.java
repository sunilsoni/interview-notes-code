package com.interview.notes.code.months.aug24.amz.test9;

public class Solution {
    private static final int MOD = 1000000007;

    public static int getMinErrors(String errorString, int x, int y) {
        int n = errorString.length();
        long[][] dp = new long[n + 1][4];

        // Initialize base cases
        dp[0][0] = 1; // Empty string

        for (int i = 1; i <= n; i++) {
            char c = errorString.charAt(i - 1);

            for (int j = 0; j < 4; j++) {
                if (c == '!' || c == '0' && (j & 1) == 0 || c == '1' && (j & 1) == 1) {
                    int prev = j;
                    if ((j & 2) != 0) prev ^= 1;

                    dp[i][j] = dp[i - 1][prev];

                    if ((j & 2) != 0) {
                        if ((j & 1) == 0) dp[i][j] = (dp[i][j] + x * dp[i - 1][prev ^ 1]) % MOD;
                        else dp[i][j] = (dp[i][j] + y * dp[i - 1][prev ^ 1]) % MOD;
                    }
                }
            }
        }

        return (int) Math.min(dp[n][1], dp[n][3]);
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(getMinErrors("101!1", 2, 3)); // Expected: 9
        System.out.println(getMinErrors("01!0", 2, 3)); // Expected: 8
        System.out.println(getMinErrors("!!!!!!!", 23, 47)); // Expected: 0

        // Additional test cases
        System.out.println(getMinErrors("0!1!0", 1, 1)); // Expected: 2
        System.out.println(getMinErrors("1!0!1", 5, 3)); // Expected: 6
    }
}
