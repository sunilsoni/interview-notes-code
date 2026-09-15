package com.interview.notes.code.year.y2026.august.common.test6;

import java.util.*; // Imports Collections, Maps, and Optional needed for data handling.
import java.util.stream.Collectors; // Imports stream collectors to group and find max values.

public class TopSalaryFinder { // Main class container for logic and manual testing.

    public static void main(String[] args) { // Main execution thread replacing JUnit.
        testBasic(); // Executes the small, predictable test case.
        testLarge(); // Executes the volume test case for performance testing.
    } // Closes main method.

    static void testBasic() { // Tests a small, hardcoded dataset to verify exact logic.
        var emps = List.of( // Creates an immutable list of mock employees to test.
            new Employee(1, 10, 5000), // Employee ID 1 in Dept 10, base salary.
            new Employee(2, 10, 8000), // Employee ID 2 in Dept 10, highest salary here.
            new Employee(3, 20, 6000)  // Employee ID 3 in Dept 20, only employee here.
        ); // Closes list creation.

        var result = findTopEarners(emps); // Runs the core grouping logic against our test data.
        var passed = result.get(10).orElseThrow().id() == 2; // Extracts Dept 10 winner and checks if it's ID 2.
        System.out.println("Basic Test: " + (passed ? "PASS" : "FAIL")); // Prints PASS or FAIL directly to console.
    } // Closes basic test method.

    static void testLarge() { // Tests ability to handle 1 million+ rows without memory crashes.
        var emps = new ArrayList<Employee>(1_000_001); // Pre-allocates a massive list to prevent expensive array resizing overhead.
        for (int i = 0; i < 1_000_000; i++) { // Loops exactly one million times to generate bulk mock data.
            emps.add(new Employee(i, i % 5, i * 0.1)); // Distributes employees across 5 departments with incrementally increasing salaries.
        } // Closes the data generation loop.
        emps.add(new Employee(9999999, 1, 9999999.0)); // Injects an undeniable top earner into department 1.

        var result = findTopEarners(emps); // Runs core logic against the 1 million+ record list.
        var passed = result.get(1).orElseThrow().id() == 9999999; // Validates the specific injected employee was found dynamically in Dept 1.
        System.out.println("Large Data Test: " + (passed ? "PASS" : "FAIL")); // Evaluates and prints PASS or FAIL.
    } // Closes large test method.

    static Map<Integer, Optional<Employee>> findTopEarners(List<Employee> emps) { // Core logic returning Dept ID mapped to top Employee.
        return emps.stream() // Opens a sequential data stream for functional processing without loops.
            .collect(Collectors.groupingBy( // Terminal operation grouping the stream elements into a Map.
                Employee::deptId, // The classification key: groups the elements by their Department ID.
                Collectors.maxBy(Comparator.comparingDouble(Employee::salary)) // Within each group bucket, finds the max object based on salary.
            )); // Closes the collector and returns the resulting Map back to the caller.
    } // Closes the core logic method.

    record Employee(int id, int deptId, double salary) {} // Java 21 record: creates an immutable data class with getters, saving boilerplate.
} // Closes the class.