package com.interview.notes.code.year.y2025.august.common.test1;

import java.util.*;
import java.util.stream.*;

public class EmployeeFilterSorted {

    // record to reduce boilerplate
    record Employee(int id, String name, double salary) {}

    public static void main(String[] args) {
        // Sample employees
        List<Employee> employees = Arrays.asList(
            new Employee(3, "Charlie", 75000),
            new Employee(1, "Alice", 45000),
            new Employee(5, "Eve", 90000),
            new Employee(2, "Bob", 60000),
            new Employee(4, "David", 30000)
        );

        // Filter salary > 50,000, sort by id, collect into LinkedHashMap
        Map<Integer, Employee> highSalaryEmployees = employees.stream()
                .filter(e -> e.salary() > 50000)
                .sorted(Comparator.comparing(Employee::id))
                .collect(Collectors.toMap(
                        Employee::id, 
                        e -> e,
                        (a, b) -> a, 
                        LinkedHashMap::new
                ));

        // Print sorted map
        highSalaryEmployees.forEach((id, emp) -> 
            System.out.println(id + " -> " + emp));
    }
}