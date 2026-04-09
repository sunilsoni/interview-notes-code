package com.interview.notes.code.year.y2026.april.common.test7;

import java.util.Objects; // Required for safe null-handling during expected vs actual comparisons.
import java.util.stream.Stream; // Required to use the modern Stream API for our custom test runner.

public class CustomStringReverser { // Defines the main class for our application.

    public static String customReverse(String str) { // Defines our custom method taking and returning a String.

        if (str == null) { // Guard clause: explicitly checks for null to prevent crashes (NullPointerException).
            return null; // Immediately returns null, as a null string cannot be reversed.
        } // Closes the null check block.

        char[] characters = str.toCharArray(); // Converts the string into a mutable array of characters for in-place swapping.

        int leftIndex = 0; // Initializes a pointer starting at the very first character of the array.
        int rightIndex = characters.length - 1; // Initializes a pointer starting at the very last character of the array.

        while (leftIndex < rightIndex) { // Loops as long as the left pointer hasn't crossed the right pointer.

            char temp = characters[leftIndex]; // Temporarily saves the left character so it isn't erased during the swap.

            characters[leftIndex] = characters[rightIndex]; // Overwrites the left position with the character from the right.
            characters[rightIndex] = temp; // Places the saved left character into the right position, completing the swap.

            leftIndex++; // Moves the left pointer one step towards the middle of the array.
            rightIndex--; // Moves the right pointer one step towards the middle of the array.

        } // Closes the while loop; at this point, the entire array is reversed.

        return new String(characters); // Converts the reversed character array back into a new String object and returns it.
    } // Closes the customReverse method.

    public static void main(String[] args) { // The entry point of the application where our test runner executes.

        // Generates a massive string to test memory limits and algorithmic time complexity.
        var massiveString = "a".repeat(1000000); // Efficiently creates a 1-million character string using Java 11+ features.
        var expectedMassive = "a".repeat(1000000); // The expected result of reversing 1 million identical characters.

        Stream.of( // Opens a Stream to sequentially process our predefined list of test scenarios.
            new TestCase("hello", "olleh"), // Standard test: verifies basic odd-length word reversal.
            new TestCase("Java 21", "12 avaJ"), // Standard test: verifies spaces and numbers reverse correctly.
            new TestCase("", ""), // Edge case: ensures empty strings don't cause index out-of-bounds errors.
            new TestCase("A", "A"), // Edge case: ensures single characters bypass the while loop cleanly.
            new TestCase(null, null), // Edge case: verifies our null guard clause works correctly.
            new TestCase(massiveString, expectedMassive) // Performance test: ensures the algorithm handles huge datasets instantly.
        ).forEach(test -> { // Iterates through each TestCase object in the stream.

            var result = customReverse(test.input()); // Executes our custom logic using the test input.
            var isPass = Objects.equals(result, test.expected()); // Safely checks if our actual result matches the expected result.

            // Constructs and prints the final output log for the current test case.
            System.out.println("Status: " + (isPass ? "PASS" : "FAIL") // Ternary operator to print PASS or FAIL cleanly.
                    + " | Input: " + truncate(test.input()) // Formats the input for readability on the console.
                    + " | Expected: " + truncate(test.expected()) // Formats the expected output for readability.
                    + " | Result: " + truncate(result)); // Formats the actual output to verify visually.

        }); // Closes the Stream's forEach loop.
    } // Closes the main method.

    private static String truncate(String str) { // A helper function strictly to keep console logs readable for the massive string.
        if (str == null) return "null"; // Prevents null strings from crashing the substring logic.
        return str.length() > 20 ? str.substring(0, 20) + "..." : str; // Truncates outputs longer than 20 chars with an ellipsis.
    } // Closes the truncate helper method.

    // Uses Java's 'record' feature to cleanly define an immutable test case structure without boilerplate code.
    record TestCase(String input, String expected) {}
} // Closes the class definition.