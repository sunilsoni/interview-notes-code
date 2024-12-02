package com.interview.notes.code.year.y2024.oct24.test3;

import java.util.ArrayList;
import java.util.List;

public class PalindromeChecker {

    public static boolean isPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return true;
        }

        // Remove non-alphanumeric characters and convert to lowercase
        s = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    public static void main(String[] args) {
        List<TestCase> testCases = new ArrayList<>();
        testCases.add(new TestCase("A man, a plan, a canal: Panama", true));
        testCases.add(new TestCase("race a car", false));
        testCases.add(new TestCase(" ", true));
        testCases.add(new TestCase(".,", true));
        testCases.add(new TestCase("ab_a", true));
        testCases.add(new TestCase("0P", false));

        // Large input test case
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000000; i++) {
            sb.append('a');
        }
        testCases.add(new TestCase(sb.toString(), true));

        int passedTests = 0;
        int totalTests = testCases.size();

        for (int i = 0; i < totalTests; i++) {
            TestCase tc = testCases.get(i);
            long startTime = System.nanoTime();
            boolean result = isPalindrome(tc.input);
            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1000000; // Convert to milliseconds

            boolean passed = (result == tc.expected);
            System.out.printf("Test Case %d: %s (Time: %d ms)\n", i + 1, passed ? "PASS" : "FAIL", duration);

            if (passed) {
                passedTests++;
            }
        }

        System.out.printf("\nPassed %d out of %d tests\n", passedTests, totalTests);
    }

    static class TestCase {
        String input;
        boolean expected;

        TestCase(String input, boolean expected) {
            this.input = input;
            this.expected = expected;
        }
    }
}
