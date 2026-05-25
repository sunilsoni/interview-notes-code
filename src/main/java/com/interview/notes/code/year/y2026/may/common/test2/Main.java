package com.interview.notes.code.year.y2026.may.common.test2;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = List.of(
            new Employee(1, "Amit", "IT", 70000),
            new Employee(2, "Ravi", "HR", 50000),
            new Employee(3, "Neha", "IT", 60000),
            new Employee(4, "Priya", "Finance", 90000),
            new Employee(5, "Kiran", "HR", 45000),
            new Employee(6, "Sonal", "Finance", 80000)
        );

        Optional.of(employees)
            .orElse(List.of())
            .stream()
            .sorted(Comparator.comparing(Employee::salary))
            .collect(Collectors.groupingBy(
                Employee::dept,
                LinkedHashMap::new,
                Collectors.toList()
            ))
            .forEach((dept, list) -> {
                System.out.println(dept);
                list.forEach(System.out::println);
            });
    }

    record Employee(int id, String name, String dept, double salary) {}
}