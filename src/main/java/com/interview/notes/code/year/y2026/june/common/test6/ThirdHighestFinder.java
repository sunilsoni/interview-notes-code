package com.interview.notes.code.year.y2026.june.common.test6;

import java.util.Arrays; // Imports the Arrays utility to convert our primitive array into a Stream
import java.util.Comparator; // Imports Comparator to allow sorting in descending (reverse) order

public class ThirdHighestFinder { // Declares the main class to contain our logic and testing methods
    
    public static void main(String[] args) { // The standard entry point method where program execution begins
        int[] arr = {1, 10, 5, 13, 21, 15, 17, 19}; // Initializes the target array provided in the problem statement
        runTest(arr, 17, "Target Case"); // Tests the primary requirement to ensure it outputs 17
        
        runTest(new int[]{5, 5, 5, 10, 10, 2}, 2, "Duplicate Case"); // Tests if duplicates are correctly ignored so we only check distinct values
        runTest(new int[]{100, 50}, -1, "Too Small Case"); // Tests safety when the array has fewer than 3 elements; expects -1
        
        int[] largeData = new int[100000]; // Initializes a large array to test performance and memory handling
        for (int i = 0; i < largeData.length; i++) largeData[i] = i; // Populates the large array with sequential numbers from 0 to 99999
        runTest(largeData, 99997, "Large Data Case"); // Tests the large array; 3rd highest below 99999 is 99997
    } // Closes the main method
    
    static void runTest(int[] data, int expected, String testName) { // Helper method to act as our custom, JUnit-free test runner
        int actual = getThirdHighest(data); // Calls our core logic method and stores the returned integer
        String status = (actual == expected) ? "PASS" : "FAIL"; // Uses a ternary operator to evaluate if the test passed or failed
        System.out.printf("%-18s -> %s (Expected: %d, Got: %d)%n", testName, status, expected, actual); // Prints the formatted result to the console for easy reading
    } // Closes the test runner method

    static int getThirdHighest(int[] arr) { // The core logic method that accepts an array and returns the 3rd highest int
        if (arr == null || arr.length < 3) return -1; // Fast-fail guard clause: returns -1 if the array is null or structurally too small
        
        return Arrays.stream(arr) // Converts the primitive int array into an IntStream pipeline
                     .distinct() // Filters out duplicate numbers so we are strictly ranking unique values
                     .boxed() // Wraps primitive ints into Integer objects so we can apply custom sorting logic
                     .sorted(Comparator.reverseOrder()) // Sorts the stream descending (from largest to smallest)
                     .skip(2) // Skips over the first two elements (the 1st and 2nd highest numbers)
                     .findFirst() // Retrieves the very next element in the stream, which is the 3rd highest
                     .orElse(-1); // Safely returns -1 if the stream ran out of elements (e.g., all duplicates)
    } // Closes the core logic method
} // Closes the class definition