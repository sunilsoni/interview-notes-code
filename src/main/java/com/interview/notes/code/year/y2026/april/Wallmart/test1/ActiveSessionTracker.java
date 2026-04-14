package com.interview.notes.code.year.y2026.april.Wallmart.test1;

import java.util.HashSet; // Imports the HashSet class for O(1) collection operations
import java.util.List; // Imports the List interface for storing our ordered log data
import java.util.Set; // Imports the Set interface for our return type

public class ActiveSessionTracker { // Defines the main class for our solution

    public static void main(String[] args) { // Standard main method used for testing instead of JUnit
        List<TestCase> tests = List.of( // Java 9+ feature to initialize an immutable list of test cases
            new TestCase( // Instantiates the first test case based on the provided interview prompt
                List.of( // Initializes the list of log strings for the primary scenario
                    "10:00 Ramya LOGIN SUCCESS", // Ramya logs in, added to active
                    "10:05 Raja LOGIN SUCCESS", // Raja logs in, added to active
                    "10:10 Ramya LOGOUT SUCCESS", // Ramya logs out, removed from active
                    "10:15 UserC LOGIN SUCCESS", // UserC logs in, added to active
                    "10:20 Raja ERROR FAIL", // Raja experiences an error, ignored by filter
                    "10:25 UserD LOGIN SUCCESS" // UserD logs in, added to active
                ), // Ends the input list for the first case
                Set.of("Raja", "UserC", "UserD") // Defines the expected output state
            ), // Ends the first test case
            new TestCase( // Instantiates a second test case to test edge scenarios and larger scale logic
                List.of( // Initializes a new list of operations
                    "09:00 Sunil LOGIN SUCCESS", // Sunil logs in successfully
                    "09:30 Sunil LOGOUT FAIL", // Sunil's logout fails, should remain active
                    "10:00 Oishi LOGIN SUCCESS", // Oishi logs in successfully
                    "10:15 Oishi LOGOUT SUCCESS", // Oishi logs out successfully
                    "10:30 Oishi LOGOUT FAIL" // Duplicate failed logout, ignored
                ), // Ends the input list for the second case
                Set.of("Sunil") // Expected output: Only Sunil remains active
            ) // Ends the second test case
        ); // Ends test initialization

        for (int i = 0; i < tests.size(); i++) { // Loops through every test case to verify logic
            TestCase tc = tests.get(i); // Extracts the current test case object
            Set<String> actual = getActiveUsers(tc.logs()); // Passes the input logs to our core business logic
            boolean passed = actual.equals(tc.expected()); // Evaluates if the method output matches expectations
            System.out.println("Test " + (i + 1) + ": " + (passed ? "PASS" : "FAIL") + " | Active: " + actual); // Outputs test results to console
        } // Ends the testing loop
    } // Ends the main method

    public static Set<String> getActiveUsers(List<String> logs) { // Core method encapsulating the business logic
        return logs.stream() // Converts the List into a Java 8 Stream for sequential processing
            .map(log -> log.split(" ")) // Splits each log string by whitespace into an array
            .filter(arr -> arr.length >= 4 && "SUCCESS".equals(arr[3])) // Filters out malformed logs and non-SUCCESS states
            .collect( // Terminates stream and collects results into a customized container
                HashSet::new, // Supplies a new empty HashSet to hold active user strings
                (activeSet, arr) -> { // BiConsumer defining how to accumulate items
                    if ("LOGIN".equals(arr[2])) activeSet.add(arr[1]); // Adds username at index 1 to Set if action is LOGIN
                    if ("LOGOUT".equals(arr[2])) activeSet.remove(arr[1]); // Removes username from Set if action is LOGOUT
                }, // Ends accumulation logic
                HashSet::addAll // Combiner method required to merge sets if stream were executed in parallel
            ); // Ends collect operation and returns the generated Set
    } // Ends the core method

    record TestCase(List<String> logs, Set<String> expected) {} // Java 14+ feature to create an immutable data carrier for tests
} // Ends the class