package com.interview.notes.code.year.y2026.july.common.test8;

import java.util.LinkedHashMap; // Imports LinkedHashMap to keep character insertion order in memory
import java.util.Map; // Imports the generic Map interface needed for our entry set stream

public class FirstNonRepeating { // Main class wrapper to hold our application logic

    public static char findFirst(String input) { // Method takes a string input and returns the target character
        
        if (input == null || input.isEmpty()) return 0; // Guard against null or empty inputs to prevent program crashes
        
        var counts = new LinkedHashMap<Character, Integer>(); // Use 'var' to minimize code words, creating an ordered map for frequencies
        
        for (char c : input.toCharArray()) { // Convert string to an array and iterate over every individual character
            counts.merge(c, 1, Integer::sum); // Add 1 to the count for this character, creating the entry if it's new
        } // Close the loop
        
        return counts.entrySet().stream() // Open a Stream on the map's data to process it declaratively 
            .filter(e -> e.getValue() == 1) // Keep only the characters that were counted exactly one time
            .map(Map.Entry::getKey) // Extract the character itself (the key) from the map's key-value pair
            .findFirst() // Stop at the very first match we find (which is naturally the first in the string due to LinkedHashMap)
            .orElse((char) 0); // If the stream is empty (meaning no matches were found), return the null character 0
    } // Close the method

    public static void main(String[] args) { // Simple main method for testing, avoiding JUnit as requested
        
        // Provided test cases from the screenshot
        test("apple", 'a'); // Standard test case 1: 'a' appears once at the start
        test("racecars", 'e'); // Standard test case 2: 'e' is the first non-repeater
        test("ababdc", 'd'); // Standard test case 3: 'a' and 'b' repeat, 'd' is next
        
        // Additional edge case
        test("aabbcc", (char) 0); // Edge case where everything repeats, should return 0
        
        // Large data input test (Creates a string over 200,000 characters long)
        String largeInput = "a".repeat(100000) + "z" + "b".repeat(100000); // Generates massive string with 'z' hidden in the middle
        test(largeInput, 'z'); // Tests performance and validates large data handling
        
    } // Close the main method

    private static void test(String input, char expected) { // Helper method to print PASS/FAIL results clearly
        
        long startTime = System.nanoTime(); // Mark start time to measure execution speed
        char result = findFirst(input); // Call the main logic and store the answer
        long endTime = System.nanoTime(); // Mark end time once calculation is finished
        
        String status = (result == expected) ? "PASS" : "FAIL"; // Compare our answer to the expected one and label it
        long durationMs = (endTime - startTime) / 1000000; // Convert the raw nanosecond time into milliseconds
        
        // Print formatted output to the console so we can verify the results visually
        System.out.printf("[%s] Expected: '%c' | Got: '%c' | Time: %d ms%n", status, expected, result, durationMs); 
        
    } // Close the test method
    
} // Close the class