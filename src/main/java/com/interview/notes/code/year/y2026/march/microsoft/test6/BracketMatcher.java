package com.interview.notes.code.year.y2026.march.microsoft.test6;

import java.util.ArrayDeque;
import java.util.stream.Stream;

public class BracketMatcher { // Main class containing our logic and the testing method.

    public static boolean isBalanced(String s) { // Method takes the input string and returns true if balanced.
        var stack = new ArrayDeque<Character>(); // Use 'var' to create a stack holding characters efficiently.

        for (char c : s.toCharArray()) { // Convert string to a char array and loop through every single character.
            switch (c) { // Use modern Java switch expression for clean, highly readable routing without 'break' statements.
                case '(', '[', '{' -> stack.push(c); // If character is any opening bracket, push it to the top of the stack.
                case ')' -> { if (stack.isEmpty() || stack.pop() != '(') return false; } // If ')', fail if stack is empty or top isn't '('.
                case ']' -> { if (stack.isEmpty() || stack.pop() != '[') return false; } // If ']', fail if stack is empty or top isn't '['.
                case '}' -> { if (stack.isEmpty() || stack.pop() != '{') return false; } // If '}', fail if stack is empty or top isn't '{'.
                default -> {} // For any other characters (letters, spaces, etc.), do nothing and just continue the loop.
            } // End of the switch block.
        } // End of the character iteration loop.

        return stack.isEmpty(); // If stack is empty at the end, all brackets were perfectly matched; otherwise, return false.
    } // End of isBalanced method.

    public static void main(String[] args) { // Simple main method for running our tests, avoiding JUnit entirely.

        var largeInput = "(".repeat(50000) + "a" + ")".repeat(50000); // Generate large balanced data using String.repeat.
        var failLargeInput = "(".repeat(50000) + "}".repeat(50000); // Generate large failing data (opening parenthesis, closing brace).

        Stream.of( // Use Java Stream API to list and iterate through our test cases cleanly.
            new TestCase("a(b)c", true), // Simple case with random characters.
            new TestCase("{[()]}", true), // Deeply nested valid brackets.
            new TestCase("{[(])}", false), // Intersecting brackets, should fail.
            new TestCase("((abc)", false), // Missing a closing parenthesis.
            new TestCase("abc)", false), // Extra closing parenthesis with no opening.
            new TestCase("", true), // Edge case: an empty string is technically perfectly balanced.
            new TestCase(largeInput, true), // Testing large data input expecting PASS.
            new TestCase(failLargeInput, false) // Testing large data input expecting FAIL.
        ).forEach(test -> { // Iterate over the stream of tests.
            boolean result = isBalanced(test.input); // Run the method and capture the boolean result.
            String status = (result == test.expected) ? "PASS" : "FAIL"; // Determine if our result matches the expected outcome.
            String preview = test.input.length() > 20 ? "Large Input..." : test.input; // Truncate very long strings for console readability.
            System.out.printf("[%s] Expected: %b | Got: %b | Input: %s%n", status, test.expected, result, preview); // Print test status nicely.
        }); // Close the stream iteration.
    } // Close the main method.

    record TestCase(String input, boolean expected) {} // Use Java 16+ 'record' to create a tiny data structure for our tests.
} // Close the class.