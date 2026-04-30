package com.interview.notes.code.year.y2026.april.common.test3;

import java.util.Arrays; // Needed to convert the String array into a Stream for processing
import java.util.stream.Collectors; // Needed to join the filtered Stream elements back into a single String

public class Main { // Defines the main class for execution

    /*
     * Implement method/function with name 'solve' below.
     * The function accepts following as parameters.
     *  1. s is of type String.
     * return String.
     */
    public static String solve(String s) { // Method signature exactly as required by the platform
        
        String[] parts = s.split(","); // Splits the comma-separated input into an array to isolate parts
        
        if (parts.length < 2) return "None"; // Safety check: returns "None" if input lacks contracts to check
        
        String targetMonth = parts[parts.length - 1].trim(); // Extracts the very last element as our target filter month
        
        String regex = ".*" + targetMonth + "\\d+"; // Simple regex: matches anything, then the target month, then digits (the year) at the end
        
        String result = Arrays.stream(parts, 0, parts.length - 1) // Creates a Stream from the array, intentionally skipping the last element (the target month)
                .map(String::trim) // Cleans up any accidental whitespace around each contract string
                .filter(contract -> contract.matches(regex)) // Filters the stream to keep only items matching our month regex
                .collect(Collectors.joining(",")); // Joins all matching items back into a single comma-separated string
                
        return result.isEmpty() ? "None" : result; // Ternary check: if no matches were joined, return "None", otherwise return the matches
    }

    public static void main(String[] args) { // Simple main method for testing without using JUnit
        
        // Test Case 2 (The one that failed)
        String testInput2 = "GEM6,CZ22,USH2,TYFZ2021,3Z23,A"; // Defines the exact input from the failing test case
        String expectedResult2 = "None"; // Defines the expected output for this case
        String actualResult2 = solve(testInput2); // Executes our solve method
        
        // Checks if actual result matches expected and prints PASS or FAIL
        if (expectedResult2.equals(actualResult2)) { // Condition to check for success
            System.out.println("PASS - Test Case 2"); // Prints success message
        } else { // Fallback if test fails
            System.out.println("FAIL - Test Case 2 | Got: " + actualResult2); // Prints failure message with actual output
        } // Ends condition
        
        // Test Case 1 (From previous context to ensure nothing broke)
        String testInput1 = "GEM6,CZ22,USH2,TYFZ2021,3Z23,Z"; // Defines input from example 1
        String expectedResult1 = "CZ22,TYFZ2021,3Z23"; // Defines expected output for example 1
        String actualResult1 = solve(testInput1); // Executes our solve method
        
        if (expectedResult1.equals(actualResult1)) { // Condition to check for success
            System.out.println("PASS - Test Case 1"); // Prints success message
        } else { // Fallback if test fails
            System.out.println("FAIL - Test Case 1 | Got: " + actualResult1); // Prints failure message with actual output
        } // Ends condition
        
    } // Ends main method
} // Ends class