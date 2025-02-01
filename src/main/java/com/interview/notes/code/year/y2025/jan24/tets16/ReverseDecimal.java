package com.interview.notes.code.year.y2025.jan24.tets16;

public class ReverseDecimal {

    // Method to reverse integer and decimal parts
    public static String reverseNumber(String input) {
        // Split the input by decimal point
        String[] parts = input.split("\\.");

        String integerPart = parts[0];
        String decimalPart = parts.length > 1 ? parts[1] : "";

        // Reverse both integer and decimal parts
        String reversedInteger = new StringBuilder(integerPart).reverse().toString();
        String reversedDecimal = new StringBuilder(decimalPart).reverse().toString();

        // Combine reversed parts and return the result
        return reversedInteger + (reversedDecimal.isEmpty() ? "" : "." + reversedDecimal);
    }

    // Main method to test different cases
    public static void main(String[] args) {
        // Test cases
        String[] testCases = {
                "123456.789", // Regular case
                "123456",     // No decimal part
                "123.007",    // Decimal with leading zeros
                "9876543210.1234567890", // Large number
                "0.12345",    // Small number
                "1000.0001"   // Decimal with small number
        };

        for (String testCase : testCases) {
            String result = reverseNumber(testCase);
            System.out.println("Input: " + testCase + " => Output: " + result);
        }

        // Testing edge cases
        System.out.println("Edge Case Test 1: " + reverseNumber("1000000000.0000000"));
        System.out.println("Edge Case Test 2: " + reverseNumber("1234567890.9876543210"));
    }
}
