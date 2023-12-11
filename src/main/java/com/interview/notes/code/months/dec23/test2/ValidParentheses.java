package com.interview.notes.code.months.dec23.test2;

import java.util.Stack;

public class ValidParentheses {

    public boolean isValid(String s) {
        // Stack to hold opening parentheses
        Stack<Character> stack = new Stack<>();

        // Iterating through each character in the string
        for (char c : s.toCharArray()) {
            // If the character is an opening parenthesis, push it onto the stack
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                // If the stack is empty or the top of the stack doesn't match the closing parenthesis, return false
                if (stack.isEmpty() || !isMatchingPair(stack.pop(), c)) {
                    return false;
                }
            }
        }

        // Return true if the stack is empty, false otherwise
        return stack.isEmpty();
    }

    private boolean isMatchingPair(char opening, char closing) {
        // Check if the opening and closing parentheses match
        return (opening == '(' && closing == ')') || (opening == '{' && closing == '}') || (opening == '[' && closing == ']');
    }

    // Example execution
    public static void main(String[] args) {
        ValidParentheses vp = new ValidParentheses();
        String testString = "({[]})";
        System.out.println("Is the string \"" + testString + "\" valid? " + vp.isValid(testString));
    }
}
