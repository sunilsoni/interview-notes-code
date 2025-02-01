package com.interview.notes.code.year.y2025.jan24.tets16;

public class ReverseDecimalNumber {

    public static String reverseDecimal(String input) {
        // Find the index of the decimal point
        int decimalIndex = input.indexOf('.');

        // Remove the decimal point
        String withoutDecimal = input.replace(".", "");

        // Reverse the entire string
        String reversed = new StringBuilder(withoutDecimal).reverse().toString();

        // Insert the decimal point back into its original position
        String result = reversed.substring(0, decimalIndex) + "." + reversed.substring(decimalIndex);

        return result;
    }

    public static void main(String[] args) {
        // Test cases
        String[] testCases = {
                "123456.789",  // Expected: 987654.321
                "123.456",     // Expected: 321.654
                "1.1",         // Expected: 1.1
                "0.123",       // Expected: 0.321
                "123456789012345678901234567890.98765432109876543210987654321"  // Large input
        };

        for (String testCase : testCases) {
            String result = reverseDecimal(testCase);
            System.out.println("Input: " + testCase + " -> Output: " + result);
        }
    }
}