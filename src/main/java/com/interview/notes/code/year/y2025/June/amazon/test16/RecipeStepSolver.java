package com.interview.notes.code.year.y2025.June.amazon.test16;

import java.util.*;

public class RecipeStepSolver {

    public static List<Integer> findStepSequence(int numSteps, int[][] rules) {
        // Create adjacency list to represent dependencies
        // Key: step number, Value: list of steps that depend on key
        Map<Integer, List<Integer>> graph = new HashMap<>();

        // Track in-degree (number of prerequisites) for each step
        int[] inDegree = new int[numSteps];

        // Initialize the graph with empty lists for all steps
        for (int i = 0; i < numSteps; i++) {
            graph.put(i, new ArrayList<>());
        }

        // Build the dependency graph from rules
        for (int[] rule : rules) {
            int dependent = rule[0];
            int prerequisite = rule[1];
            graph.get(prerequisite).add(dependent);
            inDegree[dependent]++;
        }

        // Queue to track steps with no prerequisites
        Queue<Integer> queue = new LinkedList<>();

        // Add all steps with no prerequisites to queue
        for (int i = 0; i < numSteps; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        // Result list to store the sequence
        List<Integer> result = new ArrayList<>();

        // Process steps in topological order
        while (!queue.isEmpty()) {
            int currentStep = queue.poll();
            result.add(currentStep);

            // Update dependencies for steps that depend on current step
            for (int dependent : graph.get(currentStep)) {
                inDegree[dependent]--;
                if (inDegree[dependent] == 0) {
                    queue.offer(dependent);
                }
            }
        }

        // If we couldn't process all steps, there's a cycle
        return result.size() == numSteps ? result : new ArrayList<>();
    }

    // Test method to verify solution
    public static void main(String[] args) {
        // Test Case 1: Simple linear dependency
        test(3, new int[][]{{1, 0}, {2, 1}}, "Test 1: Simple linear");

        // Test Case 2: No dependencies
        test(3, new int[][]{}, "Test 2: No dependencies");

        // Test Case 3: Circular dependency
        test(2, new int[][]{{1, 0}, {0, 1}}, "Test 3: Circular dependency");

        // Test Case 4: Complex dependencies
        test(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}},
                "Test 4: Complex dependencies");

        // Test Case 5: Large input
        int[][] largeRules = generateLargeInput(1000);
        test(1000, largeRules, "Test 5: Large input");
    }

    // Helper method to run tests
    private static void test(int numSteps, int[][] rules, String testName) {
        List<Integer> result = findStepSequence(numSteps, rules);
        System.out.println(testName);
        System.out.println("Result: " + result);
        System.out.println("Valid: " + validateResult(numSteps, rules, result));
        System.out.println("------------------------");
    }

    // Validate the result
    private static boolean validateResult(int numSteps, int[][] rules,
                                          List<Integer> result) {
        if (result.size() != numSteps) return false;

        // Check if all rules are satisfied
        for (int[] rule : rules) {
            int dependentIdx = result.indexOf(rule[0]);
            int prerequisiteIdx = result.indexOf(rule[1]);
            if (prerequisiteIdx >= dependentIdx) return false;
        }
        return true;
    }

    // Generate large test input
    private static int[][] generateLargeInput(int size) {
        List<int[]> rules = new ArrayList<>();
        for (int i = 0; i < size - 1; i++) {
            rules.add(new int[]{i + 1, i});
        }
        return rules.toArray(new int[0][]);
    }
}
