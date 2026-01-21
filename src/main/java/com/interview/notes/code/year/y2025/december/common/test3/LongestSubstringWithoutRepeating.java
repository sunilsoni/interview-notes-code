package com.interview.notes.code.year.y2025.december.common.test3;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeating {

    /**
     * Finds the length of longest substring without repeating characters
     * Uses sliding window technique for optimal performance
     */
    public static int findLongestSubstring(String inputString) {

        // Handle edge case: if string is null or empty, return 0
        if (inputString == null || inputString.isEmpty()) {
            return 0;
        }

        // HashSet to store characters in current window
        // We use Set because it doesn't allow duplicates and has O(1) lookup
        Set<Character> characterSet = new HashSet<>();

        // Left pointer of our sliding window
        // This marks the start of current substring
        int leftPointer = 0;

        // Variable to track the maximum length found so far
        // Initialize to 0 as we haven't found any substring yet
        int maxLength = 0;

        // Right pointer iterates through each character in string
        // This marks the end of current substring
        for (int rightPointer = 0; rightPointer < inputString.length(); rightPointer++) {

            // Get the current character at right pointer position
            // This is the character we want to add to our window
            char currentChar = inputString.charAt(rightPointer);

            // If character already exists in our window (duplicate found)
            // We need to shrink window from left until duplicate is removed
            while (characterSet.contains(currentChar)) {

                // Get the character at left pointer
                // This is the character we will remove from window
                char leftChar = inputString.charAt(leftPointer);

                // Remove the left character from our set
                // This shrinks our window from the left side
                characterSet.remove(leftChar);

                // Move left pointer forward
                // This reduces our window size by one from left
                leftPointer++;
            }

            // Add current character to our set
            // This expands our window to include new character
            characterSet.add(currentChar);

            // Calculate current window size
            // Window size = right pointer - left pointer + 1
            int currentWindowSize = rightPointer - leftPointer + 1;

            // Update maxLength if current window is larger
            // We always want to keep track of the longest substring found
            maxLength = Math.max(maxLength, currentWindowSize);
        }

        // Return the maximum length found
        return maxLength;
    }

    /**
     * Helper method to run a single test case
     * Compares actual result with expected result
     */
    public static void runTestCase(String testName, String input, int expected) {

        // Get the actual result from our method
        int actual = findLongestSubstring(input);

        // Check if actual matches expected
        boolean passed = (actual == expected);

        // Print test result with details
        String status = passed ? "PASS" : "FAIL";

        // Show input string (truncate if too long for display)
        String displayInput = input;
        if (input != null && input.length() > 50) {
            displayInput = input.substring(0, 50) + "...(length:" + input.length() + ")";
        }

        // Print formatted result
        System.out.println("[" + status + "] " + testName);
        System.out.println("       Input: \"" + displayInput + "\"");
        System.out.println("       Expected: " + expected + ", Actual: " + actual);
        System.out.println();
    }

    /**
     * Generates a large test string with pattern
     * Used for testing performance with big data
     */
    public static String generateLargeString(int length, String pattern) {

        // StringBuilder is efficient for string concatenation
        StringBuilder builder = new StringBuilder();

        // Keep appending pattern until we reach desired length
        while (builder.length() < length) {
            builder.append(pattern);
        }

        // Return substring of exact length needed
        return builder.substring(0, length);
    }

    /**
     * Main method to run all test cases
     * Tests various scenarios including edge cases and large data
     */
    public static void main(String[] args) {

        System.out.println("===========================================");
        System.out.println("LONGEST SUBSTRING WITHOUT REPEATING CHARS");
        System.out.println("===========================================");
        System.out.println();

        // ========== BASIC TEST CASES ==========
        System.out.println("--- BASIC TEST CASES ---");
        System.out.println();

        // Test Case 1: Standard example with repeating characters
        // "abcabcbb" -> "abc" is longest (length 3)
        runTestCase("Test 1: Standard case", "abcabcbb", 3);

        // Test Case 2: All same characters
        // "bbbbb" -> "b" is longest (length 1)
        runTestCase("Test 2: All same chars", "bbbbb", 1);

        // Test Case 3: Pattern with repeating substring
        // "pwwkew" -> "wke" is longest (length 3)
        runTestCase("Test 3: Pattern case", "pwwkew", 3);

        // Test Case 4: All unique characters
        // "abcdef" -> whole string is answer (length 6)
        runTestCase("Test 4: All unique", "abcdef", 6);

        // Test Case 5: Single character
        // "a" -> "a" is the answer (length 1)
        runTestCase("Test 5: Single char", "a", 1);

        // ========== EDGE CASES ==========
        System.out.println("--- EDGE CASES ---");
        System.out.println();

        // Test Case 6: Empty string
        // "" -> no substring possible (length 0)
        runTestCase("Test 6: Empty string", "", 0);

        // Test Case 7: Null input
        // null -> should return 0 safely
        runTestCase("Test 7: Null input", null, 0);

        // Test Case 8: Two characters same
        // "aa" -> "a" is longest (length 1)
        runTestCase("Test 8: Two same chars", "aa", 1);

        // Test Case 9: Two characters different
        // "ab" -> "ab" is longest (length 2)
        runTestCase("Test 9: Two diff chars", "ab", 2);

        // Test Case 10: Space character included
        // "a b c" -> "a b" or " b c" (length 3)
        runTestCase("Test 10: With spaces", "a b c", 3);

        // ========== SPECIAL CASES ==========
        System.out.println("--- SPECIAL CASES ---");
        System.out.println();

        // Test Case 11: Numbers in string
        // "12321" -> "123" or "321" (length 3)
        runTestCase("Test 11: Numbers", "12321", 3);

        // Test Case 12: Mixed case letters
        // "aAbBcC" -> all unique due to case sensitivity (length 6)
        runTestCase("Test 12: Mixed case", "aAbBcC", 6);

        // Test Case 13: Special characters
        // "!@#$%^" -> all unique (length 6)
        runTestCase("Test 13: Special chars", "!@#$%^", 6);

        // Test Case 14: Longest at end
        // "aabcdef" -> "abcdef" at end (length 6)
        runTestCase("Test 14: Longest at end", "aabcdef", 6);

        // Test Case 15: Longest at start
        // "abcdefa" -> "abcdef" at start (length 6)
        runTestCase("Test 15: Longest at start", "abcdefa", 6);

        // ========== LARGE DATA TESTS ==========
        System.out.println("--- LARGE DATA TESTS ---");
        System.out.println();

        // Test Case 16: Large string with repeating pattern
        // Pattern "abcd" repeated many times -> max length 4
        String largePattern = generateLargeString(100000, "abcd");
        long startTime = System.currentTimeMillis();
        runTestCase("Test 16: Large pattern (100K)", largePattern, 4);
        long endTime = System.currentTimeMillis();
        System.out.println("       Time taken: " + (endTime - startTime) + " ms");
        System.out.println();

        // Test Case 17: Large string with all same characters
        // All 'a' characters -> max length 1
        String largeAllSame = generateLargeString(100000, "a");
        startTime = System.currentTimeMillis();
        runTestCase("Test 17: Large same chars (100K)", largeAllSame, 1);
        endTime = System.currentTimeMillis();
        System.out.println("       Time taken: " + (endTime - startTime) + " ms");
        System.out.println();

        // Test Case 18: Large string with alphabet pattern
        // 26 unique letters -> max length 26
        String alphabetPattern = generateLargeString(100000, "abcdefghijklmnopqrstuvwxyz");
        startTime = System.currentTimeMillis();
        runTestCase("Test 18: Large alphabet (100K)", alphabetPattern, 26);
        endTime = System.currentTimeMillis();
        System.out.println("       Time taken: " + (endTime - startTime) + " ms");
        System.out.println();

        // Test Case 19: Very large string (1 million characters)
        String veryLarge = generateLargeString(1000000, "abcdefghij");
        startTime = System.currentTimeMillis();
        runTestCase("Test 19: Very large (1M)", veryLarge, 10);
        endTime = System.currentTimeMillis();
        System.out.println("       Time taken: " + (endTime - startTime) + " ms");
        System.out.println();

        // Test Case 20: Extreme large string (5 million characters)
        String extremeLarge = generateLargeString(5000000, "xyz");
        startTime = System.currentTimeMillis();
        runTestCase("Test 20: Extreme large (5M)", extremeLarge, 3);
        endTime = System.currentTimeMillis();
        System.out.println("       Time taken: " + (endTime - startTime) + " ms");
        System.out.println();

        System.out.println("===========================================");
        System.out.println("ALL TESTS COMPLETED");
        System.out.println("===========================================");
    }
}