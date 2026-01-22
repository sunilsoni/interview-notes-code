package com.interview.notes.code.year.y2026.jan.common.test1;

import java.util.List;
import java.util.stream.IntStream;

public class EmployeeFilter {

    // Main method: Acts as the entry point and our test runner
    public static void main(String[] args) {

        // --- Test Case 1: The Specific Requirement (5 Employees) ---
        System.out.println("Running Test Case 1: Standard Input (5 Employees)..."); // Print test start

        // Create an immutable list of 5 employees using List.of (Java 9+)
        var employees = List.of(
            new Employee("Alice", 25, "F"), // Age < 50
            new Employee("Bob", 55, "M"),   // Age > 50 (Target)
            new Employee("Charlie", 62, "M"), // Age > 50 (Target)
            new Employee("Diana", 45, "F"), // Age < 50
            new Employee("Eve", 51, "F")    // Age > 50 (Target)
        );

        // Core Logic: Stream the list -> Filter by age > 50 -> Collect to List
        // Java 16+ allows .toList() directly on stream, reducing code vs .collect(Collectors.toList())
        var result = employees.stream()         // Convert list to stream source
            .filter(e -> e.age() > 50)          // Lambda: Keep if employee age is > 50
            .toList();                          // Terminate stream and return unmodifiable List

        // define expected output manually for validation
        var expected = List.of(
            new Employee("Bob", 55, "M"),
            new Employee("Charlie", 62, "M"),
            new Employee("Eve", 51, "F")
        );

        // Verify Test Case 1
        verify(result.equals(expected), "Standard 5 Employees"); // Check if result matches expected

        // --- Test Case 2: Edge Case (Empty List) ---
        System.out.println("\nRunning Test Case 2: Empty Input..."); // Print test start
        List<Employee> emptyList = List.of(); // Create empty list
        var emptyResult = emptyList.stream().filter(e -> e.age() > 50).toList(); // Run logic
        verify(emptyResult.isEmpty(), "Empty List Check"); // Should return empty list

        // --- Test Case 3: Edge Case (No one matches) ---
        System.out.println("\nRunning Test Case 3: No Matches..."); // Print test start
        var youngList = List.of(new Employee("Kid", 10, "M")); // List with only young people
        var youngResult = youngList.stream().filter(e -> e.age() > 50).toList(); // Run logic
        verify(youngResult.isEmpty(), "No Matches Check"); // Should return empty list

        // --- Test Case 4: Large Data Performance (1 Million items) ---
        System.out.println("\nRunning Test Case 4: Large Data (1 Million inputs)..."); // Print test start

        // Generate 1 million employees using IntStream
        // Half are 20 (Fail), Half are 60 (Pass)
        var largeData = IntStream.range(0, 1_000_000) // Loop 0 to 1M
            .mapToObj(i -> new Employee("Name" + i, i % 2 == 0 ? 60 : 20, "M")) // Even index = 60yo, Odd = 20yo
            .toList(); // Collect to list

        long startTime = System.currentTimeMillis(); // Start timer

        // Process large data
        var largeResultCount = largeData.stream() // Stream 1M items
            .filter(e -> e.age() > 50)            // Filter logic
            .count();                             // Count matches (should be 500k)

        long endTime = System.currentTimeMillis(); // End timer

        // Verify large data result (Should be exactly 500,000 matches)
        boolean largeTestPassed = (largeResultCount == 500_000); // Check correctness
        verify(largeTestPassed, "Large Data Accuracy"); // Print Pass/Fail

        // Print performance stats
        System.out.println("Processed 1M records in: " + (endTime - startTime) + "ms"); // Print time taken
    }

    // Helper method to print PASS/FAIL to avoid code duplication
    private static void verify(boolean condition, String testName) {
        if (condition) { // Check if condition is true
            System.out.println("RESULT: [PASS] - " + testName); // Print PASS
        } else { // If condition is false
            System.err.println("RESULT: [FAIL] - " + testName); // Print FAIL (using err for visibility)
        }
    }

    // Java 21 Record: Define Employee data structure in one line (name, age, gender)
    // Records automatically generate constructor, getters, equals(), hashCode(), and toString()
    record Employee(String name, int age, String gender) {}
}