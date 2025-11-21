package com.interview.notes.code.year.y2025.June.common.test7;

import java.util.stream.IntStream;

public class LongestSubstring {

    // Main method to solve the problem using Java 8 Stream API
    public static int findLongestSubstring(String s) {
        // Handle edge cases
        if (s == null || s.isEmpty()) return 0;

        // Convert string to character stream
        return IntStream.range(0, s.length())
                // For each starting position, create a substring stream
                .mapToObj(start ->
                        IntStream.rangeClosed(start, s.length())
                                // Convert each range to actual substring
                                .mapToObj(end -> s.substring(start, end))
                                // Filter only valid substrings (no repeating chars)
                                .filter(sub -> sub.chars().distinct().count() == sub.length())
                                // Get length of each valid substring
                                .mapToInt(String::length)
                                // Find max length
                                .max()
                                .orElse(0)
                )
                // Find maximum among all possible starting positions
                .max(Integer::compareTo)
                .orElse(0);
    }

    // Main method with test cases
    public static void main(String[] args) {
        // Test case class to organize inputs and expected outputs
        class TestCase {
            final String input;
            final int expected;

            TestCase(String input, int expected) {
                this.input = input;
                this.expected = expected;
            }
        }

        // Define test cases
        TestCase[] testCases = {
                new TestCase("abcabcbb", 3),
                new TestCase("bbbbb", 1),
                new TestCase("pwwkew", 3),
                new TestCase("", 0),
                new TestCase(null, 0),
                new TestCase("aab", 2),
                new TestCase("dvdf", 3),
                // Large input test case
                new TestCase("abcdefghijklmnopqrstuvwxyz".repeat(100), 26)
        };

        // Run all test cases
        for (TestCase test : testCases) {
            int result = findLongestSubstring(test.input);
            boolean passed = result == test.expected;

            System.out.printf("Input: %s\nExpected: %d\nGot: %d\nResult: %s\n\n",
                    test.input == null ? "null" :
                            test.input.length() > 50 ? test.input.substring(0, 47) + "..." : test.input,
                    test.expected,
                    result,
                    passed ? "PASS" : "FAIL");
        }
    }
}
