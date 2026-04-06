package com.interview.notes.code.year.y2026.april.assessments.test8;

import java.util.Arrays; // Needed strictly for printing array contents in our tests

public class ZeroMoverOptimized { // Main class containing our optimized logic

    public static void moveZeros(int[] arr) { // Method modifies the array in-place, returning nothing (void)
        int insertPos = 0; // Pointer tracking exactly where the next non-zero number should be placed
        
        for (int i = 0; i < arr.length; i++) { // Loop through every element in the array exactly once
            if (arr[i] != 0) { // Check if the currently inspected element is a non-zero number
                
                int temp = arr[insertPos]; // Temporarily save whatever is currently at the target insertion position
                arr[insertPos] = arr[i]; // Move our discovered non-zero number to the correct front position
                arr[i] = temp; // Put the temporary value (which is guaranteed to be a zero) where the non-zero used to be
                
                insertPos++; // Move our insertion pointer forward by one to prepare for the next non-zero
            }
        }
    }

    public static void main(String[] args) { // Simple main method for testing, avoiding JUnit
        
        // Test 1: Standard
        int[] test1 = {0, 1, 3, 4, 0, 5}; // Input array
        int[] expected1 = {1, 3, 4, 5, 0, 0}; // Expected output
        runTest("Test 1 (Standard)", test1, expected1); // Execute test

        // Test 2: No Zeros
        int[] test2 = {1, 2, 3}; // Input with no zeros
        int[] expected2 = {1, 2, 3}; // Expected output (identical)
        runTest("Test 2 (No Zeros)", test2, expected2); // Execute test

        // Test 3: All Zeros
        int[] test3 = {0, 0, 0}; // Input with all zeros
        int[] expected3 = {0, 0, 0}; // Expected output (identical)
        runTest("Test 3 (All Zeros)", test3, expected3); // Execute test

        // Test 4: Large Data
        int[] test4 = new int[100000]; // Massive array default initialized to zeros
        test4[0] = 5; // Set first element
        test4[50000] = 9; // Set middle element
        
        int[] expected4 = new int[100000]; // Create expected result array
        expected4[0] = 5; // 5 moves to front
        expected4[1] = 9; // 9 moves to second position
        runTest("Test 4 (Large Data)", test4, expected4); // Execute test
    }

    private static void runTest(String testName, int[] input, int[] expected) { // Helper to run assertions
        int[] originalInput = Arrays.copyOf(input, input.length); // Keep a copy for logging purposes in case of failure
        
        moveZeros(input); // Execute our optimized in-place method
        
        boolean passed = Arrays.equals(input, expected); // Compare the now-modified input against expected
        
        if (passed) { // Check result
            System.out.println(testName + " -> PASS"); // Print success
        } else { // Handle failure
            System.out.println(testName + " -> FAIL"); // Print failure
            System.out.println("Expected: " + Arrays.toString(expected)); // Show expected state
            System.out.println("Got: " + Arrays.toString(input)); // Show actual state
        }
    }
}