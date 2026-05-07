package com.interview.notes.code.year.y2026.may.common.test1;

import java.util.*; // Imports required Java utility libraries like Map, Set, HashMap, and HashSet.

public class LogStreamProcessor { // Declares the main processor class to handle chat logs.
    
    // Uses a Map to link each username to a Set of unique users they've talked with.
    private final Map<String, Set<String>> network = new HashMap<>(); 
    
    // Custom main method for testing, avoiding heavy frameworks like JUnit as requested.
    public static void main(String[] args) {
        // Executes our automated tests when the file is run.
        runTests();
    }

    // Runs specific scenarios and prints PASS/FAIL to the console.
    private static void runTests() {
        // Test 1: Initializes processor for a simple single conversation test.
        var p1 = new LogStreamProcessor();
        // Registers a single message from A to B.
        p1.registerEvent(0, "A", "B", "Hi!");
        // Verifies the top user is either A or B (List.of is a concise Java 9+ feature).
        System.out.println("Test 1 (Simple): " + (List.of("A", "B").contains(p1.getMostActiveUser()) ? "PASS" : "FAIL"));

        // Test 2: Initializes processor for a multiple conversation test based on the image example.
        var p2 = new LogStreamProcessor();
        // A talks to B.
        p2.registerEvent(0, "A", "B", "Hi!");
        // A talks to C.
        p2.registerEvent(10, "A", "C", "Hi!");
        // C replies to A (duplicate conversation pair, shouldn't increase the unique count).
        p2.registerEvent(15, "C", "A", "Hi, there!");
        // Verifies A is the most active since A has 2 partners (B,C) while B and C only have 1 (A).
        System.out.println("Test 2 (Multiple): " + ("A".equals(p2.getMostActiveUser()) ? "PASS" : "FAIL"));

        // Test 3: Initializes processor to handle a large data scenario gracefully.
        var p3 = new LogStreamProcessor();
        // Loops 100,000 times to simulate high traffic and test system limits.
        for (int i = 0; i < 100000; i++) {
            // One 'SpamBot' user sends a message to 100,000 completely unique users.
            p3.registerEvent(i, "SpamBot", "User" + i, "Spam!");
        }
        // Verifies the Stream API correctly and quickly identifies the SpamBot as the top user among 100,001 users.
        System.out.println("Test 3 (Large Data): " + ("SpamBot".equals(p3.getMostActiveUser()) ? "PASS" : "FAIL"));
    }

    // Registers a message event between a sender and receiver.
    public void registerEvent(long ts, String sender, String receiver, String msg) {
        // Uses computeIfAbsent (Java 8+) to find/create the sender's Set, then adds the receiver.
        network.computeIfAbsent(sender, k -> new HashSet<>()).add(receiver);
        // Uses computeIfAbsent to find/create the receiver's Set, then adds the sender.
        network.computeIfAbsent(receiver, k -> new HashSet<>()).add(sender);
    }

    // Finds the user with the largest number of active conversations.
    public String getMostActiveUser() {
        // Creates a Stream (Java 8 API) of all the map entries to process them concisely.
        return network.entrySet().stream()
                // Finds the maximum entry by comparing the size of their conversation sets.
                .max(Comparator.comparingInt(e -> e.getValue().size()))
                // Extracts just the username string from the resulting max map entry.
                .map(Map.Entry::getKey)
                // Returns the username, or null if the system has no logs yet.
                .orElse(null);
    }
}