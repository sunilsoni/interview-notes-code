package com.interview.notes.code.year.y2025.feb25.common.test6;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Employee {
    private int id;
    private String name;
    private double salary;
    private String department;

    public Employee(int id, String name, double salary, String department) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", department='" + department + '\'' +
                '}';
    }
}

public class EmployeeOperations {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee(1, "Alice", 60000, "IT"),
            new Employee(2, "Bob", 45000, "HR"),
            new Employee(3, "Charlie", 80000, "IT"),
            new Employee(4, "David", 55000, "Finance"),
            new Employee(5, "Eve", 70000, "HR")
        );

        // Print original list
        System.out.println("Original Employee List:");
        employees.forEach(System.out::println);

        // Filter employees with salary >= 50000
        List<Employee> highPaidEmployees = employees.stream()
            .filter(emp -> emp.getSalary() >= 50000)
            .collect(Collectors.toList());

        System.out.println("\nEmployees with salary >= 50000:");
        highPaidEmployees.forEach(System.out::println);

        // Increase IT department salaries by 10%
        List<Employee> updatedEmployees = employees.stream()
            .map(emp -> {
                if (emp.getDepartment().equals("IT")) {
                    emp.setSalary(emp.getSalary() * 1.1);
                }
                return emp;
            })
            .collect(Collectors.toList());

        System.out.println("\nEmployees after IT department salary increase:");
        updatedEmployees.forEach(System.out::println);
    }
}
