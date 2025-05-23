package com.interview.notes.code.year.y2025.may.common.test7;

import java.util.Set;
import java.util.stream.Collectors;

public class CharacterContainment {

    // Method to check if all characters in match exist in input string
    // Using Java 8 Stream for elegant solution
    public static boolean containsAllChars(String input, String match) {
        // Guard clause for null inputs
        if (input == null || match == null) {
            return false;
        }

        // Convert input string to character set
        Set<Character> inputChars = input.chars()
                .mapToObj(ch -> (char) ch)
                .collect(Collectors.toSet());

        // Check if all characters in match exist in input set
        return match.chars()
                .mapToObj(ch -> (char) ch)
                .allMatch(inputChars::contains);
    }

    // Main method with test cases
    public static void main(String[] args) {
        // Test case runner method
        runTest("abcdef", "bcd", true, "Basic positive test");
        runTest("abcdef", "efg", false, "Basic negative test");
        runTest("", "", true, "Empty strings test");
        runTest(null, "abc", false, "Null input test");
        runTest("abc", null, false, "Null match test");
        runTest("aaa", "a", true, "Single character test");
        runTest("abcdef", "fff", false, "Repeated character test");

        // Large data test
        StringBuilder largeInput = new StringBuilder();
        StringBuilder largeMatch = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            largeInput.append((char) ('a' + (i % 26)));
            if (i < 50000) {
                largeMatch.append((char) ('a' + (i % 26)));
            }
        }
        runTest(largeInput.toString(), largeMatch.toString(), true, "Large data test");
    }

    // Helper method to run tests and print results
    private static void runTest(String input, String match, boolean expectedResult, String testName) {
        try {
            boolean result = containsAllChars(input, match);
            boolean passed = result == expectedResult;
            System.out.printf("Test: %s - %s%n", testName, passed ? "PASS" : "FAIL");
            if (!passed) {
                System.out.printf("Expected: %b, Got: %b%n", expectedResult, result);
            }
        } catch (Exception e) {
            System.out.printf("Test: %s - FAIL (Exception: %s)%n", testName, e.getMessage());
        }
    }
}
