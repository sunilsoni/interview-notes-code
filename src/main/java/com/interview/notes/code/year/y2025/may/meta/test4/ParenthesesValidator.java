package com.interview.notes.code.year.y2025.may.meta.test4;

public class ParenthesesValidator {
    public static boolean isValid(String s) {
        // Handle edge cases
        if (s == null || s.length() == 0) return true;
        if (s.length() % 2 != 0) return false;

        // Using Stack concept with StringBuilder for better performance
        StringBuilder stack = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.append(c);
            } else {
                if (stack.length() == 0) return false;

                char last = stack.charAt(stack.length() - 1);
                if ((c == ')' && last == '(') ||
                        (c == '}' && last == '{') ||
                        (c == ']' && last == '[')) {
                    stack.setLength(stack.length() - 1);
                } else {
                    return false;
                }
            }
        }

        return stack.length() == 0;
    }

    public static void main(String[] args) {
        // Test cases
        String[] testCases = {
                "()",           // true
                "()[]{}",      // true
                "(]",          // false
                "(()",         // false
                "",            // true
                "((()))",      // true
                "({[]})",      // true
                "({[})",       // false
                "((((((())",   // false
                "([]){}"       // true
        };

        // Test execution
        for (int i = 0; i < testCases.length; i++) {
            boolean result = isValid(testCases[i]);
            System.out.printf("Test Case %d: Input='%s' Output=%b%n",
                    i + 1, testCases[i], result);
        }
    }
}
