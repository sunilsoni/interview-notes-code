package com.interview.notes.code.year.y2026.march.common.test3;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class EmailFetcher { // Define the main public class that contains our business logic and testing methods

    public static String getEmailSafely(Map<String, User> users, String name) { // Public method using generics to ensure type safety, accepting the map and the target user's name
        return Optional.ofNullable(users) // Wrap the map in an Optional to safely intercept cases where the map object itself might be null
                .map(m -> m.get(name)) // If the map is present, attempt to retrieve the user by name; if the user doesn't exist, this evaluates to null safely
                .map(User::email) // If a user object was found, extract their email using a method reference to the record's built-in accessor
                .orElse("No Email Found"); // If at any point we encountered a null (null map, missing user, or null email field), return this safe default string
    } // Close the robust getEmailSafely method

    public static void main(String[] args) { // Standalone main method used to execute our test suite without relying on external testing frameworks

        // --- Test Setup --- // Comment marking the data initialization phase
        Map<String, User> userMap = new HashMap<>(); // Initialize a new HashMap instance in memory to hold our test data
        userMap.put("Alice", new User("alice@example.com")); // Populate the map with a standard, valid user and email
        userMap.put("Bob", new User(null)); // Populate the map with a user who exists but has a null email to test deep null safety

        // --- Test Case 1: Valid User Fetch --- // Comment marking the first logical test
        String result1 = getEmailSafely(userMap, "Alice"); // Invoke the method to fetch an email for a user we know exists
        System.out.println("Test 1 (Valid User): " + ("alice@example.com".equals(result1) ? "PASS" : "FAIL")); // Check if the correct email was returned and print PASS or FAIL

        // --- Test Case 2: Missing User Handling --- // Comment marking the second logical test
        String result2 = getEmailSafely(userMap, "Charlie"); // Invoke the method searching for a name that is not present in the map keys
        System.out.println("Test 2 (Missing User): " + ("No Email Found".equals(result2) ? "PASS" : "FAIL")); // Verify the method intercepted the null and returned the fallback string

        // --- Test Case 3: Null Map Handling --- // Comment marking the third logical test
        String result3 = getEmailSafely(null, "Alice"); // Pass a completely null map reference directly into the method
        System.out.println("Test 3 (Null Map): " + ("No Email Found".equals(result3) ? "PASS" : "FAIL")); // Verify the Optional chain stopped at the first step and prevented a crash

        // --- Test Case 4: Null Email Field Handling --- // Comment marking the fourth logical test
        String result4 = getEmailSafely(userMap, "Bob"); // Fetch the user Bob, whose actual email string is null inside the record
        System.out.println("Test 4 (Null Email Field): " + ("No Email Found".equals(result4) ? "PASS" : "FAIL")); // Verify the Optional chain caught the null property inside the object

        // --- Test Case 5: Large Data Performance --- // Comment marking the large-scale performance test
        Map<String, User> largeMap = new HashMap<>(); // Instantiate a new HashMap intended to hold a massive amount of records
        for (int i = 0; i < 100000; i++) { // Initiate a loop to execute one hundred thousand times
            largeMap.put("User" + i, new User("user" + i + "@example.com")); // Inject a mathematically generated username and email into the map at each iteration
        } // Terminate the data generation loop
        long startTime = System.nanoTime(); // Capture the exact system time in nanoseconds before we execute the search
        String largeResult = getEmailSafely(largeMap, "User99999"); // Search for a specific user located at the very end of the massive dataset
        long endTime = System.nanoTime(); // Capture the system time immediately after the search completes
        boolean isFast = (endTime - startTime) < 5000000; // Evaluate if the elapsed time was less than 5 milliseconds, proving O(1) performance is maintained
        System.out.println("Test 5 (Large Data O(1) Lookup): " + (isFast && "user99999@example.com".equals(largeResult) ? "PASS" : "FAIL")); // Print PASS only if both the result is correct AND the performance benchmark was met

    } // Close the main execution method

    record User(String email) {} // Java 21 feature: 'record' defines an immutable data carrier for a User, automatically generating a constructor and accessor methods
} // Close the encapsulating class