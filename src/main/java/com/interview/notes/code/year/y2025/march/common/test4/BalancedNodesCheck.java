package com.interview.notes.code.year.y2025.march.common.test4;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.Random;

public class BalancedNodesCheck {

    /**
     * Node structure:
     *  - val: an integer value (not strictly needed for counting).
     *  - subtrees: array of child nodes (could be 0..N children).
     */
    static class Node {
        public int val;
        public Node[] subtrees;

        public Node(int val) {
            this.val = val;
            this.subtrees = new Node[0];
        }

        public Node(int val, Node[] children) {
            this.val = val;
            this.subtrees = children;
        }
    }

    /**
     * Main solution method:
     * Returns the number of "balanced" nodes in the tree.
     *
     * Definition of a balanced node:
     *   - All of its children (subtrees) have the same size.
     *   - A leaf node (no children) is trivially balanced.
     */
    public static int solution(Node tree) {
        // We'll use a single-element array to store the count, so it can be updated in the helper method.
        int[] balancedCount = new int[1];
        countBalanced(tree, balancedCount);
        return balancedCount[0];
    }

    /**
     * Helper method: 
     *   - Returns the total number of nodes in the subtree rooted at 'node'.
     *   - Increments balancedCount[0] if 'node' is balanced.
     */
    private static int countBalanced(Node node, int[] balancedCount) {
        if (node == null) {
            return 0;
        }
        // If no children, it's a leaf => trivially balanced
        if (node.subtrees == null || node.subtrees.length == 0) {
            balancedCount[0]++;
            return 1;
        }

        // Recursively get subtree sizes for each child
        int[] childSizes = new int[node.subtrees.length];
        int sum = 1; // count the current node
        for (int i = 0; i < node.subtrees.length; i++) {
            childSizes[i] = countBalanced(node.subtrees[i], balancedCount);
            sum += childSizes[i];
        }

        // Check if all child subtree sizes are identical
        // Convert to a set; if the set size is 1, they are all the same.
        if (Arrays.stream(childSizes).boxed().collect(Collectors.toSet()).size() == 1) {
            balancedCount[0]++;
        }

        return sum;
    }

    /**
     * MAIN METHOD: 
     *   - Builds test trees.
     *   - Calls the solution method.
     *   - Prints PASS or FAIL for each test case.
     */
    public static void main(String[] args) {
        // Test Case 1 (Example-like tree)
        // Suppose we have a root with 2 children:
        //   root
        //   /  \
        //  c1  c2
        // c1 has 2 children (both leaves), c2 is a leaf
        // Balanced nodes? Let's see:
        //  - c1: has 2 children, both leaves => child sizes [1,1] => c1 is balanced
        //  - c2: no children => balanced
        //  - root: child subtree sizes [3,1], not equal => not balanced
        //  - The two leaves under c1 are leaves => both balanced
        // So total balanced nodes: c1, c2, and the two leaves => 4
        // Let's assume we want the result to be 4 for this test.

        Node c1Child1 = new Node(5);
        Node c1Child2 = new Node(6);
        Node c1 = new Node(2, new Node[]{ c1Child1, c1Child2 });
        Node c2 = new Node(3); // leaf
        Node root1 = new Node(1, new Node[]{ c1, c2 });

        int expected1 = 4;
        int result1 = solution(root1);
        printTestResult("Test Case 1", result1, expected1);

        // Test Case 2
        // Build a slightly larger tree with more children.
        // Let's manually craft a scenario with multiple balanced nodes.
        Node leafA = new Node(10);
        Node leafB = new Node(11);
        Node leafC = new Node(12);
        Node leafD = new Node(13);

        // Child X with two leaves => balanced
        Node childX = new Node(20, new Node[]{ leafA, leafB });
        // Child Y with two leaves => balanced
        Node childY = new Node(21, new Node[]{ leafC, leafD });
        // Root with two children => need to see if they match
        //   Each child subtree is 1 (the node) + 2 leaves => total 3 each
        //   So the root is also balanced in that sense
        Node root2 = new Node(99, new Node[]{ childX, childY });

        // Let's count:
        // - leafA, leafB, leafC, leafD => all balanced (4)
        // - childX => balanced (because child sizes [1,1])
        // - childY => balanced (same reason)
        // - root2 => balanced (childX subtree size 3, childY subtree size 3)
        // total => 4 + 2 + 1 = 7
        int expected2 = 7;
        int result2 = solution(root2);
        printTestResult("Test Case 2", result2, expected2);

        // Test Case 3 (Single node tree)
        Node singleNode = new Node(100);
        // A single node has no children => balanced
        // Should return 1
        int expected3 = 1;
        int result3 = solution(singleNode);
        printTestResult("Test Case 3 (Single Node)", result3, expected3);

        // Additional Test: Larger Tree
        // Let's build a root with many children, each child is a leaf (so all subtrees are size 1).
        // This will test performance for a bigger scenario.
        Node largeRoot = buildLargeStarTree(1000);
        // We won't predetermine the exact expected result, but let's just ensure it runs quickly.
        // In a star tree of 1 root + N leaves:
        //   - root has child subtree sizes all = 1 => root is balanced
        //   - all children are leaves => each leaf is balanced
        //   => total balanced = 1 (root) + N (leaves) = N+1
        int resultLarge = solution(largeRoot);
        System.out.println("Large Tree Test (1000 children): Balanced count = " + resultLarge);
        // If we built 1000 leaves, expected is 1001. Let's do a pass/fail check:
        int expectedLarge = 1001;
        printTestResult("Large Tree Test (1000 children)", resultLarge, expectedLarge);

        // Feel free to add more tests or edge cases below...
    }

    /**
     * Helper for printing test results in a simple pass/fail format.
     */
    private static void printTestResult(String testName, int actual, int expected) {
        if (actual == expected) {
            System.out.println(testName + ": PASS");
        } else {
            System.out.println(testName + ": FAIL (expected " + expected + ", got " + actual + ")");
        }
    }

    /**
     * Builds a "star" tree of 1 root and 'n' leaves (no deeper structure).
     * So the root has n children, each child is a leaf with 0 children.
     */
    private static Node buildLargeStarTree(int n) {
        Node[] children = new Node[n];
        for (int i = 0; i < n; i++) {
            children[i] = new Node(i);
        }
        return new Node(-1, children);
    }
}