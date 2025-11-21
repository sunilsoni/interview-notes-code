package com.interview.notes.code.year.y2025.June.amazon.test17;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RecipeOrder {
    // Returns a valid order of steps or empty list if cycle detected
    public static List<Integer> findOrder(int numSteps, List<int[]> rules) {
        // Build adjacency list for graph
        List<List<Integer>> adj = IntStream.range(0, numSteps)
                .mapToObj(i -> new ArrayList<Integer>()) // create empty list for each step
                .collect(Collectors.toList());
        // inDegree[i] = number of prerequisites for step i
        int[] inDegree = new int[numSteps];

        // Populate graph edges and in-degrees
        for (int[] rule : rules) {
            int step = rule[0], pre = rule[1];
            adj.get(pre).add(step);      // edge pre → step
            inDegree[step]++;            // step has one more prerequisite
        }

        // Initialize queue with all steps having no prerequisites
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numSteps; i++) {
            if (inDegree[i] == 0) {     // no prerequisites
                queue.add(i);
            }
        }

        List<Integer> order = new ArrayList<>(); // will store the valid order

        // Process until no available steps
        while (!queue.isEmpty()) {
            int cur = queue.remove();  // take a ready step
            order.add(cur);            // append to result

            // Decrease in-degree of neighbors; enqueue if they become ready
            for (int nei : adj.get(cur)) {
                inDegree[nei]--;
                if (inDegree[nei] == 0) {
                    queue.add(nei);
                }
            }
        }

        // If we scheduled all steps, return order; else a cycle existed
        return (order.size() == numSteps) ? order : Collections.emptyList();
    }

    // Simple main method to test cases and print PASS/FAIL
    public static void main(String[] args) {
        // List of test cases: {numSteps, rules as list of int[]}, expected output
        List<TestCase> tests = Arrays.asList(
                new TestCase(3, Arrays.asList(new int[]{1, 0}, new int[]{2, 1}), Arrays.asList(0, 1, 2)),
                new TestCase(4, Arrays.asList(new int[]{1, 0}, new int[]{2, 0}, new int[]{3, 1}), null), // any valid topol. ordering
                new TestCase(2, Arrays.asList(new int[]{0, 1}, new int[]{1, 0}), Collections.emptyList()), // cycle
                new TestCase(1, Collections.emptyList(), List.of(0))
        );

        for (int i = 0; i < tests.size(); i++) {
            TestCase t = tests.get(i);
            List<Integer> result = findOrder(t.n, t.rules);

            boolean pass;
            if (t.expected != null) {
                pass = result.equals(t.expected);
            } else {
                // For null-expected, just check result is valid topo order
                pass = isValidOrder(result, t.n, t.rules);
            }

            System.out.println("Test " + (i + 1) + ": " + (pass ? "PASS" : "FAIL") +
                    " -> " + result);
        }

        // Example of large input performance test (optional)
        int N = 100_000;
        List<int[]> bigRules = new ArrayList<>();
        for (int i = 1; i < N; i++) {
            bigRules.add(new int[]{i, i - 1});
        }
        long start = System.currentTimeMillis();
        List<Integer> bigOrder = findOrder(N, bigRules);
        long duration = System.currentTimeMillis() - start;
        System.out.println("Large N=" + N + " in " + duration + "ms, size=" + bigOrder.size());
    }

    // Helper to check a topo order is valid
    private static boolean isValidOrder(List<Integer> order, int n, List<int[]> rules) {
        if (order.size() != n) return false;
        int[] pos = new int[n];
        for (int i = 0; i < n; i++) pos[order.get(i)] = i;
        for (int[] r : rules) {
            if (pos[r[1]] > pos[r[0]]) return false; // prerequisite comes after dependent
        }
        return true;
    }

    // Simple container for test cases
    static class TestCase {
        int n;
        List<int[]> rules;
        List<Integer> expected; // null means any valid order

        TestCase(int n, List<int[]> rules, List<Integer> exp) {
            this.n = n;
            this.rules = rules;
            this.expected = exp;
        }
    }
}