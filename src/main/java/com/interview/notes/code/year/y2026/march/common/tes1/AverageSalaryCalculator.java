package com.interview.notes.code.year.y2026.march.common.tes1;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// Using Java 21 record to create a data object; this automatically creates constructors and getters
record Employee1(String name, String department, double salary) {} // Defines the Employee structure concisely

public class AverageSalaryCalculator { // Declares the main class that holds our logic and tests

    // The core method that calculates the average salary grouped by department
    public static Map<String, Double> calculateAverage(List<Employee1> employee1s) { // Accepts a list, returns a map
        return employee1s.stream() // Converts the list into a Stream so we can apply functional operations
                .collect( // Triggers the processing of the stream to gather the results
                        Collectors.groupingBy( // Instructs the collector to group items by a specific key
                                Employee1::department, // Uses the department name as the grouping key
                                Collectors.averagingDouble(Employee1::salary) // Calculates the average of the salaries within each group
                        ) // Closes the groupingBy collector
                ); // Closes the collect method and returns the final Map
    } // Closes the calculateAverage method

    // The main method serves as our testing entry point instead of using JUnit
    public static void main(String[] args) { // Standard Java entry point
        testNormalData(); // Calls the method to verify standard inputs
        testLargeData(); // Calls the method to verify performance with a massive dataset
    } // Closes the main method

    // Test case for typical business logic validation
    private static void testNormalData() { // Defines the helper method for normal testing
        List<Employee1> employee1s = List.of( // Creates an immutable list of sample employees
                new Employee1("John", "Engineering", 80000), // Adds first Engineering employee
                new Employee1("Jane", "Engineering", 90000), // Adds second Engineering employee (Avg should be 85000)
                new Employee1("Mark", "HR", 60000) // Adds HR employee (Avg should be 60000)
        ); // Closes the list creation

        Map<String, Double> result = calculateAverage(employee1s); // Executes our core logic on the test data

        boolean isEngineeringCorrect = result.get("Engineering") == 85000.0; // Validates the Engineering math
        boolean isHrCorrect = result.get("HR") == 60000.0; // Validates the HR math

        if (isEngineeringCorrect && isHrCorrect) { // Checks if both test conditions passed
            System.out.println("Test 1 (Normal Data): PASS"); // Prints success message to console
        } else { // Fallback if the logic is broken
            System.out.println("Test 1 (Normal Data): FAIL"); // Prints failure message to console
        } // Closes the conditional block
    } // Closes the normal data test method

    // Test case to ensure the solution handles large data inputs without crashing
    private static void testLargeData() { // Defines the helper method for scale testing
        List<Employee1> largeList = IntStream.range(0, 1000000) // Generates a stream of 1 million integers
                .mapToObj(i -> new Employee1("User" + i, "Sales", 50000)) // Converts each integer into an Employee in "Sales" earning 50k
                .toList(); // Collects the 1 million objects into a standard List

        Map<String, Double> result = calculateAverage(largeList); // Processes the massive dataset

        if (result.get("Sales") == 50000.0) { // Validates that the average of 1 million 50k salaries is exactly 50k
            System.out.println("Test 2 (Large Data - 1M records): PASS"); // Prints success message to console
        } else { // Fallback if memory or math fails
            System.out.println("Test 2 (Large Data - 1M records): FAIL"); // Prints failure message to console
        } // Closes the conditional block
    } // Closes the large data test method

} // Closes the entire class