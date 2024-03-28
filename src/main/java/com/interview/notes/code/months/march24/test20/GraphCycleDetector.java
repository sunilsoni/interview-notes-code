package com.interview.notes.code.months.march24.test20;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Implement a method for finding whether it contains any directed cycles (you don't need to identify the cycle, just return true if it contains at least one directed cycle and false if it doesn't) .
 * bool doesContainDirectedCycles(Node startingNode)
 */

class Node {
    int id;
    List<Node> neighbors;

    Node(int id) {
        this.id = id;
        this.neighbors = new ArrayList<>();
    }

    // Add neighbor to the node
    void addNeighbor(Node neighbor) {
        this.neighbors.add(neighbor);
    }
}

public class GraphCycleDetector {

    public boolean doesContainDirectedCycles(Node startingNode) {
        Set<Node> visited = new HashSet<>();
        Set<Node> recursionStack = new HashSet<>();

        return isCyclic(startingNode, visited, recursionStack);
    }

    private boolean isCyclic(Node node, Set<Node> visited, Set<Node> recursionStack) {
        if (recursionStack.contains(node)) {
            // Node is in recursion stack, cycle detected
            return true;
        }
        if (visited.contains(node)) {
            // Node has been visited and did not lead to a cycle earlier
            return false;
        }

        visited.add(node);
        recursionStack.add(node);

        for (Node neighbor : node.neighbors) {
            if (isCyclic(neighbor, visited, recursionStack)) {
                return true;
            }
        }

        recursionStack.remove(node);
        return false;
    }
}
