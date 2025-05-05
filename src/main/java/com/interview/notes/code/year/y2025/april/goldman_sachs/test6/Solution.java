package com.interview.notes.code.year.y2025.april.goldman_sachs.test6;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Solution {
    /**
     * Optimized solution using HashMap
     * Time Complexity: O(n) - single pass through the string
     * Space Complexity: O(1) - limited by character set size (usually 256 or Unicode)
     */
    public static char findFirst(String input) {
        // Handle edge cases
        if (input == null || input.isEmpty()) {
            return '0';
        }

        // Create frequency map
        Map<Character, Integer> charCount = new HashMap<>();

        // First pass: Count frequencies
        // O(n) time complexity
        for (char c : input.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }

        // Second pass: Find first non-repeating character
        // O(n) time complexity
        for (char c : input.toCharArray()) {
            if (charCount.get(c) == 1) {
                return c;
            }
        }

        return '0';
    }

    /**
     * Further optimized version using single pass with LinkedHashMap
     * Maintains insertion order while counting frequencies
     */
    public static char findFirstOptimized(String input) {
        if (input == null || input.isEmpty()) {
            return '0';
        }

        // LinkedHashMap maintains insertion order
        Map<Character, Integer> charCount = new LinkedHashMap<>();

        // Single pass: Count frequencies while maintaining order
        // O(n) time complexity
        for (char c : input.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }

        // Check for first character with count 1
        // O(1) to O(n) depending on position of first non-repeating char
        for (Map.Entry<Character, Integer> entry : charCount.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }

        return '0';
    }

    /**
     * Comprehensive test method
     */
    public static void runTests() {
        // Test cases with expected results
        String[][] testCases = {
                {"apple", "a"},          // Basic case
                {"racecars", "e"},       // Multiple characters
                {"ababdc", "d"},         // Character in middle
                {"aabb", "0"},           // All repeating
                {"", "0"},               // Empty string
                {"a", "a"},              // Single character
                {"aaaaaa", "0"},         // All same characters
                {"abcdefg", "a"},        // All unique
                {"leetcode", "l"},       // Common coding example
                {"loveleetcode", "v"},   // More complex example
                {"aabbccdeeff", "d"}     // Multiple repeating pairs
        };

        // Test both implementations
        System.out.println("Testing HashMap Implementation:");
        testImplementation(testCases, true);

        System.out.println("\nTesting LinkedHashMap Implementation:");
        testImplementation(testCases, false);

        // Performance test with large input
        testPerformance();
    }

    private static void testImplementation(String[][] testCases, boolean useHashMap) {
        int passed = 0;
        int total = testCases.length;

        for (String[] test : testCases) {
            String input = test[0];
            char expected = test[1].charAt(0);
            char result = useHashMap ? findFirst(input) : findFirstOptimized(input);

            if (result == expected) {
                System.out.printf("PASS: Input: \"%-12s\" → Got: '%c'%n", input, result);
                passed++;
            } else {
                System.out.printf("FAIL: Input: \"%-12s\" → Expected: '%c', Got: '%c'%n",
                        input, expected, result);
            }
        }

        System.out.printf("\nSummary: Total: %d, Passed: %d, Failed: %d%n",
                total, passed, (total - passed));
    }

    private static void testPerformance() {
        // Create large input string
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000000; i++) {
            sb.append((char) ('a' + (i % 26)));
        }
        String largeInput = sb.toString();

        // Test HashMap version
        long start = System.nanoTime();
        char result1 = findFirst(largeInput);
        long hashMapTime = System.nanoTime() - start;

        // Test LinkedHashMap version
        start = System.nanoTime();
        char result2 = findFirstOptimized(largeInput);
        long linkedHashMapTime = System.nanoTime() - start;

        System.out.println("\nPerformance Test (1M characters):");
        System.out.printf("HashMap Time: %d ms%n", hashMapTime / 1000000);
        System.out.printf("LinkedHashMap Time: %d ms%n", linkedHashMapTime / 1000000);
    }

    public static void main(String[] args) {
        runTests();
    }
}
