package com.interview.notes.code.year.y2025.july.google.test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Node {
    String name;
    Long start;
    Long end;
    List<Node> children;

    Node(String name, Long start, Long end) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.children = new ArrayList<>();
    }
}

public class FlattenFunctionRanges {

    // Recursively traverse the tree, prioritize deeper nodes
    private static void flatten(Node node, List<Range> result) {
        if (node == null) return;

        // First, recursively process children (deepest first)
        if (node.children != null) {
            for (Node child : node.children) {
                flatten(child, result);
            }
        }

        // Add current node after processing its children (deeper priority)
        result.add(new Range(node.name, node.start, node.end));
    }

    // Main function to flatten and print the ranges
    public static void flattenAndPrint(Node root) {
        List<Range> flattened = new ArrayList<>();

        // Flatten the tree
        flatten(root, flattened);

        // Sort by start address
        flattened.sort(Comparator.comparingLong(r -> r.start));

        // Print sorted flattened ranges
        flattened.forEach(r -> System.out.println(r.name + "(): " + r.start + " - " + r.end));
    }

    // Testing the implementation
    public static void main(String[] args) {
        System.out.println("Test Case 1: Simple Nested");

        // Creating the nodes as per provided example
        Node foo1 = new Node("foo", 1L, 100L);
        Node bar1 = new Node("bar", 25L, 75L);
        Node baz1 = new Node("baz", 35L, 45L);

        // Building the tree
        bar1.children.add(baz1);
        foo1.children.add(bar1);

        flattenAndPrint(foo1);

        System.out.println("\nTest Case 2: Sequential Functions (No Overlaps)");
        Node foo2 = new Node("foo", 1L, 25L);
        Node bar2 = new Node("bar", 25L, 35L);
        Node baz2 = new Node("baz", 35L, 45L);
        Node bar3 = new Node("bar", 45L, 75L);
        Node foo3 = new Node("foo", 75L, 100L);

        Node rootSequential = new Node("root", 0L, 0L); // dummy root for this case
        rootSequential.children = Arrays.asList(foo2, bar2, baz2, bar3, foo3);

        flattenAndPrint(rootSequential);

        System.out.println("\nTest Case 3: Large Data Test");

        // Large data test (to check performance)
        Node largeRoot = new Node("root", 1L, 1000000L);
        for (int i = 0; i < 1000; i++) {
            Node child = new Node("func" + i, (long) (i * 1000 + 1), (long) ((i + 1) * 1000));
            largeRoot.children.add(child);
        }

        flattenAndPrint(largeRoot);

        // Simple validation
        System.out.println("\nAll Test Cases Completed.");
    }

    // Helper class to represent flattened range
    static class Range {
        String name;
        Long start;
        Long end;

        Range(String name, Long start, Long end) {
            this.name = name;
            this.start = start;
            this.end = end;
        }
    }
}
