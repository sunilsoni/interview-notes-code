package com.interview.notes.code.year.y2025.June.amazon.test11;

import java.util.stream.IntStream;

public class ValidNumberChecker {

    /**
     * Determines if the input string is a valid number.
     * Uses Java 8 Streams for each check.
     */
    public static boolean isValidNumber(String s) {
        // If null or empty, it's not a number
        if (s == null || s.isEmpty()) {
            return false;
        }

        // Count how many '+' or '-' appear
        long signCount = s.chars()
                .filter(c -> c == '+' || c == '-')
                .count();
        // More than one sign is invalid
        if (signCount > 1) {
            return false;
        }
        // If there's one sign, it must be at the start
        if (signCount == 1 && !(s.charAt(0) == '+' || s.charAt(0) == '-')) {
            return false;
        }

        // Count how many decimal points
        long dotCount = s.chars()
                .filter(c -> c == '.')
                .count();
        // More than one '.' is invalid
        if (dotCount > 1) {
            return false;
        }

        // Ensure no illegal characters are present
        boolean hasIllegal = s.chars()
                .anyMatch(c -> !(Character.isDigit(c) || c == '+' || c == '-' || c == '.'));
        if (hasIllegal) {
            return false;
        }

        // There must be at least one digit somewhere
        long digitCount = s.chars()
                .filter(Character::isDigit)
                .count();
        return digitCount > 0;
    }

    /**
     * Simple main method to run test cases and report PASS/FAIL.
     */
    public static void main(String[] args) {
        // Test cases from the prompt
        String[] inputs = {
                "2", "0089", "-0.1", "+3.14", "4.", "-.9", ".5",  // valid
                "abc", "1a", "--6", "-+3", ".", "+", "-"          // invalid
        };
        boolean[] expected = {
                true, true, true, true, true, true, true,
                false, false, false, false, false, false, false
        };

        System.out.println("Running predefined tests:");
        for (int i = 0; i < inputs.length; i++) {
            boolean result = isValidNumber(inputs[i]);
            String status = (result == expected[i]) ? "PASS" : "FAIL";
            System.out.printf("%-5s input=\"%s\"  expected=%-5s got=%-5s%n",
                    status, inputs[i], expected[i], result);
        }

        // Large-data stress test: one million '9's
        StringBuilder sb = new StringBuilder(1_000_000);
        IntStream.range(0, 1_000_000).forEach(i -> sb.append('9'));
        String largeNumber = sb.toString();
        boolean largeResult = isValidNumber(largeNumber);
        System.out.printf("%nStress test (1M digits): %s (expected=true)%n",
                largeResult ? "PASS" : "FAIL");
    }
}