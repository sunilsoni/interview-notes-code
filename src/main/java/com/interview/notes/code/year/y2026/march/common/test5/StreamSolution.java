package com.interview.notes.code.year.y2026.march.common.test5;

import java.util.*; // Import utility classes for List, Map, and Optional.

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.maxBy;

// Java 21 Record: Zero boilerplate for Name, Salary, and Department.
record Employee(String name, double salary, String department) {}

public class StreamSolution {
    public static void main(String[] args) {
        // Create test data with 12 employees across different departments.
        List<Employee> employees = new ArrayList<>(List.of(
            new Employee("Alice", 60000, "IT"),     new Employee("Bob", 45000, "IT"),
            new Employee("Charlie", 75000, "IT"),   new Employee("David", 55000, "HR"),
            new Employee("Eve", 80000, "HR"),       new Employee("Frank", 40000, "HR"),
            new Employee("Grace", 90000, "Sales"),  new Employee("Heidi", 52000, "Sales"),
            new Employee("Ivan", 48000, "Sales"),   new Employee("Jack", 65000, "Finance"),
            new Employee("Karl", 70000, "Finance"), new Employee("Linda", 30000, "Finance")
        ));

        // Execute the logic.
        Map<String, Optional<Employee>> result = getHighestPaidByDept(employees);

        // Simple Test Validation: Check if specific top earners are correct.
        boolean pass = result.get("HR").get().name().equals("Eve") && 
                       result.get("IT").get().name().equals("Charlie") &&
                       !result.containsKey("Unknown"); // Should not have empty depts.

        System.out.println("Test Status: " + (pass ? "PASS" : "FAIL"));
        
        // Print results to verify sorting and max values.
        result.forEach((dept, emp) -> 
            System.out.println(dept + " -> " + emp.get().name() + " (" + emp.get().salary() + ")"));
    }

    /**
     * Filters by salary, groups by department (sorted), and finds max salary per group.
     */
    public static Map<String, Optional<Employee>> getHighestPaidByDept(List<Employee> list) {
        return list.stream() // Convert list to a data stream.
            .filter(e -> e.salary() > 50000) // Line 1: Remove anyone earning 50k or less.
            .collect(groupingBy( // Line 2: Start grouping logic.
                Employee::department, // Line 3: Group by the department field.
                TreeMap::new, // Line 4: Use TreeMap to keep department names sorted A-Z.
                maxBy(Comparator.comparingDouble(Employee::salary)) // Line 5: Find the max salary in each group.
            ));
    }
}