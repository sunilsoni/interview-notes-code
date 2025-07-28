package com.interview.notes.code.year.y2025.july.common.test8;

import java.util.*;
import java.util.stream.Collectors;

public class CharacterCounter {
    
    // Main method to process input string and run test cases
    public static void main(String[] args) {
        // Test Case 1: Basic test with given example
        runTest("amman", "a=2 m=2 n=1");
        
        // Test Case 2: Empty string
        runTest("", "");
        
        // Test Case 3: Single character
        runTest("a", "a=1");
        
        // Test Case 4: Large input (repeated string)
        runTest("amman".repeat(1000), "a=2000 m=2000 n=1000");
        
        // Test Case 5: Special characters
        runTest("a!m@m#a$n", "a=2 m=2 n=1");
    }

    // Method to count characters and format output
    public static String countChars(String input) {
        // Remove any non-alphabetic characters and convert to lowercase
        String cleanInput = input.replaceAll("[^a-zA-Z]", "").toLowerCase();
        
        // If input is empty after cleaning, return empty string
        if (cleanInput.isEmpty()) {
            return "";
        }

        // Use Java 8 Streams to count character frequencies
        Map<Character, Long> charCount = cleanInput.chars()
            .mapToObj(ch -> (char) ch)  // Convert int to char
            .collect(Collectors.groupingBy(  // Group characters
                ch -> ch,  // Group by character
                Collectors.counting()  // Count occurrences
            ));

        // Sort map by characters and create output string
        return charCount.entrySet().stream()
            .sorted(Map.Entry.comparingByKey())  // Sort alphabetically
            .map(entry -> entry.getKey() + "=" + entry.getValue())  // Format each entry
            .collect(Collectors.joining(" "));  // Join with spaces
    }

    // Method to run and validate test cases
    private static void runTest(String input, String expected) {
        try {
            // Get actual result
            String result = countChars(input);
            
            // Compare with expected result
            boolean passed = result.equals(expected);
            
            // Print test results
            System.out.println("Test Case:");
            System.out.println("Input: " + (input.length() > 50 ? input.substring(0, 47) + "..." : input));
            System.out.println("Expected: " + expected);
            System.out.println("Actual: " + result);
            System.out.println("Status: " + (passed ? "PASS" : "FAIL"));
            System.out.println("--------------------");
            
        } catch (Exception e) {
            // Handle any unexpected errors
            System.out.println("Test failed with exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
