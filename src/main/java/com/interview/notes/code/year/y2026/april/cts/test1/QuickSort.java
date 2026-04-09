package com.interview.notes.code.year.y2026.april.cts.test1;

import java.util.Arrays; // We need this to copy arrays and compare results for our PASS/FAIL tests.
import java.util.Random; // We need this to generate large randomized data inputs for stress testing.
import java.util.stream.Stream; // We need this to utilize the Java 8 Stream API to process our test cases.

public class QuickSort { // Main class declaration to encapsulate our sorting and testing logic.

    public static void sort(int[] arr, int low, int high) { // Main recursive method taking the array and the range to sort.
        if (low < high) { // Base condition: we only proceed if there are at least two elements to sort.
            var pivotIndex = partition(arr, low, high); // Find the correct sorted position of the pivot element using Java 10 'var'.
            sort(arr, low, pivotIndex - 1); // Recursively call sort on the left sub-array (elements smaller than pivot).
            sort(arr, pivotIndex + 1, high); // Recursively call sort on the right sub-array (elements larger than pivot).
        } // End of our recursive base condition block.
    } // End of the sort method.

    private static int partition(int[] arr, int low, int high) { // Helper method to rearrange elements around the pivot.
        var pivot = arr[high]; // We choose the last element as our pivot for simplicity.
        var i = (low - 1); // 'i' tracks the boundary of elements smaller than the pivot.

        for (var j = low; j < high; j++) { // Loop through the current segment to compare elements against the pivot.
            if (arr[j] <= pivot) { // If the current element is smaller than or equal to the pivot...
                i++; // ...we expand our boundary of smaller elements.
                swap(arr, i, j); // Swap the current element into the "smaller than pivot" territory.
            } // End of the comparison block.
        } // End of the iteration through the array segment.

        swap(arr, i + 1, high); // Finally, place the pivot exactly after the boundary of smaller elements.
        return i + 1; // Return the exact index where the pivot now sits in its perfectly sorted position.
    } // End of the partition method.

    private static void swap(int[] arr, int i, int j) { // Simple helper method to swap two elements in an array.
        var temp = arr[i]; // Store the first element in a temporary variable so we don't lose it.
        arr[i] = arr[j]; // Overwrite the first element with the second element.
        arr[j] = temp; // Put the original first element into the second element's spot.
    } // End of the swap method.

    public static void main(String[] args) { // Main method used entirely as our custom testing framework.

        var largeArray = new Random().ints(10000, 0, 1000).toArray(); // Generate 10,000 random numbers to test large data inputs.
        var largeArrayExpected = Arrays.copyOf(largeArray, largeArray.length); // Create a copy of the large array.
        Arrays.sort(largeArrayExpected); // Use Java's built-in sort to get the true expected result for the large array.

        var testCases = Stream.of( // Use Java 8 Stream API to build and hold our list of scenarios.
            new TestCase("Normal", new int[]{10, 7, 8, 9, 1, 5}, new int[]{1, 5, 7, 8, 9, 10}), // Standard mixed array test.
            new TestCase("Empty", new int[]{}, new int[]{}), // Edge case: array with no elements.
            new TestCase("Single", new int[]{5}, new int[]{5}), // Edge case: array with only one element.
            new TestCase("Sorted", new int[]{1, 2, 3, 4}, new int[]{1, 2, 3, 4}), // Edge case: array already in order.
            new TestCase("Reverse", new int[]{9, 7, 5, 3}, new int[]{3, 5, 7, 9}), // Edge case: array in completely backward order.
            new TestCase("Duplicates", new int[]{4, 2, 4, 2}, new int[]{2, 2, 4, 4}), // Edge case: array with repeating numbers.
            new TestCase("Large Data (10k)", largeArray, largeArrayExpected) // Performance case: massive array to test stack overflow limits.
        ); // End of test case Stream initialization.

        testCases.forEach(tc -> { // Use Stream forEach to iterate over every single test case cleanly.
            var actual = Arrays.copyOf(tc.input(), tc.input().length); // Make a copy of the input so we don't modify the original record.

            sort(actual, 0, actual.length - 1); // Execute our custom Quick Sort algorithm on the array.

            var passed = Arrays.equals(actual, tc.expected()); // Compare our sorted array against the expected result.
            var status = passed ? "PASS" : "FAIL"; // Assign a string status based on the boolean result.
            System.out.printf("[ %s ] %s%n", status, tc.name()); // Print the result clearly to the console.
        }); // End of the Stream processing loop.
    } // End of the main testing method.

    record TestCase(String name, int[] input, int[] expected) {} // Java 14+ feature to minimize boilerplate code for storing test data.
} // End of the QuickSort class.