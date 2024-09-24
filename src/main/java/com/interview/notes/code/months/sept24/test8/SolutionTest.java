package com.interview.notes.code.months.sept24.test8;

import java.util.*;
import java.util.stream.Collectors;

class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;

    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                '}';
    }
}

public class SolutionTest {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Alice", "Engineering", 100000),
                new Employee(2, "Bob", "Engineering", 120000),
                new Employee(3, "Charlie", "HR", 70000),
                new Employee(4, "David", "HR", 60000),
                new Employee(5, "Eve", "Marketing", 90000),
                new Employee(6, "Frank", "Marketing", 85000),
                new Employee(7, "Grace", "Engineering", 110000)
        );

        double salaryThreshold = 100000;

        // 1. Group the employees by their department
        Map<String, List<Employee>> employeesByDepartment = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

        // 2. Within each department, find the highest-paid employee
        Map<String, Optional<Employee>> highestPaidByDepartment = employeesByDepartment.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream().max(Comparator.comparingDouble(Employee::getSalary))
                ));

        // 3. Filter out departments where the highest-paid employee earns less than the threshold
        Map<String, Optional<Employee>> filteredDepartments = highestPaidByDepartment.entrySet().stream()
                .filter(entry -> entry.getValue().isPresent() && entry.getValue().get().getSalary() >= salaryThreshold)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // 4. Collect the names of the highest-paid employees within each department into a list
        List<String> highestPaidNames = filteredDepartments.values().stream()
                .filter(Optional::isPresent)
                .map(optionalEmployee -> optionalEmployee.get().getName())
                .collect(Collectors.toList());

        // 5. Calculate the average salary of all employees
        double averageSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0);

        // 6. Find the department with the highest total salary
        String departmentWithHighestTotalSalary = employeesByDepartment.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream().mapToDouble(Employee::getSalary).sum()
                ))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("No Department");

        // Output results
        System.out.println("Highest paid employees' names within each department: " + highestPaidNames);
        System.out.println("Average salary of all employees: " + averageSalary);
        System.out.println("Department with the highest total salary: " + departmentWithHighestTotalSalary);
    }
}
