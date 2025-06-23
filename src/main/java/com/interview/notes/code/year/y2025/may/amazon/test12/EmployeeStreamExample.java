package com.interview.notes.code.year.y2025.may.amazon.test12;

import java.util.List;
import java.util.stream.Collectors;

// Employee record - automatically creates constructor, getters, equals, hashCode, and toString
record Employee(int id, String name, String city) {
}

public class EmployeeStreamExample {
    public static void main(String[] args) {
        // Create list of employees
        var employees = List.of(
                new Employee(1, "John", "New York"),
                new Employee(2, "Alice", "London"),
                new Employee(3, "Bob", "New York"),
                new Employee(4, "Sarah", "Paris"),
                new Employee(5, "Mike", "London")
        );

        // Get unique cities (Method 1)
        var uniqueCities1 = employees.stream()
                .map(Employee::city)  // Using record's automatically generated getter
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Unique cities: " + uniqueCities1);

        // Get unique cities (Method 2)
        var uniqueCities2 = employees.stream()
                .map(Employee::city)
                .collect(Collectors.toSet());
        System.out.println("Unique cities: " + uniqueCities2);

        List<String> names = List.of("Sunil", "Alice", "Bob");

        names.stream()
                .map(String::toUpperCase); // No terminal operation → Nothing happens

    }
}
