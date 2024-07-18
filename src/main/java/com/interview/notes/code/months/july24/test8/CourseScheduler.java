package com.interview.notes.code.months.july24.test8;

import java.util.ArrayList;
import java.util.List;

public class CourseScheduler {
    public static void main(String[] args) {
        CourseScheduler scheduler = new CourseScheduler();
        System.out.println(scheduler.canFinish(2, new int[][]{{1, 0}})); // true
        System.out.println(scheduler.canFinish(2, new int[][]{{1, 0}, {0, 1}})); // false
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Create a graph from the prerequisites
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] pre : prerequisites) {
            graph.get(pre[1]).add(pre[0]);
        }

        // States: 0 = unvisited, 1 = visiting, 2 = visited
        int[] visited = new int[numCourses];

        // Check for cycles in the graph
        for (int i = 0; i < numCourses; i++) {
            if (hasCycle(i, graph, visited)) {
                return false;
            }
        }
        return true;
    }

    private boolean hasCycle(int course, List<List<Integer>> graph, int[] visited) {
        if (visited[course] == 1) return true; // cycle detected
        if (visited[course] == 2) return false; // already processed

        visited[course] = 1; // mark as visiting
        for (int neighbor : graph.get(course)) {
            if (hasCycle(neighbor, graph, visited)) {
                return true;
            }
        }
        visited[course] = 2; // mark as visited
        return false;
    }
}
