package com.interview.notes.code.year.y2026.june.cognizant.test6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Main { // Main class to run the program

    static List<String> toUpperNames(List<String> names) { // Method to convert list names to uppercase
        return names.stream() // Convert list into stream for processing
                .map(name -> name == null ? null : name.toUpperCase(Locale.ROOT)) // Convert each name to uppercase, keep null as null
                .toList(); // Collect final result as list
    } // End of uppercase method

    static void test(String testName, List<String> input, List<String> expected) { // Simple test method
        List<String> actual = toUpperNames(input); // Call actual method with input
        boolean pass = actual.equals(expected); // Compare actual output with expected output
        System.out.println((pass ? "PASS" : "FAIL") + " - " + testName); // Print test result
    } // End of test method

    public static void main(String[] args) { // Program starts here

        test( // Test normal names
                "Normal names", // Test name
                List.of("sunil", "rahul", "amit"), // Input list
                List.of("SUNIL", "RAHUL", "AMIT") // Expected output
        ); // End test

        test( // Test already uppercase and mixed case
                "Mixed case names", // Test name
                List.of("Sunil", "RAHUL", "aMiT"), // Input list
                List.of("SUNIL", "RAHUL", "AMIT") // Expected output
        ); // End test

        test( // Test empty list
                "Empty list", // Test name
                List.of(), // Input list
                List.of() // Expected output
        ); // End test

        test( // Test names with space
                "Names with space", // Test name
                List.of("sunil soni", "john doe"), // Input list
                List.of("SUNIL SONI", "JOHN DOE") // Expected output
        ); // End test

        test( // Test null value
                "Null value", // Test name
                Arrays.asList("sunil", null, "amit"), // Input list with null
                Arrays.asList("SUNIL", null, "AMIT") // Expected output with null
        ); // End test

        List<String> bigInput = new ArrayList<>(); // Create large input list
        List<String> bigExpected = new ArrayList<>(); // Create large expected list

        for (int i = 0; i < 100_000; i++) { // Loop for large data test
            bigInput.add("name" + i); // Add lowercase name
            bigExpected.add(("name" + i).toUpperCase(Locale.ROOT)); // Add expected uppercase name
        } // End loop

        test("Large data test", bigInput, bigExpected); // Run large data test

    } // End main method
} // End class