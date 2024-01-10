package com.interview.notes.code.months.year2023.Oct23.test8;

import java.util.Stack;

public class BracketValidator {

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '[' || c == '{' || c == '(') {
                stack.push(c);
            } else if (c == ']' && !stack.isEmpty() && stack.peek() == '[') {
                stack.pop();
            } else if (c == '}' && !stack.isEmpty() && stack.peek() == '{') {
                stack.pop();
            } else if (c == ')' && !stack.isEmpty() && stack.peek() == '(') {
                stack.pop();
            } else {
                // This case covers for scenarios where the string has a closing bracket
                // but either the stack is already empty or the top of the stack is not the matching opening bracket
                return false;
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(isValid("[{()}]")); // true
        System.out.println(isValid("[{()"));    // false
    }
}
