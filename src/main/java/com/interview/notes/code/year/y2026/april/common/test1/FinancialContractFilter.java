package com.interview.notes.code.year.y2026.april.common.test1;

import java.util.Arrays; // Imports Arrays utility to convert our string array into a Java 8 Stream
import java.util.stream.Collectors; // Imports Collectors to join our Stream results back into a single String

public class FinancialContractFilter { // Defines the main class for our filtering program

    public static String filterContracts(String input) { // Defines the method that takes the input string and returns the filtered result
        
        String[] parts = input.split(","); // Splits the raw comma-separated input string into an array of strings
        
        if (parts.length < 2) return "None"; // Edge case check: if the array has less than 2 items, there are no contracts to filter, return "None"
        
        String targetMonth = parts[parts.length - 1].trim(); // Extracts the last element as the target month and trims spaces
        
        String regex = ".*" + targetMonth + "\\d+"; // Builds a simple regex: matches any prefix, then the target month, then 1 or more digits at the end
        
        String result = Arrays.stream(parts, 0, parts.length - 1) // Creates a Java 8 Stream from the array, specifically excluding the last element (target month)
                .map(String::trim) // Maps over each contract and trims whitespace to ensure clean data processing
                .filter(contract -> contract.matches(regex)) // Filters the stream, keeping only contracts that match our simple regex logic
                .collect(Collectors.joining(",")); // Collects the filtered contracts and joins them into a single comma-separated string
                
        return result.isEmpty() ? "None" : result; // Ternary operator: returns "None" if the stream result is empty, otherwise returns the matched string
    }

    public static void main(String[] args) { // Main method serves as our built-in testing framework instead of JUnit
        
        // Test Case 1: Provided Example 1
        String input1 = "GEM6,CZ22,USH2,TYFZ2021,3Z23,Z"; // Defines input string from the first example
        String expected1 = "CZ22,TYFZ2021,3Z23"; // Defines the expected output for the first example
        runTest("Example 1", input1, expected1); // Calls our custom test runner method
        
        // Test Case 2: Provided Example 2
        String input2 = "GEM6,CZ22,USH2,TYFZ2021,3Z23,A"; // Defines input string from the second example (no matches)
        String expected2 = "None"; // Defines the expected output ("None")
        runTest("Example 2", input2, expected2); // Calls our custom test runner method
        
        // Test Case 3: Edge Case with spacing
        String input3 = "  AAB1 , BXC2022 ,  CBB4, B  "; // Defines input with messy whitespace to test our .trim() logic
        String expected3 = "AAB1,CBB4"; // Expected output should be clean and correctly filtered
        runTest("Whitespace Edge Case", input3, expected3); // Calls our custom test runner method
        
        // Test Case 4: Large Data Input
        StringBuilder largeDataBuilder = new StringBuilder(); // Uses StringBuilder for efficient string concatenation for large data
        for (int i = 0; i < 10000; i++) { // Loops 10,000 times to generate a massive dataset
            largeDataBuilder.append("XYZM22,"); // Appends a dummy contract that won't match the target
            largeDataBuilder.append("ABCZ2024,"); // Appends a dummy contract that WILL match the target
        } // Ends the data generation loop
        largeDataBuilder.append("Z"); // Appends the target month 'Z' at the very end of the massive string
        
        long startTime = System.currentTimeMillis(); // Records the start time to measure performance
        String largeDataResult = filterContracts(largeDataBuilder.toString()); // Runs our filtering method against the massive dataset
        long endTime = System.currentTimeMillis(); // Records the end time after processing
        
        // Verifies if the large data was handled correctly (it should find 10000 matches)
        if (largeDataResult.split(",").length == 10000) { // Checks if exactly 10,000 valid contracts were extracted
            System.out.println("PASS - Large Data Case (Processed in " + (endTime - startTime) + "ms)"); // Prints success message with execution time
        } else { // Fallback if the large data test fails
            System.out.println("FAIL - Large Data Case"); // Prints failure message
        } // Ends the if-else block
    }

    // Helper method to execute tests and print PASS/FAIL status without requiring JUnit
    private static void runTest(String testName, String input, String expected) { // Defines the helper method signature
        String actual = filterContracts(input); // Executes the logic to get the actual result
        if (actual.equals(expected)) { // Compares the actual result with the expected result
            System.out.println("PASS - " + testName); // Prints PASS if they match exactly
        } else { // Fallback if they do not match
            System.out.println("FAIL - " + testName + " | Expected: [" + expected + "] but got: [" + actual + "]"); // Prints FAIL along with debugging context
        } // Ends the if-else block
    } // Ends the helper method
} // Ends the class