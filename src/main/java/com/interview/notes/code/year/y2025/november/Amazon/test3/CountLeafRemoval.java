package com.interview.notes.code.year.y2025.november.Amazon.test3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// Node structure representing each element of the binary tree
class Node {
    int val;          // Value of the node
    Node left, right; // Left and right child references

    Node(int val) {   // Constructor
        this.val = val;
    }
}

public class CountLeafRemoval {

    // This method returns a list containing the number of leaf nodes
    // removed at each step until the binary tree becomes empty.
    public static List<Integer> countLeafRemovals(Node root) {
        // This map stores each node's height (distance from deepest leaf).
        // Nodes with the same height are removed together in one step.
        Map<Integer, Long> levelCount = new HashMap<>();

        // Recursively compute height for each node.
        computeHeight(root, levelCount);

        // Sort the map by level (height order) and return the leaf counts.
        return levelCount.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .map(e -> e.getValue().intValue())
                .collect(Collectors.toList());
    }

    // Recursive helper to compute height of each node.
    // Height of a leaf node is 1; parent's height = max(child heights) + 1.
    private static int computeHeight(Node node, Map<Integer, Long> levelCount) {
        // Base case: if node is null, height = 0
        if (node == null) return 0;

        // Compute height of left subtree
        int left = computeHeight(node.left, levelCount);

        // Compute height of right subtree
        int right = computeHeight(node.right, levelCount);

        // Node's height is 1 + maximum of child heights
        int height = Math.max(left, right) + 1;

        // Record how many nodes exist at this height.
        // These represent how many nodes get removed together in that step.
        levelCount.put(height, levelCount.getOrDefault(height, 0L) + 1);

        // Return this node’s height to its parent.
        return height;
    }

    // Main method: acts as a manual test driver (no JUnit)
    public static void main(String[] args) {
        // Build the example binary tree from the screenshot.
        /*
                 2
                / \
               5   7
              / \   \
             1   8   6
                / \
               3   4
         */

        Node root = new Node(2);
        root.left = new Node(5);
        root.right = new Node(7);
        root.left.left = new Node(1);
        root.left.right = new Node(8);
        root.left.right.left = new Node(3);
        root.left.right.right = new Node(4);
        root.right.right = new Node(6);

        // Expected output: [4, 2, 1, 1]
        List<Integer> result = countLeafRemovals(root);
        System.out.println("Leaf removals per step: " + result);

        // Simple validation check (PASS/FAIL)
        System.out.println(result.equals(Arrays.asList(4, 2, 1, 1)) ? "PASS" : "FAIL");

        // Test edge case: single node tree
        Node single = new Node(10);
        List<Integer> singleRes = countLeafRemovals(single);
        System.out.println("Single-node tree: " + singleRes);
        System.out.println(singleRes.equals(List.of(1)) ? "PASS" : "FAIL");

        // Performance test: large balanced tree
        Node large = generateLargeTree(100000);
        long start = System.currentTimeMillis();
        List<Integer> bigResult = countLeafRemovals(large);
        long end = System.currentTimeMillis();
        System.out.println("Large tree processed in " + (end - start) + " ms");
        System.out.println("Steps = " + bigResult.size());
    }

    // Generates a large balanced binary tree with n nodes
    private static Node generateLargeTree(int n) {
        List<Node> nodes = IntStream.rangeClosed(1, n)
                .mapToObj(Node::new)
                .collect(Collectors.toList());

        for (int i = 0; i < n; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            if (left < n) nodes.get(i).left = nodes.get(left);
            if (right < n) nodes.get(i).right = nodes.get(right);
        }
        return nodes.get(0);
    }
}
