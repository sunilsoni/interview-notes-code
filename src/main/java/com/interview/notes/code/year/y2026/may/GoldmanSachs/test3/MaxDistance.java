package com.interview.notes.code.year.y2026.may.GoldmanSachs.test3;

import java.util.stream.Collectors; // Imports Collectors to group our array elements together
import java.util.stream.IntStream; // Imports IntStream to easily loop over array indices without manual loops

public class MaxDistance { // Main class container for our program logic

    public static void main(String[] args) { // Standard entry point to test the application

        // Uses 'var' (modern feature) to declare our array of test cases cleanly
        var tests = new TestCase[] { // Opening the array definition
            new TestCase(new int[]{1, 9, 2, 4, 3, 2, 1}, 6), // Primary test: '1' is at index 0 and 6, diff is 6
            new TestCase(new int[]{1, 2, 3, 4}, 0), // Edge case: all unique numbers, max distance is 0
            new TestCase(new int[]{5, 5, 5, 5}, 3), // Edge case: all identical numbers, first and last diff is 3
            new TestCase(new int[]{}, 0), // Edge case: empty array should safely return 0
            new TestCase(new int[]{10}, 0) // Edge case: single element should safely return 0
        }; // Closing the array definition

        for (var test : tests) { // Loop over each test case one by one
            int result = findMaxDist(test.arr()); // Call our method to calculate the actual distance
            boolean isPass = result == test.expected(); // Check if the calculation matches our expectation
            System.out.println((isPass ? "PASS" : "FAIL") + " | Expected: " + test.expected() + ", Got: " + result); // Print results
        } // End of test case loop

        // Large Data Test Case creation
        int[] largeArr = new int[100000]; // Create an array with 100,000 elements to prove performance
        largeArr[0] = 99; // Put 99 at the very start of the large array
        largeArr[99999] = 99; // Put 99 at the very end of the large array
        int largeResult = findMaxDist(largeArr); // Calculate distance, testing speed and memory
        System.out.println((largeResult == 99999 ? "PASS" : "FAIL") + " | Large Data Test (100k elements)"); // Output large test result
    } // End of main method

    static int findMaxDist(int[] arr) { // Method to find the maximum distance using Stream API
        if (arr == null || arr.length == 0) return 0; // Quick safety check to prevent crashes on empty inputs

        return IntStream.range(0, arr.length).boxed() // Create a stream of index numbers (0, 1, 2...) up to array length
            .collect(Collectors.groupingBy(i -> arr[i])) // Group those index numbers by the actual value in the array at that index
            .values().stream() // Get the grouped lists of indices and turn them back into a stream
            .mapToInt(indices -> indices.getLast() - indices.getFirst()) // Java 21 feature: subtract first index from last index
            .max() // Find the highest difference value among all the groups
            .orElse(0); // If the stream is somehow empty, safely return 0 as a fallback
    } // End of findMaxDist method

    // Uses Java Records (modern feature) to cleanly define our test case structure with minimal words
    record TestCase(int[] arr, int expected) {}
} // End of class