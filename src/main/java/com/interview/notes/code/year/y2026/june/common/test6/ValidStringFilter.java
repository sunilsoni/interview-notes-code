package com.interview.notes.code.year.y2026.june.common.test6;

import java.util.List; // Imports the List collection.

public class ValidStringFilter { // Defines the main class.

    static List<String> filter(List<String> values) { // Creates a method to filter strings.
        if (values == null) return List.of(); // Returns an empty list when input is null.

        return values.stream() // Creates a stream from the list.
                .filter(value -> value != null) // Removes null values.
                .filter(value -> !value.isBlank()) // Removes empty and space-only strings.
                .toList(); // Converts the filtered stream into a list.
    } // Ends the filter method.

    static void test(String name, List<String> input, List<String> expected) { // Creates a simple test method.
        var actual = filter(input); // Runs the filter method.
        var status = actual.equals(expected) ? "PASS" : "FAIL"; // Compares actual and expected results.
        System.out.println(name + ": " + status); // Prints the test result.
    } // Ends the test method.

    public static void main(String[] args) { // Starts the program.

        test( // Tests null, empty and valid strings.
                "Normal Test", // Provides the test name.
                List.of("Java", "", " ", "Spring"), // Creates input without null because List.of rejects null.
                List.of("Java", "Spring") // Defines the expected result.
        ); // Ends the first test.

        test( // Tests an empty list.
                "Empty List", // Provides the test name.
                List.of(), // Provides an empty input list.
                List.of() // Expects an empty output list.
        ); // Ends the second test.

        test( // Tests a null input list.
                "Null List", // Provides the test name.
                null, // Provides null as input.
                List.of() // Expects an empty list.
        ); // Ends the third test.

        var inputWithNull = new java.util.ArrayList<String>(); // Creates a list that permits null.
        inputWithNull.add("Java"); // Adds a valid string.
        inputWithNull.add(null); // Adds a null value.
        inputWithNull.add(""); // Adds an empty string.
        inputWithNull.add("   "); // Adds a whitespace-only string.
        inputWithNull.add("Kafka"); // Adds another valid string.

        test( // Tests a list containing null.
                "Null Value Test", // Provides the test name.
                inputWithNull, // Provides the list containing null.
                List.of("Java", "Kafka") // Defines the expected values.
        ); // Ends the fourth test.

        var largeInput = java.util.stream.IntStream.range(0, 1_000_000) // Creates one million numbers.
                .mapToObj(number -> number % 2 == 0 ? "Java" : "") // Creates valid and empty strings.
                .toList(); // Converts the stream into a list.

        test( // Tests large input handling.
                "Large Data Test", // Provides the test name.
                largeInput, // Provides one million values.
                java.util.Collections.nCopies(500_000, "Java") // Expects five hundred thousand valid strings.
        ); // Ends the large-data test.
    } // Ends the main method.
} // Ends the class.