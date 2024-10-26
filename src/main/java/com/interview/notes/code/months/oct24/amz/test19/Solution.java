package com.interview.notes.code.months.oct24.amz.test19;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static int getMinErrors(String errorString, int x, int y) {
        final int MOD = 1000000007;
        long totalErrors = 0;
        char prev = ' ';

        for (int i = 0; i < errorString.length(); i++) {
            char current = errorString.charAt(i);
            if (current == '!') {
                if (prev == '0') {
                    // Assign '0' to avoid "01"
                    current = '0';
                } else if (prev == '1') {
                    // Assign '1' to avoid "10"
                    current = '1';
                } else {
                    // If no previous character, assign '0' (arbitrary)
                    current = '0';
                }
                // Update errors based on the assigned character
                if (prev == '0' && current == '1') {
                    totalErrors = (totalErrors + x) % MOD;
                } else if (prev == '1' && current == '0') {
                    totalErrors = (totalErrors + y) % MOD;
                }
                // Update prev
                prev = current;
            } else {
                // Current is '0' or '1'
                if (prev == '0' && current == '1') {
                    totalErrors = (totalErrors + x) % MOD;
                } else if (prev == '1' && current == '0') {
                    totalErrors = (totalErrors + y) % MOD;
                }
                // Update prev
                prev = current;
            }
        }

        return (int) (totalErrors % MOD);
    }

    public static void main(String[] args) {
        // Define test cases
        List<TestCase> testCases = new ArrayList<>();

        // Example Test Case
        testCases.add(new TestCase("101!1", 2, 3, 9));

        // Additional Test Cases
        testCases.add(new TestCase("!01!", 1, 1, 2));
        testCases.add(new TestCase("!!!", 1, 2, 3));
        testCases.add(new TestCase("0000", 5, 5, 0));
        testCases.add(new TestCase("1111", 5, 5, 0));
        testCases.add(new TestCase("0!1!0!1!", 3, 2, 12));
        testCases.add(new TestCase("", 1, 1, 0));
        testCases.add(new TestCase("!0!1!", 10, 10, 20));
        testCases.add(new TestCase("0101010101!", 100000, 100000, 100000));
        // Large Test Case
        StringBuilder largeString = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            largeString.append('!');
        }
        testCases.add(new TestCase(largeString.toString(), 1, 1, 100000));

        // Run test cases
        int passed = 0;
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            int result = getMinErrors(tc.errorString, tc.x, tc.y);
            if (result == tc.expected) {
                System.out.println("Test Case " + (i + 1) + ": PASS");
                passed++;
            } else {
                System.out.println("Test Case " + (i + 1) + ": FAIL (Expected: " + tc.expected + ", Got: " + result + ")");
            }
        }
        System.out.println("Passed " + passed + " out of " + testCases.size() + " test cases.");
    }

    static class TestCase {
        String errorString;
        int x;
        int y;
        int expected;

        TestCase(String errorString, int x, int y, int expected) {
            this.errorString = errorString;
            this.x = x;
            this.y = y;
            this.expected = expected;
        }
    }
}
