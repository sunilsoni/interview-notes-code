package com.interview.notes.code.year.y2024.jan24.test10;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Employee {
    private String name;
    private String department;
    private double salary;

    public Employee(String name, String department, double salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }
}

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        // Populate employees list
        employees.add(new Employee("John", "HR", 50000));
        employees.add(new Employee("Alice", "IT", 60000));
        employees.add(new Employee("Bob", "IT", 70000));
        employees.add(new Employee("Emma", "HR", 55000));
        employees.add(new Employee("Mike", "Finance", 75000));

        Map<String, Double> maxSalaryByDepartment = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.mapping(Employee::getSalary,
                                Collectors.collectingAndThen(Collectors.maxBy(Double::compare),
                                        optional -> optional.orElse(0.0)))));

        // Print department-wise highest salary
        maxSalaryByDepartment.forEach((department, salary) -> System.out.println("Department: " + department + ", Highest Salary: " + salary));
    }
}
