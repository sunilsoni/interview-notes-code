package com.interview.notes.code.year.y2025.july.common.test6;

import java.util.HashMap;
import java.util.Map;

public class LongestUniqueSubstring {
    
    // Method to find longest substring with unique characters
    public static String findLongestUniqueSubstring(String input) {
        // Handle edge cases
        if (input == null || input.isEmpty()) {
            return "";
        }

        // Variables to track current and max window
        int start = 0;           // Start of current window
        int maxStart = 0;        // Start of max window found
        int maxLength = 0;       // Length of max window
        
        // Map to store last seen position of each character
        Map<Character, Integer> charMap = new HashMap<>();

        // Iterate through string using sliding window
        for (int end = 0; end < input.length(); end++) {
            char currentChar = input.charAt(end);
            
            // If character is already seen in current window
            if (charMap.containsKey(currentChar)) {
                // Move start pointer to position after last occurrence
                start = Math.max(start, charMap.get(currentChar) + 1);
            }
            
            // Update last seen position of current character
            charMap.put(currentChar, end);
            
            // Update max length if current window is longer
            if (end - start + 1 > maxLength) {
                maxLength = end - start + 1;
                maxStart = start;
            }
        }
        
        // Return the longest substring found
        return input.substring(maxStart, maxStart + maxLength);
    }

    // Main method with test cases
    public static void main(String[] args) {
        // Test case 1: Normal string
        testCase("abcabcbb", "abc");
        
        // Test case 2: All same characters
        testCase("bbbbb", "b");
        
        // Test case 3: No repeating characters
        testCase("abcdef", "abcdef");
        
        // Test case 4: Empty string
        testCase("", "");
        
        // Test case 5: Single character
        testCase("a", "a");
        
        // Test case 6: Large string
        testCase("abcdefghijklmnopqrstuvwxyz".repeat(1000), "abcdefghijklmnopqrstuvwxyz");
        
        // Test case 7: Special characters
        testCase("!@#$%^&*()", "!@#$%^&*()");
    }

    // Helper method to run and validate test cases
    private static void testCase(String input, String expected) {
        String result = findLongestUniqueSubstring(input);
        boolean passed = result.length() == expected.length() && 
                        hasAllUniqueChars(result);
        
        System.out.println("Test Case: " + input.substring(0, Math.min(20, input.length())) + 
                         (input.length() > 20 ? "..." : ""));
        System.out.println("Expected length: " + expected.length());
        System.out.println("Result length: " + result.length());
        System.out.println("Status: " + (passed ? "PASS" : "FAIL"));
        System.out.println("-------------------");
    }

    // Helper method to verify if string has all unique characters
    private static boolean hasAllUniqueChars(String str) {
        return str.chars().distinct().count() == str.length();
    }
}
