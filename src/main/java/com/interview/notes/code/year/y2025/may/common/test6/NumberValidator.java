package com.interview.notes.code.year.y2025.may.common.test6;

public class NumberValidator {

    // Main method to validate if a string represents a valid number
    public static boolean isValidNumber(String str) {
        // Check if input is null or empty
        if (str == null || str.trim().isEmpty()) {
            return false;
        }

        // Remove leading/trailing whitespace
        str = str.trim();

        // Track important flags
        boolean hasDigit = false;
        boolean hasDecimal = false;
        boolean hasSign = false;

        // Iterate through each character
        for (int i = 0; i < str.length(); i++) {
            char currentChar = str.charAt(i);

            // Handle negative sign
            if (currentChar == '-') {
                // Sign can only appear at start
                if (i != 0 || hasSign) {
                    return false;
                }
                hasSign = true;
                continue;
            }

            // Handle decimal point
            if (currentChar == '.') {
                // Cannot have multiple decimals
                if (hasDecimal) {
                    return false;
                }
                hasDecimal = true;
                continue;
            }

            // Check if character is digit
            if (Character.isDigit(currentChar)) {
                hasDigit = true;
                continue;
            }

            // If character is not digit, decimal, or valid sign, return false
            return false;
        }

        // Must have at least one digit
        return hasDigit;
    }

    // Test method with various test cases
    public static void main(String[] args) {
        // Test cases array with expected results
        String[][] testCases = {
                {"123", "true"},
                {"-123", "true"},
                {"1.23", "true"},
                {"-1.23", "true"},
                {"0", "true"},
                {"abc", "false"},
                {"12a3", "false"},
                {"", "false"},
                {null, "false"},
                {"--123", "false"},
                {"1.2.3", "false"},
                {".123", "true"},
                {"123.", "true"},
                {"-0.0", "true"},
                {"999999999999999", "true"}
        };

        // Process each test case
        for (String[] testCase : testCases) {
            String input = testCase[0];
            boolean expectedResult = Boolean.parseBoolean(testCase[1]);
            boolean actualResult = isValidNumber(input);

            // Print test results
            System.out.printf("Input: %-15s Expected: %-7s Got: %-7s Result: %s%n",
                    input == null ? "null" : "\"" + input + "\"",
                    expectedResult,
                    actualResult,
                    expectedResult == actualResult ? "PASS" : "FAIL");
        }
    }
}
