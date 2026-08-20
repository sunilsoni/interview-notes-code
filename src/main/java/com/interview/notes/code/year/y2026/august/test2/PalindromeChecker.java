package com.interview.notes.code.year.y2026.august.test2;

import java.util.List;
import java.util.stream.IntStream;

public class PalindromeChecker {

    // Method to check if a string is a palindrome using Java Stream API
    public static boolean isPalindrome(String str) {
        // Guard clause: Return false if input is null to avoid NullPointerException
        if (str == null) {
            // Null strings cannot be symmetric words
            return false;
        }
        // Cache the string length to avoid recalculating it multiple times
        int len = str.length();
        // Generate index stream from 0 up to half the length of the string
        return IntStream.range(0, len / 2)
                // Compare character at index i with its counterpart at (len - 1 - i)
                .allMatch(i -> str.charAt(i) == str.charAt(len - 1 - i));
    }

    // Main execution entry point for standalone verification without JUnit
    public static void main(String[] args) {
        // Create half of a large string with 500,000 characters using modern repeat()
        String half = "a".repeat(500_000);
        // Create 1,000,001 character palindrome for performance and scale testing
        String largePalindrome = half + "b" + half;
        // Create 1,000,001 character non-palindrome with a deliberate mismatch
        String largeNonPalindrome = half + "b" + "c".repeat(500_000);

        // Define our complete test suite covering standard, edge, and large inputs
        List<TestCase> testCases = List.of(
            // Standard odd-length palindrome
            new TestCase("Standard Palindrome (Odd)", "racecar", true),
            // Standard even-length palindrome
            new TestCase("Standard Palindrome (Even)", "noon", true),
            // Standard non-palindrome string
            new TestCase("Standard Non-Palindrome", "hello", false),
            // Single character string (trivially symmetric)
            new TestCase("Single Character", "a", true),
            // Empty string (vacuously symmetric)
            new TestCase("Empty String", "", true),
            // Null input validation
            new TestCase("Null Input", null, false),
            // Two identical characters
            new TestCase("Two Identical Chars", "aa", true),
            // Two distinct characters
            new TestCase("Two Different Chars", "ab", false),
            // 1-million character large palindrome dataset
            new TestCase("Large Palindrome (1M chars)", largePalindrome, true),
            // 1-million character large non-palindrome dataset
            new TestCase("Large Non-Palindrome (1M chars)", largeNonPalindrome, false)
        );

        // Counter to track total number of passing tests
        int passedCount = 0;

        // Loop through each test case sequentially
        for (TestCase tc : testCases) {
            // Execute our palindrome method on the test input
            boolean actual = isPalindrome(tc.input());
            // Verify if actual output matches the expected result
            boolean isPassed = (actual == tc.expected());
            // Increment pass count if test expectation was met
            if (isPassed) {
                // Add 1 to total passes
                passedCount++;
            }
            // Print clear PASS/FAIL result to standard console output
            System.out.printf("[%s] %s -> Expected: %b, Got: %b%n",
                    isPassed ? "PASS" : "FAIL", tc.testName(), tc.expected(), actual);
        }

        // Print visual divider line for summary report
        System.out.println("--------------------------------------------------");
        // Print total results summary
        System.out.printf("Test Execution Summary: %d / %d Tests Passed%n", passedCount, testCases.size());
    }

    // Java record to define immutable test case structure with minimal syntax
    record TestCase(String testName, String input, boolean expected) {}
}