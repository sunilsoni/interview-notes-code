package com.interview.notes.code.year.y2026.june.common.test10;

import java.util.*; // Import utility classes needed for Collections, Lists, Maps, and Sets.
import java.util.stream.Collectors; // Import Collectors utility to perform grouping and mapping in the Stream API.

public class BankEmployeeManager { // Define the main class that encapsulates our employee management logic.

    public static Map<Integer, List<String>> processEmployeeRoles(List<Employee> employees) { // Method to transform the flat list into the required HashMap.
        if (employees == null || employees.isEmpty()) { // Check if the input list is null or empty to prevent NullPointerException.
            return new HashMap<>(); // Return an empty Map early if there is no data to process.
        } // Close the if-condition block.

        return employees.stream() // Convert the List into a Stream to enable functional-style data processing.
                .filter(Objects::nonNull) // Filter out any completely null Employee objects from the stream.
                .filter(e -> e.id() != null && e.role() != null) // Filter out entries missing an ID or Role since they cannot be mapped.
                .collect(Collectors.groupingBy( // Group the remaining valid stream elements into a Map.
                        Employee::id, // Designate the employee's ID as the key for the resulting Map.
                        Collectors.mapping( // Specify how to transform the values associated with each key.
                                Employee::role, // Extract only the role string from the Employee object.
                                Collectors.collectingAndThen( // Perform a two-step collection process to ensure uniqueness and correct type.
                                        Collectors.toSet(), // First, collect roles into a Set, which automatically rejects duplicate roles.
                                        ArrayList::new // Then, pass that Set to the ArrayList constructor to fulfill the List return requirement.
                                ) // Close the collectingAndThen operation.
                        ) // Close the mapping operation.
                )); // Close the groupingBy and collect operations, returning the final Map.
    } // Close the processEmployeeRoles method.

    public static void main(String[] args) { // Main method to serve as the entry point and custom test runner.
        runAllTests(); // Call the custom test execution method to validate our logic.
    } // Close the main method.

    // 4. Testing & Validation Execution
    private static void runAllTests() { // Custom test runner method to avoid using JUnit.

        // Test Case 1: Example Input provided in the requirements.
        List<Employee> standardInput = Arrays.asList( // Create a list of employees based on the prompt's example.
                new Employee(101, "Alice", "Manager"), // Add Alice as Manager.
                new Employee(101, "Alice", "Teller"), // Add Alice as Teller.
                new Employee(102, "Bob", "Clerk"), // Add Bob as Clerk.
                new Employee(103, "Charlie", "Manager"), // Add Charlie as Manager.
                new Employee(103, "Charlie", "Auditor") // Add Charlie as Auditor.
        ); // Close standard input list initialization.
        Map<Integer, List<String>> expected1 = Map.of( // Define the expected output map for standard input.
                101, Arrays.asList("Manager", "Teller"), // Alice should have two unique roles.
                102, List.of("Clerk"), // Bob should have one role.
                103, Arrays.asList("Manager", "Auditor") // Charlie should have two unique roles.
        ); // Close expected map initialization.
        evaluateTest("Test 1: Standard Input", processEmployeeRoles(standardInput), expected1); // Run and evaluate Test 1.

        // Test Case 2: Handling duplicates for the same employee.
        List<Employee> duplicateInput = Arrays.asList( // Create a list with duplicate roles to test uniqueness filter.
                new Employee(101, "Alice", "Manager"), // Add Alice as Manager.
                new Employee(101, "Alice", "Manager") // Add Alice as Manager AGAIN to trigger duplicate logic.
        ); // Close duplicate input list initialization.
        Map<Integer, List<String>> expected2 = Map.of(101, List.of("Manager")); // Expected output should only contain one Manager role.
        evaluateTest("Test 2: Duplicate Roles", processEmployeeRoles(duplicateInput), expected2); // Run and evaluate Test 2.

        // Test Case 3: Empty List and Null Inputs.
        List<Employee> emptyInput = new ArrayList<>(); // Create an empty list to test edge case handling.
        evaluateTest("Test 3: Empty List", processEmployeeRoles(emptyInput), new HashMap<>()); // Run and evaluate Test 3 (expecting empty map).

        // Test Case 4: Invalid Data (Null objects and null fields).
        List<Employee> invalidInput = Arrays.asList( // Create a list with corrupt data to test safety filters.
                null, // A completely null object in the list.
                new Employee(null, "Ghost", "Teller"), // An employee with a null ID.
                new Employee(104, "Dave", null), // An employee with a null role.
                new Employee(105, "Eve", "VP") // A valid employee to ensure the stream doesn't crash and still processes good data.
        ); // Close invalid input list initialization.
        Map<Integer, List<String>> expected4 = Map.of(105, List.of("VP")); // Only the valid employee (Eve) should survive the filters.
        evaluateTest("Test 4: Null/Invalid Data", processEmployeeRoles(invalidInput), expected4); // Run and evaluate Test 4.

        // Test Case 5: Large Data Input.
        List<Employee> largeInput = new ArrayList<>(); // Create a list to hold a massive amount of data.
        for (int i = 0; i < 100000; i++) { // Loop 100,000 times to simulate a large enterprise dataset.
            largeInput.add(new Employee(i % 100, "Emp" + i, "Role" + (i % 3))); // Add employees mapping to 100 unique IDs and 3 roles each.
        } // Close large data loop.
        Map<Integer, List<String>> largeResult = processEmployeeRoles(largeInput); // Process the massive dataset.
        boolean isLargeDataPass = largeResult.size() == 100 && largeResult.get(0).size() == 3; // Verify we got 100 unique IDs, each with 3 unique roles.
        System.out.println("Test 5: Large Data Processing -> " + (isLargeDataPass ? "PASS" : "FAIL")); // Manually print the result for the large data test.
    } // Close the runAllTests method.

    private static void evaluateTest(String testName, Map<Integer, List<String>> actual, Map<Integer, List<String>> expected) { // Helper method to compare actual vs expected maps.
        boolean isPass = true; // Assume the test passes initially.
        if (actual.size() != expected.size()) { // Check if the number of keys matches.
            isPass = false; // Fail the test if map sizes differ.
        } else { // If sizes match, inspect the contents.
            for (Map.Entry<Integer, List<String>> entry : expected.entrySet()) { // Iterate through each expected key-value pair.
                List<String> expectedRoles = entry.getValue(); // Get the expected list of roles.
                List<String> actualRoles = actual.get(entry.getKey()); // Get the actual list of roles computed by our method.
                if (actualRoles == null || !new HashSet<>(actualRoles).containsAll(expectedRoles) || actualRoles.size() != expectedRoles.size()) { // Compare ignoring list order by using Sets.
                    isPass = false; // Fail the test if roles don't match exactly.
                    break; // Exit the loop early since the test has already failed.
                } // Close role comparison check.
            } // Close iteration over map entries.
        } // Close else block.
        System.out.println(testName + " -> " + (isPass ? "PASS" : "FAIL")); // Output the final result of the test case to the console.
    } // Close evaluateTest method.

    public record Employee(Integer id, String name, String role) {} // Use Java record to create an immutable data carrier, minimizing boilerplate code.
} // Close the class.