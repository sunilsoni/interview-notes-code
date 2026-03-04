package com.interview.notes.code.year.y2026.march.common.test5;

import java.util.Arrays;
import java.util.stream.IntStream;

public class DuplicateRemover { // Declares the main public class that contains our program logic.

    // Method that takes an integer array and returns a new array with only unique values.
    public static int[] removeDuplicates(int[] input) { // Declares a public static method accessible without instantiation.
        return Arrays.stream(input) // Converts the raw primitive int array into a sequential IntStream.
                     .distinct() // Uses the Stream API to filter out any duplicate values behind the scenes.
                     .toArray(); // Collects the remaining unique elements from the stream back into a primitive int array.
    } // Closes the removeDuplicates method block.

    public static void main(String[] args) { // The main method serves as the entry point to execute our tests.
        
        // Defines a compact 'record' (modern Java feature) to hold test inputs, expected outputs, and test names cleanly.
        record TestCase(int[] input, int[] expected, String name) {} // Records automatically generate constructors and getters, saving lines of code.

        // Initializes an array of our TestCase records to run through various scenarios.
        TestCase[] tests = { // Opens the array initialization block for our test data.
            new TestCase(new int[]{1, 1, 2, 3, 4, 4, 4, 5, 5, 7, 7}, new int[]{1, 2, 3, 4, 5, 7}, "Provided Case"), // Tests the specific array you provided in the prompt.
            new TestCase(new int[]{}, new int[]{}, "Empty Array Case"), // Edge case: ensures the program doesn't crash on empty input.
            new TestCase(new int[]{9, 9, 9, 9}, new int[]{9}, "All Duplicates Case"), // Edge case: ensures it properly reduces an array of identical numbers.
            new TestCase(IntStream.range(0, 1000000).map(i -> i % 5).toArray(), new int[]{0, 1, 2, 3, 4}, "Large Data Case") // Performance case: creates a 1-million item array to verify large data handling.
        }; // Closes the test array initialization.

        // Loops through every single TestCase object stored in the 'tests' array.
        for (TestCase test : tests) { // Starts a standard enhanced for-loop for iteration.
            int[] result = removeDuplicates(test.input()); // Calls our logic method using the current test's input array.
            boolean passed = Arrays.equals(result, test.expected()); // Compares the actual output array with the expected array to see if they match.
            System.out.println(test.name() + " -> " + (passed ? "PASS" : "FAIL")); // Prints the test name and conditionally prints PASS or FAIL based on the boolean result.
        } // Closes the for-loop block.
        
    } // Closes the main method block.
} // Closes the DuplicateRemover class block.