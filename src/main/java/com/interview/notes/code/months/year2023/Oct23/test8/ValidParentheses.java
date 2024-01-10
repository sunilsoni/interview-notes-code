package com.interview.notes.code.months.year2023.Oct23.test8;

import java.util.Stack;

public class ValidParentheses {
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == ')' && !stack.isEmpty() && stack.peek() == '(') {
                stack.pop();
            } else if (c == ']' && !stack.isEmpty() && stack.peek() == '[') {
                stack.pop();
            } else if (c == '}' && !stack.isEmpty() && stack.peek() == '{') {
                stack.pop();
            } else {
                return false; // Invalid character or unbalanced brackets
            }
        }

        return stack.isEmpty(); // If stack is empty, all brackets were matched.
    }

    public static void main(String[] args) {
        String s1 = "[{()}]";
        String s2 = "[{()";

        System.out.println("s1 is valid: " + isValid(s1)); // Output: true
        System.out.println("s2 is valid: " + isValid(s2)); // Output: false
    }
}
