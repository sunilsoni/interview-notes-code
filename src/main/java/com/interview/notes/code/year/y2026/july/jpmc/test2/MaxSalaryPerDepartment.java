package com.interview.notes.code.year.y2026.july.jpmc.test2;

import java.util.List; // Needed to handle the input and output collections
import java.util.function.Function; // Needed for Function.identity() to return the object itself
import java.util.stream.Collectors; // Needed for Collectors.toMap to group and filter data
import java.util.stream.IntStream; // Needed to generate massive datasets for performance testing

// Java record defines our Employee with the new department and salary fields automatically
record Employee(long id, String name, String department, double salary) {}

public class MaxSalaryPerDepartment { // Main class to hold our business logic and tests

    // Method to find the top earner per department and return them as a list
    public static List<Employee> getTopEarners(List<Employee> employees) { // Takes the full employee list
        return employees.stream() // Opens a stream to process the list items one by one
                .collect(Collectors.toMap( // Gathers the stream into a Map temporarily to filter out lower salaries
                        Employee::department, // Key mapper: Uses the department string as the unique key
                        Function.identity(), // Value mapper: Uses the Employee object itself as the map's value
                        (emp1, emp2) -> emp1.salary() > emp2.salary() ? emp1 : emp2 // Merge function: If same department, keep the employee with the higher salary
                )) // The map now perfectly holds only 1 top earner per department
                .values() // Grabs only the values (the Employee objects) from our temporary map
                .stream() // Turns those extracted values back into a stream
                .toList(); // Packages the final stream of top earners into an immutable List (Java 16+ feature)
    }

    // Main method to run our custom tests without needing JUnit
    public static void main(String[] args) { // Application entry point
        
        System.out.println("Running tests...\n"); // Prints starting message
        
        // TEST CASE 1: Standard scenario (10 employees, 3 departments)
        var list1 = List.of( // Creates an immutable list of 10 employees
            new Employee(1, "A", "IT", 5000), // IT employee, salary 5000
            new Employee(2, "B", "IT", 9000), // IT employee, highest salary in IT
            new Employee(3, "C", "IT", 6000), // IT employee, salary 6000
            new Employee(4, "D", "HR", 4000), // HR employee, salary 4000
            new Employee(5, "E", "HR", 4500), // HR employee, highest salary in HR
            new Employee(6, "F", "Sales", 3000), // Sales employee, salary 3000
            new Employee(7, "G", "Sales", 8000), // Sales employee, highest salary in Sales
            new Employee(8, "H", "Sales", 7500), // Sales employee, salary 7500
            new Employee(9, "I", "Sales", 4000), // Sales employee, salary 4000
            new Employee(10, "J", "Sales", 2000) // Sales employee, salary 2000
        ); // Closes list creation
        
        var result1 = getTopEarners(list1); // Calls our logic method
        // Checks if output has exactly 3 employees, and verifies the exact top earners exist
        boolean test1Pass = result1.size() == 3 && 
                            result1.stream().anyMatch(e -> e.name().equals("B")) && // B is max in IT
                            result1.stream().anyMatch(e -> e.name().equals("E")) && // E is max in HR
                            result1.stream().anyMatch(e -> e.name().equals("G"));   // G is max in Sales
        System.out.println("Test 1 (Standard 10 Employees / 3 Depts): " + (test1Pass ? "PASS" : "FAIL")); // Prints pass/fail
        
        // TEST CASE 2: Empty List
        var list2 = List.<Employee>of(); // Creates an empty list
        var result2 = getTopEarners(list2); // Processes empty list
        boolean test2Pass = result2.isEmpty(); // The output should also be completely empty
        System.out.println("Test 2 (Empty List): " + (test2Pass ? "PASS" : "FAIL")); // Prints pass/fail
        
        // TEST CASE 3: Large Data (1,000,000 records across 1,000 departments)
        var largeList = IntStream.range(0, 1_000_000) // Generates integers from 0 to 999,999
                // Maps numbers to employees. Modulo 1000 creates exactly 1000 distinct departments
                .mapToObj(i -> new Employee(i, "Emp" + i, "Dept" + (i % 1000), i)) 
                .toList(); // Collects the 1 million objects into a list
        
        long startTime = System.currentTimeMillis(); // Starts a timer to check performance
        var largeResult = getTopEarners(largeList); // Processes the 1 million records
        long endTime = System.currentTimeMillis(); // Stops the timer
        
        // If there were 1,000 distinct departments, there should be exactly 1,000 top earners returned
        boolean test3Pass = largeResult.size() == 1000; 
        System.out.println("Test 3 (1,000,000 Employees / 1,000 Depts): " + (test3Pass ? "PASS" : "FAIL")); // Prints pass/fail
        System.out.println("Large data processing took: " + (endTime - startTime) + " milliseconds."); // Shows speed
    }
}