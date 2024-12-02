package com.interview.notes.code.year.y2024.aug24.test15;

public class Main2 {
    public static void main(String[] args) {
        // Example test cases
        System.out.println(getMinErrors("01!0", 2, 3)); // Expected output: 8
        System.out.println(getMinErrors("!!!!!!!", 23, 47)); // Expected output: 0

        // Additional test cases
        System.out.println(getMinErrors("101!1", 2, 3)); // Expected output: 9
        System.out.println(getMinErrors("0!1!0", 4, 5)); // Expected output: 9
        System.out.println(getMinErrors("1!!0", 1, 2)); // Expected output: 2
    }

    public static int getMinErrors(String errorString, int x, int y) {
        int n = errorString.length();
        int[] dp0 = new int[n + 1]; // dp array for replacing '!' with '0'
        int[] dp1 = new int[n + 1]; // dp array for replacing '!' with '1'
        int mod = 1000000007;

        for (int i = 1; i <= n; i++) {
            char c = errorString.charAt(i - 1);
            if (c == '0') {
                dp0[i] = dp0[i - 1];
                dp1[i] = (int) (((long) dp1[i - 1] + y) % mod);
            } else if (c == '1') {
                dp0[i] = (int) (((long) dp0[i - 1] + x) % mod);
                dp1[i] = dp1[i - 1];
            } else { // c == '!'
                dp0[i] = (int) Math.min(((long) dp0[i - 1] + x) % mod, ((long) dp1[i - 1] + y) % mod);
                dp1[i] = (int) Math.min(((long) dp0[i - 1] + y) % mod, ((long) dp1[i - 1] + x) % mod);
            }
        }

        return Math.min(dp0[n], dp1[n]);
    }
}
