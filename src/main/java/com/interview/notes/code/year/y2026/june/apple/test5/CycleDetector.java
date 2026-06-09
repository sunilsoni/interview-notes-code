package com.interview.notes.code.year.y2026.june.apple.test5;

import java.util.*;
import java.util.stream.Collectors; // Required to use Stream API for mapping lists to maps

// Java 21 Record provides a compact, immutable data structure for our build targets, eliminating boilerplate constructors
record Target(String name, List<String> dependencies) {} // Holds the target name and what it needs to build

public class CycleDetector { // Main class containing our cycle detection algorithm

    // Public entry method that takes a list of targets and returns a list representing the exact cycle path
    public static List<String> findCycle(List<Target> targets) { // Accepts the raw list of targets
        
        // Edge case protection: if input is null or empty, there is no cycle, so return an empty list
        if (targets == null || targets.isEmpty()) return List.of(); // Fast-fail for safe execution
        
        // Convert the List of targets into a Map for instant O(1) adjacency lookups during traversal
        Map<String, List<String>> graph = targets.stream() // Open a stream on the targets
            .collect(Collectors.toMap(Target::name, Target::dependencies)); // Map the target name (Key) to its dependencies (Value)
            
        Set<String> visited = new HashSet<>(); // Set to track nodes that have been completely processed and are cycle-free
        Set<String> visiting = new HashSet<>(); // Set to track nodes currently in our active recursion stack (O(1) lookup)
        List<String> path = new ArrayList<>(); // List to track the exact ordered path to construct the error message
        
        // Iterate through every declared target to ensure disconnected graphs are fully checked
        for (Target target : targets) { // Enhanced for-loop to start DFS from each node
            
            // Initiate the recursive Depth-First Search for the current target
            List<String> cycle = dfs(target.name(), graph, visited, visiting, path); // Pass state tracking variables into DFS
            
            // If the returned list is not empty, it means a cycle was found deeply within the recursion
            if (!cycle.isEmpty()) return cycle; // Immediately halt everything and return the broken path to the user
        }
        
        return List.of(); // If the loops finish naturally, the graph is totally safe. Return empty list.
    }

    // Private helper method that performs the actual recursive DFS graph traversal
    private static List<String> dfs(String node, Map<String, List<String>> graph, Set<String> visited, Set<String> visiting, List<String> path) { // Requires current node and tracking states
        
        // Base Case 1 (Cycle Found!): If we arrive at a node that is currently in our 'visiting' stack, we looped back on ourselves.
        if (visiting.contains(node)) { // O(1) check if we are caught in a circle
            
            int startIndex = path.indexOf(node); // Find exactly where the loop started in our ordered path history
            List<String> cycle = new ArrayList<>(path.subList(startIndex, path.size())); // Extract just the looped portion of the path
            cycle.add(node); // Append the repeated node to the end to close the loop visually (e.g., A -> B -> A)
            return cycle; // Return the exact cycle loop back up the recursion tree
        }
        
        // Base Case 2 (Already Safe): If the node is in the 'visited' set, we already proved it has no cycles in previous runs
        if (visited.contains(node)) return List.of(); // Return empty to skip redundant processing, keeping time at O(N+M)
        
        visiting.add(node); // Mark the current node as "currently being explored"
        path.add(node); // Push the node onto our ordered path history list
        
        // Fetch dependencies for this node; use empty list as fallback if the target relies on an external/missing node
        List<String> dependencies = graph.getOrDefault(node, List.of()); // Safely get dependencies without NullPointerExceptions
        
        // Recursively visit all dependencies of the current node
        for (String dep : dependencies) { // Iterate over the required dependencies
            
            List<String> cycle = dfs(dep, graph, visited, visiting, path); // Dive one level deeper into the graph
            
            // If the recursive call found a cycle below us, propagate it upwards immediately
            if (!cycle.isEmpty()) return cycle; // Short-circuit the loop to stop wasting CPU cycles
        }
        
        // Backtracking phase: We have safely explored all children of this node without finding a cycle
        path.remove(path.size() - 1); // Pop the current node off the path history since we are done with it
        visiting.remove(node); // Remove from the active recursion tracker
        visited.add(node); // Permanently mark this node as safe and cycle-free
        
        return List.of(); // Return empty list indicating this branch is totally clean
    }

