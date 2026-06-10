package com.interview.notes.code.year.y2026.june.cognizant.test1;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

record Employee(String name, String department) {}

public class Main {
    public static void main(String[] args) {

        List<Employee> employees = List.of(
                new Employee("John", "IT"),
                new Employee("Amit", "IT"),
                new Employee("Rohit", "HR"),
                new Employee("Ronit", "Accounts")
        );

        Map<String, List<String>> result = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.mapping(Employee::name, Collectors.toList())
                ));

        System.out.println(result);
    }
}