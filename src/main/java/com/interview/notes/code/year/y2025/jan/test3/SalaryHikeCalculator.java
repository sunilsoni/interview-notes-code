package com.interview.notes.code.year.y2025.jan.test3;

import java.util.List;
import java.util.stream.Collectors;

class Employee {
    private final String name;
    private double salary;
    private final int yearsOfExperience;

    // Constructor
    public Employee(String name, double salary, int yearsOfExperience) {
        this.name = name;
        this.salary = salary;
        this.yearsOfExperience = yearsOfExperience;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", yearsOfExperience=" + yearsOfExperience +
                '}';
    }
}

public class SalaryHikeCalculator {
    private static final double HIKE_PERCENTAGE = 0.05; // 5% hike
    private static final int EXPERIENCE_THRESHOLD = 5;

    public static List<Employee> applySalaryHike(List<Employee> employees) {
        return employees.stream()
                .map(emp -> {
                    if (emp.getYearsOfExperience() >= EXPERIENCE_THRESHOLD) {
                        // Create new Employee object with updated salary
                        double newSalary = emp.getSalary() * (1 + HIKE_PERCENTAGE);
                        emp.setSalary(newSalary);
                    }
                    return emp;
                })
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        // Example usage
        List<Employee> employees = List.of(
                new Employee("John", 50000, 6),
                new Employee("Alice", 60000, 4),
                new Employee("Bob", 75000, 8),
                new Employee("Sarah", 45000, 3)
        );

        System.out.println("Before salary hike:");
        employees.forEach(System.out::println);

        List<Employee> updatedEmployees = applySalaryHike(employees);

        System.out.println("\nAfter salary hike:");
        updatedEmployees.forEach(System.out::println);
    }
}
