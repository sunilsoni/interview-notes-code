package com.interview.notes.code.year.y2026.june.common.test1;

import java.util.*; // Import utility classes like Map, Set, Queue, List

// Main class to encapsulate our URL navigation logic and testing
public class UrlNavigationAnalyzer {

    // Adjacency list to represent the directed graph of URL links (Referrer -> URL)
    private final Map<String, Set<String>> graph = new HashMap<>();

    // Main method replacing JUnit for testing, executing predefined scenarios
    public static void main(String[] args) {
        // Instantiate the analyzer service
        UrlNavigationAnalyzer analyzer = new UrlNavigationAnalyzer();

        // Define standard test data reflecting the provided problem table
        List<LogEntry> data = List.of(
            // Root access, no referrer
            new LogEntry("/", "C4CA4...", 1625153267, "null"),
            // Self loop on root
            new LogEntry("/", "C4CA4...", 1625153254, "/"),
            // Search accessed from root
            new LogEntry("/search", "C4CA4...", 1625153298, "/"),
            // Detail accessed from search
            new LogEntry("/detail", "C4CA4...", 1625153298, "/search"),
            // Reviews accessed from detail
            new LogEntry("/reviews", "C4CA4...", 1625153298, "/detail"),
            // Help accessed from root
            new LogEntry("/help", "ECCBC...", 1625153314, "/"),
            // Cart accessed from detail
            new LogEntry("/cart", "C4CA4...", 1625153325, "/detail")
        );

        // Feed the test data into the graph processor
        analyzer.processLogs(data);

        // Helper method invocation to print test results cleanly
        runTest("Test 1: Direct link (/search -> /detail)", analyzer.isLinked("/search", "/detail"), true);
        runTest("Test 2: Indirect link (/ -> /reviews)", analyzer.isLinked("/", "/reviews"), true);
        runTest("Test 3: No link path (/help -> /cart)", analyzer.isLinked("/help", "/cart"), false);
        runTest("Test 4: Self mapping (/ -> /)", analyzer.isLinked("/", "/"), true);

        // Generate massive synthetic data to test memory/stack limits (10,000 node chain)
        List<LogEntry> largeData = new ArrayList<>();
        // Loop 10,000 times to build a sequential link chain
        for (int i = 0; i < 10000; i++) {
            // Link node i to node i+1
            largeData.add(new LogEntry("/page" + (i + 1), "session", 123L, "/page" + i));
        }
        // Instantiate a new analyzer for the large dataset isolation
        UrlNavigationAnalyzer largeAnalyzer = new UrlNavigationAnalyzer();
        // Process the massive dataset
        largeAnalyzer.processLogs(largeData);
        // Test reachability from start of the chain to the very end
        runTest("Test 5: Large Data Chain (0 -> 10000)", largeAnalyzer.isLinked("/page0", "/page10000"), true);
    }

    // Simple test validation method to output PASS/FAIL status directly to console
    private static void runTest(String testName, boolean actual, boolean expected) {
        // Compare actual result against expected result
        if (actual == expected) {
            // Print PASS in green-friendly text if they match
            System.out.println("[PASS] " + testName);
        } else {
            // Print FAIL with expected/actual mismatch details if they differ
            System.out.println("[FAIL] " + testName + " | Expected: " + expected + ", Got: " + actual);
        }
    }

    // Method to ingest raw log entries and construct the graph relationships
    public void processLogs(List<LogEntry> logs) {
        // Start a stream to process the list of logs sequentially
        logs.stream()
            // Filter out logs where referrer is null or the literal string "null"
            .filter(log -> log.referrer() != null && !log.referrer().equals("null"))
            // Iterate over each valid log entry to populate the graph
            .forEach(log -> graph
                // Get the set of target URLs for this referrer, or create a new set if none exists
                .computeIfAbsent(log.referrer(), k -> new HashSet<>())
                // Add the current URL to the referrer's set of outgoing links
                .add(log.url())
            );
    }

    // Method to check if a path exists from startUrl to targetUrl using BFS
    public boolean isLinked(String startUrl, String targetUrl) {
        // If the start URL has no outgoing links, it can't reach anything
        if (!graph.containsKey(startUrl)) return false;
        // If the start and target are identical, it is inherently reachable
        if (startUrl.equals(targetUrl)) return true;

        // Initialize a Queue to manage nodes to visit for Breadth-First Search
        Queue<String> queue = new LinkedList<>();
        // Initialize a Set to track visited nodes and prevent infinite cycle loops
        Set<String> visited = new HashSet<>();

        // Enqueue the starting URL to kick off the search
        queue.add(startUrl);
        // Mark the starting URL as visited immediately
        visited.add(startUrl);

        // Loop continuously as long as there are nodes left to explore in the queue
        while (!queue.isEmpty()) {
            // Remove and retrieve the URL at the front of the queue
            String current = queue.poll();
            // If the current URL matches our target, a path exists, return true
            if (current.equals(targetUrl)) return true;

            // Fetch all outgoing links for the current URL, defaulting to an empty set if none exist
            for (String neighbor : graph.getOrDefault(current, Collections.emptySet())) {
                // Attempt to add the neighbor to visited; returns true if it wasn't already there
                if (visited.add(neighbor)) {
                    // Add the unvisited neighbor to the queue for future exploration
                    queue.add(neighbor);
                }
            }
        }
        // If the queue empties and the target was never found, no path exists
        return false;
    }

    // Java 21 Record to create a concise, immutable data carrier for log entries
    record LogEntry(String url, String sessionId, long timestamp, String referrer) {}
}