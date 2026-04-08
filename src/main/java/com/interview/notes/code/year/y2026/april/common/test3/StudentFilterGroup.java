package com.interview.notes.code.year.y2026.april.common.test3;

import java.util.List; // Imports List interface for collection management
import java.util.Map; // Imports Map interface for key-value pair grouping
import java.util.stream.Collectors; // Imports Collectors to aggregate stream elements
import java.util.stream.IntStream; // Imports IntStream to generate large test data

// Defines an immutable record for Student, avoiding boilerplate code like getters/setters
record Student(String name, int marks, String city) {}

// Defines the main class to encapsulate the application and testing logic
public class StudentFilterGroup {

    // Main method serves as the entry point and our simple testing framework
    public static void main(String[] args) {
        // Generates a mock list of students using var for type inference
        var students = List.of(
            // Creates first student instance scoring above 70
            new Student("Alice", 85, "Pune"),
            // Creates second student instance scoring below 70
            new Student("Bob", 65, "Mumbai"),
            // Creates third student instance in the same city as Alice
            new Student("Charlie", 90, "Pune"),
            // Creates fourth student instance with exact boundary marks (70)
            new Student("David", 70, "Delhi"),
            // Creates fifth student instance to test grouping
            new Student("Eve", 95, "Mumbai")
        );

        // Calls the core logic method to process the mock list
        var result = processStudents(students);

        // Runs test 1: Checks if Pune has exactly 2 students (Alice, Charlie)
        boolean test1 = result.get("Pune").size() == 2;
        // Prints the PASS/FAIL outcome for test 1
        System.out.println("Test 1 (Pune size == 2): " + (test1 ? "PASS" : "FAIL"));

        // Runs test 2: Checks if Bob is excluded since 65 is not > 70
        boolean test2 = result.get("Mumbai").size() == 1;
        // Prints the PASS/FAIL outcome for test 2
        System.out.println("Test 2 (Mumbai size == 1): " + (test2 ? "PASS" : "FAIL"));

        // Runs test 3: Checks if Delhi is completely absent because David scored exactly 70
        boolean test3 = !result.containsKey("Delhi");
        // Prints the PASS/FAIL outcome for test 3
        System.out.println("Test 3 (Delhi absent): " + (test3 ? "PASS" : "FAIL"));

        // Simulates large data input generating 1 million student records
        var largeStudents = IntStream.range(0, 1000000)
            // Maps each integer to a new Student, alternating cities and marks
            .mapToObj(i -> new Student("S" + i, i % 100, i % 2 == 0 ? "Pune" : "Mumbai"))
            // Collects the generated stream into an immutable List (Java 16+)
            .toList();
        
        // Records start time to measure performance
        long start = System.currentTimeMillis(); 
        // Processes the large list through the grouping logic
        var largeResult = processStudents(largeStudents); 
        // Records end time after processing completes
        long end = System.currentTimeMillis(); 

        // Validates that large data processing generated expected map keys
        boolean test4 = largeResult.containsKey("Pune"); 
        // Prints the PASS/FAIL and execution time for the large dataset
        System.out.println("Test 4 (Large Data 1M rows in " + (end - start) + "ms): " + (test4 ? "PASS" : "FAIL"));
    }

    // Method extracts logic to process the list, returning a grouped map
    static Map<String, List<Student>> processStudents(List<Student> list) {
        // Returns the final map built from the stream pipeline
        return list.stream() 
            // Filters the stream to only include students strictly scoring > 70
            .filter(s -> s.marks() > 70) 
            // Collects the filtered elements, grouping them using the city field as the map key
            .collect(Collectors.groupingBy(Student::city)); 
    }
}