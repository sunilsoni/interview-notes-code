package com.interview.notes.code.year.y2026.may.Virtusa.test1;

public class Main {

    // Method to check if parentheses are balanced
    public static boolean isValidParentheses(String s) {

        // Count how many open brackets are not closed yet
        int open = 0;

        // Read each character one by one
        for (char ch : s.toCharArray()) {

            // If current character is opening bracket, increase count
            if (ch == '(') open++;

            // If current character is closing bracket, decrease count
            else if (ch == ')') open--;

            // If closing bracket comes before opening bracket, invalid
            if (open < 0) return false;
        }

        // Valid only when all opening brackets are closed
        return open == 0;
    }

    // Simple test method without JUnit
    static void test(String input, boolean expected) {

        // Call actual method
        boolean actual = isValidParentheses(input);

        // Print PASS if actual result matches expected result
        System.out.println(
                (actual == expected ? "PASS" : "FAIL") +
                " | Input: " + input +
                " | Expected: " + expected +
                " | Actual: " + actual
        );
    }

    public static void main(String[] args) {

        // Given test cases
        test("(a + b) * (c + d)", true);
        test("(a + b))", false);
        test("(a + (b * c) - (d / e))", true);
        test("((a + b)", false);

        // Edge test cases
        test("", true);
        test("abc + xyz", true);
        test(")", false);
        test("(", false);
        test("()()", true);
        test("())(", false);

        // Large input test case
        String large = "(".repeat(100_000) + ")".repeat(100_000);
        test(large, true);
    }
}