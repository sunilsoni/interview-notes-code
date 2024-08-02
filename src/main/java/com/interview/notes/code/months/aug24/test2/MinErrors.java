package com.interview.notes.code.months.aug24.test2;

import java.util.Arrays;

public class MinErrors {

    private static final int MOD = 1000000007;

    public static void main(String[] args) {
        // Example tests
        System.out.println(getMinErrors("010!", 2, 3)); // Expected output: 8
        System.out.println(getMinErrors("!!!!!!!", 23, 47)); // Expected output: 0
        System.out.println(getMinErrors("1011!1", 2, 3)); // Expected output: 9
    }

    public static int getMinErrors(String errorString, int x, int y) {
        int n = errorString.length();
        int[][] dp = new int[n + 1][4];

        for (int i = 1; i <= n; i++) {
            char c = errorString.charAt(i - 1);
            Arrays.fill(dp[i], Integer.MAX_VALUE);

            for (int prev = 0; prev < 4; prev++) {
                if (c == '0' || c == '!') {
                    int errors = dp[i - 1][prev];
                    if ((prev & 2) != 0) errors = (errors + y) % MOD;
                    dp[i][prev & 2] = Math.min(dp[i][prev & 2], errors);
                }
                if (c == '1' || c == '!') {
                    int errors = dp[i - 1][prev];
                    if ((prev & 1) != 0) errors = (errors + x) % MOD;
                    dp[i][(prev & 2) | 1] = Math.min(dp[i][(prev & 2) | 1], errors);
                }
            }
        }

        int result = Integer.MAX_VALUE;
        for (int state = 0; state < 4; state++) {
            result = Math.min(result, dp[n][state]);
        }
        return result;
    }
}
