package com.interview.notes.code.year.y2025.may.common.test10;

public class MultiBracketChecker {
    
    // Main method to check if all types of brackets are balanced
    public static boolean isBalanced(String input) {
        // Handle null or empty input cases
        if (input == null || input.isEmpty()) {
            return true;
        }

        // Using StringBuilder as a stack to track opening brackets
        StringBuilder stack = new StringBuilder();
        
        // Process each character in the input string
        for (char current : input.toCharArray()) {
            // If it's an opening bracket, add to stack
            if (isOpenBracket(current)) {
                stack.append(current);
            }
            // If it's a closing bracket
            else if (isCloseBracket(current)) {
                // Check if stack is empty (no matching opening bracket)
                if (stack.length() == 0) {
                    return false;
                }
                
                // Get the last opening bracket from stack
                char lastOpen = stack.charAt(stack.length() - 1);
                
                // Check if brackets match
                if (!isMatchingPair(lastOpen, current)) {
                    return false;
                }
                
                // Remove the matched opening bracket
                stack.setLength(stack.length() - 1);
            }
        }
        
        // Return true if all brackets are matched (stack is empty)
        return stack.length() == 0;
    }

    // Helper method to check if character is an opening bracket
    private static boolean isOpenBracket(char c) {
        return c == '(' || c == '{' || c == '[';
    }

    // Helper method to check if character is a closing bracket
    private static boolean isCloseBracket(char c) {
        return c == ')' || c == '}' || c == ']';
    }

    // Helper method to check if brackets form a matching pair
    private static boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') ||
               (open == '{' && close == '}') ||
               (open == '[' && close == ']');
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test cases array with expected results
        TestCase[] testCases = {
            new TestCase("()", true),
            new TestCase("{[]}", true),
            new TestCase("({[]})", true),
            new TestCase("({[})", false),
            new TestCase("((()))", true),
            new TestCase("{[()]}", true),
            new TestCase("[", false),
            new TestCase("}", false),
            new TestCase("", true),
            new TestCase(null, true),
            new TestCase("({[]}){[]}", true),
            new TestCase("Hello(World)[Test]{Block}", true),
            new TestCase("([)]", false),
            new TestCase("{[}]", false),
            // Large input test
            new TestCase("{[()]}".repeat(1000), true),
            new TestCase("({[]})".repeat(100) + "}", false)
        };

        // Process and display results for each test case
        for (TestCase test : testCases) {
            boolean result = isBalanced(test.input);
            boolean passed = result == test.expected;
            
            System.out.printf("Input: %-30s Expected: %-7b Got: %-7b Test: %s%n",
                    test.input == null ? "null" : 
                    test.input.length() > 25 ? test.input.substring(0, 22) + "..." : 
                    "\"" + test.input + "\"",
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
