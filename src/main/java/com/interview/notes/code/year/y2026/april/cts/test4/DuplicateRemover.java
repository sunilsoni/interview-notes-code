package com.interview.notes.code.year.y2026.april.cts.test4;

import java.util.Arrays; // Import Arrays utility to handle array operations like streaming and comparing

public class DuplicateRemover { // Define the main class to hold our logic

    public static int[] removeDuplicates(int[] arr) { // Method accepts an integer array and returns a new array
        if (arr == null) return new int[0]; // If the input is null, return an empty array to prevent NullPointerException
        return Arrays.stream(arr) // Convert the primitive int array into an IntStream for processing
                     .distinct() // Automatically filter out duplicate values, keeping only unique ones
                     .toArray(); // Collect the remaining stream elements back into a standard int array and return it
    }

    public static void main(String[] args) { // Main method serves as our test runner (replacing JUnit)
        
        int[] test1 = {1, 2, 3, 3, 4, 4, 5, 6, 7}; // Create a standard test array with sorted duplicates
        int[] expected1 = {1, 2, 3, 4, 5, 6, 7}; // Define what the correct answer should look like
        check("Test 1 (Normal Case)", test1, expected1); // Pass data to helper method to print PASS/FAIL

        int[] test2 = {2, 2, 2, 2, 2}; // Create an array where every single number is identical
        int[] expected2 = {2}; // The correct answer should just be one element
        check("Test 2 (All Duplicates)", test2, expected2); // Verify that it trims down to a single number

        int[] test3 = new int[0]; // Create an empty array to test edge cases
        int[] expected3 = new int[0]; // The result of an empty array should be an empty array
        check("Test 3 (Empty Array)", test3, expected3); // Verify the edge case passes safely

        int[] largeData = new int[1000000]; // Create a massive array with 1 million elements for load testing
        Arrays.fill(largeData, 99); // Fill all 1 million slots with the number 99
        int[] largeExpected = {99}; // The expected output is a single element array containing 99
        check("Test 4 (Large Data - 1M items)", largeData, largeExpected); // Verify performance and correctness on large data
    }

    private static void check(String testName, int[] input, int[] expected) { // Helper method to compare results
        int[] result = removeDuplicates(input); // Call our main logic method with the provided test input
        boolean isPass = Arrays.equals(result, expected); // Compare the actual result array with the expected array
        System.out.println(testName + " -> " + (isPass ? "PASS" : "FAIL")); // Print the test name and the final PASS or FAIL string
    }
}