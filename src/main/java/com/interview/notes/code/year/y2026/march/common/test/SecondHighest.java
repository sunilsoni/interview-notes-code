package com.interview.notes.code.year.y2026.march.common.test;

import java.util.Comparator;
import java.util.List;

public class SecondHighest { // Declaring the main class

    public static Integer getSecondHighest(List<Integer> list) { // Method taking our merged list of integers
        return list.stream() // Converting the list into a data stream for processing
                .sorted(Comparator.reverseOrder()) // Sorting the numbers in descending order (highest at the front)
                .skip(1) // Skipping exactly 1 element (which drops the highest number)
                .findFirst() // Grabbing the very next number available (which is now the second highest)
                .orElse(null); // Safely returning null if the list had fewer than 2 items to prevent crashes
    } // Ends the method

    public static void main(String[] args) { // Main method where we run our test cases
        
        // Test Case 1: The exact sorted list from your example
        List<Integer> resultList = List.of(1, 2, 3, 4, 5, 6); // Creating the list from the previous step
        checkTest("Test 1 (Standard)", 5, getSecondHighest(resultList)); // We expect 5. Prints PASS or FAIL.
        
        // Test Case 2: An unsorted list to prove the sorting logic works
        List<Integer> unsortedList = List.of(10, 42, 5, 23); // The highest is 42, second highest is 23
        checkTest("Test 2 (Unsorted)", 23, getSecondHighest(unsortedList)); // We expect 23. Prints PASS or FAIL.
        
        // Test Case 3: Edge case where the list is too small to have a second highest
        List<Integer> tinyList = List.of(99); // A list with only one single item
        checkTest("Test 3 (Too Small)", null, getSecondHighest(tinyList)); // We expect null. Prints PASS or FAIL.
        
    } // Ends the main method

    private static void checkTest(String testName, Integer expected, Integer actual) { // Helper method to verify results
        if ((expected == null && actual == null) || (expected != null && expected.equals(actual))) { // Safely checking if expected matches actual
            System.out.println(testName + ": PASS"); // Prints PASS to the console if correct
        } else { // Fallback block if the logic fails
            System.out.println(testName + ": FAIL - Expected " + expected + " but got " + actual); // Prints FAIL and shows the error
        } // Ends the if-else block
    } // Ends the checkTest helper method

} // Ends the main class