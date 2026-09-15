package com.interview.notes.code.year.y2026.september.common.test4;

import java.util.Collection; // Imports the Collection interface used for our input parameter
import java.util.List; // Imports the List interface for creating test cases
import java.util.Objects; // Imports Objects class to safely compare expected and actual results
import java.util.stream.IntStream; // Imports IntStream to generate large data sets for testing

public class LargestNumberFinder { // Defines the main class for our application

    // --- BRUTE FORCE SOLUTION ---
    public static Integer findMaxBruteForce(Collection<Integer> numbers) { // Method accepts a collection and returns the max integer
        if (numbers == null || numbers.isEmpty()) { // Checks if the input is null or empty to prevent errors
            return null; // Returns null because there is no maximum in an empty collection
        } // Ends the if condition
        var max = Integer.MIN_VALUE; // Initializes max to the lowest possible integer so any number will be larger
        for (var num : numbers) { // Loops through every single number in the collection
            if (num > max) { // Compares the current number against our highest found so far
                max = num; // Updates the max variable if the current number is strictly larger
            } // Ends the comparison block
        } // Ends the loop
        return max; // Returns the final largest number found after checking everything
    } // Ends the brute force method

    // --- BEST SOLUTION (Stream API) ---
    public static Integer findMaxBest(Collection<Integer> numbers) { // Method accepts a collection and returns the max integer
        if (numbers == null || numbers.isEmpty()) { // Validates input to avoid NullPointerException
            return null; // Returns null for empty inputs
        } // Ends the if condition
        return numbers.stream() // Converts the collection into a stream to process data declaratively
                .max(Integer::compareTo) // Uses built-in max function comparing elements naturally
                .orElse(null); // Safely extracts the value or returns null if somehow empty
    } // Ends the best solution method

    // --- TESTING SYSTEM ---
    static void main(String[] args) { // Main method serves as the entry point for executing tests
        
        System.out.println("Testing Brute Force Solution:"); // Prints header for brute force tests
        runAllTests(true); // Calls the test runner telling it to use the brute force logic
        
        System.out.println("\nTesting Best Solution (Streams):"); // Prints header for the best solution tests
        runAllTests(false); // Calls the test runner telling it to use the optimal logic
        
    } // Ends the main method

    private static void runAllTests(boolean useBruteForce) { // Helper method to run test suite against either algorithm
        
        var normalList = List.of(1, 5, 3, 9, 2); // Creates a basic list of positive numbers
        check("Normal positive numbers", 9, normalList, useBruteForce); // Tests standard behavior expects 9
        
        var negativeList = List.of(-10, -5, -30, -1); // Creates a list of purely negative numbers
        check("Negative numbers", -1, negativeList, useBruteForce); // Tests negative logic expects -1
        
        var mixedList = List.of(-5, 0, 10, 50, -100); // Creates a list with mixed signs
        check("Mixed numbers", 50, mixedList, useBruteForce); // Tests mixed logic expects 50
        
        var emptyList = List.<Integer>of(); // Creates an empty list to test edge cases
        check("Empty collection", null, emptyList, useBruteForce); // Tests empty list expects null
        
        var singleElement = List.of(42); // Creates a list with exactly one element
        check("Single element", 42, singleElement, useBruteForce); // Tests single element expects 42
        
        var largeData = IntStream.rangeClosed(1, 1000000).boxed().toList(); // Generates 1 million sequential numbers efficiently
        check("Large dataset (1M items)", 1000000, largeData, useBruteForce); // Tests performance and correctness on massive data
        
    } // Ends the test runner method

    private static void check(String testName, Integer expected, Collection<Integer> data, boolean useBruteForce) { // Utility to validate and print PASS/FAIL
        var actual = useBruteForce ? findMaxBruteForce(data) : findMaxBest(data); // Chooses which algorithm to run based on the flag
        var passed = Objects.equals(expected, actual); // Compares actual output with expected output safely handling nulls
        var status = passed ? "PASS" : "FAIL"; // Assigns 'PASS' if they match, 'FAIL' otherwise
        System.out.println(status + " | " + testName); // Prints the final test result and the test name to the console
    } // Ends the check method

} // Ends the class declaration