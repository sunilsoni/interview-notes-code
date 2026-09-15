package com.interview.notes.code.year.y2026.august.common.test6;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

record Employee(String department, Integer salary) {}

public class SalaryFinder {
    public static void main(String[] args) {
        List<Employee> employees = List.of(
            new Employee("IT", 100000), new Employee("IT", 90000), new Employee("IT", 80000),
            new Employee("HR", 70000), new Employee("HR", 60000),
            new Employee("Finance", 120000), new Employee("Finance", 120000), new Employee("Finance", 110000)
        );

        var secondHighestByDept = employees.stream()
            .collect(Collectors.groupingBy(
                Employee::department,
                Collectors.collectingAndThen(
                    Collectors.toList(),
                    list -> list.stream()
                        .map(Employee::salary)
                        .distinct() // Handles identical highest salaries
                        .sorted(Comparator.reverseOrder())
                        .skip(1) // Skips the highest
                        .findFirst() // Grabs the second highest
            )));

        System.out.println(secondHighestByDept);
        // Output: {Finance=Optional[110000], IT=Optional[90000], HR=Optional[60000]}
    }
}