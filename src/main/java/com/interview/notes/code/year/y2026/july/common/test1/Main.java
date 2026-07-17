package com.interview.notes.code.year.y2026.july.common.test1;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function; // Provides Function.identity().
import java.util.stream.Collectors; // Provides groupingBy() and counting().

public class Main { // Starts the Main class.

    static Optional<Character> firstNonRepeating(String text) { // Finds the first unique character.
        if (text == null || text.isEmpty()) return Optional.empty(); // Handles null and empty input.

        return text.chars() // Creates a stream of character values.
                .mapToObj(value -> (char) value) // Converts each integer value into a Character.
                .collect(Collectors.groupingBy( // Groups identical characters together.
                        Function.identity(), // Uses the character itself as the map key.
                        LinkedHashMap::new, // Maintains the original character order.
                        Collectors.counting())) // Counts how many times each character occurs.
                .entrySet() // Creates a stream-ready set of character-count entries.
                .stream() // Starts streaming the map entries.
                .filter(entry -> entry.getValue() == 1) // Keeps only non-repeating characters.
                .map(Map.Entry::getKey) // Extracts the character from each matching entry.
                .findFirst(); // Returns the first non-repeating character.
    } // Ends the method.

    static void test(String input, Character expected) { // Runs one test case.
        Character actual = firstNonRepeating(input).orElse(null); // Gets the result or null.
        String status = Objects.equals(actual, expected) ? "PASS" : "FAIL"; // Compares the result.
        System.out.printf("%s | Input: %s | Expected: %s | Actual: %s%n", // Prints test details.
                status, input, expected, actual); // Supplies values to the output format.
    } // Ends the test method.

    public static void main(String[] args) { // JVM starts execution here.
        test("ddaadeffgg", 'e'); // Provided example.
        test("swiss", 'w'); // Tests a unique character in the middle.
        test("aabbcc", null); // Tests when no unique character exists.
        test("x", 'x'); // Tests a single-character string.
        test("", null); // Tests an empty string.
        test(null, null); // Tests a null value.

        String largeInput = "a".repeat(100_000) // Creates many repeated 'a' characters.
                + "z" // Adds one unique character.
                + "b".repeat(100_000); // Creates many repeated 'b' characters.

        test(largeInput, 'z'); // Verifies behavior with a large input.
    } // Ends the main method.
} // Ends the Main class.