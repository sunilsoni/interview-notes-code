package com.interview.notes.code.year.y2026.jan.common.test6;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StudentFilterApp {

    public static void main(String[] args) {

        // --- 1. Define Test Data ---
        List<Student> students = new ArrayList<>();
        // Case A: Valid Candidate (4 marks, avg 60) -> SHOULD PASS
        students.add(new Student("Alice", List.of(60, 60, 60, 60)));
        // Case B: Not enough marks (2 marks) -> SHOULD FAIL
        students.add(new Student("Bob", List.of(90, 90)));
        // Case C: Avg too low (4 marks, avg 40) -> SHOULD FAIL
        students.add(new Student("Charlie", List.of(40, 40, 40, 40)));
        // Case D: Valid Candidate (5 marks, avg 80), name starts with Z -> SHOULD PASS (but sorted last)
        students.add(new Student("Zara", List.of(80, 80, 80, 80, 80)));

        // --- 2. Execute Logic ---
        List<Student> result = processStudents(students);

        // --- 3. Run Verification ---
        runTests(result);

        // --- 4. Large Data Test ---
        testLargeData();
    }

    // Core Logic Method
    static List<Student> processStudents(List<Student> input) {
        // Return empty list if input is null to avoid crashes
        if (input == null) return List.of();

        return input.stream() // Convert list to a stream for processing
            .filter(s -> s.marks() != null && s.marks().size() > 3) // Check if marks exist and count is greater than 3
            .filter(s -> s.marks().stream() // Create a sub-stream of the marks integers
                .mapToInt(Integer::intValue) // Convert Integer objects to primitive ints for math
                .average() // Calculate the average
                .orElse(0.0) > 50) // If marks list is empty use 0.0, then check if avg > 50
            .sorted(Comparator.comparing(Student::name)) // Sort the remaining students by Name alphabetically
            .toList(); // Java 16+: Collect results directly into a list (Unmodifiable)
    }

    // Simple Test Framework
    static void runTests(List<Student> result) {
        System.out.println("--- Running Standard Tests ---");

        // Test 1: Alice should be present
        boolean hasAlice = result.stream().anyMatch(s -> s.name().equals("Alice"));
        printTestResult("Contains Alice (Valid)", hasAlice);

        // Test 2: Bob should be absent (Too few marks)
        boolean hasBob = result.stream().anyMatch(s -> s.name().equals("Bob"));
        printTestResult("Excludes Bob (Low Count)", !hasBob);

        // Test 3: Charlie should be absent (Low Average)
        boolean hasCharlie = result.stream().anyMatch(s -> s.name().equals("Charlie"));
        printTestResult("Excludes Charlie (Low Avg)", !hasCharlie);

        // Test 4: Sorting Order (Alice should be before Zara)
        boolean isSorted = result.get(0).name().equals("Alice") && result.get(result.size()-1).name().equals("Zara");
        printTestResult("Is Sorted Correctly", isSorted);
    }

    // Performance Test for Large Data
    static void testLargeData() {
        System.out.println("\n--- Running Large Data Test ---");
        List<Student> hugeList = new ArrayList<>();

        // Generate 1 million dummy records
        for (int i = 0; i < 1_000_000; i++) {
            // Add a "Pass" candidate
            hugeList.add(new Student("Student" + i, List.of(60, 60, 60, 60)));
        }

        long startTime = System.currentTimeMillis(); // Start timer
        List<Student> result = processStudents(hugeList); // Process
        long endTime = System.currentTimeMillis(); // End timer

        boolean countCheck = result.size() == 1_000_000;
        printTestResult("Handle 1 Million Records", countCheck);
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
    }

    // Helper to print nice PASS/FAIL messages
    static void printTestResult(String testName, boolean condition) {
        // If condition is true, print PASS in green (if terminal supports it) or just text
        String status = condition ? "PASS" : "FAIL";
        System.out.printf("[%s] : %s%n", status, testName);
    }

    // Java Record: A concise way to define the data carrier (Available in modern Java)
    // This automatically creates constructor, accessors, toString, etc.
    record Student(String name, List<Integer> marks) {}
}