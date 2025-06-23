package com.interview.notes.code.year.y2025.may.amazon.test1;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FriendPathFinder {

    /**
     * Find the shortest path of friends from start to end.
     * Uses BFS and a predecessor map to rebuild the path.
     */
    public static List<String> findFriendPath(
            Map<String, List<String>> graph,  // adjacency list of friendships
            String start,                      // starting profile
            String end                         // target profile
    ) {
        // If start and end are the same, immediately return a single-element path
        if (start.equals(end)) {
            return Collections.singletonList(start);
        }

        // Queue to process profiles in breadth-first order
        Queue<String> queue = new LinkedList<>();
        // Set to keep track of profiles we've already visited
        Set<String> visited = new HashSet<>();
        // Map each profile to its predecessor on the discovered path
        Map<String, String> predecessor = new HashMap<>();

        // Start BFS by adding the start profile
        queue.add(start);
        visited.add(start);

        // Perform BFS until we exhaust the queue or find the end
        while (!queue.isEmpty()) {
            String current = queue.poll();  // take the next profile to explore

            // Check all friends (neighbors) of the current profile
            for (String neighbor : graph.getOrDefault(current, Collections.emptyList())) {
                // If we haven't seen this neighbor yet, it's newly discovered
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);            // mark it so we don't revisit
                    predecessor.put(neighbor, current); // remember how we reached it
                    queue.add(neighbor);              // schedule it for further exploration

                    // Stop search as soon as we reach the target
                    if (neighbor.equals(end)) {
                        queue.clear();  // break out of outer while too
                        break;
                    }
                }
            }
        }

        // If we never reached 'end', return empty list
        if (!predecessor.containsKey(end)) {
            return Collections.emptyList();
        }

        // Reconstruct the path by walking backwards from end → start
        List<String> path = new ArrayList<>();
        String step = end;
        while (step != null) {
            path.add(step);
            step = predecessor.get(step);
        }
        // Reverse to get start → end order
        Collections.reverse(path);
        return path;
    }

    /**
     * Simple test runner in main.
     * Builds several scenarios and prints PASS/FAIL for each.
     */
    public static void main(String[] args) {
        // --- Test 1: Basic chain example ---
        Map<String, List<String>> graph1 = new HashMap<>();
        // Helper to add mutual friendship
        BiConsumer<String, String> addFriend = (a, b) -> {
            graph1.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
            graph1.computeIfAbsent(b, k -> new ArrayList<>()).add(a);
        };
        // Build the example chain: Gordon–Shantel–Maria–David–Stephen
        addFriend.accept("Gordon", "Shantel");
        addFriend.accept("Shantel", "Maria");
        addFriend.accept("Maria", "David");
        addFriend.accept("David", "Stephen");
        List<String> result1 = findFriendPath(graph1, "Gordon", "Stephen");
        List<String> expected1 = Arrays.asList("Gordon", "Shantel", "Maria", "David", "Stephen");
        System.out.println("Test 1 (basic chain): " + (result1.equals(expected1) ? "PASS" : "FAIL"));

        // --- Test 2: No path exists ---
        Map<String, List<String>> graph2 = new HashMap<>();
        graph2.put("A", Arrays.asList("B")); // A–B
        graph2.put("C", Arrays.asList("D")); // C–D
        List<String> result2 = findFriendPath(graph2, "A", "D");
        System.out.println("Test 2 (no path): " + (result2.isEmpty() ? "PASS" : "FAIL"));

        // --- Test 3: Start equals end ---
        Map<String, List<String>> graph3 = new HashMap<>();
        graph3.put("X", Arrays.asList("Y"));
        List<String> result3 = findFriendPath(graph3, "X", "X");
        System.out.println("Test 3 (same node): " + (result3.equals(Collections.singletonList("X")) ? "PASS" : "FAIL"));

        // --- Test 4: Large linear graph for scalability ---
        int N = 10_000;
        Map<String, List<String>> largeGraph = new HashMap<>();
        // Build a straight chain Node0–Node1–…–Node9999
        IntStream.range(0, N - 1).forEach(i -> {
            String a = "Node" + i;
            String b = "Node" + (i + 1);
            largeGraph.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
            largeGraph.computeIfAbsent(b, k -> new ArrayList<>()).add(a);
        });
        // Expected path [Node0, Node1, …, Node9999]
        List<String> expectedLarge = IntStream.range(0, N)
                .mapToObj(i -> "Node" + i)
                .collect(Collectors.toList());
        List<String> result4 = findFriendPath(largeGraph, "Node0", "Node" + (N - 1));
        System.out.println("Test 4 (large graph): " + (result4.equals(expectedLarge) ? "PASS" : "FAIL"));
    }
}