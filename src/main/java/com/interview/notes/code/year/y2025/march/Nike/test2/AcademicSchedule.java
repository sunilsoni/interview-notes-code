package com.interview.notes.code.year.y2025.march.Nike.test2;

import java.util.*;
import java.util.stream.*;

public class AcademicSchedule {

    // Method to perform topological sort for course scheduling
    public static List<Integer> findCourseSchedule(int N, List<List<Integer>> prerequisites) {
        // Build graph and in-degree array
        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[N];
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }
        
        // Process each prerequisite line.
        for (List<Integer> pre : prerequisites) {
            // First element is the course; the rest are prerequisites.
            int course = pre.get(0);
            for (int i = 1; i < pre.size(); i++) {
                int prereq = pre.get(i);
                // Create an edge from prereq to course.
                graph.get(prereq).add(course);
                inDegree[course]++;
            }
        }
        
        // Use a priority queue to always select the smallest index course available
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        List<Integer> schedule = new ArrayList<>();
        while (!queue.isEmpty()) {
            int current = queue.poll();
            schedule.add(current);
            // Process all neighbors of current course.
            for (int neighbor : graph.get(current)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        
        // If schedule size is less than N, there is a cycle (invalid input)
        if (schedule.size() != N) {
            throw new IllegalArgumentException("There is a cycle in the prerequisites, schedule not possible.");
        }
        return schedule;
    }

    // Testing method using a simple main method
    public static void main(String[] args) {
        // List of test cases
        List<TestCase> tests = Arrays.asList(
            new TestCase(4, Arrays.asList(
                Arrays.asList(1, 0),
                Arrays.asList(2, 0),
                Arrays.asList(3, 1, 2)
            ), Arrays.asList(0, 1, 2, 3)),
            // Edge case: single course, no prerequisites
            new TestCase(1, new ArrayList<>(), Arrays.asList(0)),
            // Additional test: multiple valid orders, smallest index order required
            new TestCase(6, Arrays.asList(
                Arrays.asList(1, 0),
                Arrays.asList(2, 0),
                Arrays.asList(3, 1),
                Arrays.asList(4, 1, 2),
                Arrays.asList(5, 3, 4)
            ), Arrays.asList(0, 1, 2, 3, 4, 5))
        );
        
        boolean allPassed = true;
        for (int i = 0; i < tests.size(); i++) {
            TestCase tc = tests.get(i);
            try {
                List<Integer> result = findCourseSchedule(tc.N, tc.prerequisites);
                if (!result.equals(tc.expected)) {
                    allPassed = false;
                    System.out.println("Test " + (i+1) + " FAILED: Expected " + tc.expected + ", got " + result);
                } else {
                    System.out.println("Test " + (i+1) + " PASSED");
                }
            } catch (Exception e) {
                allPassed = false;
                System.out.println("Test " + (i+1) + " FAILED with exception: " + e.getMessage());
            }
        }
        
        // Large data input test
        try {
            int largeN = 100000;
            // Generate a large test case with a simple chain: 0 -> 1 -> 2 -> ... -> largeN-1
            List<List<Integer>> largePrerequisites = IntStream.range(1, largeN)
                    .mapToObj(i -> Arrays.asList(i, i - 1))
                    .collect(Collectors.toList());
            long startTime = System.currentTimeMillis();
            List<Integer> largeResult = findCourseSchedule(largeN, largePrerequisites);
            long endTime = System.currentTimeMillis();
            if (largeResult.size() == largeN && largeResult.get(0) == 0 && largeResult.get(largeN - 1) == largeN - 1) {
                System.out.println("Large input test PASSED in " + (endTime - startTime) + " ms");
            } else {
                System.out.println("Large input test FAILED");
            }
        } catch (Exception e) {
            System.out.println("Large input test FAILED with exception: " + e.getMessage());
        }
        
        if (allPassed) {
            System.out.println("All tests PASSED");
        } else {
            System.out.println("Some tests FAILED");
        }
    }
}

// Helper class to represent a test case
class TestCase {
    int N;
    List<List<Integer>> prerequisites;
    List<Integer> expected;
    
    TestCase(int N, List<List<Integer>> prerequisites, List<Integer> expected) {
        this.N = N;
        this.prerequisites = prerequisites;
        this.expected = expected;
    }
}
