package com.interview.notes.code.year.y2024.aug24.amz.test10;

class Solution {

    private static final int MOD = 1000000007;

    public static int getMinErrors(String errorString, int x, int y) {
        int n = errorString.length();
        long[][] dp = new long[n + 1][2]; // dp[i][0]: min errors with '0' at i-th position, dp[i][1]: min errors with '1' at i-th position

        // Base case:
        dp[0][0] = 0;
        dp[0][1] = 0;

        for (int i = 1; i <= n; i++) {
            char currentChar = errorString.charAt(i - 1);

            if (currentChar == '0') {
                dp[i][0] = dp[i - 1][1] + y; // If '0' at i-th position, previous character must be '1'
                dp[i][1] = dp[i - 1][0] + x; // If '1' at i-th position, previous character must be '0'
            } else if (currentChar == '1') {
                dp[i][0] = dp[i - 1][1] + x; // If '0' at i-th position, previous character must be '1'
                dp[i][1] = dp[i - 1][0] + y; // If '1' at i-th position, previous character must be '0'
            } else if (currentChar == '!') {
                // Try both '0' and '1' at i-th position and take the minimum
                dp[i][0] = Math.min(dp[i - 1][0], dp[i - 1][1] + y);
                dp[i][1] = Math.min(dp[i - 1][1], dp[i - 1][0] + x);
            }
        }

        return (int) ((Math.min(dp[n][0], dp[n][1])) % MOD);
    }


    public static void main(String[] args) {
        // Test cases as provided in the screenshots "01!0"
        System.out.println(getMinErrors("01!0", 2, 3)); // Output: 8
        System.out.println(getMinErrors("010!", 2, 3)); // Output: 8
        System.out.println(getMinErrors("!!!!!!", 23, 47)); // Output: 0
    }

}

