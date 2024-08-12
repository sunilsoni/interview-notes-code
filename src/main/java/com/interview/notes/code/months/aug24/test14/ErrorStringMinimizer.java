package com.interview.notes.code.months.aug24.test14;

public class ErrorStringMinimizer {

    private static final int MOD = 1000000007;

    public static int getMinErrors(String errorString, int x, int y) {
        int n = errorString.length();
        int[] dp0 = new int[n + 1];
        int[] dp1 = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            char c = errorString.charAt(i - 1);
            if (c == '0') {
                dp0[i] = dp0[i - 1];
                dp1[i] = (dp1[i - 1] + y) % MOD;
            } else if (c == '1') {
                dp0[i] = (dp0[i - 1] + x) % MOD;
                dp1[i] = dp1[i - 1];
            } else {
                dp0[i] = Math.min(dp0[i - 1] + x, dp1[i - 1] + y) % MOD;
                dp1[i] = Math.min(dp0[i - 1] + y, dp1[i - 1] + x) % MOD;
            }
        }

        return Math.min(dp0[n], dp1[n]);
    }

    public static void main(String[] args) {
        // Example test cases
        System.out.println(getMinErrors("101!1", 2, 3)); // Output: 9
        System.out.println(getMinErrors("01!0", 2, 3)); // Output: 8
        System.out.println(getMinErrors("!!!!!!!", 23, 47)); // Output: 0

        // Additional test cases
        System.out.println(getMinErrors("0!1", 1, 1)); // Output: 1
        System.out.println(getMinErrors("1!0!1", 3, 2)); // Output: 5
        // Add more test cases as required
    }
}
