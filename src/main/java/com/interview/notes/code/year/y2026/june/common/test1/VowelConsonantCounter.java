package com.interview.notes.code.year.y2026.june.common.test1;

import java.util.Arrays;  // Used for Arrays.stream() and Arrays.fill().
import java.util.Objects; // Used to remove null strings safely.

public class VowelConsonantCounter { // Main program class.

    static Count count(String[] words) { // Counts vowels and consonants.

        if (words == null) return new Count(0, 0); // Handles a null input array.

        var totals = Arrays.stream(words) // Creates a stream from the string array.
                .filter(Objects::nonNull) // Ignores null elements inside the array.
                .flatMapToInt(String::chars) // Converts all strings into characters.
                .map(Character::toLowerCase) // Converts uppercase letters to lowercase.
                .collect( // Collects vowel and consonant counts.
                        () -> new long[2], // Creates counters: index 0 vowel, index 1 consonant.
                        (result, ch) -> { // Processes each character.
                            if (Character.isLetter(ch)) { // Ignores numbers and special characters.
                                if ("aeiou".indexOf(ch) >= 0) result[0]++; // Counts a vowel.
                                else result[1]++; // Counts every other letter as a consonant.
                            } // Ends the letter check.
                        }, // Ends character processing.
                        (left, right) -> { // Combines results if a parallel stream is used.
                            left[0] += right[0]; // Combines vowel counts.
                            left[1] += right[1]; // Combines consonant counts.
                        } // Ends result combining.
                ); // Finishes stream collection.

        return new Count(totals[0], totals[1]); // Returns both counts.
    } // Ends count method.

    static void test(String name, String[] input, Count expected) { // Runs one test.
        var actual = count(input); // Calls the business logic.
        var status = actual.equals(expected) ? "PASS" : "FAIL"; // Compares results.
        System.out.printf("%s: %s | Expected=%s, Actual=%s%n", // Prints test details.
                name, status, expected, actual); // Supplies the output values.
    } // Ends test method.

    public static void main(String[] args) { // Program execution starts here.

        test( // Tests the input given in the question.
                "Given input", // Test name.
                new String[]{"Hello", ",", "World", "!"}, // Input values.
                new Count(3, 7) // Expected vowels and consonants.
        ); // Ends the given-input test.

        test( // Tests an empty array.
                "Empty input", // Test name.
                new String[]{}, // No strings.
                new Count(0, 0) // Nothing should be counted.
        ); // Ends the empty-input test.

        test( // Tests characters that are not letters.
                "Numbers and symbols", // Test name.
                new String[]{"123", "@#!"}, // Numbers and special characters.
                new Count(0, 0) // They must be ignored.
        ); // Ends the symbols test.

        test( // Tests uppercase and lowercase letters.
                "Mixed case", // Test name.
                new String[]{"AEIOU", "bcDF"}, // Mixed-case input.
                new Count(5, 4) // Five vowels and four consonants.
        ); // Ends the mixed-case test.

        test( // Tests null values and spaces.
                "Null and spaces", // Test name.
                new String[]{"Java 21", null, "Stream"}, // Contains spaces, digits, and null.
                new Count(4, 6) // Expected valid-letter counts.
        ); // Ends the null-value test.

        test( // Tests a completely null input.
                "Null array", // Test name.
                null, // Null array input.
                new Count(0, 0) // Returns zero instead of throwing an exception.
        ); // Ends the null-array test.

        var large = new String[100_000]; // Creates a large input array.
        Arrays.fill(large, "HelloWorld"); // Adds a 10-character value to every position.

        test( // Tests performance with one million characters.
                "Large input", // Test name.
                large, // Large array.
                new Count(300_000, 700_000) // Each value has 3 vowels and 7 consonants.
        ); // Ends the large-input test.
    } // Ends main method.

    record Count(long vowels, long consonants) {} // Stores both final counts.
} // Ends program class.