package com.interview.notes.code.year.y2025.June.amazon.test13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CharacterFrequencyFinder {

    // Main method to find kth most frequent character
    public static char findKthMostFrequent(List<Character> chars, int k) {
        // Handle edge cases
        if (chars == null || chars.isEmpty() || k <= 0 || k > chars.size()) {
            throw new IllegalArgumentException("Invalid input parameters");
        }

        // Group characters by frequency using Java 8 Streams
        Map<Character, Long> frequencyMap = chars.stream()
                .collect(Collectors.groupingBy(
                        ch -> ch,    // Key: character
                        Collectors.counting()  // Value: frequency count
                ));

        // Sort characters by frequency (descending) and then alphabetically (descending)
        return frequencyMap.entrySet().stream()
                .sorted((e1, e2) -> {
                    int freqCompare = e2.getValue().compareTo(e1.getValue());
                    // If frequencies are equal, sort alphabetically in descending order
                    return freqCompare != 0 ? freqCompare : e2.getKey().compareTo(e1.getKey());
                })
                .map(Map.Entry::getKey)  // Get only the characters
                .skip(k - 1)             // Skip to kth position
                .findFirst()             // Get the kth element
                .orElseThrow(() -> new IllegalArgumentException("K is out of range"));
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test Case 1: Basic case
        testCase(Arrays.asList('a', 'b', 'a', 'c', 'b', 'a'), 2, 'b', "Basic case");

        // Test Case 2: All same characters
        testCase(Arrays.asList('a', 'a', 'a', 'a'), 1, 'a', "All same characters");

        // Test Case 3: Equal frequencies
        testCase(Arrays.asList('a', 'a', 'b', 'b', 'c', 'c'), 2, 'b', "Equal frequencies");

        // Test Case 4: Large input
        List<Character> largeInput = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            largeInput.add((char) ('a' + i % 26));
        }
        testCase(largeInput, 3, 'x', "Large input");

        // Test Case 5: Edge case - single character
        testCase(List.of('a'), 1, 'a', "Single character");
    }

    // Helper method to run test cases
    private static void testCase(List<Character> input, int k, char expected, String testName) {
        try {
            char result = findKthMostFrequent(input, k);
            boolean passed = result == expected;
            System.out.printf("Test: %s - %s%n", testName, passed ? "PASS" : "FAIL");
            if (!passed) {
                System.out.printf("Expected: %c, Got: %c%n", expected, result);
            }
        } catch (Exception e) {
            System.out.printf("Test: %s - FAIL (Exception: %s)%n", testName, e.getMessage());
        }
    }
}
