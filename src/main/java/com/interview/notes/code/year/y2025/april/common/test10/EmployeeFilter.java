package com.interview.notes.code.year.y2025.april.common.test10;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Employee {
    private final String name;
    private final int id;
    private final String department;
    private final double salary;

    public Employee(String name, int id, String department, double salary) {
        this.name = name;
        this.id = id;
        this.department = department;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{name='" + name + "', id=" + id +
                ", department='" + department + "', salary=" + salary + "}";
    }

    // Getters
    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }
}

public class EmployeeFilter {
    public static List<Employee> filterHREmployees(List<Employee> employees) {
        if (employees == null || employees.isEmpty()) {
            return new ArrayList<>();
        }

        return employees.stream()
                .filter(emp -> "HR".equalsIgnoreCase(emp.getDepartment()))
                .filter(emp -> emp.getSalary() > 50000)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        // Create test data
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("John", 1, "HR", 60000));
        employees.add(new Employee("Alice", 2, "HR", 45000));
        employees.add(new Employee("Bob", 3, "IT", 55000));
        employees.add(new Employee("Carol", 4, "HR", 75000));
        employees.add(new Employee("David", 5, "Finance", 65000));

        // Print original list
        System.out.println("Original Employee List:");
        employees.forEach(System.out::println);
        System.out.println("\n-------------------\n");

        // Filter and print results
        System.out.println("HR Employees with salary > 50000:");
        List<Employee> filteredEmployees = filterHREmployees(employees);
        filteredEmployees.forEach(System.out::println);

        // Verify results
        boolean passed = filteredEmployees.size() == 2; // Should get 2 HR employees > 50k
        System.out.println("\nTest Status: " + (passed ? "PASS" : "FAIL"));
        System.out.println("Expected count: 2");
        System.out.println("Actual count: " + filteredEmployees.size());
    }
}
