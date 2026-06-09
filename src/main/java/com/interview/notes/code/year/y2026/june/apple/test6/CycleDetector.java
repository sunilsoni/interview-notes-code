package com.interview.notes.code.year.y2026.june.apple.test6;

import java.util.*; // Imports List, Set, Map, HashSet, ArrayList, and utility classes.
import java.util.stream.Collectors; // Imports Collectors to convert stream data into a Map.

record Target(String name, List<String> dependencies) {} // Immutable record to store target name and dependency list.

public class CycleDetector { // Main class that contains cycle detection logic.

    public static List<String> findCycle(List<Target> targets) { // Method returns the exact cycle path if found.

        if (targets == null || targets.isEmpty()) return List.of(); // If input is null or empty, no cycle exists.

        Map<String, List<String>> graph = targets.stream() // Start streaming all target records.
                .filter(Objects::nonNull) // Ignore null target objects safely.
                .filter(t -> t.name() != null && !t.name().isBlank()) // Ignore targets with null or blank names.
                .collect(Collectors.toMap( // Convert target list into graph map.
                        Target::name, // Use target name as key.
                        t -> t.dependencies() == null ? List.of() : t.dependencies(), // Use empty list if dependencies are null.
                        (a, b) -> a // If duplicate target names exist, keep the first one.
                )); // End graph map creation.

        Set<String> visited = new HashSet<>(); // Stores nodes fully processed and proven cycle-free.
        Set<String> visiting = new HashSet<>(); // Stores nodes currently in active DFS path.
        List<String> path = new ArrayList<>(); // Stores current path to build useful cycle diagnostic.

        for (String node : graph.keySet()) { // Check every target, including disconnected graph parts.

            List<String> cycle = dfs(node, graph, visited, visiting, path); // Start DFS from current target.

            if (!cycle.isEmpty()) return cycle; // If cycle found, return immediately.
        }

        return List.of(); // No cycle found, return empty list.
    }

    private static List<String> dfs(String node, Map<String, List<String>> graph, Set<String> visited, Set<String> visiting, List<String> path) { // Recursive DFS helper.

        if (visiting.contains(node)) { // If node is already in active path, cycle is found.

            int startIndex = path.indexOf(node); // Find where this cycle started in current path.

            List<String> cycle = new ArrayList<>(path.subList(startIndex, path.size())); // Extract only cycle part.

            cycle.add(node); // Add repeated node to close the cycle path.

            return cycle; // Return exact cycle path.
        }

        if (visited.contains(node)) return List.of(); // If already checked safely, skip it.

        if (!graph.containsKey(node)) return List.of(); // Missing dependency is handled by separate invalid dependency validator.

        visiting.add(node); // Mark current node as actively being explored.

        path.add(node); // Add current node into active path.

        for (String dep : graph.getOrDefault(node, List.of())) { // Loop through dependencies of current node.

            if (dep == null || dep.isBlank()) continue; // Ignore null or blank dependency names.

            List<String> cycle = dfs(dep, graph, visited, visiting, path); // Recursively check dependency.

            if (!cycle.isEmpty()) return cycle; // If dependency path found a cycle, return it.
        }

        path.remove(path.size() - 1); // Remove current node from path during backtracking.

        visiting.remove(node); // Remove current node from active DFS set.

        visited.add(node); // Mark current node as fully checked and safe.

        return List.of(); // No cycle found in this branch.
    }

    public static void main(String[] args) { // Main method to run simple PASS/FAIL tests.

        Target t1 = new Target("App", List.of("DB", "UI")); // App depends on DB and UI.
        Target t2 = new Target("UI", List.of("API")); // UI depends on API.
        Target t3 = new Target("DB", List.of()); // DB has no dependency.
        Target t4 = new Target("API", List.of()); // API has no dependency.

        test("Healthy Graph", List.of(t1, t2, t3, t4), List.of()); // No cycle expected.

        Target t5 = new Target("ServiceA", List.of("ServiceB")); // ServiceA depends on ServiceB.
        Target t6 = new Target("ServiceB", List.of("ServiceA")); // ServiceB depends back on ServiceA.

        test("Simple Cycle", List.of(t5, t6), List.of("ServiceA", "ServiceB", "ServiceA")); // Cycle expected.

        Target t7 = new Target("App", List.of("UI")); // App depends on UI.
        Target t8 = new Target("UI", List.of("Auth")); // UI depends on Auth.
        Target t9 = new Target("Auth", List.of("DB")); // Auth depends on DB.
        Target t10 = new Target("DB", List.of("UI")); // DB depends back on UI.

        test("Deep Cycle", List.of(t7, t8, t9, t10), List.of("UI", "Auth", "DB", "UI")); // Cycle expected.

        Target t11 = new Target("Utils", List.of("Utils")); // Utils depends on itself.

        test("Self Loop", List.of(t11), List.of("Utils", "Utils")); // Self-cycle expected.

        test("Null Input", null, List.of()); // Null input should not fail.

        test("Missing Dependency Separate Check", List.of(new Target("App", List.of("Missing"))), List.of()); // Missing target is not cycle.

        List<Target> largeData = new ArrayList<>(); // Creates large graph list.

        List<String> rootDeps = new ArrayList<>(); // Stores many root dependencies.

        for (int i = 1; i <= 99_998; i++) { // Generate many independent child nodes.

            rootDeps.add("Node" + i); // Add child node as root dependency.

            largeData.add(new Target("Node" + i, List.of())); // Add child target with no dependency.
        }

        rootDeps.add("BadNode"); // Add dependency that creates cycle.

        largeData.add(new Target("Root", rootDeps)); // Root depends on many nodes and BadNode.

        largeData.add(new Target("BadNode", List.of("Root"))); // BadNode depends back on Root.

        test("Large Wide Graph Cycle", largeData, List.of("Root", "BadNode", "Root")); // Cycle expected.
    }

    private static void test(String name, List<Target> input, List<String> expected) { // Simple custom test method.

        List<String> actual = findCycle(input); // Run actual cycle detection.

        boolean passed = actual.equals(expected); // Compare actual result with expected result.

        System.out.println(name + " -> " + (passed ? "PASS" : "FAIL") + " | Expected: " + expected + ", Actual: " + actual); // Print test output.
    }
}