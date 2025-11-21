package com.interview.notes.code.year.y2025.july.google.test6;

import java.util.*;

/**
 * Solution for the Shared Cab Routing Problem to find optimal meeting points
 */
public class SharedCabRouting {
    // Graph representation using adjacency list
    public static Map<Character, List<Character>> graph = new HashMap<>();

    /**
     * Calculates shortest distance between two nodes using BFS
     */
    static int dist(Character source, Character dest) {
        Queue<Character> q = new LinkedList<>();
        Set<Character> visited = new HashSet<>();
        q.add(source);
        visited.add(source);
        int level = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Character pop = q.remove();
                if (pop == dest)
                    return level;
                //visited.add(pop);
                for (char x : graph.getOrDefault(pop, new ArrayList<>())) {
                    if (!visited.contains(x)) {
                        q.add(x);
                        visited.add(x);
                    }
                }

            }
            level++;
        }
        return level;
    }

    /**
     * Initializes the graph with cities and their connections
     */
    static void buildGraph() {
        // Clear existing graph data
        graph.clear();

        // Add edges for each city
        // Graph structure:
        /*
        A - F
        |   \
        C - E - D
        |       |
        B - G - H
        */
        graph.put('A', Arrays.asList('C', 'F'));
        graph.put('B', Arrays.asList('C', 'G'));
        graph.put('C', Arrays.asList('A', 'E', 'B'));
        graph.put('D', Arrays.asList('E', 'H'));
        graph.put('F', Arrays.asList('A', 'E'));
        graph.put('E', Arrays.asList('C', 'D', 'F'));
        graph.put('G', Arrays.asList('B', 'H'));
        graph.put('H', Arrays.asList('G', 'D'));
    }

    /**
     * Finds minimum total distance for both passengers
     */
    static int findMinimumDistance(Character sourceA, Character sourceB, Character dest) {
        // Initialize minimum distance to maximum possible value
        int minDistance = Integer.MAX_VALUE;
        int currentDistance;

        // Try each city as potential meeting point
        for (Character meetingPoint : graph.keySet()) {
            // Calculate total distance through current meeting point
            currentDistance = dist(sourceA, meetingPoint) +
                    dist(sourceB, meetingPoint) +
                    dist(meetingPoint, dest);

            // Update minimum if current distance is smaller
            minDistance = Math.min(currentDistance, minDistance);
        }
        return minDistance;
    }

    /**
     * Explains the path details for given source points and destination
     */
    static void explainPath(Character sourceA, Character sourceB, Character dest) {
        // Initialize variables to track best meeting point
        int minDistance = Integer.MAX_VALUE;
        Character bestMeetingPoint = null;

        // Find the best meeting point
        for (Character meetingPoint : graph.keySet()) {
            // Calculate total distance through current meeting point
            int currentDistance = dist(sourceA, meetingPoint) +
                    dist(sourceB, meetingPoint) +
                    dist(meetingPoint, dest);
            // Update if current distance is better
            if (currentDistance < minDistance) {
                minDistance = currentDistance;
                bestMeetingPoint = meetingPoint;
            }
        }

        // Print detailed path explanation
        System.out.printf("Best meeting point: %c\n", bestMeetingPoint);
        System.out.print("Distance breakdown:\n");
        System.out.printf("- %c to meeting point %c: %d\n",
                sourceA, bestMeetingPoint, dist(sourceA, bestMeetingPoint));
        System.out.printf("- %c to meeting point %c: %d\n",
                sourceB, bestMeetingPoint, dist(sourceB, bestMeetingPoint));
        System.out.printf("- Meeting point %c to destination %c: %d\n",
                bestMeetingPoint, dest, dist(bestMeetingPoint, dest));
    }

    /**
     * Main method to run test cases
     */
    public static void main(String[] args) {
        // Initialize the graph
        buildGraph();

        // Create list of test cases
        List<TestCase> testCases = Arrays.asList(
                new TestCase('A', 'B', 'D', 4, "Standard case: A and B meet at C or E to reach D"),
                new TestCase('A', 'A', 'D', 3, "Same starting point: A to D through F"),
                new TestCase('F', 'G', 'D', 4, "Different route case: F and G meet at E or H to reach D")
        );

        // Run each test case
        for (TestCase test : testCases) {
            // Calculate result for current test
            int result = findMinimumDistance(test.sourceA, test.sourceB, test.dest);

            // Print test results
            System.out.printf("Test: %s\n", test.description);
            System.out.printf("From %c and %c to %c\n",
                    test.sourceA, test.sourceB, test.dest);
            System.out.printf("Expected: %d, Got: %d\n",
                    test.expectedResult, result);
            System.out.printf("Test %s\n",
                    (result == test.expectedResult ? "PASSED" : "FAILED"));

            // Show detailed path explanation
            explainPath(test.sourceA, test.sourceB, test.dest);
            System.out.println("\n");
        }
    }

    /**
     * Test case class to organize different scenarios
     */
    static class TestCase {
        Character sourceA, sourceB, dest;
        int expectedResult;
        String description;

        // Constructor for test case
        TestCase(Character a, Character b, Character d, int expected, String desc) {
            sourceA = a;
            sourceB = b;
            dest = d;
            expectedResult = expected;
            description = desc;
        }
    }
}
