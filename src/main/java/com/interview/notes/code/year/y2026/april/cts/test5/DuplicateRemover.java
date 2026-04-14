package com.interview.notes.code.year.y2026.april.cts.test5;

import java.util.Arrays; // Import utility to copy arrays and compare test results

public class DuplicateRemover { // Main class containing our logic and tests

    public static int[] removeDuplicates(int[] arr) { // Method accepts an int array and returns an int array
        
        if (arr == null || arr.length == 0) return arr; // If array is null or empty, return it immediately to prevent crashes
        
        int uniqueIndex = 1; // Start index at 1 because the very first element (index 0) is always unique
        
        for (int i = 1; i < arr.length; i++) { // Loop starts at 1 and checks every number until the end of the array
            
            if (arr[i] != arr[i - 1]) { // Compare current number with previous number; if they are different, it's a new unique number
                arr[uniqueIndex] = arr[i]; // Overwrite the duplicate spot near the front with this newly found unique number
                uniqueIndex++; // Increase our unique count tracker so the next unique number goes into the next slot
            }
        }
        
        return Arrays.copyOf(arr, uniqueIndex); // Shrink the array to the exact size of unique numbers and return it
    }

    public static void main(String[] args) { // Main method serves as our testing ground instead of JUnit
        
        int[] test1 = {1, 2, 3, 3, 4, 4, 5, 6, 7}; // Test case 1: Standard sorted array with duplicates
        int[] expected1 = {1, 2, 3, 4, 5, 6, 7}; // Expected output for Test 1
        check("Test 1 (Normal Case)", test1, expected1); // Run the check method

        int[] test2 = {2, 2, 2, 2, 2}; // Test case 2: Array where all numbers are exactly the same
        int[] expected2 = {2}; // Expected output is just a single 2
        check("Test 2 (All Duplicates)", test2, expected2); // Run the check method

        int[] test3 = new int[0]; // Test case 3: An empty array edge case
        int[] expected3 = new int[0]; // Expected output is still an empty array
        check("Test 3 (Empty Array)", test3, expected3); // Run the check method

        int[] largeData = new int[1000000]; // Test case 4: Massive array with 1 million items to test performance
        Arrays.fill(largeData, 99); // Fill all 1 million slots with the number 99
        int[] largeExpected = {99}; // Expected output is a single 99
        check("Test 4 (Large Data - 1M items)", largeData, largeExpected); // Run the check method
    }

    private static void check(String testName, int[] input, int[] expected) { // Helper method to validate and print results
        int[] result = removeDuplicates(input); // Execute the logic on the input array
        boolean isPass = Arrays.equals(result, expected); // Check if the actual result matches the expected result perfectly
        System.out.println(testName + " -> " + (isPass ? "PASS" : "FAIL")); // Print the test name and the result to the console
    }
}