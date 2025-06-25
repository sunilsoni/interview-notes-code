package com.interview.notes.code.year.y2025.June.amazon.test11;

public class NumberValidator {
    
    // Main function to validate if a string represents a valid number
    public static boolean isValidNumber(String str) {
        // Handle null or empty input
        if (str == null || str.trim().isEmpty()) {
            return false;
        }
        
        // Remove leading/trailing whitespace
        str = str.trim();
        
        // Track if we've seen decimal point and sign
        boolean hasDecimal = false;
        boolean hasDigit = false;
        
        // Iterate through each character
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            
            // Handle sign (+ or -) - only valid at start
            if ((c == '+' || c == '-') && i == 0) {
                continue;
            }
            
            // Handle decimal point - can only appear once
            if (c == '.') {
                if (hasDecimal) return false; // Second decimal point found
                hasDecimal = true;
                continue;
            }
            
            // Check if character is digit
            if (Character.isDigit(c)) {
                hasDigit = true;
                continue;
            }
            
            // If we reach here, invalid character found
            return false;
        }
        
        // Must have at least one digit
        return hasDigit;
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test cases array - each entry has test string and expected result
        String[][] testCases = {
            {"2", "true"},
            {"0089.", "true"},
            {"-0.1", "true"},
            {"+3.14", "true"},
            {"4.", "true"},
            {"-.9", "true"},
            {".5", "true"},
            {"abc", "false"},
            {"1a", "false"},
            {"--6", "false"},
            {"-+3", "false"},
            {"", "false"},
            {null, "false"},
            {"999999999999", "true"},  // Large number test
            {".0000000001", "true"}    // Small decimal test
        };

        // Process each test case
        for (String[] test : testCases) {
            String input = test[0];
            boolean expected = Boolean.parseBoolean(test[1]);
            boolean result = isValidNumber(input);
            
            // Print test results
            System.out.printf("Input: %-15s Expected: %-7s Got: %-7s Result: %s%n",
                    input == null ? "null" : "\"" + input + "\"",
                    expected,
                    result,
                    expected == result ? "PASS" : "FAIL");
        }
    }
}
