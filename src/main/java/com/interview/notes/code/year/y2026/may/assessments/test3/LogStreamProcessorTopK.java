package com.interview.notes.code.year.y2026.may.common.test2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LogStreamProcessorFast { // Declares the optimized processor class.

    // Uses a Map to link each username to a Set of unique users they've talked with.
    private final Map<String, Set<String>> network = new HashMap<>();

    // Tracks the current top user instantly in memory.
    private String topUser = null;

    // Tracks the highest conversation count achieved so far.
    private int maxCount = 0;

    // Simple main method for testing without JUnit
    public static void main(String[] args) {
        var p = new LogStreamProcessorFast();
        p.registerEvent(0, "A", "B", "Hi!");
        p.registerEvent(10, "A", "C", "Hi!");
        p.registerEvent(15, "C", "A", "Hi, there!"); // Duplicate connection test
        System.out.println("Basic Test: " + ("A".equals(p.getMostActiveUser()) ? "PASS" : "FAIL"));

        var pLarge = new LogStreamProcessorFast();
        for (int i = 0; i < 100000; i++) pLarge.registerEvent(i, "SpamBot", "User" + i, "Spam!");
        System.out.println("Large Data Test: " + ("SpamBot".equals(pLarge.getMostActiveUser()) ? "PASS" : "FAIL"));
    }

    // Registers a message event between a sender and receiver.
    public void registerEvent(long ts, String sender, String receiver, String msg) {
        // Retrieves or creates the sender's Set, adds the receiver, and checks if they took the lead.
        updateAndCheckLeader(sender, receiver);
        // Retrieves or creates the receiver's Set, adds the sender, and checks if they took the lead.
        updateAndCheckLeader(receiver, sender);
    }

    // Helper method to add a connection and update the leaderboard instantly.
    private void updateAndCheckLeader(String user, String partner) {
        // computeIfAbsent finds/creates the user's Set.
        var connections = network.computeIfAbsent(user, k -> new HashSet<>());

        // MINIMAL OPTIMIZATION: connections.add() returns 'true' ONLY if the partner is a new addition.
        // We now only check the size if a new person was actually added, skipping work on duplicate messages.
        if (connections.add(partner) && connections.size() > maxCount) {
            // If they beat the record, update the max count.
            maxCount = connections.size();
            // Crown this user as the new global top user.
            topUser = user;
        }
    }

    // Finds the user with the largest number of active conversations.
    public String getMostActiveUser() {
        // Instantly returns the tracked top user without scanning the map at all!
        return topUser;
    }
}