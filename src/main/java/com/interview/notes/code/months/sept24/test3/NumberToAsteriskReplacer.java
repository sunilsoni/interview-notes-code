package com.interview.notes.code.months.sept24.test3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberToAsteriskReplacer {

    public static void main(String[] args) {
        // Test cases
        testCase("abcde1f3gh5ij3k17m5nopqrstuvwxyz", "abcde*f***gh*****ij***k*****************m*****nopqrstuvwxyz", "Test Case 1");
        testCase("123abc456def", "***abc******def", "Test Case 2");
        testCase("NoNumbersHere", "NoNumbersHere", "Test Case 3");
        testCase("1", "*", "Test Case 4");
        testCase("999", "*********", "Test Case 5");
        testCase("a1b22c333d4444e55555", "a*b**c***d****e*****", "Test Case 6");

        // Large input test case
        StringBuilder largeInput = new StringBuilder();
        StringBuilder expectedOutput = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            largeInput.append("a").append(i % 10);
            expectedOutput.append("a").append("*".repeat(i % 10));
        }
        testCase(largeInput.toString(), expectedOutput.toString(), "Large Input Test");
    }

    // Corrected method to replace numbers with asterisks
    public static String replaceNumbersWithAsterisks(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        StringBuilder result = new StringBuilder();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(input);

        int lastEnd = 0;
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();

            // Append characters before the number
            result.append(input.substring(lastEnd, start));

            // Replace number with asterisks
            int numAsterisks = end - start; // Use the length of the matched number
            result.append("*".repeat(numAsterisks));

            lastEnd = end;
        }

        // Append remaining characters after the last number
        result.append(input.substring(lastEnd));

        return result.toString();
    }

    // Test case method with pass/fail status
    private static void testCase(String input, String expected, String testName) {
        long startTime = System.nanoTime();
        String result = replaceNumbersWithAsterisks(input);
        long endTime = System.nanoTime();

        boolean passed = result.equals(expected);

        System.out.println(testName + ": " + (passed ? "Passed" : "Failed"));
        if (!passed) {
            System.out.println("Expected: " + expected);
            System.out.println("Got:      " + result);
        }
        System.out.println("Execution time: " + (endTime - startTime) / 1_000_000 + " ms");
        System.out.println();
    }
}
