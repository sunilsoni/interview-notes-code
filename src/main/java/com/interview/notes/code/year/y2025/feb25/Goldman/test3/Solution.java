package com.interview.notes.code.year.y2025.feb25.Goldman.test3;

public class Solution {
    
    public static String rle(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }
        
        StringBuilder result = new StringBuilder();
        int count = 1;
        
        for (int i = 1; i <= input.length(); i++) {
            // If we're at the end or current char differs from previous
            if (i == input.length() || input.charAt(i) != input.charAt(i-1)) {
                result.append(input.charAt(i-1));
                if (count > 1) {
                    result.append(count);
                }
                count = 1;
            } else {
                count++;
            }
        }
        
        return result.toString();
    }

    public static void main(String[] args) {
        // Test cases
        testCase("", "");                  // Empty string
        testCase("a", "a");                // Single character
        testCase("aa", "a2");              // Two same characters
        testCase("aaa", "a3");             // Three same characters
        testCase("aabbc", "a2b2c");        // Multiple groups
        testCase("aaabbbaa", "a3b3a2");    // Repeated patterns
        testCase("abcde", "abcde");        // No repeating chars
        testCase("aabbccaabb", "a2b2c2a2b2"); // Longer input
    }
    
    private static void testCase(String input, String expected) {
        String result = rle(input);
        System.out.printf("Input: %-15s Expected: %-15s Result: %-15s %s%n", 
            input, expected, result, expected.equals(result) ? "PASS" : "FAIL");
    }
}
