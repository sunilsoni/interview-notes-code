package com.interview.notes.code.year.y2025.jan.test9;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

class Employee {
    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{name='" + name + "', salary=" + salary + "}";
    }
}

public class HighestSalaryExample {
    public static void main(String[] args) {
        // Create a list of employees
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("John", 50000));
        employees.add(new Employee("Alice", 65000));
        employees.add(new Employee("Bob", 45000));
        employees.add(new Employee("Sarah", 70000));
        employees.add(new Employee("Mike", 55000));

        // Method 1: Find employee with highest salary using max()
        Optional<Employee> highestPaidEmployee = employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary));

        highestPaidEmployee.ifPresent(employee ->
                System.out.println("Highest paid employee: " + employee));

        // Method 2: Sort by salary in descending order and get first
        Optional<Employee> highestPaidEmployee2 = employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .findFirst();

        // Method 3: Get all employees sorted by salary (descending)
        System.out.println("\nAll employees sorted by salary (descending):");
        employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .forEach(System.out::println);

        // Method 4: Get top 3 highest paid employees
        System.out.println("\nTop 3 highest paid employees:");
        employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .limit(3)
                .forEach(System.out::println);

        // Method 5: Get name of highest paid employee
        String highestPaidName = employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary))
                .map(Employee::getName)
                .orElse("No employees");
        System.out.println("\nHighest paid employee name: " + highestPaidName);

        // Method 6: Get highest salary
        double highestSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .max()
                .orElse(0.0);
        System.out.println("Highest salary: " + highestSalary);
    }
}
