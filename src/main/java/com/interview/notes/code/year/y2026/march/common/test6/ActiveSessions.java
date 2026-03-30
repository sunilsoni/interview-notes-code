package com.interview.notes.code.year.y2026.march.common.test6;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ActiveSessions { // Main class wrapper required by Java to run the application
    
    public static void main(String[] args) { // The entry point of the program where execution begins
        
        // --- TEST CASE 1: Provided standard input --- // Comment separating our test cases
        List<String> test1 = List.of( // Using Java 9+ List.of for a concise, immutable list of test data
            "10:00 Sai LOGIN SUCCESS", // Sai logs in successfully
            "10:05 Rahul LOGIN SUCCESS", // Rahul logs in successfully
            "10:10 Sai LOGOUT SUCCESS", // Sai logs out successfully (should be removed from active)
            "10:15 UserC LOGIN SUCCESS", // UserC logs in successfully
            "10:20 Rahul ERROR FAIL", // Rahul fails his action, so his active state should not change
            "10:25 UserD LOGIN SUCCESS" // UserD logs in successfully
        ); // Closes the list definition
        
        Set<String> result1 = getActiveUsers(test1); // Calls our main logic method to evaluate the logs
        checkTest("Test 1 (Standard)", result1, Set.of("Rahul", "UserC", "UserD")); // Tests if our output matches the expected active users

        // --- TEST CASE 2: Large Data & Edge Scenarios --- // Comment for our second test block
        List<String> test2 = List.of( // Creating a new immutable list for edge cases
            "09:00 Alice LOGIN SUCCESS", // Alice logs in
            "09:30 Bob LOGIN SUCCESS", // Bob logs in
            "10:00 Alice LOGOUT SUCCESS", // Alice logs out safely
            "10:15 Alice LOGIN SUCCESS", // Alice logs back in again (edge case: multiple sessions)
            "10:20 Bob LOGOUT ERROR" // Bob fails to log out (edge case: unknown error string, should remain active)
        ); // Closes the second list
        
        Set<String> result2 = getActiveUsers(test2); // Calls our logic method for the second dataset
        checkTest("Test 2 (Edge Cases)", result2, Set.of("Alice", "Bob")); // Both should be active based on the logs
    } // Closes the main method

    // Core logic method to process logs and return a Set of active users
    static Set<String> getActiveUsers(List<String> logs) { // Accepts the log list and returns unique usernames
        Set<String> activeUsers = new HashSet<>(); // HashSet is used because it prevents duplicates and has fast O(1) lookups
        
        logs.stream().forEach(log -> { // Creates a stream from the list and iterates over each log line individually
            String[] parts = log.split(" "); // Splits the line by spaces to easily isolate the specific data columns
            String user = parts[1]; // Grabs the second word (index 1), which we know is the username
            String action = parts[2]; // Grabs the third word (index 2), which is the attempted action (LOGIN/LOGOUT)
            String status = parts[3]; // Grabs the fourth word (index 3), which is the result (SUCCESS/FAIL/ERROR)
            
            if ("LOGIN".equals(action) && "SUCCESS".equals(status)) { // Condition: Only proceed if they successfully logged in
                activeUsers.add(user); // Adds the user to our active set to mark them as currently online
            } else if ("LOGOUT".equals(action) && "SUCCESS".equals(status)) { // Condition: Only proceed if they successfully logged out
                activeUsers.remove(user); // Removes the user from our active set since their session ended cleanly
            } // We purposely don't have an 'else' block because failed states should not alter a user's session state
        }); // Closes the stream loop
        
        return activeUsers; // Returns the final populated set containing only currently active users
    } // Closes the getActiveUsers method

    // Custom testing method to replace JUnit as requested
    static void checkTest(String testName, Set<String> actual, Set<String> expected) { // Takes the test name, our generated output, and what we expect
        if (actual.equals(expected)) { // Compares the actual output Set against the expected Set
            System.out.println(testName + " -> PASS"); // Prints PASS to the console if the sets match perfectly
        } else { // Fallback if the sets do not match
            System.out.println(testName + " -> FAIL. Expected: " + expected + ", Got: " + actual); // Prints FAIL and shows where the mismatch happened for easy debugging
        } // Closes the if/else block
    } // Closes the checkTest method
} // Closes the ActiveSessions class