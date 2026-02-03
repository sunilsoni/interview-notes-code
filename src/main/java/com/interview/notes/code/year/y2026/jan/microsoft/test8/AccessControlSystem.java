package com.interview.notes.code.year.y2026.jan.microsoft.test8;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// Main class containing the system and the tests
public class AccessControlSystem {

    // --- DOMAIN MODELS (Using Java Records for conciseness) ---

    public static void main(String[] args) {
        System.out.println("Starting Access Control Tests...\n"); // Print start message

        // 1. Setup Data: Create Permissions using Set.of() (Java 9+ immutable sets)
        // Admin gets all permissions
        var adminPerms = Set.of("OPEN_DOOR", "VIEW_LOGS", "ACCESS_SERVER"); // 'var' infers type
        // Guest gets limited permissions
        var guestPerms = Set.of("OPEN_DOOR"); // Guests can only open doors

        // 2. Setup Roles
        var adminRole = new Role("Admin", adminPerms); // Create Admin role
        var guestRole = new Role("Guest", guestPerms); // Create Guest role
        var emptyRole = new Role("Intern", new HashSet<>()); // Create role with no permissions

        // 3. Setup Users
        var alice = new User("Alice", adminRole); // Alice is an Admin
        var bob = new User("Bob", guestRole); // Bob is a Guest
        var eve = new User("Eve", emptyRole); // Eve is an Intern

        // Initialize Service
        var service = new SecurityService(); // Instance of our logic class

        // --- EXECUTE TEST CASES ---

        // Test Case 1: Positive - Admin should access server
        test("Admin Access Server", service.isAllowed(alice, "ACCESS_SERVER"), true);

        // Test Case 2: Negative - Guest should NOT access server
        test("Guest Access Server", service.isAllowed(bob, "ACCESS_SERVER"), false);

        // Test Case 3: Positive - Guest should open door
        test("Guest Open Door", service.isAllowed(bob, "OPEN_DOOR"), true);

        // Test Case 4: Edge Case - Role with no permissions
        test("Intern No Access", service.isAllowed(eve, "OPEN_DOOR"), false);

        // Test Case 5: Edge Case - Null User input
        test("Null User Check", service.isAllowed(null, "OPEN_DOOR"), false);

        // Test Case 6: Edge Case - Checking permission that doesn't exist
        test("Unknown Permission", service.isAllowed(alice, "FLY_TO_MARS"), false);

        // --- LARGE DATA TEST ---
        System.out.println("\nRunning Large Data Performance Test..."); // Log section
        long startTime = System.currentTimeMillis(); // Capture start time

        // Generate 1 million users using Java Streams
        // All assigned the 'Guest' role for this test
        List<User> hugeUserList = IntStream.range(0, 1_000_000) // Loop 0 to 1 million
                .mapToObj(i -> new User("User" + i, guestRole)) // Create User obj for each number
                .collect(Collectors.toList()); // Collect into a List

        // Check permission for the last user in the list (User999999)
        boolean result = service.isAllowed(hugeUserList.get(999_999), "OPEN_DOOR"); // Perform check

        long endTime = System.currentTimeMillis(); // Capture end time

        // Verify result and print time taken
        test("Large Data (1M Users) Check", result, true);
        System.out.println("Time taken for 1M check: " + (endTime - startTime) + "ms"); // Print duration
    }

    // Helper method to print PASS/FAIL based on expected result
    public static void test(String testName, boolean actual, boolean expected) {
        String status = (actual == expected) ? "PASS" : "FAIL"; // Determine status
        // Print formatted result: [PASS] Test Name
        System.out.printf("[%s] %s%n", status, testName);
        if (!status.equals("PASS")) { // If failed, print details
            System.err.println("   -> Expected: " + expected + ", Got: " + actual); // Error detail
        }
    }

    // --- SYSTEM LOGIC ---

    // Record for Role: Holds a name and a Set of unique permission strings
    // 'record' automatically creates constructor, getters, equals(), and hashCode()
    public record Role(String name, Set<String> permissions) {
        // Helper method to check if this role has a specific permission
        public boolean hasAccess(String permission) {
            return permissions.contains(permission); // O(1) lookup speed
        }
    }

    // --- TESTING FRAMEWORK (No JUnit, Simple Main) ---

    // Record for User: Holds the user's name and their assigned Role
    public record User(String name, Role role) {}

    // The service class that manages the logic
    public static class SecurityService {

        // Method to check if a user is allowed to perform an action
        public boolean isAllowed(User user, String action) {
            if (user == null || user.role() == null) return false; // Safety check: reject null users
            return user.role().hasAccess(action); // Delegate check to the Role record
        }
    }
}