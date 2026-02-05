package com.interview.notes.code.year.y2026.feb.Linkedin.test1;

import java.util.*;

// 1. Define the Interface for Member as per requirements
interface Member {
    String getName(); // Method to get member's name

    int getMemberId(); // Method to get unique ID
}

// 2. Define the Interface to get connections
interface MemberConnections {
    List<Member> getConnections(Member member); // Returns list of friends
}

// 3. Define the Interface for the logic
interface FindConnectionDistance {
    int calculateConnectionDistance(Member member1, Member member2); // The method to implement
}

// Concrete implementation of Member using Java Records (Java 14+) reduces boilerplate
// This replaces the need for a class with fields, constructor, getters, and equals/hashCode
record SimpleMember(String name, int id) implements Member {
    @Override
    public String getName() {
        return name;
    } // Getter implementation

    @Override
    public int getMemberId() {
        return id;
    } // Getter implementation

    @Override
    public String toString() {
        return name;
    } // For pretty printing in tests
}

// Main Solution Class
public class LinkedInDistanceSearch implements FindConnectionDistance {

    // Dependency injection for the data source (the graph)
    private final MemberConnections graph;

    // Constructor to inject the connection data
    public LinkedInDistanceSearch(MemberConnections graph) {
        this.graph = graph; // Assign the graph provider
    }

    public static void main(String[] args) {
        System.out.println("--- Starting Tests ---");

        // --- Data Setup (Simulating the graph from the image) ---
        // Creating members
        var bob = new SimpleMember("Bob", 1); // ID 1
        var alice = new SimpleMember("Alice", 2); // ID 2
        var frank = new SimpleMember("Frank", 3); // ID 3
        var john = new SimpleMember("John", 4); // ID 4
        var jenny = new SimpleMember("Jenny", 5); // ID 5
        var lucy = new SimpleMember("Lucy", 6); // ID 6
        var stranger = new SimpleMember("Stranger", 99); // ID 99 (Disconnected)

        // Storing connections in a Map for easy lookup during tests
        Map<Integer, List<Member>> adjacencyList = new HashMap<>();

        // Helper lambda to add connections (bi-directional simulated manually for clarity)
        adjacencyList.put(bob.getMemberId(), List.of(alice, john));
        adjacencyList.put(alice.getMemberId(), List.of(bob, frank, lucy));
        adjacencyList.put(frank.getMemberId(), List.of(alice));
        adjacencyList.put(john.getMemberId(), List.of(bob, jenny));
        adjacencyList.put(jenny.getMemberId(), List.of(john, lucy));
        adjacencyList.put(lucy.getMemberId(), List.of(jenny, alice));
        adjacencyList.put(stranger.getMemberId(), List.of()); // No friends

        // Create our data provider implementation
        MemberConnections dataProvider = member -> adjacencyList.getOrDefault(member.getMemberId(), List.of());

        // Initialize our solution
        LinkedInDistanceSearch solver = new LinkedInDistanceSearch(dataProvider);

        // --- Test Cases ---

        // Test 1: Direct Connection (Bob -> Alice)
        // Expected: 1
        runTest("Direct Connection", solver, bob, alice, 1);

        // Test 2: Two Hops (Bob -> Alice -> Frank)
        // Expected: 2
        runTest("Two Hops", solver, bob, frank, 2);

        // Test 3: Three Hops (Frank -> Alice -> Lucy -> Jenny)
        // Expected: 3
        runTest("Three Hops", solver, frank, jenny, 3);

        // Test 4: Self Connection (Bob -> Bob)
        // Expected: 0
        runTest("Self Check", solver, bob, bob, 0);

        // Test 5: No Connection (Bob -> Stranger)
        // Expected: -1
        runTest("Disconnected", solver, bob, stranger, -1);

        // --- Large Data Test ---
        System.out.println("\nRunning Large Data Test...");
        runLargeDataTest();
    }

    // ================= TESTING SECTION =================

