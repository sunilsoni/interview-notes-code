package com.interview.notes.code.year.y2026.feb.microsoft.test11;

import java.util.*;

public class TicketGrouping { // Main class container for our solution and tests

    // Core method to identify and group all related tickets based on root cause
    public static List<List<Integer>> groupTickets(List<int[]> pairs) { // Takes input pairs, returns grouped lists
        var graph = new HashMap<Integer, Set<Integer>>(); // We need a map to act as our graph, linking tickets to their neighbors
        
        for (var pair : pairs) { // Loop through every single input pair array to build our connections
            graph.computeIfAbsent(pair[0], k -> new HashSet<>()).add(pair[1]); // If ticket 0 has no list, make one, then add ticket 1
            graph.computeIfAbsent(pair[1], k -> new HashSet<>()).add(pair[0]); // Do the reverse since relationships go both ways
        } // End of graph building loop
        
        var visited = new HashSet<Integer>(); // Set used to track tickets we have already sorted into a group
        var result = new ArrayList<List<Integer>>(); // This will hold our final output: a list of the grouped lists
        
        for (var node : graph.keySet()) { // Iterate over every unique ticket ID we found in the graph
            if (!visited.contains(node)) { // If this ticket isn't in a group yet, it means we found a new group
                var currentGroup = new ArrayList<Integer>(); // Create a fresh, empty list to hold this new group's tickets
                var queue = new LinkedList<Integer>(); // Use a Queue for Breadth-First Search to find all connected tickets
                
                queue.add(node); // Add our starting ticket to the queue to begin the search
                visited.add(node); // Mark this starting ticket as visited so we don't process it again
                
                while (!queue.isEmpty()) { // Keep processing as long as there are connected tickets left in the queue
                    var curr = queue.poll(); // Remove and get the ticket at the front of the queue
                    currentGroup.add(curr); // Add this ticket to our current working group
                    
                    for (var neighbor : graph.get(curr)) { // Look up all directly related tickets (neighbors) for the current ticket
                        if (visited.add(neighbor)) { // Try to add neighbor to visited set; returns true if it wasn't there already
                            queue.add(neighbor); // Because it's a newly discovered ticket, add it to the queue to check its neighbors later
                        } // End of visited check
                    } // End of neighbor iteration
                } // End of queue processing loop
                
                result.add(currentGroup); // The queue is empty, so this group is fully built; add it to the final results
            } // End of unvisited node check
        } // End of node iteration
        
        return result; // Return the fully constructed list of groups back to the caller
    } // End of grouping method

    // -------------------------------------------------------------------------------------------------
    // TESTING SECTION - Using a simple main method instead of JUnit
    // -------------------------------------------------------------------------------------------------

    public static void main(String[] args) { // Standard entry point for our standalone tests
        var tests = new ArrayList<TestCase>(); // List to hold all the test cases we are about to define

        // Test Case 1: Standard input from the problem description
        tests.add(new TestCase( // Add a new test case object
            "Test 1 - Standard", // Name of the test
            List.of(new int[]{0,1}, new int[]{2,4}, new int[]{3,4}, new int[]{5,8}, new int[]{8,7}, new int[]{9,5}, new int[]{0,4}, new int[]{6,7}), // Input pairs
            List.of(List.of(0,1,2,3,4), List.of(5,6,7,8,9)) // Expected grouped output
        )); // End of test case 1

        // Test Case 2: One giant connected group from the problem description
        tests.add(new TestCase( // Add second test case
            "Test 2 - Single Large Group", // Name of the test
            List.of(new int[]{0,1}, new int[]{2,4}, new int[]{3,4}, new int[]{5,8}, new int[]{8,7}, new int[]{9,5}, new int[]{0,4}, new int[]{6,7}, new int[]{2,8}), // Input pairs bridging the two groups
            List.of(List.of(0,1,2,3,4,5,6,7,8,9)) // Expected single large group
        )); // End of test case 2

        // Test Case 3: Multiple smaller isolated groups from the problem description
        tests.add(new TestCase( // Add third test case
            "Test 3 - Many Small Groups", // Name of the test
            List.of(new int[]{0,1}, new int[]{2,4}, new int[]{6,3}, new int[]{5,8}, new int[]{9,7}), // Disconnected input pairs
            List.of(List.of(0,1), List.of(2,4), List.of(6,3), List.of(5,8), List.of(9,7)) // Expected multiple small groups
        )); // End of test case 3

        // Run standard tests
        for (var tc : tests) { // Loop through the test cases we defined
            var actual = groupTickets(tc.input()); // Call our core method to get the actual result
            var passed = compareResults(actual, tc.expected()); // Compare actual result to expected using helper method
            System.out.println(tc.name() + ": " + (passed ? "PASS" : "FAIL")); // Print the outcome to the console
        } // End of test execution loop

        // Test Case 4: Large Data Input (Stress Test)
        runLargeDataTest(); // Call separate method to generate and test massive data
    } // End of main method

    // Helper method to safely compare two lists of lists where order doesn't matter
    private static boolean compareResults(List<List<Integer>> actual, List<List<Integer>> expected) { // Takes actual and expected lists
        var normalizedActual = normalize(actual); // Standardize the actual list's sorting
        var normalizedExpected = normalize(expected); // Standardize the expected list's sorting
        return normalizedActual.equals(normalizedExpected); // Return true if they now match exactly
    } // End of compare method

    // Uses Java 8 Streams to sort inner lists, then sort outer lists, so they can be reliably compared
    private static List<List<Integer>> normalize(List<List<Integer>> input) { // Takes an unordered nested list
        return input.stream() // Convert the outer list into a stream
            .map(list -> list.stream().sorted().toList()) // Sort every integer inside the inner lists
            .sorted(Comparator.comparing(Object::toString)) // Sort the outer lists by their string representations
            .toList(); // Collect the heavily sorted streams back into a final List
    } // End of normalize method

    // Method to test program limits with large inputs
    private static void runLargeDataTest() { // No arguments, just runs the stress test
        var largeInput = new ArrayList<int[]>(); // List to hold massive amount of pairs
        var expectedGroup = new ArrayList<Integer>(); // List to hold massive expected single group

        for (int i = 0; i < 50000; i++) { // Loop 50,000 times to create a massive chain
            largeInput.add(new int[]{i, i + 1}); // Link ticket i to i+1, creating one massive root cause
            expectedGroup.add(i); // Add the ticket to expected output list
        } // End of large data loop
        expectedGroup.add(50000); // Add the final tail ticket to the expected list

        var expectedResult = List.of((List<Integer>) expectedGroup); // Wrap the single huge group into the required nested list format
        var actual = groupTickets(largeInput); // Run our algorithm against the 50,000 pairs

        var passed = compareResults(actual, expectedResult); // Check if the 50,000 items were grouped correctly
        System.out.println("Test 4 - Large Data (50,000 elements): " + (passed ? "PASS" : "FAIL")); // Print result
    } // End of large data test method

    // We use a Java Record to cleanly define what a test case looks like
    record TestCase(String name, List<int[]> input, List<List<Integer>> expected) {} // Holds test name, input data, and expected output

} // End of class