package com.interview.notes.code.year.y2024.nov24.amazon.test6;

import java.util.LinkedList;
import java.util.Queue;

public class GetMaximumWidthTest {
    public static void main(String[] args) {
        // Initialize BinaryTree instances for different test cases
        BinaryTree[] testTrees = new BinaryTree[5];

        // Test Case 1: Empty Tree
        testTrees[0] = new BinaryTree();
        // Expected Maximum Width: 0

        // Test Case 2: Single Node Tree
        testTrees[1] = new BinaryTree();
        testTrees[1].root = new TreeNode('A');
        // Expected Maximum Width: 1

        // Test Case 3: Complete Binary Tree
        testTrees[2] = new BinaryTree();
        testTrees[2].root = new TreeNode('A');
        testTrees[2].root.left = new TreeNode('B');
        testTrees[2].root.right = new TreeNode('C');
        testTrees[2].root.left.left = new TreeNode('D');
        testTrees[2].root.left.right = new TreeNode('E');
        testTrees[2].root.right.left = new TreeNode('F');
        testTrees[2].root.right.right = new TreeNode('G');
        // Expected Maximum Width: 4

        // Test Case 4: Tree with Missing Nodes (Expected Width: 7)
        testTrees[3] = new BinaryTree();
        testTrees[3].root = new TreeNode('A');
        testTrees[3].root.left = new TreeNode('B');
        testTrees[3].root.right = new TreeNode('C');
        testTrees[3].root.left.left = new TreeNode('D');
        testTrees[3].root.left.right = new TreeNode('E');
        testTrees[3].root.right.left = new TreeNode('F');
        testTrees[3].root.right.right = new TreeNode('G');

        // Level 4
        testTrees[3].root.left.left.left = new TreeNode('H');
        testTrees[3].root.left.left.right = new TreeNode('I');
        testTrees[3].root.left.right.left = new TreeNode('J');
        testTrees[3].root.left.right.right = new TreeNode('K'); // Added Node K
        testTrees[3].root.right.left.left = new TreeNode('L');
        testTrees[3].root.right.left.right = new TreeNode('M'); // Added Node M
        testTrees[3].root.right.right.left = new TreeNode('N');
        testTrees[3].root.right.right.right = new TreeNode('O'); // Added Node O

        // Removing Node O to achieve maximum width of 7
        testTrees[3].root.right.right.right = null;

        // Level 5
        testTrees[3].root.left.left.left.left = new TreeNode('P');
        testTrees[3].root.left.left.left.right = new TreeNode('Q');
        // Expected Maximum Width: 7

        // Test Case 5: Large Tree
        testTrees[4] = generateLargeTree(1000000); // 1 million nodes
        // Expected Maximum Width: 500,000 (for a complete binary tree)

        // Define expected results
        long[] expectedResults = {0, 1, 4, 7, 500000}; // Adjusted expected for large tree accordingly

        // Run tests
        for (int i = 0; i < testTrees.length; i++) {
            BinaryTree tree = testTrees[i];
            long expected = expectedResults[i];
            long actual = tree.getMaximumWidth();
            if (i == 4) {
                // For large tree, ensure it runs without errors and check expected width
                if (actual == expected) {
                    System.out.println("Test Case " + (i + 1) + ": PASS");
                } else {
                    System.out.println("Test Case " + (i + 1) + ": FAIL (Expected: " + expected + ", Got: " + actual + ")");
                }
            } else if (actual == expected) {
                System.out.println("Test Case " + (i + 1) + ": PASS");
            } else {
                System.out.println("Test Case " + (i + 1) + ": FAIL (Expected: " + expected + ", Got: " + actual + ")");
            }
        }
    }

    /**
     * Generates a large binary tree with the specified number of nodes.
     * This example generates a complete binary tree.
     *
     * @param numNodes The total number of nodes in the tree.
     * @return An instance of BinaryTree with the generated tree.
     */
    private static BinaryTree generateLargeTree(int numNodes) {
        BinaryTree tree = new BinaryTree();
        if (numNodes <= 0) return tree;

        tree.root = new TreeNode('A');
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(tree.root);

        char currentChar = 'B';
        int count = 1;

        while (count < numNodes) {
            TreeNode current = queue.poll();
            if (current.left == null && count < numNodes) {
                current.left = new TreeNode(currentChar++);
                queue.offer(current.left);
                count++;
            }
            if (current.right == null && count < numNodes) {
                current.right = new TreeNode(currentChar++);
                queue.offer(current.right);
                count++;
            }
        }

        return tree;
    }
}
