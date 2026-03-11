package com.interview.notes.code.year.y2026.march.common.test3;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AnagramGrouper { // Declares the main public class for our application

    // Method to group anagrams using Java Streams
    public static List<List<String>> group(String[] words) { // Accepts the String array and returns a list of lists
        return Arrays.stream(words) // Converts the raw array into a sequential Stream for processing
            .collect(Collectors.groupingBy(word -> { // Groups elements into a Map based on a custom key
                char[] chars = word.toCharArray(); // Converts the current string to a char array so we can sort it
                Arrays.sort(chars); // Sorts characters alphabetically to create a uniform signature (e.g., "eat" -> "aet")
                return new String(chars); // Returns the sorted string to be used as the grouping key
            })) // The result is a Map where keys are sorted strings and values are Lists of matching original strings
            .values() // Extracts only the values (the Lists of grouped anagrams) from the Map
            .stream() // Converts that Collection of Lists back into a Stream
            .toList(); // Collects the stream into an unmodifiable List (a clean feature available in Java 16+)
    } // Closes the group method

    // Simple main method to act as our test runner without using the JUnit framework
    public static void main(String[] args) { // Standard entry point for a Java application
        
        // Test 1: Standard input from the provided requirements
        String[] test1 = {"eat", "tea", "tan", "ate", "nat", "bat"}; // Initializes the standard test array
        checkTest("Test 1 (Standard)", group(test1), 3); // Executes grouping and expects exactly 3 sub-lists

        // Test 2: Edge Case - Empty Array
        String[] test2 = {}; // Initializes an empty array to ensure we don't hit a NullPointerException
        checkTest("Test 2 (Empty)", group(test2), 0); // Executes grouping and expects 0 sub-lists

        // Test 3: Edge Case - Single String
        String[] test3 = {"a"}; // Initializes an array with a single character string
        checkTest("Test 3 (Single)", group(test3), 1); // Expects exactly 1 group containing the single string

        // Test 4: Large Data Input Case
        String[] test4 = new String[100000]; // Creates a massively sized array to test performance and memory
        Arrays.fill(test4, "listen"); // Fills all 100,000 slots with the same word
        test4[99999] = "silent"; // Changes the last word to an anagram to ensure sorting still catches it
        checkTest("Test 4 (Large Data)", group(test4), 1); // Expects exactly 1 massive group containing all 100,000 items
        
    } // Closes the main method

    // Helper method to validate test output and print PASS or FAIL
    private static void checkTest(String testName, List<List<String>> result, int expectedGroups) { // Accepts test details
        if (result.size() == expectedGroups) { // Checks if the actual number of groups matches the expected number
            System.out.println(testName + " -> PASS"); // Prints PASS to the console if the sizes match
        } else { // Fallback execution if the sizes do not match
            System.out.println(testName + " -> FAIL (Got " + result.size() + " groups, expected " + expectedGroups + ")"); // Prints FAIL and shows the mismatch
        } // Closes the if-else block
    } // Closes the helper method

} // Closes the AnagramGrouper class