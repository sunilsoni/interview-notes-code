package com.interview.notes.code.year.y2025.march.Nike.test1;

import java.util.*;

public class CourseScheduler {

    /**
     * Topological sort using Kahn's Algorithm + PriorityQueue for smallest-first ties.
     *
     * @param N          Number of courses
     * @param prereqList Each line: first int is course, following ints are prerequisites
     * @return A list of courses in the order they should be taken, or empty if a cycle is detected
     */
    public static List<Integer> topologicalSort(int N, List<List<Integer>> prereqList) {
        // Build adjacency list and in-degree array
        List<List<Integer>> adjList = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            adjList.add(new ArrayList<>());
        }
        int[] inDegree = new int[N];

        // Fill adjacency list
        // Each sub-list: [course, p1, p2, ...]
        // Means p1->course, p2->course, ...
        for (List<Integer> line : prereqList) {
            int course = line.get(0);
            for (int i = 1; i < line.size(); i++) {
                int prereq = line.get(i);
                adjList.get(prereq).add(course);
                inDegree[course]++;
            }
        }

        // PriorityQueue to ensure smallest index first
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        // Add all courses with inDegree == 0
        for (int i = 0; i < N; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int current = queue.poll();
            result.add(current);

            // Reduce in-degree for all courses dependent on current
            for (int dependentCourse : adjList.get(current)) {
                inDegree[dependentCourse]--;
                if (inDegree[dependentCourse] == 0) {
                    queue.offer(dependentCourse);
                }
            }
        }

        // If we couldn't schedule all courses, return empty or handle error
        if (result.size() < N) {
            // In a real scenario, you might throw an exception or print an error.
            // We'll just return an empty list here to indicate an issue.
            return new ArrayList<>();
        }
        return result;
    }

    /**
     * Simple test runner in the main method (NO JUnit).
     */
    public static void main(String[] args) {
        // We will store tests in a list, each containing:
        // 1) N
        // 2) prereqList (each line as List<Integer>)
        // 3) expected result (List<Integer>)
        // For large data tests, you can build big lists similarly.

        List<TestCase> testCases = new ArrayList<>();

        // TestCase 1: Example from the prompt
        // N=4
        // 1 0 => course=1, prereq=0
        // 2 0 => course=2, prereq=0
        // 3 1 2 => course=3, prereq=1 & 2
        // Expected order: [0, 1, 2, 3] (smallest index first, valid topological order)
        testCases.add(new TestCase(
                4,
                Arrays.asList(
                        Arrays.asList(1, 0),
                        Arrays.asList(2, 0),
                        Arrays.asList(3, 1, 2)
                ),
                Arrays.asList(0, 1, 2, 3)
        ));

        // TestCase 2: No prerequisites
        // N=3
        // No lines describing prerequisites => all inDegree=0
        // Expected order: [0, 1, 2]
        testCases.add(new TestCase(
                3,
                Collections.emptyList(),
                Arrays.asList(0, 1, 2)
        ));

        // TestCase 3: A single chain
        // N=4
        // 1 0 => 0 -> 1
        // 2 1 => 1 -> 2
        // 3 2 => 2 -> 3
        // Expected order: [0, 1, 2, 3]
        testCases.add(new TestCase(
                4,
                Arrays.asList(
                        Arrays.asList(1, 0),
                        Arrays.asList(2, 1),
                        Arrays.asList(3, 2)
                ),
                Arrays.asList(0, 1, 2, 3)
        ));

        // TestCase 4: Potentially ambiguous order but we want smallest first
        // N=3
        // 1 0 => 0 -> 1
        // 2 0 => 0 -> 2
        // No direct ordering between 1 and 2,
        // Both 1 and 2 depend on 0,
        // So after 0, we can have [1,2] or [2,1].
        // But we want smaller index first => [0, 1, 2]
        testCases.add(new TestCase(
                3,
                Arrays.asList(
                        Arrays.asList(1, 0),
                        Arrays.asList(2, 0)
                ),
                Arrays.asList(0, 1, 2)
        ));

        // TestCase 5: (Optional) Checking a "cycle" scenario if we like
        // For demonstration, let's say there's a cycle (0 -> 1, 1 -> 0).
        // Expected result: we get an empty list from our method.
        // If you want to handle differently, do so.
        testCases.add(new TestCase(
                2,
                Arrays.asList(
                        Arrays.asList(1, 0),
                        Arrays.asList(0, 1) // This creates a cycle 0->1->0
                ),
                Collections.emptyList() // no valid schedule
        ));

        // Run each test
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            List<Integer> result = topologicalSort(tc.N, tc.prereqs);
            boolean pass = result.equals(tc.expectedOrder);
            System.out.println("Test #" + (i + 1)
                    + (pass ? " PASSED" : " FAILED")
                    + " | Output: " + result
                    + " | Expected: " + tc.expectedOrder);
        }

        // You can add more or bigger test cases as needed.
    }

    // Helper class for storing test data
    static class TestCase {
        int N;
        List<List<Integer>> prereqs;
        List<Integer> expectedOrder;

        TestCase(int N, List<List<Integer>> prereqs, List<Integer> expectedOrder) {
            this.N = N;
            this.prereqs = prereqs;
            this.expectedOrder = expectedOrder;
        }
    }
}
