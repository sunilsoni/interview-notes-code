package com.interview.notes.code.year.y2026.july.assessments.test6;

import java.util.Map; // Stores each character and its occurrence count.
import java.util.function.Function; // Provides Function.identity() for grouping.
import java.util.stream.Collectors; // Converts stream data into a frequency map.

public class Solution { // Contains the solution and test methods.

    public static char findFirst(String input) { // Finds the first non-repeating character.
        if (input == null || input.isEmpty()) return 0; // Returns 0 for null or empty input.

        Map<Integer, Long> counts = input.chars() // Creates a stream of character numeric values.
                .boxed() // Converts primitive int values into Integer objects.
                .collect(Collectors.groupingBy( // Groups identical character values.
                        Function.identity(), // Uses each character value as the map key.
                        Collectors.counting())); // Counts how many times each character appears.

        return (char) input.chars() // Reads characters again in their original order.
                .filter(c -> counts.get(c) == 1L) // Keeps characters appearing exactly once.
                .findFirst() // Selects the first matching character.
                .orElse(0); // Returns 0 when every character repeats.
    } // Ends the findFirst method.

    static boolean test(Test test) { // Runs one test case.
        char actual = findFirst(test.input()); // Executes the solution using the test input.
        boolean passed = actual == test.expected(); // Checks the actual result against expected.

        System.out.printf( // Prints the test result in a readable format.
                "%-6s input=%s expected=%s actual=%s%n", // Defines the output format.
                passed ? "PASS" : "FAIL", // Prints PASS or FAIL.
                show(test.input()), // Formats the input value.
                show(test.expected()), // Formats the expected character.
                show(actual)); // Formats the actual character.

        return passed; // Returns whether this test passed.
    } // Ends the test method.

    static boolean doTestsPass() { // Executes all provided and additional test cases.
        String largeUnique = "a".repeat(100_000) + "z"; // Creates large input with z as unique.
        String largeRepeated = "ab".repeat(50_000); // Creates large input with no unique character.

        var tests = new Test[]{ // Creates the complete collection of test cases.
                new Test("apple", 'a'), // Provided test case.
                new Test("racecars", 'e'), // Provided test case.
                new Test("ababdc", 'd'), // Provided test case.
                new Test("a", 'a'), // Tests a single character.
                new Test("aabb", (char) 0), // Tests when all characters repeat.
                new Test("", (char) 0), // Tests an empty string.
                new Test(null, (char) 0), // Tests null input.
                new Test("swiss", 'w'), // Tests repeated characters around a unique character.
                new Test("1122334", '4'), // Tests numeric characters.
                new Test("AaA", 'a'), // Confirms that matching is case-sensitive.
                new Test(largeUnique, 'z'), // Tests 100,001 characters with one unique character.
                new Test(largeRepeated, (char) 0) // Tests 100,000 repeated characters.
        }; // Ends the test array.

        boolean result = java.util.Arrays.stream(tests) // Creates a stream of all tests.
                .map(Solution::test) // Executes every test and returns its result.
                .reduce(true, Boolean::logicalAnd); // Returns true only when all tests pass.

        System.out.println( // Prints the combined test result.
                "\nALL TESTS: " + (result ? "PASS" : "FAIL")); // Selects PASS or FAIL.

        return result; // Returns the final test status.
    } // Ends the doTestsPass method.

    static String show(String value) { // Formats string values for output.
        if (value == null) return "null"; // Displays null clearly.
        if (value.isEmpty()) return "\"\""; // Displays an empty string clearly.
        if (value.length() > 30) return "length=" + value.length(); // Hides huge string content.
        return '"' + value + '"'; // Adds quotation marks around normal strings.
    } // Ends the string show method.

    static String show(char value) { // Formats character values for output.
        return value == 0 ? "0" : "'" + value + "'"; // Displays 0 or the actual character.
    } // Ends the character show method.

    public static void main(String[] args) { // Starts program execution.
        doTestsPass(); // Runs all tests without using JUnit.
    } // Ends the main method.

    record Test(String input, char expected) {} // Stores one test input and expected result.
} // Ends the Solution class.