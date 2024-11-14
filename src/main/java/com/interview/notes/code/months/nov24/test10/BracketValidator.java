package com.interview.notes.code.months.nov24.test10;

import java.util.ArrayDeque;
import java.util.Deque;

public class BracketValidator {

    /**
     * Determines if the input string of brackets is valid.
     *
     * @param s the input string containing brackets
     * @return true if the string is valid, false otherwise
     */
    public static boolean isValid(String s) {
        // Edge case: empty string is valid
        if (s == null || s.length() == 0) {
            return true;
        }

        // Stack to keep track of opening brackets
        Deque<Character> stack = new ArrayDeque<>();

        // Iterate through each character in the string
        for (char ch : s.toCharArray()) {
            // If it's an opening bracket, push to stack
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            }
            // If it's a closing bracket, check for validity
            else if (ch == ')' || ch == '}' || ch == ']') {
                // If stack is empty, no matching opening bracket
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                // Check if the popped bracket matches the current closing bracket
                if (!isMatchingPair(top, ch)) {
                    return false;
                }
            }
            // Invalid character encountered
            else {
                return false;
            }
        }

        // If stack is empty, all brackets were matched
        return stack.isEmpty();
    }

    /**
     * Helper method to check if two brackets are a matching pair.
     *
     * @param open  the opening bracket
     * @param close the closing bracket
     * @return true if they are a matching pair, false otherwise
     */
    private static boolean isMatchingPair(char open, char close) {
        switch (open) {
            case '(':
                return close == ')';
            case '{':
                return close == '}';
            case '[':
                return close == ']';
            default:
                return false;
        }
    }

    /**
     * Main method to run test cases and output PASS/FAIL results.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        // Define test cases
        String[] testCases = {
            "",             // Expected: true
            "()",           // Expected: true
            "()[]{}",       // Expected: true
            "{[]}",         // Expected: true
            "(]",           // Expected: false
            "([)]",         // Expected: false
            "{[}",          // Expected: false
            "(((((((((())))))))))", // Expected: true
            "([{}])",       // Expected: true
            "([}{])",       // Expected: false
            generateLargeValidInput(100000),    // Expected: true
            generateLargeInvalidInput(100000),  // Expected: false
        };

        // Expected results corresponding to the test cases
        boolean[] expectedResults = {
            true,
            true,
            true,
            true,
            false,
            false,
            false,
            true,
            true,
            false,
            true,
            false
        };

        // Run test cases
        for (int i = 0; i < testCases.length; i++) {
            String testCase = testCases[i];
            boolean expected = expectedResults[i];
            boolean result = isValid(testCase);
            if (result == expected) {
                System.out.println("Test Case " + (i + 1) + ": PASS");
            } else {
                System.out.println("Test Case " + (i + 1) + ": FAIL (Expected " + expected + ", Got " + result + ")");
            }
        }
    }

    /**
     * Generates a large valid bracket string with the specified number of bracket pairs.
     *
     * @param n the number of bracket pairs
     * @return a valid bracket string
     */
    private static String generateLargeValidInput(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append("()[]{}");
        }
        return sb.toString();
    }

    /**
     * Generates a large invalid bracket string with the specified number of bracket pairs.
     *
     * @param n the number of bracket pairs
     * @return an invalid bracket string
     */
    private static String generateLargeInvalidInput(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append("([)]{}");
        }
        return sb.toString();
    }
}
