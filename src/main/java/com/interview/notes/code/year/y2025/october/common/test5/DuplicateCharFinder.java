package com.interview.notes.code.year.y2025.october.common.test5;

import java.util.HashMap;
import java.util.Map;

public class DuplicateCharFinder {
    
    // Main method to test our duplicate character finding logic
    public static void main(String[] args) {
        // Test cases to verify our solution works correctly
        testDuplicateChars("Robert Williams");
        testDuplicateChars("John Doe");
        testDuplicateChars("aabbccdd");
        testDuplicateChars("");  // Empty string test case
        testDuplicateChars("a"); // Single character test case
        testDuplicateChars("aaaaaa"); // All same characters
        
        // Test case for large input
        StringBuilder largeInput = new StringBuilder();
        for(int i = 0; i < 100000; i++) {
            largeInput.append("abcdef");
        }
        testDuplicateChars(largeInput.toString());
    }
    
    // Method to test each input and print results
    private static void testDuplicateChars(String input) {
        System.out.println("\nTesting input: " + input);
        
        // Record start time for performance measurement
        long startTime = System.currentTimeMillis();
        
        // Get duplicate characters using Java 8 streams
        String result = findDuplicates(input);
        
        // Calculate execution time
        long endTime = System.currentTimeMillis();
        
        // Print results
        System.out.println("Duplicate characters: " + result);
        System.out.println("Execution time: " + (endTime - startTime) + "ms");
    }
    
    // Method to find duplicate characters in a string
    private static String findDuplicates(String input) {
        // Handle null input
        if (input == null) return "";
        
        // Create a map to store character counts
        Map<Character, Long> charCount = new HashMap<>();
        
        // Use Java 8 streams to count characters
        input.chars()
            .mapToObj(ch -> (char) ch)
            .filter(ch -> !Character.isWhitespace(ch)) // Ignore whitespace
            .forEach(ch -> charCount.merge(ch, 1L, Long::sum));
        
        // Use streams to find and collect characters that appear more than once
        return charCount.entrySet().stream()
            .filter(entry -> entry.getValue() > 1)
            .map(entry -> entry.getKey() + "(" + entry.getValue() + ")")
            .reduce("", (a, b) -> a + (a.isEmpty() ? "" : ", ") + b);
    }
}
