package com.interview.notes.code.months.Sep23.test2;

import java.util.Stack;

public class RemoveBrackets1 {

    public static String RemoveBrackets(String str) {
        Stack<Character> stack = new Stack<>();
        int unmatchedClosing = 0;

        for (char c : str.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if (c == ')' || c == '}' || c == ']') {
                if (stack.isEmpty() || !isMatchingPair(stack.peek(), c)) {
                    unmatchedClosing++;
                } else {
                    stack.pop();
                }
            }
        }

        return Integer.toString(stack.size() + unmatchedClosing);
    }

    public static boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') ||
                (open == '{' && close == '}') ||
                (open == '[' && close == ']');
    }

    public static void main(String[] args) {
        System.out.println("Test 1 passing: " + (RemoveBrackets("{[()]>[{{[(((").equals("6"))); // Example test, should print true
    }
}
