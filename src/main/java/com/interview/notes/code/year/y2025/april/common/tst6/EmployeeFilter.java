package com.interview.notes.code.year.y2025.april.common.tst6;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Employee {
    private String name;
    private int id;
    private String department;
    private double salary;

    public Employee(String name, int id, String department, double salary) {
        this.name = name;
        this.id = id;
        this.department = department;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{name='" + name + "', id=" + id + 
               ", department='" + department + "', salary=" + salary + "}";
    }

    // Getters
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
}

public class EmployeeFilter {
    public static List<Employee> filterHREmployees(List<Employee> employees) {
        if (employees == null || employees.isEmpty()) {
            return new ArrayList<>();
        }

        return employees.stream()
                .filter(emp -> "HR".equalsIgnoreCase(emp.getDepartment()))
                .filter(emp -> emp.getSalary() > 50000)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        // Test cases
        List<Employee> employees = new ArrayList<>();
        
        // Adding test data
        employees.add(new Employee("John", 1, "HR", 60000));
        employees.add(new Employee("Alice", 2, "HR", 45000));
        employees.add(new Employee("Bob", 3, "IT", 55000));
        employees.add(new Employee("Carol", 4, "HR", 75000));
        employees.add(new Employee("David", 5, "Finance", 65000));

        // Test Case 1: Normal case
        System.out.println("Test Case 1: Normal filtering");
        List<Employee> filtered = filterHREmployees(employees);
        boolean passed = filtered.size() == 2; // Should get 2 HR employees > 50k
        System.out.println("Filtered Employees: ");
        filtered.forEach(System.out::println);
        System.out.println("Status: " + (passed ? "PASS" : "FAIL"));
        System.out.println("-------------------");

        // Test Case 2: Empty list
        System.out.println("Test Case 2: Empty list");
        List<Employee> emptyList = new ArrayList<>();
        List<Employee> emptyResult = filterHREmployees(emptyList);
        System.out.println("Status: " + (emptyResult.isEmpty() ? "PASS" : "FAIL"));
        System.out.println("-------------------");

        // Test Case 3: Null input
        System.out.println("Test Case 3: Null input");
        List<Employee> nullResult = filterHREmployees(null);
        System.out.println("Status: " + (nullResult.isEmpty() ? "PASS" : "FAIL"));
        System.out.println("-------------------");

        // Test Case 4: Large dataset
        System.out.println("Test Case 4: Large dataset (1000 employees)");
        List<Employee> largeList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            largeList.add(new Employee(
                "Emp" + i, 
                i, 
                i % 3 == 0 ? "HR" : "Other", 
                40000 + (i * 100)
            ));
        }
        List<Employee> largeResult = filterHREmployees(largeList);
        System.out.println("Number of filtered employees: " + largeResult.size());
        System.out.println("First few results:");
        largeResult.stream().limit(3).forEach(System.out::println);
        System.out.println("Status: PASS");
    }
}
