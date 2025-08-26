package com.interview.notes.code.year.y2025.august.oracle.test2;

import java.util.*;
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

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public double getSalary() { return salary; }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }

    public static void main(String[] args) {
        // Step 1: Create employees
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Ravi", 50000),
                new Employee(2, "Sunil", 70000),
                new Employee(3, "Kranthi", 65000),
                new Employee(4, "Bhavana", 90000),
                new Employee(5, "Feroz", 75000),
                new Employee(6, "Ali", 75000),     // Duplicate 2nd highest
                new Employee(7, "Maha", 70000)     // Another duplicate lower
        );

        // Step 2: Extract unique salaries in descending order
        List<Double> uniqueSalaries = employees.stream()
                .map(Employee::getSalary)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        // Step 3: Find 2nd highest salary
        if (uniqueSalaries.size() < 2) {
            System.out.println("Not enough unique salaries to find 2nd highest.");
            return;
        }
        double secondHighestSalary = uniqueSalaries.get(1);

        // Step 4: Get all employees having 2nd highest salary
        List<Employee> secondHighestEmployees = employees.stream()
                .filter(e -> e.getSalary() == secondHighestSalary)
                .collect(Collectors.toList());

        // Step 5: Print result
        System.out.println("Second highest salary = " + secondHighestSalary);
        System.out.println("Employees with second highest salary:");
        secondHighestEmployees.forEach(System.out::println);
    }
}