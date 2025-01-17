package com.interview.notes.code.year.y2025.jan24.test11;

import java.math.BigDecimal;
import java.util.*;

class Employee {
    private int id;
    private String name;
    private BigDecimal salary;

    // Constructor
    public Employee(int id, String name, BigDecimal salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getSalary() {
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

public class Solution {
    public static void main(String[] args) {
        // Create test data
        List<Employee> employees = Arrays.asList(
                new Employee(1, "John", new BigDecimal("70000")),
                new Employee(2, "Alice", new BigDecimal("80000")),
                new Employee(3, "Bob", new BigDecimal("90000")),
                new Employee(4, "Carol", new BigDecimal("75000")),
                new Employee(5, "David", new BigDecimal("85000"))
        );

        // Method 1: Using skip and findFirst
        Optional<Employee> thirdHighestSalary1 = employees.stream()
                .sorted((e1, e2) -> e2.getSalary().compareTo(e1.getSalary()))
                .skip(2)
                .findFirst();

        // Method 2: Using limit and skip
        Optional<Employee> thirdHighestSalary2 = employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .skip(2)
                .limit(1)
                .findFirst();

        // Method 3: Using distinct to handle duplicate salaries
        Optional<Employee> thirdHighestSalary3 = employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .map(Employee::getSalary)
                .distinct()
                .skip(2)
                .findFirst()
                .flatMap(salary -> employees.stream()
                        .filter(e -> e.getSalary().equals(salary))
                        .findFirst());

        // Print results
        System.out.println("Method 1 Result:");
        thirdHighestSalary1.ifPresent(System.out::println);

        System.out.println("\nMethod 2 Result:");
        thirdHighestSalary2.ifPresent(System.out::println);

        System.out.println("\nMethod 3 Result (handles duplicates):");
        thirdHighestSalary3.ifPresent(System.out::println);

        // Test with empty list
        System.out.println("\nTesting with empty list:");
        List<Employee> emptyList = new ArrayList<>();
        Optional<Employee> emptyResult = findThirdHighestSalary(emptyList);
        System.out.println("Empty list result: " + emptyResult.orElse(null));

        // Test with list having less than 3 employees
        System.out.println("\nTesting with small list:");
        List<Employee> smallList = Arrays.asList(
                new Employee(1, "John", new BigDecimal("70000")),
                new Employee(2, "Alice", new BigDecimal("80000"))
        );
        Optional<Employee> smallListResult = findThirdHighestSalary(smallList);
        System.out.println("Small list result: " + smallListResult.orElse(null));
    }

    // Recommended method with proper error handling
    public static Optional<Employee> findThirdHighestSalary(List<Employee> employees) {
        if (employees == null || employees.size() < 3) {
            return Optional.empty();
        }

        return employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .map(Employee::getSalary)
                .distinct()
                .skip(2)
                .findFirst()
                .flatMap(salary -> employees.stream()
                        .filter(e -> e.getSalary().equals(salary))
                        .findFirst());
    }
}
