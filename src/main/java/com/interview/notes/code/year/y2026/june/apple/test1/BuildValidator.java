package com.interview.notes.code.year.y2026.june.apple.test1;

import java.util.List; // Required to use the List collection interface for storing targets and dependencies
import java.util.Set; // Required to use the Set collection interface to store unique dependencies
import java.util.stream.Collectors; // Required for Stream reductions like toSet()
import java.util.stream.IntStream; // Required to generate large streams of integers for load testing

// Java 21 Record minimizes boilerplate words to define immutable data structures
record Target(String name, List<String> dependencies) {} // Represents a build target and its list of required dependencies

public class BuildValidator { // Main class containing the logic to validate dependencies

    // Method takes a list of targets and returns a set of invalid dependencies
    public static Set<String> findInvalidDependencies(List<Target> targets) { // Static method for direct access without instantiation
        
        // Create a fast lookup set of all valid target names
        Set<String> validTargets = targets.stream() // Convert the list of targets into a sequential stream
            .map(Target::name) // Extract just the name string from each Target record
            .collect(Collectors.toSet()); // Terminal operation to collect names into a HashSet for O(1) lookups
            
        // Find and return dependencies that are not in the validTargets set
        return targets.stream() // Open a new stream over the list of targets
            .flatMap(target -> target.dependencies().stream()) // Flatten all lists of dependencies into a single continuous stream of strings
            .filter(dep -> !validTargets.contains(dep)) // Filter condition: keep the dependency ONLY if it is not in the valid targets set
            .collect(Collectors.toSet()); // Collect the remaining invalid dependencies into a Set to remove duplicates and return
    }

    // Main method to act as our standalone testing framework
    public static void main(String[] args) { // Standard entry point for a Java application
        
        // Test Case 1: All dependencies exist
        Target t1 = new Target("App", List.of("DB", "Cache")); // Target App depends on DB and Cache
        Target t2 = new Target("DB", List.of()); // Target DB has no dependencies
        Target t3 = new Target("Cache", List.of()); // Target Cache has no dependencies
        test("All Valid", List.of(t1, t2, t3), Set.of()); // Execute test expecting an empty set of invalid dependencies
        
        // Test Case 2: Some dependencies are missing
        Target t4 = new Target("UI", List.of("API", "Logger")); // Target UI depends on API and Logger
        Target t5 = new Target("API", List.of("Auth")); // Target API depends on Auth
        test("Invalid Found", List.of(t4, t5), Set.of("Logger", "Auth")); // Execute test expecting Logger and Auth to be flagged
        
        // Test Case 3: Empty input lists
        test("Empty Input", List.of(), Set.of()); // Execute test with no targets, expecting no invalid dependencies
        
        // Test Case 4: Large data volume testing
        List<Target> largeData = IntStream.range(0, 100_000) // Generate 100,000 integers using Stream API
            .mapToObj(i -> new Target("T" + i, List.of("T" + (i + 1), "MissingLib"))) // Map each number to a Target, adding a forward dependency and a missing one
            .toList(); // Collect the generated large dataset directly into a List (Java 16+ feature)
            
        // Test the large dataset
        test("Large Data Load", largeData, Set.of("T100000", "MissingLib")); // Execute test, expecting the very last forward dependency and the hardcoded missing lib to be caught
    }

    // Custom assertion method to print PASS or FAIL to the console
    private static void test(String name, List<Target> input, Set<String> expected) { // Accepts test name, input data, and the expected output
        
        Set<String> actual = findInvalidDependencies(input); // Call the business logic method with the test input
        
        boolean passed = actual.equals(expected); // Compare actual output against expected output to determine boolean result
        
        // Print formatted result string to standard output using ternary operator
        System.out.println(name + " -> " + (passed ? "PASS" : "FAIL") + " | Expected: " + expected + ", Actual: " + actual); // Output the exact comparison for debugging
    }
}