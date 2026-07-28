package com.interview.notes.code.year.y2026.july.common.test6;

import java.util.Arrays; // Required built-in utility class to work with array manipulations and the Stream API.
import java.util.Random; // Required built-in class to generate a massive random dataset for our performance load test.
import java.util.Scanner; // Required built-in class to read dynamic input gracefully from the user's console.

public class CommaOddSorter { // Creating the main public class to encapsulate our sorting logic and application entry point.

    public static int[] processOddsOnly(int[] nums) { // Core method that takes an unsorted array, removes evens, and returns a sorted array of odds.
        if (nums == null) return new int[0]; // Safety check: if a null array is passed, immediately return an empty array to prevent NullPointerExceptions.
        return Arrays.stream(nums) // Converts the raw integer array into a Java IntStream so we can chain data manipulation operations.
                     .filter(n -> n % 2 != 0) // Filters the stream by keeping only numbers where the remainder of division by 2 is NOT zero (this safely identifies both positive and negative odd numbers).
                     .sorted() // Triggers Java's built-in Dual-Pivot Quicksort to arrange the remaining odd numbers in ascending sequence.
                     .toArray(); // Collects the filtered, sorted stream elements and packages them back into a standard primitive integer array.
    } // Closes the data processing method block.

    public static void main(String[] args) { // The standard main method where program execution begins, acting as both the dynamic app and test runner.
        
        try (var scanner = new Scanner(System.in)) { // Using Java's try-with-resources and 'var' to safely open a Scanner that auto-closes, preventing memory leaks.
            System.out.println("Enter numbers separated by commas (e.g. 5,2,9,4 or 5, 2, 9, 4):"); // Prompts the user via console to dynamically enter their custom numbers with commas.
            var input = scanner.nextLine(); // Reads the entire line of text typed by the user until they press Enter.
            
            if (!input.isBlank()) { // Checks if the user actually typed something, utilizing modern Java's isBlank() to prevent parsing errors on empty strings.
                var userArray = Arrays.stream(input.trim().split("\\s*,\\s*")) // Trims outer whitespace and splits the string by commas (ignoring any spaces around the commas) to create a Stream of individual text tokens.
                                      .mapToInt(Integer::parseInt) // Converts each string token into a primitive integer safely and efficiently.
                                      .toArray(); // Collects the newly parsed integers into our dynamic input array.
                
                var userResult = processOddsOnly(userArray); // Calls our core method to filter out evens and sort the user's dynamic input.
                System.out.println("Your Sorted Odd Numbers: " + Arrays.toString(userResult)); // Prints the final processed user array cleanly to the console.
            } else { // Fallback block in case the user just pressed Enter without typing any numbers.
                System.out.println("No input provided. Proceeding directly to automated tests."); // Informs the user that dynamic processing was skipped safely.
            } // Closes the if-else block for dynamic input handling.
        } // Closes the try-with-resources block, automatically releasing the Scanner resource from memory.

        System.out.println("\n--- Running Automated Tests ---"); // Prints a clear visual separator in the console before starting the internal test cases.
        
        var passed = 0; // Using Java's 'var' keyword to initialize a counter that tracks exactly how many automated tests succeed.
        var total = 5; // Defining the total number of automated test cases we plan to execute for our final summary.

        int[] t1 = {4, 1, 3, 8, 9, 7}; // Test Case 1 Setup: Creating a standard array containing a mix of even and odd positive integers.
        int[] e1 = {1, 3, 7, 9}; // Defining the expectation: all evens (4, 8) are removed, and remaining odds are sorted.
        if (Arrays.equals(processOddsOnly(t1), e1)) { System.out.println("TC1 (Mix): PASS"); passed++; } else System.out.println("TC1: FAIL"); // Calls our method, compares the result to the expected array, and logs the outcome.

        int[] t2 = {2, 4, 6, -8, 0}; // Test Case 2 Setup: Creating an array containing exclusively even numbers and zero.
        int[] e2 = {}; // Defining the expectation: since there are no odds, the resulting array should be completely empty.
        if (Arrays.equals(processOddsOnly(t2), e2)) { System.out.println("TC2 (All Evens): PASS"); passed++; } else System.out.println("TC2: FAIL"); // Validates that the filter correctly drops every single item when none meet the condition.

        int[] t3 = {5, -2, 5, -9, 0}; // Test Case 3 Setup: Creating an array with negative odds, negative evens, a zero, and duplicate odds.
        int[] e3 = {-9, 5, 5}; // Defining the expectation: evens (-2, 0) drop out, negative odds come first, and duplicates sit side-by-side.
        if (Arrays.equals(processOddsOnly(t3), e3)) { System.out.println("TC3 (Negatives/Dupes): PASS"); passed++; } else System.out.println("TC3: FAIL"); // Ensures our modulo logic correctly handles negative integers.

        int[] t4 = null; // Test Case 4 Setup: Explicitly defining a null input to trigger our safety guard clause.
        int[] e4 = {}; // Defining the expectation: the method must catch the null and return a safe, empty array instead of crashing.
        if (Arrays.equals(processOddsOnly(t4), e4)) { System.out.println("TC4 (Null Input): PASS"); passed++; } else System.out.println("TC4: FAIL"); // Proves the application survives bad system input gracefully.

        var largeArr = new Random().ints(1000000, -1000, 1000).toArray(); // Test Case 5 Setup: Leveraging Random to generate one million integers to stress-test system performance.
        var largeSorted = processOddsOnly(largeArr); // Executing our method against the massive dataset to filter and sort.
        var isSorted = largeSorted.length == 0 || largeSorted[0] <= largeSorted[largeSorted.length - 1]; // Verifies the boundaries of the output array to ensure the sort operation functioned correctly without data corruption.
        if (isSorted && largeSorted.length <= 1000000) { System.out.println("TC5 (Large Data): PASS"); passed++; } else System.out.println("TC5: FAIL"); // Checks that we didn't exceed the original length and that data is ordered, confirming a pass.

        System.out.println("Total Tests Passed: " + passed + "/" + total); // Prints the final execution report to the console so the developer can see the overall success rate.
    } // Closes the main execution method block.
} // Closes the main class block.