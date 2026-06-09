package com.interview.notes.code.year.y2026.june.apple.test2;

import java.util.List; // Required to use the List interface for collections
import java.util.Set; // Required to use the Set interface for unique collections and fast lookups
import java.util.stream.Collectors; // Required for Stream terminal operations to collect results
import java.util.stream.IntStream; // Required to generate sequential streams for large load testing

// Java 21 Record for immutable data structures, minimizing boilerplate code like getters and constructors
record Target(String name, List<String> dependencies) {} // Represents a build target and its required dependencies

public class BuildValidator { // Main class containing the dependency validation logic

    // Method now takes a pre-provided set of known targets alongside the list of targets to validate
    public static Set<String> findInvalidDependencies(Set<String> knownTargets, List<Target> targets) { // Implements the new assumption from the discussion
        
        return targets.stream() // Open a sequential stream over the list of targets to process them
            .flatMap(target -> target.dependencies().stream()) // Flatten all dependency lists into a single continuous stream of strings
            .filter(dep -> !knownTargets.contains(dep)) // Filter condition: retain the dependency ONLY if it is NOT in the provided knownTargets set
            .collect(Collectors.toSet()); // Collect remaining invalid dependencies into a Set to remove duplicates and return
    }

    // Main method serves as our lightweight, dependency-free testing framework
    public static void main(String[] args) { // Standard entry point for Java applications
        
        // Setup known targets set (simulating the "provided for you" condition from the interview transcript)
        Set<String> known = Set.of("App", "DB", "Cache", "UI", "API"); // Initialize an immutable set of valid target names for O(1) lookups
        
        // Test Case 1: All dependencies are valid and exist in the known set
        Target t1 = new Target("App", List.of("DB", "Cache")); // Target App depends on DB and Cache
        test("All Valid", known, List.of(t1), Set.of()); // Execute test expecting an empty set of invalid dependencies
        
        // Test Case 2: Dependencies include unknown targets
        Target t2 = new Target("UI", List.of("API", "Logger")); // UI depends on API (known) and Logger (unknown)
        Target t3 = new Target("API", List.of("Auth")); // API depends on Auth (unknown)
        test("Invalid Found", known, List.of(t2, t3), Set.of("Logger", "Auth")); // Execute test expecting Logger and Auth to be flagged as invalid
        
        // Test Case 3: Empty input target list
        test("Empty Input", known, List.of(), Set.of()); // Execute test with empty list, expecting no exceptions and no invalid dependencies
        
        // Test Case 4: Large data volume testing to ensure performance at scale
        Set<String> largeKnown = IntStream.range(0, 100_000) // Generate 100,000 integers using Stream API
            .mapToObj(i -> "T" + i) // Map each integer to a string target name
            .collect(Collectors.toSet()); // Collect into the provided known targets set
            
        List<Target> largeData = IntStream.range(0, 100_000) // Generate 100,000 test targets
            .mapToObj(i -> new Target("T" + i, List.of("T" + (i + 1), "MissingLib"))) // Each depends on the next target and a hardcoded missing library
            .toList(); // Collect the generated large dataset directly into an immutable List
            
        // Test the large dataset against the large known targets set
        test("Large Data Load", largeKnown, largeData, Set.of("T100000", "MissingLib")); // Execute test, expecting the out-of-bounds target and "MissingLib"
    }

    // Custom assertion method to evaluate tests and print PASS/FAIL results
    private static void test(String name, Set<String> knownTargets, List<Target> input, Set<String> expected) { // Accepts test parameters and expected outcome
        
        Set<String> actual = findInvalidDependencies(knownTargets, input); // Execute the business logic with provided inputs
        
        boolean passed = actual.equals(expected); // Compare the actual result with the expected result for equality
        
        // Print formatted output to console displaying PASS/FAIL status and exact data
        System.out.println(name + " -> " + (passed ? "PASS" : "FAIL") + " | Expected: " + expected + ", Actual: " + actual); // Output test execution details for debugging
    }
}