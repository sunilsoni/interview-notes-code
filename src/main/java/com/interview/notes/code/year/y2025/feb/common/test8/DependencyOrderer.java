package com.interview.notes.code.year.y2025.feb.common.test8;

import java.util.*;

public class DependencyOrderer {
    public static void main(String[] args) {
        // Test Case 1: Given example
        Map<String, Set<String>> test1 = new HashMap<>();
        test1.put("A", new HashSet<>(Arrays.asList("B", "C")));
        test1.put("B", new HashSet<>(Arrays.asList("C", "D")));
        test1.put("C", new HashSet<>(Arrays.asList("B", "D")));
        test1.put("D", new HashSet<>(List.of("E")));
        test1.put("E", new HashSet<>());

        DependencyOrderer orderer = new DependencyOrderer();
        List<Set<Component>> result = orderer.orderDependencies(test1);

        System.out.println("Test Case 1 Result:");
        for (int i = 0; i < result.size(); i++) {
            System.out.println("Level " + i + ": " + result.get(i));
        }

        // Verify result
        boolean pass = result.size() == 3 && // Should have 3 levels
                result.get(0).toString().contains("E") && // E should be first
                result.get(1).toString().contains("D") && // D should be second
                result.get(2).toString().contains("B") && // B,C should be together
                result.get(2).toString().contains("C") &&
                result.get(2).toString().contains("A");

        System.out.println("Test Case 1: " + (pass ? "PASS" : "FAIL"));

        // Add more test cases here...
    }

    public List<Set<Component>> orderDependencies(Map<String, Set<String>> dependencyMap) {
        // Create components
        Map<String, Component> components = new HashMap<>();
        dependencyMap.forEach((name, deps) -> {
            components.putIfAbsent(name, new Component(name));
        });

        // Build dependency graph
        dependencyMap.forEach((name, deps) -> {
            Component comp = components.get(name);
            deps.forEach(dep -> comp.dependencies.add(components.get(dep)));
        });

        // Find levels using modified Kahn's algorithm
        List<Set<Component>> result = new ArrayList<>();
        Set<Component> visited = new HashSet<>();
        Set<Component> processing = new HashSet<>();

        components.values().forEach(comp -> {
            if (!visited.contains(comp)) {
                findLevel(comp, visited, processing, result);
            }
        });

        return result;
    }

    private void findLevel(Component comp, Set<Component> visited,
                           Set<Component> processing, List<Set<Component>> result) {
        if (processing.contains(comp)) {
            // Handle circular dependency
            Set<Component> cyclicGroup = new HashSet<>();
            processing.forEach(cyclicGroup::add);
            result.add(cyclicGroup);
            visited.addAll(cyclicGroup);
            processing.clear();
            return;
        }

        if (visited.contains(comp)) return;

        processing.add(comp);

        for (Component dep : comp.dependencies) {
            findLevel(dep, visited, processing, result);
        }

        if (processing.contains(comp)) {
            Set<Component> level = new HashSet<>();
            level.add(comp);
            result.add(level);
            visited.add(comp);
            processing.remove(comp);
        }
    }

    static class Component {
        String name;
        Set<Component> dependencies = new HashSet<>();

        Component(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
