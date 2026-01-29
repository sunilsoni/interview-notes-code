package com.interview.notes.code.year.y2026.jan.cognizant.test4;

import java.util.*;
import java.util.stream.Collectors;

public class HighestSalaryPerDept {

    public static void main(String[] args) {
        // --- Test Case 1: Standard Data ---
        List<Employee> data1 = List.of( // Create immutable list of employees
                new Employee(1, "Alice", 50000, "IT"), // IT: Low
                new Employee(2, "Bob", 80000, "IT"),   // IT: High (Winner)
                new Employee(3, "Charlie", 40000, "HR"), // HR: Low
                new Employee(4, "David", 60000, "HR"),   // HR: High (Winner)
                new Employee(5, "Eve", 70000, "Finance") // Finance: Only one
        );
        // We expect Bob (IT), David (HR), Eve (Finance)
        test("Standard Case", data1, 3); // Check if we got 3 departments back

        // --- Test Case 2: Duplicate Top Salaries ---
        List<Employee> data2 = List.of(
                new Employee(1, "A", 90000, "Sales"),
                new Employee(2, "B", 90000, "Sales") // Same salary
        );
        test("Duplicate Max", data2, 1); // Should return 1 dept (Sales), picks first found usually

        // --- Test Case 3: Empty List ---
        test("Empty Input", new ArrayList<>(), 0); // Should return 0 results safely

        // --- Test Case 4: Large Data (Performance) ---
        List<Employee> largeData = new ArrayList<>(); // Use ArrayList for dynamic addition
        Random r = new Random(); // Random number generator
        for (int i = 0; i < 1_000_000; i++) { // Loop 1 million times
            String dept = "Dept" + (i % 10); // Create 10 distinct departments
            largeData.add(new Employee(i, "Emp" + i, r.nextDouble(), dept)); // Add random data
        }
        test("Large Data 1M", largeData, 10); // We expect exactly 10 winners (1 per dept)
    }

    // --- Core Logic: Find Highest Salary Per Dept ---
    static Map<String, Employee> getTopEarners(List<Employee> list) {
        if (list == null || list.isEmpty()) return Collections.emptyMap(); // Fast exit if input is null/empty

        return list.stream() // Start streaming the list elements
                .collect(Collectors.groupingBy( // Group elements by a key
                        Employee::department, // Key: Department Name
                        Collectors.collectingAndThen( // Finish the collection with a final transformation
                                Collectors.maxBy(Comparator.comparingDouble(Employee::salary)), // Find max salary in group
                                Optional::get // Unwrap Optional (safe here as groupingBy only makes groups for existing items)
                        )
                ));
    }

    // --- Helper for Testing ---
    static void test(String name, List<Employee> input, int expectedCount) {
        long start = System.currentTimeMillis(); // Start timer
        Map<String, Employee> result = getTopEarners(input); // Execute logic
        long end = System.currentTimeMillis(); // Stop timer

        boolean pass = result.size() == expectedCount; // Validating simple count for brevity

        // Print result in simple format: PASS/FAIL | Test Name | Time
        System.out.printf("[%s] %s | Found: %d | Time: %dms%n",
                pass ? "PASS" : "FAIL", name, result.size(), (end - start));

        // Optional: Print actual winners for small datasets only
        if (input.size() < 10 && pass) {
            result.forEach((dept, emp) -> System.out.println("   -> " + dept + ": " + emp.name() + " (" + emp.salary() + ")"));
        }
    }

    // Java 21 Record: Immutable data carrier. Automatic constructor, getters, equals/hashcode.
    record Employee(int id, String name, double salary, String department) {
    }
}