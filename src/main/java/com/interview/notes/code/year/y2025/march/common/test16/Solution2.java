package com.interview.notes.code.year.y2025.march.common.test16;

import java.util.HashMap;
import java.util.Map;

public class Solution2 {

    /**
     * Converts a fraction to its decimal representation as a string.
     * Handles both terminating and repeating decimals.
     *
     * @param numerator   The top number in the fraction
     * @param denominator The bottom number in the fraction
     * @return String representation of the decimal value
     */
    public static String vulgarToDecimal(long numerator, long denominator) {
        // Handle division by zero
        if (denominator == 0) {
            return "Undefined";
        }

        // Handle negative numbers
        boolean isNegative = (numerator < 0) ^ (denominator < 0);
        numerator = Math.abs(numerator);
        denominator = Math.abs(denominator);

        // Calculate the integer part
        long integerPart = numerator / denominator;

        // If there's no fractional part, return just the integer
        if (numerator % denominator == 0) {
            return isNegative ? "-" + integerPart : "" + integerPart;
        }

        // Start building the result
        StringBuilder result = new StringBuilder();
        if (isNegative) {
            result.append("-");
        }
        result.append(integerPart).append(".");

        // Calculate the fractional part
        Map<Long, Integer> remainderPositions = new HashMap<>();
        long remainder = numerator % denominator;

        while (remainder != 0 && !remainderPositions.containsKey(remainder)) {
            // Store the current position for this remainder
            remainderPositions.put(remainder, result.length());

            // Multiply by 10 and continue division
            remainder *= 10;
            result.append(remainder / denominator);
            remainder %= denominator;
        }

        // If we found a repeating cycle
        if (remainder != 0) {
            int cycleStart = remainderPositions.get(remainder);
            result.insert(cycleStart, "(");
            result.append(")");
        }

        return result.toString();
    }

    /**
     * Tests the vulgarToDecimal method with various test cases.
     * Returns true if all tests pass, otherwise false.
     */
    public static boolean doTestsPass() {
        // Create a map of test cases: key=input, value=expected output
        Map<String, String> testCases = new HashMap<>();

        // Basic test cases
        testCases.put("1/2", "0.5");
        testCases.put("1/3", "0.(3)");
        testCases.put("2/3", "0.(6)");
        testCases.put("11/21", "0.(523809)");
        testCases.put("11/31", "0.(3548387096774193)");

        // Edge cases
        testCases.put("0/5", "0");
        testCases.put("5/1", "5");
        testCases.put("-1/2", "-0.5");
        testCases.put("1/-2", "-0.5");
        testCases.put("-1/-2", "0.5");

        // Mixed repeating/non-repeating
        testCases.put("1/6", "0.1(6)");
        testCases.put("1/7", "0.(142857)");

        // Large numbers
        testCases.put("12345/98765", "0.12(498716827667007239205893334045258951354639520723)");

        // Run all test cases
        boolean allPassed = true;
        for (Map.Entry<String, String> test : testCases.entrySet()) {
            String[] parts = test.getKey().split("/");
            long numerator = Long.parseLong(parts[0]);
            long denominator = Long.parseLong(parts[1]);

            String expected = test.getValue();
            String actual = vulgarToDecimal(numerator, denominator);

            if (!expected.equals(actual)) {
                System.out.println("Test failed for " + test.getKey() +
                        ": Expected \"" + expected +
                        "\" but got \"" + actual + "\"");
                allPassed = false;
            }
        }

        if (allPassed) {
            System.out.println("All tests passed!");
        } else {
            System.out.println("Some tests failed!");
        }

        return allPassed;
    }

    public static void main(String[] args) {
        doTestsPass();
    }
}