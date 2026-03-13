package com.interview.notes.code.year.y2026.march.oracle.test1;

public class NumberRuns { // Define the main class to hold our logic and testing methods

    public static void main(String[] args) { // Main method serves as the entry point and testing framework
        System.out.println("Running Tests..."); // Print a header to the console to indicate tests are starting
        
        test(new int[]{1}, "1"); // Test case 1: Single element array should return just the number
        test(new int[]{1, 3}, "1,3"); // Test case 2: Non-consecutive elements should be separated by commas
        test(new int[]{1, 2}, "1:2"); // Test case 3: Two consecutive elements should form a run with a colon
        test(new int[]{1, 2, 3}, "1:3"); // Test case 4: Multiple consecutive elements form a single run
        test(new int[]{0, 2, 3, 4, 6, 7, 9}, "0,2:4,6:7,9"); // Test case 5: Mixed consecutive and non-consecutive elements
        
        var largeData = java.util.stream.IntStream.range(1, 1000001).toArray(); // Generate an array of one million consecutive numbers to test large data handling
        test(largeData, "1:1000000"); // Test case 6: Large dataset should compress into a single start:end run instantly
    } // End of main method

    private static void test(int[] input, String expected) { // Helper method to execute a test and print PASS/FAIL status
        var result = stringifyNumberRuns(input); // Execute the core logic method with the provided input array
        var passed = result.equals(expected); // Evaluate if the returned string perfectly matches the expected string
        System.out.println((passed ? "PASS" : "FAIL") + " | Expected: " + expected + " | Got: " + result); // Output the result conditionally, showing what we expected vs what we got
    } // End of test method

    public static String stringifyNumberRuns(int[] arr) { // Core method taking an integer array and returning the formatted string
        if (arr == null || arr.length == 0) return ""; // Guard clause: immediately return an empty string if the input is missing or empty to prevent exceptions
        
        var parts = new java.util.ArrayList<String>(); // Initialize a dynamic List to accumulate the formatted strings (using 'var' for Java 21 brevity)
        int start = arr[0]; // Set the 'start' variable to the very first number in the array to begin our first run
        
        for (int i = 1; i <= arr.length; i++) { // Loop starting from index 1 and deliberately going ONE past the array boundary to force the final run to process
            if (i == arr.length || arr[i] != arr[i - 1] + 1) { // Trigger condition: If we are at the end of the array OR the current number is NOT consecutive
                parts.add(start == arr[i - 1] ? String.valueOf(start) : start + ":" + arr[i - 1]); // Add the number alone if start equals the previous number, otherwise add them joined by a colon
                if (i < arr.length) start = arr[i]; // If we haven't reached the end of the array, safely update 'start' to the current number to begin the next run track
            } // End of trigger condition block
        } // End of the single-pass loop
        
        return String.join(",", parts); // Join all the accumulated string parts with commas and return the final output
    } // End of stringifyNumberRuns method
} // End of class