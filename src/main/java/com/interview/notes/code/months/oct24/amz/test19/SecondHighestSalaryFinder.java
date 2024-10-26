package com.interview.notes.code.months.oct24.amz.test19;

import java.util.*;
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

    public double getSalary() {
        return salary;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return String.format("Employee{id=%d, name='%s', salary=%.2f, department='%s'}", id, name, salary, department);
    }
}

public class SecondHighestSalaryFinder {

    public static Map<String, Optional<Double>> findSecondHighestSalary(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.collectingAndThen(
                                Collectors.mapping(Employee::getSalary, Collectors.toSet()),
                                salaries -> salaries.stream()
                                        .sorted(Comparator.reverseOrder())
                                        .skip(1)
                                        .findFirst()
                        )
                ));
    }

    public static void main(String[] args) {
        // Sample test cases
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Alice", 90000, "IT"),
                new Employee(2, "Bob", 85000, "IT"),
                new Employee(3, "Charlie", 80000, "IT"),
                new Employee(4, "David", 90000, "HR"),
                new Employee(5, "Eva", 87000, "HR"),
                new Employee(6, "Frank", 87000, "HR"),
                new Employee(7, "George", 95000, "Finance"),
                new Employee(8, "Hannah", 75000, "Finance"),
                new Employee(9, "Ivy", 50000, "Finance")
        );

        // Find second highest salary from each department
        Map<String, Optional<Double>> result = findSecondHighestSalary(employees);

        // Output results
        result.forEach((department, salary) ->
                System.out.println("Department: " + department + ", Second Highest Salary: " +
                        (salary.isPresent() ? salary.get() : "N/A")));

        // Testing with additional edge cases (e.g., fewer than 2 employees)
        testCases();
    }

    private static void testCases() {
        List<Employee> largeInputTest = new ArrayList<>();
        // Generate large input data
        for (int i = 1; i <= 100000; i++) {
            largeInputTest.add(new Employee(i, "Employee" + i, Math.random() * 100000, "IT"));
        }

        System.out.println("Running large data input test...");
        Map<String, Optional<Double>> largeResult = findSecondHighestSalary(largeInputTest);
        System.out.println("Large Input Test Completed.");

        // Fewer than 2 employees in a department
        List<Employee> edgeCase = Arrays.asList(
                new Employee(10, "John", 70000, "Legal")
        );

        Map<String, Optional<Double>> edgeCaseResult = findSecondHighestSalary(edgeCase);
        edgeCaseResult.forEach((department, salary) ->
                System.out.println("Department: " + department + ", Second Highest Salary: " +
                        (salary.isPresent() ? salary.get() : "N/A")));
    }
}
