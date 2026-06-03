package com.interview.notes.code.year.y2026.june.cognizant.test1;

import java.util.HashSet; // Import HashSet to efficiently store and look up unique characters
import java.util.stream.Stream; // Import Stream API to create a clean, loop-free testing pipeline

public class LongestSubstring { // Define the main class that encapsulates our solution and tests

    // IMPLEMENTATION
    public static int getLongest(String s) { // Method definition taking the target string and returning the longest substring length
        var seen = new HashSet<Character>(); // Use Java 10+ 'var' to reduce words; HashSet tracks the unique characters in our current window
        int max = 0, left = 0; // 'max' tracks the highest length found; 'left' tracks the starting index of our sliding window
        for (int right = 0; right < s.length(); right++) { // 'right' pointer expands our window one character at a time to the right
            while (seen.contains(s.charAt(right))) { // If the character at 'right' is already in our Set, we have hit a duplicate
                seen.remove(s.charAt(left++)); // Shrink the window from the left side, removing characters from the Set until the duplicate is gone
            } // Close the while loop used for shrinking
            seen.add(s.charAt(right)); // Now that the window is valid (no duplicates), add the current 'right' character to the Set
            max = Math.max(max, right - left + 1); // Calculate current window length (right - left + 1) and update 'max' if it's the largest seen
        } // Close the for loop traversing the string
        return max; // Return the final highest length recorded
    } // Close the implementation method

    public static void main(String[] args) { // Simple main method to execute test cases without requiring external testing frameworks
        var largeInput = "a".repeat(50000) + "bcdef" + "g".repeat(50000); // Use Java 11+ String.repeat to generate a massive 100,005 character string

        Stream.of( // Initialize a Stream of our test cases to cleanly process them
            new TestCase("abcdabc", 4), // The primary test case provided in the requirements
            new TestCase("bbbbb", 1), // Edge case: String with all identical characters
            new TestCase("pwwkew", 3), // Standard case: The longest substring is in the middle
            new TestCase("", 0), // Edge case: Completely empty string
            new TestCase(" ", 1), // Edge case: String containing only a single space
            new TestCase(largeInput, 7) // Large data case: Tests for performance bottlenecks and memory safety
        ).forEach(test -> { // Iterate over each test case object in the stream
            long start = System.nanoTime(); // Record the exact start time to measure performance
            int result = getLongest(test.input()); // Execute our sliding window logic on the current test string
            long duration = (System.nanoTime() - start) / 1000000; // Calculate how long the execution took in milliseconds
            boolean passed = result == test.expected(); // Determine if the actual result matches the expected result

            // Print the formatted PASS/FAIL output directly to the console
            System.out.printf("[%s] Input: %-15s | Expected: %d | Got: %d | Time: %dms%n", // Use printf for clean tabular alignment
                passed ? "PASS" : "FAIL", // Use a ternary operator to output PASS or FAIL
                test.input().length() > 10 ? test.input().substring(0, 10) + "..." : test.input(), // Truncate very long inputs so the console remains readable
                test.expected(), result, duration); // Pass in the expected value, actual value, and time taken
        }); // Close the forEach block
    } // Close the main method

    // TESTING
    record TestCase(String input, int expected) {} // Use Java 14+ 'record' to create an immutable data carrier for tests in a single line
} // Close the main class