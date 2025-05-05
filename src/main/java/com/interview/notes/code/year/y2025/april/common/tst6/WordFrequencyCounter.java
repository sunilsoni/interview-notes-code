package com.interview.notes.code.year.y2025.april.common.tst6;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class WordFrequencyCounter {

    public static Map<String, Long> countWords(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null or empty");
        }

        return Arrays.stream(input.toLowerCase().split("\\s+"))
                .collect(Collectors.groupingBy(
                        word -> word,
                        Collectors.counting()
                ));
    }

    public static void main(String[] args) {
        // Test cases
        runTest("Test Case 1",
                "I like Java i live java i love java",
                Map.of("i", 3L, "like", 1L, "java", 3L, "live", 1L, "love", 1L));

        runTest("Test Case 2",
                "hello hello world",
                Map.of("hello", 2L, "world", 1L));

        // Edge case - single word
        runTest("Test Case 3",
                "word",
                Map.of("word", 1L));

        // Edge case - empty spaces
        runTest("Test Case 4",
                "  word   word  ",
                Map.of("word", 2L));
    }

    private static void runTest(String testName, String input, Map<String, Long> expected) {
        try {
            Map<String, Long> result = countWords(input);
            boolean passed = result.equals(expected);

            System.out.println(testName + ": " + (passed ? "PASS" : "FAIL"));
            if (!passed) {
                System.out.println("Expected: " + expected);
                System.out.println("Got: " + result);
            }
        } catch (Exception e) {
            System.out.println(testName + ": FAIL (Exception: " + e.getMessage() + ")");
        }
    }
}
