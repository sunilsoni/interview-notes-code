package com.interview.notes.code.year.y2026.feb.common.test1;

import java.util.List;
import java.util.stream.IntStream;

public class SalaryOptimizer {

    public static void main(String[] args) {
        // --- TEST CASE 1: Standard Scenario ---
        List<Employee> input1 = List.of(
            new Employee(1, "Alice", 15000), // Should increase to 16500
            new Employee(2, "Bob", 25000),   // Should stay 25000
            new Employee(3, "Charlie", 19999) // Should increase to 21998.9
        );
        List<Employee> output1 = updateSalaries(input1);

        // Validation logic for Case 1
        boolean test1Pass = output1.get(0).salary() == 16500 &&
                            output1.get(1).salary() == 25000 &&
                            output1.get(2).salary() == 21998.9;
        printTestResult("Standard Mixed Data", test1Pass);

        // --- TEST CASE 2: Edge Case (Exact Boundary) ---
        List<Employee> input2 = List.of(new Employee(4, "David", 20000)); // Should NOT increase
        List<Employee> output2 = updateSalaries(input2);

        // Validation: 20000 is not "less than" 20000, so it remains same.
        boolean test2Pass = output2.get(0).salary() == 20000;
        printTestResult("Boundary Condition (20k)", test2Pass);

        // --- TEST CASE 3: Large Data Simulation (Performance) ---
        // Generating 1 million records efficiently using IntStream
        List<Employee> largeInput = IntStream.range(0, 1_000_000)
            .mapToObj(i -> new Employee(i, "Emp" + i, 10000)) // All 10k salaries
            .toList();

        long startTime = System.currentTimeMillis(); // Start timer
        List<Employee> largeOutput = updateSalaries(largeInput); // Process
        long endTime = System.currentTimeMillis(); // End timer

        // Validation: Check first and last element to ensure data integrity
        boolean test3Pass = largeOutput.size() == 1_000_000 &&
                            largeOutput.get(0).salary() == 11000 &&
                            largeOutput.get(999_999).salary() == 11000;

        printTestResult("Large Dataset (1 Million)", test3Pass);
        System.out.println("Processing Time for 1M records: " + (endTime - startTime) + "ms");
    }

    // Core Logic Method
    public static List<Employee> updateSalaries(List<Employee> employees) {
        return employees.stream() // Convert list to a Stream for processing
            .map(emp -> { // Transform each element
                // Check if salary is strictly less than threshold (20,000)
                if (emp.salary() < 20000) {
                    // Create NEW record with 10% hike (Salary * 1.10)
                    return new Employee(emp.id(), emp.name(), emp.salary() * 1.10);
                }
                // If condition false, return original record as is
                return emp;
            })
            .toList(); // Java 16+ feature: Collects stream to unmodifiable List
    }

    // Helper to print PASS/FAIL in a readable format
    private static void printTestResult(String caseName, boolean isPass) {
        // Printing formatted result string
        System.out.println("Test Case: " + caseName + " -> " + (isPass ? "PASS" : "FAIL"));
    }

    // Java 21 Record: Reduces boilerplate. Automatically creates constructor, getters, equals, hashcode, and toString.
    // We use 'double' for salary for simplicity in this calculation context.
    record Employee(int id, String name, double salary) {}
}