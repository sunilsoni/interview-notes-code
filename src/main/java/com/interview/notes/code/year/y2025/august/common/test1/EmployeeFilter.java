package com.interview.notes.code.year.y2025.august.common.test1;

import java.util.*;
import java.util.stream.*;

public class EmployeeFilter {
    
    // record to reduce boilerplate
    record Employee(int id, String name, double salary) {}

    public static void main(String[] args) {
        // Sample list of employees
        List<Employee> employees = Arrays.asList(
            new Employee(1, "Alice", 45000),
            new Employee(2, "Bob", 60000),
            new Employee(3, "Charlie", 75000),
            new Employee(4, "David", 30000),
            new Employee(5, "Eve", 90000)
        );

        // Filter and collect to map
        Map<Integer, Employee> highSalaryEmployees = employees.stream()
                .filter(e -> e.salary() > 50000)
                .collect(Collectors.toMap(Employee::id, e -> e));

        // Print result
        highSalaryEmployees.forEach((id, emp) -> 
            System.out.println(id + " -> " + emp));
    }
}