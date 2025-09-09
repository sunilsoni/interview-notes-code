package com.interview.notes.code.year.y2025.september.assesment.test7;

import java.util.*;

/*
**Final Question (combined and cleaned):**

Deliver a feature for users that checks how many degrees of separation exist between two different businesses using company
* relationship data. For example, Business A regularly buys goods from Business B, which regularly buys from Business C.
* Business A is one degree separated from Business C. The code must return the shortest path if there are multiple.

**Example:**

* Company A purchases dairy from Company B.
* Company B purchases pet feed from Company C.
* Relationship chain: A <--> B <--> C
* Degree of separation between A and C = 1.


 */
public class BusinessNetwork {

    // Method to calculate shortest degree of separation between two businesses
    public static int degreeOfSeparation(Map<String, List<String>> network, String start, String target) {
        // If either business does not exist, return -1
        if (!network.containsKey(start) || !network.containsKey(target)) {
            return -1;
        }

        // Use BFS (Breadth First Search) because it guarantees shortest path in an unweighted graph
        Queue<String> queue = new LinkedList<>();
        Map<String, Integer> distance = new HashMap<>(); // store distance from start

        queue.add(start);
        distance.put(start, 0);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            int currentDist = distance.get(current);

            // If we reached target, return the degree of separation
            if (current.equals(target)) {
                return currentDist - 1; // subtract 1 because degree = pathLength - 1
            }

            // Traverse neighbors (using streams for Java 8 style)
            List<String> neighbors = network.getOrDefault(current, Collections.emptyList());
            neighbors.stream()
                    .filter(n -> !distance.containsKey(n)) // only visit unvisited
                    .forEach(n -> {
                        queue.add(n);
                        distance.put(n, currentDist + 1);
                    });
        }

        return -1; // no path found
    }

    // Helper method to build bidirectional relationships
    public static void addRelation(Map<String, List<String>> network, String a, String b) {
        network.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
        network.computeIfAbsent(b, k -> new ArrayList<>()).add(a);
    }

    // -------------------- Testing --------------------
    public static void main(String[] args) {
        Map<String, List<String>> network = new HashMap<>();

        // Build example network: A <-> B <-> C
        addRelation(network, "A", "B");
        addRelation(network, "B", "C");

        // Test cases
        List<String> results = new ArrayList<>();

        results.add(runTest(network, "A", "C", 1));  // Expected degree = 1
        results.add(runTest(network, "A", "B", 0));  // Expected degree = 0
        results.add(runTest(network, "A", "D", -1)); // "D" not in network
        results.add(runTest(network, "C", "A", 1));  // Symmetric

        // Large data test (simulate 10000 chain links)
        Map<String, List<String>> largeNetwork = new HashMap<>();
        for (int i = 0; i < 10000; i++) {
            addRelation(largeNetwork, "N" + i, "N" + (i + 1));
        }
        results.add(runTest(largeNetwork, "N0", "N9999", 9999));

        // Print final results
        System.out.println("---- Test Results ----");
        results.forEach(System.out::println);
    }

    // Method to check if actual == expected and return PASS/FAIL
    private static String runTest(Map<String, List<String>> network, String start, String end, int expected) {
        int actual = degreeOfSeparation(network, start, end);
        return "Input: " + start + " -> " + end + " | Expected: " + expected + " | Got: " + actual +
                " | " + (expected == actual ? "PASS" : "FAIL");
    }
}