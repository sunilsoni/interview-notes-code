package com.interview.notes.code.year.y2025.november.common.test3;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Employee {
    String name;
    String department;

    public Employee(String name, String department) {
        this.name = name;
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }
}

public class MaxDept {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee("Alice", "IT"),
            new Employee("Alice", "Fin"),
            new Employee("Bob", "IT")
        );

        String maxDept = employees.stream()
            .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()))
            .entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse("No department");

        System.out.println("Department with max employees: " + maxDept);
    }
}
