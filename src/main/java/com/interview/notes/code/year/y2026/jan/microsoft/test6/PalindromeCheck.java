package com.interview.notes.code.year.y2026.jan.microsoft.test6;

import java.util.stream.IntStream;

public class PalindromeCheck {

    /**
     * Checks if a string is a palindrome using Java Stream API.
     */
    static boolean isPalindrome(String str) {
        // Check if input is null first to avoid crashes
        if (str == null) return false;

        // Remove whitespace/noise if needed, but for strict raw check, we use raw length
        // Use 'var' (Java 10+) to infer type 'int' for length to reduce verbosity
        var len = str.length();

        // IntStream.range(0, len / 2) creates a stream of numbers from 0 up to half the string length
        // .allMatch() is a short-circuiting operator; it stops as soon as a mismatch is found (efficient for large data)
        return IntStream.range(0, len / 2)
                .allMatch(i -> str.charAt(i) == str.charAt(len - 1 - i));
    }

    /**
     * Simple custom test runner in main method.
     */
    public static void main(String[] args) {
        System.out.println("Running Palindrome Tests...");

        // --- Test Case 1: Standard Palindrome ---
        runTest("madam", true);

        // --- Test Case 2: Not a Palindrome ---
        runTest("hello", false);

        // --- Test Case 3: Empty String (Edge Case) ---
        // An empty string reads the same forwards/backwards
        runTest("", true);

        // --- Test Case 4: Single Character (Edge Case) ---
        runTest("a", true);

        // --- Test Case 5: Case Sensitivity Check ---
        // "Madam" != "madam" in strict check
        runTest("Madam", false);

        // --- Test Case 6: Large Data Input ---
        // Create a massive palindrome string effectively
        // 'repeat' is a Java 11 feature useful for creating large test data
        var largeData = "a".repeat(1000000) + "b" + "a".repeat(1000000);
        runTest(largeData, true);

        // --- Test Case 7: Large Data Fail ---
        var largeFail = "a".repeat(1000000) + "b";
        runTest(largeFail, false);
    }

    // Helper method to execute test and print PASS/FAIL
    static void runTest(String input, boolean expected) {
        // Measure start time for performance check (optional but good for large data)
        var start = System.currentTimeMillis();

        // Execute the logic
        var result = isPalindrome(input);

        // Calculate status
        var status = (result == expected) ? "PASS" : "FAIL";

        // Print distinct result for visual verification
        // Show input snippet if it's too long
        var displayInput = (input != null && input.length() > 20) ? input.substring(0, 10) + "..." : input;

        System.out.printf("[%s] Input: '%s' | Expected: %b | Got: %b | Time: %dms%n",
                status, displayInput, expected, result, (System.currentTimeMillis() - start));
    }
}