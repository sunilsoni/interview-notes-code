package com.interview.notes.code.year.y2025.august.common.test9;

import java.util.stream.Stream;

public class StringReverseOrder {

    // Main method to process string reversal and run test cases
    public static void main(String[] args) {
        // Create instance of the class to access non-static methods
        StringReverseOrder solution = new StringReverseOrder();

        // Run all test cases
        solution.runTestCases();
    }

    // Method to reverse the order of words in a string
    public String reverseStringOrder(String input) {
        // Handle null input case
        if (input == null) return "";

        // Split input string into words, reverse order, and join with space
        return Stream.of(input.split("\\s+"))  // Split string into words array
                .filter(word -> !word.isEmpty())  // Remove empty strings
                .reduce((word1, word2) -> word2 + " " + word1)  // Reverse order
                .orElse("");  // Handle empty input case
    }

    // Method to run and validate test cases
    private void runTestCases() {
        // Test Case 1: Basic case
        runTest("England Beats India", "India Beats England", "Basic Test Case");

        // Test Case 2: Single word
        runTest("Hello", "Hello", "Single Word Test");

        // Test Case 3: Empty string
        runTest("", "", "Empty String Test");

        // Test Case 4: Null input
        runTest(null, "", "Null Input Test");

        // Test Case 5: Multiple spaces
        runTest("Java   is    awesome", "awesome is Java", "Multiple Spaces Test");

        // Test Case 6: Large input test
        StringBuilder largeInput = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            largeInput.append("word").append(i).append(" ");
        }
        String expected = new StringBuilder(largeInput.toString().trim())
                .reverse().toString();
        runTest(largeInput.toString(), expected, "Large Input Test");
    }

    // Helper method to run individual test and print result
    private void runTest(String input, String expected, String testName) {
        String result = reverseStringOrder(input);
        boolean passed = expected.equals(result);

        // Print test results with formatting
        System.out.println("Test: " + testName);
        System.out.println("Input: " + input);
        System.out.println("Expected: " + expected);
        System.out.println("Result: " + result);
        System.out.println("Status: " + (passed ? "PASS" : "FAIL"));
        System.out.println("------------------------");
    }
}