    // Standalone main method to act as our native testing framework without JUnit
    public static void main(String[] args) { // Execution entry point
        
        // Test Case 1: Healthy Build Graph (No cycles)
        Target t1 = new Target("App", List.of("DB", "UI")); // App depends on DB and UI
        Target t2 = new Target("UI", List.of("API")); // UI depends on API
        Target t3 = new Target("DB", List.of()); // DB depends on nothing
        Target t4 = new Target("API", List.of()); // API depends on nothing
        test("Healthy Graph", List.of(t1, t2, t3, t4), List.of()); // Expect an empty list since it is a valid DAG (Directed Acyclic Graph)
        
        // Test Case 2: Simple Cycle (A -> B -> A)
        Target t5 = new Target("ServiceA", List.of("ServiceB")); // A depends on B
        Target t6 = new Target("ServiceB", List.of("ServiceA")); // B depends on A (Cycle!)
        test("Simple Cycle", List.of(t5, t6), List.of("ServiceA", "ServiceB", "ServiceA")); // Expect exact cycle loop returned
        
        // Test Case 3: Deep Cycle embedded in a larger graph (App -> UI -> Auth -> DB -> UI)
        Target t7 = new Target("App", List.of("UI")); // App depends on UI
        Target t8 = new Target("UI", List.of("Auth")); // UI depends on Auth
        Target t9 = new Target("Auth", List.of("DB")); // Auth depends on DB
        Target t10 = new Target("DB", List.of("UI")); // DB depends back on UI (Cycle here!)
        test("Deep Cycle", List.of(t7, t8, t9, t10), List.of("UI", "Auth", "DB", "UI")); // We only want the cycle part, omitting "App"
        
        // Test Case 4: Self-loop (A target depends on itself)
        Target t11 = new Target("Utils", List.of("Utils")); // Accidental self-reference
        test("Self Loop", List.of(t11), List.of("Utils", "Utils")); // Expect a single hop cycle
        
        // Test Case 5: Large Data Volume Test (100,000 nodes)
        // We create a "wide" graph where a Root node depends on 99,998 separate children to test processing speed.
        // We inject a cycle at the very end to ensure it traverses successfully.
        List<Target> largeData = new ArrayList<>(); // Initialize standard ArrayList for large data injection
        List<String> rootDeps = new ArrayList<>(); // List to hold 99,998 dependencies for the root node
        for (int i = 1; i <= 99_998; i++) { // Loop to generate children
            rootDeps.add("Node" + i); // Add node names to the root dependency list
            largeData.add(new Target("Node" + i, List.of())); // Create actual child targets with no dependencies
        }
        rootDeps.add("BadNode"); // Inject a node that will cause a cycle
        largeData.add(new Target("Root", rootDeps)); // Create the massive root target
        largeData.add(new Target("BadNode", List.of("Root"))); // BadNode depends back on Root, causing the cycle
        
        // Execute Large Data Test
        test("Large Data Load", largeData, List.of("Root", "BadNode", "Root")); // Verify it completes instantly and finds the loop
    }

    // Custom test assertion method to evaluate logic and print PASS/FAIL clearly
    private static void test(String name, List<Target> input, List<String> expected) { // Takes test name, input data, and expected path
        
        List<String> actual = findCycle(input); // Execute core algorithm
        
        boolean passed = actual.equals(expected); // Check if actual extracted cycle precisely matches expected cycle
        
        // Format diagnostic output to the terminal using ternary operator for PASS/FAIL
        System.out.println(name + " -> " + (passed ? "PASS" : "FAIL") + " | Expected: " + expected + ", Actual: " + actual); // Print test result
    }
}