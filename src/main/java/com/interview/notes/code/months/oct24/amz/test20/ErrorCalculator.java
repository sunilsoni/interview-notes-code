package com.interview.notes.code.months.oct24.amz.test20;

public class ErrorCalculator {
    private static final int MOD = 1000000007;

    public static int getMinErrors(String errorString, int x, int y) {
        if (errorString == null || errorString.isEmpty() || errorString.length() == 1) {
            return 0;
        }

        int unknownCount = (int) errorString.chars().filter(ch -> ch == '!').count();

        if (unknownCount == 0) {
            return calculateErrors(errorString, x, y);
        }

        int minErrors = Integer.MAX_VALUE;
        // Try all possible combinations for unknown positions
        for (int mask = 0; mask < (1 << unknownCount); mask++) {
            StringBuilder current = new StringBuilder(errorString);
            int unknownIndex = 0;
            // Replace each '!' with either '0' or '1'
            for (int i = 0; i < current.length(); i++) {
                if (current.charAt(i) == '!') {
                    char replacement = ((mask >> unknownIndex) & 1) == 1 ? '1' : '0';
                    current.setCharAt(i, replacement);
                    unknownIndex++;
                }
            }
            int currentErrors = calculateErrors(current.toString(), x, y);
            minErrors = Math.min(minErrors, currentErrors);
        }

        return minErrors;
    }

    private static int calculateErrors(String s, int x, int y) {
        int errors = 0;
        int n = s.length();

        // Count occurrences of '1' to the right of each position
        int[] onesAfter = new int[n];
        onesAfter[n - 1] = s.charAt(n - 1) == '1' ? 1 : 0;
        for (int i = n - 2; i >= 0; i--) {
            onesAfter[i] = onesAfter[i + 1] + (s.charAt(i) == '1' ? 1 : 0);
        }

        // Count occurrences of '0' to the right of each position
        int[] zerosAfter = new int[n];
        zerosAfter[n - 1] = s.charAt(n - 1) == '0' ? 1 : 0;
        for (int i = n - 2; i >= 0; i--) {
            zerosAfter[i] = zerosAfter[i + 1] + (s.charAt(i) == '0' ? 1 : 0);
        }

        // Calculate errors
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == '0') {
                // Add errors for all '1's that come after this '0'
                errors = (errors + (x * (onesAfter[i + 1]))) % MOD;
            } else if (s.charAt(i) == '1') {
                // Add errors for all '0's that come after this '1'
                errors = (errors + (y * (zerosAfter[i + 1]))) % MOD;
            }
        }

        return errors;
    }

    public static void main(String[] args) {
        testCase1();
        testCase2();
        testCase3();
        testCase4();
        testCase5();
        testLargeInput();
    }

    private static void testCase1() {
        String input = "101!1";
        int x = 2;
        int y = 3;
        int expected = 9;
        int result = getMinErrors(input, x, y);
        printTestResult("Test Case 1", expected, result);
    }

    private static void testCase2() {
        String input = "!";
        int x = 5;
        int y = 6;
        int expected = 0;
        int result = getMinErrors(input, x, y);
        printTestResult("Test Case 2", expected, result);
    }

    private static void testCase3() {
        String input = "!!";
        int x = 1;
        int y = 1;
        int expected = 1;
        int result = getMinErrors(input, x, y);
        printTestResult("Test Case 3", expected, result);
    }

    private static void testCase4() {
        String input = "1!0!";
        int x = 2;
        int y = 3;
        int expected = 4;
        int result = getMinErrors(input, x, y);
        printTestResult("Test Case 4", expected, result);
    }

    private static void testCase5() {
        String input = "";
        int x = 1;
        int y = 1;
        int expected = 0;
        int result = getMinErrors(input, x, y);
        printTestResult("Test Case 5", expected, result);
    }

    private static void testLargeInput() {
        StringBuilder largeInput = new StringBuilder();
        for (int i = 0; i < 15; i++) {
            largeInput.append("10!");
        }
        int x = 1000000;
        int y = 1000000;
        int result = getMinErrors(largeInput.toString(), x, y);
        System.out.println("Large Input Test (45 characters): Result = " + result);
    }

    private static void printTestResult(String testName, int expected, int result) {
        System.out.println(testName + ": " +
                (expected == result ? "PASS" : "FAIL") +
                " (Expected: " + expected + ", Got: " + result + ")");
    }
}