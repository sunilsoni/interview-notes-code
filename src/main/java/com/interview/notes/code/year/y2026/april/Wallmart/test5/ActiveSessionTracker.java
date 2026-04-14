package com.interview.notes.code.year.y2026.april.Wallmart.test5;

import java.util.HashSet; // Imports HashSet for O(1) time complexity lookups and insertions
import java.util.List; // Imports List interface for our sequential log data
import java.util.Set; // Imports Set interface to represent unique active users

public class ActiveSessionTracker { // Defines the main class for the solution

    public static void main(String[] args) { // Simple main method used for testing to avoid JUnit overhead
        List<TestCase> tests = List.of( // Java 9+ List.of creates an immutable list of our test scenarios
            new TestCase( // Initializes the primary test case handling Raja's failure
                List.of( // Defines the ordered list of application logs
                    "10:00 Ramya LOGIN SUCCESS", // Ramya logs in -> State: Active
                    "10:05 Raja LOGIN SUCCESS", // Raja logs in -> State: Active
                    "10:10 Ramya LOGOUT SUCCESS", // Ramya logs out -> State: Inactive
                    "10:15 UserC LOGIN SUCCESS", // UserC logs in -> State: Active
                    "10:20 Raja ERROR FAIL", // Raja has an error -> NEW RULE: Treat as logout -> State: Inactive
                    "10:25 UserD LOGIN SUCCESS" // UserD logs in -> State: Active
                ), // Closes log list
                Set.of("UserC", "UserD") // Expected output: Raja is now REMOVED due to the error
            ) // Closes test case
        ); // Closes list initialization

        for (int i = 0; i < tests.size(); i++) { // Iterates over every test case for validation
            TestCase tc = tests.get(i); // Extracts the current test case payload
            Set<String> actual = getActiveUsers(tc.logs()); // Executes core logic against the test logs
            boolean passed = actual.equals(tc.expected()); // Checks if our calculated set matches the expected set
            System.out.println("Test " + (i + 1) + ": " + (passed ? "PASS" : "FAIL") + " | Active: " + actual); // Prints results to console
        } // Closes test loop
    } // Closes main method

    public static Set<String> getActiveUsers(List<String> logs) { // Core business logic method to process sessions
        return logs.stream() // Initiates a sequential Java 8 Stream over the log entries
            .map(log -> log.split(" ")) // Splits each string by spaces into a string array
            .filter(arr -> arr.length >= 4) // Filters out any malformed data to prevent IndexOutOfBounds exceptions
            .collect( // Collects the stream output into our final data structure
                HashSet::new, // Supplies a new empty HashSet to track the active sessions
                (activeSet, arr) -> { // BiConsumer defines exactly how to process each log array into the Set
                    String user = arr[1]; // Extracts the username at array index 1
                    String action = arr[2]; // Extracts the action (LOGIN/LOGOUT/ERROR) at index 2
                    String status = arr[3]; // Extracts the status (SUCCESS/FAIL) at index 3

                    // NEW LOGIC: If action is LOGOUT, ERROR, or status is FAIL, treat it as a logout event
                    if ("LOGOUT".equals(action) || "ERROR".equals(action) || "FAIL".equals(status)) { // Evaluates removal conditions
                        activeSet.remove(user); // Removes the user from the active sessions set
                    } else if ("LOGIN".equals(action) && "SUCCESS".equals(status)) { // Evaluates strict addition conditions
                        activeSet.add(user); // Adds the user to the active sessions set
                    } // Closes conditional block
                }, // Closes BiConsumer logic
                HashSet::addAll // Combiner method merges sets if parallel streams were utilized
            ); // Closes collect operation and returns the populated HashSet
    } // Closes logic method

    record TestCase(List<String> logs, Set<String> expected) {} // Java 14+ record creates an immutable container for test data to keep code short
} // Closes class