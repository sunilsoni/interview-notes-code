package com.interview.notes.code.year.y2026.april.common.test7;

import java.util.LinkedHashMap; // Required to preserve the exact order characters appear in the string
import java.util.Map; // The base Map interface needed for our frequency dictionary
import java.util.function.Function; // Used for the identity function to map elements to themselves
import java.util.stream.Collectors; // Provides the groupingBy and counting tools for our stream

public class FirstNonRepeating { // Main class declaration

    public static Character findFirstUnique(String input) { // Takes the input string and returns the first unique Character wrapper
        if (input == null || input.isEmpty()) { // Fast-fail check to prevent NullPointerException or wasted processing
            return null; // Return null immediately if the string is empty or missing
        } // End of sanity check
        
        return input.chars() // Converts the string into an IntStream of underlying character ASCII/Unicode values
            .mapToObj(c -> (char) c) // Boxes each integer value back into a Character object for the map
            .collect(Collectors.groupingBy( // Gathers the stream elements into a Map based on our rules
                Function.identity(), // The key is the character itself
                LinkedHashMap::new, // CRITICAL: Forces the use of LinkedHashMap so we don't lose the string's left-to-right order
                Collectors.counting() // The value is the total count of how many times that character appeared
            )) // Finishes building the Map<Character, Long>
            .entrySet().stream() // Converts our newly populated LinkedHashMap into a fresh stream of Key-Value pairs
            .filter(entry -> entry.getValue() == 1L) // Keeps only the map entries where the character count is exactly 1
            .map(Map.Entry::getKey) // Discards the counts, leaving us with a stream of just the unique characters
            .findFirst() // Grabs the very first character that survived the filter (guaranteed to be the first in the string)
            .orElse(null); // Safely returns null if the stream is empty (meaning every character repeated)
    } // End of the core algorithm method

    public static void main(String[] args) { // Standalone entry point to run our custom tests without JUnit
        test("abbc", 'a'); // Test case 1: Standard case where the first char is the answer
        test("aabbcd", 'c'); // Test case 2: Standard case where the answer is buried in the middle
        test("aabbcc", null); // Test case 3: Edge case where absolutely no characters are unique
        test("", null); // Test case 4: Edge case checking empty string handling
        test(null, null); // Test case 5: Edge case checking null safety
        
        var largeInput = "a".repeat(1000000) + "b".repeat(1000000) + "c"; // Uses Java 11+ repeat() and Java 10+ var to simulate heavy load cleanly
        test(largeInput, 'c'); // Test case 6: Stress test processing 2 million characters to ensure streams handle large data
    } // End of test suite execution

    private static void test(String input, Character expected) { // Helper method to evaluate tests and print PASS/FAIL console output
        var result = findFirstUnique(input); // Executes our logic using 'var' (Java 21 feature subset) for clean local inference
        var passed = (expected == null && result == null) || (expected != null && expected.equals(result)); // Safely compares expected vs actual, handling nulls
        var status = passed ? "PASS" : "FAIL"; // Ternary operator to assign a readable string tag
        var displayInput = input == null ? "null" : (input.length() > 15 ? input.substring(0, 15) + "..." : input); // Truncates massive strings so the console remains readable
        System.out.println("[" + status + "] Input: '" + displayInput + "' | Expected: " + expected + " | Got: " + result); // Prints the final formatted test report line
    } // End of test helper method
} // End of class