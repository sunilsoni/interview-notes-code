package com.interview.notes.code.year.y2026.may.assessments.test1;

import java.util.*; // Imports Collections framework (Map, Set, PriorityQueue, List)

public class LogStreamProcessorTopK { 

    // Adjacency list: Links each user to a Set of their unique conversation partners.
    private final Map<String, Set<String>> network = new HashMap<>();

    // Simple main method for testing without external frameworks.
    public static void main(String[] args) {
        // --- Test 1: Basic Top 2 ---
        var p = new LogStreamProcessorTopK();
        // A talks to B, C, D (Count: 3)
        p.registerEvent(1, "A", "B", "m");
        p.registerEvent(2, "A", "C", "m");
        p.registerEvent(3, "A", "D", "m");
        // B talks to E (Count: 2 -> A and E)
        p.registerEvent(4, "B", "E", "m");

        var top2 = p.getTopKActiveUsers(2);
        boolean test1 = top2.size() == 2 && top2.get(0).equals("A") && top2.get(1).equals("B");
        System.out.println("Basic Test (Top 2): " + (test1 ? "PASS" : "FAIL"));

        // --- Test 2: Large Data Top 3 ---
        var pLarge = new LogStreamProcessorTopK();
        // Generate massive network: User0 gets most friends, User1 second most, etc.
        for (int i = 0; i < 10000; i++) {
            for (int j = i; j < 10000; j++) {
                pLarge.registerEvent(0, "User" + i, "Friend" + j, "Spam");
            }
        }

        var top3 = pLarge.getTopKActiveUsers(3);
        boolean testLarge = top3.get(0).equals("User0") && top3.get(1).equals("User1") && top3.get(2).equals("User2");
        System.out.println("Large Data Test (Top 3): " + (testLarge ? "PASS" : "FAIL"));
    }

    // O(1) Time: Registers the event symmetrically for both users.
    public void registerEvent(long ts, String sender, String receiver, String msg) {
        // computeIfAbsent safely initializes the Set if missing, then adds the connection.
        network.computeIfAbsent(sender, k -> new HashSet<>()).add(receiver);
        network.computeIfAbsent(receiver, k -> new HashSet<>()).add(sender);
    }

    // O(U log K) Time: Returns the Top K most active users efficiently using a Min-Heap.
    public List<String> getTopKActiveUsers(int k) {
        // Guard clause: Return empty list if invalid K is requested.
        if (k <= 0) return Collections.emptyList();

        // Min-Heap: Sorts users by connection count. The LOWEST count stays at the top.
        var minHeap = new PriorityQueue<String>(Comparator.comparingInt(u -> network.get(u).size()));

        // Iterate over all unique users currently in our map.
        for (String user : network.keySet()) {
            minHeap.offer(user); // Add user to the heap.

            // If heap size exceeds K, instantly eject the user with the lowest score.
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // Drain the heap into our final result list.
        var result = new ArrayList<String>();
        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll()); // Pulls them out from lowest to highest.
        }

        // Reverse so the absolute highest user is at index 0.
        Collections.reverse(result);
        return result;
    }
}