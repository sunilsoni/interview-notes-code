package com.interview.notes.code.year.y2025.jan25.test1;

import java.util.Arrays;
import java.util.List;

public class EmployeeData {
    public static List<Employee> getEmployees() {
        return Arrays.asList(
                new Employee(1, "Alice", "IT", 6000),
                new Employee(2, "Bob", "IT", 7000),
                new Employee(3, "Charlie", "IT", 7000),
                new Employee(4, "David", "HR", 5000),
                new Employee(5, "Eve", "HR", 6000),
                new Employee(6, "Frank", "HR", 4000),
                new Employee(7, "Grace", "Finance", 8000),
                new Employee(8, "Heidi", "Finance", 8000),
                new Employee(9, "Ivan", "Finance", 7000),
                new Employee(10, "Judy", "IT", 8000)
        );
    }
}