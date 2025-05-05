package com.interview.notes.code.year.y2025.april.goldman_sachs.test1;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    /**
     * Optimized single-pass solution using HashMap
     * Stores both frequency and first index position
     */
    public static char findFirstNonRepeating(String input) {
        if (input == null || input.isEmpty()) {
            return '0';
        }

        // Map to store character frequency and first index
        // Key: character
        // Value: int[2] where
        // - index 0 stores frequency
        // - index 1 stores first index position
        Map<Character, int[]> charMap = new HashMap<>();

        // Single pass: Store both frequency and first index
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (!charMap.containsKey(c)) {
                charMap.put(c, new int[]{1, i}); // {frequency, first index}
            } else {
                charMap.get(c)[0]++; // Increment frequency
            }
        }

        // Find character with frequency 1 and minimum index
        int minIndex = input.length();
        char result = '0';

        for (Map.Entry<Character, int[]> entry : charMap.entrySet()) {
            int[] value = entry.getValue();
            if (value[0] == 1 && value[1] < minIndex) {
                minIndex = value[1];
                result = entry.getKey();
            }
        }

        return result;
    }

    // Test method
    public static void runTests() {
        String[][] testCases = {
                {"leetcode", "l"},        // First char is non-repeating
                {"loveleetcode", "v"},    // Non-repeating char in middle
                {"aabb", "0"},            // No non-repeating char
                {"abcab", "c"},           // Single non-repeating char
                {"", "0"},                // Empty string
                {"a", "a"},               // Single char
                {"aaa", "0"},             // All repeating
                {"abcdef", "a"},          // All unique
                {"aabbc", "c"},           // Last char is non-repeating
                {"zxvczbtxyzvy", "c"}     // Complex case
        };

        for (String[] test : testCases) {
            String input = test[0];
            char expected = test[1].charAt(0);
            char result = findFirstNonRepeating(input);

            System.out.printf("Input: %-12s | Expected: %c | Got: %c | %s%n",
                    input, expected, result,
                    (result == expected ? "✓ PASS" : "✗ FAIL"));
        }
    }

    // Performance test
    public static void testPerformance() {
        // Create large test string
        StringBuilder sb = new StringBuilder();
        int size = 1_000_000;
        for (int i = 0; i < size; i++) {
            sb.append((char) ('a' + (i % 26)));
        }
        String largeInput = sb.toString();

        // Measure execution time
        long startTime = System.nanoTime();
        char result = findFirstNonRepeating(largeInput);
        long endTime = System.nanoTime();

        System.out.printf("%nPerformance Test (String length: %d)%n", size);
        System.out.printf("Execution time: %d ms%n", (endTime - startTime) / 1_000_000);
        System.out.printf("Result: %c%n", result);
    }

    public static void main(String[] args) {
        System.out.println("Running test cases:");
        runTests();
        testPerformance();
    }
}
