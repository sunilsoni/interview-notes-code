package com.interview.notes.code.year.y2026.april.cts.test3;

import java.util.List;

public class SpacePreservingReverser { // Main class declaration

    public static String reverse(String input) { // Method to reverse string keeping spaces
        if (input == null) return null; // Handle null input to prevent NullPointerException
        
        char[] chars = input.toCharArray(); // Convert to char array for fast, in-place modifications
        int left = 0; // Initialize left pointer at the start of the string
        int right = chars.length - 1; // Initialize right pointer at the end of the string
        
        while (left < right) { // Loop until pointers meet or cross in the middle
            if (chars[left] == ' ') { // Check if current left character is a space
                left++; // Skip space by moving left pointer to the right
            } else if (chars[right] == ' ') { // Check if current right character is a space
                right--; // Skip space by moving right pointer to the left
            } else { // Both pointers are on valid, non-space characters
                char temp = chars[left]; // Store left character temporarily to prevent overwrite
                chars[left] = chars[right]; // Overwrite left position with right character
                chars[right] = temp; // Put the original left character into the right position
                left++; // Advance left pointer to process the next character
                right--; // Retreat right pointer to process the previous character
            }
        }
        return new String(chars); // Reconstruct and return the final string from the modified array
    }

    public static void main(String[] args) { // Main method serving as our custom test runner

        // Use 'var' (Java 10+) for type inference to keep code short and clean
        var testCases = List.of( // Create an immutable list of test scenarios
            new TestCase("example is a test", "tsetasi e l pmaxe"), // Standard test case
            new TestCase("a b c", "c b a"), // Small evenly spaced test
            new TestCase("   ", "   "), // Edge case: only spaces
            new TestCase("a", "a"), // Edge case: single character
            new TestCase(" a b  c ", " c b  a ") // Edge case: leading, trailing, and double spaces
        );

        // Use Java 8 Stream API to process and execute all test cases
        testCases.stream().forEach(test -> { // Iterate over each test case
            var result = reverse(test.input()); // Execute the core logic
            var status = result.equals(test.expected()) ? "PASS" : "FAIL"; // Evaluate result against expected
            System.out.println(status + " | Input: '" + test.input() + "' -> Output: '" + result + "'"); // Log it
        });

        // Stress test for large data inputs
        var largeInput = "a b ".repeat(500000); // Java 11+ repeat() generates a 2-million-character string
        var startTime = System.currentTimeMillis(); // Start timer to measure performance
        reverse(largeInput); // Run the algorithm on the massive string
        var endTime = System.currentTimeMillis(); // Stop timer

        // Print large data test result, confirming fast O(N) execution
        System.out.println("PASS | Large data (2M chars) processed in: " + (endTime - startTime) + "ms");
    }

    // Java 14+ Record to hold test data cleanly in one line without boilerplate code
    record TestCase(String input, String expected) {}
}