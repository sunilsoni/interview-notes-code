package com.interview.notes.code.year.y2025.august.common.test14;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// Employee class
class Employee {
    private final String name;
    private final String department;
    private final double salary;

    // Constructor
    public Employee(String name, String department, double salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return name + " (" + department + ", " + salary + ")";
    }
}

public class EmployeeFilter {
    public static void main(String[] args) {
        // Step 1: Sample employee list
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", "IT", 75000),
                new Employee("Bob", "HR", 50000),
                new Employee("Charlie", "IT", 65000),
                new Employee("David", "Finance", 80000),
                new Employee("Eve", "IT", 55000),
                new Employee("Frank", "IT", 90000)
        );

        // Step 2: Stream pipeline
        List<String> result = employees.stream()
                .filter(emp -> emp.getDepartment().equalsIgnoreCase("IT"))   // only IT department
                .filter(emp -> emp.getSalary() > 60000)                      // salary > 60000
                .sorted(Comparator.comparingDouble(Employee::getSalary)      // sort by salary
                        .reversed())                                        // descending
                .map(Employee::getName)                                     // collect only names
                .collect(Collectors.toList());

        // Step 3: Print result
        System.out.println("IT Employees with salary > 60000 (sorted by salary desc):");
        System.out.println(result);
    }
}