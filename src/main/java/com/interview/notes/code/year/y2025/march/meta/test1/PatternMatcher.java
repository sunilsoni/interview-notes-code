package com.interview.notes.code.year.y2025.march.meta.test1;

public class PatternMatcher {

    // Method to verify if pattern matches the string
    public static boolean matchesPattern(String pattern, String str) {
        int i = 0; // pointer for pattern
        int j = 0; // pointer for str

        while (i < pattern.length() && j < str.length()) {
            char ch = pattern.charAt(i);
            
            // If character is a digit, calculate the number to skip
            if (Character.isDigit(ch)) {
                int num = 0;
                while (i < pattern.length() && Character.isDigit(pattern.charAt(i))) {
                    num = num * 10 + (pattern.charAt(i) - '0'); // build the number
                    i++;
                }
                j += num; // skip num characters in string
            } else {
                // Direct character comparison
                if (ch != str.charAt(j)) {
                    return false; // mismatch found
                }
                i++;
                j++;
            }
        }
        // Both pointers should reach the end simultaneously for a successful match
        return i == pattern.length() && j == str.length();
    }

    // Main method for test cases
    public static void main(String[] args) {
        runTests();
    }

    // Method to run predefined test cases and print results
    private static void runTests() {
        test("i18n", "internationalization", true);
        test("i18n", "interpolation", false);
        test("a10z", "aabcdefghijz", true);
        test("a10z", "aabcdefz", false);
        // Large input test case
        test("a1000000z", "a" + "x".repeat(1000000) + "z", true);
    }

    // Utility method for testing
    private static void test(String pattern, String str, boolean expected) {
        boolean result = matchesPattern(pattern, str);
        System.out.println((result == expected ? "PASS" : "FAIL") +
            " | Pattern: " + pattern + " | String: " + (str.length() > 20 ? "[large input]" : str));
    }
}
