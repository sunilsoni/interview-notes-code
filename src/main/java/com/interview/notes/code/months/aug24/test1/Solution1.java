package com.interview.notes.code.months.aug24.test1;

public class Solution1 {
    private static final int MOD = 1_000_000_007;

    public static int getMinErrors(String errorString, int x, int y) {
        int n = errorString.length();
        int[][] dp = new int[n + 1][4];

        // Initialize base cases
        for (int j = 0; j < 4; j++) {
            dp[0][j] = 0;
        }

        for (int i = 1; i <= n; i++) {
            char c = errorString.charAt(i - 1);
            for (int j = 0; j < 4; j++) {
                int prev0 = j & 1;
                int prev1 = (j & 2) >> 1;

                if (c == '0' || c == '!') {
                    int errors = dp[i - 1][j];
                    if (prev1 == 1) errors = (errors + y) % MOD;
                    dp[i][j & 2] = Math.min(dp[i][j & 2], errors);
                }

                if (c == '1' || c == '!') {
                    int errors = dp[i - 1][j];
                    if (prev0 == 1) errors = (errors + x) % MOD;
                    dp[i][j | 1] = Math.min(dp[i][j | 1], errors);
                }
            }

            // Reset to minimum for next iteration
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < 4; j++) {
                min = Math.min(min, dp[i][j]);
            }
            for (int j = 0; j < 4; j++) {
                if (dp[i][j] > min) dp[i][j] = min;
            }
        }

        int result = Integer.MAX_VALUE;
        for (int j = 0; j < 4; j++) {
            result = Math.min(result, dp[n][j]);
        }
        return result;
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(getMinErrors("101!1", 2, 3)); // Expected: 9
        System.out.println(getMinErrors("01!0", 2, 3)); // Expected: 8
        System.out.println(getMinErrors("!!!!!!!", 23, 47)); // Expected: 0
        System.out.println(getMinErrors("0110", 2, 3)); // Expected: 5
        System.out.println(getMinErrors("!0!1!", 1, 1)); // Expected: 2
    }
}
