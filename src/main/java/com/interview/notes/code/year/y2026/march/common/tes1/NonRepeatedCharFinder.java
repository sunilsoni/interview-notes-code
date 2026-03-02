package com.interview.notes.code.year.y2026.march.common.tes1;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class NonRepeatedCharFinder { // This is our main class to hold the logic for finding the unique character.

    public static Character findFirstNonRepeated(String str) { // This function takes a string and returns the first unique character, or null if none exists.
        if (str == null || str.isEmpty()) { // We check if the input is null or empty first to prevent crashing on bad inputs.
            return null; // We return null immediately because there are zero characters to check in an empty or missing string.
        } // We close the validation if-block.

        var charCounts = str.chars() // We use 'var' (a modern Java feature) for brevity, starting by creating an integer stream of character codes from the string.
            .mapToObj(c -> (char) c) // We convert each integer code back into a Character object so we can properly group them.
            .collect(Collectors.groupingBy(c -> c, LinkedHashMap::new, Collectors.counting())); // We count occurrences of each character and store them in a LinkedHashMap to preserve the insertion order.

        return charCounts.entrySet().stream() // We take the entries from our populated map and turn them back into a stream so we can filter them.
            .filter(entry -> entry.getValue() == 1) // We filter the stream to only keep characters that have a count of exactly 1 (meaning they are unique).
            .map(Map.Entry::getKey) // We map the remaining entries to grab just the character itself (the key), throwing away the count.
            .findFirst() // We pull the very first character out of our filtered stream, which represents our answer.
            .orElse(null); // We return that character, or null if the stream is empty (meaning every single character was repeated).
    } // We close the finder method.

    public static void main(String[] args) { // This is our simple main method for testing, completely avoiding JUnit as requested.
        runTest("interview", 'n'); // Test 1: The primary example from your prompt where 'n' is the first unique char.
        runTest("swiss", 'w'); // Test 2: A word where the first letter repeats ('s'), making 'w' the first unique one.
        runTest("aabbcc", null); // Test 3: A word where every character repeats, so we expect null.
        runTest("", null); // Test 4: An empty string to make sure our early validation check works properly.
        
        var largeString = "a".repeat(1000000) + "b" + "c".repeat(1000000); // Test 5: We create a 2-million character string using modern Java's repeat() method to simulate heavy data inputs.
        runTest(largeString, 'b'); // We test the massive string where 'b' is the only unique character buried securely in the middle.
    } // We close the main method.

    private static void runTest(String input, Character expected) { // This is our helper method to run an individual test and neatly print PASS or FAIL.
        var result = findFirstNonRepeated(input); // We call our function with the test input and store the result.
        var passed = (expected == null && result == null) || (expected != null && expected.equals(result)); // We check if the actual result perfectly matches what we expected to happen.
        
        var inputLabel = input.length() > 20 ? "Large String (" + input.length() + " chars)" : input; // We create a short label for the output so printing massive strings doesn't flood and break the console.
        var status = passed ? "PASS" : "FAIL"; // We set the status to PASS if the logic worked, or FAIL if it didn't.
        System.out.println(status + " | Input: " + inputLabel + " | Expected: " + expected + " | Got: " + result); // We print the clear, single-line summary of the test case to the console.
    } // We close the test helper method.
} // We close the class.