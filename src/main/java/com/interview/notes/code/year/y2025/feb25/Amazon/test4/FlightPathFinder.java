package com.interview.notes.code.year.y2025.feb25.Amazon.test4;

import java.util.*;

public class FlightPathFinder {
    private Map<String, Set<String>> flightMap;

    public FlightPathFinder() {
        flightMap = new HashMap<>();
    }

    public static void main(String[] args) {
        FlightPathFinder finder = new FlightPathFinder();

        // Add test flights
        finder.addFlight("Seattle", "Portland");
        finder.addFlight("Seattle", "Houston");
        finder.addFlight("Seattle", "New York City");
        finder.addFlight("New York City", "Philadelphia");
        finder.addFlight("Houston", "New York City");

        // Test cases
        testPath(finder, "Seattle", "Philadelphia");
        testPath(finder, "Portland", "Philadelphia");
        testPath(finder, "Seattle", "Invalid");
        testPath(finder, "Seattle", "Portland");
    }

    private static void testPath(FlightPathFinder finder, String start, String end) {
        System.out.println("\nTesting path from " + start + " to " + end);
        List<String> path = finder.findPath(start, end);

        if (path != null) {
            System.out.println("Path found: " + String.join(" -> ", path));
        } else {
            System.out.println("No path found");
        }
    }

    public void addFlight(String from, String to) {
        // Add both directions since flights are bidirectional
        flightMap.computeIfAbsent(from, k -> new HashSet<>()).add(to);
        flightMap.computeIfAbsent(to, k -> new HashSet<>()).add(from);
    }

    public List<String> findPath(String start, String end) {
        if (!flightMap.containsKey(start) || !flightMap.containsKey(end)) {
            return null;
        }

        Queue<String> queue = new LinkedList<>();
        Map<String, String> parentMap = new HashMap<>();
        Set<String> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();

            if (current.equals(end)) {
                return reconstructPath(parentMap, start, end);
            }

            for (String next : flightMap.getOrDefault(current, new HashSet<>())) {
                if (!visited.contains(next)) {
                    visited.add(next);
                    parentMap.put(next, current);
                    queue.offer(next);
                }
            }
        }
        return null;
    }

    private List<String> reconstructPath(Map<String, String> parentMap, String start, String end) {
        List<String> path = new ArrayList<>();
        String current = end;

        while (current != null) {
            path.add(0, current);
            current = parentMap.get(current);
        }
        return path;
    }
}
