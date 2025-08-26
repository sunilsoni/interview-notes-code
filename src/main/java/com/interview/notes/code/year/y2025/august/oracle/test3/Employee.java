package com.interview.notes.code.year.y2025.august.oracle.test3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Employee {
    private int id;
    private String name;
    private double salary;

    // Constructor
    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
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

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }

    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee(1, "John", 50000),
            new Employee(2, "Alice", 60000),
            new Employee(3, "Bob", 60000),
            new Employee(4, "Charlie", 45000),
            new Employee(5, "David", 70000)
        );

        // Find the second highest salary
        double secondHighestSalary = employees.stream()
                .map(Employee::getSalary)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .orElse(0.0);

        // Find all employees with the second highest salary
        List<Employee> employeesWithSecondHighestSalary = employees.stream()
                .filter(emp -> emp.getSalary() == secondHighestSalary)
                .collect(Collectors.toList());

        System.out.println("Second highest salary: " + secondHighestSalary);
        System.out.println("Employees with second highest salary:");
        employeesWithSecondHighestSalary.forEach(System.out::println);
    }
}
