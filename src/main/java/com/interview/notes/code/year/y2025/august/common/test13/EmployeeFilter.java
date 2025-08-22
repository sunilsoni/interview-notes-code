package com.interview.notes.code.year.y2025.august.common.test13;

import java.util.*;
import java.util.stream.Collectors;

record Employee(String name, String department, double salary) {}

public class EmployeeFilter {
    public static void main(String[] args) {
        var employees = List.of(
                new Employee("Alice", "IT", 75000),
                new Employee("Bob", "HR", 50000),
                new Employee("Charlie", "IT", 65000),
                new Employee("David", "Finance", 80000),
                new Employee("Eve", "IT", 55000),
                new Employee("Frank", "IT", 90000)
        );

        var result = employees.stream()
                .filter(e -> e.department().equalsIgnoreCase("IT") && e.salary() > 60000)
                .sorted(Comparator.comparingDouble(Employee::salary).reversed())
                .map(Employee::name)
                .toList(); // Java 16+ concise collector

        System.out.println(result);
    }
}