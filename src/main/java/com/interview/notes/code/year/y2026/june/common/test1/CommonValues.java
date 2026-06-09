package com.interview.notes.code.year.y2026.june.common.test1;

import java.util.List;              // Used to store input and output numbers
import java.util.Set;               // Used for fast search of common numbers
import java.util.stream.Collectors; // Used to collect stream output into Set/List

public class CommonValues {         // Main class to run the program

    public static List<Integer> commonValues(List<Integer> list1, List<Integer> list2) { // Method to find common values

        Set<Integer> lookup = list2.stream()      // Convert second list into stream
                .collect(Collectors.toSet());     // Store second list values in Set for fast contains check

        return list1.stream()                     // Start reading values from first list one by one
                .filter(lookup::contains)         // Keep only values which are present in second list
                .distinct()                       // Remove duplicate common values
                .toList();                        // Convert final stream result into List
    }

    static void test(String name, List<Integer> input1, List<Integer> input2, List<Integer> expected) { // Simple test method

        List<Integer> actual = commonValues(input1, input2); // Call our method and store actual result

        boolean passed = actual.equals(expected);            // Compare actual result with expected result

        System.out.println(name + " : " + (passed ? "PASS" : "FAIL")); // Print PASS or FAIL

        if (!passed) {                                       // If test failed, print details
            System.out.println("Expected : " + expected);    // Print expected output
            System.out.println("Actual   : " + actual);      // Print actual output
        }
    }

    public static void main(String[] args) {                  // Program starts from main method

        List<Integer> ints1 = List.of(1,2,3,4,5,6,6,5,4,3,2,1); // First input list with duplicate values

        List<Integer> ints2 = List.of(6,5,4,3,2,1);             // Second input list

        test(                                                       // Test case 1
                "Given Test Case",                                 // Test name
                ints1,                                             // First list
                ints2,                                             // Second list
                List.of(1,2,3,4,5,6)                                // Expected common unique values
        );

        test(                                                       // Test case 2
                "No Common Values",                                // Test name
                List.of(10,20,30),                                  // First list
                List.of(1,2,3),                                     // Second list
                List.of()                                           // Expected empty list
        );

        test(                                                       // Test case 3
                "With Duplicates",                                  // Test name
                List.of(1,1,2,2,3,3),                                // First list with duplicates
                List.of(2,3,4),                                     // Second list
                List.of(2,3)                                        // Expected unique common values
        );

        test(                                                       // Test case 4
                "Large Data",                                      // Test name
                java.util.stream.IntStream.rangeClosed(1, 100000)   // Create numbers from 1 to 100000
                        .boxed()                                    // Convert int to Integer
                        .toList(),                                  // Convert stream to List
                java.util.stream.IntStream.rangeClosed(50000, 150000) // Create numbers from 50000 to 150000
                        .boxed()                                    // Convert int to Integer
                        .toList(),                                  // Convert stream to List
                java.util.stream.IntStream.rangeClosed(50000, 100000) // Expected common values
                        .boxed()                                    // Convert int to Integer
                        .toList()                                   // Convert stream to List
        );
    }
}