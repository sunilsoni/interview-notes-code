package com.interview.notes.code.year.y2025.july.hackerank.test7;

import java.util.*;

class Node {
    int val;
    List<Node> children;

    // Constructor for leaf nodes
    public Node(int val) {
        this.val = val;
        this.children = new ArrayList<>();
    }

    // Constructor for non-leaf nodes
    public Node(int val, List<Node> children) {
        this.val = val;
        this.children = children;
    }
}

public class SubtreeExtractor {

    /**
     * Extracts the subtree rooted at targetId.
     * @param root     the root of the full N-ary tree
     * @param targetId the value to find
     * @return the Node whose val == targetId, or null if not found
     */
    public static Node extractSubtree(Node root, int targetId) {
        if (root == null) {
            return null;               // no node here
        }
        if (root.val == targetId) {
            return root;              // found the target node
        }
        // search each child in turn, using Java 8 streams
        return root.children.stream()
            .map(child -> extractSubtree(child, targetId)) // recurse
            .filter(Objects::nonNull)                       // keep only hits
            .findFirst()                                     // take first hit
            .orElse(null);                                   // or return null
    }

    /**
     * Counts nodes in a subtree (null subtree → count 0).
     * @param node the root of the subtree
     * @return number of nodes under (and including) this node
     */
    private static int countNodes(Node node) {
        if (node == null) {
            return 0;                   // empty subtree
        }
        // 1 for this node, plus sum of all children
        return 1 + node.children.stream()
                    .mapToInt(SubtreeExtractor::countNodes)
                    .sum();
    }

    /**
     * Runs a single test and prints PASS/FAIL.
     * @param root           full tree root
     * @param targetId       value to extract
     * @param expectedCount  expected number of nodes in that subtree
     */
    private static void runTest(Node root, int targetId, int expectedCount) {
        Node subtree = extractSubtree(root, targetId);
        int actualCount = countNodes(subtree);
        if (actualCount == expectedCount) {
            System.out.println("Test targetId=" + targetId + " PASSED");
        } else {
            System.out.println("Test targetId=" + targetId
                + " FAILED: expected " + expectedCount
                + " but got " + actualCount);
        }
    }

    public static void main(String[] args) {
        // Build the example tree:
        //          1
        //        /   \
        //       2     3
        //      / \   / \
        //     4   5 6   7
        Node root = new Node(1, Arrays.asList(
            new Node(2, Arrays.asList(new Node(4), new Node(5))),
            new Node(3, Arrays.asList(new Node(6), new Node(7)))
        ));

        // Test cases:
        runTest(root, 3, 3);   // nodes {3,6,7}
        runTest(root, 2, 3);   // nodes {2,4,5}
        runTest(root, 4, 1);   // leaf {4}
        runTest(root, 8, 0);   // not found → null → count 0
    }
}