package com.interview.notes.code.year.y2026.july.common.test2;

import java.util.Arrays; // Required built-in utility class to work with array comparisons and the Stream API.
import java.util.Random; // Required built-in class to easily generate a massive dataset of random numbers for our load test.

public class NumberSorter { // Creating the main public class to encapsulate our sorting logic and application entry point.

    public static int[] sort(int[] nums) { // Our core sorting method that takes an unsorted integer array and returns a newly sorted one.
        if (nums == null) return new int[0]; // Safety check: if a null array is passed, we return an empty array to prevent application crashes.
        return Arrays.stream(nums) // Converts the raw integer array into a Java IntStream so we can chain powerful data operations.
                     .sorted() // Triggers Java's built-in Dual-Pivot Quicksort to efficiently arrange the stream elements in ascending order.
                     .toArray(); // Collects the sorted stream elements and packages them back into a standard integer array for the caller.
    } // Closes the sorting method block.

    public static void main(String[] args) { // The standard main method where our program execution begins, acting as our test runner.
        var passed = 0; // Using Java's 'var' keyword for type inference to create a counter that tracks how many tests succeed.
        var total = 5; // Using 'var' to define the total number of test cases we plan to run, making summary calculations easy.

        int[] t1 = {4, 1, 3, 9, 7}; // Test Case 1 Setup: Creating a standard array of unsorted, positive integers.
        int[] e1 = {1, 3, 4, 7, 9}; // Defining the exact sequence we expect to see if the sorting logic works correctly.
        if (Arrays.equals(sort(t1), e1)) { System.out.println("TC1 (Normal): PASS"); passed++; } else System.out.println("TC1: FAIL"); // Calls our method, compares the result to the expectation, prints the outcome, and increments the pass counter if successful.

        int[] t2 = {}; // Test Case 2 Setup: Creating an empty array to ensure our logic doesn't crash when there is no data to sort.
        int[] e2 = {}; // Defining the expectation: an empty array should simply return an empty array.
        if (Arrays.equals(sort(t2), e2)) { System.out.println("TC2 (Empty): PASS"); passed++; } else System.out.println("TC2: FAIL"); // Runs the empty array through the sorter and validates the output.

        int[] t3 = {5, -1, 5, -9, 0}; // Test Case 3 Setup: Creating an array with negative values, a zero, and duplicate numbers.
        int[] e3 = {-9, -1, 0, 5, 5}; // Defining the expectation: negatives should come first, and duplicates must sit side-by-side.
        if (Arrays.equals(sort(t3), e3)) { System.out.println("TC3 (Negatives/Dupes): PASS"); passed++; } else System.out.println("TC3: FAIL"); // Validates that the built-in sort correctly handles complex primitive variations.

        int[] t4 = null; // Test Case 4 Setup: Explicitly defining a null input to trigger our safety check.
        int[] e4 = {}; // Defining the expectation: our method should catch the null and return a safe, empty array.
        if (Arrays.equals(sort(t4), e4)) { System.out.println("TC4 (Null Input): PASS"); passed++; } else System.out.println("TC4: FAIL"); // Validates that our program survives bad data without throwing a NullPointerException.

        var largeArr = new Random().ints(1000000, 1, 1000).toArray(); // Test Case 5 Setup: Leveraging the Random class to generate one million random numbers to test system performance and memory limits.
        var largeSorted = sort(largeArr); // Executing our sorting method against the massive one-million element array.
        if (largeSorted.length == 1000000 && largeSorted[0] <= largeSorted[999999]) { System.out.println("TC5 (Large Data): PASS"); passed++; } else System.out.println("TC5: FAIL"); // Verifies no data was lost (length check) and the data is ordered (first element is less than or equal to the last).

        System.out.println("Total Tests Passed: " + passed + "/" + total); // Prints a final summary report to the console showing how many tests succeeded out of the total.
    } // Closes the main method block.
} // Closes the main application class block.