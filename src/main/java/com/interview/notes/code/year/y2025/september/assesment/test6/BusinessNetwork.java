package com.interview.notes.code.year.y2025.september.assesment.test6;

import java.util.*;
import java.util.stream.IntStream;

// Main class to find degrees of separation between businesses
public class BusinessNetwork {

    // Represents a business relationship graph using adjacency list
    private final Map<String, Set<String>> businessGraph;

    // Constructor initializes empty graph
    public BusinessNetwork() {
        // Using HashMap for O(1) lookup of businesses
        this.businessGraph = new HashMap<>();
    }

    // Main method with test cases
    public static void main(String[] args) {
        BusinessNetwork network = new BusinessNetwork();

        // Test Case 1: Basic case
        network.addRelationship("A", "B");
        network.addRelationship("B", "C");
        System.out.println("Test 1 (A to C): " +
                (network.findDegreesOfSeparation("A", "C") == 1 ? "PASS" : "FAIL"));

        // Test Case 2: Direct connection
        System.out.println("Test 2 (A to B): " +
                (network.findDegreesOfSeparation("A", "B") == 0 ? "PASS" : "FAIL"));

        // Test Case 3: Non-existent path
        System.out.println("Test 3 (A to D): " +
                (network.findDegreesOfSeparation("A", "D") == -1 ? "PASS" : "FAIL"));

        // Test Case 4: Large network
        String[] businesses = IntStream.range(0, 1000)
                .mapToObj(i -> "Business" + i)
                .toArray(String[]::new);

        // Create large network connections
        for (int i = 0; i < businesses.length - 1; i++) {
            network.addRelationship(businesses[i], businesses[i + 1]);
        }

        System.out.println("Test 4 (Large network): " +
                (network.findDegreesOfSeparation(businesses[0], businesses[999]) == 999 ? "PASS" : "FAIL"));
    }

    // Adds a business relationship (bidirectional) to the graph
    public void addRelationship(String business1, String business2) {
        // Add business1 to business2's connections
        businessGraph.computeIfAbsent(business1, k -> new HashSet<>()).add(business2);
        // Add business2 to business1's connections (bidirectional)
        businessGraph.computeIfAbsent(business2, k -> new HashSet<>()).add(business1);
    }

    // Finds shortest path between two businesses using BFS
    public int findDegreesOfSeparation(String start, String end) {
        // Handle invalid inputs
        if (!businessGraph.containsKey(start) || !businessGraph.containsKey(end)) {
            return -1;
        }

        // Queue for BFS traversal
        Queue<String> queue = new LinkedList<>();
        // Track visited businesses
        Set<String> visited = new HashSet<>();
        // Map to track distance from start
        Map<String, Integer> distance = new HashMap<>();

        // Initialize BFS
        queue.offer(start);
        visited.add(start);
        distance.put(start, 0);

        // BFS traversal
        while (!queue.isEmpty()) {
            String current = queue.poll();

            // Found target business
            if (current.equals(end)) {
                return distance.get(current);
            }

            // Process all connected businesses
            businessGraph.getOrDefault(current, new HashSet<>())
                    .stream()
                    .filter(neighbor -> !visited.contains(neighbor))
                    .forEach(neighbor -> {
                        queue.offer(neighbor);
                        visited.add(neighbor);
                        distance.put(neighbor, distance.get(current) + 1);
                    });
        }

        // No path found
        return -1;
    }
}
