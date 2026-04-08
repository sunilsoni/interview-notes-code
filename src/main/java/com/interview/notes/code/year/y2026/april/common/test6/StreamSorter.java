package com.interview.notes.code.year.y2026.april.common.test6;

import java.util.Arrays; // Imports the standard Arrays utility class to create streams and check equality.

public class StreamSorter { // Declares the main public class that holds our application logic.
    
    public static void main(String[] args) { // The main method serves as the standard entry point for running our Java application.
        
        // Test Case 1: The original array from your prompt.
        var arr1 = new String[]{"str1", "Str1", "abc", "123", "xyz"}; // Declares the input array using Java's 'var' keyword to minimize code words.
        var exp1 = new String[]{"123", "abc", "str1", "xyz"}; // Defines the correct, perfectly sorted outcome to compare our result against.
        runTest("Test 1 (Provided)", arr1, exp1); // Calls our custom helper method to execute the stream sort and verify the result.
        
        // Test Case 2: A larger dataset to prove the stream handles more data effectively.
        var arr2 = new String[]{"zebra", "apple", "mango", "banana", "kiwi", "grape"}; // Initializes a larger array of random, unsorted words.
        var exp2 = new String[]{"apple", "banana", "grape", "kiwi", "mango", "zebra"}; // Defines the expected alphabetical order for the larger dataset.
        runTest("Test 2 (Larger Data)", arr2, exp2); // Calls the test method again to verify the stream handles multiple elements properly.
        
    } // Closes the main method block.

    static void runTest(String testName, String[] input, String[] expected) { // A helper method to run tests and output PASS or FAIL.
        
        // Here is the Java 8 Stream API implementation you requested.
        var sortedArray = Arrays.stream(input) // Step 1: Converts the input String array into a sequential Stream of Strings so we can process it.
                                .sorted() // Step 2: An intermediate operation that sorts the flowing stream elements in their natural, alphabetical order.
                                .toArray(String[]::new); // Step 3: A terminal operation that collects the sorted stream back into a brand new String array.
        
        var isMatch = Arrays.equals(sortedArray, expected); // Compares our newly sorted stream output against the expected array to see if they match exactly.
        
        var resultText = isMatch ? "PASS" : "FAIL"; // Uses a simple ternary operator to set the text to "PASS" if true, or "FAIL" if false.
        
        System.out.println(testName + " Result: " + resultText); // Prints the test name and the final PASS/FAIL string to the console output.
        
    } // Closes the helper method block.
    
} // Closes the StreamSorter class block.