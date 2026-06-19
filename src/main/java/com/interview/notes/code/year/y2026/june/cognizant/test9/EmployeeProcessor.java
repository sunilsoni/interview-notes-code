package com.interview.notes.code.year.y2026.june.cognizant.test9;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeProcessor { // Defining the main execution class

    // Core business logic method to extract the top 3 highest paid employees in every city
    public static Map<String, List<Employee>> getTop3EmployeesPerCity(List<Employee> employees) {
        return employees.stream() // Converting the input list into a sequential object stream
            .collect(Collectors.groupingBy( // Grouping elements together based on a specific classifier key
                Employee::city, // Using the city record component as the map grouping key
                Collectors.collectingAndThen( // Downstream collector to transform the grouped elements after collection
                    Collectors.toList(), // First, gathering all matching city records into a temporary list
                    list -> list.stream() // Turning the sub-list back into a stream for sorting operations
                        .sorted(Comparator.comparingDouble(Employee::salary).reversed()) // Sorting by salary in descending order
                        .limit(3) // Restricting the output stream to contain a maximum of 3 top elements
                        .toList() // Collecting the final sliced stream into an immutable Java list
                )
            )); // Closing the groupingBy collector execution block
    }

    // Single main method acting as both application runtime launcher and test harness
    public static void main(String[] args) {
        runStandardTests(); // Invoking the functional test suite validation block
        runLargeScaleDataPerformanceTest(); // Invoking the scalability stress test block
    }

    // Method containing concrete functional business scenarios to verify execution correctness
    private static void runStandardTests() {
        // Constructing an isolated sample dataset representing multiple cities with varied salaries
        List<Employee> sampleData = List.of(
            new Employee("Alice", "Washington DC", 120000), // Entry 1 for Washington DC
            new Employee("Bob", "Washington DC", 150000), // Entry 2 for Washington DC (Highest)
            new Employee("Charlie", "Washington DC", 110000), // Entry 3 for Washington DC
            new Employee("David", "Washington DC", 130000), // Entry 4 for Washington DC
            new Employee("David", "Washington DC", 90000), // Entry 5 for Washington DC
            new Employee("Eva", "Florida", 95000), // Entry 1 for Florida
            new Employee("Frank", "Florida", 140000), // Entry 2 for Florida (Highest)
            new Employee("Grace", "Florida", 85000) // Entry 3 for Florida
        );

        // Executing the business logic mapping code over the standard sample dataset
        Map<String, List<Employee>> result = getTop3EmployeesPerCity(sampleData);

        // Validating that Washington DC contains exactly 3 entries as requested
        boolean passDC = result.get("Washington DC").size() == 3
            && result.get("Washington DC").get(0).salary() == 150000 // Ensuring the highest salary is ranked first
            && result.get("Washington DC").get(1).salary() == 130000; // Ensuring the second highest salary is ranked second

        // Validating that Florida matches exactly 3 entries matching the criteria
        boolean passFlorida = result.get("Florida").size() == 3
            && result.get("Florida").get(0).salary() == 140000; // Checking that the top earner ranks first

        // Outputting visual confirmation to show standard functional test execution status
        System.out.println("Standard Functionality Test: " + ((passDC && passFlorida) ? "PASS" : "FAIL"));
    }

    // Performance assertion testing engine designed to handle large volumetric data streams
    private static void runLargeScaleDataPerformanceTest() {
        List<Employee> largeDataset = new ArrayList<>(); // Instantiating an array list infrastructure container
        String[] locations = {"Washington DC", "Florida", "New York", "Texas"}; // Array containing distinct test markets

        // Loop block to inject 100,000 unique records dynamically into the processing system
        for (int i = 0; i < 100000; i++) {
            String targetCity = locations[i % locations.length]; // Dynamically rotating target cities evenly
            largeDataset.add(new Employee("Emp_" + i, targetCity, 50000 + (i % 5000))); // Populating record parameters
        }

        long startTime = System.currentTimeMillis(); // Capturing exact system timestamp prior to operation execution
        Map<String, List<Employee>> largeResult = getTop3EmployeesPerCity(largeDataset); // Executing data operations
        long executionDuration = System.currentTimeMillis() - startTime; // Computing complete processing run duration

        // Validating that every city present in the mapping payload resolved exactly 3 items
        boolean massScalePass = largeResult.values().stream().allMatch(list -> list.size() == 3);

        // Printing results statement tracking capacity limit boundaries
        System.out.println("Massive Volumetric Data Processing Test: " + (massScalePass ? "PASS" : "FAIL")
            + " (Processed " + largeDataset.size() + " rows in " + executionDuration + "ms)");
    }

    // Defining a Java 21 Record to store immutable Employee data without boilerplate methods
    public record Employee(String name, String city, double salary) {}
}