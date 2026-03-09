package com.interview.notes.code.year.y2026.march.common.tes1;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class MapIterator { // Declares our main class to hold the logic and tests

    public static long sumMapValues(Map<String, Integer> map) { // Method takes a map and returns a sum so we can test it
        return map.entrySet() // Grabs the set of key-value pairs from the map so we can iterate over them
                .stream() // Converts that set into a Stream for simple, functional processing
                .mapToInt(Map.Entry::getValue) // Extracts only the integer value from each entry pair
                .sum(); // Adds all those extracted values together and returns the final total
    } // Closes the sumMapValues method

    public static void main(String[] args) { // Main method serves as our entry point and custom testing framework
        
        // Test Case 1: Standard small map
        var map1 = Map.of("A", 1, "B", 2, "C", 3); // Uses Java's var and Map.of to quickly create a map with 3 items
        test("Small Map", sumMapValues(map1) == 6); // Tests if 1+2+3 equals 6 and prints PASS or FAIL

        // Test Case 2: Empty map edge case
        var emptyMap = new HashMap<String, Integer>(); // Creates a completely empty HashMap
        test("Empty Map", sumMapValues(emptyMap) == 0); // Tests if the stream correctly handles empty data by returning 0

        // Test Case 3: Large Data Map
        var largeMap = new HashMap<String, Integer>(); // Creates a new HashMap to hold our massive dataset
        IntStream.range(0, 1000000) // Creates a fast integer stream from 0 up to 999,999
                 .forEach(i -> largeMap.put("Key" + i, 1)); // Puts 1 million entries into the map, each with a value of 1
        test("Large Data Map", sumMapValues(largeMap) == 1000000); // Tests if the stream can process 1 million items without failing
        
    } // Closes the main method

    private static void test(String name, boolean passed) { // Helper method to format our test results nicely
        System.out.println(name + ": " + (passed ? "PASS" : "FAIL")); // Prints the test name, evaluates the boolean, and prints PASS or FAIL
    } // Closes the test helper method

} // Closes the MapIterator class