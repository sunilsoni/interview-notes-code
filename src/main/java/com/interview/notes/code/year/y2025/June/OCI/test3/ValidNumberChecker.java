package com.interview.notes.code.year.y2025.June.OCI.test3;

import java.util.Arrays;

public class ValidNumberChecker {
    /**
     * Determines if the given string is a valid number with:
     *  - optional leading '+' or '-'
     *  - digits
     *  - at most one decimal point
     * Examples of valid: "2", "0089", "-0.1", "+3.14", "4.", "-.9", ".5"
     * Examples of invalid: "abc", "1a", "--6", "-+3", ".", "+", ""
     */
    public static boolean isValidNumber(String s) {
        // Null check: null cannot represent a number
        if (s == null) {
            return false;
        }
        // We do not allow leading/trailing spaces per spec; reject if trimming changes content
        // If trimming is desired, one could call s = s.trim(), but spec shows no whitespace handling.
        if (s.isEmpty()) {
            // Empty string has no digits
            return false;
        }
        int len = s.length();
        int index = 0;
        // Check for optional sign at the start
        char firstChar = s.charAt(0);
        if (firstChar == '+' || firstChar == '-') {
            // Skip the sign for further checks
            index++;
            // If string is only '+' or '-', there are no digits or dot afterward => invalid
            if (index == len) {
                return false;
            }
        }
        boolean seenDigit = false;  // whether we've seen at least one digit
        boolean seenDot = false;    // whether we've seen a decimal point
        // Iterate over the remaining characters
        while (index < len) {
            char c = s.charAt(index);
            if (c >= '0' && c <= '9') {
                // It's a digit
                seenDigit = true;
            } else if (c == '.') {
                // Decimal point: allow only if we haven't seen one yet
                if (seenDot) {
                    // Second dot => invalid
                    return false;
                }
                seenDot = true;
                // Note: we allow dot even if no digit before or after yet; final seenDigit check ensures at least one digit overall
            } else {
                // Any other character is invalid
                return false;
            }
            index++;
        }
        // At least one digit must appear
        return seenDigit;
    }

    /**
     * Simple main method to test isValidNumber with PASS/FAIL output.
     * Uses Java 8 Streams for concise iteration over test cases.
     */
    public static void main(String[] args) {
        // Given examples
        String[] validExamples = {
            "2", "0089", "-0.1", "+3.14", "4.", "-.9", ".5"
        };
        String[] invalidExamples = {
            "abc", "1a", "--6", "-+3", "", "+", "-", ".", "..", ".-5", "5-", "3.1.4", " 3", "3 ", "3. ", " . ", "6..", "+.", "-.", "+.e", "e9"
        };
        System.out.println("Testing valid examples:");
        // For each valid example, expect true
        Arrays.stream(validExamples).forEach(s -> {
            boolean result = isValidNumber(s);
            String status = result ? "PASS" : "FAIL";
            System.out.println("Input: \"" + s + "\" => expected: true, actual: " + result + " => " + status);
        });
        System.out.println("\nTesting invalid examples:");
        // For each invalid example, expect false
        Arrays.stream(invalidExamples).forEach(s -> {
            boolean result = isValidNumber(s);
            String status = (!result) ? "PASS" : "FAIL";
            System.out.println("Input: \"" + s + "\" => expected: false, actual: " + result + " => " + status);
        });
        System.out.println("\nTesting large input performance:");
        // Generate a large numeric string: e.g., 100_000 digits
        int largeSize = 100_000;
        char[] digits = new char[largeSize];
        Arrays.fill(digits, '9');
        String largeNumeric = new String(digits);
        // This should be valid
        long start = System.currentTimeMillis();
        boolean largeResult = isValidNumber(largeNumeric);
        long duration = System.currentTimeMillis() - start;
        System.out.println("Large numeric string of length " + largeSize + " => result: " + largeResult +
                           " (expected: true), time: " + duration + " ms");
        // Also test a large invalid string: e.g., insert a letter in middle
        StringBuilder sb = new StringBuilder(largeNumeric);
        sb.setCharAt(largeSize / 2, 'a');
        String largeInvalid = sb.toString();
        start = System.currentTimeMillis();
        boolean largeInvalidResult = isValidNumber(largeInvalid);
        duration = System.currentTimeMillis() - start;
        System.out.println("Large string with a letter inserted => result: " + largeInvalidResult +
                           " (expected: false), time: " + duration + " ms");
    }
}
