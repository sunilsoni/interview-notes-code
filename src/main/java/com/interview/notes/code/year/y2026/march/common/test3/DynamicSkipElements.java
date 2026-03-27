package com.interview.notes.code.year.y2026.march.common.test3;

import java.util.Arrays; // Imported to easily compare our actual output arrays with expected arrays
import java.util.stream.IntStream; // Imported to generate a sequence of numbers (indices) easily

public class DynamicSkipElements { // The main class to encapsulate our logic

    public static void main(String[] args) { // The starting point of our Java application
        
        // The original input array provided in the screenshot
        int[] input = {54, 65, 89, 87, 85, 82, 84, 77, 75, 75, 88, 88, 87}; // Declaring the data

        // Test Case 1: Skip = 3 (From the first line of your screenshot)
        int skip3 = 3; // Declaring the dynamic variable for skipping 3 elements
        int[] expectedSkip3 = {54, 85, 75, 87}; // The expected output when skipping 3
        runTest("Test Skip=3", input, skip3, expectedSkip3); // Running the test to check pass/fail

        // Test Case 2: Skip = 2 (From the second line of your screenshot discussion)
        int skip2 = 2; // Declaring the dynamic variable for skipping 2 elements
        int[] expectedSkip2 = {54, 87, 84, 75, 87}; // The expected output when skipping 2
        runTest("Test Skip=2", input, skip2, expectedSkip2); // Running the test to check pass/fail
        
        // Test Case 3: Skip = 0 (Edge case: shouldn't skip anything, should return identical array)
        int skip0 = 0; // Declaring dynamic variable to skip 0 elements
        runTest("Test Skip=0", input, skip0, input); // Expected output is the exact same input array

        // Test Case 4: Large data input to ensure the stream handles massive scale
        int size = 100000; // Defining a massive array size of one hundred thousand
        int skipLarge = 4; // We will skip 4 elements at a time for this test
        int step = skipLarge + 1; // The mathematical step size (every 5th element)
        int[] largeInput = new int[size]; // Creating the massive input array
        int[] largeExpected = new int[(size + step - 1) / step]; // Calculating exact size needed for expected array
        for (int i = 0; i < size; i++) { // Looping through to populate the large data
            largeInput[i] = i; // Filling the input with sequential numbers
            if (i % step == 0) { // Checking if this index is one we want to keep
                largeExpected[i / step] = i; // Placing the kept value into our expected array
            } // Closing if statement
        } // Closing loop
        runTest("Test Large Data (Skip=4)", largeInput, skipLarge, largeExpected); // Executing the large data test
    } // Closing main method

    // The core, dynamic logic method using Java 8/21 Streams
    public static int[] getElementsWithSkip(int[] input, int skip) { // Accepts the array and the dynamic skip variable
        int stepSize = skip + 1; // We calculate the mathematical step size (e.g., skip 3 means every 4th element)
        
        return IntStream.range(0, input.length) // Generates a stream of numbers from 0 up to the length of the array
                .filter(i -> i % stepSize == 0) // Filters indices, keeping only those that divide evenly by our step size
                .map(i -> input[i]) // Maps the surviving even/step indices back to their actual array values
                .toArray(); // Collects the final stream of values and packages them into a standard integer array
    } // Closing the logic method

    // Simple custom testing method to print PASS/FAIL without needing JUnit framework
    public static void runTest(String testName, int[] input, int skip, int[] expected) { // Accepts test parameters
        int[] actual = getElementsWithSkip(input, skip); // Calls our main logic method to get the actual result
        boolean isPass = Arrays.equals(actual, expected); // Compares actual result to expected result
        System.out.println(testName + " -> " + (isPass ? "PASS" : "FAIL")); // Prints the result cleanly to the console
    } // Closing the test method

} // Closing the class