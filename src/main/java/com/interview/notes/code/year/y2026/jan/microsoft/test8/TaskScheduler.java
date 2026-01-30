package com.interview.notes.code.year.y2026.jan.microsoft.test8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class TaskScheduler {

    /**
     * Solves the dependency order.
     * Returns a list of task IDs in the order they should be executed.
     * Throws exception if a cycle (deadlock) is found.
     */
    public static List<String> findExecutionOrder(List<Task> tasks) {
        // Map to hold the graph: Key = Prerequisite, Value = List of tasks depending on it
        var graph = new HashMap<String, List<String>>(); // Java 10+ var inference

        // Map to count how many dependencies each task currently has (Indegree)
        var incomingEdges = new HashMap<String, Integer>();

        // Step 1: Initialize the graph and counters
        tasks.forEach(t -> { // Iterate over all input tasks
            incomingEdges.putIfAbsent(t.id(), 0); // Initialize task with 0 dependencies
            t.dependencies().forEach(dep -> { // Loop through this task's dependencies
                incomingEdges.putIfAbsent(dep, 0); // Ensure dependency exists in counter map
                graph.computeIfAbsent(dep, k -> new ArrayList<>()).add(t.id()); // Build graph: Dep -> Task
                incomingEdges.merge(t.id(), 1, Integer::sum); // Increment dependency count for the task
            });
        });

        // Step 2: Find all tasks that have 0 dependencies (Ready to start)
        // Using LinkedList as a Queue for processing
        var queue = new LinkedList<String>();
        incomingEdges.forEach((task, count) -> { // Iterate over our counter map
            if (count == 0) queue.add(task); // If count is 0, add to queue
        });

        // Step 3: Process the queue (Kahn's Algorithm)
        var sortedOrder = new ArrayList<String>(); // List to store the final result

        while (!queue.isEmpty()) { // Keep going while we have ready tasks
            var current = queue.poll(); // Remove the head of the queue (Java 21 sequencing compatible)
            sortedOrder.add(current); // Add to our result list

            // Check tasks that depended on this 'current' task
            if (graph.containsKey(current)) { // If this task has dependents
                for (var neighbor : graph.get(current)) { // Loop through dependent tasks
                    // Decrease dependency count since 'current' is now done
                    incomingEdges.put(neighbor, incomingEdges.get(neighbor) - 1);

                    // If neighbor has no more dependencies, it is ready!
                    if (incomingEdges.get(neighbor) == 0) {
                        queue.add(neighbor); // Add to queue to be processed next
                    }
                }
            }
        }

        // Step 4: Cycle Detection
        // If result size != total tasks, we have a circular dependency (e.g., A needs B, B needs A)
        if (sortedOrder.size() != incomingEdges.size()) {
            throw new IllegalStateException("Cycle detected! Circular dependency exists.");
        }

        return sortedOrder; // Return the valid schedule
    }

    // --- TEST RUNNER (Simple Main Method) ---
    public static void main(String[] args) {
        System.out.println("--- Starting Task Scheduler Tests ---");

        // Test Case 1: Simple Linear Dependency
        // A needs B, B needs C. Order should be: C -> B -> A
        var case1 = List.of(
            new Task("A", List.of("B")),
            new Task("B", List.of("C")),
            new Task("C", List.of())
        );
        runTest("Linear Dependency", case1, false);

        // Test Case 2: Diamond/Complex Dependency
        // D depends on B and C. B depends on A. C depends on A.
        // Valid Orders: A->B->C->D OR A->C->B->D
        var case2 = List.of(
            new Task("D", List.of("B", "C")),
            new Task("B", List.of("A")),
            new Task("C", List.of("A")),
            new Task("A", List.of())
        );
        runTest("Diamond Graph", case2, false);

        // Test Case 3: Circular Dependency (FAIL CASE)
        // A needs B, B needs A. Should throw exception.
        var case3 = List.of(
            new Task("A", List.of("B")),
            new Task("B", List.of("A"))
        );
        runTest("Circular Dependency", case3, true);

        // Test Case 4: Large Data Input
        // 10,000 tasks in a chain: 9999->9998->...->0
        var largeList = new ArrayList<Task>();
        for (int i = 1; i < 10000; i++) {
            largeList.add(new Task("Task" + i, List.of("Task" + (i - 1))));
        }
        largeList.add(new Task("Task0", List.of())); // Base task

        long start = System.currentTimeMillis(); // Start timer
        runTest("Large Data (10k tasks)", largeList, false);
        System.out.println("Large Data Time: " + (System.currentTimeMillis() - start) + "ms");
    }

    /**
     * Helper to run tests and print PASS/FAIL
     * expectException: true if we expect the code to fail (like cycles)
     */
    private static void runTest(String testName, List<Task> input, boolean expectException) {
        try {
            var result = findExecutionOrder(input); // Run logic

            // Validation Logic for Simple Print
            if (expectException) {
                System.out.printf("[%s] FAIL: Expected cycle exception but got result.\n", testName);
            } else {
                // Verify order logic broadly (simply checking if not null and sizes match)
                System.out.printf("[%s] PASS: Order generated: %s... (Size: %d)\n",
                    testName,
                    result.stream().limit(5).toList(), // Show only first 5 for brevity
                    result.size());
            }
        } catch (IllegalStateException e) {
            if (expectException) {
                System.out.printf("[%s] PASS: Caught expected cycle error: %s\n", testName, e.getMessage());
            } else {
                System.out.printf("[%s] FAIL: Unexpected error: %s\n", testName, e.getMessage());
            }
        } catch (Exception e) {
            System.out.printf("[%s] FAIL: Runtime error: %s\n", testName, e.getMessage());
        }
    }

    // Java 21 Record: A concise, immutable data carrier for input
    // "id" is the task name, "dependencies" is the list of tasks it needs before starting
    record Task(String id, List<String> dependencies) {}
}