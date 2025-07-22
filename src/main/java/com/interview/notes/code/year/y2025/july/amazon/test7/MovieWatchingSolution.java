package com.interview.notes.code.year.y2025.july.amazon.test7;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*

### **Problem Statement:**

Let’s assume you want to watch a total of `n` movies, labeled from `0` to `n-1`.

You are given a list called `movies` where each element `movies[i] = [x, y]` **indicates that you must watch movie `y` first if you want to watch movie `x`**.

Return `true` if you can finish watching all the movies, and `false` otherwise.

---

### **Input Example:**

```
n = 4
movies = [[1, 0], [2, 1], [3, 0]]
```

### **Output:**

```
true
```

---
 */
public class MovieWatchingSolution {
    public static boolean canWatchAllMovies(int n, int[][] movies) {
        // Create adjacency list to represent movie dependencies
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // Create in-degree array to track prerequisites for each movie
        int[] inDegree = new int[n];

        // Build the graph and calculate in-degrees
        for (int[] movie : movies) {
            graph.get(movie[1]).add(movie[0]); // Add edge from prerequisite to dependent movie
            inDegree[movie[0]]++; // Increment in-degree for dependent movie
        }

        // Create queue for BFS and add all movies with no prerequisites
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        // Count movies we can watch
        int watchedMovies = 0;

        // Process movies in topological order
        while (!queue.isEmpty()) {
            int currentMovie = queue.poll();
            watchedMovies++;

            // Process all dependent movies
            for (int nextMovie : graph.get(currentMovie)) {
                inDegree[nextMovie]--;
                if (inDegree[nextMovie] == 0) {
                    queue.offer(nextMovie);
                }
            }
        }

        // Return true if we can watch all movies
        return watchedMovies == n;
    }

    public static void main(String[] args) {
        // Test Case 1: Simple valid case
        test(4, new int[][]{{1, 0}, {2, 1}, {3, 0}}, true, "Test Case 1");

        // Test Case 2: Cycle detection
        test(2, new int[][]{{1, 0}, {0, 1}}, false, "Test Case 2");

        // Test Case 3: Empty dependencies
        test(3, new int[][]{}, true, "Test Case 3");

        // Test Case 4: Large input
        int[][] largeInput = generateLargeInput(10000);
        test(10000, largeInput, true, "Large Input Test");
    }

    private static void test(int n, int[][] movies, boolean expectedResult, String testName) {
        boolean result = canWatchAllMovies(n, movies);
        System.out.println(testName + ": " +
                (result == expectedResult ? "PASS" : "FAIL") +
                " (Expected: " + expectedResult + ", Got: " + result + ")");
    }

    private static int[][] generateLargeInput(int n) {
        // Generate a large DAG
        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            edges.add(new int[]{i + 1, i});
        }
        return edges.toArray(new int[0][]);
    }
}
