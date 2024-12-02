package com.interview.notes.code.year.y2024.aug24.amz.test10;

public class ErrorMinimizer {

    public static int getMinErrors(String errorString, int x, int y) {
        int MOD = 1000000007;
        int n = errorString.length();

        long[][] dp = new long[n + 1][2];  // dp[i][0] - errors if '!' replaced with '0' till index i
        // dp[i][1] - errors if '!' replaced with '1' till index i

        int count0 = 0;
        int count1 = 0;

        for (int i = 1; i <= n; i++) {
            char ch = errorString.charAt(i - 1);
            if (ch == '0') {
                dp[i][0] = dp[i - 1][0];
                dp[i][1] = dp[i - 1][1] + x;
                count0++;
            } else if (ch == '1') {
                dp[i][0] = dp[i - 1][0] + y;
                dp[i][1] = dp[i - 1][1];
                count1++;
            } else {  // ch == '!'
                dp[i][0] = Math.min(dp[i - 1][0], dp[i - 1][1] + x);
                dp[i][1] = Math.min(dp[i - 1][0] + y, dp[i - 1][1]);
            }
        }

        return (int) (Math.min(dp[n][0], dp[n][1]) % MOD);
    }

    public static void main(String[] args) {
        // Test cases as provided in the screenshots
        System.out.println(getMinErrors("01!0", 2, 3)); // Expected Output: 8
        System.out.println(getMinErrors("!!!!!!", 23, 47)); // Expected Output: 0
    }
}
