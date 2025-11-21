package com.interview.notes.code.year.y2025.august.oracle.test1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Employee {
    private final int id;
    private final String name;
    private final double salary;

    // Constructor to create Employee object
    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public static void main(String[] args) {
        // Step 1: Create a list of employees
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Ravi", 50000),
                new Employee(2, "Sunil", 70000),
                new Employee(3, "Kranthi", 65000),
                new Employee(4, "Bhavana", 90000),
                new Employee(5, "Feroz", 75000)
        );

        // Step 2: Find the employee with the 2nd highest salary
        Optional<Employee> secondHighest = employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed()) // Sort in descending order
                .skip(1) // Skip the highest salary
                .findFirst(); // Pick the next one (2nd highest)

        // Step 3: Print result
        if (secondHighest.isPresent()) {
            System.out.println("Second highest salary: " + secondHighest.get());
        } else {
            System.out.println("Not enough employees to find 2nd highest salary.");
        }
    }

    // Getters (needed for accessing fields in streams)
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
}