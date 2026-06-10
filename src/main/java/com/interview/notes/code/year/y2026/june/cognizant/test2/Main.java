package com.interview.notes.code.year.y2026.june.cognizant.test2;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

record Employee(String name, String department, double salary) {}

public class Main {
    public static void main(String[] args) {

        List<Employee> employees = List.of(
                new Employee("John", "IT", 15000),
                new Employee("Amit", "IT", 25000),
                new Employee("Rohit", "HR", 18000),
                new Employee("Ronit", "Accounts", 12000)
        );

        Map<String, List<String>> result = employees.stream()
                .filter(e -> e.salary() < 20000)
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.mapping(Employee::name, Collectors.toList())
                ));

        System.out.println(result);
    }
}