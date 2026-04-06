package com.interview.notes.code.year.y2026.april.assessments.test6;

import java.util.Arrays; // Needed to initialize our memory array
import java.util.stream.Stream; // Needed to run test cases using the Stream API

public class LongestSubstring { // Main class declaration

    // Main algorithm to find the longest substring
    public static int solve(String s) { // Method takes a string and returns an integer length
        var max = 0; // Use 'var' (Java 10+) to implicitly type the max length variable to 0
        var seen = new int[128]; // Use a fixed array to store the last seen indices for ASCII characters
        Arrays.fill(seen, -1); // Fill with -1 because index 0 is a valid position in a string
        var start = 0; // Initialize the start pointer of our sliding window at the beginning
        
        for (var end = 0; end < s.length(); end++) { // Iterate through the string character by character with an 'end' pointer
            var ch = s.charAt(end); // Extract the current character at the end pointer
            start = Math.max(start, seen[ch] + 1); // Move start past the previous duplicate (if it exists) to maintain uniqueness
            max = Math.max(max, end - start + 1); // Calculate current window size and update max if the current size is larger
            seen[ch] = end; // Update our array memory with the current index of this specific character
        } // End of the string iteration loop
        
        return max; // Return the highest length found after checking the whole string
    } // End of solve method

    public static void main(String[] args) { // Entry point for our custom testing logic
        var largeInput = "a".repeat(1000000) + "xyz"; // Generate a massive string (Java 11+) for stress testing performance

        // Create a stream of test cases
        var tests = Stream.of( // Using Stream API to hold and group our data
            new Test("abcabcbb", 3, "Standard Case"), // Standard test containing repeating sets
            new Test("bbbbb", 1, "All Duplicates"), // Test where every character is the same
            new Test("pwwkew", 3, "Answer in Middle"), // Test where the longest substring (wke) is buried in the middle
            new Test("", 0, "Empty String"), // Edge case handling for zero characters
            new Test(" ", 1, "Single Space"), // Edge case handling for whitespace
            new Test("au", 2, "Two Uniques"), // Edge case handling for short strings
            new Test(largeInput, 3, "Large Data Input") // Tests memory and speed on 1 million characters
        ); // End of stream definition

        tests.forEach(test -> { // Iterate over each test case using Stream's forEach method
            var result = solve(test.input()); // Run our sliding window algorithm on the test input
            var status = (result == test.expected()) ? "PASS" : "FAIL"; // Check if output matches expected, assign PASS/FAIL string
            System.out.println(status + " | " + test.name() + " | Expected: " + test.expected() + " | Got: " + result); // Print the final formatted test output
        }); // End of stream processing loop
    } // End of main method

    // Java 14+ Record to hold test cases cleanly without boilerplate getters/setters
    record Test(String input, int expected, String name) {} // Defines a compact data structure for our test definitions
} // End of class