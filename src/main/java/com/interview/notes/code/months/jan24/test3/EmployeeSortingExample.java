package com.interview.notes.code.months.jan24.test3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeSortingExample {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();

        // Populate the list of employees...

        // Sort by salary (ascending)
        List<Employee> sortedBySalary = employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary))
                .collect(Collectors.toList());

        // Sort by name (ascending) within the same salary group
        List<Employee> sortedByNameWithinSalaryGroup = sortedBySalary.stream()
                .sorted(Comparator.comparing(Employee::getName))
                .collect(Collectors.toList());

        // Sort by ID (ascending) within the same salary and name group
        List<Employee> sortedByIdWithinSalaryAndNameGroup = sortedByNameWithinSalaryGroup.stream()
                .sorted(Comparator.comparingInt(Employee::getId))
                .collect(Collectors.toList());

        // Print the sorted list
        sortedByIdWithinSalaryAndNameGroup.forEach(System.out::println);
    }
}
