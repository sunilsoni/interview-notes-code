package com.interview.notes.code.year.y2026.june.common.test9;

import java.util.List; // Imports the List interface for handling collections of data.

public class NumberAnalyzer { // Declares the main public class for our solution.

    // Method to find the greatest number in a list.
    public static int getGreatest(List<Integer> nums) { // Accepts a List of Integers as input.
        if (nums == null || nums.isEmpty()) return -1; // Returns -1 immediately if the list is null or empty to prevent errors.
        return nums.stream() // Converts the List into a sequential Stream for processing.
                   .mapToInt(Integer::intValue) // Unboxes Integers to primitive ints for better performance.
                   .max() // Finds the maximum primitive value in the stream.
                   .orElse(-1); // Safely returns -1 if the stream is somehow empty.
    } // Closes the getGreatest method.

    // Method to calculate the sum of all even numbers.
    public static long getEvenSum(List<Integer> nums) { // Returns a 'long' to prevent overflow with very large data sums.
        if (nums == null || nums.isEmpty()) return 0; // Returns 0 immediately if there is no data to sum.
        return nums.stream() // Converts the List into a sequential Stream.
                   .filter(n -> n % 2 == 0) // Keeps only the numbers that leave no remainder when divided by 2 (even numbers).
                   .mapToLong(Integer::longValue) // Converts the remaining numbers to primitive 'long' types to handle huge sums safely.
                   .sum(); // Adds all the filtered long values together and returns the total.
    } // Closes the getEvenSum method.

    // Main method to act as our custom testing framework.
    public static void main(String[] args) { // Entry point of the application.

        // Initializing our test cases using 'var' for minimal syntax and List.of() for immutable lists.
        var tests = List.of( // Creates an unmodifiable list of our TestCases.
            new TestCase("Standard 1-10", List.of(1, 2, 8, 6, 5, 4, 7, 9, 10, 3), 10, 30), // The primary requirement scenario.
            new TestCase("All Odds", List.of(1, 3, 5, 7, 9), 9, 0), // Edge case: No even numbers exist to sum.
            new TestCase("Empty List", List.of(), -1, 0), // Edge case: Testing empty input handling.
            new TestCase("Large Data", List.of(2000000000, 2000000000), 2000000000, 4000000000L) // Edge case: Large inputs testing 'long' capacity.
        ); // Ends the test case list initialization.

        // Loop through and execute each test case automatically.
        for (var t : tests) { // Iterates over every TestCase object in the tests list.
            var actualMax = getGreatest(t.input()); // Executes our max logic with the test input.
            var actualSum = getEvenSum(t.input()); // Executes our sum logic with the test input.

            var maxPass = actualMax == t.expectedMax(); // Checks if our calculated max matches the expected max.
            var sumPass = actualSum == t.expectedSum(); // Checks if our calculated sum matches the expected sum.

            if (maxPass && sumPass) { // If both operations return the correct expected results...
                System.out.println(t.name() + " -> PASS"); // ...print a PASS message to the console.
            } else { // If either operation fails...
                System.out.println(t.name() + " -> FAIL (Max: " + actualMax + ", Sum: " + actualSum + ")"); // ...print a FAIL message with details for debugging.
            } // Ends the condition check.
        } // Ends the test execution loop.
    } // Ends the main method.

    // Using a Java Record to create a tiny, immutable class for holding test data without boilerplate getters/setters.
    record TestCase(String name, List<Integer> input, int expectedMax, long expectedSum) {} // Defines the structure of a single test case.
} // Ends the class.