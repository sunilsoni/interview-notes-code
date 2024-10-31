package com.interview.notes.code.months.oct24.amazon.test18;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        testGetMinErrors();
    }

    public static void testGetMinErrors() {
        // Test case 1: Example from the problem statement
        String errorString1 = "101!1";
        int x1 = 2;
        int y1 = 3;
        int expected1 = 9;
        int result1 = getMinErrors(errorString1, x1, y1);
        System.out.println("Test Case 1: " + (result1 == expected1 ? "PASS" : "FAIL"));

        // Test case 2: All zeros
        String errorString2 = "00000";
        int x2 = 1;
        int y2 = 1;
        int expected2 = 0; // No '01' or '10' subsequences
        int result2 = getMinErrors(errorString2, x2, y2);
        System.out.println("Test Case 2: " + (result2 == expected2 ? "PASS" : "FAIL"));

        // Test case 3: All ones
        String errorString3 = "11111";
        int x3 = 1;
        int y3 = 1;
        int expected3 = 0; // No '01' or '10' subsequences
        int result3 = getMinErrors(errorString3, x3, y3);
        System.out.println("Test Case 3: " + (result3 == expected3 ? "PASS" : "FAIL"));

        // Test case 4: Alternating '0' and '1'
        String errorString4 = "0101010101";
        int x4 = 2;
        int y4 = 3;
        int expected4 = calculateExpectedErrors(errorString4, x4, y4);
        int result4 = getMinErrors(errorString4, x4, y4);
        System.out.println("Test Case 4: " + (result4 == expected4 ? "PASS" : "FAIL"));

        // Test case 5: String with all '!'
        String errorString5 = "!!!!!!!!!!";
        int x5 = 5;
        int y5 = 7;
        int expected5 = calculateExpectedErrorsForAllExclamations(errorString5.length(), x5, y5);
        int result5 = getMinErrors(errorString5, x5, y5);
        System.out.println("Test Case 5: " + (result5 == expected5 ? "PASS" : "FAIL"));

        // Test case 6: Large input with random '0', '1', and '!'
        String errorString6 = generateRandomString(100000, 0.3, 0.3, 0.4);
        int x6 = 10;
        int y6 = 15;
        // Since we don't have an expected value, we'll just ensure it runs without error
        try {
            int result6 = getMinErrors(errorString6, x6, y6);
            System.out.println("Test Case 6: PASS");
        } catch (Exception e) {
            System.out.println("Test Case 6: FAIL");
        }

        // Test case 7: Edge case with maximum length and all '!'
        String errorString7 = generateStringWithCharacter(100000, '!');
        int x7 = 1000000000;
        int y7 = 1000000000;
        try {
            int result7 = getMinErrors(errorString7, x7, y7);
            System.out.println("Test Case 7: PASS");
        } catch (Exception e) {
            System.out.println("Test Case 7: FAIL");
        }
    }

    // Helper method to calculate expected errors for a known string
    public static int calculateExpectedErrors(String s, int x, int y) {
        final int MOD = 1_000_000_007;
        long zeros_so_far = 0;
        long ones_so_far = 0;
        long total_errors = 0;
        for (char c : s.toCharArray()) {
            if (c == '0') {
                total_errors = (total_errors + ones_so_far * y) % MOD;
                zeros_so_far++;
            } else if (c == '1') {
                total_errors = (total_errors + zeros_so_far * x) % MOD;
                ones_so_far++;
            }
        }
        return (int) (total_errors % MOD);
    }

    // Helper method for test case 5 where all characters are '!'
    public static int calculateExpectedErrorsForAllExclamations(int length, int x, int y) {
        final int MOD = 1_000_000_007;
        // Since we can choose all '0's or all '1's to minimize errors
        // Both choices will result in zero errors
        return 0;
    }

    // Helper method to generate a random string of '0', '1', and '!'
    public static String generateRandomString(int length, double zeroProb, double oneProb, double exclProb) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            double p = random.nextDouble();
            if (p < zeroProb) {
                sb.append('0');
            } else if (p < zeroProb + oneProb) {
                sb.append('1');
            } else {
                sb.append('!');
            }
        }
        return sb.toString();
    }

    // Helper method to generate a string with a single character
    public static String generateStringWithCharacter(int length, char c) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(c);
        }
        return sb.toString();
    }

    public static int getMinErrors1(String errorString, int x, int y) {
        final int MOD = 1_000_000_007;
        long zeros_so_far = 0;
        long ones_so_far = 0;
        long total_errors = 0;
        int n = errorString.length();
        for (int i = 0; i < n; i++) {
            char c = errorString.charAt(i);
            if (c == '0') {
                total_errors = (total_errors + ones_so_far * y) % MOD;
                zeros_so_far++;
            } else if (c == '1') {
                total_errors = (total_errors + zeros_so_far * x) % MOD;
                ones_so_far++;
            } else if (c == '!') {
                // Decide whether to replace '!' with '0' or '1'
                long errors_if_zero = (ones_so_far * y) % MOD;
                long errors_if_one = (zeros_so_far * x) % MOD;
                if (errors_if_zero <= errors_if_one) {
                    total_errors = (total_errors + errors_if_zero) % MOD;
                    zeros_so_far++;
                } else {
                    total_errors = (total_errors + errors_if_one) % MOD;
                    ones_so_far++;
                }
            }
        }
        return (int) (total_errors % MOD);
    }

    public static int getMinErrors2(String errorString, int x, int y) {
        final int MOD = 1_000_000_007;
        int n = errorString.length();
        long zerosSoFar = 0;
        long onesSoFar = 0;
        long totalErrors = 0;

        char[] s = errorString.toCharArray();

        if (x <= y) {
            // Assign all '!' to '1's
            for (int i = 0; i < n; i++) {
                char c = s[i];
                if (c == '0') {
                    totalErrors = (totalErrors + onesSoFar * y) % MOD;
                    zerosSoFar++;
                } else {
                    if (c == '!') {
                        c = '1';
                    }
                    totalErrors = (totalErrors + zerosSoFar * x) % MOD;
                    onesSoFar++;
                }
            }
        } else {
            // Assign all '!' to '0's
            for (int i = 0; i < n; i++) {
                char c = s[i];
                if (c == '1') {
                    totalErrors = (totalErrors + zerosSoFar * x) % MOD;
                    onesSoFar++;
                } else {
                    if (c == '!') {
                        c = '0';
                    }
                    totalErrors = (totalErrors + onesSoFar * y) % MOD;
                    zerosSoFar++;
                }
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

        long totalErrors = 0;
        for (int i = 0; i < n; i++) {
            if (s[i] == '!') {
                // Compute errors if '!' is replaced with '0'
                long costIfZero = ((long) prefixOnes[i] * y + (long) suffixOnes[i] * x) % MOD;
                // Compute errors if '!' is replaced with '1'
                long costIfOne = ((long) prefixZeros[i] * x + (long) suffixZeros[i] * y) % MOD;

                if (costIfZero <= costIfOne) {
                    s[i] = '0';
                    totalErrors = (totalErrors + costIfZero) % MOD;
                } else {
                    s[i] = '1';
                    totalErrors = (totalErrors + costIfOne) % MOD;
                }
            } else if (s[i] == '0') {
                // Errors caused by '0' at position i
                long errors = ((long) prefixOnes[i] * y + (long) suffixOnes[i] * x) % MOD;
                totalErrors = (totalErrors + errors) % MOD;
            } else if (s[i] == '1') {
                // Errors caused by '1' at position i
                long errors = ((long) prefixZeros[i] * x + (long) suffixZeros[i] * y) % MOD;
                totalErrors = (totalErrors + errors) % MOD;
            }
        }
        return (int) (totalErrors % MOD);
    }

}
