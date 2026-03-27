package com.interview.notes.code.year.y2026.march.common.test2;

import java.util.Arrays; // We import this to easily compare arrays in our test method
import java.util.stream.IntStream; // We import IntStream to easily generate sequences of numbers

public class AlternateElements { // Creating the main class to hold our logic

    public static void main(String[] args) { // The main method where the program starts executing
        
        // Test Case 1: The exact data from your screenshot
        int[] input1 = {54, 65, 89, 87, 85, 82, 84, 77, 75, 75, 88, 88, 87}; // Storing the input numbers
        int[] expected1 = {54, 89, 85, 84, 75, 88, 87}; // Storing the correct output numbers
        runTest("Test 1 (Screenshot Data)", input1, expected1); // Running the test to check pass/fail
        
        // Test Case 2: Edge case for an empty array
        int[] input2 = {}; // Creating an empty input array
        int[] expected2 = {}; // The output should also be empty
        runTest("Test 2 (Empty Array)", input2, expected2); // Running the test
        
        // Test Case 3: Edge case for a single element
        int[] input3 = {10}; // Array with just one number
        int[] expected3 = {10}; // The output should just be that same number
        runTest("Test 3 (Single Element)", input3, expected3); // Running the test

        // Test Case 4: Large data input to ensure it handles scale well
        int size = 100000; // Defining a large size of one hundred thousand
        int[] largeInput = new int[size]; // Creating a large input array
        int[] largeExpected = new int[size / 2]; // The expected array will be exactly half the size
        for (int i = 0; i < size; i++) { // Looping through to populate the large arrays
            largeInput[i] = i; // Filling the input with sequential numbers
            if (i % 2 == 0) { // Checking if the current index is an even number
                largeExpected[i / 2] = i; // Placing the even index values into the expected array
            } // Closing the if statement
        } // Closing the loop
        runTest("Test 4 (Large Data)", largeInput, largeExpected); // Running the large data test
    } // Closing the main method

    // The core logic method to solve the problem
    public static int[] getAlternateValues(int[] input) { // Method takes an array and returns an array
        return IntStream.range(0, input.length) // Creates a stream of index numbers from 0 to array length
                .filter(i -> i % 2 == 0) // Filters the stream to only keep the even index numbers
                .map(i -> input[i]) // Grabs the actual array value at that even index
                .toArray(); // Converts the final stream of values back into a standard array
    } // Closing the logic method

    // Simple custom test runner method to avoid using JUnit
    public static void runTest(String testName, int[] input, int[] expected) { // Takes test name, input, and expected result
        int[] actual = getAlternateValues(input); // Calls our logic method to see what it actually produces
        boolean isPass = Arrays.equals(actual, expected); // Checks if the actual output perfectly matches expected
        System.out.println(testName + " -> " + (isPass ? "PASS" : "FAIL")); // Prints the test name and either PASS or FAIL
    } // Closing the test method

} // Closing the class