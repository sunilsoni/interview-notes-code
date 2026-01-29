package com.interview.notes.code.year.y2026.jan.common.test6;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CharacterFrequency {

    /**
     * Calculates the frequency of each character in a string.
     * * @param input The string to analyze
     *
     * @return A Map containing characters as keys and their counts as values
     */
    public static Map<Character, Long> getCharFrequency(String input) {
        // Check if input is null; if so, return an empty map to avoid errors
        if (input == null) return Collections.emptyMap();

        // Start the Stream pipeline on the input string
        return input.chars() // Convert String to an IntStream of character codes
                .mapToObj(c -> (char) c) // Cast each integer code back to a Character object
                .collect(Collectors.groupingBy( // Start grouping the elements
                        Function.identity(), // Use the character itself as the key for the map
                        Collectors.counting() // Count the occurrences of each character for the value
                ));
    }

    // Simple helper to compare two maps for our test cases
    private static boolean mapsAreEqual(Map<Character, Long> actual, Map<Character, Long> expected) {
        // If both are null, they are equal
        if (actual == null && expected == null) return true;
        // If one is null but not the other, they are not equal
        if (actual == null || expected == null) return false;
        // Standard Map equals() checks if they contain exactly the same mappings
        return actual.equals(expected);
    }

    public static void main(String[] args) {
        System.out.println("Running Tests...\n");

        // --- Test Case 1: Standard Input "communication" ---
        String input1 = "communication"; // Define input
        Map<Character, Long> expected1 = new HashMap<>(); // Create expected result map manually
        expected1.put('c', 2L);
        expected1.put('o', 2L);
        expected1.put('m', 2L);
        expected1.put('u', 1L);
        expected1.put('n', 2L);
        expected1.put('i', 2L);
        expected1.put('a', 1L);
        expected1.put('t', 1L);

        // Execute logic
        Map<Character, Long> result1 = getCharFrequency(input1);
        // Print Result
        System.out.println("Test Case 1 (Standard): " + (mapsAreEqual(result1, expected1) ? "PASS" : "FAIL"));
        System.out.println("Result: " + result1);

        // --- Test Case 2: Empty String ---
        String input2 = ""; // Define empty input
        Map<Character, Long> expected2 = Collections.emptyMap(); // Expect empty map

        // Execute logic
        Map<Character, Long> result2 = getCharFrequency(input2);
        // Print Result
        System.out.println("\nTest Case 2 (Empty): " + (mapsAreEqual(result2, expected2) ? "PASS" : "FAIL"));

        // --- Test Case 3: Null Input ---
        String input3 = null; // Define null input
        Map<Character, Long> expected3 = Collections.emptyMap(); // Expect empty map (handled safely)

        // Execute logic
        Map<Character, Long> result3 = getCharFrequency(input3);
        // Print Result
        System.out.println("\nTest Case 3 (Null): " + (mapsAreEqual(result3, expected3) ? "PASS" : "FAIL"));

        // --- Test Case 4: Large Data Input ---
        // Create a large string programmatically (e.g., "aaa..." 1 million times)
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000000; i++) sb.append("a"); // Append 'a' 1 million times
        String input4 = sb.toString();

        // We expect a map with a single entry: 'a' -> 1,000,000
        Map<Character, Long> expected4 = new HashMap<>();
        expected4.put('a', 1000000L);

        long startTime = System.currentTimeMillis(); // Start timer for performance check
        Map<Character, Long> result4 = getCharFrequency(input4); // Execute logic
        long endTime = System.currentTimeMillis(); // End timer

        // Print Result
        System.out.println("\nTest Case 4 (Large Data - 1M chars): " + (mapsAreEqual(result4, expected4) ? "PASS" : "FAIL"));
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
    }
}