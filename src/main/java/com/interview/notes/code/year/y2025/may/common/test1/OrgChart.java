package com.interview.notes.code.year.y2025.may.common.test1;

import java.util.*;
import java.util.stream.Collectors;

class Employee {
    int id;
    String name;
    Integer managerId;  // Using Integer to allow null

    public Employee(int id, String name, Integer managerId) {
        this.id = id;
        this.name = name;
        this.managerId = managerId;
    }
}

public class OrgChart {
    private static void printOrgChart(Employee manager, Map<Integer, List<Employee>> subordinates, String prefix) {
        // Print current manager
        System.out.println(prefix + manager.name);

        // Get and sort subordinates
        List<Employee> directReports = subordinates.getOrDefault(manager.id, new ArrayList<>());

        // Print each subordinate
        for (int i = 0; i < directReports.size(); i++) {
            printOrgChart(directReports.get(i), subordinates, prefix + "|__ ");
        }
    }

    public static void printOrganization(List<Employee> employees) {
        // Find CEO (employee with null managerId)
        Employee ceo = employees.stream()
                .filter(e -> e.managerId == null)
                .findFirst()
                .orElse(null);

        if (ceo == null) {
            System.out.println("No CEO found!");
            return;
        }

        // Create manager-subordinates mapping
        Map<Integer, List<Employee>> subordinates = employees.stream()
                .filter(e -> e.managerId != null)
                .collect(Collectors.groupingBy(
                        e -> e.managerId,
                        HashMap::new,
                        Collectors.toList()
                ));

        // Start printing from CEO
        printOrgChart(ceo, subordinates, "");
    }

    public static void main(String[] args) {
        // Test cases
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Eric", null),
                new Employee(2, "Jack", 1),
                new Employee(3, "Viral", 2),
                new Employee(4, "Michael", 2),
                new Employee(5, "Nitesh", 1),
                new Employee(6, "George", 4),
                new Employee(7, "Ryan", 2)
        );

        System.out.println("Test Case 1 - Normal Organization:");
        printOrganization(employees);

        // Edge case - Empty organization
        System.out.println("\nTest Case 2 - Empty Organization:");
        printOrganization(new ArrayList<>());

        // Edge case - Single employee
        System.out.println("\nTest Case 3 - Single Employee:");
        printOrganization(List.of(new Employee(1, "Solo", null)));

        // Large organization test (generating 1000 employees)
        List<Employee> largeOrg = new ArrayList<>();
        largeOrg.add(new Employee(1, "CEO", null));
        for (int i = 2; i <= 1000; i++) {
            largeOrg.add(new Employee(i, "Emp" + i, (i - 1) / 3 + 1));
        }

        System.out.println("\nTest Case 4 - Large Organization (1000 employees):");
        long startTime = System.currentTimeMillis();
        printOrganization(largeOrg);
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken for large org: " + (endTime - startTime) + "ms");
    }
}
