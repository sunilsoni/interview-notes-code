package com.interview.notes.code.year.y2026.july.common.test2;

import java.util.ArrayList; // Imports ArrayList to dynamically build a massive list of strings for the large data test.
import java.util.Collections; // Imports Collections to help generate large datasets instantly for testing.
import java.util.List; // Imports the List interface to handle our collection of strings (dog, cat, etc.).
import java.util.Map; // Imports the Map interface to store the final key-value pairs (length : count).
import java.util.stream.Collectors; // Imports Collectors utility to group and count stream elements without manual loops.

public class StringLengthCounter { // Declares the main public class that encapsulates our grouping logic.

    public static Map<Integer, Long> countStringsByLength(List<String> words) { // Defines a method that takes a list of words and returns a length-to-count map.
        return words.stream() // Converts the input list into a Java Stream for functional, loop-free processing.
                .filter(w -> w != null) // Filters out any null values to safely prevent NullPointerExceptions during the length check.
                .collect(Collectors.groupingBy(String::length, Collectors.counting())); // Core logic: Groups items by their string length, and counts how many fall into each bucket!
    } // Closes the filtering and grouping method block.

    public static void main(String[] args) { // Main entry point to run our application and custom tests.
        
        // TEST CASE 1: Your exact requested scenario
        var standardInput = List.of("dog", "cat", "elephant", "rabbit"); // Creates an unmodifiable list containing your exact animals using Java's var inference.
        var expectedStandard = Map.of(3, 2L, 8, 1L, 6, 1L); // Defines the expected map: length 3 has 2 items, length 8 has 1, length 6 has 1. (L denotes Long).
        runTest(standardInput, expectedStandard, "Standard Animal List Test"); // Executes our test runner to verify the grouping logic works perfectly.

        // TEST CASE 2: Edge Case - Empty List
        var emptyInput = List.<String>of(); // Creates a completely empty list to test safety.
        var expectedEmpty = Map.<Integer, Long>of(); // We expect an empty map in return when processing an empty list.
        runTest(emptyInput, expectedEmpty, "Empty List Test"); // Verifies the logic handles empty inputs without crashing or throwing errors.

        // TEST CASE 3: Large Data Scale (100,000+ items)
        var largeInput = new ArrayList<>(Collections.nCopies(100000, "dog")); // Instantly creates a massive list of 100,000 "dog" strings (length 3).
        largeInput.add("rabbit"); // Adds a single "rabbit" (length 6) at the end to create a second length category for the stream to process.
        var expectedLarge = Map.of(3, 100000L, 6, 1L); // Expects exactly 100,000 items of length 3, and 1 item of length 6.
        runTest(largeInput, expectedLarge, "Large Data Scale Test"); // Runs the massive dataset through our tester to ensure it scales without performance drops.
        
    } // Closes the main method block.

    private static void runTest(List<String> input, Map<Integer, Long> expected, String testName) { // Helper method to compare the actual map against expected map.
        var actualResult = countStringsByLength(input); // Executes our core grouping logic on the input data and stores it using 'var' to save words.
        
        if (actualResult.equals(expected)) { // Checks if our dynamically generated map perfectly matches the hardcoded expected map.
            System.out.println("PASS : " + testName + " -> Output: " + actualResult); // Prints success and visually shows the output (e.g., {3=2, 6=1, 8=1}).
        } else { // Fallback execution block if the maps do not match exactly.
            System.out.println("FAIL : " + testName); // Prints a clear failure message to the console.
            System.out.println("   Expected: " + expected); // Prints what the map should have been for easy debugging.
            System.out.println("   Got     : " + actualResult); // Prints what the stream logic actually returned.
        } // Closes the if-else comparison block.
    } // Closes the test runner helper method.

} // Closes the main class block.