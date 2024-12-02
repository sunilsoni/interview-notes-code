package com.interview.notes.code.year.y2024.july24.test8;

import java.util.ArrayList;
import java.util.List;

/*
There are a total of n courses you have to take, labeled from o to n - 1. You are
given an array prerequisites where prerequisites[7] = lai, bil indicates that you must
take course bi first if you want to take course ai.
 For example, the pair [0, 1], indicates that to take course O you have to first take
course 1.
 Return true if you can finish all courses. Otherwise, return false.
  Example 1:
 Input: n= 2, prerequisites = [[1, 0]]
 Output: true
 Explanation: There are a total of 2 courses to take.
 I To take course 1 you should have finished course 0. So it is possible.
 Example 2:
  Input: n = 2, prerequisites = [[1, 0], [0, 11]
  Output: false
  Explanation: There are a total of 2 courses to take.
  To take course 1 you should have finished course O, and to take course O you should
also have finished course 1. So it is impossible.

 */
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
