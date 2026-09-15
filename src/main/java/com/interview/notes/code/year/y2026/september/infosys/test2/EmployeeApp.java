package com.interview.notes.code.year.y2026.september.infosys.test2;

import java.util.Comparator;
import java.util.List;

record Employee(int id, String name, double salary) {}

public class EmployeeApp {
    static void main(String[] args) {
        var employees = List.of(
            new Employee(1, "Alice", 50000.0),
            new Employee(2, "Charlie", 60000.0),
            new Employee(3, "Bob", 75000.0)
        );

        var highestPaid = employees.stream()
            .sorted(Comparator.comparingDouble(Employee::salary).reversed())
            .findFirst()
            .orElse(null);

        System.out.println("Highest paid employee: " + highestPaid);
    }
}