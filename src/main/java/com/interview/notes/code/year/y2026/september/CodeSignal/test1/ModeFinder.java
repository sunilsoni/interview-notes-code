package com.interview.notes.code.year.y2026.september.CodeSignal.test1;

import java.util.Arrays; // Imports Arrays utility class for array manipulation
import java.util.Map; // Imports Map interface for key-value data structures
import java.util.function.Function; // Imports Function interface for identity mapping
import java.util.stream.Collectors; // Imports Collectors for stream reduction operations
import java.util.stream.IntStream; // Imports IntStream for primitive stream generation (large data test)

public class ModeFinder { // Declares the main public class for our solution
    
    static void main(String[] args) { // Main method serves as the entry point for custom testing
        test(new int[]{5, 3, 8, 4, 9, 8, 4, 1, 4}, new int[]{4}, "Standard Example"); // Tests the first example from the screenshot
        test(new int[]{1, 2, 1, 2}, new int[]{1, 2}, "Tie Example"); // Tests the second example where either 1 or 2 is a valid answer
        test(new int[]{99}, new int[]{99}, "Single Element"); // Tests an edge case array containing only one number
        
        int[] largeData = IntStream.generate(() -> 7).limit(1000000).toArray(); // Generates an array of 1 million 7s for heavy load testing
        largeData[500] = 3; // Inserts a different number to verify it doesn't break large sequences
        test(largeData, new int[]{7}, "Large Data (1 Million Elements)"); // Tests the algorithm's performance against high volume data
    } // Ends the main method
    
    public static int findMode(int[] numbers) { // Defines the core logic method accepting an integer array
        if (numbers == null || numbers.length == 0) { // Validates input to ensure the array is not null or empty
            throw new IllegalArgumentException("Array cannot be empty"); // Throws exception if validation fails to prevent runtime crashes
        } // Closes the validation block
        
        var frequencies = Arrays.stream(numbers) // Uses Java 'var' (modern feature) and opens a stream on the primitive array
            .boxed() // Boxes primitive ints into Integer objects so the Collections framework can process them
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())); // Groups identical numbers together and counts them into a Map
            
        return frequencies.entrySet() // Retrieves the set of key-value pairs (number=count) from the generated frequency map
            .stream() // Converts the map's entry set back into a Stream to locate the highest value
            .max(Map.Entry.comparingByValue()) // Scans the map to find the entry with the highest frequency count
            .map(Map.Entry::getKey) // Extracts the actual number (the key) from the winning max entry
            .orElseThrow(); // Safely returns the result or throws a default exception if the map is empty
    } // Ends the findMode method
    
    private static void test(int[] input, int[] expectedOptions, String testName) { // Helper method to execute tests and print PASS/FAIL output
        int result = findMode(input); // Calls our main logic method to calculate the mode
        boolean passed = Arrays.stream(expectedOptions).anyMatch(e -> e == result); // Checks if the calculated result matches any of our valid expected answers
        System.out.println((passed ? "PASS" : "FAIL") + " - " + testName + " | Result: " + result); // Prints the formatted PASS/FAIL test outcome to the console
    } // Ends the test helper method
} // Ends the class