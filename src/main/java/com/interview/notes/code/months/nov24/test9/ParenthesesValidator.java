package com.interview.notes.code.months.nov24.test9;

import java.util.Stack;

public class ParenthesesValidator {

    // Main method containing test cases and validation
    public static void main(String[] args) {
        // Test cases array - each element is {input string, expected result}
        TestCase[] testCases = {
                new TestCase("()", true),
                new TestCase("()[]{}", true),
                new TestCase("(]", false),
                new TestCase("([)]", false),
                new TestCase("{[]}", true),
                new TestCase("", true),
                new TestCase("((", false),
                new TestCase(")))", false),
                new TestCase("({[)}]", false),
                // Large input test case
                new TestCase("(" + "[]".repeat(10000) + ")", true),
                // Edge cases
                new TestCase(null, false),
                new TestCase("   ", false),
                new TestCase("({})[()]{}[]", true)
        };

        runTests(testCases);
    }

    // Method to validate parentheses
    public static boolean isValid(String s) {
        if (s == null || s.trim().isEmpty() || s.length() % 2 != 0) {
            return s != null && s.trim().isEmpty();
        }

        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;

                char top = stack.pop();
                if ((c == ')' && top != '(') ||
                        (c == '}' && top != '{') ||
                        (c == ']' && top != '[')) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    // Method to run all test cases
    private static void runTests(TestCase[] testCases) {
        int passed = 0;
        int total = testCases.length;

        for (int i = 0; i < total; i++) {
            TestCase test = testCases[i];
            boolean result = isValid(test.input);
            boolean isPassed = result == test.expected;

            System.out.printf("Test Case %d: %s\n", i + 1,
                    isPassed ? "PASSED" : "FAILED");
            System.out.printf("Input: %s\n",
                    test.input == null ? "null" :
                            test.input.length() > 50 ?
                                    test.input.substring(0, 47) + "..." : test.input);
            System.out.printf("Expected: %b, Got: %b\n\n",
                    test.expected, result);

            if (isPassed) passed++;
        }

        System.out.printf("Results: %d/%d tests passed (%.2f%%)\n",
                passed, total, (passed * 100.0 / total));
    }

    // Test case class to hold input and expected output
    static class TestCase {
        String input;
        boolean expected;

        TestCase(String input, boolean expected) {
            this.input = input;
            this.expected = expected;
        }
    }
}