package com.interview.notes.code.year.y2026.june.cognizant.test5;

import java.util.Comparator; // Required to define the sorting rules
import java.util.List; // Required to use the List interface for collections
import java.util.stream.IntStream; // Required to quickly generate large datasets for testing

// Define Employee as a Java 21 record to create a lightweight, immutable data carrier without boilerplate getters/setters
record Employee(int id, String name, double salary) {} 

public class EmployeeSorter { // Main class encapsulating our logic

    public static void main(String[] args) { // Entry point of the application
        
        // --- Test Case 1: Standard Sorting ---
        List<Employee> standardInput = List.of( // Create an immutable list of raw employee data
            new Employee(1, "Zack", 50000), // Adding an employee to test name sorting
            new Employee(2, "Alice", 60000), // Adding an employee to test alphabetical priority
            new Employee(3, "Alice", 45000)  // Adding an employee with the same name to test secondary salary sorting
        );
        List<Employee> standardExpected = List.of( // Create the expected correct outcome
            new Employee(3, "Alice", 45000), // Alice with lower salary should be first
            new Employee(2, "Alice", 60000), // Alice with higher salary should be second
            new Employee(1, "Zack", 50000)   // Zack is alphabetically last
        );
        runTest("Standard Sort Test", standardInput, standardExpected); // Execute the test and print Pass/Fail

        // --- Test Case 2: Large Data Input ---
        List<Employee> largeInput = IntStream.range(0, 100000) // Generate 100,000 numbers for ID creation
            .mapToObj(i -> new Employee(i, "Employee", 100000 - i)) // Map each number to an Employee object, salaries descending
            .toList(); // Collect the generated stream into a list
            
        long startTime = System.currentTimeMillis(); // Start a timer to ensure performance holds up
        List<Employee> largeResult = sortEmployees(largeInput); // Run the sorting logic on the large list
        long endTime = System.currentTimeMillis(); // Stop the timer
        
        // Check if the last item has the highest salary, proving the secondary sort worked on the large set
        boolean isLargePass = largeResult.get(99999).salary() == 100000; 
        System.out.println("Large Data Test (100k records) : " + (isLargePass ? "PASS" : "FAIL") + " in " + (endTime - startTime) + "ms"); // Print result
    }

    // Core sorting method logic
    public static List<Employee> sortEmployees(List<Employee> list) { // Method accepts a list and returns a sorted list
        return list.stream() // Open a sequential data stream to process the collection
            .sorted( // Apply a sorting operation on the stream pipeline
                Comparator.comparing(Employee::name) // Primary condition: sort alphabetically by the name field
                .thenComparingDouble(Employee::salary) // Secondary condition: if names are identical, sort by salary ascending
            ) // Close the comparator logic
            .toList(); // Execute the stream and collect the final output into a new, immutable List (Java 16+ feature)
    }

    // Helper method to validate expected results against actual results
    private static void runTest(String testName, List<Employee> input, List<Employee> expected) { // Accepts test name, input, and expected output
        List<Employee> result = sortEmployees(input); // Trigger the sorting algorithm on the input
        if (result.equals(expected)) { // Compare the result directly to the expected list (Records handle deep equals automatically)
            System.out.println(testName + " : PASS"); // If they match, print PASS
        } else { // If they do not match
            System.out.println(testName + " : FAIL"); // Print FAIL
        }
    }
}