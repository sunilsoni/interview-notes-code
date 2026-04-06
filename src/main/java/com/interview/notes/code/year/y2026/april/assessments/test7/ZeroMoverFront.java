package com.interview.notes.code.year.y2026.april.assessments.test7;

import java.util.Arrays; // Needed to compare arrays in our test method

public class ZeroMoverFront { // Main class for moving zeros to the front

    static void moveZerosToFront(int[] arr) { // Method to modify the array in-place
        int insert = arr.length - 1; // Point to the last index where a non-zero should go

        for (int i = arr.length - 1; i >= 0; i--) { // Loop backwards from the end of the array down to 0
            if (arr[i] != 0) { // Check if the current number is a non-zero
                arr[insert--] = arr[i]; // Put non-zero at insert position, then move insert pointer left
            }
        }

        while (insert >= 0) { // Fill all remaining front positions that were skipped
            arr[insert--] = 0; // Put zero in leftover spots so all zeros end up at the front
        }
    }

    public static void main(String[] args) { // Simple testing via main method
        
        // Test 1: Standard input
        int[] test1 = {0, 1, 0, 3, 12}; // Create standard test array
        int[] expected1 = {0, 0, 1, 3, 12}; // Zeros move to front, 1, 3, 12 keep relative order
        runTest("Test 1 (Standard)", test1, expected1); // Execute test

        // Test 2: Array with no zeros
        int[] test2 = {1, 2, 3}; // Array with only regular numbers
        int[] expected2 = {1, 2, 3}; // Nothing should change
        runTest("Test 2 (No Zeros)", test2, expected2); // Execute test

        // Test 3: Large Data Input
        int[] test3 = new int[100000]; // Massive array of 100,000 elements
        test3[99999] = 5; // Put non-zero at the very end
        test3[50000] = 9; // Put non-zero in the middle
        
        int[] expected3 = new int[100000]; // Create expected result array
        expected3[99999] = 5; // The 5 stays at the very end
        expected3[99998] = 9; // The 9 moves right next to the 5
        // The remaining 99,998 elements at the front will be 0
        
        runTest("Test 3 (Large Data)", test3, expected3); // Execute large test
    }

    private static void runTest(String testName, int[] input, int[] expected) { // Helper to run tests cleanly
        moveZerosToFront(input); // Call our logic method to modify the input array
        boolean passed = Arrays.equals(input, expected); // Verify if the modified array matches the expected array
        
        if (passed) { // Check if successful
            System.out.println(testName + " -> PASS"); // Print success
        } else { // Handle failure
            System.out.println(testName + " -> FAIL"); // Print failure
            System.out.println("Expected: " + Arrays.toString(expected)); // Show what we wanted
            System.out.println("Got:      " + Arrays.toString(input)); // Show what we actually got
        }
    }
}