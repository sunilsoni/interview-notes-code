package com.interview.notes.code.year.y2024.aug24.test13;

public class Main {
    public static int getMinErrors(String errorString, int x, int y) {
        int MOD = 1000000007;
        int n = errorString.length();

        // Arrays to count number of subsequences
        long[] count0 = new long[n + 1];
        long[] count1 = new long[n + 1];

        // Forward pass to count subsequences ending at each position
        for (int i = 0; i < n; i++) {
            count0[i + 1] = count0[i];
            count1[i + 1] = count1[i];
            if (errorString.charAt(i) == '0') {
                count0[i + 1] = (count0[i + 1] + 1) % MOD;
            }
            if (errorString.charAt(i) == '1') {
                count1[i + 1] = (count1[i + 1] + count0[i]) % MOD;
            }
        }

        // Backward pass to count subsequences starting at each position
        long[] count0_rev = new long[n + 1];
        long[] count1_rev = new long[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            count0_rev[i] = count0_rev[i + 1];
            count1_rev[i] = count1_rev[i + 1];
            if (errorString.charAt(i) == '0') {
                count0_rev[i] = (count0_rev[i] + count1_rev[i + 1]) % MOD;
            }
            if (errorString.charAt(i) == '1') {
                count1_rev[i] = (count1_rev[i] + 1) % MOD;
            }
        }

        long minErrors = Long.MAX_VALUE;

        // Compute errors by treating '!' as '0'
        long errors0 = 0;
        for (int i = 0; i < n; i++) {
            if (errorString.charAt(i) == '!') {
                errors0 = (errors0 + count1[i]) % MOD;
            }
        }
        errors0 = (errors0 * y) % MOD;

        // Compute errors by treating '!' as '1'
        long errors1 = 0;
        for (int i = 0; i < n; i++) {
            if (errorString.charAt(i) == '!') {
                errors1 = (errors1 + count0_rev[i + 1]) % MOD;
            }
        }
        errors1 = (errors1 * x) % MOD;

        // Compute total errors for mixed cases
        for (int i = 0; i <= n; i++) {
            long totalErrors = (errors0 + errors1) % MOD;
            minErrors = Math.min(minErrors, totalErrors);
        }

        return (int) minErrors;
    }

    public static void main(String[] args) {
        System.out.println(getMinErrors("01!0", 2, 3)); // Output: 8
        System.out.println(getMinErrors("!!!!!!!", 23, 47)); // Output: 0
    }
}
