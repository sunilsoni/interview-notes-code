package com.interview.notes.code.months.oct24.amazon.test18;

public class Solution {
    private static final int MOD = 1000000007;

    public static int getMinErrors(String errorString, int x, int y) {
        // If string length is less than 2, no errors possible
        if (errorString.length() < 2) {
            return 0;
        }

        // If no exclamation marks, just count the errors in the given string
        if (!errorString.contains("!")) {
            return countErrors(errorString, x, y);
        }

        // Find first exclamation mark
        int exclamationIndex = errorString.indexOf('!');

        // Try both possibilities (0 and 1) for the exclamation mark
        String str0 = errorString.substring(0, exclamationIndex) + "0" +
                errorString.substring(exclamationIndex + 1);
        String str1 = errorString.substring(0, exclamationIndex) + "1" +
                errorString.substring(exclamationIndex + 1);

        // Recursively solve for both possibilities
        int errors0 = getMinErrors(str0, x, y);
        int errors1 = getMinErrors(str1, x, y);

        // Return minimum of both possibilities
        return Math.min(errors0, errors1);
    }

    private static int countErrors(String s, int x, int y) {
        long totalErrors = 0;
        int n = s.length();

        // Count "01" subsequences
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == '0' && s.charAt(j) == '1') {
                    totalErrors = (totalErrors + x) % MOD;
                }
                // Count "10" subsequences
                else if (s.charAt(i) == '1' && s.charAt(j) == '0') {
                    totalErrors = (totalErrors + y) % MOD;
                }
            }
        }

        return (int) totalErrors;
    }

    public static void main(String[] args) {
        // Test Case 1: Given example
        runTestCase("101!1", 2, 3, 9);

        // Test Case 2: String with no exclamation mark
        runTestCase("1010", 2, 3, 10);

        // Test Case 3: String with multiple exclamation marks
        runTestCase("1!0!", 2, 2, 4);

        // Test Case 4: Single character string
        runTestCase("!", 5, 5, 0);

        // Test Case 5: All exclamation marks
        runTestCase("!!!", 1, 1, 1);

        // Test Case 6: Long string with multiple exclamation marks
        runTestCase("10!1!01!", 3, 4, 21);

        // Test Case 7: String with alternating pattern
        runTestCase("10101", 1, 1, 6);

        // Test Case 8: String with all zeros and one exclamation
        runTestCase("000!000", 5, 5, 0);

        // Test Case 9: String with all ones and one exclamation
        runTestCase("111!111", 5, 5, 0);

        // Test Case 10: Complex case with multiple exclamation marks
        runTestCase("1!0!1!0", 2, 3, 8);
    }

    private static void runTestCase(String errorString, int x, int y, int expectedResult) {
        int result = getMinErrors(errorString, x, y);
        System.out.println("Test Case:");
        System.out.println("Input String: " + errorString);
        System.out.println("x = " + x + ", y = " + y);
        System.out.println("Expected Result: " + expectedResult);
        System.out.println("Actual Result: " + result);
        System.out.println("Test " + (result == expectedResult ? "PASSED" : "FAILED"));
        System.out.println("--------------------");
    }
}