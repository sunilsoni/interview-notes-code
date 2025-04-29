package com.interview.notes.code.year.y2025.april.goldman_sachs.test5;

public class Solution {
    
    /**
     * Finds first non-repeating character using brute force approach
     * Time Complexity: O(n²) where n is length of input string
     * Space Complexity: O(1) as we only use constant extra space
     */
    public static char findFirst(String input) {
        // Handle edge case
        if (input == null || input.isEmpty()) {
            return '0';
        }
        
        // Check each character
        for (int i = 0; i < input.length(); i++) {
            boolean isRepeating = false;
            
            // Compare with all other characters
            for (int j = 0; j < input.length(); j++) {
                if (i != j && input.charAt(i) == input.charAt(j)) {
                    isRepeating = true;
                    break;
                }
            }
            
            // If character doesn't repeat, return it
            if (!isRepeating) {
                return input.charAt(i);
            }
        }
        
        // No non-repeating character found
        return '0';
    }

    /**
     * Test method with various test cases including edge cases
     */
    public static void runTests() {
        // Test cases array - each element is {input, expected output}
        String[][] testCases = {
            {"apple", "a"},      // Basic case
            {"racecars", "e"},   // Multiple characters
            {"ababdc", "d"},     // Character in middle
            {"aabb", "0"},       // All repeating
            {"", "0"},           // Empty string
            {"a", "a"},          // Single character
            {"aaaaaa", "0"},     // All same characters
            {"abcdefg", "a"}     // All unique
        };

        int passed = 0;
        int total = testCases.length;

        // Run each test case
        for (String[] test : testCases) {
            String input = test[0];
            char expected = test[1].charAt(0);
            char result = findFirst(input);
            
            if (result == expected) {
                System.out.println("PASS: Input: \"" + input + "\" → Got: '" + result + "'");
                passed++;
            } else {
                System.out.println("FAIL: Input: \"" + input + "\" → Expected: '" + expected + "', Got: '" + result + "'");
            }
        }

        // Print summary
        System.out.println("\nTotal: " + total + ", Passed: " + passed + ", Failed: " + (total - passed));
    }

    public static void main(String[] args) {
        runTests();
    }
}
