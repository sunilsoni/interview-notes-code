package com.interview.notes.code.year.y2025.september.assesment.test1;

import java.util.*;
import java.util.stream.Collectors;

public class EmployeeSalaryCalculator {

    // Method to calculate total salary of a manager and their team
    public static int calculateTeamSalary(List<Employee> employees, int managerId) {
        // Step 1: Build manager -> employees mapping
        Map<Integer, List<Employee>> managerMap = employees.stream()
                .collect(Collectors.groupingBy(Employee::managerId));

        // Step 2: Find manager object
        Optional<Employee> managerOpt = employees.stream()
                .filter(e -> e.id() == managerId)
                .findFirst();

        if (!managerOpt.isPresent()) {
            return 0; // Manager not found
        }

        // Step 3: Start DFS from manager
        return dfs(managerOpt.get(), managerMap);
    }

    // DFS to traverse hierarchy and sum salaries
    private static int dfs(Employee manager, Map<Integer, List<Employee>> managerMap) {
        int total = manager.salary();

        // Get direct reports
        List<Employee> subordinates = managerMap.getOrDefault(manager.id(), Collections.emptyList());

        for (Employee emp : subordinates) {
            total += dfs(emp, managerMap); // Recursive call
        }

        return total;
    }

    // Main method for testing
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee(1, 0, 100),  // CEO
                new Employee(2, 1, 80),   // Reports to 1
                new Employee(3, 1, 70),   // Reports to 1
                new Employee(4, 2, 50),   // Reports to 2
                new Employee(5, 2, 40),   // Reports to 2
                new Employee(6, 3, 60)    // Reports to 3
        );

        // Test cases
        System.out.println("Manager 1 total salary = " + calculateTeamSalary(employees, 1)); // Expected: 400
        System.out.println("Manager 2 total salary = " + calculateTeamSalary(employees, 2)); // Expected: 170
        System.out.println("Manager 3 total salary = " + calculateTeamSalary(employees, 3)); // Expected: 130
        System.out.println("Manager 4 total salary = " + calculateTeamSalary(employees, 4)); // Expected: 50
        System.out.println("Unknown manager = " + calculateTeamSalary(employees, 99));       // Expected: 0
    }

    // Record class to hold employee details
    record Employee(int id, int managerId, int salary) {
    }
}