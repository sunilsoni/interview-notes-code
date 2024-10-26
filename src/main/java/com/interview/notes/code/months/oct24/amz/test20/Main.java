package com.interview.notes.code.months.oct24.amz.test20;

public class Main {

    public static void main(String[] args) {
        testGetMinErrors();
    }

    public static void testGetMinErrors() {
        // User's test case 1
        String errorString1 = "!0001!1";
        int x1 = 424;
        int y1 = 405;
        int expected1 = 3361;
        int result1 = getMinErrors(errorString1, x1, y1);
        System.out.println("User Test Case 1: " + (result1 == expected1 ? "PASS" : "FAIL"));

        // User's test case 2
        String errorString2 = "100!11!00000011111111";
        int x2 = 0;
        int y2 = 100;
        int expected2 = 800;
        int result2 = getMinErrors(errorString2, x2, y2);
        System.out.println("User Test Case 2: " + (result2 == expected2 ? "PASS" : "FAIL"));

        // User's test case 3
        String errorString3 = "!0001!1001!!100!0011!1!00!0011!11!0!0011!001!!1101!0010!1010!1001!!101!0111!0!11!0110!10!0!1100!010!!1101!0011!1010!0100!!001!1001!0!10!0010!10!0!1010!100!!1100!0110!1001!0111!!110!0000!1!11!1111!1";
        int x3 = 670;
        int y3 = 725;
        int expected3 = 1942153;
        int result3 = getMinErrors(errorString3, x3, y3);
        System.out.println("User Test Case 3: " + (result3 == expected3 ? "PASS" : "FAIL"));

        // Test Case 1: From original examples
        String errorStringEx1 = "101!1";
        int xEx1 = 2;
        int yEx1 = 3;
        int expectedEx1 = 9;
        int resultEx1 = getMinErrors(errorStringEx1, xEx1, yEx1);
        System.out.println("Test Case 1: " + (resultEx1 == expectedEx1 ? "PASS" : "FAIL"));

        // Test Case 4: Alternating '0's and '1's
        String errorString4 = "0101010101";
        int x4 = 2;
        int y4 = 3;
        int expected4 = calculateExpectedErrors(errorString4, x4, y4);
        int result4 = getMinErrors(errorString4, x4, y4);
        System.out.println("Test Case 4: " + (result4 == expected4 ? "PASS" : "FAIL"));
    }

    // Helper method to calculate expected errors for a known string
    public static int calculateExpectedErrors(String s, int x, int y) {
        final int MOD = 1_000_000_007;
        long zerosSoFar = 0;
        long onesSoFar = 0;
        long totalErrors = 0;
        for (char c : s.toCharArray()) {
            if (c == '0') {
                totalErrors = (totalErrors + onesSoFar * y) % MOD;
                zerosSoFar++;
            } else if (c == '1') {
                totalErrors = (totalErrors + zerosSoFar * x) % MOD;
                onesSoFar++;
            }
        }
        return (int) (totalErrors % MOD);
    }

    public static int getMinErrors(String errorString, int x, int y) {
        final int MOD = 1_000_000_007;
        int n = errorString.length();
        char[] s = errorString.toCharArray();

        // Precompute prefix sums of zeros and ones
        int[] prefixZeros = new int[n];
        int[] prefixOnes = new int[n];
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                prefixZeros[i] = prefixZeros[i - 1];
                prefixOnes[i] = prefixOnes[i - 1];
            }
            if (s[i] == '0') {
                prefixZeros[i]++;
            } else if (s[i] == '1') {
                prefixOnes[i]++;
            }
        }

        // Precompute suffix sums of zeros and ones
        int[] suffixZeros = new int[n];
        int[] suffixOnes = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            if (i < n - 1) {
                suffixZeros[i] = suffixZeros[i + 1];
                suffixOnes[i] = suffixOnes[i + 1];
            }
            if (s[i] == '0') {
                suffixZeros[i]++;
            } else if (s[i] == '1') {
                suffixOnes[i]++;
            }
        }

        // First pass: Decide for each '!' whether to assign '0' or '1'
        for (int i = 0; i < n; i++) {
            if (s[i] == '!') {
                // Compute costIfZero
                long costIfZero = ((long) prefixOnes[i] * y + (long) suffixOnes[i] * x);
                // Compute costIfOne
                long costIfOne = ((long) prefixZeros[i] * x + (long) suffixZeros[i] * y);
                if (costIfZero <= costIfOne) {
                    s[i] = '0';
                } else {
                    s[i] = '1';
                }
            }
        }

        // Second pass: Compute total_errors
        long totalErrors = 0;
        long zerosSoFar = 0;
        long onesSoFar = 0;
        for (int i = 0; i < n; i++) {
            if (s[i] == '0') {
                totalErrors = (totalErrors + onesSoFar * y) % MOD;
                zerosSoFar++;
            } else if (s[i] == '1') {
                totalErrors = (totalErrors + zerosSoFar * x) % MOD;
                onesSoFar++;
            }
        }
        return (int) totalErrors;
    }
}
