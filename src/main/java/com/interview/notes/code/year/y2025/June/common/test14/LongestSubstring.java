package com.interview.notes.code.year.y2025.June.common.test14;

public class LongestSubstring {
    
    // Method to find longest substring from given string
    public static String findLongestSubstring(String input) {
        // Handle edge cases
        if (input == null || input.isEmpty()) {
            return "";
        }
        
        // If string length is 1, return the string itself
        if (input.length() == 1) {
            return input;
        }
        
        String longestSubstring = ""; // Store the longest substring found
        int maxLength = 0; // Track maximum length found so far
        
        // Outer loop - starting point of substring
        for (int i = 0; i < input.length(); i++) {
            // Inner loop - ending point of substring
            for (int j = i + 1; j <= input.length(); j++) {
                // Get current substring
                String current = input.substring(i, j);
                // Update longest substring if current is longer
                if (current.length() > maxLength) {
                    maxLength = current.length();
                    longestSubstring = current;
                }
            }
        }
        
        return longestSubstring;
    }
    
    // Main method for testing
    public static void main(String[] args) {
        // Test cases
        testCase("hello", "hello", "Test 1: Basic case");
        testCase("a", "a", "Test 2: Single character");
        testCase("", "", "Test 3: Empty string");
        testCase(null, "", "Test 4: Null input");
        testCase("my name is Sravan", "my name is Sravan", "Test 5: Given example");
        testCase("ab cd ef", "ab cd ef", "Test 6: String with spaces");
        
        // Large input test
        StringBuilder largeInput = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            largeInput.append("a");
        }
        testCase(largeInput.toString(), largeInput.toString(), "Test 7: Large input");
    }
    
    // Helper method to run test cases
    private static void testCase(String input, String expected, String testName) {
        String result = findLongestSubstring(input);
        boolean passed = result.equals(expected);
        System.out.println(testName + ": " + (passed ? "PASS" : "FAIL"));
        if (!passed) {
            System.out.println("Expected: " + expected);
            System.out.println("Got: " + result);
        }
    }
}