    // Helper method to print PASS/FAIL results cleanly
    private static void runTest(String testName, LinkedInDistanceSearch solver, Member m1, Member m2, int expected) {
        long startTime = System.nanoTime(); // Start timer
        int result = solver.calculateConnectionDistance(m1, m2); // Execute logic
        long duration = (System.nanoTime() - startTime) / 1000; // Calculate duration in microseconds

        String status = (result == expected) ? "PASS" : "FAIL"; // Determine status
        // Print formatted result
        System.out.printf("[%s] %s -> %s | Exp: %d, Act: %d | Time: %d us | Status: %s%n",
                testName, m1.getName(), m2.getName(), expected, result, duration, status);
    }

    // Method to simulate large data input
    private static void runLargeDataTest() {
        int size = 100_000; // Create 100,000 members
        Map<Integer, List<Member>> hugeGraph = new HashMap<>(); // Storage for big graph

        // Create a long chain: 0 -> 1 -> 2 ... -> 99,999
        // This is the worst case for BFS depth
        for (int i = 0; i < size; i++) {
            Member current = new SimpleMember("User" + i, i);
            Member next = new SimpleMember("User" + (i + 1), i + 1);
            if (i < size - 1) {
                hugeGraph.put(current.getMemberId(), List.of(next)); // Link current to next
            }
        }

        // Implementation for the huge graph
        MemberConnections hugeProvider = member -> hugeGraph.getOrDefault(member.getMemberId(), List.of());
        LinkedInDistanceSearch hugeSolver = new LinkedInDistanceSearch(hugeProvider);

        Member start = new SimpleMember("User0", 0);
        Member end = new SimpleMember("User500", 500); // Target distance 500

        long t1 = System.currentTimeMillis();
        int dist = hugeSolver.calculateConnectionDistance(start, end);
        long t2 = System.currentTimeMillis();

        System.out.println("Large Chain Test (0 to 500): " + (dist == 500 ? "PASS" : "FAIL"));
        System.out.println("Time taken: " + (t2 - t1) + "ms");
    }

    @Override
    public int calculateConnectionDistance(Member start, Member target) {
        // Edge case: If start and target are the same person, distance is 0
        if (start.getMemberId() == target.getMemberId()) return 0;

        // Queue for BFS: stores the current member we are visiting
        // We use a Queue because BFS is First-In-First-Out (FIFO)
        Queue<Member> queue = new LinkedList<>();

        // Map to keep track of visited members and their distance from start
        // Using ID (int) as key is memory efficient and prevents checking same person twice
        Map<Integer, Integer> distanceMap = new HashMap<>();

        // Initialize: Add the starting member to queue
        queue.add(start);
        // Initialize: Distance to self is 0
        distanceMap.put(start.getMemberId(), 0);

        // Loop until there are no more members to check
        while (!queue.isEmpty()) {
            // Remove the next member from the front of the queue
            var current = queue.poll();
            // Get the distance of the current member so far
            int currentDist = distanceMap.get(current.getMemberId());

            // Get all connections (neighbors) for the current member
            // If getConnections returns null, default to empty list to avoid NullPointer
            var neighbors = graph.getConnections(current);
            if (neighbors == null) neighbors = Collections.emptyList();

            // Iterate through every friend of the current member
            for (var neighbor : neighbors) {
                // Check if we have found the target member
                if (neighbor.getMemberId() == target.getMemberId()) {
                    return currentDist + 1; // Found! Return current distance + 1 hop
                }

                // Optimization: Only process this neighbor if we haven't visited them yet
                // This prevents cycles (infinite loops) and redundant processing
                if (!distanceMap.containsKey(neighbor.getMemberId())) {
                    // Mark as visited and store distance (current + 1)
                    distanceMap.put(neighbor.getMemberId(), currentDist + 1);
                    // Add to queue to explore their connections in the next iteration
                    queue.add(neighbor);
                }
            }
        }

        // If queue becomes empty and we haven't found target, they are not connected
        return -1;
    }
}