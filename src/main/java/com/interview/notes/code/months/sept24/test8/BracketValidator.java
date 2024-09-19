package com.interview.notes.code.months.sept24.test8;

import java.util.Stack;

public class BracketValidator {

    public static void main(String[] args) {
        String[] testCases = {"()", "(C)", "(J)", "", "(", ")", "(())", "(()", "())", "()()"};
        for (String testCase : testCases) {
            boolean result = isValid(testCase);
            System.out.println("Input: \"" + testCase + "\" Output: " + result);
        }
    }

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.isEmpty() || stack.pop() != '(') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
