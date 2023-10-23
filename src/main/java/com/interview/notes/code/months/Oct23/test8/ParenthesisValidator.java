package com.interview.notes.code.months.Oct23.test8;

import java.util.Stack;

public class ParenthesisValidator {

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
                return false; // unmatched closing parenthesis or unmatched opening parenthesis
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(isValid("[{()}]"));  // true
        System.out.println(isValid("[{()"));    // false
    }
}
