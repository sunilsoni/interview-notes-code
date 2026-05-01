package com.interview.notes.code.year.y2026.april.common.test7;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors; // Imports Collectors to perform grouping and counting operations on streams
import java.util.stream.IntStream; // Imports IntStream to generate large datasets efficiently for testing

// Defines Employee record with a new 'gender' field, keeping it immutable and eliminating boilerplate
record Employee(String name, String department, double salary, String gender) {} // Holds state for name, dept, salary, and gender

public class EmployeeGenderCount { // Main class to encapsulate our counting logic and test runner methods

    // Method to group employees by gender and count them, returning a Map of String (gender) to Long (count)
    static Map<String, Long> countByGender(List<Employee> list) { // Accepts the employee list as input
        return list.stream() // Converts the input list into a stream to process the data declaratively
            .collect(Collectors.groupingBy(Employee::gender, Collectors.counting())); // Groups elements by gender and counts occurrences per group
    } // Ends the core counting method

    public static void main(String[] args) { // Standard entry point of the program to run our custom tests
        testNormalCase(); // Executes the test with a small, predictable dataset to verify basic logic
        testLargeDataCase(); // Executes the test with a massive dataset to ensure performance and memory stability
    } // Ends the main method

    static void testNormalCase() { // Method to verify the core counting logic works on standard data
        List<Employee> input = List.of( // Creates an immutable list of sample employees for testing
            new Employee("Alice", "IT", 5000, "Female"), // Adds a female employee to the test list
            new Employee("Bob", "HR", 4000, "Male"), // Adds a male employee to the test list
            new Employee("Charlie", "IT", 3000, "Male"), // Adds a second male employee to the test list
            new Employee("Diana", "HR", 4500, "Female") // Adds a second female employee to the test list
        ); // Closes the list initialization

        Map<String, Long> result = countByGender(input); // Calls our target method to perform the counting

        boolean malePass = result.get("Male") == 2L; // Asserts that the calculated count of males is exactly 2
        boolean femalePass = result.get("Female") == 2L; // Asserts that the calculated count of females is exactly 2

        if (malePass && femalePass) { // Evaluates if both gender counts match our expected outcomes
            System.out.println("Normal Case: PASS"); // Outputs a success message to the console if correct
        } else { // Fallback block triggered if the counting logic failed
            System.out.println("Normal Case: FAIL"); // Outputs a failure message to the console if incorrect
        } // Ends the validation block
    } // Ends the normal test method

    static void testLargeDataCase() { // Method to verify the logic handles large volumes without crashing
        List<Employee> largeList = IntStream.range(0, 100000) // Generates a continuous stream of 100,000 integers
            .mapToObj(i -> new Employee("Emp" + i, "IT", 5000, i % 2 == 0 ? "Male" : "Female")) // Maps numbers to alternating Male/Female employees
            .toList(); // Collects the generated objects directly into an unmodifiable list (Java 16+ feature)

        Map<String, Long> result = countByGender(largeList); // Processes the massive list to count the genders

        boolean malePass = result.get("Male") == 50000L; // Asserts that exactly half of the 100k records (50,000) are male
        boolean femalePass = result.get("Female") == 50000L; // Asserts that exactly half of the 100k records (50,000) are female

        if (malePass && femalePass) { // Evaluates if the large dataset was counted accurately without data loss
            System.out.println("Large Data Case: PASS"); // Prints success message confirming heavy load handling
        } else { // Fallback block triggered if the large data processing yields wrong numbers
            System.out.println("Large Data Case: FAIL"); // Prints failure message for the heavy load test
        } // Ends the validation block
    } // Ends the large data test method
} // Ends the main class wrapper