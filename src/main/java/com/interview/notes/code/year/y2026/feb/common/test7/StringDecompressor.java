package com.interview.notes.code.year.y2026.feb.common.test7;

import java.util.Objects;

public class StringDecompressor {

    public static void main(String[] args) {
        // 1. Basic Test Case
        test("a2b5c1d3", "aabbbbbcddd", "Basic Case");

        // 2. Multi-digit Number Case (a occurring 12 times)
        test("a12b1", "aaaaaaaaaaaab", "Multi-digit Count");

        // 3. Single Character Case
        test("z5", "zzzzz", "Single Char");

        // 4. Large Data Test Case (Performance Check)
        // We create a large expected string dynamically to verify logic handles size
        String heavyInput = "a1000b1000";
        test(heavyInput, "a".repeat(1000) + "b".repeat(1000), "Large Data 2000 chars");

        // 5. Empty Case
        test("", "", "Empty String");
    }

    /**
     * Decompresses string using StringBuilder for O(N) efficiency.
     */
    public static String decompress(String input) {
        // Handle null or empty input immediately to avoid errors
        if (input == null || input.isEmpty()) return "";

        StringBuilder sb = new StringBuilder(); // Efficiently builds the result string
        int length = input.length(); // Cache length to avoid calling it repeatedly

        int i = 0; // Pointer to traverse the string
        while (i < length) { // Loop until we reach end of string
            char charToRepeat = input.charAt(i); // Step 1: Get the character (e.g., 'a')
            i++; // Move pointer to the next character (which should be a number)

            // Step 2: Extract the full number (handles multi-digits like 12, 100)
            int start = i; // Mark where the number starts
            while (i < length && Character.isDigit(input.charAt(i))) { // Keep moving while we see digits
                i++; // Advance pointer
            }

            // Parse the substring of digits into an Integer
            int count = Integer.parseInt(input.substring(start, i));

            // Step 3: Append the character 'count' times using Java 11+ feature
            sb.append(String.valueOf(charToRepeat).repeat(count));
        }

        return sb.toString(); // Convert builder back to string
    }

    /**
     * Simple Test Harness to verify Pass/Fail without JUnit
     */
    public static void test(String input, String expected, String testName) {
        long startTime = System.nanoTime(); // Start timer for performance check
        String result = decompress(input); // Run the logic
        long endTime = System.nanoTime(); // End timer

        // Check if result matches expected value
        if (Objects.equals(result, expected)) {
            // Print PASS with execution time in microseconds
            System.out.printf("[PASS] %-20s | Time: %4d µs%n", testName, (endTime - startTime) / 1000);
        } else {
            // Print FAIL with details for debugging
            System.out.println("[FAIL] " + testName);
            System.out.println("   Input:    " + input);
            System.out.println("   Expected: " + expected);
            System.out.println("   Got:      " + result);
        }
    }
}