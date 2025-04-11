package com.interview.notes.code.year.y2025.april.common.test1;

import java.util.*;
import java.util.stream.Collectors;

public class GraphFixer {
    
    // Adjacency list: Node -> List of Nodes
    private final Map<String, List<String>> adjacency;

    // Constructor
    public GraphFixer(Map<String, List<String>> adjacency) {
        // Make a deep copy or store directly (here storing directly for simplicity)
        this.adjacency = adjacency;
    }

    /**
     * Step 1 & 2: Remove all lowercase nodes and edges referencing them.
     */
    public void removeLowercaseNodes() {
        // Identify all lowercase nodes
        Set<String> lowercaseNodes = adjacency.keySet().stream()
                .filter(k -> k.equals(k.toLowerCase())) // or: !k.equals(k.toUpperCase())
                .collect(Collectors.toSet());
        
        // Remove all lowercase keys entirely
        adjacency.keySet().removeAll(lowercaseNodes);

        // Also remove any edges that point to lowercase nodes
        for (String key : adjacency.keySet()) {
            List<String> filtered = adjacency.get(key).stream()
                    .filter(n -> !lowercaseNodes.contains(n))
                    .collect(Collectors.toList());
            adjacency.put(key, filtered);
        }
    }

    /**
     * Step 3: Remove cycles (break loops) among uppercase nodes.
     * We will detect and remove back edges using DFS.
     */
    public void removeCycles() {
        Set<String> visited = new HashSet<>();
        Set<String> recursionStack = new HashSet<>();

        // We must attempt a DFS from each node, since graph can be disconnected.
        for (String node : adjacency.keySet()) {
            if (!visited.contains(node)) {
                dfsRemoveBackEdges(node, visited, recursionStack);
            }
        }
    }

    /**
     * DFS to remove back edges (edges that lead to a node already in the recursion stack).
     */
    private void dfsRemoveBackEdges(String current, Set<String> visited, Set<String> recursionStack) {
        visited.add(current);
        recursionStack.add(current);

        // Iterate over adjacency of current node
        List<String> neighbors = adjacency.getOrDefault(current, new ArrayList<>());
        // We'll store valid (non-back-edge) neighbors here
        List<String> newNeighbors = new ArrayList<>();

        for (String neighbor : neighbors) {
            // If neighbor is not yet visited, recurse
            if (!visited.contains(neighbor)) {
                dfsRemoveBackEdges(neighbor, visited, recursionStack);
                // If neighbor didn't get removed during DFS, keep it
                if (adjacency.containsKey(neighbor)) {
                    newNeighbors.add(neighbor);
                }
            } else if (!recursionStack.contains(neighbor)) {
                // Visited but not in recursion stack -> it's okay, no back edge
                newNeighbors.add(neighbor);
            }
            // else if recursionStack.contains(neighbor), it's a back edge -> do not add
        }

        // Update adjacency with the filtered neighbor list
        adjacency.put(current, newNeighbors);

        // Remove current from recursion stack
        recursionStack.remove(current);
    }

    /**
     * Step 4: Find a path from start to end using DFS or BFS in the pruned acyclic graph.
     * We return the first path found.
     */
    public List<String> findPath(String start, String end) {
        List<String> path = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        boolean found = dfsFindPath(start, end, visited, path);
        if (found) {
            return path;
        }
        return Collections.emptyList(); // no path found
    }

    private boolean dfsFindPath(String current, String end,
                                Set<String> visited,
                                List<String> path) {
        if (!adjacency.containsKey(current)) {
            return false; // node doesn't exist or was removed
        }
        visited.add(current);
        path.add(current);

        // If we've reached the end
        if (current.equals(end)) {
            return true;
        }

        for (String neighbor : adjacency.getOrDefault(current, new ArrayList<>())) {
            if (!visited.contains(neighbor)) {
                if (dfsFindPath(neighbor, end, visited, path)) {
                    return true;
                }
            }
        }
        path.remove(path.size() - 1);
        return false;
    }

    /**
     * Simple main method to test the logic (no JUnit).
     */
    public static void main(String[] args) {
        // Step 0: Build the example graph as described.
        // Lowercase nodes: "c", "b"
        // Here, for demonstration, we’ll combine them to show the adjacency:
        //   A -> B
        //   B -> c
        //   c -> D
        //   D -> B (creates a loop among B->c->D->B if we treat c as uppercase)
        //   D -> b
        //   b -> F
        //   F -> c
        // But let's adapt to exactly reflect the bullet points with uppercase-lowercase consistency:
        
        Map<String, List<String>> adjacency = new HashMap<>();
        adjacency.put("A", Arrays.asList("B"));
        adjacency.put("B", Arrays.asList("C"));      // We'll assume "C" was meant uppercase
        adjacency.put("C", Arrays.asList("D"));      // "C" -> "D"
        adjacency.put("D", Arrays.asList("B", "b")); // "D" -> "B" (cycle) and "D" -> "b"
        adjacency.put("b", Arrays.asList("F"));      // "b" -> "F"
        adjacency.put("F", Arrays.asList("C"));      // "F" -> "C"
        
        // Create the GraphFixer object
        GraphFixer fixer = new GraphFixer(adjacency);

        System.out.println("=== Before Removal ===");
        printAdjacency(fixer.adjacency);

        // 1) Remove lowercase nodes
        fixer.removeLowercaseNodes();
        System.out.println("\n=== After Removing Lowercase ===");
        printAdjacency(fixer.adjacency);

        // 2) Remove cycles
        fixer.removeCycles();
        System.out.println("\n=== After Removing Cycles ===");
        printAdjacency(fixer.adjacency);

        // 3) Find path from A to F
        List<String> path = fixer.findPath("A", "F");
        System.out.println("\n=== Path from A to F ===");
        if (path.isEmpty()) {
            System.out.println("No path found!");
        } else {
            System.out.println(String.join(" -> ", path));
        }

        // Extra: A simple pass/fail check (not JUnit).
        // The desired final output is "A -> B -> D -> F".
        String result = String.join("->", path);
        String expected = "A->B->D->F";
        if (result.equals(expected)) {
            System.out.println("\nTEST RESULT: PASS");
        } else {
            System.out.println("\nTEST RESULT: FAIL (Expected: " + expected + ", Got: " + result + ")");
        }
    }

    // Utility to print adjacency map
    private static void printAdjacency(Map<String, List<String>> adjacency) {
        adjacency.forEach((k,v) -> {
            System.out.println(k + " -> " + v);
        });
    }
}
