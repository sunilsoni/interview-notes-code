package com.interview.notes.code.year.y2025.march.Nike.test1;

import java.util.*;
/*

### **Academic Schedule**

**Description:**
Consider an academic program that consists of `N` courses. Every course is designated by an integer index from `0` to `N-1`.

Some courses have others as prerequisites. For example, if course `2` is a prerequisite of course `3`, you must finish course `2` before enrolling in course `3`.

You can attend only a single course at a time.
Build a schedule to complete all courses in a **linear sequence** such that all prerequisites for every course are satisfied.

If more than one valid schedule exists, choose the one where courses with smaller indices are finished first.

**Input:**
- The first line contains a positive integer `N` representing the number of courses in the academic program.
- Each additional line describes the prerequisites of a given course as a space-delimited set of indices.

**Note:**
- Each set of prerequisites has at least two indices.
- The **first index** in the set denotes the course for which a prerequisite exists.
- All other indices in the line designate which courses are required for the first.

**Example Input:**
```
4
1 0
2 0
3 1 2
```

**Explanation:**
- Course 1 requires course 0
- Course 2 requires course 0
- Course 3 requires courses 1 and 2

 */
public class AcademicSchedule {
    public static List<Integer> buildSchedule(int n, List<List<Integer>> prerequisites) {
        // Build adjacency list representation of the graph
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        
        // Track in-degree (number of prerequisites) for each course
        int[] inDegree = new int[n];
        
        // Process prerequisites
        for (List<Integer> prereq : prerequisites) {
            int course = prereq.get(0);
            for (int i = 1; i < prereq.size(); i++) {
                int prerequisite = prereq.get(i);
                graph.get(prerequisite).add(course);
                inDegree[course]++;
            }
        }
        
        // Priority queue to ensure courses with smaller indices are processed first
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        
        // Add all courses with no prerequisites to the queue
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        List<Integer> schedule = new ArrayList<>();
        
        // Process courses in topological order
        while (!queue.isEmpty()) {
            int course = queue.poll();
            schedule.add(course);
            
            // Update in-degrees of adjacent courses
            for (int nextCourse : graph.get(course)) {
                inDegree[nextCourse]--;
                if (inDegree[nextCourse] == 0) {
                    queue.offer(nextCourse);
                }
            }
        }
        
        // Check if all courses can be scheduled
        if (schedule.size() != n) {
            return null; // Cycle detected, no valid schedule
        }
        
        return schedule;
    }
    
    public static void main(String[] args) {
        // Test cases
        testCase1();
        testCase2();
        testCase3();
        testCase4();
        testCaseLarge();
    }
    
    private static void testCase1() {
        System.out.println("Test Case 1: Example from problem statement");
        int n = 5;
        List<List<Integer>> prerequisites = new ArrayList<>();
        prerequisites.add(Arrays.asList(1, 0));
        prerequisites.add(Arrays.asList(2, 0));
        prerequisites.add(Arrays.asList(3, 1, 2));
        
        List<Integer> result = buildSchedule(n, prerequisites);
        List<Integer> expected = Arrays.asList(0, 1, 2, 3);
        
        if (result != null && result.equals(expected)) {
            System.out.println("PASS: " + result);
        } else {
            System.out.println("FAIL: Expected " + expected + ", got " + result);
        }
    }
    
    private static void testCase2() {
        System.out.println("\nTest Case 2: No prerequisites");
        int n = 3;
        List<List<Integer>> prerequisites = new ArrayList<>();
        
        List<Integer> result = buildSchedule(n, prerequisites);
        List<Integer> expected = Arrays.asList(0, 1, 2);
        
        if (result != null && result.equals(expected)) {
            System.out.println("PASS: " + result);
        } else {
            System.out.println("FAIL: Expected " + expected + ", got " + result);
        }
    }
    
    private static void testCase3() {
        System.out.println("\nTest Case 3: Cyclic dependencies");
        int n = 3;
        List<List<Integer>> prerequisites = new ArrayList<>();
        prerequisites.add(Arrays.asList(0, 1));
        prerequisites.add(Arrays.asList(1, 2));
        prerequisites.add(Arrays.asList(2, 0));
        
        List<Integer> result = buildSchedule(n, prerequisites);
        
        if (result == null) {
            System.out.println("PASS: Correctly detected cycle");
        } else {
            System.out.println("FAIL: Expected null (cycle detection), got " + result);
        }
    }
    
    private static void testCase4() {
        System.out.println("\nTest Case 4: Multiple valid schedules");
        int n = 5;
        List<List<Integer>> prerequisites = new ArrayList<>();
        prerequisites.add(Arrays.asList(1, 0));
        prerequisites.add(Arrays.asList(2, 0));
        prerequisites.add(Arrays.asList(4, 3));
        
        List<Integer> result = buildSchedule(n, prerequisites);
        List<Integer> expected = Arrays.asList(0, 1, 2, 3, 4);
        
        if (result != null && result.equals(expected)) {
            System.out.println("PASS: " + result);
        } else {
            System.out.println("FAIL: Expected " + expected + ", got " + result);
        }
    }
    
    private static void testCaseLarge() {
        System.out.println("\nTest Case 5: Large input");
        int n = 1000;
        List<List<Integer>> prerequisites = new ArrayList<>();
        
        // Create a linear chain of prerequisites: 0 -> 1 -> 2 -> ... -> 999
        for (int i = 1; i < n; i++) {
            prerequisites.add(Arrays.asList(i, i-1));
        }
        
        long startTime = System.currentTimeMillis();
        List<Integer> result = buildSchedule(n, prerequisites);
        long endTime = System.currentTimeMillis();
        
        boolean isCorrect = true;
        if (result != null && result.size() == n) {
            for (int i = 0; i < n; i++) {
                if (result.get(i) != i) {
                    isCorrect = false;
                    break;
                }
            }
        } else {
            isCorrect = false;
        }
        
        if (isCorrect) {
            System.out.println("PASS: Processed " + n + " courses in " + (endTime - startTime) + "ms");
        } else {
            System.out.println("FAIL: Incorrect result for large input");
        }
    }
}