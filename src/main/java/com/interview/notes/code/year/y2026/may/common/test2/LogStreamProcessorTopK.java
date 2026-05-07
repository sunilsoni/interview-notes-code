package com.interview.notes.code.year.y2026.may.common.test2;

import java.util.*; // Imports the Java Collections Framework (Map, Set, PriorityQueue, List) needed for our core data structures.

public class LogStreamProcessorTopK { // Main class to encapsulate our state and logic for the chat processor.

    // A Map where Key = Username (String), Value = Set of unique people they talked to (Set<String>).
    // We use a Map for O(1) lookups and a Set to automatically drop duplicate conversations without extra coding logic.
    private final Map<String, Set<String>> network = new HashMap<>();

    // Main method to run our automated test cases without relying on external frameworks like JUnit.
    public static void main(String[] args) {
        // Triggers the test suite execution immediately when the class is run.
        runTests();
    }

    // Method containing our specific test scenarios to validate the algorithm's correctness.
    private static void runTests() {

        // Instantiates our processor for the Top K logic test.
        var p = new LogStreamProcessorTopK();

        // Simulates A talking to B, C, and D. (User A gets 3 unique connections).
        p.registerEvent(1, "A", "B", "msg");
        p.registerEvent(2, "A", "C", "msg");
        p.registerEvent(3, "A", "D", "msg");

        // Simulates B talking to E. (User B gets 2 total unique connections: A and E).
        p.registerEvent(4, "B", "E", "msg");

        // We request the Top 2 users. We expect [A, B] since A has 3 connections, and B has 2.
        var top2 = p.getTopKActiveUsers(2);

        // Evaluates if the returned list exactly matches our expected size and order.
        boolean test1 = top2.size() == 2 && top2.get(0).equals("A") && top2.get(1).equals("B");

        // Prints the clear PASS/FAIL result of Test 1 to the console.
        System.out.println("Test 1 (Top 2 Exact Match): " + (test1 ? "PASS" : "FAIL"));
    }

    // Registers a message event. We only care about who talked to whom, so we ignore 'ts' and 'msg'.
    public void registerEvent(long ts, String sender, String receiver, String msg) {

        // computeIfAbsent checks if 'sender' exists. If not, it creates a new HashSet.
        // We then add the 'receiver' to this Set. If they already talked, the Set safely ignores it (O(1) time).
        network.computeIfAbsent(sender, k -> new HashSet<>()).add(receiver);

        // We do the exact same thing for the receiver because a conversation goes both ways.
        // This ensures that if we later query the receiver's stats, their connection count is completely accurate.
        network.computeIfAbsent(receiver, k -> new HashSet<>()).add(sender);
    }

    // Returns a list of the top 'k' most active users based on their unique conversation counts.
    public List<String> getTopKActiveUsers(int k) {

        // Guard clause: If k is 0 or negative, we instantly return an empty list to prevent runtime exceptions.
        if (k <= 0) return Collections.emptyList();

        // Creates a Min-Heap (PriorityQueue).
        // Comparator.comparingInt tells the heap to order users by looking up their Set size in our 'network' map.
        // A Min-Heap keeps the user with the LOWEST score sitting at the very top of the queue.
        var minHeap = new PriorityQueue<String>(Comparator.comparingInt(user -> network.get(user).size()));

        // Loops through every single unique user we have currently tracked in our network Map.
        for (String user : network.keySet()) {

            // We offer (add) the current user into our Min-Heap.
            minHeap.offer(user);

            // If adding this user made our Heap larger than our allowed Top 'K' capacity...
            if (minHeap.size() > k) {
                // ...we poll (remove) the top element.
                // Because it's a Min-Heap, this automatically kicks out the person with the lowest score, keeping only the best.
                minHeap.poll();
            }
        }

        // We create an ArrayList to store our final Top K winners.
        var result = new ArrayList<String>();

        // We loop until our Min-Heap is completely empty.
        while (!minHeap.isEmpty()) {
            // We poll the heap and add the user to our result list.
            // Note: Because it's a Min-Heap, the users come out in ascending order (lowest of the Top K comes out first).
            result.add(minHeap.poll());
        }

        // We reverse the list so the absolute most active user is placed at index 0 (descending order).
        Collections.reverse(result);

        // Return the final, correctly sorted list of the Top K most active users.
        return result;
    }
}