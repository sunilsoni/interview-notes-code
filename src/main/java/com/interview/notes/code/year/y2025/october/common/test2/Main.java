package com.interview.notes.code.year.y2025.october.common.test2;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

// Simple TestCase class to hold test inputs and expected outputs
class TestCase {
    int length;   // expected length of longest substring
    String text;  // input text for test
}

public class Main {

    public static void main(String[] args) {

        // Step 1: Create test cases
        TestCase[] testCases = new TestCase[3];

        // Test case 1: "pwwkew" → longest substring = "wke" → length = 3
        testCases[0] = new TestCase();
        testCases[0].length = 3;
        testCases[0].text = "pwwkew";

        // Test case 2: "abcabcbb" → "abc" → length = 3
        testCases[1] = new TestCase();
        testCases[1].length = 3;
        testCases[1].text = "abcabcbb";

        // Test case 3: "bbbbb" → "b" → length = 1
        testCases[2] = new TestCase();
        testCases[2].length = 1;
        testCases[2].text = "bbbbb";

        // Step 2: Run each test case
        for (TestCase testCase : testCases) {
            int expected = testCase.length;  // expected value
            String text = testCase.text;     // input text

            // Step 3: Call the function under test
            int actual = CalculateLengthOfLongestSubstring(text);

            // Step 4: Print result as PASS/FAIL
            if (actual != expected) {
                System.out.printf("❌ ERROR: Expected {%d} but got {%d} for input \"%s\"\n",
                        expected, actual, text);
            } else {
                System.out.printf("✅ PASS: Input \"%s\" → Length = %d\n", text, actual);
            }
        }

        // Step 5: Optional large test case
        String largeInput = generateLargeString(10000);
        long start = System.currentTimeMillis();
        int result = CalculateLengthOfLongestSubstring(largeInput);
        long end = System.currentTimeMillis();
        System.out.println("\nLarge Data Test: Completed in " + (end - start) + " ms. Result length = " + result);
    }

    // Helper method to generate large random string for performance testing
    private static String generateLargeString(int length) {
        StringBuilder sb = new StringBuilder();
        Random r = new Random();
        for (int i = 0; i < length; i++) {
            sb.append((char) ('a' + r.nextInt(26)));  // add random lowercase letter
        }
        return sb.toString();
    }

    // Core logic: find length of longest substring without repeating characters
    private static int CalculateLengthOfLongestSubstring(String input) {
        // Handle null or empty input
        if (input == null || input.isEmpty()) return 0;

        // Set to store unique characters in the current window
        Set<Character> seen = new HashSet<>();

        int maxLength = 0;  // stores length of longest substring found
        int start = 0;      // left boundary of sliding window

        // Move right pointer (end) through the string
        for (int end = 0; end < input.length(); end++) {
            char current = input.charAt(end);

            // If character already exists, shrink window from left until it's unique
            while (seen.contains(current)) {
                seen.remove(input.charAt(start));
                start++; // move left pointer forward
            }

            // Add current character to the set
            seen.add(current);

            // Calculate and store max window size
            maxLength = Math.max(maxLength, end - start + 1);
        }

        return maxLength;
    }
}