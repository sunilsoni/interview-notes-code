package com.interview.notes.code.year.y2025.June.amazon.test12;

public class NumberValidator {

    public static boolean isValidNumber(String str) {
        if (str == null || str.trim().isEmpty()) {
            return false;
        }

        str = str.trim();

        boolean hasDecimal = false;
        boolean hasDigit = false;
        boolean hasExponent = false;
        boolean hasDigitBeforeExponent = false;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            // Handle sign
            if ((c == '+' || c == '-')) {
                // Sign is only valid at start or right after 'e'/'E'
                if (i > 0 && str.charAt(i - 1) != 'e' && str.charAt(i - 1) != 'E') {
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
                // Can't have multiple exponents
                if (hasExponent) return false;
                // Must have digit before exponent
                if (!hasDigit) return false;
                hasExponent = true;
                hasDigitBeforeExponent = hasDigit;
                hasDigit = false; // Reset for checking digits after exponent
                continue;
            }

            // Check if character is digit
            if (Character.isDigit(c)) {
                hasDigit = true;
                if (!hasExponent) {
                    hasDigitBeforeExponent = true;
                }
                continue;
            }

            // If we reach here, invalid character found
            return false;
        }

        // Must have at least one digit and if there's an exponent,
        // must have digits both before and after it
        return hasDigit && (!hasExponent || hasDigitBeforeExponent);
    }

    public static void main(String[] args) {
        String[][] testCases = {
                {"2", "true"},
                {"0089.", "true"},
                {"-0.1", "true"},
                {"+3.14", "true"},
                {"4.", "true"},
                {"-.9", "true"},
                {".5", "true"},
                {"1e5", "true"},
                {"1E5", "true"},
                {"-1.7e+3", "true"},
                {"1.7e-3", "true"},
                {"2e10", "true"},
                {"abc", "false"},
                {"1a", "false"},
                {"--6", "false"},
                {"-+3", "false"},
                {"", "false"},
                {null, "false"},
                {"e9", "false"},      // No digit before exponent
                {"1e", "false"},      // No digit after exponent
                {"1.e5", "true"},     // Valid
                {"1.e", "false"},     // No digit after exponent
                {"1ee5", "false"},    // Multiple exponents
                {"1e5.2", "false"},   // Decimal after exponent
                {"1E5E7", "false"},   // Multiple exponents
                {".e5", "false"},     // No digit before exponent
                {"3.14e-9", "true"},  // Valid negative exponent
                {"3e+7", "true"},     // Valid positive exponent
                {"999999999999", "true"},
                {".0000000001", "true"}
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
