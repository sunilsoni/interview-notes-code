package com.interview.notes.code.year.y2025.feb.Amazon.test3;

import java.util.*;

public class FlightScheduler {

    // Graph representation: each airport maps to a list of directly connected airports.
    private Map<String, List<String>> flightGraph = new HashMap<>();

    /**
     * Main method for running tests.
     */
    public static void main(String[] args) {
        FlightScheduler scheduler = new FlightScheduler();

        // Setup the flights (the provided direct flights)
        scheduler.addFlight("Seattle", "Portland");
        scheduler.addFlight("Seattle", "Houston");
        scheduler.addFlight("Seattle", "New York City");
        scheduler.addFlight("New York City", "Philadelphia");
        scheduler.addFlight("Houston", "New York City");

        // Using single element arrays for mutable counters
        final int[] testsPassed = {0};
        final int[] totalTests = {0};

        // Inner class for test helper functionality.
        class TestHelper {
            void check(String testName, List<String> expected, List<String> actual) {
                totalTests[0]++;
                if (actual == null) {
                    System.out.println(testName + " : FAIL (expected " + expected + ", got null)");
                } else if (!actual.get(0).equals(expected.get(0)) ||
                        !actual.get(actual.size() - 1).equals(expected.get(expected.size() - 1))) {
                    // Check that the path starts and ends as expected.
                    System.out.println(testName + " : FAIL (expected start " + expected.get(0) +
                            " and end " + expected.get(expected.size() - 1) + ", got " + actual);
                } else {
                    System.out.println(testName + " : PASS");
                    testsPassed[0]++;
                }
            }
        }
        TestHelper tester = new TestHelper();

        // Test 1: Seattle -> Philadelphia.
        // Expected valid path should start with "Seattle" and end with "Philadelphia".
        List<String> path1 = scheduler.findPath("Seattle", "Philadelphia");
        tester.check("Test 1 (Seattle -> Philadelphia)", Arrays.asList("Seattle", "Philadelphia"), path1);

        // Test 2: Seattle -> Portland (direct flight)
        List<String> path2 = scheduler.findPath("Seattle", "Portland");
        tester.check("Test 2 (Seattle -> Portland)", Arrays.asList("Seattle", "Portland"), path2);

        // Test 3: Same start and destination
        List<String> path3 = scheduler.findPath("Houston", "Houston");
        tester.check("Test 3 (Houston -> Houston)", Arrays.asList("Houston"), path3);

        // Test 4: No available route
        // For this test, we add an isolated airport "Miami" with no flights connecting it.
        scheduler.flightGraph.putIfAbsent("Miami", new ArrayList<>());
        List<String> path4 = scheduler.findPath("Seattle", "Miami");
        totalTests[0]++;
        if (path4 == null) {
            System.out.println("Test 4 (Seattle -> Miami): PASS");
            testsPassed[0]++;
        } else {
            System.out.println("Test 4 (Seattle -> Miami): FAIL (expected null, got " + path4 + ")");
        }

        // Test 5: Large data input test
        // We generate a large graph where each node is connected in a chain.
        FlightScheduler largeScheduler = new FlightScheduler();
        int largeSize = 10000;
        for (int i = 1; i < largeSize; i++) {
            // Create a chain: "Airport1" -> "Airport2" -> ... -> "Airport10000"
            largeScheduler.addFlight("Airport" + i, "Airport" + (i + 1));
        }
        // Now, find path from Airport1 to Airport10000.
        long startTime = System.currentTimeMillis();
        List<String> largePath = largeScheduler.findPath("Airport1", "Airport" + largeSize);
        long endTime = System.currentTimeMillis();
        totalTests[0]++;
        if (largePath != null && largePath.size() == largeSize) {
            System.out.println("Test 5 (Large data input): PASS (Time taken: " + (endTime - startTime) + " ms)");
            testsPassed[0]++;
        } else {
            System.out.println("Test 5 (Large data input): FAIL (Invalid path length or null)");
        }

        // Summary of test results.
        System.out.println("\nTotal tests passed: " + testsPassed[0] + " out of " + totalTests[0]);
    }

    /**
     * Adds a bidirectional (direct) flight between two airports.
     *
     * @param airport1 the first airport
     * @param airport2 the second airport
     */
    public void addFlight(String airport1, String airport2) {
        flightGraph.computeIfAbsent(airport1, k -> new ArrayList<>()).add(airport2);
        flightGraph.computeIfAbsent(airport2, k -> new ArrayList<>()).add(airport1);
    }

    /**
     * Returns one valid path from the start airport to the destination airport.
     * If no path exists, returns null.
     *
     * @param start       the starting airport
     * @param destination the destination airport
     * @return List of airports representing the path, or null if no path exists.
     */
    public List<String> findPath(String start, String destination) {
        // Edge case: if start equals destination, return a list with only one airport.
        if (start.equals(destination)) {
            return Collections.singletonList(start);
        }

        // Map to keep track of visited airports and their predecessor (for path reconstruction)
        Map<String, String> predecessor = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            List<String> neighbors = flightGraph.getOrDefault(current, new ArrayList<>());

            for (String neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    predecessor.put(neighbor, current);

                    // If we have reached the destination, build and return the path.
                    if (neighbor.equals(destination)) {
                        return buildPath(predecessor, start, destination);
                    }

                    queue.offer(neighbor);
                }
            }
        }
        // No path found.
        return null;
    }

    /**
     * Reconstructs the path from start to destination using the predecessor map.
     *
     * @param predecessor Map of each airport to its predecessor in the BFS.
     * @param start       The starting airport.
     * @param destination The destination airport.
     * @return The path as a list of airports.
     */
    private List<String> buildPath(Map<String, String> predecessor, String start, String destination) {
        LinkedList<String> path = new LinkedList<>();
        String current = destination;
        while (current != null) {
            path.addFirst(current);
            current = predecessor.get(current);
        }
        // Verify the path starts with the start airport
        if (!path.isEmpty() && path.getFirst().equals(start)) {
            return path;
        }
        return null;
    }
}