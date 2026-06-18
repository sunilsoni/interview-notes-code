package com.interview.notes.code.year.y2026.june.common.test5;

import java.util.*; // Brings in fundamental data structures like Map, List, and HashMap.
import java.util.stream.Collectors; // Imports Stream collectors to assemble data back into maps.

public class MapSorter { // Defines our main execution class.
    public static void main(String[] args) { // Main method serves as our application runner without JUnit.
        runBaseTestCase(); // Runs validation on your provided specific sample map.
        runLargeTestCase(); // Runs performance and accuracy test on 100,000 entries.
    } // Closes main method.

    private static void runBaseTestCase() { // Setup and validation for the specific prompt input.
        Map<String, Integer> map = new HashMap<>(); // Initializes a HashMap to hold our unsorted test items.
        map.put("A", 1); // Maps key "A" to value 1.
        map.put("B", 2); // Maps key "B" to value 2 (fixing the prompt's syntax typo).
        map.put("C", 3); // Maps key "C" to value 3.
        map.put("D", 5); // Maps key "D" to value 5.
        map.put("E", 6); // Maps key "E" to value 6.

        Map<String, Integer> sortedMap = sortMapDescending(map); // Invokes sorting and captures the ordered result map.

        List<String> expectedOrder = List.of("E", "D", "C", "B", "A"); // Expected order of keys based on descending values.
        List<String> actualOrder = new ArrayList<>(sortedMap.keySet()); // Grabs the ordered keys from our processed map.

        if (expectedOrder.equals(actualOrder)) { // Checks if the generated order perfectly matches expectations.
            System.out.println("Base Test: PASS"); // Prints success indicator if orders match.
        } else { // Execution branch if sorting layout is incorrect.
            System.out.println("Base Test: FAIL"); // Prints failure indicator if orders mismatch.
        } // Closes conditional block.
    } // Closes base test method.

    private static void runLargeTestCase() { // Scalability test method to ensure code handles heavy workloads efficiently.
        Map<String, Integer> largeMap = new HashMap<>(); // Instantiates a large container map.
        for (int i = 0; i < 100_000; i++) { // Loops a hundred thousand times to build scale.
            largeMap.put("Key" + i, i); // Pairs a distinct string key with its incremental integer value.
        } // Closes data generation loop.

        Map<String, Integer> sortedLargeMap = sortMapDescending(largeMap); // Sorts the 100k entries using our stream logic.

        String firstKey = sortedLargeMap.keySet().iterator().next(); // Extracts the topmost key from the sorted collection.
        if ("Key99999".equals(firstKey) && sortedLargeMap.size() == 100_000) { // Validates that the max value item scaled to index 0 and data size is intact.
            System.out.println("Large Data Test: PASS"); // Prints success indicator for the heavy load test.
        } else { // Execution branch if large volume sorting encounters an issue.
            System.out.println("Large Data Test: FAIL"); // Prints failure indicator for scale test.
        } // Closes validation conditional.
    } // Closes large data test method.

    private static Map<String, Integer> sortMapDescending(Map<String, Integer> inputMap) { // Stream-based core utility method to perform descending sort.
        return inputMap.entrySet() // Accesses the underlying key-value pair elements of the target map.
            .stream() // Converts the elements into a sequential stream flow for transformation.
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()) // Sorts stream components by values in an inverted manner.
            .collect(Collectors.toMap( // Packages the stream pipeline back into a fresh map object.
                Map.Entry::getKey, // Extracts the original key to act as the new map's key.
                Map.Entry::getValue, // Extracts the original value to act as the new map's value.
                (oldValue, newValue) -> oldValue, // Merge rule placeholder required by the compiler for collision mapping.
                LinkedHashMap::new // Enforces use of LinkedHashMap to lock in and preserve the stream's final sorted order.
            )); // Finalizes mapping collection operations.
    } // Closes sorting method.
}