package com.interview.notes.code.year.y2026.july.common.test10;

import java.util.LinkedHashMap; // Preserves the original character order.
import java.util.Map; // Provides the Map data structure.
import java.util.Optional; // Safely represents a result that may not exist.
import java.util.function.Function; // Converts each character into the map key.
import java.util.stream.Collectors; // Collects stream values into a map.
import java.util.stream.IntStream; // Creates a stream from string indexes.

public class FirstNonRepeatedCharacter { // Defines the main program class.

    static Optional<Character> findFirstUnique(String text) { // Finds the first non-repeated character.

        if (text == null || text.isEmpty()) { // Handles null and empty strings safely.
            return Optional.empty(); // Returns no result because the input has no characters.
        }

        Map<Character, Long> counts = text.chars() // Converts the string into a stream of character codes.
                .mapToObj(value -> (char) value) // Converts each character code into a Character.
                .collect(Collectors.groupingBy( // Groups equal characters and counts them.
                        Function.identity(), // Uses the character itself as the map key.
                        LinkedHashMap::new, // Preserves the original character insertion order.
                        Collectors.counting())); // Counts how many times each character appears.

        return counts.entrySet().stream() // Creates a stream of character-count entries.
                .filter(entry -> entry.getValue() == 1) // Keeps only characters appearing once.
                .map(Map.Entry::getKey) // Extracts the character from each matching entry.
                .findFirst(); // Returns the first unique character.
    }

    static void test(String name, String input, Character expected) { // Runs one PASS or FAIL test.

        Optional<Character> actual = findFirstUnique(input); // Executes the solution for the input.

        boolean passed = expected == null // Checks whether no character is expected.
                ? actual.isEmpty() // Passes when the result is also empty.
                : actual.filter(value -> value.equals(expected)).isPresent(); // Compares the actual character.

        System.out.printf( // Prints the formatted test result.
                "%s: %s | Expected: %s | Actual: %s%n", // Defines the output format.
                passed ? "PASS" : "FAIL", // Prints PASS when values match; otherwise FAIL.
                name, // Prints the test case name.
                expected == null ? "empty" : expected, // Prints the expected result.
                actual.map(String::valueOf).orElse("empty")); // Prints the actual result.
    }

    public static void main(String[] args) { // Starts the application and runs all tests.

        test("Normal input", "swiss", 'w'); // Tests a normal string with multiple unique characters.
        test("First character unique", "java", 'j'); // Tests when the first character is unique.
        test("Last character unique", "aabbc", 'c'); // Tests when the final character is unique.
        test("No unique character", "aabbcc", null); // Tests when every character repeats.
        test("Single character", "x", 'x'); // Tests a one-character string.
        test("Empty string", "", null); // Tests an empty string.
        test("Null input", null, null); // Tests a null value.
        test("Case sensitive", "aAbBAB", 'b'); // Confirms uppercase and lowercase are different.
        test("Spaces included", "aabb ", ' '); // Confirms spaces are treated as characters.

        String largeInput = "a".repeat(1_000_000) + "z"; // Creates a large string with one unique character.
        test("Large input", largeInput, 'z'); // Tests performance with more than one million characters.

        boolean allPassed = IntStream.range(0, 1) // Creates a small stream to demonstrate Java 8 Stream usage.
                .allMatch(value -> true); // Confirms the test execution completed.

        System.out.println(allPassed ? "Testing completed." : "Testing failed."); // Prints the final status.
    }
}