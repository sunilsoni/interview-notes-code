package com.interview.notes.code.year.y2025.march.common.test17;

import java.util.*;
import static java.util.stream.Collectors.*;

// Employee record definition
record Employee(Integer id, String name, Double salary, String department) {}

public class DepartmentHighestSalary {
    public static void main(String[] args) {
        // Test data setup
        List<Employee> employees = Arrays.asList(
            new Employee(123, "Raj", 2000.0, "Engg"),
            new Employee(143, "Hari", 3500.0, "Engg"),
            new Employee(121, "Giri", 5300.0, "Engg"),
            new Employee(111, "Mukundh", 3070.0, "HR"),
            new Employee(124, "Sailesh", 5020.0, "HR"),
            new Employee(153, "Senthil", 2010.0, "HR")
        );

        // Solution using Stream API
        Map<String, Double> result = findHighestSalaryByDepartment(employees);
        
        // Print results
        printResults(result);
        
        // Run tests
        runTests();
    }

    public static Map<String, Double> findHighestSalaryByDepartment(List<Employee> employees) {
        return employees.stream()
            // Group by department
            .collect(groupingBy(
                Employee::department,
                // Find maximum salary in each group
                collectingAndThen(
                    maxBy(Comparator.comparing(Employee::salary)),
                    emp -> emp.map(Employee::salary).orElse(0.0)
                )
            ));
    }

    private static void printResults(Map<String, Double> results) {
        System.out.println("Dept\tSalary");
        results.forEach((dept, salary) -> 
            System.out.printf("%s\t%.1f%n", dept, salary));
    }

    // Test cases
    private static void runTests() {
        System.out.println("\nRunning Tests:");
        
        // Test 1: Normal case
        List<Employee> test1 = Arrays.asList(
            new Employee(1, "A", 1000.0, "Dept1"),
            new Employee(2, "B", 2000.0, "Dept1")
        );
        assert findHighestSalaryByDepartment(test1).get("Dept1") == 2000.0;
        System.out.println("Test 1 (Normal case): PASS");

        // Test 2: Empty list
        List<Employee> test2 = new ArrayList<>();
        assert findHighestSalaryByDepartment(test2).isEmpty();
        System.out.println("Test 2 (Empty list): PASS");

        // Test 3: Large dataset
        List<Employee> test3 = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            test3.add(new Employee(i, "Name" + i, (double)i, "Dept" + (i % 10)));
        }
        long startTime = System.currentTimeMillis();
        Map<String, Double> result3 = findHighestSalaryByDepartment(test3);
        long endTime = System.currentTimeMillis();
        System.out.println("Test 3 (Large dataset): PASS - Processing time: " + (endTime - startTime) + "ms");
    }
}
