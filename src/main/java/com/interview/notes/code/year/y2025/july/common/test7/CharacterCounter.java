package com.interview.notes.code.year.y2025.july.common.test7;

import java.util.*;
import java.util.stream.Collectors;

public class CharacterCounter {
    
    // Main method to process input string and run test cases
    public static void main(String[] args) {
        // Test Case 1: Basic test with given example
        runTest("amman", "a=2 m=2 n=1", "Most repeated: a,m (2 times)");
        
        // Test Case 2: Clear maximum repeating character
        runTest("aaamm", "a=3 m=2", "Most repeated: a (3 times)");
        
        // Test Case 3: Empty string
        runTest("", "", "Most repeated: none");
        
        // Test Case 4: Single character
        runTest("a", "a=1", "Most repeated: a (1 time)");
        
        // Test Case 5: Large input with clear maximum
        runTest("aaabbc", "a=3 b=2 c=1", "Most repeated: a (3 times)");
    }

    // Class to hold character count result and maximum repeating characters
    static class CountResult {
        String frequencyString;
        String maxRepeatingInfo;

        CountResult(String frequencyString, String maxRepeatingInfo) {
            this.frequencyString = frequencyString;
            this.maxRepeatingInfo = maxRepeatingInfo;
        }
    }

    // Method to count characters and find maximum repeating characters
    public static CountResult countChars(String input) {
        // Remove any non-alphabetic characters and convert to lowercase
        String cleanInput = input.replaceAll("[^a-zA-Z]", "").toLowerCase();
        
        // If input is empty after cleaning, return empty result
        if (cleanInput.isEmpty()) {
            return new CountResult("", "Most repeated: none");
        }

        // Use Java 8 Streams to count character frequencies
        Map<Character, Long> charCount = cleanInput.chars()
            .mapToObj(ch -> (char) ch)
            .collect(Collectors.groupingBy(
                ch -> ch,
                Collectors.counting()
            ));

        // Find maximum frequency
        long maxFrequency = charCount.values().stream()
            .max(Long::compareTo)
            .orElse(0L);

        // Find all characters with maximum frequency
        List<Character> maxChars = charCount.entrySet().stream()
            .filter(entry -> entry.getValue() == maxFrequency)
            .map(Map.Entry::getKey)
            .sorted()
            .collect(Collectors.toList());

        // Create frequency string
        String frequencyString = charCount.entrySet().stream()
            .sorted(Map.Entry.comparingByKey())
            .map(entry -> entry.getKey() + "=" + entry.getValue())
            .collect(Collectors.joining(" "));

        // Create maximum repeating info string
        String maxRepeatingInfo = String.format("Most repeated: %s (%d time%s)",
            maxChars.size() > 1 
                ? maxChars.stream().map(String::valueOf).collect(Collectors.joining(","))
                : maxChars.get(0),
            maxFrequency,
            maxFrequency > 1 ? "s" : "");

        return new CountResult(frequencyString, maxRepeatingInfo);
    }

    // Method to run and validate test cases
    private static void runTest(String input, String expectedFreq, String expectedMax) {
        try {
            // Get actual result
            CountResult result = countChars(input);
            
            // Compare with expected results
            boolean freqPassed = result.frequencyString.equals(expectedFreq);
            boolean maxPassed = result.maxRepeatingInfo.equals(expectedMax);
            boolean passed = freqPassed && maxPassed;
            
            // Print test results
            System.out.println("Test Case:");
            System.out.println("Input: " + (input.length() > 50 ? input.substring(0, 47) + "..." : input));
            System.out.println("Expected Frequency: " + expectedFreq);
            System.out.println("Actual Frequency: " + result.frequencyString);
            System.out.println("Expected Max: " + expectedMax);
            System.out.println("Actual Max: " + result.maxRepeatingInfo);
            System.out.println("Status: " + (passed ? "PASS" : "FAIL"));
            System.out.println("--------------------");
            
        } catch (Exception e) {
            // Handle any unexpected errors
            System.out.println("Test failed with exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
