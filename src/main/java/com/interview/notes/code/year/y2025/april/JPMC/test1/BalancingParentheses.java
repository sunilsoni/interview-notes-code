package com.interview.notes.code.year.y2025.april.JPMC.test1;

import java.util.Random;

public class BalancingParentheses {

    /**
     * Returns the minimum number of insertions needed to make the parentheses string valid.
     */
    public static int getMin(String s) {
        int open = 0;       // Count of unmatched '('
        int insertions = 0; // Count of inserted '(' for unmatched ')'

        for (char c : s.toCharArray()) {
            if (c == '(') {
                open++;
            } else {
                // c == ')'
                if (open == 0) {
                    // Need to insert '('
                    insertions++;
                    open = 1; // Now we assume we have that '('
                }
                open--; // Match the ')' with one '('
            }
        }

        // If there are unmatched '(' remaining, we need to insert ')' for each
        insertions += open;

        return insertions;
    }

    /**
     * Simple main method to test various cases (PASS/FAIL).
     */
    public static void main(String[] args) {
        // Test with provided samples and some additional edge cases
        test("()))", 2);   // Sample Case 0
        test("()()", 0);   // Sample Case 1
        test("))((", 4);   // Additional Example
        test("(", 1);      // We need one ')' => total insertions = 1
        test(")", 1);      // We need one '(' => total insertions = 1
        test("(()", 1);    // Insert one ')' => total insertions = 1
        test(")))(((", 6); // All mismatched
        test("(((((((", 7); // Only '(' => need 7 ')' for 7 '('
        test(")))))))", 7); // Only ')' => need 7 '('

        // Test with a larger input (to show it handles big cases efficiently)
        // We'll create a random large string of parentheses (length ~100000) and just ensure the code runs.
        String largeInput = generateLargeParenthesesString(100000);
        int resultLarge = getMin(largeInput);
        System.out.println("Large Input Test (length=100000) => Insertions: " + resultLarge);
    }

    /**
     * Helper method to test a single input against an expected output.
     */
    private static void test(String s, int expected) {
        int result = getMin(s);
        String status = (result == expected) ? "PASS" : "FAIL";
        System.out.printf("Input: %s | Output: %d | Expected: %d | %s%n",
                s, result, expected, status);
    }

    /**
     * Generates a large random parentheses string for performance testing.
     */
    private static String generateLargeParenthesesString(int length) {
        Random rand = new Random(0); // Fixed seed for reproducibility
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            // 50-50 chance of '(' or ')'
            sb.append(rand.nextBoolean() ? '(' : ')');
        }
        return sb.toString();
    }
}
