package com.interview.notes.code.year.y2026.september.common.test1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Main {

    /**
     * Iterates through the string to find duplicated characters, counting them,
     * and sorting them by their first duplicated occurrence.
     */
    public static String countDuplicates(String str) {
        var counts = new HashMap<Character, Integer>(); // Creates a map to track the total frequency of each character
        var order = new ArrayList<Character>(); // Creates a list to save characters in the exact order they become duplicates

        for (char c : str.toCharArray()) { // Converts string to a char array and loops through each character
            int count = counts.getOrDefault(c, 0) + 1; // Calculates the new frequency by getting the existing count (or 0) and adding 1
            counts.put(c, count); // Stores the updated frequency back into the map for this specific character
            
            if (count == 2) { // Checks if this is exactly the second time we've seen this character
                order.add(c); // Appends the character to our order list since it has just triggered the duplicate condition
            } // Ends the duplicate check block
        } // Ends the character iteration loop

        return order.stream() // Converts our ordered list of duplicates into a Stream for concise mapping and formatting
                .map(c -> (c == ' ' ? "Space" : c) + " " + counts.get(c)) // Checks if the char is a space to print "Space", otherwise prints the char, followed by its total count
                .collect(Collectors.joining(", ")); // Aggregates the individually mapped strings into one final string separated by commas
    }

    static void main(String[] args) {
        // Run standard test cases
        runTest("Example 1", "abc ab ae b c dac", "a 4, b 3, Space 5, c 3");
        runTest("Example 2", "abcf a b a b c dac", "a 4, Space 6, b 3, c 3");
        
        // Run edge case: no duplicates
        runTest("No Duplicates", "abcdefg", "");
        
        // Run edge case: all same characters
        runTest("All Same", "aaaaa", "a 5");

        // Run Large Data test
        // Generates a massive string by repeating "abc " 250,000 times (1,000,000 characters)
        String largeInput = "abc ".repeat(250_000);
        // We expect 'a', 'b', 'c', and ' ' to each have 250,000 occurrences.
        // In "abc abc ...", the second 'a' appears first, then second 'b', second 'c', second space.
        String expectedLarge = "a 250000, b 250000, c 250000, Space 250000";
        runTest("Large Data (1M chars)", largeInput, expectedLarge);
    }

    /**
     * Custom lightweight testing method to validate expected vs actual results
     */
    private static void runTest(String testName, String input, String expected) {
        long startTime = System.currentTimeMillis(); // Captures start time for performance tracking
        String actual = countDuplicates(input); // Executes the target method with the given input
        long duration = System.currentTimeMillis() - startTime; // Calculates total execution time
        
        if (expected.equals(actual)) { // Validates if the returned string exactly matches our expected output
            System.out.println("PASS -> " + testName + " (Took " + duration + "ms)"); // Prints success message
        } else { // Handles the scenario where the outputs do not match
            System.out.println("FAIL -> " + testName); // Prints failure indicator
            System.out.println("   Expected: [" + expected + "]"); // Displays what the program should have output
            System.out.println("   Actual:   [" + actual + "]"); // Displays what the program actually output
        } // Ends the validation block
    }
}