package com.interview.notes.code.year.y2026.june.common.test2;

import java.util.*; // Import utility classes for Map, Set, Queue, and List

// Main class containing only the most essential logic
public class BasicUrlAnalyzer {

    // Graph: A map where the Key is a referrer, and the Value is a set of URLs it links to
    private final Map<String, Set<String>> graph = new HashMap<>();

    // ONE SIMPLE EXAMPLE: To prove the logic works
    public static void main(String[] args) {
        // Create our analyzer tool
        BasicUrlAnalyzer analyzer = new BasicUrlAnalyzer();

        // Create a tiny list of just two clicks: "/" -> "/search" -> "/detail"
        List<Log> data = List.of(
            new Log("/search", "/"),       // User clicked from "/" to "/search"
            new Log("/detail", "/search")  // User clicked from "/search" to "/detail"
        );

        // Tell the analyzer to read these two clicks
        analyzer.process(data);

        // Ask the main question: Can we get from "/" all the way to "/detail"?
        boolean result = analyzer.isLinked("/", "/detail");

        // Print the final answer to the screen
        System.out.println("Can we reach /detail from /? Answer: " + result);
    }

    // METHOD 1: Process the logs to build our linking graph
    public void process(List<Log> logs) {
        // Convert the list into a stream to process items one by one
        logs.stream()
            // Ignore any logs where the referrer is missing or literally says "null"
            .filter(log -> log.referrer() != null && !log.referrer().equals("null"))
            // For every valid log entry, do the following block:
            .forEach(log -> graph
                // Find the referrer in the map, or create a new empty Set if it's not there yet
                .computeIfAbsent(log.referrer(), k -> new HashSet<>())
                // Add the destination URL to this referrer's Set of links
                .add(log.url())
            );
    }

    // METHOD 2: Check if you can get from the start URL to the target URL
    public boolean isLinked(String start, String target) {
        // If the starting URL has never linked to anything, it's impossible to go anywhere
        if (!graph.containsKey(start)) return false;
        
        // Queue: Holds the list of URLs we need to check next (Breadth-First Search)
        Queue<String> queue = new LinkedList<>();
        // Set: Remembers URLs we already checked so we don't go in circles forever
        Set<String> visited = new HashSet<>();
        
        // Put the starting URL into the queue to begin
        queue.add(start);
        // Immediately mark the starting URL as visited
        visited.add(start);
        
        // Keep looping as long as there are still URLs in our queue to check
        while (!queue.isEmpty()) {
            // Take the first URL out of the line (queue)
            String current = queue.poll();
            
            // If the URL we just took out is our exact target, we found a path!
            if (current.equals(target)) return true;
            
            // Look up all the URLs that 'current' links to (or get an empty set if none)
            for (String nextUrl : graph.getOrDefault(current, Collections.emptySet())) {
                // Try to add this next URL to our 'visited' list
                if (visited.add(nextUrl)) {
                    // If it was successfully added (meaning it's new), put it in the line to check later
                    queue.add(nextUrl);
                }
            }
        }
        // If the line (queue) becomes empty and we never found the target, no path exists
        return false;
    }

    // Record: A tiny, modern Java feature to hold our two important fields
    record Log(String url, String referrer) {}
}