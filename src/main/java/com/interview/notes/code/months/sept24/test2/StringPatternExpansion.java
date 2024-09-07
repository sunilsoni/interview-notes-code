package com.interview.notes.code.months.sept24.test2;

import java.util.Stack;

public class StringPatternExpansion {
    public static void main(String[] args) {
        // Test cases
        testCase("ab3[z]a", "abzzza", 1);
        testCase("Ab12[a]", "abaaaaaaaaaaaa", 2);
        testCase("ab2[wy]", "abwywy", 3);
        testCase("abc2[sad3[z]ely", "abcsadzzzesadzzzey", 4);
        testCase("a2[wy3[a]]", "awyaaawyaaa", 5);

        // Edge cases
        testCase("", "", 6);
        testCase("abc", "abc", 7);
        testCase("3[a]2[b]", "aaabb", 8);
        testCase("2[]", "", 9);  // Empty brackets
        testCase("2[3[]]", "", 10);  // Nested empty brackets

        // Large input test case
        StringBuilder largeInput = new StringBuilder("a");
        for (int i = 0; i < 10; i++) {
            largeInput.append("10[a]");
        }
        String expectedLarge = "a" + "a".repeat(10000000);
        testCase(largeInput.toString(), expectedLarge, 11);

        // Invalid input test cases
        testCase("2[a]]]", null, 12);  // Unmatched closing bracket
        testCase("2[a", null, 13);  // Unclosed bracket
        testCase("a]", null, 14);  // Unexpected closing bracket
    }

    public static String expandPattern(String input) {
        try {
            Stack<Object> stack = new Stack<>();
            stack.push(new StringBuilder());
            int number = 0;

            for (char c : input.toCharArray()) {
                if (Character.isDigit(c)) {
                    number = number * 10 + (c - '0');
                } else if (c == '[') {
                    stack.push(number);
                    stack.push(new StringBuilder());
                    number = 0;
                } else if (c == ']') {
                    StringBuilder temp = new StringBuilder();
                    while (!stack.isEmpty() && stack.peek() instanceof StringBuilder) {
                        temp.insert(0, stack.pop());
                    }
                    if (stack.isEmpty() || !(stack.peek() instanceof Integer)) {
                        throw new IllegalArgumentException("Mismatched brackets");
                    }
                    int repeatCount = (Integer) stack.pop();
                    StringBuilder repeated = new StringBuilder();
                    for (int i = 0; i < repeatCount; i++) {
                        repeated.append(temp);
                    }
                    if (stack.isEmpty()) {
                        stack.push(new StringBuilder());
                    }
                    ((StringBuilder) stack.peek()).append(repeated);
                } else {
                    if (stack.isEmpty() || !(stack.peek() instanceof StringBuilder)) {
                        stack.push(new StringBuilder());
                    }
                    ((StringBuilder) stack.peek()).append(c);
                }
            }

            if (stack.size() != 1 || !(stack.peek() instanceof StringBuilder)) {
                throw new IllegalArgumentException("Invalid pattern");
            }

            return stack.pop().toString();
        } catch (Exception e) {
            System.err.println("Error processing input: " + e.getMessage());
            return null;
        }
    }

    private static void testCase(String input, String expected, int testNumber) {
        String result = expandPattern(input);
        boolean passed = (expected == null && result == null) || (expected != null && expected.equals(result));
        System.out.println("Test Case " + testNumber + ": " + (passed ? "Passed" : "Failed"));
        if (!passed) {
            System.out.println("  Input: " + input);
            System.out.println("  Expected: " + expected);
            System.out.println("  Got: " + result);
        }
    }
}
