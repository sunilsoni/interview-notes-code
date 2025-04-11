package com.interview.notes.code.year.y2025.april.common.test2;

import java.util.*;
import java.util.stream.Collectors;

public class RemoveLowercaseAndLoops {

    // Simple Node class
    static class Node {
        String name;
        List<Node> neighbors = new ArrayList<>();

        Node(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    // Build the sample graph from the problem statement
    private static Map<String, Node> buildSampleGraph() {
        // Create all nodes
        Node A = new Node("A");
        Node B = new Node("B");
        Node c = new Node("c");
        Node D = new Node("D");
        Node b = new Node("b");
        Node F = new Node("F");

        // Connect them (some edges mimic the diagram)
        A.neighbors.add(B);
        B.neighbors.add(c);
        c.neighbors.add(D);
        D.neighbors.add(b);
        b.neighbors.add(F);
        // Example loop: let’s say F also points back to c
        F.neighbors.add(c);

        // Put into a Map so we can easily look them up if needed
        Map<String, Node> graph = new HashMap<>();
        graph.put("A", A);
        graph.put("B", B);
        graph.put("c", c);
        graph.put("D", D);
        graph.put("b", b);
        graph.put("F", F);

        return graph;
    }

    // Remove lowercase nodes from the graph
    private static void removeLowerCaseNodes(Map<String, Node> graph) {
        // Identify all lowercase nodes
        Set<String> toRemove = graph.keySet().stream()
                .filter(key -> isLowerCase(key))
                .collect(Collectors.toSet());

        // Remove these nodes from the map
        for (String rem : toRemove) {
            graph.remove(rem);
        }

        // Remove any edges pointing to them
        for (Node node : graph.values()) {
            node.neighbors.removeIf(n -> toRemove.contains(n.name));
        }
    }

    // Detect and remove loops using DFS
    // We'll do a DFS starting from 'A', ignoring nodes that are not reachable from 'A'.
    // If we find a loop, we remove the edge creating that loop.
    private static void removeLoops(Node start) {
        // visited: nodes we have fully processed
        // inStack: nodes currently in the recursion stack
        Set<Node> visited = new HashSet<>();
        Set<Node> inStack = new HashSet<>();

        dfsRemoveLoops(start, visited, inStack);
    }

    private static void dfsRemoveLoops(Node current, Set<Node> visited, Set<Node> inStack) {
        if (current == null) return;
        if (visited.contains(current)) {
            return;
        }

        visited.add(current);
        inStack.add(current);

        // We'll store valid neighbors in a new list to avoid concurrent modification
        List<Node> filtered = new ArrayList<>();
        for (Node neighbor : current.neighbors) {
            if (!inStack.contains(neighbor)) {
                // neighbor not in the recursion stack -> we can keep/traverse it
                filtered.add(neighbor);
                dfsRemoveLoops(neighbor, visited, inStack);
            } else {
                // If neighbor is inStack, that means adding current->neighbor would form a loop
                // so we do NOT add it to "filtered"
            }
        }

        // Update neighbors to only valid edges
        current.neighbors = filtered;

        inStack.remove(current);
    }

    // Simple check for lowercase name
    private static boolean isLowerCase(String s) {
        // You might refine logic if nodes can be multi-character, etc.
        return s.equals(s.toLowerCase());
    }

    // Traverse the resulting graph from A and print a single path (if it exists)
    private static void printPathFromA(Node A) {
        // We do a simple DFS or BFS to track a path. For demonstration, we’ll do DFS:
        List<String> path = new ArrayList<>();
        Set<Node> visited = new HashSet<>();
        buildPathDFS(A, path, visited);

        // Print the path (A -> B -> D -> F)
        if (!path.isEmpty()) {
            System.out.println("Final Path (no loops, uppercase only): " + String.join(" -> ", path));
        } else {
            System.out.println("No path from A found (or A does not exist).");
        }
    }

    private static void buildPathDFS(Node current, List<String> path, Set<Node> visited) {
        if (current == null || visited.contains(current)) {
            return;
        }
        visited.add(current);
        path.add(current.name);

        // If there's exactly 1 neighbor, keep going; if multiple neighbors, pick the first for demonstration
        // (In a real scenario, you might gather all possible paths or handle differently)
        if (!current.neighbors.isEmpty()) {
            buildPathDFS(current.neighbors.get(0), path, visited);
        }
    }

    public static void main(String[] args) {
        // Build sample
        Map<String, Node> graph = buildSampleGraph();

        // 1) Remove lowercase nodes
        removeLowerCaseNodes(graph);

        // 2) Remove loops from the uppercase-only graph
        //    Start from A (if A exists)
        Node A = graph.get("A");
        if (A != null) {
            removeLoops(A);
        }

        // 3) Print final path from A
        printPathFromA(A);

        // -----------------------------------------------------------
        // Additional checks (simulating tests in a simple main method)
        // -----------------------------------------------------------
        boolean passCase1 = (graph.size() == 4) && graph.containsKey("A")
                            && graph.containsKey("B") && graph.containsKey("D")
                            && graph.containsKey("F");
        System.out.println("Test Case 1 (Only A,B,D,F remain): " + (passCase1 ? "PASS" : "FAIL"));

        // If we want to see that A->B->D->F is indeed the chain:
        Node B = graph.get("B");
        Node D = graph.get("D");
        Node F = graph.get("F");
        boolean passCase2 = (A != null && A.neighbors.size() == 1 && A.neighbors.get(0) == B) &&
                            (B != null && B.neighbors.size() == 1 && B.neighbors.get(0) == D) &&
                            (D != null && D.neighbors.size() == 1 && D.neighbors.get(0) == F) &&
                            (F != null && F.neighbors.isEmpty());
        System.out.println("Test Case 2 (Chain A->B->D->F): " + (passCase2 ? "PASS" : "FAIL"));
    }
}