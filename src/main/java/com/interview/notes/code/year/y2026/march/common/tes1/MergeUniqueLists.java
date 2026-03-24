package com.interview.notes.code.year.y2026.march.common.tes1;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class MergeUniqueLists { // Declaring the main class that holds our logic

    public static List<Integer> merge(List<Integer> list1, List<Integer> list2) { // Method to merge lists, takes two integer lists as input
        return Stream.concat( // Returns the final list by first combining two streams together
                list1.stream(), // Converts the first list into a data stream so we can process it
                list2.stream() // Converts the second list into a data stream to join with the first
        ) // Ends the concat method, now we have one unified stream of integers
        .distinct() // Filters the stream to only allow unique elements, automatically removing duplicates
        .toList(); // Java 16+ feature that directly collects the distinct stream items back into an unmodifiable List
    } // Ends the merge method

    public static void main(String[] args) { // Main method where program execution begins, used here for testing
        
        // Test Case 1: Standard lists with some overlapping elements
        List<Integer> l1 = List.of(1, 2, 3, 4); // Creating first immutable list with basic numbers
        List<Integer> l2 = List.of(3, 4, 5, 6); // Creating second list with numbers 3 and 4 overlapping
        List<Integer> expected1 = List.of(1, 2, 3, 4, 5, 6); // Defining the expected output without duplicates
        checkTest("Test 1 (Standard)", expected1, merge(l1, l2)); // Passing inputs to test helper to print PASS/FAIL

        // Test Case 2: One list is completely empty
        List<Integer> emptyList = List.of(); // Creating an empty list to test edge cases
        List<Integer> l3 = List.of(7, 8, 9); // Creating a normal list to merge with the empty one
        List<Integer> expected2 = List.of(7, 8, 9); // The expected result is just the elements of the non-empty list
        checkTest("Test 2 (Empty List)", expected2, merge(emptyList, l3)); // Validating the empty list scenario

        // Test Case 3: Lists with heavy internal duplication
        List<Integer> duplicates1 = List.of(1, 1, 1, 2); // First list with multiple identical values
        List<Integer> duplicates2 = List.of(2, 2, 3, 3); // Second list with multiple identical values
        List<Integer> expectedDupe = List.of(1, 2, 3); // Expected output should strip all internal and cross-list duplicates
        checkTest("Test 3 (Heavy Duplicates)", expectedDupe, merge(duplicates1, duplicates2)); // Validating the heavy duplicate scenario

        // Test Case 4: Large Data Input (Simulating thousands of elements)
        List<Integer> largeList1 = Collections.nCopies(50000, 10); // Generates a list of 50,000 items, all the number 10
        List<Integer> largeList2 = Collections.nCopies(50000, 20); // Generates a list of 50,000 items, all the number 20
        List<Integer> expectedLarge = List.of(10, 20); // Merging 100,000 items should just result in two unique numbers: 10 and 20
        checkTest("Test 4 (Large Data)", expectedLarge, merge(largeList1, largeList2)); // Testing performance and accuracy on large datasets
        
    } // Ends the main method

    private static void checkTest(String testName, List<Integer> expected, List<Integer> actual) { // Helper method to verify and print test results
        if (Objects.equals(expected, actual)) { // Checks if the expected list exactly matches the actual returned list
            System.out.println(testName + ": PASS"); // Prints PASS to the console if they match perfectly
        } else { // Fallback block if the lists do not match
            System.out.println(testName + ": FAIL - Expected " + expected + " but got " + actual); // Prints FAIL and shows the discrepancy for debugging
        } // Ends the if-else block
    } // Ends the checkTest helper method

} // Ends the main class