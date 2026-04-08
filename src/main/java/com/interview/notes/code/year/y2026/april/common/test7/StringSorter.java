package com.interview.notes.code.year.y2026.april.common.test7;

import java.util.Arrays; // Imports the standard Arrays utility class so we can use its built-in sorting and equality checks.

public class StringSorter { // Declares the main public class that will encapsulate our application logic.
    
    public static void main(String[] args) { // Defines the main method, which is the required entry point for any standard Java application.
        
        // Test Case 1: The specific array provided in your prompt.
        var arr1 = new String[]{"str1", "abc", "123", "xyz"}; // Declares and initializes the input string array using 'var' to minimize code words.
        var exp1 = new String[]{"123", "abc", "str1", "xyz"}; // Defines the exact expected output after lexicographical sorting (numbers first, then letters).
        runTest("Test 1 (Provided)", arr1, exp1); // Calls our custom helper method to execute the sort and check if the result matches the expectation.
        
        // Test Case 2: Simulating a larger data set to ensure performance constraints are met.
        var arr2 = new String[]{"zebra", "apple", "mango", "banana", "kiwi", "grape", "orange", "pear"}; // Initializes a slightly larger array of unordered words.
        var exp2 = new String[]{"apple", "banana", "grape", "kiwi", "mango", "orange", "pear", "zebra"}; // Defines the perfectly sorted alphabetical outcome.
        runTest("Test 2 (Larger Data)", arr2, exp2); // Calls the helper method again to verify the larger dataset sorts correctly.
        
    } // Closes the main method block.

    static void runTest(String testName, String[] input, String[] expected) { // Defines a reusable helper method to process sorting and handle PASS/FAIL logic.
        
        Arrays.sort(input); // Executes Java's highly-optimized Timsort algorithm to sort the 'input' array directly in-place.
        
        var isMatch = Arrays.equals(input, expected); // Compares the newly sorted array element-by-element with the 'expected' array, storing the boolean result.
        
        var resultText = isMatch ? "PASS" : "FAIL"; // Uses a ternary operator to assign the string "PASS" if true, or "FAIL" if false, keeping code concise.
        
        System.out.println(testName + " Result: " + resultText); // Prints the name of the test alongside its final PASS/FAIL status to the console.
        
    } // Closes the runTest helper method block.
    
} // Closes the StringSorter class definition.