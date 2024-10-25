package com.interview.notes.code.months.oct24.test18;

import java.util.*;

public class LeastStressfulPathSolution {
    public static void main(String[] args) {
        testAll();
    }

    static void testAll() {
        System.out.println("Running all test cases...\n");
        
        // Test Case 1 - Sample case from problem
        testCase1();
        
        // Test Case 2 - No path exists
        testCase2();
        
        // Test Case 3 - Large graph
        testCase3();
        
        // Test Case 4 - Edge cases
        testCase4();
    }

    static void testCase1() {
        System.out.println("Test Case 1: Sample case from problem");
        List<Integer> graphFrom = Arrays.asList(1, 2, 1, 4, 4, 1, 5);
        List<Integer> graphTo = Arrays.asList(2, 3, 4, 3, 3, 5, 3);
        List<Integer> weights = Arrays.asList(10, 5, 3, 2, 2, 4, 6);
        int result = getMinimumStress(5, graphFrom, graphTo, weights, 1, 3);
        boolean passed = result == 3;
        printResult("Sample case", passed, 3, result);
    }

    static void testCase2() {
        System.out.println("\nTest Case 2: No path exists");
        List<Integer> graphFrom = Arrays.asList(1);
        List<Integer> graphTo = Arrays.asList(2);
        List<Integer> weights = Arrays.asList(10);
        int result = getMinimumStress(3, graphFrom, graphTo, weights, 1, 3);
        boolean passed = result == -1;
        printResult("No path exists", passed, -1, result);
    }

    static void testCase3() {
        System.out.println("\nTest Case 3: Large graph performance");
        // Generate large graph
        int n = 100000;
        List<Integer> graphFrom = new ArrayList<>();
        List<Integer> graphTo = new ArrayList<>();
        List<Integer> weights = new ArrayList<>();
        
        // Create path with increasing weights
        for (int i = 1; i < n; i++) {
            graphFrom.add(i);
            graphTo.add(i + 1);
            weights.add(i);
        }
        
        long startTime = System.currentTimeMillis();
        int result = getMinimumStress(n, graphFrom, graphTo, weights, 1, n);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        
        System.out.println("Execution time: " + executionTime + "ms");
        boolean passed = result > 0 && executionTime < 5000;
        printResult("Large graph performance", passed, "N/A", result);
    }

    static void testCase4() {
        System.out.println("\nTest Case 4: Edge cases");
        
        // Single node
        int result1 = getMinimumStress(1, new ArrayList<>(), new ArrayList<>(), 
                                     new ArrayList<>(), 1, 1);
        printResult("Single node", result1 == -1, -1, result1);

        // Two nodes with direct edge
        List<Integer> graphFrom = Arrays.asList(1);
        List<Integer> graphTo = Arrays.asList(2);
        List<Integer> weights = Arrays.asList(5);
        int result2 = getMinimumStress(2, graphFrom, graphTo, weights, 1, 2);
        printResult("Two nodes with direct edge", result2 == 5, 5, result2);

        // Maximum weight test
        List<Integer> maxWeights = Arrays.asList(1000000000);
        int result3 = getMinimumStress(2, graphFrom, graphTo, maxWeights, 1, 2);
        printResult("Maximum weight", result3 == 1000000000, 1000000000, result3);
    }

    static void printResult(String testName, boolean passed, Object expected, Object actual) {
        System.out.println(String.format("%-30s: %s", testName, 
                          passed ? "PASSED" : "FAILED"));
        if (!passed) {
            System.out.println("Expected: " + expected + ", Actual: " + actual);
        }
    }

    static int getMinimumStress(int graphNodes, List<Integer> graphFrom, 
                               List<Integer> graphTo, List<Integer> graphWeight,
                               int source, int destination) {
        // Create adjacency list
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int i = 1; i <= graphNodes; i++) {
            graph.put(i, new ArrayList<>());
        }
        
        // Build undirected graph
        for (int i = 0; i < graphFrom.size(); i++) {
            int u = graphFrom.get(i);
            int v = graphTo.get(i);
            int w = graphWeight.get(i);
            graph.get(u).add(new Edge(v, w));
            graph.get(v).add(new Edge(u, w));
        }
        
        // Modified Dijkstra's algorithm
        PriorityQueue<Path> pq = new PriorityQueue<>();
        Set<Integer> visited = new HashSet<>();
        
        pq.offer(new Path(source, 0));
        
        while (!pq.isEmpty()) {
            Path current = pq.poll();
            
            if (current.node == destination) {
                return current.maxStress;
            }
            
            if (visited.contains(current.node)) {
                continue;
            }
            
            visited.add(current.node);
            
            for (Edge edge : graph.get(current.node)) {
                if (!visited.contains(edge.to)) {
                    pq.offer(new Path(edge.to, 
                            Math.max(current.maxStress, edge.weight)));
                }
            }
        }
        
        return -1;
    }
    
    static class Edge {
        int to, weight;
        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
    
    static class Path implements Comparable<Path> {
        int node, maxStress;
        Path(int node, int maxStress) {
            this.node = node;
            this.maxStress = maxStress;
        }
        
        @Override
        public int compareTo(Path other) {
            return Integer.compare(this.maxStress, other.maxStress);
        }
    }
}