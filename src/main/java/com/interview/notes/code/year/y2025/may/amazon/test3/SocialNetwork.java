package com.interview.notes.code.year.y2025.may.amazon.test3;

import java.util.*;

public class SocialNetwork {

    // Represents the social network with a map of profile names to their friends list
    private static Map<String, List<String>> friendGraph = new HashMap<>();

    // BFS to find the shortest path between two profiles
    public static List<String> findConnectionPath(String start, String end) {
        // queue to store paths for BFS
        Queue<List<String>> queue = new LinkedList<>();
        // set to keep track of visited profiles
        Set<String> visited = new HashSet<>();

        // initialize BFS with starting profile
        queue.offer(Arrays.asList(start));
        visited.add(start);

        while (!queue.isEmpty()) {
            List<String> path = queue.poll(); // dequeue current path
            String current = path.get(path.size() - 1); // last profile in path

            // If we reached the target profile, return the path
            if (current.equals(end)) {
                return path;
            }

            // Explore friends of current profile
            for (String friend : friendGraph.getOrDefault(current, Collections.emptyList())) {
                // if friend hasn't been visited, enqueue new path and mark as visited
                if (!visited.contains(friend)) {
                    visited.add(friend);
                    List<String> newPath = new ArrayList<>(path);
                    newPath.add(friend);
                    queue.offer(newPath);
                }
            }
        }

        // No connection found
        return Collections.emptyList();
    }

    // Testing the method with different scenarios
    public static void main(String[] args) {
        // Setup graph
        friendGraph.put("Gordon", Arrays.asList("Shantel", "Tom"));
        friendGraph.put("Shantel", Arrays.asList("Maria", "Gordon"));
        friendGraph.put("Maria", Arrays.asList("David"));
        friendGraph.put("David", Arrays.asList("Stephen"));
        friendGraph.put("Tom", Arrays.asList("Alice"));
        friendGraph.put("Alice", Arrays.asList("Stephen"));
        friendGraph.put("Stephen", Collections.emptyList()); // No friends

        testConnection("Gordon", "Stephen"); // Expected: ["Gordon", "Shantel", "Maria", "David", "Stephen"]
        testConnection("Tom", "Stephen");    // Expected: ["Tom", "Alice", "Stephen"]
        testConnection("Alice", "Gordon");   // Expected: [] (no connection)
        testConnection("Stephen", "Gordon"); // Expected: [] (no connection)
    }

    private static void testConnection(String start, String end) {
        List<String> connectionPath = findConnectionPath(start, end);
        if (!connectionPath.isEmpty()) {
            System.out.println("PASS: Connection from " + start + " to " + end + ": " + connectionPath);
        } else {
            System.out.println("FAIL: No connection found from " + start + " to " + end);
        }
    }
}
