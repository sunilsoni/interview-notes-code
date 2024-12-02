package com.interview.notes.code.year.y2024.jan24.test13;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ParenthesesValidator {

    private static final Map<Character, Character> matchingParentheses = new HashMap<>();

    static {
        matchingParentheses.put(')', '(');
        matchingParentheses.put(']', '[');
        matchingParentheses.put('}', '{');
    }

    public static boolean isValid(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        }

        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (matchingParentheses.containsValue(c)) {
                stack.push(c);
            } else if (matchingParentheses.containsKey(c)) {
                if (stack.isEmpty() || stack.peek() != matchingParentheses.get(c)) {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String s1 = "{{{]]]";
        System.out.println(isValid(s1)); // Expected output: false
    }
}
