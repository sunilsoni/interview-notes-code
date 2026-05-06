package com.interview.notes.code.year.y2026.may.assessments.test2;

import java.util.List; // Needed to use the List data structure to store our results
import java.util.Map; // Needed to handle the key-value pairs when counting characters
import java.util.function.Function; // Needed for Function.identity() to map elements to themselves
import java.util.stream.Collectors; // Needed to group and count elements in the stream

public class DuplicateCharacterFinder { // Declaring our main public class

    public static List<Character> getDuplicates(String input) { // Method takes a String and returns a List of duplicate characters
        if (input == null || input.isEmpty()) { // Check if the input is null or empty to prevent crash errors
            return List.of(); // Return an empty list immediately if the input is invalid
        } // Close the if-statement block

        return input.chars() // Converts the string into a stream of integer values representing characters
                .mapToObj(c -> (char) c) // Casts each integer back into a Character object so we can read it
                .filter(c -> c != ' ') // Ignores blank spaces so we only count actual letters
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())) // Groups characters and counts how many times each appears, creating a Map
                .entrySet().stream() // Takes that Map and turns it back into a stream of key-value pairs
                .filter(entry -> entry.getValue() > 1) // Filters the stream to only keep characters that have a count greater than 1 (duplicates)
                .map(Map.Entry::getKey) // Extracts just the character (the key) and drops the count number
                .toList(); // Java 16+ feature: collects the final characters directly into an unmodifiable List to save code words
    } // Close the getDuplicates method

    public static void main(String[] args) { // Main method to run our manual test cases without JUnit
        
        // TEST CASE 1: The provided standard string
        var input1 = "java is a awesome programming language"; // Java 10+ feature: using 'var' to keep code short
        var expected1 = List.of('a', 'e', 'g', 'i', 'm', 'o', 'r'); // These are the letters that appear more than once
        testRunner("Test 1 (Standard String)", input1, expected1); // Call our helper method to check pass/fail
        
        // TEST CASE 2: No duplicates
        var input2 = "cat dog"; // A simple string where no letters repeat
        var expected2 = List.<Character>of(); // We expect an empty list back
        testRunner("Test 2 (No Duplicates)", input2, expected2); // Run the test
        
        // TEST CASE 3: Empty string
        var input3 = ""; // Testing an empty input
        var expected3 = List.<Character>of(); // We expect an empty list back
        testRunner("Test 3 (Empty String)", input3, expected3); // Run the test
        
        // TEST CASE 4: Large data input
        var input4 = "a".repeat(10000) + "b".repeat(5000) + "c"; // Simulating a massive string with 15001 characters using Java 11 repeat feature
        var expected4 = List.of('a', 'b'); // Only 'a' and 'b' are duplicated, 'c' is single
        testRunner("Test 4 (Large Data)", input4, expected4); // Run the large data test
    } // Close the main method

    // Helper method to automatically check if tests PASS or FAIL
    private static void testRunner(String testName, String input, List<Character> expected) { // Takes test details and compares results
        var result = getDuplicates(input); // Call our main logic method
        // Check if our result contains exactly the same elements as the expected list (ignoring order)
        boolean passed = result.size() == expected.size() && result.containsAll(expected); // Compares sizes and contents to verify correctness
        
        if (passed) { // If the condition is true...
            System.out.println(testName + " : PASS"); // Print success message to console
        } else { // If the condition is false...
            System.out.println(testName + " : FAIL. Expected: " + expected + ", but got: " + result); // Print failure message with details
        } // Close if-else block
    } // Close testRunner method
} // Close the class