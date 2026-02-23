package com.interview.notes.code.year.y2026.feb.yahoo.test2;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class NonRepeatingFinder { // Class containing logic and tests

    public static char getFirstUnique(String text) { // Method accepts a string and returns a character
        if (text == null || text.isEmpty()) { // Check for bad input to prevent NullPointerException
            return ' '; // Return space as an indicator that no valid character was found
        } // Close the if block

        var charCounts = text.chars() // Use Java 'var' for cleaner code. text.chars() creates an IntStream of characters
                .mapToObj(c -> (char) c) // Convert the raw integer stream into a stream of Character objects
                .collect(Collectors.groupingBy( // Group the characters together into a Map
                        Function.identity(), // The character itself becomes the key in our Map
                        LinkedHashMap::new, // We specifically request a LinkedHashMap to preserve insertion order
                        Collectors.counting() // The value in the Map will be the total count of each character
                )); // Close the collect operation

        return charCounts.entrySet().stream() // Open a new stream from the key-value pairs in our Map
                .filter(entry -> entry.getValue() == 1L) // Filter out any character that appears more than once
                .map(Map.Entry::getKey) // Discard the counts, we only care about the character (the key) now
                .findFirst() // Pick the very first character that survived the filter
                .orElse(' '); // If all characters repeat, return a space as the default fallback
    } // Close the method

    public static void main(String[] args) { // Main method serves as our custom testing framework
        
        test("swiss", 'w', "Standard word test"); // 'w' is the first unique char
        test("aabbcc", ' ', "All repeating characters test"); // No unique chars, expects space
        test("a", 'a', "Single character test"); // 'a' is the only char
        test("", ' ', "Empty string test"); // Edge case, expects space
        test(null, ' ', "Null input test"); // Edge case, expects space

        var largeData = "a".repeat(1000000) + "b" + "c".repeat(1000000); // Create a massive 2-million char string 
        test(largeData, 'b', "Large data input test"); // 'b' is the only unique character in the middle
    } // Close main method

    private static void test(String input, char expected, String testName) { // Helper method to run test cases
        var result = getFirstUnique(input); // Call our main logic method
        if (result == expected) { // Compare actual result with expected result
            System.out.println("PASS - " + testName); // Print PASS if they match
        } else { // Handle the failure scenario
            System.out.println("FAIL - " + testName + " | Expected: '" + expected + "', Got: '" + result + "'"); // Print FAIL with details
        } // Close if-else block
    } // Close test helper method
} // Close the class