package com.interview.notes.code.year.y2025.august.common.test13;

import java.util.*;
import java.util.stream.Collectors;

public class EmployeeSortDemo {
    public static void main(String[] args) {
        // Create sample employees
        List<Employee> employees = Arrays.asList(
            new Employee(1, "Ravi", 50000),
            new Employee(2, "Amit", 70000),
            new Employee(3, "Zara", 70000),
            new Employee(4, "Kiran", 40000),
            new Employee(5, "Bhavana", 70000)
        );

        // Sort: salary (desc) then name (asc)
        List<Employee> sortedList = employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary) // sort by salary
                        .reversed()                               // descending salary
                        .thenComparing(Employee::getName))        // then by name asc
                .collect(Collectors.toList());

        // Print sorted employees
        sortedList.forEach(System.out::println);
    }
}