package com.interview.notes.code.year.y2026.may.common.test3;

import java.util.*; // Imports required Java utility collections like Map, Set, PriorityQueue.

public class LogStreamProcessorTopK { // Declares the class to handle Top K active users.

    // Uses a Map to link each username to a Set of unique users they've talked with.
    private final Map<String, Set<String>> network = new HashMap<>();

    // Custom main method for testing, avoiding heavy frameworks like JUnit.
    public static void main(String[] args) {
        // Executes our automated test suite when the file is run.
        runTests();
    }

    // Runs specific scenarios and prints PASS/FAIL to the console.
    private static void runTests() {
        // Initializes processor for the Top K test.
        var p = new LogStreamProcessorTopK();

        // A talks to B, C, D (A has 3 partners)
        p.registerEvent(1, "A", "B", "msg");
        p.registerEvent(2, "A", "C", "msg");
        p.registerEvent(3, "A", "D", "msg");

        // B talks to E (B has 2 partners: A and E)
        p.registerEvent(4, "B", "E", "msg");

        // C has 1 partner (A). D has 1 partner (A). E has 1 partner (B).

        // Requests the top 2 users. Expecting [A, B] since A=3, B=2.
        var top2 = p.getTopKActiveUsers(2);
        // Verifies the size is exactly 2, index 0 is A, and index 1 is B.
        boolean test1 = top2.size() == 2 && top2.get(0).equals("A") && top2.get(1).equals("B");
        // Prints the result of the Top K logic test.
        System.out.println("Test 1 (Top 2 Exact Match): " + (test1 ? "PASS" : "FAIL"));

        // Initializes processor to handle a large data scenario gracefully.
        var pLarge = new LogStreamProcessorTopK();
        // Loops 10,000 times to simulate high traffic and create a leaderboard.
        for (int i = 0; i < 10000; i++) {
            // User0 gets 10,000 friends. User1 gets 9,999 friends... User9 gets 9,991 friends.
            for (int j = i; j < 10000; j++) {
                // Registers the large volume of events.
                pLarge.registerEvent(ts(), "User" + i, "Friend" + j, "Spam!");
            }
        }

        // Fetches the top 3 from this massive dataset.
        var top3 = pLarge.getTopKActiveUsers(3);
        // Validates that User0, User1, and User2 correctly took the top spots.
        boolean testLarge = top3.get(0).equals("User0") && top3.get(1).equals("User1") && top3.get(2).equals("User2");
        // Prints the result of the Large Data test.
        System.out.println("Test 2 (Large Data Top 3): " + (testLarge ? "PASS" : "FAIL"));
    }

    // Helper method to generate dummy timestamps for the large data test.
    private static long ts() {
        // Returns current time in milliseconds just to satisfy the method signature.
        return System.currentTimeMillis();
    }

    // Registers a message event between a sender and receiver in O(1) time.
    public void registerEvent(long ts, String sender, String receiver, String msg) {
        // Finds/creates the sender's Set, then adds the receiver to track the unique connection.
        network.computeIfAbsent(sender, k -> new HashSet<>()).add(receiver);
        // Finds/creates the receiver's Set, then adds the sender to track the unique connection.
        network.computeIfAbsent(receiver, k -> new HashSet<>()).add(sender);
    }

    // Returns a list of the 'k' most active users in descending order of their unique conversations.
    public List<String> getTopKActiveUsers(int k) {
        // Guard clause: if k is 0 or negative, instantly return an empty list to avoid errors.
        if (k <= 0) return Collections.emptyList();

        // Creates a Min-Heap (PriorityQueue). We use Comparator.comparingInt to sort by their connection count.
        // The user with the LOWEST count stays at the top/head of this queue.
        var minHeap = new PriorityQueue<String>(Comparator.comparingInt(user -> network.get(user).size()));

        // Loops through every unique user currently registered in our network map.
        for (String user : network.keySet()) {
            // Adds the current user into our Min-Heap "VIP Club".
            minHeap.offer(user);
            // If our club exceeds the allowed size 'k'...
            if (minHeap.size() > k) {
                // ...the bouncer kicks out the head of the queue (the user with the lowest score in the club).
                minHeap.poll();
            }
        }

        // Creates an ArrayList to hold our final Top K result.
        var result = new ArrayList<String>();
        // Loops until the heap is entirely empty.
        while (!minHeap.isEmpty()) {
            // Removes users from the heap and adds them to our result list.
            // Note: This pulls them out from lowest to highest.
            result.add(minHeap.poll());
        }

        // Reverses the list so the user with the absolute HIGHEST count appears at index 0.
        Collections.reverse(result);

        // Returns the final properly sorted list of Top K users.
        return result;
    }
}