package com.interview.notes.code.year.y2025.july.google.test7;// import utility classes for data structures and streams
import java.util.*;
import java.util.stream.*;
/*


### 🚕 Problem Statement: Optimal Cab Cost for Alice and Bob

A state has **N different cities**, denoted by **uppercase letters**.
Alice is in city **A** and Bob is at city **B**.
They are travelling to meet their friend **David** in city **D**.
These cities are interconnected by roads.

Alice and Bob each take cabs to reach their destination. However, they want to **minimize the total cab costs**.
If both of them **reach the same city**, they can take a **common cab together** to continue to city **D**.

---

### 🗺️ City Graph:

```
A - F
|   \
C - E - D
|
B - G - H
```

---

### 🧠 Goal:

Find the **minimum total cab cost** for Alice and Bob to reach **city D**, either separately or by meeting at an intermediate city and sharing a cab from there.

Assume:

* Each edge between cities represents a cab route of **cost = 1**.
* If they take a cab together from a common city, that leg is counted **once**.

---



 */
// main class to solve the cab cost problem and run tests
public class OptimalCabCost {

    // class to hold data for each test case
    static class TestCase {
        String name;           // name of the test case
        int n;                 // number of nodes (cities)
        List<int[]> edges;     // list of edges as pairs of node indices
        int startA;            // Alice's starting city index
        int startB;            // Bob's starting city index
        int destD;             // David's destination city index
        int expected;          // expected minimum cost result

        // constructor to initialize all test case fields
        TestCase(String name, int n, List<int[]> edges, int startA, int startB, int destD, int expected) {
            this.name = name;       // set test case name
            this.n = n;             // set number of cities
            this.edges = edges;     // set edges list
            this.startA = startA;   // set Alice's city
            this.startB = startB;   // set Bob's city
            this.destD = destD;     // set David's city
            this.expected = expected; // set expected result
        }
    }

    // method to perform BFS and compute shortest distances from a source node
    public static int[] bfs(List<List<Integer>> adj, int src) {
        int n = adj.size();                                            // total number of nodes
        int[] dist = new int[n];                                       // array to store distances
        Arrays.fill(dist, Integer.MAX_VALUE / 2);                      // initialize distances to a large value
        Queue<Integer> queue = new LinkedList<>();                     // queue for BFS traversal
        dist[src] = 0;                                                 // distance to source is zero
        queue.offer(src);                                              // enqueue the source node
        while (!queue.isEmpty()) {                                     // loop until queue is empty
            int u = queue.poll();                                     // dequeue next node
            for (int v : adj.get(u)) {                                // examine each neighbor of u
                if (dist[v] > dist[u] + 1) {                          // if a shorter path is found
                    dist[v] = dist[u] + 1;                           // update distance for v
                    queue.offer(v);                                   // enqueue v for further exploration
                }
            }
        }
        return dist;                                                  // return the array of distances
    }

    // method to compute minimum total cab cost for Alice and Bob
    public static int minCabCost(int n, List<int[]> edges, int startA, int startB, int destD) {
        List<List<Integer>> adj = new ArrayList<>();                   // adjacency list representation
        for (int i = 0; i < n; i++) {                                  // initialize neighbor lists
            adj.add(new ArrayList<>());
        }
        for (int[] e : edges) {                                        // build graph edges
            int u = e[0];                                              // first city of edge
            int v = e[1];                                              // second city of edge
            adj.get(u).add(v);                                        // add connection u->v
            adj.get(v).add(u);                                        // add connection v->u (undirected)
        }
        int[] distA = bfs(adj, startA);                                // distances from Alice's city
        int[] distB = bfs(adj, startB);                                // distances from Bob's city
        int[] distD = bfs(adj, destD);                                // distances from David's city
        int max = Integer.MAX_VALUE / 2;                               // large sentinel value
        int minCost = distA[destD] + distB[destD];                     // cost if they travel separately
        for (int i = 0; i < n; i++) {                                  // consider each city as meeting point
            if (distA[i] < max && distB[i] < max && distD[i] < max) { // ensure all are reachable
                int cost = distA[i] + distB[i] + distD[i];            // total cost via meeting here
                if (cost < minCost) {                                // update if better
                    minCost = cost;
                }
            }
        }
        return minCost;                                                // return the minimum cost found
    }

    // main method to execute test cases and print pass/fail results
    public static void main(String[] args) {
        List<TestCase> testCases = Arrays.asList(                     // list of predefined test cases
            new TestCase("ExampleGraph", 8, Arrays.asList(
                new int[]{0,5}, new int[]{0,2}, new int[]{2,4}, new int[]{4,3},
                new int[]{2,1}, new int[]{1,6}, new int[]{6,7}, new int[]{7,3}
            ), 0, 1, 3, 4),                                           // expected 4 for the example
            new TestCase("TwoNodesSameStart", 2, Arrays.asList(
                new int[]{0,1}
            ), 0, 0, 1, 1),                                          // expected 1 when both start together
            new TestCase("SeparatePaths", 3, Arrays.asList(
                new int[]{0,2}, new int[]{1,2}
            ), 0, 1, 2, 2),                                          // expected 2 when no sharing helps
            new TestCase("SingleNode", 1, Collections.emptyList(), 0, 0, 0, 0), // trivial case
            new TestCase("LargeLineGraph", 10000,                      // large input performance test
                IntStream.range(0, 9999).mapToObj(i -> new int[]{i, i+1}).collect(Collectors.toList()),
                0, 0, 9999, 9999                                      // expected cost 9999 on a line
            )
        );                                                          // end of test case definitions

        for (TestCase tc : testCases) {                              // iterate through test cases
            long start = System.currentTimeMillis();                 // record start time
            int result = minCabCost(tc.n, tc.edges, tc.startA, tc.startB, tc.destD); // compute result
            long duration = System.currentTimeMillis() - start;      // calculate elapsed time
            if (result == tc.expected) {                             // check against expected value
                System.out.println(tc.name + ": PASS in " + duration + "ms"); // print pass message
            } else {                                                 // if not matching
                System.out.println(tc.name + ": FAIL (expected " + tc.expected + ", got " + result + ")"); // print fail message
            }
        }
    }
}