package com.interview.notes.code.year.y2026.september.CodeSignal.test5;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// Java 21 record generates constructor, getters, equals, hashCode, and toString in a single compact declaration
record Employee(String name, String department, double salary) {}

public class DepartmentSalaryAnalyzer {

    // Method to find the highest paid employee in every distinct department
    public static Map<String, Employee> getHighestPaidPerDept(List<Employee> employees) {
        // Check for null or empty input to prevent NullPointerExceptions early in execution
        if (employees == null || employees.isEmpty()) {
            // Return an immutable empty map to avoid unnecessary processing and null returns
            return Collections.emptyMap();
        }

        // Convert the employee list into a sequential stream for functional processing
        return employees.stream()
            // Collect stream elements into a Map using custom key, value, and collision rules
            .collect(Collectors.toMap(
                // Use the department name as the unique key in the resulting map
                Employee::department,
                // Map each stream element to itself as the initial value associated with the key
                emp -> emp,
                // Resolve collisions by comparing salaries and selecting the employee with the higher value
                BinaryOperator.maxBy(Comparator.comparingDouble(Employee::salary))
            ));
    }

    // Direct main method to execute test suite without external dependencies like JUnit
    static void main(String[] args) {
        // Track overall test suite execution state
        boolean allPassed = true;

        // --- Test 1: Standard Multiple Departments ---
        // Setup input data across distinct departments with varying salaries
        List<Employee> standardList = List.of(
            new Employee("Alice", "Engineering", 120000.0),
            new Employee("Bob", "Engineering", 145000.0),
            new Employee("Charlie", "HR", 85000.0),
            new Employee("Diana", "HR", 92000.0),
            new Employee("Evan", "Marketing", 105000.0)
        );
        // Execute the method under test
        Map<String, Employee> res1 = getHighestPaidPerDept(standardList);
        // Validate Engineering top earner is Bob and HR top earner is Diana
        boolean pass1 = res1.get("Engineering").salary() == 145000.0 
                     && res1.get("HR").salary() == 92000.0 
                     && res1.get("Marketing").salary() == 105000.0;
        // Print result for Test 1
        allPassed &= recordTestResult("Test 1: Standard Multiple Departments", pass1);

        // --- Test 2: Single Employee List ---
        // Setup list containing only one record
        List<Employee> singleList = List.of(new Employee("Solo", "Finance", 99000.0));
        // Execute analyzer on single element
        Map<String, Employee> res2 = getHighestPaidPerDept(singleList);
        // Validate the department exists and matches the lone employee
        boolean pass2 = res2.size() == 1 && res2.get("Finance").name().equals("Solo");
        // Print result for Test 2
        allPassed &= recordTestResult("Test 2: Single Employee", pass2);

        // --- Test 3: Empty and Null Input ---
        // Validate behavior against an empty list
        boolean pass3Empty = getHighestPaidPerDept(Collections.emptyList()).isEmpty();
        // Validate behavior against null input without crashing
        boolean pass3Null = getHighestPaidPerDept(null).isEmpty();
        // Print result for Test 3
        allPassed &= recordTestResult("Test 3: Empty and Null Input", pass3Empty && pass3Null);

        // --- Test 4: Equal Highest Salaries (Ties) ---
        // Setup employees with identical maximum salaries in the same department
        List<Employee> tieList = List.of(
            new Employee("DevA", "Tech", 100000.0),
            new Employee("DevB", "Tech", 100000.0)
        );
        // Execute analyzer
        Map<String, Employee> res4 = getHighestPaidPerDept(tieList);
        // Ensure one employee is selected and salary remains maximum
        boolean pass4 = res4.containsKey("Tech") && res4.get("Tech").salary() == 100000.0;
        // Print result for Test 4
        allPassed &= recordTestResult("Test 4: Salary Tie Handling", pass4);

        // --- Test 5: High Volume / Large Dataset Benchmark ---
        // Define volume size: 500,000 records across 50 departments
        int datasetSize = 500_000;
        // Generate mock data efficiently using IntStream
        List<Employee> largeList = IntStream.range(0, datasetSize)
            .mapToObj(i -> new Employee("Emp_" + i, "Dept_" + (i % 50), i % 10000))
            .toList();
        // Measure start time for performance tracking
        long startTime = System.currentTimeMillis();
        // Process large list through stream pipeline
        Map<String, Employee> res5 = getHighestPaidPerDept(largeList);
        // Measure completion duration
        long duration = System.currentTimeMillis() - startTime;
        // Validate all 50 departments are captured correctly
        boolean pass5 = res5.size() == 50;
        // Print result for Test 5 with runtime execution speed
        allPassed &= recordTestResult("Test 5: Large Dataset (500k records, " + duration + "ms)", pass5);

        // Final overall status verification output
        System.out.println("\n-------------------------------------------");
        System.out.println("Final Suite Verdict: " + (allPassed ? "ALL TESTS PASSED" : "TEST FAILURE DETECTED"));
        System.out.println("-------------------------------------------");
    }

    // Helper method to format test pass/fail output without external test runners
    private static boolean recordTestResult(String testName, boolean condition) {
        // Check test outcome and print corresponding status tag
        if (condition) {
            System.out.printf("[PASS] %s%n", testName);
            return true;
        } else {
            System.err.printf("[FAIL] %s%n", testName);
            return false;
        }
    }
}