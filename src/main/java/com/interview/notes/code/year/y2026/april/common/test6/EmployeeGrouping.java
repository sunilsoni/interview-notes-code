package com.interview.notes.code.year.y2026.april.common.test6;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors; // Import Collectors for Stream API grouping operations
import java.util.stream.IntStream; // Import IntStream to efficiently generate large mock datasets

// Define Employee as a Java record to eliminate boilerplate getters, setters, and constructors
record Employee(String name, String department, double salary) {} // Holds the state for name, dept, and salary immutably

public class EmployeeGrouping { // Main wrapper class for our logic and tests

    // Core method: takes a list, sorts it globally by salary, then groups it by department
    static Map<String, List<Employee>> groupAndSort(List<Employee> list) { // Returns a Map of department names to Lists of Employees
        return list.stream() // Convert the input list into a sequence of elements for processing
            .sorted(Comparator.comparingDouble(Employee::salary)) // Sort the entire stream ascending by the salary field
            .collect(Collectors.groupingBy(Employee::department)); // Group by department; groupingBy preserves the prior sorted order
    } // End of core method

    public static void main(String[] args) { // Standard execution entry point for our custom test runner
        testNormalCase(); // Execute the basic test case with a small, predictable dataset
        testLargeDataCase(); // Execute the stress test to ensure performance on large data
    } // End of main method

    static void testNormalCase() { // Method to validate standard business logic
        List<Employee> input = List.of( // Create an immutable list of test employees using Java 9+ factory method
            new Employee("Alice", "IT", 5000), // Add Alice to IT with a high salary
            new Employee("Bob", "HR", 4000), // Add Bob to HR with a baseline salary
            new Employee("Charlie", "IT", 3000), // Add Charlie to IT with a lower salary to test sorting
            new Employee("David", "HR", 4500) // Add David to HR with a higher salary to test sorting
        ); // Close the List.of initialization

        Map<String, List<Employee>> result = groupAndSort(input); // Invoke our target logic

        // Charlie (3000) should appear before Alice (5000) in the IT list
        boolean itPass = result.get("IT").get(0).name().equals("Charlie"); // Assert the first IT employee is the lowest paid
        
        // Bob (4000) should appear before David (4500) in the HR list
        boolean hrPass = result.get("HR").get(0).name().equals("Bob"); // Assert the first HR employee is the lowest paid

        if (itPass && hrPass) { // Evaluate if both department sorting rules were successfully applied
            System.out.println("Normal Case: PASS"); // Output success to the console
        } else { // Triggered if sorting or grouping failed
            System.out.println("Normal Case: FAIL"); // Output failure to the console
        } // End of validation block
    } // End of normal test method

    static void testLargeDataCase() { // Method to validate behavior under heavy data loads
        List<Employee> largeList = IntStream.range(0, 100000) // Generate 100,000 integers to act as our data source
            .mapToObj(i -> new Employee("Emp" + i, i % 2 == 0 ? "A" : "B", 100000 - i)) // Map to employees, alternating depts, with descending salaries
            .toList(); // Collect directly to an unmodifiable list (Java 16+ concise collection)

        Map<String, List<Employee>> result = groupAndSort(largeList); // Process the massive list

        boolean sizePass = result.get("A").size() == 50000; // Assert that exactly half the records went to department A
        boolean sortPass = result.get("A").get(0).salary() < result.get("A").get(1).salary(); // Assert that the initially descending salaries are now correctly ascending

        if (sizePass && sortPass) { // Check if both volume processing and sorting held up
            System.out.println("Large Data Case: PASS"); // Print success for the heavy load test
        } else { // Triggered if memory errors occurred or sorting broke
            System.out.println("Large Data Case: FAIL"); // Print failure for the heavy load test
        } // End of validation block
    } // End of large data test method
} // End of main class