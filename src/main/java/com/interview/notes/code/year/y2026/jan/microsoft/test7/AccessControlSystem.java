package com.interview.notes.code.year.y2026.jan.microsoft.test7;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class AccessControlSystem {

    // --- DOMAIN RECORDS (Data Holders) ---

    public static void main(String[] args) {
        System.out.println("Starting Dynamic Access Control Tests...\n");

        // 1. Setup Data
        // Use 'new HashSet' to ensure the collection is editable
        var internPerms = new HashSet<String>(); // Starts empty
        var internRole = new Role("Intern", internPerms); // Create shared Role object

        // 2. Create Users (Both point to the SAME 'internRole' object)
        var eve = new User("Eve", internRole);
        var adam = new User("Adam", internRole);

        // 3. Initialize Service
        var service = new SecurityService();

        // --- TEST CASE 1: Verify Initial State (No Access) ---
        // Eve should NOT be able to view logs yet
        test("Eve Initial Access (View Logs)", service.isAllowed(eve, "VIEW_LOGS"), false);

        // --- TEST CASE 2: Dynamic Update (The "Promotion") ---
        System.out.println("LOG: Promoting Interns (Adding 'VIEW_LOGS')...");
        // We update the Role ONCE. We do not touch Eve or Adam.
        service.addPermission(internRole, "VIEW_LOGS");

        // Verify Eve now has access (She sees the updated Role)
        test("Eve Promoted Access", service.isAllowed(eve, "VIEW_LOGS"), true);

        // --- TEST CASE 3: Shared Reference Check ---
        // Adam should AUTOMATICALLY have access too, without us touching him
        test("Adam Automatic Access", service.isAllowed(adam, "VIEW_LOGS"), true);

        // --- TEST CASE 4: Large Data Performance ---
        // Proof that we don't need to loop through users
        testLargeDataUpdate(service, internRole);
    }

    // Helper method to run large scale test
    public static void testLargeDataUpdate(SecurityService service, Role role) {
        System.out.println("\nRunning Large Data Test...");

        // 1. Simulate 1 million users all pointing to the simplified Role
        // usage of 'range' creates a stream of numbers 0 to 1,000,000
        List<User> hugeUserList = IntStream.range(0, 1_000_000)
                .mapToObj(i -> new User("User" + i, role)) // Create 1M users
                .toList(); // Store them (Java 16+ shortcut)

        // 2. Add a new permission to the single Role object
        long start = System.currentTimeMillis();
        service.addPermission(role, "ACCESS_SERVER"); // Instant update
        long end = System.currentTimeMillis();

        // 3. Check the very last user in the list
        User lastUser = hugeUserList.get(999_999);
        boolean result = service.isAllowed(lastUser, "ACCESS_SERVER");

        // 4. Output results
        test("1 Million Users Update", result, true);
        System.out.println("Update took: " + (end - start) + "ms (Instant)");
    }

    // --- SERVICE (Business Logic) ---

    // Simple test helper to print PASS/FAIL
    public static void test(String name, boolean actual, boolean expected) {
        String status = (actual == expected) ? "PASS" : "FAIL";
        System.out.printf("[%s] %s (Expected: %s, Got: %s)%n", status, name, expected, actual);
    }

    // --- TESTING (Simple Main Method) ---

    // Role Record: Holds the role name and a MUTABLE Set of permissions
    // We use HashSet so we can add permissions later (Set.of() would be read-only)
    public record Role(String name, Set<String> permissions) {
        // Helper method: Checks if the permission exists in the set
        public boolean hasPermission(String permission) {
            return permissions.contains(permission); // O(1) fast lookup
        }
    }

    // User Record: Holds name and a REFERENCE to a Role object
    public record User(String name, Role role) {}

    public static class SecurityService {

        // Check if user has access
        public boolean isAllowed(User user, String action) {
            // Guard clause: Handle nulls to prevent crashes
            if (user == null || user.role() == null) return false;
            // Delegate the check to the user's assigned Role
            return user.role().hasPermission(action);
        }

        // NEW FEATURE: Add permission to an existing Role
        // This modifies the Role object directly. All users with this role see the change.
        public void addPermission(Role role, String newPermission) {
            role.permissions().add(newPermission); // Adds to the shared Set
        }
    }
}