package com.interview.notes.code.months.nov24.test3;

import java.util.Stack;

public class ParenthesesValidator {

    public static void main(String[] args) {
        // Test cases
        String[] testCases = {
                "[(){}]",  // Valid
                "[(){}]",  // Valid
                "[({})]",  // Valid
                "[(){}",    // Invalid
                "({[()]})", // Valid
                "((()))",   // Valid
                "((]",      // Invalid
                "{[()]}",   // Valid
                "",         // Valid (empty)
                "[{(})]"    // Invalid
        };

        runTests(testCases);
    }

    private static boolean isValidParentheses(String s) {
        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } else if (ch == ')' || ch == '}' || ch == ']') {
                if (stack.isEmpty()) {
                    return false; // No opening match
                }
                char top = stack.pop();
                if (!isMatchingPair(top, ch)) {
                    return false; // Mismatched parentheses
                }
            }
        }
        return stack.isEmpty(); // Valid if stack is empty
    }

    private static boolean isMatchingPair(char opening, char closing) {
        return (opening == '(' && closing == ')') ||
                (opening == '{' && closing == '}') ||
                (opening == '[' && closing == ']');
    }

    private static void runTests(String[] testCases) {
        System.out.println("Running tests...");

        for (String testCase : testCases) {
            boolean result = isValidParentheses(testCase);
            System.out.println("Test case: \"" + testCase + "\" - " + (result ? "PASS" : "FAIL"));
        }
    }
}
