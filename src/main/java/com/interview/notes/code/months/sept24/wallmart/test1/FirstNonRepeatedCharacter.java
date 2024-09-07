package com.interview.notes.code.months.sept24.wallmart.test1;

import java.util.LinkedHashMap;
import java.util.Map;

public class FirstNonRepeatedCharacter {

    public static char findFirstNonRepeatedChar(String input) {
        if (input == null || input.isEmpty()) {
            return '\0'; // Return null character for empty or null input
        }

        // Using LinkedHashMap to maintain insertion order
        Map<Character, Integer> charCount = new LinkedHashMap<>();

        // First pass: Count character occurrences
        for (char c : input.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }

        // Second pass: Find the first character with count 1
        for (Map.Entry<Character, Integer> entry : charCount.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }

        return '\0'; // Return null character if no non-repeated character found
    }

    public static void main(String[] args) {
        // Test cases
        testCase("krishna", 'k', 1);
        testCase("aabbcdeeff", 'c', 2);
        testCase("aabbcc", '\0', 3);
        testCase("", '\0', 4);
        testCase("aaaaaa", '\0', 5);
        testCase("abcdefg", 'a', 6);

        // Large input test case
        StringBuilder largeInput = new StringBuilder();
        for (int i = 0; i < 1000000; i++) {
            largeInput.append((char) ('a' + i % 26));
        }
        largeInput.append('X');
        testCase(largeInput.toString(), 'X', 7);
    }

    private static void testCase(String input, char expected, int testNumber) {
        long startTime = System.nanoTime();
        char result = findFirstNonRepeatedChar(input);
        long endTime = System.nanoTime();

        boolean passed = result == expected;
        System.out.printf("Test Case %d: %s (Execution time: %.3f ms)%n",
                testNumber,
                passed ? "Passed" : "Failed",
                (endTime - startTime) / 1_000_000.0);

        if (!passed) {
            System.out.printf("  Expected: '%c', Got: '%c'%n", expected, result);
        }
    }
}
