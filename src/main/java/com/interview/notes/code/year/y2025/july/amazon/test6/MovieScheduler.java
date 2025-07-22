package com.interview.notes.code.year.y2025.july.amazon.test6;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MovieScheduler {         // class containing our solution

    // Main method to run several test cases and print PASS/FAIL
    public static void main(String[] args) {
        MovieScheduler scheduler = new MovieScheduler();       // create solution instance

        // Test 1: simple chain → should succeed
        int n1 = 4;
        int[][] movies1 = {{1, 0}, {2, 1}, {3, 0}};
        printTestResult("Test1", scheduler.canFinish(n1, movies1), true);

        // Test 2: direct cycle 0↔1 → should fail
        int n2 = 2;
        int[][] movies2 = {{1, 0}, {0, 1}};
        printTestResult("Test2", scheduler.canFinish(n2, movies2), false);

        // Test 3: no prerequisites → always succeed
        int n3 = 5;
        int[][] movies3 = {};
        printTestResult("Test3", scheduler.canFinish(n3, movies3), true);

        // Test 4: self-dependency → fail
        int n4 = 1;
        int[][] movies4 = {{0, 0}};
        printTestResult("Test4", scheduler.canFinish(n4, movies4), false);

        // Test 5: large linear chain of 10 000 movies → succeed
        int n5 = 10_000;
        int[][] movies5 = IntStream.range(1, n5)                      // build chain 1←0, 2←1, …
                .mapToObj(i -> new int[]{i, i - 1})
                .toArray(int[][]::new);
        printTestResult("Test5", scheduler.canFinish(n5, movies5), true);

        // Test 6: same chain plus final link creating a cycle → fail
        int[][] movies6 = Stream.concat(
                        IntStream.range(1, n5)
                                .mapToObj(i -> new int[]{i, i - 1}),
                        Stream.of(new int[]{0, n5 - 1})             // cycle back to 0
                )
                .toArray(int[][]::new);
        printTestResult("Test6", scheduler.canFinish(n5, movies6), false);
    }

    // Helper to print whether each test passes
    private static void printTestResult(String testName, boolean result, boolean expected) {
        if (result == expected) {                                // compare actual vs expected
            System.out.println(testName + " PASS");              // success case
        } else {
            System.out.println(testName + " FAIL");              // failure case
        }
    }

    // Method to check if we can finish all movies given prerequisites
    public boolean canFinish(int n, int[][] movies) { // n = total movies, movies = prereq pairs
        // Build adjacency list: for each movie, store list of movies that depend on it
        List<List<Integer>> graph = new ArrayList<>();         // graph.get(i) → list of dependents
        IntStream.range(0, n).forEach(i -> graph.add(new ArrayList<>())); // initialize n empty lists

        // Array to hold in-degree (number of prerequisites) for each movie
        int[] inDegree = new int[n];                           // default all zeros

        // Populate graph and in-degree array from prerequisites
        for (int[] pre : movies) {                             // for each [x, y]
            int x = pre[0];                                     // movie to watch
            int y = pre[1];                                     // required movie
            graph.get(y).add(x);                                // add edge y → x
            inDegree[x]++;                                      // x has one more prerequisite
        }

        // Queue for BFS (movies ready to watch, i.e., inDegree == 0)
        Queue<Integer> queue = new LinkedList<>();             // FIFO queue
        IntStream.range(0, n)                                  // for each movie index
                .filter(i -> inDegree[i] == 0)                // pick those with no prereqs
                .forEach(queue::offer);                       // enqueue them

        int count = 0;                                         // count of movies we can schedule

        // Process the queue in topological order
        while (!queue.isEmpty()) {                             // while there’s a movie ready
            int curr = queue.poll();                           // take one movie
            count++;                                           // we can watch it
            for (int next : graph.get(curr)) {                 // for each dependent movie
                inDegree[next]--;                              // remove the dependency
                if (inDegree[next] == 0) {                     // if no more prereqs
                    queue.offer(next);                         // ready to watch
                }
            }
        }

        // If we scheduled all n movies, return true; otherwise a cycle exists
        return count == n;
    }
}