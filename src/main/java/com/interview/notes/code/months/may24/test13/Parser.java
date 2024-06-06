package com.interview.notes.code.months.may24.test13;

import java.util.Stack;

class Parser {
    static String isBalanced1(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.isEmpty()) {
                    return "false";
                }
                stack.pop();
            }
        }

        // If the stack is empty, all opening brackets had matching closing brackets
        return stack.isEmpty() ? "true" : "false";
    }

    static String isBalanced(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if (c == ')' || c == '}' || c == ']') {
                if (stack.isEmpty()) {
                    return "false";
                }
                char top = stack.pop();
                if ((c == ')' && top != '(') || (c == '}' && top != '{') || (c == ']' && top != '[')) {
                    return "false";
                }
            }
        }
        return stack.isEmpty() ? "true" : "false";
    }

    public static void main(String[] args) {
        // Sample input strings
        String[] inputs = {"(())", "((()))", "{}"};

        // Test each string
        for (String input : inputs) {
            System.out.println("Input: " + input + " - Balanced: " + isBalanced(input));
        }
    }
}
