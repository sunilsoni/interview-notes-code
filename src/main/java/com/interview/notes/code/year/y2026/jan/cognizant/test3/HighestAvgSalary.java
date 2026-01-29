package com.interview.notes.code.year.y2026.jan.cognizant.test3;

import java.util.*;
import java.util.stream.Collectors;

public class HighestAvgSalary {

    public static void main(String[] args) {
        // --- Test Case 1: Standard Scenario ---
        List<Employee> data1 = List.of( // Create immutable list of employees
                new Employee(1, "Alice", 50000, "IT"), // IT Avg: 55k
                new Employee(2, "Bob", 60000, "IT"),
                new Employee(3, "Charlie", 40000, "HR"), // HR Avg: 42.5k
                new Employee(4, "David", 45000, "HR"),
                new Employee(5, "Eve", 70000, "Finance") // Finance Avg: 70k (Highest)
        );
        test("Standard Case", data1, "Finance"); // Verify if result matches "Finance"

        // --- Test Case 2: One Department ---
        List<Employee> data2 = List.of(
                new Employee(1, "A", 1000, "Sales"),
                new Employee(2, "B", 2000, "Sales")
        );
        test("Single Dept", data2, "Sales"); // Should return the only department present

        // --- Test Case 3: Empty List ---
        test("Empty Input", new ArrayList<>(), null); // Should handle empty list gracefully (return null or default)

        // --- Test Case 4: Large Data Input (Performance Check) ---
        List<Employee> largeData = new ArrayList<>(); // Create simpler ArrayList for dynamic adding
        Random rand = new Random(); // Random generator for dummy data
        // Generate 1 million records to test O(N) performance
        for (int i = 0; i < 1_000_000; i++) {
            // Randomly assign very low salary to "LowDept" and high to "HighDept" to ensure predictability
            String dept = (i % 2 == 0) ? "LowDept" : "HighDept";
            double salary = (i % 2 == 0) ? 100 : 100000;
            largeData.add(new Employee(i, "Emp" + i, salary, dept)); // Add to list
        }
        test("Large Data (1M)", largeData, "HighDept"); // Expect "HighDept" to win efficiently
    }

    // --- Core Logic Method ---
    static String getTopDept(List<Employee> employees) {
        if (employees == null || employees.isEmpty()) return null; // Edge case: Handle empty/null inputs immediately

        return employees.stream() // Convert list to Stream to enable functional operations
                .collect(Collectors.groupingBy( // Group data by a specific key
                        Employee::department, // Key mapper: Group by department name
                        Collectors.averagingDouble(Employee::salary) // Downstream collector: Calculate average of salary field
                )) // Result is now Map<String, Double> (Dept -> AvgSalary)
                .entrySet() // Convert Map to Set<Entry> to stream over the results
                .stream() // Stream the entries
                .max(Map.Entry.comparingByValue()) // Find max entry based on the value (Average Salary)
                .map(Map.Entry::getKey) // Extract the key (Department Name) from the max entry
                .orElse(null); // Return null if logic fails (safeguard)
    }

    // --- Helper Method for Pass/Fail Testing ---
    static void test(String testName, List<Employee> input, String expected) {
        long startTime = System.currentTimeMillis(); // Capture start time for performance check
        String actual = getTopDept(input); // Run the logic
        long endTime = System.currentTimeMillis(); // Capture end time

        // Check if actual result matches expected result (handle nulls safely)
        boolean passed = Objects.equals(actual, expected);

        // Print formatted result: PASS/FAIL, Name, Result, and Execution Time
        System.out.printf("[%s] %s | Expected: %s, Got: %s | Time: %dms%n",
                passed ? "PASS" : "FAIL", // Conditional print for status
                testName, // Test case name
                expected, // What we wanted
                actual, // What we got
                (endTime - startTime) // How long it took
        );
    }

    // Java 21 Record: Immutable data carrier, reduces boilerplate (no getters/setters/constructors needed)
    record Employee(int id, String name, double salary, String department) {
    }
}