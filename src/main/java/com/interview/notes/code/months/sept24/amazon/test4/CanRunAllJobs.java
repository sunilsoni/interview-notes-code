package com.interview.notes.code.months.sept24.amazon.test4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CanRunAllJobs {
    public static void main(String[] args) {
        // Test Case 1
        int n1 = 2;
        int[][] prerequisites1 = {{1, 0}};
        boolean result1 = canRunAllJobs(n1, prerequisites1);
        System.out.println("Test Case 1: " + (result1 == true ? "PASS" : "FAIL"));

        // Test Case 2
        int n2 = 2;
        int[][] prerequisites2 = {{1, 0}, {0, 1}};
        boolean result2 = canRunAllJobs(n2, prerequisites2);
        System.out.println("Test Case 2: " + (result2 == false ? "PASS" : "FAIL"));

        // Test Case 3
        int n3 = 4;
        int[][] prerequisites3 = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        boolean result3 = canRunAllJobs(n3, prerequisites3);
        System.out.println("Test Case 3: " + (result3 == true ? "PASS" : "FAIL"));

        // Additional Test Case: Empty prerequisites
        int n4 = 3;
        int[][] prerequisites4 = {};
        boolean result4 = canRunAllJobs(n4, prerequisites4);
        System.out.println("Test Case 4 (Empty prerequisites): " + (result4 == true ? "PASS" : "FAIL"));

        // Additional Test Case: Single job
        int n5 = 1;
        int[][] prerequisites5 = {};
        boolean result5 = canRunAllJobs(n5, prerequisites5);
        System.out.println("Test Case 5 (Single job): " + (result5 == true ? "PASS" : "FAIL"));
    }

    public static boolean canRunAllJobs(int n, int[][] prerequisites) {
        // Create an adjacency list to represent the graph
        List<List<Integer>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // Build the graph and calculate in-degrees
        int[] inDegree = new int[n];
        for (int[] prereq : prerequisites) {
            int from = prereq[1];
            int to = prereq[0];
            graph.get(from).add(to);
            inDegree[to]++;
        }

        // Initialize queue with nodes having 0 in-degree
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        // Perform topological sort
        int completedJobs = 0;
        while (!queue.isEmpty()) {
            int job = queue.poll();
            completedJobs++;

            for (int neighbor : graph.get(job)) {
                if (--inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // If all jobs are completed, return true; otherwise, false
        return completedJobs == n;
    }
}
