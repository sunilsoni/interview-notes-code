package com.interview.notes.code.year.y2026.june.apple.test3;

import java.util.Arrays; // Required to create test lists that allow null values (List.of() strictly rejects nulls)
import java.util.List; // Required to use the List interface for storing collections of targets
import java.util.Objects; // Required for utility methods like Objects::nonNull to filter out null elements
import java.util.Set; // Required to use the Set interface for unique collections and fast O(1) lookups
import java.util.stream.Collectors; // Required for Stream terminal operations to collect results into a Set
import java.util.stream.IntStream; // Required to generate sequential streams for large load testing
import java.util.stream.Stream; // Required to generate empty streams when handling null dependency lists

// Java 21 Record for immutable data structures, minimizing boilerplate code like getters and constructors
record Target(String name, List<String> dependencies) {} // Represents a build target and its required dependencies

public class BuildValidator { // Main class containing the dependency validation and edge-case handling logic

    // Method takes a pre-provided set of known targets and a list of targets to validate
    public static Set<String> findInvalidDependencies(Set<String> knownTargets, List<Target> targets) { // Entry point for validation
        
        // Edge Case 1: Fast-fail if the caller passes null for either required parameter
        if (knownTargets == null || targets == null) return Set.of(); // Return an empty set immediately to prevent NullPointerException
        
        return targets.stream() // Open a sequential stream over the list of targets to process them
            .filter(Objects::nonNull) // Edge Case 2: Safely drop any null Target objects sitting inside the list
            // Edge Case 3: Handle targets that have a null dependency list by returning an empty stream instead of crashing
            .flatMap(target -> target.dependencies() == null ? Stream.empty() : target.dependencies().stream()) 
            // Edge Case 4: Safely drop dependencies that are null or just empty spaces (blank strings)
            .filter(dep -> dep != null && !dep.isBlank()) 
            .filter(dep -> !knownTargets.contains(dep)) // Core logic: retain the dependency ONLY if it is NOT in the provided knownTargets set
            .collect(Collectors.toSet()); // Collect remaining invalid dependencies into a Set to remove duplicates and return
    }

    // Main method serves as our lightweight, dependency-free testing framework
    public static void main(String[] args) { // Standard entry point for Java applications
        
        Set<String> known = Set.of("App", "DB", "UI", "API"); // Initialize an immutable set of valid target names for O(1) lookups
        
        // Test Case 1: Null parameters (Extreme Edge Case)
        test("Null Parameters", null, null, Set.of()); // Pass nulls for both arguments, expecting a safe empty set return
        
        // Test Case 2: Messy Data (Null objects, null lists, blank strings)
        Target messyTarget1 = new Target("UI", Arrays.asList("API", null, "   ", "MissingLib")); // Contains a valid dep, a null dep, a blank string, and an invalid dep
        Target messyTarget2 = new Target("Bad", null); // The dependency list itself is completely null
        List<Target> messyData = Arrays.asList(messyTarget1, messyTarget2, null); // List contains our messy targets plus a literal null target object
        test("Messy Edge Cases", known, messyData, Set.of("MissingLib")); // Expecting it to gracefully ignore all nulls/blanks and only flag "MissingLib"
        
        // Test Case 3: Empty input target list
        test("Empty Input", known, List.of(), Set.of()); // Execute test with empty list, expecting no exceptions and no invalid dependencies
        
        // Test Case 4: Large data volume testing to ensure performance at scale
        Set<String> largeKnown = IntStream.range(0, 100_000) // Generate 100,000 integers using Stream API
            .mapToObj(i -> "T" + i) // Map each integer to a string target name
            .collect(Collectors.toSet()); // Collect into the provided known targets set
            
        List<Target> largeData = IntStream.range(0, 100_000) // Generate 100,000 test targets
            .mapToObj(i -> new Target("T" + i, List.of("T" + (i + 1), "MissingLib"))) // Each depends on the next target and a hardcoded missing library
            .toList(); // Collect the generated large dataset directly into an immutable List
            
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