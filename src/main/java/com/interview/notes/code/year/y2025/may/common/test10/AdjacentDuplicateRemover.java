package com.interview.notes.code.year.y2025.may.common.test10;

import java.util.Stack;

public class AdjacentDuplicateRemover {
    
    // Method to remove adjacent duplicates using Stack and Stream
    public static String removeAdjacentDuplicates(String input) {
        // Handle null or empty input
        if (input == null || input.isEmpty()) {
            return "";
        }

        // Use Stack to track characters
        Stack<Character> stack = new Stack<>();
        
        // Process each character in the input string
        input.chars()
            .mapToObj(ch -> (char) ch)
            .forEach(currentChar -> {
                // If stack is not empty and top character matches current
                if (!stack.isEmpty() && stack.peek() == currentChar) {
                    stack.pop(); // Remove matching character
                } else {
                    stack.push(currentChar); // Add new character
                }
            });
        
        // Convert stack result to string using Stream
        return stack.stream()
            .map(String::valueOf)
            .reduce("", String::concat);
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test cases
        String[][] testCases = {
            {"abbccdd", "a"},
            {"bccb", ""},
            {"xwxx", "xw"},
            {"xwxw", "xwxw"},
            // Edge cases
            {"", ""},
            {"a", "a"},
            {"aaa", "a"},
            // Large input case
            {new String(new char[10000]).replace("\0", "a"), "a"}
        };

        // Process each test case
        for (String[] testCase : testCases) {
            String input = testCase[0];
            String expectedOutput = testCase[1];
            String actualOutput = removeAdjacentDuplicates(input);
            
            // Check if test passed
            boolean passed = actualOutput.equals(expectedOutput);
            
            // Print test results
            System.out.printf("Input: %-20s Expected: %-10s Actual: %-10s Result: %s%n",
                    input.length() > 20 ? input.substring(0, 17) + "..." : input,
                    expectedOutput,
                    actualOutput,
                    passed ? "PASS" : "FAIL");
        }
    }
}
