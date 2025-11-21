package com.interview.notes.code.year.y2025.jan.test23;

public class DecimalReverser {

    public static String reverseDecimal(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        // Split number into whole and decimal parts
        String[] parts = input.split("\\.");

        // Handle whole number part
        StringBuilder wholeReversed = new StringBuilder(parts[0]).reverse();

        // If no decimal part, return just the reversed whole number
        if (parts.length == 1) {
            return wholeReversed.toString();
        }

        // Handle decimal part - reverse from right to left
        String decimalPart = parts[1];
        StringBuilder decimalReversed = new StringBuilder();

        // Process decimal part from left to right
        for (int i = decimalPart.length() - 1; i >= 0; i--) {
            decimalReversed.append(decimalPart.charAt(i));
        }

        // Combine both parts with decimal point
        return wholeReversed + "." + decimalReversed;
    }

    public static void runTest(String input, String expected) {
        String result = reverseDecimal(input);
        System.out.println("Test Case: " + input);
        System.out.println("Expected : " + expected);
        System.out.println("Result   : " + result);
        System.out.println("Status   : " + (result.equals(expected) ? "PASS" : "FAIL"));
        System.out.println("--------------------");
    }

    public static void main(String[] args) {
        // Basic test cases
        runTest("123456.789", "987654.321");

        // Edge cases
        runTest("0.0", "0.0");
        runTest("1.0", "1.0");
        runTest("100", "001");

        // Large numbers
        runTest("123456789.987654321", "987654321.123456789");

        // Special cases
        runTest("0.001", "0.100");
        runTest("1000.0001", "0001.0001");

        // Additional test cases
        runTest("12345.6789", "98765.4321");
        runTest("0.1234", "0.4321");
        runTest("1234.0", "4321.0");
    }
}
