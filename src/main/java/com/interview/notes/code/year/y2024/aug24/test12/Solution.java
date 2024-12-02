package com.interview.notes.code.year.y2024.aug24.test12;

public class Solution {
    private static final int MOD = 1_000_000_007;

    public static int getMinErrors(String errorString, int x, int y) {
        int n = errorString.length();
        int[] dp0 = new int[n + 1]; // dp array for replacing '!' with '0'
        int[] dp1 = new int[n + 1]; // dp array for replacing '!' with '1'
        int mod = 1000000007;

        for (int i = 1; i <= n; i++) {
            char c = errorString.charAt(i - 1);
            if (c == '0') {
                dp0[i] = dp0[i - 1];
                dp1[i] = dp1[i - 1] + y;
            } else if (c == '1') {
                dp0[i] = dp0[i - 1] + x;
                dp1[i] = dp1[i - 1];
            } else { // c == '!'
                dp0[i] = Math.min(dp0[i - 1] + x, dp1[i - 1] + y);
                dp1[i] = Math.min(dp0[i - 1] + y, dp1[i - 1] + x);
            }
            dp0[i] %= mod;
            dp1[i] %= mod;
        }

        return Math.min(dp0[n], dp1[n]);
    }

    public static int getMinErrors1(String errorString, int x, int y) {
        int MOD = 1000000007;
        int n = errorString.length();

        // Dynamic programming arrays to store the minimum errors
        int[] dp0 = new int[n + 1]; // minimum errors ending with '0'
        int[] dp1 = new int[n + 1]; // minimum errors ending with '1'

        for (int i = 0; i < n; i++) {
            char c = errorString.charAt(i);

            if (c == '0') {
                dp0[i + 1] = dp0[i]; // no new subsequences
                dp1[i + 1] = dp1[i] + y; // '10' subsequences increase by y
            } else if (c == '1') {
                dp0[i + 1] = dp0[i] + x; // '01' subsequences increase by x
                dp1[i + 1] = dp1[i]; // no new subsequences
            } else if (c == '!') {
                int newDp0 = Math.min(dp0[i], dp1[i] + y); // choosing '0'
                int newDp1 = Math.min(dp1[i], dp0[i] + x); // choosing '1'

                dp0[i + 1] = newDp0 % MOD;
                dp1[i + 1] = newDp1 % MOD;
            }
        }

        return Math.min(dp0[n], dp1[n]) % MOD;
    }


    public static void main(String[] args) {
        System.out.println(getMinErrors("101!1", 2, 3)); // Expected: 12
        System.out.println(getMinErrors("01!0", 2, 3)); // Expected: 10
        System.out.println(getMinErrors("!!!!!!!", 23, 47)); // Expected: 443
    }
}
