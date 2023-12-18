package com.interview.notes.code.months.nov23.test7;

import java.util.Stack;

public class BalancedParentheses1 {

    public static boolean isBalanced(String str) {
        Stack<Character> stack = new Stack<>();

        for (char c : str.toCharArray()) {
            // Push opening parentheses onto the stack
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                // Check for corresponding opening parentheses
                if (stack.isEmpty() || !isMatchingPair(stack.pop(), c)) {
                    return false;
                }
            }
        }

        // String is balanced if stack is empty
        return stack.isEmpty();
    }

    private static boolean isMatchingPair(char opening, char closing) {
        return (opening == '(' && closing == ')') ||
                (opening == '{' && closing == '}') ||
                (opening == '[' && closing == ']');
    }

    // Example execution
    public static void main(String[] args) {
        String input = "{(()}]";
        boolean result = isBalanced(input);
        System.out.println("Is balanced: " + result); // Expected output: false
    }
}
