package com.interview.notes.code.year.y2026.april.common.test5;

import java.util.ArrayList; // Imports ArrayList for large data generation in tests
import java.util.Collections; // Imports Collections to easily generate large lists
import java.util.List; // Imports the List interface to handle the collection of letters

public class WordBuilder { // Declares the main class to hold our application logic

    public static boolean canBuild(List<String> letters, String word) { // Defines the method taking the available letters and target word
        if (letters == null || word == null) return false; // Checks for missing inputs to prevent unexpected application crashes
        
        var counts = new int[256]; // Uses Java 'var' to create a fast integer array for tracking ASCII character frequencies
        
        letters.forEach(letter -> counts[letter.toLowerCase().charAt(0)]++); // Uses Java 8 Iterable/forEach to count each available letter, standardizing to lowercase
        
        for (var c : word.toLowerCase().toCharArray()) { // Loops through each character of the target word in lowercase format
            if (--counts[c] < 0) { // Subtracts 1 from the available letter count and checks if it drops below zero
                return false; // Returns false immediately because we ran out of the required letter
            } // Closes the if statement
        } // Closes the for loop
        
        return true; // Returns true because the loop finished, meaning we had enough letters for the whole word
    } // Closes the canBuild method

    public static void main(String[] args) { // Main method serves as our custom testing framework
        runTest("Missing letters", List.of("A", "B", "C"), "Apple", false); // Tests a basic failing scenario based on your example
        runTest("Exact match", List.of("a", "p", "p", "l", "e"), "apple", true); // Tests a basic passing scenario
        runTest("Case differences", List.of("A", "p", "P", "L", "E"), "apple", true); // Tests case insensitivity
        runTest("Extra unused letters", List.of("A", "P", "P", "L", "E", "Z", "X"), "Apple", true); // Tests having more letters than needed
        
        var largeList = new ArrayList<>(Collections.nCopies(1000000, "a")); // Generates a massive list of one million 'a's for stress testing
        largeList.add("b"); // Adds a single 'b' to the end of the large data pool
        largeList.add("c"); // Adds a single 'c' to the end of the large data pool
        runTest("Large data check", largeList, "cab", true); // Tests performance with a massive data pool to ensure O(N) efficiency
    } // Closes the main method

    private static void runTest(String testName, List<String> letters, String word, boolean expected) { // Helper method to execute tests and format the output
        var result = canBuild(letters, word); // Calls our main logic method and stores the boolean result
        if (result == expected) { // Checks if the actual result matches our expected outcome
            System.out.println("PASS: " + testName); // Prints a success message to the console
        } else { // Handles the scenario where the test fails
            System.out.println("FAIL: " + testName + " (Expected " + expected + " but got " + result + ")"); // Prints a failure message with diagnostic details
        } // Closes the if/else block
    } // Closes the test helper method
} // Closes the class