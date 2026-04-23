package com.interview.notes.code.year.y2026.april.common.test10;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class VowelCounter {

    public static void main(String[] args) {
        // Define the input data as a list for easy streaming
        List<String> countries = List.of("india", "australia", "pakistan", "canada", "chily", "america", "arab");

        // Execute the search logic
        String result = findCountryWithMaxVowels(countries);
        
        // Run internal tests to verify correctness
        runTests(result);
    }

    /**
     * Finds the country with the most vowels using Java 21 Stream logic.
     */
    public static String findCountryWithMaxVowels(List<String> list) {
        return list.stream() // Convert the list into a pipeline for processing
            .max(Comparator.comparingLong(VowelCounter::countVowels)) // Compare countries by their vowel count
            .orElse(""); // Return an empty string if the list is empty
    }

    /**
     * Helper method to count vowels in a single word.
     */
    private static long countVowels(String word) {
        // Check if character is a vowel (a, e, i, o, u) ignoring case
        return word.toLowerCase().chars() // Convert string to a stream of characters
            .filter(c -> "aeiou".indexOf(c) != -1) // Keep only characters that exist in the vowel string
            .count(); // Return the total count of filtered characters
    }

    /**
     * Simple main-method based testing to verify PASS/FAIL status.
     */
    public static void runTests(String actualResult) {
        System.out.println("--- Starting Tests ---");

        // Test Case 1: Provided Data
        String expected = "australia"; // Australia has 5 vowels (a, u, a, i, a)
        checkResult("Provided List Test", expected, actualResult);

        // Test Case 2: Edge Case - Empty Strings
        checkResult("Empty Input Test", "", findCountryWithMaxVowels(List.of()));

        // Test Case 3: Large Input Simulation
        List<String> largeData = Collections.nCopies(100000, "australia");
        checkResult("Large Data Test", "australia", findCountryWithMaxVowels(largeData));

        System.out.println("--- Tests Completed ---");
    }

    private static void checkResult(String testName, String expected, String actual) {
        // Check if the actual result matches our expected logic
        if (expected.equals(actual)) {
            System.out.println(testName + ": PASS"); // Logic is correct
        } else {
            System.out.println(testName + ": FAIL (Expected " + expected + " but got " + actual + ")"); // Logic error detected
        }
    }
}