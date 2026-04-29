package com.interview.notes.code.year.y2026.april.common.test2;

import java.util.List; // Imports the List interface to store our final formatted output sequences.
import java.util.stream.IntStream; // Imports IntStream to generate a sequence of indices for the string.

public class StringSorter { // Declares the main class that contains our sorting logic and test runner.

    public static List<String> processString(String s) { // Method accepts a string and returns a list of formatted strings.
        if (s == null || s.isEmpty()) { // Checks if the input is null or empty to prevent NullPointerException or wasted processing.
            return List.of(); // Returns an immutable empty list if the input is invalid.
        } // Closes the if statement block.

        return IntStream.range(0, s.length()) // Generates a stream of integers starting from 0 up to the string's length (exclusive).
                .mapToObj(i -> new CharData(s.charAt(i), i)) // Maps each integer (index) into a new CharData object containing the char and index.
                .sorted((a, b) -> Character.compare(a.ch(), b.ch())) // Sorts the stream of objects comparing the characters by their ASCII values.
                .map(d -> "Char>>index>>" + d.ch() + " >> " + d.index()) // Maps the sorted objects into the exact requested string format.
                .toList(); // Collects the stream into an unmodifiable list (Java 16+ feature, shorter than Collectors.toList()).
    } // Closes the processing method.

    public static void runTest(String testName, String input, List<String> expected) { // Helper method to compare actual output with expected output.
        var actual = processString(input); // Uses Java 10+ 'var' keyword to infer the List<String> type for the actual result.

        if (actual.equals(expected)) { // Compares the expected list with the actual generated list.
            System.out.println(testName + " : PASS"); // If they match exactly, prints PASS to the console.
        } else { // Fallback execution block if the test case fails.
            System.out.println(testName + " : FAIL"); // Prints FAIL to indicate the logic did not meet expectations.
            System.out.println("Expected: " + expected); // Prints the expected result to help with debugging.
            System.out.println("Actual  : " + actual); // Prints the actual result to show where the discrepancy occurred.
        } // Closes the if-else block.
    } // Closes the test helper method.

    public static void main(String[] args) { // Main method serves as the entry point of the program.

        // Test Case 1: Provided basic example
        var expected1 = List.of( // Uses var and List.of() to create an immutable list of expected outputs.
            "Char>>index>>  >> 4", // Expected output for space character at index 4.
            "Char>>index>>! >> 11", // Expected output for exclamation mark at index 11.
            "Char>>index>>S >> 5", // Expected output for uppercase S at index 5.
            "Char>>index>>T >> 0", // Expected output for uppercase T at index 0.
            "Char>>index>>e >> 1", // Expected output for lowercase e at index 1.
            "Char>>index>>g >> 10", // Expected output for lowercase g at index 10.
            "Char>>index>>i >> 8", // Expected output for lowercase i at index 8.
            "Char>>index>>n >> 9", // Expected output for lowercase n at index 9.
            "Char>>index>>r >> 7", // Expected output for lowercase r at index 7.
            "Char>>index>>s >> 2", // Expected output for lowercase s at index 2.
            "Char>>index>>t >> 3", // Expected output for lowercase t at index 3.
            "Char>>index>>t >> 6" // Expected output for lowercase t at index 6.
        ); // Closes the expected list creation.
        runTest("Test Case 1 (Standard Input)", "Test String!", expected1); // Executes the standard test case.

        // Test Case 2: Empty String
        runTest("Test Case 2 (Empty String)", "", List.of()); // Tests an empty string to ensure it returns an empty list without errors.

        // Test Case 3: Null Input
        runTest("Test Case 3 (Null Input)", null, List.of()); // Tests null input to verify our null check prevents crashes.

        // Test Case 4: Repeated Characters
        var expectedRepeats = List.of("Char>>index>>A >> 0", "Char>>index>>A >> 1", "Char>>index>>A >> 2"); // Defines expected output for string "AAA".
        runTest("Test Case 4 (Repeats)", "AAA", expectedRepeats); // Runs test on repeated characters to verify stable sorting.

        // Test Case 5: Large Input Handling
        String largeInput = "A".repeat(100000); // Uses Java 11+ String.repeat to generate a massive 100,000 character string.
        var largeActual = processString(largeInput); // Processes the large string to test performance and memory handling.
        if (largeActual.size() == 100000 && largeActual.get(99999).contains("99999")) { // Validates the size and the last element's index.
            System.out.println("Test Case 5 (Large Input) : PASS"); // Prints PASS if large input is handled gracefully without OutOfMemory errors.
        } else { // Fallback if large input processing fails.
            System.out.println("Test Case 5 (Large Input) : FAIL"); // Prints FAIL if there's an issue with large string handling.
        } // Closes large input validation block.

    } // Closes the main method.

    // Uses Java 14+ record feature to create a concise, immutable data carrier for a character and its index.
    record CharData(char ch, int index) {} // Automatically generates constructor, getters, equals, and hashCode.
} // Closes the main class.