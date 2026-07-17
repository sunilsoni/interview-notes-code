package com.interview.notes.code.year.y2026.july.google.test1;

import java.util.*; // Import standard utility classes like List, Map, Set, Queue
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ListCompressor { // Define the main class that will hold our logic and tests

    public static boolean canCompress(List<List<Integer>> lists) { // Method to check if lists can be merged
        var graph = new HashMap<Integer, Set<Integer>>(); // Map to store each number and the numbers that directly follow it
        var inDegree = new HashMap<Integer, Integer>(); // Map to track how many numbers must come before a specific number

        lists.stream().flatMap(List::stream).distinct().forEach(node -> { // Flatten all lists into a single stream, filter to unique numbers, and loop over them
            graph.put(node, new HashSet<>()); // Initialize an empty set for the current number to hold its future followers
            inDegree.put(node, 0); // Initialize the prerequisite count (incoming edges) for this number to 0
        }); // End of initialization loop

        lists.forEach(list -> { // Loop through each sub-list provided in the input
            IntStream.range(0, list.size() - 1).forEach(i -> { // Loop through valid indices to grab adjacent pairs
                var u = list.get(i); // The current number in the list (must come before the next)
                var v = list.get(i + 1); // The next number in the list (must come after the current)
                if (graph.get(u).add(v)) { // Attempt to add 'v' to 'u's followers; returns true if it's a new, unique relationship
                    inDegree.merge(v, 1, Integer::sum); // If new, increase 'v's prerequisite count by 1
                } // End of if-statement
            }); // End of inner pair-processing loop
        }); // End of outer list-processing loop

        var queue = inDegree.entrySet().stream() // Create a stream from the in-degree map entries
                .filter(entry -> entry.getValue() == 0) // Filter to keep only numbers that currently have 0 prerequisites
                .map(Map.Entry::getKey) // Extract just the numbers (the keys) from the filtered entries
                .collect(Collectors.toCollection(LinkedList::new)); // Collect these ready-to-process numbers into a Queue

        var processedCount = 0; // Counter to track exactly how many numbers we successfully process

        while (!queue.isEmpty()) { // Continue looping as long as there are numbers ready to be processed
            var current = queue.poll(); // Remove and retrieve the next number from the front of the queue
            processedCount++; // Increment our processed counter because we are finalizing this number's position

            graph.get(current).forEach(neighbor -> { // Loop through all the numbers that were waiting for 'current' to be processed
                var updatedDegree = inDegree.merge(neighbor, -1, Integer::sum); // Decrease their prerequisite count by 1
                if (updatedDegree == 0) { // Check if the neighbor now has zero remaining prerequisites
                    queue.add(neighbor); // If zero, it's ready, so add it to the processing queue
                } // End of zero-check if-statement
            }); // End of neighbor processing loop
        } // End of the while queue loop

        return processedCount == inDegree.size(); // If processed numbers equals total unique numbers, we succeeded (no cycles)
    } // End of method

    public static void main(String[] args) { // Main method to run our manual tests without JUnit
        System.out.println("Running test cases...\n"); // Print a starting header

        // Test Case 1: From the provided image
        var list1 = List.of(1, 2, 3, 6, 9, 15, 12); // First list from image
        var list2 = List.of(2, 6, 8, 10, 11); // Second list from image
        var input1 = List.of(list1, list2); // Combine them into the main input
        runTest("Test 1 (Image Example)", input1, true); // Run the test, expecting true

        // Test Case 2: Impossible scenario (Cycle)
        var list3 = List.of(1, 2, 3); // A asserts 1->2->3
        var list4 = List.of(3, 1); // B asserts 3->1 (Creates a cycle)
        var input2 = List.of(list3, list4); // Combine into input
        runTest("Test 2 (Cycle / Contradiction)", input2, false); // Run the test, expecting false

        // Test Case 3: Single list
        var list5 = List.of(5, 4, 3, 2, 1); // Just one list, naturally valid
        var input3 = List.of(list5); // Combine into input
        runTest("Test 3 (Single List)", input3, true); // Run test, expecting true

        // Test Case 4: Large Data Input Scenario
        var largeInput = new ArrayList<List<Integer>>(); // Create a container for massive input
        for (int i = 0; i < 50000; i++) { // Loop 50,000 times to generate big data
            largeInput.add(List.of(i, i + 1, i + 2)); // Add small ordered sublists creating a massive linear chain
        } // End loop
        runTest("Test 4 (Large Data - 50,000 lists)", largeInput, true); // Run large scale test, expecting true
    } // End main

    private static void runTest(String testName, List<List<Integer>> input, boolean expected) { // Helper to format output
        var result = canCompress(input); // Execute the logic on the input
        if (result == expected) { // Check if the result matches our expectation
            System.out.println("PASS - " + testName); // Print PASS if they match
        } else { // Otherwise...
            System.out.println("FAIL - " + testName + " (Expected: " + expected + ", Got: " + result + ")"); // Print FAIL with details
        } // End if/else
    } // End helper method
} // End class