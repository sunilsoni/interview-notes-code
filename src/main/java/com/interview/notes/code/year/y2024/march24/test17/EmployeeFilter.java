package com.interview.notes.code.year.y2024.march24.test17;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeFilter {
    public static void main(String[] args) {
        // Sample HashMap with Employee ID as key and Employee object as value
        Map<Integer, Employee> employeeMap = new HashMap<>();
        // Populate employeeMap with some sample data
        // Employee ID: 1001, Name: Alice
        // Employee ID: 1002, Name: Bob
        // Employee ID: 1003, Name: Charlie
        // Employee ID: 9999, Name: Andrew
        employeeMap.put(1001, new Employee(1001, "Alice"));
        employeeMap.put(1002, new Employee(1002, "Bob"));
        employeeMap.put(1003, new Employee(1003, "Charlie"));
        employeeMap.put(9999, new Employee(9999, "Andrew"));

        // Filter employees with IDs starting with 1002-9999
        Map<Integer, Employee> filteredEmployeesByIdRange = employeeMap.entrySet().stream()
                .filter(entry -> entry.getKey() >= 1002 && entry.getKey() <= 9999)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // Print filtered employees by ID range
        System.out.println("Employees with IDs starting from 1002 to 9999:");
        filteredEmployeesByIdRange.forEach((id, employee) -> System.out.println(id + ": " + employee.getName()));

        // Filter employees whose names start with 'A'
        Map<Integer, Employee> filteredEmployeesByNameStartsWithA = employeeMap.entrySet().stream()
                .filter(entry -> entry.getValue().getName().startsWith("A"))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // Print filtered employees by name starting with 'A'
        System.out.println("\nEmployees whose names start with 'A':");
        filteredEmployeesByNameStartsWithA.forEach((id, employee) -> System.out.println(id + ": " + employee.getName()));
    }

    static class Employee {
        private final int id;
        private final String name;

        public Employee(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}
