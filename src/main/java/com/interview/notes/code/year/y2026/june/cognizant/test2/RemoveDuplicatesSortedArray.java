package com.interview.notes.code.year.y2026.june.cognizant.test2;

import java.util.Arrays; // Import Arrays utility to print and compare array data

public class RemoveDuplicatesSortedArray { // Main class to keep the full program

    static int removeDuplicates(int[] arr) { // Method removes duplicates and returns unique element count

        if (arr == null || arr.length == 0) return 0; // If array is null or empty, there is no unique element

        int write = 1; // First element is always unique, so next unique value should go at index 1

        for (int read = 1; read < arr.length; read++) { // Start reading from second element because first is already unique

            if (arr[read] != arr[write - 1]) { // Check if current value is different from last stored unique value

                arr[write] = arr[read]; // Store the new unique value at the write position

                write++; // Move write pointer to next position for future unique value
            }
        }

        return write; // Return count of unique elements
    }

    static int[] uniquePart(int[] arr, int len) { // Helper method to return only unique part of array

        return Arrays.copyOf(arr, len); // Copy only first len elements because they contain unique values
    }

    static void test(String name, int[] input, int[] expected) { // Simple test method without JUnit

        int len = removeDuplicates(input); // Remove duplicates and get unique count

        int[] actual = uniquePart(input, len); // Extract only unique part from modified input array

        boolean pass = Arrays.equals(actual, expected); // Compare actual output with expected output

        System.out.println(name + " : " + (pass ? "PASS" : "FAIL")); // Print test result

        System.out.println("Expected: " + Arrays.toString(expected)); // Print expected unique values

        System.out.println("Actual  : " + Arrays.toString(actual)); // Print actual unique values

        System.out.println(); // Print empty line for clean output
    }

    public static void main(String[] args) { // Program execution starts here

        test("Normal case", // Test name for normal duplicate values
                new int[]{1, 1, 2, 3, 3, 4, 5, 6, 6, 6, 7}, // Input sorted array with duplicates
                new int[]{1, 2, 3, 4, 5, 6, 7}); // Expected unique values

        test("No duplicates", // Test name for already unique array
                new int[]{1, 2, 3, 4, 5}, // Input array without duplicates
                new int[]{1, 2, 3, 4, 5}); // Expected same array

        test("All duplicates", // Test name for all same values
                new int[]{7, 7, 7, 7}, // Input array where every value is duplicate
                new int[]{7}); // Expected only one value

        test("Empty array", // Test name for empty array
                new int[]{}, // Input empty array
                new int[]{}); // Expected empty output

        test("Single element", // Test name for one element
                new int[]{10}, // Input with one value
                new int[]{10}); // Expected same one value

        int[] large = new int[100000]; // Create large input array to test performance

        Arrays.fill(large, 5); // Fill all values with same number to create many duplicates

        test("Large data case", // Test name for large input
                large, // Large input array
                new int[]{5}); // Expected only one unique value
    }
}