package com.interview.notes.code.year.y2026.april.common.test5;

import java.util.List; // Imports List for collection handling
import java.util.Optional; // Imports Optional for null-safe return types

// Compact record definition for the Student entity
record Student(String name, int marks, String city) {}

// Main class to demonstrate Optional-based search logic
public class StudentOptionalSearch {

    // Main method for manual testing of the search functionality
    public static void main(String[] args) {
        // Initializes a list of 100 students (simulated with 3 for brevity)
        var students = List.of(
            new Student("Alice", 85, "Pune"),
            new Student("Bob", 65, "Mumbai"),
            new Student("Charlie", 95, "Delhi")
        );

        // TEST 1: Search for an existing student
        Optional<Student> result1 = findStudentOptional(students, "Alice");
        // Checks if the Optional contains a value and if that value matches Alice
        boolean pass1 = result1.isPresent() && result1.get().name().equals("Alice");
        System.out.println("Test 1 (Alice Found): " + (pass1 ? "PASS" : "FAIL"));

        // TEST 2: Search for a non-existing student
        Optional<Student> result2 = findStudentOptional(students, "Sunil");
        // Checks if the Optional is empty as expected
        boolean pass2 = result2.isEmpty();
        System.out.println("Test 2 (Sunil Not Found): " + (pass2 ? "PASS" : "FAIL"));

        // Large data handling simulation
        // Optional is memory efficient even with large inputs as it only wraps one object (or null)
        boolean testLarge = findStudentOptional(students, "Charlie").isPresent();
        System.out.println("Test 3 (Functional Check): " + (testLarge ? "PASS" : "FAIL"));
    }

    /**
     * Searches for a student and returns an Optional wrapper.
     * This avoids returning null and forces the caller to handle the "not found" case.
     */
    static Optional<Student> findStudentOptional(List<Student> list, String name) {
        // Initiates the stream from the student list
        return list.stream()
            // Filters the students where the name matches the input string
            .filter(s -> s.name().equalsIgnoreCase(name))
            // Returns an Optional containing the first match, or an empty Optional if none found
            .findFirst(); 
    }
}