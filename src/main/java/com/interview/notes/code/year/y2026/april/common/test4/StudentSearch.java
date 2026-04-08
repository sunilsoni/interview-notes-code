package com.interview.notes.code.year.y2026.april.common.test4;

import java.util.List; // Imports List for managing the student collection

// Custom exception class to be thrown when a student search fails
class StudentNotFoundException extends RuntimeException {
    // Constructor that accepts a custom error message
    public StudentNotFoundException(String message) {
        super(message); // Passes the message to the parent RuntimeException
    }
}

// Immutable record for Student to keep code concise and readable
record Student(String name, int marks, String city) {}

// Main class to demonstrate the search logic and testing
public class StudentSearch {

    // Main method to run our test cases without JUnit
    public static void main(String[] args) {
        // Initializes a sample list of students using var
        var students = List.of(
            new Student("Alice", 85, "Pune"),
            new Student("Bob", 65, "Mumbai"),
            new Student("Charlie", 95, "Delhi")
        );

        // TEST 1: Searching for an existing student (Alice)
        try {
            // Calls the search method for Alice
            var found = findStudentByName(students, "Alice");
            // Verifies the name matches and prints PASS/FAIL
            boolean pass1 = found.name().equals("Alice");
            System.out.println("Test 1 (Alice Found): " + (pass1 ? "PASS" : "FAIL"));
        } catch (Exception e) {
            System.out.println("Test 1 (Alice Found): FAIL (Exception thrown)");
        }

        // TEST 2: Searching for a non-existing student (Sunil)
        try {
            // This call should trigger the custom exception
            findStudentByName(students, "Sunil");
            // If the code reaches here, the test failed to catch an exception
            System.out.println("Test 2 (Sunil Not Found): FAIL (No exception thrown)");
        } catch (StudentNotFoundException e) {
            // Verifies the error message is correct and prints PASS
            boolean pass2 = e.getMessage().contains("Sunil");
            System.out.println("Test 2 (Sunil Not Found): " + (pass2 ? "PASS" : "FAIL"));
        }
    }

    /**
     * Searches for a student by name.
     * @param list The list of students to search through.
     * @param name The name to look for.
     * @return The found Student object.
     * @throws StudentNotFoundException if the student is not in the list.
     */
    static Student findStudentByName(List<Student> list, String name) {
        // Returns the student if found, otherwise throws the exception immediately
        return list.stream()
            // Filters the stream to match the specific name provided as input
            .filter(s -> s.name().equalsIgnoreCase(name))
            // Retrieves the first matching element found in the stream
            .findFirst()
            // Throws our custom exception if the Optional is empty (no match found)
            .orElseThrow(() -> new StudentNotFoundException("Student not found with name: " + name));
    }
}