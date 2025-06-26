package com.interview.notes.code.year.y2025.June.common.test7;

import java.util.*;
import java.util.stream.*;

class Employee {
    private String name;
    private double salary;
    private int age;

    // Constructor
    public Employee(String name, double salary, int age) {
        this.name = name;
        this.salary = salary;
        this.age = age;
    }

    // Getters
    public String getName() { return name; }
    public double getSalary() { return salary; }
    public int getAge() { return age; }
}

public class EmployeeProcessor {
    
    /**
     * Processes employees to get filtered names in uppercase
     * @param employees Input list of employees
     * @return List of uppercase names meeting criteria
     */
    public static List<String> getFilteredEmployeeNames(List<Employee> employees) {
        // Handle null input case
        if (employees == null) return new ArrayList<>();
        
        return employees.stream()  // Convert list to stream for processing
            .filter(emp -> emp.getSalary() > 50000)  // Filter salary condition
            .filter(emp -> emp.getAge() > 25)        // Filter age condition
            .map(Employee::getName)                   // Extract name from Employee
            .map(String::toUpperCase)                // Convert name to uppercase
            .collect(Collectors.toList());           // Collect results to List
    }

    public static void main(String[] args) {
        // Test Case 1: Normal case
        List<Employee> testCase1 = Arrays.asList(
            new Employee("John", 60000, 30),
            new Employee("Alice", 45000, 27),
            new Employee("Bob", 55000, 23),
            new Employee("Carol", 70000, 35)
        );
        
        // Test Case 2: Empty list
        List<Employee> testCase2 = new ArrayList<>();
        
        // Test Case 3: Large dataset
        List<Employee> testCase3 = new ArrayList<>();
        for(int i = 0; i < 100000; i++) {
            testCase3.add(new Employee("Emp" + i, 
                                     40000 + (i * 1000), 
                                     20 + (i % 20)));
        }
        
        // Test Case 4: Null input
        List<Employee> testCase4 = null;

        // Run tests
        runTest("Test 1 - Normal case", testCase1);
        runTest("Test 2 - Empty list", testCase2);
        runTest("Test 3 - Large dataset", testCase3);
        runTest("Test 4 - Null input", testCase4);
    }

    private static void runTest(String testName, List<Employee> input) {
        System.out.println("\nExecuting " + testName);
        try {
            long startTime = System.currentTimeMillis();
            List<String> result = getFilteredEmployeeNames(input);
            long endTime = System.currentTimeMillis();
            
            System.out.println("Results: " + result);
            System.out.println("Execution time: " + (endTime - startTime) + "ms");
            System.out.println("Status: PASS");
        } catch (Exception e) {
            System.out.println("Status: FAIL");
            System.out.println("Error: " + e.getMessage());
        }
    }
}
