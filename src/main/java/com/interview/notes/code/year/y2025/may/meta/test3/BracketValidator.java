package com.interview.notes.code.year.y2025.may.meta.test3;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.Map;

public class BracketValidator {

    /**
     * Returns true if s is a balanced bracket string.
     */
    public static boolean isValid(String s) {
        if (s.length() % 2 != 0) return false;  // odd length can't be balanced

        Map<Character, Character> closingToOpening = Map.of(
                ')', '(',
                ']', '[',
                '}', '{'
        );
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (closingToOpening.containsValue(c)) {
                // it's an opening bracket
                stack.push(c);
            } else {
                // it's a closing bracket
                if (stack.isEmpty() || stack.pop() != closingToOpening.get(c)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    /**
     * Simple main method to run PASS/FAIL tests, including a large input test.
     */
    public static void main(String[] args) {
        // Provided examples
        Map<String, Boolean> tests = new LinkedHashMap<>();
        tests.put("()", true);
        tests.put("()[]{}", true);
        tests.put("(]", false);
        tests.put("(()", false);
        tests.put("", true);            // edge: empty string
        tests.put("{[()]}", true);      // nested
        tests.put("{[(])}", false);     // wrong nesting
        tests.put("(((((())))))", true);// deep nesting

        // Large input test (1 million '(' followed by 1 million ')')
        StringBuilder large = new StringBuilder();
        for (int i = 0; i < 1_000_000; i++) large.append('(');
        for (int i = 0; i < 1_000_000; i++) large.append(')');
        tests.put(large.toString(), true);

        // Run tests
        tests.forEach((input, expected) -> {
            boolean result = isValid(input);
            String label = (result == expected) ? "PASS" : "FAIL";
            System.out.printf("%s | input length=%d | expected=%b, got=%b%n",
                    label, input.length(), expected, result);
        });
    }
}
