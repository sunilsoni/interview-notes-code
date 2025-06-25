package com.interview.notes.code.year.y2025.June.amazon.test13;

public class NumberValidator {
    
    public static boolean isValidNumber(String str) {
        if (str == null || str.trim().isEmpty()) {
            return false;
        }
        
        str = str.trim();
        
        boolean hasDecimal = false;
        boolean hasDigit = false;
        boolean hasExponent = false;
        
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            
            // Handle sign
            if ((c == '+' || c == '-')) {
                // Sign is only valid at start or right after 'e'/'E'
                if (i > 0 && str.charAt(i-1) != 'e' && str.charAt(i-1) != 'E') {
                    return false;
                }
                continue;
            }
            
            // Handle decimal point
            if (c == '.') {
                // Can't have decimal after exponent
                if (hasExponent || hasDecimal) return false;
                hasDecimal = true;
                continue;
            }
            
            // Handle exponent
            if (c == 'e' || c == 'E') {
                // Can't have multiple exponents and must have digit before exponent
                if (hasExponent || !hasDigit) return false;
                hasExponent = true;
                hasDigit = false; // Reset for checking digits after exponent
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

    public static void main(String[] args) {
        String[][] testCases = {
            {"1e5", "true"},      // Valid
            {"2.5e3", "true"},    // Valid
            {"e5", "false"},      // Invalid - no digit before e
            {".e5", "false"},     // Invalid - no digit before e
            {"+e5", "false"},     // Invalid - no digit before e
            {"1.7e-3", "true"},   // Valid
            {"1e", "false"},      // Invalid - no digit after e
            {"1ee5", "false"},    // Invalid - multiple e's
            {"1e5.2", "false"},   // Invalid - decimal after e
            {"3.14e-9", "true"},  // Valid
            {"3e+7", "true"}      // Valid
        };

        for (String[] test : testCases) {
            String input = test[0];
            boolean expected = Boolean.parseBoolean(test[1]);
            boolean result = isValidNumber(input);
            
            System.out.printf("Input: %-15s Expected: %-7s Got: %-7s Result: %s%n",
                    input == null ? "null" : "\"" + input + "\"",
                    expected,
                    result,
                    expected == result ? "PASS" : "FAIL");
        }
    }
}
