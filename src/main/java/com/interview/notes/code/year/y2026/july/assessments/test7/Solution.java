package com.interview.notes.code.year.y2026.july.assessments.test7;

import java.util.LinkedHashMap; // Required to maintain the insertion order of characters
import java.util.Map; // Required for Map interface interactions
import java.util.function.Function; // Required for Function.identity() in the Stream API
import java.util.stream.Collectors; // Required for Stream reductions like groupingBy and counting

public class Solution { // Main class containing our algorithm and testing logic

    public static char findFirst(String input) { // Method signature requested to find the first unique character
        if (input == null || input.isEmpty()) return (char) 0; // Immediate guard clause to handle edge cases returning '0' early
        
        return input.chars() // Convert the String into an IntStream of character ASCII/Unicode values
            .mapToObj(c -> (char) c) // Box each integer value back into a Character object for mapping
            .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting())) // Group chars into a LinkedHashMap to count frequencies while strictly preserving insertion order
            .entrySet().stream() // Open a new stream on the generated map's entries to filter them
            .filter(entry -> entry.getValue() == 1L) // Filter out any characters that appear more than once (count > 1)
            .map(Map.Entry::getKey) // Extract only the character (the key) from the valid map entries
            .findFirst() // Grab the very first character that survived the filter (guaranteed to be the first non-repeating due to LinkedHashMap)
            .orElse((char) 0); // If the stream is empty (all characters repeated), return '0' as per requirements
    } // Close findFirst method

    public static boolean doTestsPass() { // Method to execute all test cases and validate logic without JUnit
        var inputs = new String[]{"apple", "racecars", "ababdc", "aabbcc", "a"}; // Define input array using Java 21 'var' for brevity, adding edge cases
        var outputs = new char[]{'a', 'e', 'd', 0, 'a'}; // Define expected outputs corresponding directly to the inputs array
        var allPassed = true; // Boolean flag to track the overall success of the test suite
        
        for (var i = 0; i < inputs.length; i++) { // Loop over each index to test the standard cases
            var actual = findFirst(inputs[i]); // Execute the method under test with the current string
            var passed = actual == outputs[i]; // Compare the method's return value against the expected output
            if (passed) { // Condition to check if the specific test passed
                System.out.println("PASS for: " + inputs[i]); // Print a clear success message to the console
            } else { // Condition if the specific test failed
                System.out.println("FAIL for: " + inputs[i] + " (Expected " + outputs[i] + ", Got " + actual + ")"); // Print a detailed failure message
                allPassed = false; // Mark the entire test suite flag as failed
            } // Close if/else block
        } // Close for loop

        var largeInput = "x".repeat(1000000) + "y" + "z".repeat(1000000); // Construct a large string (2 million+ characters) to test performance and memory
        var startTime = System.currentTimeMillis(); // Record the start time before processing the large string
        var largeResult = findFirst(largeInput); // Execute the algorithm against the massive data input
        var executionTime = System.currentTimeMillis() - startTime; // Calculate the total time taken in milliseconds
        
        if (largeResult == 'y') { // Check if the algorithm correctly identified 'y' right in the middle
            System.out.println("PASS Large Data (2M+ chars) in " + executionTime + "ms"); // Print success and performance metrics
        } else { // Condition if the large data test failed
            System.out.println("FAIL Large Data"); // Print failure for the large test
            allPassed = false; // Mark the test suite flag as failed
        } // Close large test if/else block
        
        return allPassed; // Return the final boolean indicating if absolutely all tests succeeded
    } // Close doTestsPass method

    public static void main(String[] args) { // Standard entry point for executing a Java application natively
        if (doTestsPass()) { // Execute tests and evaluate the returned boolean
            System.out.println("All tests pass"); // Print final confirmation if true
        } else { // Handle the case where any test failed
            System.out.println("There are test failures"); // Print final warning if false
        } // Close if/else block
    } // Close main method
} // Close Solution class