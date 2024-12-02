package com.interview.notes.code.year.y2024.jan24.test7;

import java.util.Stack;

public class BalancedParentheses {
    public static int longestBalancedSubstring(String input) {
        int maxLength = 0;
        int start = -1;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(') {
                // Push the index of '(' onto the stack
                stack.push(i);
            } else {
                if (!stack.isEmpty()) {
                    // Pop the last '('
                    stack.pop();

                    // Check if stack is empty, update start
                    if (stack.isEmpty()) {
                        start = i;
                    } else {
                        // Update maxLength
                        maxLength = Math.max(maxLength, i - stack.peek());
                    }
                } else {
                    // Reset start if unmatched ')' is found
                    start = i;
                }
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        String input = "((()()()((";
        System.out.println("Longest Balanced Substring Length: " + longestBalancedSubstring(input));
        System.out.println(longestBalancedSubstring("((()))()()(("));//10
    }
}
