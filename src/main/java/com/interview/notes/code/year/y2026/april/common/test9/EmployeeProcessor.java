package com.interview.notes.code.year.y2026.april.common.test9;

import java.util.List; // Needed to use the List collection to hold our data items
import java.util.Map; // Needed to use the Map collection for key-value pair results
import java.util.stream.Collectors; // Needed for grouping and averaging stream elements
import java.util.stream.IntStream; // Needed to quickly generate large numbers of test records

public class EmployeeProcessor { // Defining the main class to hold our application logic

    // 1. Filter active employees in Engineering with salary > 80000
    public static List<Employee> getHighEarningActiveEngineers(List<Employee> employees) { // Method takes a list and returns a filtered list
        return employees.stream() // Convert the list into a stream to process elements sequentially
            .filter(Employee::active) // Keep only employees where the 'active' boolean is true
            .filter(e -> "Engineering".equals(e.department())) // Keep only employees whose department matches "Engineering"
            .filter(e -> e.salary() > 80000) // Keep only employees earning more than 80000
            .toList(); // Collect the remaining elements into an unmodifiable List (Java 16+ feature to save words)
    } // End of filtering method

    // 2. Average salary by department
    public static Map<String, Double> getAverageSalaryByDept(List<Employee> employees) { // Method returns a map of Dept Name to Average Salary
        return employees.stream() // Convert the list into a stream for processing
            .collect(Collectors.groupingBy( // Group the stream elements into a Map based on a specific key
                Employee::department, // Use the department name as the Map key
                Collectors.averagingDouble(Employee::salary) // Calculate the average of the salaries for all employees in that department
            )); // Close the collect operation
    } // End of averaging method

    // 3. Group employees by department
    public static Map<String, List<Employee>> getEmployeesByDept(List<Employee> employees) { // Method returns a map of Dept Name to a List of Employees
        return employees.stream() // Convert the list into a stream for processing
            .collect(Collectors.groupingBy(Employee::department)); // Group the elements into a Map where the key is the department, and the value is a list of those employees
    } // End of grouping method

    // Main method for testing
    public static void main(String[] args) { // The entry point of our Java application

        var employees = List.of( // Using 'var' (Java 10+) to let the compiler infer the List type, saving code words
            new Employee("E1", "Alice",   "Engineering", "OU-North", 85000, true), // Creating record 1
            new Employee("E2", "Bob",     "Finance",     "OU-South", 72000, true), // Creating record 2
            new Employee("E3", "Charlie", "Engineering", "OU-North", 90000, false), // Creating record 3
            new Employee("E4", "Diana",   "Engineering", "OU-North", 95000, true), // Creating record 4
            new Employee("E5", "Eve",     "HR",          "OU-East",  65000, true) // Creating record 5
        ); // Closing the list creation

        System.out.println("--- Running Standard Test Cases ---"); // Print a header to the console for clarity

        // Test Case 1: Filtering
        var filtered = getHighEarningActiveEngineers(employees); // Call the filter method and store the result
        var test1Pass = filtered.size() == 2 && filtered.get(0).name().equals("Alice") && filtered.get(1).name().equals("Diana"); // Verify we only got the 2 correct people
        System.out.println("Test 1 (Filter): " + (test1Pass ? "PASS" : "FAIL")); // Print the pass or fail result using a ternary operator

        // Test Case 2: Average Salary
        var averages = getAverageSalaryByDept(employees); // Call the averaging method and store the result
        var enggAvg = (85000 + 90000 + 95000) / 3.0; // Manually calculate what the engineering average should be
        var test2Pass = averages.get("Engineering").equals(enggAvg) && averages.get("HR").equals(65000.0); // Verify the map contains the correct mathematical averages
        System.out.println("Test 2 (Averages): " + (test2Pass ? "PASS" : "FAIL")); // Print the pass or fail result to the console

        // Test Case 3: Grouping
        var grouped = getEmployeesByDept(employees); // Call the grouping method and store the result
        var test3Pass = grouped.get("Engineering").size() == 3 && grouped.get("Finance").size() == 1; // Verify the correct number of employees are in each department bucket
        System.out.println("Test 3 (Grouping): " + (test3Pass ? "PASS" : "FAIL")); // Print the pass or fail result to the console


        System.out.println("\n--- Running Large Data Test Case ---"); // Print a header to separate standard tests from load tests

        // Generate 1 million records to test performance and memory limits
        var largeData = IntStream.range(0, 1000000) // Create an integer stream from 0 to 999,999
            .mapToObj(i -> new Employee("ID"+i, "Name"+i, (i%2==0 ? "Engineering" : "Sales"), "OU", 85000, true)) // Convert each number into an Employee object, alternating departments
            .toList(); // Collect the 1 million objects into a list

        try { // Start a try-catch block to handle any potential memory crashes gracefully
            var largeDataStart = System.currentTimeMillis(); // Record the exact time before processing starts
            var largeFiltered = getHighEarningActiveEngineers(largeData); // Run our filtering method against the 1 million records
            var largeAverages = getAverageSalaryByDept(largeData); // Run our averaging method against the 1 million records
            var largeTime = System.currentTimeMillis() - largeDataStart; // Calculate how many milliseconds it took to run both operations

            // If the filtered list size is exactly half (since we alternated departments) we know it succeeded
            if (largeFiltered.size() == 500000 && largeAverages.size() == 2) { // Check if the data aggregations are mathematically correct
                System.out.println("Large Data Test: PASS (Completed in " + largeTime + " ms)"); // Print success and show the time taken
            } else { // Fallback if the logic calculations were somehow wrong
                System.out.println("Large Data Test: FAIL (Incorrect logic results)"); // Print a failure message for logic errors
            } // End if-else block
        } catch (Exception e) { // Catch any errors, such as OutOfMemoryError
            System.out.println("Large Data Test: FAIL (" + e.getMessage() + ")"); // Print a failure message showing what crashed
        } // End try-catch block

    } // End of main method

    // Employee model definition using a modern Java record
    public record Employee( // 'record' automatically creates an immutable class with getters, equals, and hashcode
        String id, // Unique identifier for the employee
        String name, // The first name of the employee
        String department, // The department where the employee works
        String organizationUnit, // The organizational unit or branch
        double salary, // The annual salary of the employee
        boolean active // A flag indicating if they currently work here
    ) {} // Closing the record definition body
} // End of class