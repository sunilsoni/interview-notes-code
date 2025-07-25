package com.interview.notes.code.year.y2025.july.google.test8;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OptimalCabCost {

    public static void main(String[] args) {
        // Define several test cases, including a larger chain of 26 cities
        List<TestCase> testCases = Arrays.asList(
                new TestCase(
                        "SimpleChain",
                        Arrays.asList('A', 'B', 'C', 'D'),
                        Arrays.asList("AB", "BC", "CD"),
                        'A', 'B', 'D',
                        3
                ),
                new TestCase(
                        "SimpleStar",
                        Arrays.asList('A', 'B', 'C', 'D'),
                        Arrays.asList("AC", "BC", "CD"),
                        'A', 'B', 'D',
                        3
                ),
                new TestCase(
                        "CompleteGraph",
                        Arrays.asList('A', 'B', 'C', 'D'),
                        Arrays.asList("AB", "AC", "AD", "BC", "BD", "CD"),
                        'A', 'B', 'D',
                        2
                ),
                new TestCase(
                        "LargeChain",
                        // Cities A through Z
                        "ABCDEFGHIJKLMNOPQRSTUVWXYZ".chars()
                                .mapToObj(c -> (char) c)
                                .collect(Collectors.toList()),
                        // Roads A–B, B–C, …, Y–Z
                        "ABCDEFGHIJKLMNOPQRSTUVWXYZ".chars()
                                .mapToObj(c -> (char) c)
                                .filter(c -> c < 'Z')
                                .map(c -> "" + c + (char) (c + 1))
                                .collect(Collectors.toList()),
                        'A', 'B', 'Z',
                        25
                )
        );

        // Run each test case and report PASS/FAIL
        for (TestCase tc : testCases) {
            int result = computeMinCost(tc.nodes, tc.edges, tc.startA, tc.startB, tc.destD);
            if (result == tc.expected) {
                System.out.println("Test " + tc.name + ": PASS");
            } else {
                System.out.println("Test " + tc.name + ": FAIL (expected "
                        + tc.expected + " but got " + result + ")");
            }
        }
    }

    // Computes the minimum total cab cost given the graph and start/destination labels
    private static int computeMinCost(
            List<Character> nodes,
            List<String> edges,
            char startA,
            char startB,
            char destD
    ) {
        // Build an adjacency list for the graph
        Map<Character, List<Character>> graph = buildGraph(nodes, edges);

        // Run BFS from A, B, and D to get distance maps
        Map<Character, Integer> distA = bfs(graph, startA);
        Map<Character, Integer> distB = bfs(graph, startB);
        Map<Character, Integer> distD = bfs(graph, destD);

        // For each city c, compute distA[c] + distB[c] + distD[c], then take the minimum
        // If any distance is missing (i.e. unreachable), we treat cost as very large
        int minCost = nodes.stream()
                .mapToInt(c -> {
                    int da = distA.getOrDefault(c, Integer.MAX_VALUE);
                    int db = distB.getOrDefault(c, Integer.MAX_VALUE);
                    int dd = distD.getOrDefault(c, Integer.MAX_VALUE);
                    // if any part is unreachable, skip by using a large sum
                    return da < Integer.MAX_VALUE && db < Integer.MAX_VALUE && dd < Integer.MAX_VALUE
                            ? da + db + dd
                            : Integer.MAX_VALUE;
                })
                .min()
                .orElse(-1);  // if no city yields a valid path

        // If still Integer.MAX_VALUE, return -1 to indicate no solution; else return the min sum
        return minCost == Integer.MAX_VALUE ? -1 : minCost;
    }

    // Builds an undirected graph from the list of nodes and edge strings
    private static Map<Character, List<Character>> buildGraph(
            List<Character> nodes,
            List<String> edges
    ) {
        // Initialize each node with an empty neighbor list
        Map<Character, List<Character>> graph = nodes.stream()
                .collect(Collectors.toMap(Function.identity(), c -> new ArrayList<>()));

        // For each edge like "AB", add B to A’s list and A to B’s list
        for (String e : edges) {
            char u = e.charAt(0);
            char v = e.charAt(1);
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        return graph;
    }

    // Performs BFS from a given start city to compute shortest distances to all reachable cities
    private static Map<Character, Integer> bfs(
            Map<Character, List<Character>> graph,
            char start
    ) {
        Map<Character, Integer> dist = new HashMap<>();  // will hold distance to each city
        Queue<Character> q = new ArrayDeque<>();         // queue for BFS
        dist.put(start, 0);                              // distance to start is zero
        q.offer(start);                                  // enqueue the start city

        // Standard BFS loop
        while (!q.isEmpty()) {
            char cur = q.poll();                         // take next city
            int d = dist.get(cur);                       // its distance
            for (char nei : graph.get(cur)) {            // for each neighbor
                if (!dist.containsKey(nei)) {            // if not seen before
                    dist.put(nei, d + 1);                // record distance
                    q.offer(nei);                        // enqueue neighbor
                }
            }
        }

        return dist;  // return the map of shortest distances
    }

    // A simple class to bundle test cases
    static class TestCase {
        String name;                     // name of the test
        List<Character> nodes;           // list of city labels
        List<String> edges;              // list of roads (e.g. "AB" means A–B)
        char startA, startB, destD;      // starting cities for Alice (A), Bob (B), and David (D)
        int expected;                    // expected minimum cost

        // Constructor for convenience
        TestCase(String name, List<Character> nodes, List<String> edges,
                 char startA, char startB, char destD, int expected) {
            this.name = name;
            this.nodes = nodes;
            this.edges = edges;
            this.startA = startA;
            this.startB = startB;
            this.destD = destD;
            this.expected = expected;
        }
    }
}