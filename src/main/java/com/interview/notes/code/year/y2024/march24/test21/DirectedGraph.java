package com.interview.notes.code.year.y2024.march24.test21;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Node {
    int id;
    List<Node> neighbors;

    public Node(int id) {
        this.id = id;
        this.neighbors = new ArrayList<>();
    }
}

public class DirectedGraph {
    private static boolean hasCycle(Node node, Set<Node> visited, Set<Node> recursionStack) {
        visited.add(node);
        recursionStack.add(node);

        for (Node neighbor : node.neighbors) {
            if (!visited.contains(neighbor)) {
                if (hasCycle(neighbor, visited, recursionStack)) {
                    return true;
                }
            } else if (recursionStack.contains(neighbor)) {
                // Found a back edge, indicating a cycle
                return true;
            }
        }

        recursionStack.remove(node);
        return false;
    }

    public static boolean doesContainDirectedCycles(Node startingNode) {
        Set<Node> visited = new HashSet<>();
        Set<Node> recursionStack = new HashSet<>();

        return hasCycle(startingNode, visited, recursionStack);
    }

    public static void main(String[] args) {
        // Example usage:
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);

        node1.neighbors.add(node2);
        node2.neighbors.add(node3);
        node3.neighbors.add(node1); // Creates a cycle

        boolean containsCycles = doesContainDirectedCycles(node1);
        System.out.println("Contains cycles: " + containsCycles);
    }
}
