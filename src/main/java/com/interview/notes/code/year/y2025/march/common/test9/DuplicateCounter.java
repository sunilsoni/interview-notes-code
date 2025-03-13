package com.interview.notes.code.year.y2025.march.common.test9;

import java.util.*;
import java.util.stream.Collectors;

/**
 * DuplicateCounter class that processes input to count duplicate occurrences.
 */
public class DuplicateCounter {

    /**
     * countDuplicates:
     * - Splits the input string into tokens (using spaces as delimiters).
     * - Uses Java 8 streams to group tokens and count their occurrences.
     * - Filters the result to include only tokens that occur more than once.
     *
     * @param input A string containing tokens separated by spaces.
     * @return A map where keys are tokens and values are their counts (only for duplicates).
     */
    public static Map<String, Long> countDuplicates(String input) {
        // Split the input string into tokens based on whitespace.
        String[] tokens = input.split("\\s+");

        // Group tokens by their value and count the occurrences using streams.
        Map<String, Long> frequencyMap = Arrays.stream(tokens)
                .collect(Collectors.groupingBy(token -> token, Collectors.counting()));

        // Filter out tokens that occur only once; keep only duplicates.
        Map<String, Long> duplicates = frequencyMap.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return duplicates;
    }

    /**
     * main method:
     * - Demonstrates the duplicate counting on the provided example.
     * - Runs a series of tests to validate the solution.
     *
     * @param args Command-line arguments (not used here).
     */
    public static void main(String[] args) {
        // Example input provided in the problem statement.
        String input = "2 1 3 1 2 s 5 a 1 a 3";

        // Calculate duplicate occurrences.
        Map<String, Long> duplicates = countDuplicates(input);

        // Output the duplicates and their counts.
        System.out.println("Duplicate occurrences: " + duplicates);

        // Run test cases to validate the solution.
        runTests();
    }

    /**
     * runTests:
     * - Contains several test cases including edge cases and a large data test.
     * - For each test case, compares the expected output to the actual result.
     * - Prints PASS/FAIL messages accordingly.
     */
    public static void runTests() {
        // Create a list to store different test cases.
        List<TestCase> testCases = new ArrayList<>();

        // Test case 1: Provided example input.
        testCases.add(new TestCase("2 1 3 1 2 s 5 a 1 a 3",
                Map.of("2", 2L, "1", 3L, "3", 2L, "a", 2L)));

        // Test case 2: No duplicates present.
        testCases.add(new TestCase("x y z", new HashMap<>()));

        // Test case 3: All tokens are the same.
        testCases.add(new TestCase("a a a a", Map.of("a", 4L)));

        // Test case 4: Mixed numbers with duplicates.
        testCases.add(new TestCase("1 2 3 2 1", Map.of("1", 2L, "2", 2L)));

        // Test case 5: Large input test with a repeated token.
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb.append("test ").append(" ");
        }
        String largeInput = sb.toString().trim();
        testCases.add(new TestCase(largeInput, Map.of("test", 10000L)));

        boolean allPass = true;
        // Iterate through each test case.
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            Map<String, Long> result = countDuplicates(tc.input);
            // Check if the result matches the expected output.
            if (!result.equals(tc.expected)) {
                System.out.println("Test case " + (i + 1) + " FAILED.");
                System.out.println("Input: " + tc.input);
                System.out.println("Expected: " + tc.expected);
                System.out.println("Got: " + result);
                allPass = false;
            } else {
                System.out.println("Test case " + (i + 1) + " PASSED.");
            }
        }
        // Overall test result.
        System.out.println(allPass ? "All test cases passed." : "Some test cases failed.");
    }

    /**
     * TestCase:
     * - A simple inner class to store test case data: input string and the expected frequency map.
     */
    static class TestCase {
        String input;
        Map<String, Long> expected;

        TestCase(String input, Map<String, Long> expected) {
            this.input = input;
            this.expected = expected;
        }
    }
}