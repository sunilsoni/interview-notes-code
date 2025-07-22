package com.interview.notes.code.year.y2025.july.amazon.test7;

import java.util.HashSet;
import java.util.Set;

class Employee {
    long id;                    // Unique identifier for employee
    String name;                // Employee name (not necessarily unique)
    Employee manager;           // Reference to direct manager (null for CEO)
    Set<Employee> reports;      // Direct reports (empty for non-managers)

    // Constructor for easy employee creation
    public Employee(long id, String name) {
        this.id = id;
        this.name = name;
        this.reports = new HashSet<>();
    }
}

class OrganizationHelper {

    // Finds the closest common manager for two employees
    public static Employee whoIsOurBoss(Employee emp1, Employee emp2) {
        // Handle edge cases
        if (emp1 == null || emp2 == null) return null;
        if (emp1 == emp2) return emp1.manager;

        // Get path from emp1 to CEO
        Set<Employee> path1 = getPathToCEO(emp1);

        // Get path from emp2 to CEO
        Set<Employee> path2 = getPathToCEO(emp2);

        // Find common managers using Java 8 streams
        return path1.stream()
                .filter(path2::contains)
                .findFirst()
                .orElse(null);
    }

    // Helper method to get path from employee to CEO
    private static Set<Employee> getPathToCEO(Employee emp) {
        Set<Employee> path = new HashSet<>();
        Employee current = emp.manager;

        // Travel up the hierarchy until we reach CEO
        while (current != null) {
            path.add(current);
            current = current.manager;
        }
        return path;
    }
}

// Main class for testing
public class OrganizationTest {
    public static void main(String[] args) {
        // Create test organization structure
        Employee ceo = new Employee(1, "CEO");
        Employee vp1 = new Employee(2, "VP1");
        Employee vp2 = new Employee(3, "VP2");
        Employee dir1 = new Employee(4, "Director1");
        Employee dir2 = new Employee(5, "Director2");
        Employee mgr1 = new Employee(6, "Manager1");
        Employee mgr2 = new Employee(7, "Manager2");

        // Set up hierarchy
        vp1.manager = ceo;
        vp2.manager = ceo;
        dir1.manager = vp1;
        dir2.manager = vp2;
        mgr1.manager = dir1;
        mgr2.manager = dir2;

        // Add reports
        ceo.reports.add(vp1);
        ceo.reports.add(vp2);
        vp1.reports.add(dir1);
        vp2.reports.add(dir2);
        dir1.reports.add(mgr1);
        dir2.reports.add(mgr2);

        // Test cases
        runTest("Test 1", mgr1, mgr2, ceo);
        runTest("Test 2", mgr1, dir1, dir1);
        runTest("Test 3", vp1, vp2, ceo);
        runTest("Test 4", mgr1, mgr1, dir1);
        runTest("Test 5", null, mgr1, null);
    }

    // Helper method to run tests
    private static void runTest(String testName, Employee emp1, Employee emp2, Employee expectedBoss) {
        Employee result = OrganizationHelper.whoIsOurBoss(emp1, emp2);
        boolean passed = result == expectedBoss;

        System.out.printf("%s: %s%n", testName, passed ? "PASS" : "FAIL");
        if (!passed) {
            System.out.printf("Expected: %s, Got: %s%n",
                    expectedBoss != null ? expectedBoss.name : "null",
                    result != null ? result.name : "null");
        }
    }
}
