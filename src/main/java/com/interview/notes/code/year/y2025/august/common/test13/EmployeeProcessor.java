package com.interview.notes.code.year.y2025.august.common.test13;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Employee1 class to represent Employee1 data
 */
class Employee1 {
    private final String name;
    private final String department;
    private final double salary;

    // Constructor to initialize Employee1 object
    public Employee1(String name, String department, double salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    // Getter methods to access private fields
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
        return "Employee1{name='" + name + "', department='" + department + "', salary=" + salary + "}";
    }
}

public class EmployeeProcessor {

    /**
     * Main method to test the solution with various test cases
     */
    public static void main(String[] args) {
        System.out.println("=== Testing Employee1 Processor ===\n");

        // Test Case 1: Basic functionality test
        testCase1();

        // Test Case 2: No IT Employee1s
        testCase2();

        // Test Case 3: IT Employee1s but none earning > 60,000
        testCase3();

        // Test Case 4: Large dataset test
        testCase4();

        // Test Case 5: Mixed departments and salaries
        testCase5();

        System.out.println("\n=== All tests completed ===");
    }

    /**
     * Process Employee1s to find IT department Employee1s earning more than 60,000
     * Sorted by salary in descending order and returns only names
     */
    public static List<String> processEmployee1s(List<Employee1> Employee1s) {
        return Employee1s.stream()  // Convert list to stream for processing
                .filter(emp -> "IT".equals(emp.getDepartment()))  // Filter only IT department Employee1s
                .filter(emp -> emp.getSalary() > 60000)  // Filter Employee1s earning more than 60,000
                .sorted((e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary()))  // Sort by salary descending
                .map(Employee1::getName)  // Extract only the name from each Employee1
                .collect(Collectors.toList());  // Collect results into a List<String>
    }

    /**
     * Test Case 1: Basic functionality with expected IT Employee1s
     */
    private static void testCase1() {
        List<Employee1> Employee1s = Arrays.asList(
                new Employee1("Alice", "IT", 75000),
                new Employee1("Bob", "HR", 50000),
                new Employee1("Charlie", "IT", 85000),
                new Employee1("Diana", "IT", 65000),
                new Employee1("Eve", "Finance", 70000)
        );

        List<String> expected = Arrays.asList("Charlie", "Alice", "Diana");
        List<String> result = processEmployee1s(Employee1s);

        boolean passed = expected.equals(result);
        System.out.println("Test Case 1 - Basic functionality: " + (passed ? "PASS" : "FAIL"));
        if (!passed) {
            System.out.println("Expected: " + expected);
            System.out.println("Got: " + result);
        }
    }

    /**
     * Test Case 2: No IT department Employee1s
     */
    private static void testCase2() {
        List<Employee1> Employee1s = Arrays.asList(
                new Employee1("Alice", "HR", 75000),
                new Employee1("Bob", "Finance", 85000),
                new Employee1("Charlie", "Marketing", 65000)
        );

        List<String> expected = Collections.emptyList();
        List<String> result = processEmployee1s(Employee1s);

        boolean passed = expected.equals(result);
        System.out.println("Test Case 2 - No IT Employee1s: " + (passed ? "PASS" : "FAIL"));
        if (!passed) {
            System.out.println("Expected: " + expected);
            System.out.println("Got: " + result);
        }
    }

    /**
     * Test Case 3: IT Employee1s but none earning > 60,000
     */
    private static void testCase3() {
        List<Employee1> Employee1s = Arrays.asList(
                new Employee1("Alice", "IT", 55000),
                new Employee1("Bob", "IT", 60000),
                new Employee1("Charlie", "IT", 45000)
        );

        List<String> expected = Collections.emptyList();
        List<String> result = processEmployee1s(Employee1s);

        boolean passed = expected.equals(result);
        System.out.println("Test Case 3 - IT Employee1s <= 60,000: " + (passed ? "PASS" : "FAIL"));
        if (!passed) {
            System.out.println("Expected: " + expected);
            System.out.println("Got: " + result);
        }
    }

    /**
     * Test Case 4: Large dataset test (10,000 Employee1s)
     */
    private static void testCase4() {
        List<Employee1> Employee1s = new ArrayList<>();
        Random random = new Random(42); // Fixed seed for reproducible results

        // Generate 10,000 Employee1s with random data
        for (int i = 0; i < 10000; i++) {
            String name = "Emp" + i;
            String[] depts = {"IT", "HR", "Finance", "Marketing"};
            String department = depts[random.nextInt(depts.length)];
            double salary = 30000 + random.nextDouble() * 70000; // Salary between 30k-100k

            Employee1s.add(new Employee1(name, department, salary));
        }

        // Add specific test Employee1s to ensure we have some IT Employee1s > 60,000
        Employee1s.add(new Employee1("HighPaidIT1", "IT", 95000));
        Employee1s.add(new Employee1("HighPaidIT2", "IT", 85000));
        Employee1s.add(new Employee1("HighPaidIT3", "IT", 75000));

        List<String> result = processEmployee1s(Employee1s);

        // Verify that results are sorted in descending order by salary
        boolean sortedCorrectly = true;
        if (result.size() > 1) {
            // Check if results contain our test Employee1s and are in correct order
            sortedCorrectly = result.get(0).equals("HighPaidIT1") &&
                    result.get(1).equals("HighPaidIT2") &&
                    result.get(2).equals("HighPaidIT3");
        }

        boolean passed = !result.isEmpty() && sortedCorrectly;
        System.out.println("Test Case 4 - Large dataset: " + (passed ? "PASS" : "FAIL"));
        System.out.println("   Found " + result.size() + " IT Employee1s earning > 60,000");
    }

    /**
     * Test Case 5: Mixed departments with various salary ranges
     */
    private static void testCase5() {
        List<Employee1> Employee1s = Arrays.asList(
                new Employee1("Alice", "IT", 55000),  // Should be excluded (<= 60,000)
                new Employee1("Bob", "IT", 65000),    // Should be included
                new Employee1("Charlie", "HR", 75000), // Wrong department
                new Employee1("Diana", "IT", 95000),   // Should be included (highest salary)
                new Employee1("Eve", "IT", 45000),     // Should be excluded (<= 60,000)
                new Employee1("Frank", "Finance", 85000), // Wrong department
                new Employee1("Grace", "IT", 75000)    // Should be included
        );

        List<String> expected = Arrays.asList("Diana", "Grace", "Bob");
        List<String> result = processEmployee1s(Employee1s);

        boolean passed = expected.equals(result);
        System.out.println("Test Case 5 - Mixed departments: " + (passed ? "PASS" : "FAIL"));
        if (!passed) {
            System.out.println("Expected: " + expected);
            System.out.println("Got: " + result);
        }
    }
}
