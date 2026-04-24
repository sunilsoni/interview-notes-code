package com.interview.notes.code.year.y2026.april.common.test9;

import java.util.*; // Imports standard utility classes like Map, LinkedHashMap, and List.
import java.util.stream.Collectors;

public class MapSorter { // Defines the main class for our sorting utility.

    public static Map<String, Integer> sortMapByValue(Map<String, Integer> inputMap) { // Method signature accepting a Map and returning a sorted Map.
        return inputMap.entrySet() // Retrieves the set of key-value pairs (entries) from the input map.
                .stream() // Converts the set of entries into a sequential stream for processing.
                .sorted(Map.Entry.comparingByValue()) // Sorts the stream elements using the built-in value comparator.
                .collect(Collectors.toMap( // Collects the sorted stream elements back into a new Map.
                        Map.Entry::getKey, // Method reference to extract the key for the new map entry.
                        Map.Entry::getValue, // Method reference to extract the value for the new map entry.
                        (oldValue, newValue) -> oldValue, // Merge function to resolve key collisions (impossible here, but required by syntax).
                        LinkedHashMap::new // Method reference instructing the collector to use a LinkedHashMap to preserve the sorted order.
                )); // Closes the collect method and terminates the stream pipeline.
    } // Closes the sorting method.

    public static void main(String[] args) { // The main method where execution begins and testing occurs.
        
        // --- TEST CASE 1: Image Data --- // Comment marking the first test case.
        var input1 = Map.of("A", 20, "B", 50, "C", 11, "E", 10, "P", 40, "R", 30); // Uses Java's var and Map.of to create an immutable map of the image data.
        var expectedKeys1 = List.of("E", "C", "A", "R", "P", "B"); // Defines the expected order of keys when sorted by value ascending.
        var result1 = sortMapByValue(input1); // Executes the sorting method on the input data.
        var actualKeys1 = new ArrayList<>(result1.keySet()); // Extracts the keys from the sorted map into a List to check their order.
        checkTest("Test 1 (Image Data)", actualKeys1.equals(expectedKeys1)); // Calls the helper method to verify if actual keys match expected keys and prints PASS/FAIL.

        // --- TEST CASE 2: Empty Map --- // Comment marking the second test case for edge scenarios.
        var input2 = Map.<String, Integer>of(); // Creates an empty, immutable map.
        var result2 = sortMapByValue(input2); // Executes the sorting method on the empty map.
        checkTest("Test 2 (Empty Map)", result2.isEmpty()); // Verifies that sorting an empty map results in an empty map.

        // --- TEST CASE 3: Large Data --- // Comment marking the third test case for volume handling.
        var largeInput = new HashMap<String, Integer>(); // Creates a mutable HashMap to hold a large number of entries.
        for (int i = 100000; i > 0; i--) { // Loops 100,000 times in reverse order to simulate unsorted bulk data.
            largeInput.put("Key" + i, i); // Inserts entries where the value decreases, meaning "Key1" mapped to 1 should end up first.
        } // Closes the for loop.
        var result3 = sortMapByValue(largeInput); // Sorts the 100,000 entry map.
        var firstKey = result3.keySet().iterator().next(); // Grabs the very first key from the newly sorted map's iterator.
        checkTest("Test 3 (Large Data)", firstKey.equals("Key1")); // Verifies the map sorted successfully by checking if the lowest value's key is at the front.
    } // Closes the main method.

    private static void checkTest(String testName, boolean isPassed) { // Helper method to standardize our console output for testing.
        System.out.println(testName + ": " + (isPassed ? "PASS" : "FAIL")); // Uses a ternary operator to print the test name alongside "PASS" or "FAIL".
    } // Closes the helper method.
} // Closes the class.