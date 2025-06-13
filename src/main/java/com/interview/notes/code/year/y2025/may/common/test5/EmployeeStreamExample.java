package com.interview.notes.code.year.y2025.may.common.test5;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

record Employee(int id, String name, String city) {}

public class EmployeeStreamExample {
    public static void main(String[] args) {
        var employees = List.of(
            new Employee(1, "John", "New York"),
            new Employee(2, "Alice", "London"),
            new Employee(3, "Bob", "New York"),
            new Employee(4, "Sarah", "Paris"),
            new Employee(5, "Mike", "London")
        );

        // Method 1: Using Collectors.toSet()
        var uniqueCities1 = employees.stream()
                .map(Employee::city)
                .collect(Collectors.toSet());
        System.out.println("Unique cities (Set): " + uniqueCities1);

        // Method 2: Using HashSet constructor
        var uniqueCities2 = new HashSet<>(employees.stream()
                .map(Employee::city)
                .collect(Collectors.toList()));
        System.out.println("Unique cities (HashSet): " + uniqueCities2);

        // Method 3: Using toCollection with HashSet
        var uniqueCities3 = employees.stream()
                .map(Employee::city)
                .collect(Collectors.toCollection(HashSet::new));
        System.out.println("Unique cities (toCollection): " + uniqueCities3);
    }
}
