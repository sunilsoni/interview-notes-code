package com.interview.notes.code.year.y2026.july.jpmc.test3;

import java.util.Comparator; // Needed to easily compare salaries using Comparator.comparingDouble
import java.util.List; // Needed to use the List interface for our input and output
import java.util.Optional; // Needed because Collectors.maxBy returns an Optional (safeguard for empty groups)
import java.util.stream.Collectors; // Needed for the groupingBy and maxBy collectors
import java.util.stream.IntStream; // Needed to generate the large dataset for performance testing

// Java record creates an immutable class with getters, constructors, and data methods automatically
record Employee(long id, String name, String department, double salary) {}

public class GroupingByMaxSalary { // Main class containing our logic and custom tests

    // The core method that finds the highest paid employee per department
    public static List<Employee> getTopEarners(List<Employee> employees) { // Accepts the employee list
        return employees.stream() // Starts a pipeline to process employees one by one
                .collect(Collectors.groupingBy( // Organizes the stream items into groups (creates a Map behind the scenes)
                        Employee::department, // Grouping rule: Use the department name as the category bucket
                        Collectors.maxBy(Comparator.comparingDouble(Employee::salary)) // For each bucket, find the Employee with the highest salary
                )) // This gives us a Map<String, Optional<Employee>>
                .values() // Extracts just the values (the Optional<Employee> objects) from the map
                .stream() // Turns those extracted values back into a stream for further processing
                .flatMap(Optional::stream) // Safely unpacks the Employee out of the Optional (ignores empty ones)
                .toList(); // Gathers the final, unpacked top earners into an immutable List (Java 16+ feature)
    }

    // Custom testing method as requested (no JUnit)
    public static void main(String[] args) { // Standard entry point for a Java application
        
        System.out.println("Running tests for groupingBy approach...\n"); // Prints starting message
        
        // TEST CASE 1: Standard scenario (10 employees, 3 departments)
        var list1 = List.of( // Creates an immutable list of test employees
            new Employee(1, "A", "IT", 5000), // Standard IT employee
            new Employee(2, "B", "IT", 9000), // Top earner in IT
            new Employee(3, "C", "IT", 6000), // Standard IT employee
            new Employee(4, "D", "HR", 4000), // Standard HR employee
            new Employee(5, "E", "HR", 4500), // Top earner in HR
            new Employee(6, "F", "Sales", 3000), // Standard Sales employee
            new Employee(7, "G", "Sales", 8000), // Top earner in Sales
            new Employee(8, "H", "Sales", 7500), // Standard Sales employee
            new Employee(9, "I", "Sales", 4000), // Standard Sales employee
            new Employee(10, "J", "Sales", 2000) // Standard Sales employee
        ); // Closes the list creation
        
        var result1 = getTopEarners(list1); // Calls our groupingBy method
        // Verifies we have exactly 3 employees and they are specifically B, E, and G
        boolean test1Pass = result1.size() == 3 && 
                            result1.stream().anyMatch(e -> e.name().equals("B")) && // Checks if B is present
                            result1.stream().anyMatch(e -> e.name().equals("E")) && // Checks if E is present
                            result1.stream().anyMatch(e -> e.name().equals("G"));   // Checks if G is present
        System.out.println("Test 1 (Standard 10 Employees): " + (test1Pass ? "PASS" : "FAIL")); // Prints the result
        
        // TEST CASE 2: Empty List
        var list2 = List.<Employee>of(); // Creates an empty list to test edge cases
        var result2 = getTopEarners(list2); // Processes the empty list
        boolean test2Pass = result2.isEmpty(); // Verifies the output is also empty without crashing
        System.out.println("Test 2 (Empty List): " + (test2Pass ? "PASS" : "FAIL")); // Prints the result
        
        // TEST CASE 3: Large Data (1,000,000 records across 1,000 departments)
        var largeList = IntStream.range(0, 1_000_000) // Generates integers from 0 to 999,999
                .mapToObj(i -> new Employee(i, "Emp" + i, "Dept" + (i % 1000), i)) // Maps to employees in 1000 distinct departments
                .toList(); // Collects the 1 million objects into a list
        
        long startTime = System.currentTimeMillis(); // Grabs the current time to start a stopwatch
        var largeResult = getTopEarners(largeList); // Processes the massive list
        long endTime = System.currentTimeMillis(); // Stops the stopwatch
        
        // Checks if exactly 1000 top earners were returned (one for each department)
        boolean test3Pass = largeResult.size() == 1000; 
        System.out.println("Test 3 (1,000,000 Employees / 1,000 Depts): " + (test3Pass ? "PASS" : "FAIL")); // Prints the result
        System.out.println("Large data processing took: " + (endTime - startTime) + " milliseconds."); // Shows performance time
    }
}