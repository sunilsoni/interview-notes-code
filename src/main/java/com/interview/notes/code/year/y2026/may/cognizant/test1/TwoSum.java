package com.interview.notes.code.year.y2026.may.cognizant.test1;

import java.util.Arrays; // Imports the Arrays utility class to easily compare and print array contents
import java.util.HashMap; // Imports the HashMap class so we can store and retrieve data instantly
import java.util.Map; // Imports the Map interface to act as a standard reference type for our HashMap

public class TwoSum { // Declares our main application class named TwoSum

    public static int[] find(int[] nums, int target) { // Defines our core method that takes the number array and our goal sum
        Map<Integer, Integer> map = new HashMap<>(); // Creates an empty map to remember numbers we've seen and their exact positions
        for (int i = 0; i < nums.length; i++) { // Starts a loop to go through each number in the array exactly once
            int required = target - nums[i]; // Calculates the exact missing number needed to hit our target sum
            if (map.containsKey(required)) { // Checks our map to see if we already processed that missing number earlier
                return new int[]{map.get(required), i}; // If found, immediately creates and returns a new array holding both indices
            } // Closes the if-statement block
            map.put(nums[i], i); // If not found, stores the current number and its index in the map for future lookups
        } // Closes the for-loop block
        return new int[]{}; // Returns an empty array as a fallback just in case no answer exists (satisfies compiler)
    } // Closes the find method

    public static void test(int[] input, int target, int[] expected, String testName) { // Defines a custom tester method to verify our logic
        int[] result = find(input, target); // Calls our core find method and stores the returned answer
        boolean passed = Arrays.equals(result, expected); // Compares our actual answer with the expected answer to see if they match perfectly
        System.out.println(testName + ": " + (passed ? "PASS" : "FAIL") + " -> " + Arrays.toString(result)); // Prints the test name, status, and actual result to the screen
    } // Closes the test method

    public static void main(String[] args) { // Main entry point method where the program starts running
        
        // Standard Example
        int[] test1 = {2, 7, 11, 15}; // Creates our first test array based on the classic problem example
        test(test1, 9, new int[]{0, 1}, "Test 1 (Standard)"); // Runs the tester expecting positions 0 and 1
        
        // Different Order Example
        int[] test2 = {3, 2, 4}; // Creates a second array where the answer isn't right at the beginning
        test(test2, 6, new int[]{1, 2}, "Test 2 (Middle Match)"); // Runs the tester expecting positions 1 and 2
        
        // Duplicate Numbers Example
        int[] test3 = {3, 3}; // Creates a tricky array using the same number twice to ensure we don't reuse the same index
        test(test3, 6, new int[]{0, 1}, "Test 3 (Duplicates)"); // Runs the tester expecting positions 0 and 1
        
        // Large Data Simulation
        int[] largeData = new int[100000]; // Creates a massive array simulating heavy real-world data loads
        largeData[99998] = 5; // Plants the first part of our answer at the very end of the massive array
        largeData[99999] = 5; // Plants the second part of our answer right next to it
        test(largeData, 10, new int[]{99998, 99999}, "Test 4 (Large Data)"); // Runs the tester to ensure our O(N) map logic handles big data without timing out
        
    } // Closes the main method
} // Closes the TwoSum class