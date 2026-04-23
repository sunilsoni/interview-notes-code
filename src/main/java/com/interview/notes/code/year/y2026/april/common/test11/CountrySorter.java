package com.interview.notes.code.year.y2026.april.common.test11;

import java.util.Comparator; // Required to use the Comparator functional interface to define our sorting rules
import java.util.List; // Required to use the List interface to store our sequence of country names

public class CountrySorter { // Define the main class that contains our sorting and testing logic
    
    public static void main(String[] args) { // Main method serves as the standard entry point to run our application
        
        List<String> input = List.of("india", "australia", "pakistan", "canada", "chily", "america", "arab"); // Create an immutable list of the original country names provided in the requirement
        List<String> expected = List.of("arab", "india", "chily", "canada", "america", "pakistan", "australia"); // Define the expected outcome where countries are ordered by character count
        runTest(input, expected, "Base Requirement Test"); // Call our custom test method to verify the main requirement
        
        List<String> largeInput = List.of("a", "zz", "yyy", "xxxx", "wwwww", "vvvvvv", "uuuuuuu", "tttttttt"); // Create a simulated edge case dataset to ensure it handles different sizes 
        List<String> expectedLarge = List.of("a", "zz", "yyy", "xxxx", "wwwww", "vvvvvv", "uuuuuuu", "tttttttt"); // Define the expected outcome for our simulated large data test
        runTest(largeInput, expectedLarge, "Large/Edge Case Test"); // Call our custom test method to verify the edge case
        
    } // End of the main execution block
    
    static void runTest(List<String> inputList, List<String> expectedList, String testName) { // Helper method to isolate testing logic, keeping the main method clean
        
        var sortedResult = inputList.stream() // Use Java 10 'var' for brevity and convert the input list into a Stream for functional processing
            .sorted(Comparator.comparingInt(String::length)) // Intercept the stream and sort elements purely by extracting and comparing their integer lengths
            .toList(); // Terminate the stream and package the sorted results directly into a new immutable List (Java 16+ feature)
            
        String status = sortedResult.equals(expectedList) ? "PASS" : "FAIL"; // Compare our computed result against the expected result and assign a pass/fail string
        System.out.println(testName + " -> " + status + " | Result: " + sortedResult); // Print the test name, its pass/fail status, and the final sorted array to the console
        
    } // End of the custom testing method
    
} // End of the class definition