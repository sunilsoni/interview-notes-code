package com.interview.notes.code.year.y2026.april.common.test7;

import java.util.Objects; // Imports Objects class to safely check equality, even with nulls.
import java.util.stream.Stream; // Imports Stream API to process test cases iteratively.

public class StringReverser { // Declares the main public class for our program.

    public static String reverseString(String str) { // Defines the method that takes a String and returns a String.
        if (str == null) { // Checks if the input string is null to prevent NullPointerExceptions.
            return null; // Returns null immediately if the input is null.
        } // Closes the if-statement block.
        return new StringBuilder(str) // Creates a new StringBuilder instance initialized with our string.
                .reverse() // Calls the highly optimized, built-in method to reverse the character sequence.
                .toString(); // Converts the modified StringBuilder back into a standard String and returns it.
    } // Closes the reverseString method.

    public static void main(String[] args) { // Defines the main method where program execution begins.

        // Simulating a large data input case using Java's string repeat feature.
        var largeString = "a".repeat(1000000); // Creates a string of 1 million 'a' characters efficiently.
        var expectedLarge = "a".repeat(1000000); // The reverse of 1 million 'a's is identical to the input.

        Stream.of( // Starts a Stream of predefined objects to act as our test runner.
            new TestCase("hello", "olleh"), // Test case 1: Standard lowercase word.
            new TestCase("", ""), // Test case 2: Edge case for an empty string.
            new TestCase("A", "A"), // Test case 3: Edge case for a single character.
            new TestCase(null, null), // Test case 4: Edge case for a null value.
            new TestCase("Java 21", "12 avaJ"), // Test case 5: String containing spaces and numbers.
            new TestCase(largeString, expectedLarge) // Test case 6: Large data input to test performance.
        ).forEach(test -> { // Iterates over every test case in the stream using a lambda expression.
            var result = reverseString(test.input()); // Executes our logic and stores the returned string.
            var isPass = Objects.equals(result, test.expected()); // Compares result to expected, handling nulls safely.

            // Prints the test results to the console.
            System.out.println("Status: " + (isPass ? "PASS" : "FAIL") // Checks boolean to print PASS or FAIL.
                    + " | Input: " + truncate(test.input()) // Calls helper method to print input safely.
                    + " | Expected: " + truncate(test.expected()) // Calls helper method to print expected safely.
                    + " | Result: " + truncate(result)); // Calls helper method to print actual result safely.
        }); // Closes the forEach stream operation.
    } // Closes the main method.

    private static String truncate(String str) { // Helper method to keep console output readable for large data.
        if (str == null) return "null"; // Returns the word "null" if the string is actually null.
        return str.length() > 20 ? str.substring(0, 20) + "..." : str; // Truncates string and adds "..." if over 20 chars.
    } // Closes the truncate method.

    // A Java record is a concise way to create a data-carrying class for our test cases.
    record TestCase(String input, String expected) {} // Defines input and expected output properties.
} // Closes the class.