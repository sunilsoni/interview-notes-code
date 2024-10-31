package com.interview.notes.code.months.oct24.amazon.test2;

import java.util.*;

class Node {
    public int data;
    public List<Node> neighbours;

    public Node() {
        data = 0;
        this.neighbours = new ArrayList<>();
    }

    public Node(int data) {
        this.data = data;
        this.neighbours = new ArrayList<>();
    }

    public Node(int data, List<Node> neighbours) {
        this.data = data;
        this.neighbours = neighbours;
    }
}

public class SolnTest {

    // Method to clone the graph
    public static Node cloneGraph(Node node) {
        if (node == null) return null;

        // Map to store the original node -> cloned node mapping
        Map<Node, Node> clonedMap = new HashMap<>();

        // Start DFS to clone the graph
        return cloneDFS(node, clonedMap);
    }

    private static Node cloneDFS(Node node, Map<Node, Node> clonedMap) {
        // If the node is already cloned, return the clone
        if (clonedMap.containsKey(node)) {
            return clonedMap.get(node);
        }

        // Clone the node
        Node clone = new Node(node.data);
        clonedMap.put(node, clone);

        // Clone all the neighbours recursively
        for (Node neighbour : node.neighbours) {
            clone.neighbours.add(cloneDFS(neighbour, clonedMap));
        }

        return clone;
    }

    // Method to compare two graphs (original and cloned) to ensure they are the same
    public static boolean areGraphsEqual(Node node1, Node node2, Set<Node> visited) {
        if (node1 == null && node2 == null) return true;
        if (node1 == null || node2 == null) return false;
        if (node1.data != node2.data) return false;

        visited.add(node1);

        if (node1.neighbours.size() != node2.neighbours.size()) return false;

        for (int i = 0; i < node1.neighbours.size(); i++) {
            Node neighbour1 = node1.neighbours.get(i);
            Node neighbour2 = node2.neighbours.get(i);

            if (!visited.contains(neighbour1)) {
                if (!areGraphsEqual(neighbour1, neighbour2, visited)) {
                    return false;
                }
            }
        }
        return true;
    }

    // Test method to check if the clone is correct
    public static void runTests() {
        // Test case 1: Simple graph with two nodes
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        node1.neighbours.add(node2);
        node2.neighbours.add(node1);

        Node clonedGraph = cloneGraph(node1);
        System.out.println(areGraphsEqual(node1, clonedGraph, new HashSet<>()) ? "PASS" : "FAIL");

        // Test case 2: Graph with a cycle
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        node3.neighbours.add(node4);
        node4.neighbours.add(node3);

        Node clonedGraph2 = cloneGraph(node3);
        System.out.println(areGraphsEqual(node3, clonedGraph2, new HashSet<>()) ? "PASS" : "FAIL");

        // Test case 3: Graph with multiple nodes and edges
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        node5.neighbours.add(node6);
        node6.neighbours.add(node7);
        node7.neighbours.add(node5);

        Node clonedGraph3 = cloneGraph(node5);
        System.out.println(areGraphsEqual(node5, clonedGraph3, new HashSet<>()) ? "PASS" : "FAIL");

        // Test case 4: Null graph
        Node clonedGraph4 = cloneGraph(null);
        System.out.println(clonedGraph4 == null ? "PASS" : "FAIL");
    }

    public static void main(String[] args) {
        runTests();
    }
}
