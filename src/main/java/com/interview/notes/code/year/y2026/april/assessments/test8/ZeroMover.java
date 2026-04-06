package com.interview.notes.code.year.y2026.april.assessments.test8;

import java.util.Arrays; // Imports the Arrays utility class to help us print and compare array contents
import java.util.stream.IntStream; // Imports the IntStream class to utilize Java 8 Stream API for integers

public class ZeroMover { // Declares the main public class that will contain our logic and tests

    public static int[] moveZeros(int[] arr) { // Defines a public method that accepts an array and returns the modified array
        return IntStream.concat( // Uses IntStream.concat to join two separate streams end-to-end
            Arrays.stream(arr).filter(n -> n != 0), // Converts array to stream and filters OUT zeros (keeps only non-zeros)
            Arrays.stream(arr).filter(n -> n == 0)  // Converts array to stream and filters OUT non-zeros (keeps only zeros)
        ).toArray(); // Converts the newly combined stream back into a standard integer array and returns it
    }

    public static void main(String[] args) { // The main method serves as our simple testing framework instead of JUnit
        
        // Test Case 1: The standard example provided in your requirements
        int[] test1 = {0, 1, 3, 4, 0, 5}; // Initializes the first input array
        int[] expected1 = {1, 3, 4, 5, 0, 0}; // Initializes the expected result for test 1
        runTest("Test 1 (Standard)", test1, expected1); // Calls our custom test runner method

        // Test Case 2: Array with no zeros
        int[] test2 = {1, 2, 3}; // Initializes an array that doesn't need modification
        int[] expected2 = {1, 2, 3}; // The expected result is identical to the input
        runTest("Test 2 (No Zeros)", test2, expected2); // Calls our custom test runner method

        // Test Case 3: Array with only zeros
        int[] test3 = {0, 0, 0}; // Initializes an array full of zeros
        int[] expected3 = {0, 0, 0}; // The expected result is identical to the input
        runTest("Test 3 (All Zeros)", test3, expected3); // Calls our custom test runner method

        // Test Case 4: Large data input
        int[] test4 = new int[100000]; // Creates a large array of 100,000 elements (defaults to all zeros)
        test4[0] = 5; // Sets the first element to 5
        test4[50000] = 9; // Sets a middle element to 9
        
        int[] expected4 = new int[100000]; // Creates the expected large array
        expected4[0] = 5; // The 5 should be moved to the front
        expected4[1] = 9; // The 9 should follow right after the 5, with 99,998 zeros behind it
        runTest("Test 4 (Large Data)", test4, expected4); // Calls our custom test runner method
    }

    private static void runTest(String testName, int[] input, int[] expected) { // Helper method to execute tests and print PASS/FAIL
        var result = moveZeros(input); // Uses Java 10+ 'var' keyword for cleaner syntax; calls our main logic method
        boolean passed = Arrays.equals(result, expected); // Compares the actual result with the expected array to check if they match
        
        if (passed) { // Checks if the test passed
            System.out.println(testName + " -> PASS"); // Prints a success message to the console
        } else { // If the arrays do not match exactly
            System.out.println(testName + " -> FAIL"); // Prints a failure message
            System.out.println("Expected: " + Arrays.toString(expected)); // Prints what the array SHOULD look like
            System.out.println("Got: " + Arrays.toString(result)); // Prints what our method ACTUALLY returned
        }
    }
}