package com.interview.notes.code.year.y2025.jan.tets16;

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

        // Handle decimal part
        StringBuilder decimalReversed = new StringBuilder(parts[1]).reverse();

        // Combine both parts with decimal point
        return wholeReversed.toString() + "." + decimalReversed.toString();
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
    }
}
