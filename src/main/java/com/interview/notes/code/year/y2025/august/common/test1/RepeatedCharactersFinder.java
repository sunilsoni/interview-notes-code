package com.interview.notes.code.year.y2025.august.common.test1;

import java.util.Map;
import java.util.stream.Collectors;

public class RepeatedCharactersFinder {

    // Main method to process input string and find repeated characters
    public static String findRepeatedChars(String input) {
        // Convert input to lowercase to handle case-insensitive comparison
        input = input.toLowerCase();

        // Use Stream API to process characters
        return input.chars()
                // Convert to stream of characters
                .mapToObj(ch -> String.valueOf((char) ch))
                // Filter out spaces for cleaner processing
                .filter(ch -> !ch.equals(" "))
                // Group characters by their occurrence
                .collect(Collectors.groupingBy(ch -> ch, Collectors.counting()))
                // Convert to entry set for processing
                .entrySet().stream()
                // Filter only characters that appear more than once
                .filter(entry -> entry.getValue() > 1)
                // Extract only the character
                .map(Map.Entry::getKey)
                // Join all repeated characters
                .collect(Collectors.joining());
    }

    // Main method containing test cases
    public static void main(String[] args) {
        // Test case 1: Basic test with repeated characters
        testCase("This the input string", "thisn", "Basic Test");

        // Test case 2: No repeated characters
        testCase("abcdef", "", "No Repeats Test");

        // Test case 3: All repeated characters
        testCase("aabbcc", "abc", "All Repeats Test");

        // Test case 4: Empty string
        testCase("", "", "Empty String Test");

        // Test case 5: Large input test
        StringBuilder largeInput = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            largeInput.append("abcdefghijklmnopqrstuvwxyz");
        }
        testCase(largeInput.toString(), "abcdefghijklmnopqrstuvwxyz", "Large Input Test");
    }

    // Helper method to run test cases and print results
    private static void testCase(String input, String expected, String testName) {
        try {
            String result = findRepeatedChars(input);
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
