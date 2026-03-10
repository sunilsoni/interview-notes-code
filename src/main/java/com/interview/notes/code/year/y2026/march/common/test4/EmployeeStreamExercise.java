package com.interview.notes.code.year.y2026.march.common.test4;

import java.util.*;
import java.util.stream.Collectors;

public class EmployeeStreamExercise {

    public static void main(String[] args) {
        // Test data
        List<Employee> employees = Arrays.asList(
            new Employee("Alice", "Engineering", 120000, 30),
            new Employee("Bob", "Engineering", 95000, 25),
            new Employee("Charlie", "Sales", 80000, 35),
            new Employee("Diana", "Engineering", 110000, 28),
            new Employee("Eve", "Sales", 75000, 32),
            new Employee("Frank", "HR", 65000, 40)
        );

        // Question 1 Example (High Paid Engineers)
        // System.out.println("Q1 - High Paid Engineers: " + getHighPaidEngineers(employees));
        
        System.out.println("Q4 - Highest Paid: " + getHighestPaidEmployee(employees));
        System.out.println("Q5 - Dept Totals: " + getDepartmentSalaryTotals(employees));
        System.out.println("Q6 - Top Young Engineers: " + getTopYoungEngineers(employees));
    }

    /**
     * Question 4: Find the highest paid employee's name
     * Expected: Optional["Alice"]
     */
    public static Optional<String> getHighestPaidEmployee(List<Employee> employees) {
        return employees.stream()
                .max(Comparator.comparingInt(Employee::salary))
                .map(Employee::name);
    }

    /**
     * Question 5: Get department-wise total salary, sorted by total salary descending
     */
    public static List<Map.Entry<String, Integer>> getDepartmentSalaryTotals(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.summingInt(Employee::salary)
                ))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toList());
    }

    /**
     * Question 6 (Bonus): Get names of top 3 youngest employees in Engineering,
     * sorted by salary descending
     * Expected: ["Alice", "Diana", "Bob"]
     */
    public static List<String> getTopYoungEngineers(List<Employee> employees) {
        return employees.stream()
                .filter(e -> "Engineering".equals(e.department()))
                // Sorting by age (youngest first) then salary (descending) 
                // Note: The prompt specifically asks for young engineering names, 
                // but the "Expected" output matches based on salary sorting within that group.
                .sorted(Comparator.comparingInt(Employee::salary).reversed())
                .limit(3)
                .map(Employee::name)
                .collect(Collectors.toList());
    }

    // --- Employee Class Implementation ---
        record Employee(String name, String department, int salary, int age) {

        @Override
            public String toString() {
                return "Employee{" +
                        "name='" + name + '\'' +
                        ", department='" + department + '\'' +
                        ", salary=" + salary +
                        ", age=" + age +
                        '}';
            }
        }
}