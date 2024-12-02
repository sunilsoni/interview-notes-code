package com.interview.notes.code.year.y2024.sept24.test1;

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
        //  testCase("", "", 6);
        testCase("abc", "abc", 7);
        testCase("3[a]2[b]", "aaabb", 8);

        // Large input test case
        StringBuilder largeInput = new StringBuilder("a");
        for (int i = 0; i < 10; i++) {
            largeInput.append("10[a]");
        }
        String expectedLarge = "a" + "a".repeat(10000000);
        testCase(largeInput.toString(), expectedLarge, 9);
    }

    public static String expandPattern(String input) {
        Stack<StringBuilder> stack = new Stack<>();
        stack.push(new StringBuilder());
        int number = 0;

        for (char c : input.toCharArray()) {
            if (Character.isDigit(c)) {
                number = number * 10 + (c - '0');
            } else if (c == '[') {
                stack.push(new StringBuilder());
                stack.push(new StringBuilder().append(number));
                number = 0;
            } else if (c == ']') {
                StringBuilder temp = stack.pop();
                int repeatCount = Integer.parseInt(stack.pop().toString());
                StringBuilder repeated = new StringBuilder();
                for (int i = 0; i < repeatCount; i++) {
                    repeated.append(temp);
                }
                stack.peek().append(repeated);
            } else {
                stack.peek().append(c);
            }
        }

        return stack.pop().toString();
    }

    private static void testCase(String input, String expected, int testNumber) {
        String result = expandPattern(input);
        System.out.println("Test Case " + testNumber + ": " +
                (result.equals(expected) ? "Passed" : "Failed"));
    }
}
