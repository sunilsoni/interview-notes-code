package com.interview.notes.code.year.y2025.july.google.test4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Node {
    String name;        // Function name
    Long start;         // Start address
    Long end;          // End address 
    List<Node> children = new ArrayList<>();  // Nested functions

    // Constructor to create a node
    Node(String name, Long start, Long end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }
}

class FunctionRangeFlattener {

    // Main method to flatten the function ranges
    public static List<Node> flattenRanges(Node root) {
        // Result list to store flattened ranges
        List<Node> result = new ArrayList<>();

        // Handle empty input
        if (root == null) return result;

        // Process the tree using recursive helper
        flattenHelper(root, result);

        // Sort ranges by start address
        result.sort((a, b) -> a.start.compareTo(b.start));

        return result;
    }

    // Recursive helper to traverse and flatten the tree
    private static void flattenHelper(Node node, List<Node> result) {
        // Process children first (deeper nesting takes precedence)
        for (Node child : node.children) {
            flattenHelper(child, result);
        }

        // Add current node to result
        result.add(node);
    }

    // Pretty print the ranges
    public static void printRanges(List<Node> ranges) {
        ranges.forEach(n ->
                System.out.printf("%s(): %d - %d%n", n.name, n.start, n.end));
    }

    // Main method with test cases
    public static void main(String[] args) {
        // Test Case 1: Simple sequential ranges
        Node root1 = new Node("root", 0L, 100L);
        root1.children.add(new Node("foo", 1L, 25L));
        root1.children.add(new Node("bar", 25L, 35L));
        root1.children.add(new Node("baz", 35L, 45L));
        root1.children.add(new Node("bar", 45L, 75L));
        root1.children.add(new Node("foo", 75L, 100L));

        System.out.println("Test Case 1 - Sequential ranges:");
        printRanges(flattenRanges(root1));

        // Test Case 2: Nested ranges
        Node root2 = new Node("root", 0L, 100L);
        Node outer = new Node("outer", 10L, 90L);
        Node inner = new Node("inner", 20L, 80L);
        outer.children.add(inner);
        root2.children.add(outer);

        System.out.println("\nTest Case 2 - Nested ranges:");
        printRanges(flattenRanges(root2));

        // Test Case 3: Large dataset
        Node root3 = new Node("root", 0L, 1000000L);
        Random rand = new Random();
        for (int i = 0; i < 1000; i++) {
            long start = rand.nextInt(900000);
            root3.children.add(new Node(
                    "func" + i,
                    start,
                    start + rand.nextInt(100000)
            ));
        }

        System.out.println("\nTest Case 3 - Large dataset (1000 functions):");
        List<Node> result3 = flattenRanges(root3);
        System.out.println("Total ranges: " + result3.size());
    }
}
