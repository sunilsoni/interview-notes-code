package com.interview.notes.code.year.y2025.feb.common.test1;

import java.util.*;

public class DependencyOrderer {
    // Main method for testing the implementation
    public static void main(String[] args) {
        // Test Case 1: Create test data for the given example
        Map<String, Set<String>> test1 = new HashMap<>();
        test1.put("A", new HashSet<>(Arrays.asList("B", "C")));     // A depends on B and C
        test1.put("B", new HashSet<>(Arrays.asList("C", "D")));     // B depends on C and D
        test1.put("C", new HashSet<>(Arrays.asList("B", "D")));     // C depends on B and D
        test1.put("D", new HashSet<>(Arrays.asList("E")));          // D depends on E
        test1.put("E", new HashSet<>());                            // E has no dependencies

        // Create orderer instance
        DependencyOrderer orderer = new DependencyOrderer();

        // Get ordered result
        List<Set<Component>> result = orderer.orderDependencies(test1);

        // Print results
        System.out.println("Test Case 1 Result:");
        for (int i = 0; i < result.size(); i++) {
            System.out.println("Level " + i + ": " + result.get(i));
        }

        // Verify the result meets all requirements
        boolean pass = result.size() == 3 &&                     // Should have 3 levels
                result.get(0).toString().contains("E") &&  // E should be first
                result.get(1).toString().contains("D") &&  // D should be second
                result.get(2).toString().contains("B") &&  // B,C should be together
                result.get(2).toString().contains("C") &&
                result.get(2).toString().contains("A");

        System.out.println("Test Case 1: " + (pass ? "PASS" : "FAIL"));

        // Test Case 2: Large dataset test
        Map<String, Set<String>> largeTest = new HashMap<>();
        // Create a large circular dependency chain
        for (int i = 0; i < 10000; i++) {
            largeTest.put("Comp" + i, new HashSet<>(
                    Arrays.asList("Comp" + (i + 1 % 10000))));
        }

        // Measure performance
        long start = System.currentTimeMillis();
        orderer.orderDependencies(largeTest);
        long end = System.currentTimeMillis();
        System.out.println("Large dataset test: " + (end - start) + "ms");
    }

    // Main method to order dependencies and return levels of components
    public List<Set<Component>> orderDependencies(Map<String, Set<String>> dependencyMap) {
        // Map to store component objects keyed by their names
        Map<String, Component> components = new HashMap<>();

        // Create Component objects for each component name
        dependencyMap.forEach((name, deps) -> {
            components.putIfAbsent(name, new Component(name));
        });

        // Build the dependency graph by connecting components
        dependencyMap.forEach((name, deps) -> {
            Component comp = components.get(name);
            deps.forEach(dep -> comp.dependencies.add(components.get(dep)));
        });

        // Lists and sets for tracking the ordering process
        List<Set<Component>> result = new ArrayList<>();    // Final ordered result
        Set<Component> visited = new HashSet<>();          // Track visited components
        Set<Component> processing = new HashSet<>();       // Track components being processed

        // Process each component to find its level
        components.values().forEach(comp -> {
            if (!visited.contains(comp)) {
                findLevel(comp, visited, processing, result);
            }
        });

        return result;
    }

    // Recursive method to find the level of each component
    private void findLevel(Component comp, Set<Component> visited,
                           Set<Component> processing, List<Set<Component>> result) {
        // If component is being processed, we found a circular dependency
        if (processing.contains(comp)) {
            Set<Component> cyclicGroup = new HashSet<>();
            processing.forEach(cyclicGroup::add);      // Group all components in the cycle
            result.add(cyclicGroup);                   // Add cycle group to result
            visited.addAll(cyclicGroup);               // Mark all cycle components as visited
            processing.clear();                        // Clear processing set
            return;
        }

        // Skip if already visited
        if (visited.contains(comp)) return;

        // Add component to processing set
        processing.add(comp);

        // Recursively process all dependencies
        for (Component dep : comp.dependencies) {
            findLevel(dep, visited, processing, result);
        }

        // If component is still in processing, add it to result
        if (processing.contains(comp)) {
            Set<Component> level = new HashSet<>();
            level.add(comp);
            result.add(level);
            visited.add(comp);
            processing.remove(comp);
        }
    }

    // Inner class to represent a component and its dependencies
    static class Component {
        String name;                                    // Name of the component
        Set<Component> dependencies = new HashSet<>();  // Set of components this component depends on

        // Constructor initializes a component with a name
        Component(String name) {
            this.name = name;
        }

        // Override toString for readable output
        @Override
        public String toString() {
            return name;
        }
    }
}
