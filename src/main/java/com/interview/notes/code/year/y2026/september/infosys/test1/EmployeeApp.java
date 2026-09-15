package com.interview.notes.code.year.y2026.september.infosys.test1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// Java 14+ record creates an immutable class with fields, constructor, getters, and toString automatically, minimizing code.
record Employee(int id, String name, double salary) {} // Defines the Employee entity with the exact three requested fields.

// Application class containing business logic and the manual test framework.
public class EmployeeApp { // Encapsulates the program scope.
    
    // The JVM executes this main method to run our manual test suite instead of using JUnit.
    static void main(String[] args) { // Program entry point.
        // Executes the primary requirement: 3 employees, sorting, and finding the highest salary.
        runTest("Standard 3 Employees", createStandardList(), "Bob"); // Passes the expected highest earner 'Bob'.
        
        // Executes the large data test case to ensure the Stream API handles 100,000+ records without memory leaks.
        runTest("Large Data Input", createLargeList(), "MaxPay"); // Passes the expected highest earner 'MaxPay'.
        
        // Executes an edge case passing an empty list to verify the code doesn't throw a NullPointerException.
        runTest("Empty List Edge Case", List.of(), null); // Expects a null result since nobody exists.
    } // Closes main method.

    // Test runner method that asserts equality and prints PASS or FAIL for our manual test suite.
    static void runTest(String testName, List<Employee> list, String expectedMaxName) { // Takes test name, dataset, and expected string.
        // Calls our core logic method to process the list and extract the highest paid employee.
        var highest = sortAndGetHighest(list); // Uses 'var' (Java 10+) to reduce code verbosity; compiler infers type Employee.
        
        // Safely extracts the name if the employee object isn't null, otherwise assigns null.
        var actualName = (highest != null) ? highest.name() : null; // Prevents NullPointerException on empty list outcomes.
        
        // Evaluates if the actual outcome matches our expected outcome using null-safe Objects.equals.
        var status = Objects.equals(actualName, expectedMaxName) ? "PASS" : "FAIL"; // Assigns PASS if true, FAIL if false.
        
        // Outputs the final test evaluation to the console.
        System.out.println(testName + " -> " + status); // Formats and prints the test execution result.
    } // Closes test runner method.

    // Core business requirement: sorts the employee list by salary and returns the highest paid.
    static Employee sortAndGetHighest(List<Employee> emps) { // Accepts the employee list.
        // Guard clause safely returns null instantly if the provided list is missing or empty.
        if (emps == null || emps.isEmpty()) return null; // Protects the stream pipeline from processing null references.
        
        // Converts the List into a sequential Stream for functional-style data processing.
        return emps.stream() // Initiates the pipeline.
            // Sorts elements by salary in descending order (highest to lowest) to satisfy the sorting requirement.
            .sorted(Comparator.comparingDouble(Employee::salary).reversed()) // Uses method reference for clean syntax.
            // Grabs the very first element from the freshly sorted stream.
            .findFirst() // Terminal operation that returns an Optional<Employee>.
            // Unwraps the Optional, returning the Employee object or null if it was theoretically empty.
            .orElse(null); // Resolves the Optional safely.
    } // Closes sorting method.

    // Factory method building the standard 3-employee list requested in the prompt.
    static List<Employee> createStandardList() { // Returns a hardcoded List.
        // Returns an immutable list initialized with exactly three objects.
        return List.of( // Java 9+ factory method for concise collection creation.
            new Employee(1, "Alice", 50000.0), // Employee 1 with base salary.
            new Employee(2, "Charlie", 60000.0), // Employee 2 with medium salary.
            new Employee(3, "Bob", 75000.0) // Employee 3 with the highest salary.
        ); // Closes List.of.
    } // Closes standard list method.

    // Factory method building a large dataset to stress-test the sorting operation.
    static List<Employee> createLargeList() { // Returns a dynamically generated large List.
        // Generates a sequence of 100,000 integers to simulate a large database table.
        var list = IntStream.range(0, 100000) // Creates a stream from 0 to 99,999.
            // Transforms each integer into a unique Employee record.
            .mapToObj(i -> new Employee(i, "Emp" + i, 40000 + i)) // Increments salary safely to guarantee a sort order.
            // Accumulates the streamed objects into a mutable ArrayList.
            .collect(Collectors.toCollection(ArrayList::new)); // Uses ArrayList so we can append to it on the next line.
            
        // Explicitly injects an outlier record with a massive salary to serve as the guaranteed expected maximum.
        list.add(new Employee(999999, "MaxPay", 9999999.0)); // Ensures our test runner has a definitive target to find.
        
        // Returns the 100,001 item list for processing.
        return list; // Pushes the dataset out to the caller.
    } // Closes large list method.
} // Closes main class.