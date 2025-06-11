package com.interview.notes.code.year.y2025.may.common.test10;

public class ParenthesisChecker {
    
    // Main method to check if parentheses are balanced
    public static boolean isBalanced(String input) {
        // Handle null or empty input
        if (input == null || input.isEmpty()) {
            return true;
        }

        // Using StringBuilder for efficient string operations
        StringBuilder stack = new StringBuilder();
        
        // Process each character in the input string
        for (char c : input.toCharArray()) {
            // If opening bracket, add to stack
            if (c == '(') {
                stack.append(c);
            }
            // If closing bracket, check if stack is empty or matches
            else if (c == ')') {
                if (stack.length() == 0) {
                    return false;  // No matching opening bracket
                }
                stack.setLength(stack.length() - 1); // Remove last opening bracket
            }
        }
        
        // Return true if stack is empty (all brackets matched)
        return stack.length() == 0;
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test cases array with expected results
        TestCase[] testCases = {
            new TestCase("()", true),
            new TestCase("((()))", true),
            new TestCase("(())", true),
            new TestCase("(()", false),
            new TestCase("))", false),
            new TestCase("", true),
            new TestCase(null, true),
            new TestCase("(((((((((()))))))))", true),
            new TestCase("(hello(world))", true),
            new TestCase(")(", false)
        };

        // Process each test case
        for (TestCase test : testCases) {
            boolean result = isBalanced(test.input);
            boolean passed = result == test.expected;
            
            System.out.printf("Input: %-20s Expected: %-7b Got: %-7b Test: %s%n",
                    test.input == null ? "null" : "\"" + test.input + "\"",
                    test.expected,
                    result,
                    passed ? "PASS" : "FAIL");
        }
    }

    // Helper class to store test cases
    static class TestCase {
        String input;
        boolean expected;

        TestCase(String input, boolean expected) {
            this.input = input;
            this.expected = expected;
        }
    }
}
