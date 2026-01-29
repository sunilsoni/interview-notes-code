package com.interview.notes.code.year.y2026.jan.cognizant.test3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class HighestSalaryGlobal {

    public static void main(String[] args) {
        // --- Test Case 1: Standard Scenario ---
        List<Employee> data1 = List.of(
                new Employee(1, "Alice", 50000, "IT"),
                new Employee(2, "Bob", 60000, "IT"),
                new Employee(3, "Charlie", 95000, "Finance"), // Global Highest
                new Employee(4, "David", 40000, "HR")
        );
        // We expect "Finance" because Charlie has the highest salary (95k)
        test("Standard Case", data1, "Finance");

        // --- Test Case 2: Tie/Duplicate Max ---
        List<Employee> data2 = List.of(
                new Employee(1, "A", 1000, "Sales"),
                new Employee(2, "B", 2000, "Marketing"), // Max
                new Employee(3, "C", 2000, "Admin")      // Also Max
        );
        // Should find one of the departments with 2000.
        // Note: .max() returns the first one encountered if ties exist, or arbitrary depending on implementation.
        // We will accept "Marketing" or "Admin". logic handles first found usually.
        test("Tie Case", data2, "Marketing");

        // --- Test Case 3: Empty List ---
        test("Empty Input", new ArrayList<>(), "None"); // Should handle safely

        // --- Test Case 4: Large Data (Performance) ---
        List<Employee> largeData = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < 1_000_000; i++) {
            // Mostly low salaries, but put one HUGE salary at the end
            double salary = r.nextDouble() * 1000;
            largeData.add(new Employee(i, "Emp" + i, salary, "RegularDept"));
        }
        // Add the winner manually
        largeData.add(new Employee(999, "Winner", 1_000_000, "WinnerDept"));

        test("Large Data 1M", largeData, "WinnerDept");
    }

    // --- Core Logic ---
    static String findDeptWithHighestSalary(List<Employee> employees) {
        if (employees == null || employees.isEmpty()) return "None"; // Edge case safety

        return employees.stream() // Start stream
                .max(Comparator.comparingDouble(Employee::salary)) // Find the single Max element based on salary
                .map(emp -> emp.department()) // If found, map to Department Name
                .orElse("None"); // Default if something goes wrong (e.g. empty)
    }

    // --- Helper for Testing ---
    static void test(String testName, List<Employee> input, String expected) {
        long start = System.currentTimeMillis();
        String result = findDeptWithHighestSalary(input); // Run logic
        long end = System.currentTimeMillis();

        boolean pass = result.equals(expected);

        // Print Output: PASS/FAIL | Test Name | Result | Time
        System.out.printf("[%s] %s | Expected: %s, Got: %s | Time: %dms%n",
                pass ? "PASS" : "FAIL", testName, expected, result, (end - start));
    }

    // Java 21 Record: Simple, immutable data carrier
    record Employee(int id, String name, double salary, String department) {
    }
}