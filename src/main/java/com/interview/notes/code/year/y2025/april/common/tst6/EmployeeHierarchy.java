package com.interview.notes.code.year.y2025.april.common.tst6;

import java.util.HashSet;
import java.util.Set;

public class EmployeeHierarchy {
    // Method to find closest common manager
    public static Employee whoIsOurBoss(Employee emp1, Employee emp2) {
        // Handle edge cases
        if (emp1 == null || emp2 == null) return null;
        if (emp1 == emp2) return emp1.manager;

        // Get path from emp1 to CEO
        Set<Employee> path1 = new HashSet<>();
        Employee current = emp1;
        while (current != null) {
            path1.add(current);
            current = current.manager;
        }

        // Find first common manager in emp2's path to CEO
        current = emp2;
        while (current != null) {
            if (path1.contains(current)) {
                return current;
            }
            current = current.manager;
        }

        return null;
    }

    // Main method for testing
    public static void main(String[] args) {
        // Create test hierarchy
        Employee ceo = new Employee(1, "CEO");
        Employee vp1 = new Employee(2, "VP1");
        Employee vp2 = new Employee(3, "VP2");
        Employee dir1 = new Employee(4, "Director1");
        Employee dir2 = new Employee(5, "Director2");
        Employee emp1 = new Employee(6, "Employee1");
        Employee emp2 = new Employee(7, "Employee2");

        // Set up hierarchy
        vp1.manager = ceo;
        vp2.manager = ceo;
        dir1.manager = vp1;
        dir2.manager = vp2;
        emp1.manager = dir1;
        emp2.manager = dir2;

        // Test cases
        System.out.println("Test Case 1: Same level employees");
        Employee result1 = whoIsOurBoss(emp1, emp2);
        System.out.println("Expected: CEO, Got: " + result1.name);

        System.out.println("\nTest Case 2: Same department");
        Employee emp3 = new Employee(8, "Employee3");
        emp3.manager = dir1;
        Employee result2 = whoIsOurBoss(emp1, emp3);
        System.out.println("Expected: Director1, Got: " + result2.name);

        System.out.println("\nTest Case 3: Edge case - null input");
        Employee result3 = whoIsOurBoss(null, emp1);
        System.out.println("Expected: null, Got: " + result3);
    }

    // Employee class definition
    static class Employee {
        long id;
        String name;
        Employee manager;
        Set<Employee> reports;

        // Constructor
        public Employee(long id, String name) {
            this.id = id;
            this.name = name;
            this.reports = new HashSet<>();
        }
    }
}
