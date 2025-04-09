package com.interview.notes.code.year.y2025.march.Nike.test2;

import java.util.*;

public class AcademicScheduler {
    public static List<Integer> buildSchedule(int n, List<String> prerequisites) {
        // Create courses with their prerequisites
        Course[] courses = new Course[n];
        for (int i = 0; i < n; i++) {
            courses[i] = new Course(i);
        }

        // Parse prerequisites
        for (String prereq : prerequisites) {
            String[] parts = prereq.trim().split("\\s+");
            int courseId = Integer.parseInt(parts[0]);
            for (int i = 1; i < parts.length; i++) {
                courses[courseId].prerequisites.add(Integer.parseInt(parts[i]));
            }
        }

        // Build schedule using modified topological sort
        List<Integer> schedule = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Set<Integer> processing = new HashSet<>();

        // Process courses in order (to prioritize smaller indices)
        for (int i = 0; i < n; i++) {
            if (!visited.contains(i)) {
                if (!dfs(i, courses, visited, processing, schedule)) {
                    return new ArrayList<>(); // Cycle detected
                }
            }
        }

        Collections.reverse(schedule);
        return schedule;
    }

    private static boolean dfs(int courseId, Course[] courses, Set<Integer> visited,
                               Set<Integer> processing, List<Integer> schedule) {
        if (processing.contains(courseId)) return false; // Cycle detected
        if (visited.contains(courseId)) return true;

        processing.add(courseId);

        // Process prerequisites first
        for (int prereq : courses[courseId].prerequisites) {
            if (!dfs(prereq, courses, visited, processing, schedule)) {
                return false;
            }
        }

        processing.remove(courseId);
        visited.add(courseId);
        schedule.add(courseId);
        return true;
    }

    public static void main(String[] args) {
        // Test cases
        testCase1();
        testCase2();
        testCase3();
        testLargeInput();
    }

    private static void testCase1() {
        int n = 4;
        List<String> prerequisites = Arrays.asList(
                "1 0",
                "2 0",
                "3 1 2"
        );
        List<Integer> result = buildSchedule(n, prerequisites);
        System.out.println("Test Case 1: " +
                (result.equals(Arrays.asList(0, 1, 2, 3)) ? "PASS" : "FAIL"));
    }

    private static void testCase2() {
        // Cycle detection test
        int n = 3;
        List<String> prerequisites = Arrays.asList(
                "1 0",
                "2 1",
                "0 2"
        );
        List<Integer> result = buildSchedule(n, prerequisites);
        System.out.println("Test Case 2 (Cycle): " +
                (result.isEmpty() ? "PASS" : "FAIL"));
    }

    private static void testCase3() {
        // Empty prerequisites
        int n = 3;
        List<String> prerequisites = new ArrayList<>();
        List<Integer> result = buildSchedule(n, prerequisites);
        System.out.println("Test Case 3 (No Prerequisites): " +
                (result.equals(Arrays.asList(0, 1, 2)) ? "PASS" : "FAIL"));
    }

    private static void testLargeInput() {
        // Large input test (1000 courses)
        int n = 1000;
        List<String> prerequisites = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            prerequisites.add(i + " " + (i - 1));
        }
        List<Integer> result = buildSchedule(n, prerequisites);
        System.out.println("Large Input Test: " +
                (result.size() == n ? "PASS" : "FAIL"));
    }

    static class Course {
        int id;
        Set<Integer> prerequisites = new HashSet<>();

        Course(int id) {
            this.id = id;
        }
    }
}
