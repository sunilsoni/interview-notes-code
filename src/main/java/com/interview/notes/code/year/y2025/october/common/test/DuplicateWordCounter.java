package com.interview.notes.code.year.y2025.october.common.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class DuplicateWordCounter {

    // Main method to process word counting and run tests
    public static void main(String[] args) {
        // Create instance of the class to access methods
        DuplicateWordCounter counter = new DuplicateWordCounter();

        // Run all test cases
        counter.runTests();
    }

    // Method to count duplicate words using Stream API
    public Map<String, Long> countDuplicateWords(String input) {
        // Handle null input case
        if (input == null || input.trim().isEmpty()) {
            return new HashMap<>();
        }

        // Split input string into words, convert to lowercase, and remove punctuation
        return Arrays.stream(input.toLowerCase().split("\\s+"))
                .map(word -> word.replaceAll("[^a-zA-Z]", "")) // Remove non-alphabetic chars
                .filter(word -> !word.isEmpty()) // Remove empty strings
                .collect(Collectors.groupingBy( // Group words by their occurrence
                        word -> word,
                        Collectors.counting() // Count occurrences
                ));
    }

    // Method to run all test cases
    private void runTests() {
        // Test Case 1: Basic duplicate words
        testCase("Test 1 - Basic duplicates",
                "the dog and the cat and dog",
                Map.of("the", 2L, "dog", 2L, "and", 2L, "cat", 1L));

        // Test Case 2: Case insensitive
        testCase("Test 2 - Case insensitive",
                "The THE the tHe",
                Map.of("the", 4L));

        // Test Case 3: Empty input
        testCase("Test 3 - Empty input",
                "",
                new HashMap<>());

        // Test Case 4: Single word
        testCase("Test 4 - Single word",
                "hello",
                Map.of("hello", 1L));

        // Test Case 5: Large input
        StringBuilder largeInput = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            largeInput.append("test word duplicate test ");
        }
        testCase("Test 5 - Large input",
                largeInput.toString(),
                Map.of("test", 20000L, "word", 10000L, "duplicate", 10000L));
    }

    // Helper method to run individual test cases
    private void testCase(String testName, String input, Map<String, Long> expected) {
        // Get actual result
        Map<String, Long> result = countDuplicateWords(input);

        // Compare with expected result
        boolean passed = result.equals(expected);

        // Print test results
        System.out.println(testName + ": " + (passed ? "PASS" : "FAIL"));
        if (!passed) {
            System.out.println("Expected: " + expected);
            System.out.println("Actual: " + result);
        }
    }
}
