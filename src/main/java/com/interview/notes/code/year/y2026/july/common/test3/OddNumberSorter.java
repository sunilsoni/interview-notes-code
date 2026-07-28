package com.interview.notes.code.year.y2026.july.common.test3;

import java.util.Arrays; // Required built-in utility class to perform array comparisons and generate Streams.
import java.util.Random; // Required built-in class to easily generate our massive random dataset for the load test.

public class OddNumberSorter { // Creating the main public class to encapsulate our filtering/sorting logic and execution entry point.

    public static int[] processOddsOnly(int[] nums) { // Core method that takes an unsorted array, removes evens, and returns a sorted array of odds.
        if (nums == null) return new int[0]; // Safety check: if a null array is passed, immediately return an empty array to prevent NullPointerExceptions.
        return Arrays.stream(nums) // Converts the raw integer array into a Java IntStream so we can chain data manipulation operations.
                     .filter(n -> n % 2 != 0) // Filters the stream by keeping only numbers where the remainder of division by 2 is NOT zero (this safely identifies both positive and negative odd numbers).
                     .sorted() // Triggers Java's built-in Dual-Pivot Quicksort to arrange the remaining odd numbers in ascending sequence.
                     .toArray(); // Collects the filtered, sorted stream elements and packages them back into a standard primitive integer array.
    } // Closes the data processing method block.

    public static void main(String[] args) { // The standard main method where program execution begins, acting as our manual test runner.
        var passed = 0; // Using Java's 'var' keyword for type inference to initialize a counter that tracks successful test cases.
        var total = 5; // Defining the total number of test cases we are running to provide an accurate final summary fraction.

        int[] t1 = {4, 1, 3, 8, 9, 7}; // Test Case 1 Setup: Creating a standard array containing a mix of even and odd positive integers.
        int[] e1 = {1, 3, 7, 9}; // Defining the expectation: all evens (4, 8) are removed, and odds are sorted.
        if (Arrays.equals(processOddsOnly(t1), e1)) { System.out.println("TC1 (Mix): PASS"); passed++; } else System.out.println("TC1: FAIL"); // Calls our method, compares the result to the expected array, and logs the outcome.

        int[] t2 = {2, 4, 6, -8, 0}; // Test Case 2 Setup: Creating an array containing exclusively even numbers and zero.
        int[] e2 = {}; // Defining the expectation: since there are no odds, the resulting array should be completely empty.
        if (Arrays.equals(processOddsOnly(t2), e2)) { System.out.println("TC2 (All Evens): PASS"); passed++; } else System.out.println("TC2: FAIL"); // Validates that the filter correctly drops every single item when none meet the condition.

        int[] t3 = {5, -2, 5, -9, 0}; // Test Case 3 Setup: Creating an array with negative odds, negative evens, a zero, and duplicate odds.
        int[] e3 = {-9, 5, 5}; // Defining the expectation: evens (-2, 0) drop out, negative odds come first, and duplicates sit together.
        if (Arrays.equals(processOddsOnly(t3), e3)) { System.out.println("TC3 (Negatives/Dupes): PASS"); passed++; } else System.out.println("TC3: FAIL"); // Ensures our modulo logic correctly handles negative integers.

        int[] t4 = null; // Test Case 4 Setup: Explicitly defining a null input to trigger our safety guard clause.
        int[] e4 = {}; // Defining the expectation: the method must catch the null and return a safe, empty array instead of crashing.
        if (Arrays.equals(processOddsOnly(t4), e4)) { System.out.println("TC4 (Null Input): PASS"); passed++; } else System.out.println("TC4: FAIL"); // Proves the application survives bad input gracefully.

        var largeArr = new Random().ints(1000000, -1000, 1000).toArray(); // Test Case 5 Setup: Leveraging Random to generate one million integers spanning negative to positive to stress-test performance.
        var largeSorted = processOddsOnly(largeArr); // Executing our method against the massive dataset to filter and sort.
        var isSorted = largeSorted.length == 0 || largeSorted[0] <= largeSorted[largeSorted.length - 1]; // Verifies the boundaries of the output array to ensure the sort operation functioned correctly on massive data.
        if (isSorted && largeSorted.length <= 1000000) { System.out.println("TC5 (Large Data): PASS"); passed++; } else System.out.println("TC5: FAIL"); // Checks that we didn't exceed the original length and that the data is ordered, confirming a pass.

        System.out.println("Total Tests Passed: " + passed + "/" + total); // Prints the final execution report to the console so the developer can see the overall success rate.
    } // Closes the main execution method block.
} // Closes the class block.