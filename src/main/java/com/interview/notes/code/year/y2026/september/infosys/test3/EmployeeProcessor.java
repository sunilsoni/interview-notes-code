package com.interview.notes.code.year.y2026.september.infosys.test3;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

record Employee(int id, String name, double salary) {}

public class EmployeeProcessor {
    static void main(String[] args) {
        List<Employee> employees = List.of(
            new Employee(1, "Alice", 90000),
            new Employee(2, "Bob", 120000),
            new Employee(1, "Alice Dup", 80000), // Duplicate ID
            new Employee(3, "Charlie", 150000),
            new Employee(4, "Dave", 110000)
        );

        employees.stream()
            // 1. Remove duplicates by ID (keeps the first occurrence)
            .collect(Collectors.toMap(Employee::id, e -> e, (existing, replacement) -> existing))
            .values().stream()
            // 2. Sort by salary descending
            .sorted(Comparator.comparingDouble(Employee::salary).reversed())
            // 3. Keep top 3
            .limit(3)
            // 4. Print results
            .forEach(System.out::println);
    }
}