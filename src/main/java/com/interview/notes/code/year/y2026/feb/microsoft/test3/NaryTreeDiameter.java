package com.interview.notes.code.year.y2026.feb.microsoft.test3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NaryTreeDiameter {

    // Global variable to track the maximum distance found across the whole tree
    static long maxDistance = 0;

    public static void main(String[] args) {
        testDiagramCase();  // Run the case from your image
        testLargeData();    // Run a performance check
    }

    // Recursive function to calculate max height and update diameter
    static long calculateHeight(Node node) {
        // Base Case: If leaf, return its own value (distance to parent)
        // If it's a leaf, the path length downwards is 0, so we just return the edge weight up.
        if (node.children.isEmpty()) return node.val;

        // Java 8+ Stream API: Process children to find their max path depths
        // 1. Map each child to its calculated height (recursion)
        // 2. Sort results in descending order (Largest paths first)
        // 3. Keep only the top 2 (since diameter uses at most 2 branches)
        var paths = node.children.stream()
            .map(NaryTreeDiameter::calculateHeight) // Recurse: Get max depth of child
            .sorted(Comparator.reverseOrder())      // Sort: O(K log K)
            .limit(2)                               // Optimization: Only need top 2
            .toList();                              // Convert to list for access

        // Get the longest branch (or 0 if no children)
        long max1 = paths.isEmpty() ? 0 : paths.get(0);

        // Get the second longest branch (or 0 if only 1 child)
        long max2 = paths.size() > 1 ? paths.get(1) : 0;

        // Update Global Maximum: The longest path passing THROUGH this node
        // is the sum of the two longest child branches.
        maxDistance = Math.max(maxDistance, max1 + max2);

        // Return to Parent: The longest single branch extending from this node
        // plus this node's own edge weight (node.val) to connect to parent.
        return max1 + node.val;
    }

    static void testDiagramCase() {
        System.out.println("--- Test Case 1: Provided Diagram ---");

        // Reconstructing the tree from your image
        var root = new Node(0); // Root (value irrelevant for internal paths)

        // Level 1: Nodes 5, 1, 3
        var n5 = new Node(5);
        var n1 = new Node(1);
        var n3 = new Node(3);
        Collections.addAll(root.children, n5, n1, n3);

        // Children of 5 -> 4, 4
        n5.children.add(new Node(4));
        n5.children.add(new Node(4));

        // Children of 1 -> 7
        n1.children.add(new Node(7));

        // Children of 3 -> 2, 6, 10
        Collections.addAll(n3.children, new Node(2), new Node(6), new Node(10));

        // Reset and Solve
        maxDistance = 0;
        calculateHeight(root);

        // Verification: Path 4 -> 5 -> Root -> 3 -> 10 = 4+5+3+10 = 22
        if (maxDistance == 22) {
            System.out.println("Result: PASS (Calculated: 22)");
        } else {
            System.out.println("Result: FAIL (Expected: 22, Got: " + maxDistance + ")");
        }
    }

    // --- TEST METHODS ---

    static void testLargeData() {
        System.out.println("\n--- Test Case 2: Large Data (100,000 Nodes) ---");

        // Constructing a "Star Graph" to test sorting speed
        var bigRoot = new Node(0);

        // Add 100,000 children with small random weights
        for(int i=0; i<100_000; i++) {
            bigRoot.children.add(new Node(1));
        }

        // Add two massive branches to ensure we can identify the max
        bigRoot.children.add(new Node(5000));
        bigRoot.children.add(new Node(5000));

        // Reset and Solve
        maxDistance = 0;
        long start = System.currentTimeMillis();
        calculateHeight(bigRoot);
        long end = System.currentTimeMillis();

        // Verification: 5000 + 5000 = 10000
        if (maxDistance == 10000) {
            System.out.println("Result: PASS (Time: " + (end - start) + "ms)");
        } else {
            System.out.println("Result: FAIL (Expected: 10000, Got: " + maxDistance + ")");
        }
    }

    // Java 21 Record: concise way to define the Node class.
    // 'val' acts as the edge weight from its parent.
    record Node(int val, List<Node> children) {
        // Compact constructor to initialize the list automatically
        Node(int val) { this(val, new ArrayList<>()); }
    }
}