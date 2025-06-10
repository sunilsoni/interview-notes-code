package com.interview.notes.code.year.y2025.may.amazon.test2;

import java.util.*;

public class FacebookConnections {
    // Represents the social network using adjacency list
    private Map<String, List<String>> network;

    public FacebookConnections() {
        // Initialize network map to store connections
        this.network = new HashMap<>();
    }

    // Add a friendship connection between two profiles
    public void addConnection(String profile1, String profile2) {
        // Add profile2 to profile1's friend list
        network.computeIfAbsent(profile1, k -> new ArrayList<>()).add(profile2);
        // Add profile1 to profile2's friend list (undirected graph)
        network.computeIfAbsent(profile2, k -> new ArrayList<>()).add(profile1);
    }

    // Find connection path between start and end profiles using BFS
    public List<String> findConnectionPath(String start, String end) {
        // Handle edge cases
        if (!network.containsKey(start) || !network.containsKey(end)) {
            return Collections.emptyList();
        }
        if (start.equals(end)) {
            return Collections.singletonList(start);
        }

        // Queue for BFS traversal
        Queue<String> queue = new LinkedList<>();
        // Track visited profiles
        Map<String, String> visited = new HashMap<>();
        
        queue.offer(start);
        visited.put(start, null);

        // BFS implementation
        while (!queue.isEmpty()) {
            String current = queue.poll();
            
            // Check all friends of current profile
            for (String friend : network.getOrDefault(current, Collections.emptyList())) {
                if (!visited.containsKey(friend)) {
                    visited.put(friend, current);
                    queue.offer(friend);
                    
                    // If end profile found, construct path
                    if (friend.equals(end)) {
                        return constructPath(visited, start, end);
                    }
                }
            }
        }
        
        return Collections.emptyList(); // No path found
    }

    // Helper method to construct path from visited map
    private List<String> constructPath(Map<String, String> visited, String start, String end) {
        List<String> path = new ArrayList<>();
        String current = end;
        
        // Traverse back from end to start
        while (current != null) {
            path.add(0, current);
            current = visited.get(current);
        }
        
        return path;
    }

    // Main method for testing
    public static void main(String[] args) {
        FacebookConnections fb = new FacebookConnections();
        
        // Test Case 1: Basic connection path
        System.out.println("Test Case 1: Basic Path");
        fb.addConnection("Gordon", "Shantel");
        fb.addConnection("Shantel", "Maria");
        fb.addConnection("Maria", "David");
        fb.addConnection("David", "Stephen");
        
        List<String> path = fb.findConnectionPath("Gordon", "Stephen");
        System.out.println("Expected: [Gordon, Shantel, Maria, David, Stephen]");
        System.out.println("Actual: " + path);
        System.out.println("Result: " + 
            (path.equals(Arrays.asList("Gordon", "Shantel", "Maria", "David", "Stephen")) 
            ? "PASS" : "FAIL"));

        // Test Case 2: Direct connection
        System.out.println("\nTest Case 2: Direct Connection");
        fb.addConnection("Alice", "Bob");
        path = fb.findConnectionPath("Alice", "Bob");
        System.out.println("Expected: [Alice, Bob]");
        System.out.println("Actual: " + path);
        System.out.println("Result: " + 
            (path.equals(Arrays.asList("Alice", "Bob")) ? "PASS" : "FAIL"));

        // Test Case 3: No connection exists
        System.out.println("\nTest Case 3: No Connection");
        path = fb.findConnectionPath("Alice", "Unknown");
        System.out.println("Expected: []");
        System.out.println("Actual: " + path);
        System.out.println("Result: " + 
            (path.isEmpty() ? "PASS" : "FAIL"));

        // Test Case 4: Large network test
        System.out.println("\nTest Case 4: Large Network");
        // Create a large network with 1000 connections
        for (int i = 0; i < 1000; i++) {
            fb.addConnection("User" + i, "User" + (i + 1));
        }
        path = fb.findConnectionPath("User0", "User999");
        System.out.println("Large network test completed");
        System.out.println("Path length: " + path.size());
        System.out.println("Result: " + (path.size() == 1000 ? "PASS" : "FAIL"));
    }
}
