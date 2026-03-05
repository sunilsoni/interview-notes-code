package com.interview.notes.code.year.y2026.march.common.test3;

import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class SortCompanies { // Define the main public class to execute our sorting program

    // Method to sort a list in descending order using Java Streams
    static List<String> sortDescending(List<String> input) { // Accept a List of Strings and return a newly sorted List
        return input.stream() // Convert the input list into a Stream so we can process its elements
                .sorted(Comparator.reverseOrder()) // Sort the stream elements in descending (reverse alphabetical) order
                .toList(); // Collect the processed stream back into an unmodifiable List (Modern Java feature)
    } // End of the sorting method

    // Custom testing method to verify logic without using external libraries like JUnit
    static void runTest(List<String> input, List<String> expected, String testName) { // Accept input, expected output, and a label
        var actual = sortDescending(input); // Execute our sort method and store the result using the concise 'var' keyword
        var status = actual.equals(expected) ? "PASS" : "FAIL"; // Check if actual matches expected; assign PASS or FAIL
        System.out.println(testName + ": " + status); // Print the test label alongside its pass/fail result to the console
    } // End of the custom testing method

    public static void main(String[] args) { // Define the main method where the program execution begins
        
        // Test Case 1: The standard input shown in the user's image
        var imageList = List.of("Meta", "Apple", "Amazon", "Netflix"); // Create an immutable list with the image's data
        var expectedImage = List.of("Netflix", "Meta", "Apple", "Amazon"); // Define the manually verified expected descending output
        runTest(imageList, expectedImage, "Image Test Case"); // Run our custom test method for the basic data

        // Test Case 2: Edge case handling an empty list
        var emptyList = List.<String>of(); // Create a completely empty list of strings
        var expectedEmpty = List.<String>of(); // The expected output of sorting an empty list is still an empty list
        runTest(emptyList, expectedEmpty, "Empty List Test"); // Run the custom test method for the edge case

        // Test Case 3: Large data input to verify performance and stability
        var largeList = IntStream.range(0, 100000) // Generate numbers from 0 to 99,999
                .mapToObj(i -> "Company" + i) // Map each number to a string to simulate 100,000 company names
                .toList(); // Collect these generated strings into a list
        
        var expectedLarge = largeList.stream() // Create a stream from the large list to build our expected baseline
                .sorted(Comparator.reverseOrder()) // Sort this baseline securely to know what the result *should* be
                .toList(); // Collect the baseline into a list
        
        runTest(largeList, expectedLarge, "Large Data Test (100k items)"); // Run the test method to ensure no stack/memory issues occur
    } // End of the main method
} // End of the class