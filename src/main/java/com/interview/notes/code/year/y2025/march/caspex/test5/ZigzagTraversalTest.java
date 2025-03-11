package com.interview.notes.code.year.y2025.march.caspex.test5;

import java.util.*;
import java.util.stream.*;

public class ZigzagTraversalTest {

    // Definition for a binary tree node.
    static class TreeNode {
        int val;
        TreeNode left, right;
        // Constructor: sets the node's value.
        TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * Performs a zigzag (spiral order) traversal on a binary tree.
     * Traversal rule: 
     *   - Odd levels (level 1, 3, ...): traverse right-to-left.
     *   - Even levels (level 2, 4, ...): traverse left-to-right.
     * (Note: since level 1 has only one element, the order is the same.)
     *
     * @param root The root node of the binary tree.
     * @return An integer array containing the nodes in zigzag traversal order.
     */
    public static int[] getLevelSpiral(TreeNode root) {
        // If the tree is empty, return an empty array.
        if (root == null) return new int[0];

        // List to store the final traversal order.
        List<Integer> result = new ArrayList<>();

        // Use a queue to perform level order traversal.
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        // Flag to indicate level order.
        // Start with level 1 (odd level) where we want right-to-left order.
        // (Since there is only one element at level 1, the order doesn't change.)
        boolean isOddLevel = true; 

        // Process the tree level by level.
        while (!queue.isEmpty()) {
            // Get the number of nodes at the current level.
            int levelSize = queue.size();
            // Temporary list to store nodes' values of the current level.
            List<Integer> levelList = new ArrayList<>();

            // Process all nodes at this level.
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll(); // Dequeue the next node.
                levelList.add(node.val);      // Add its value to the level list.

                // Enqueue left child if it exists.
                if (node.left != null) {
                    queue.add(node.left);
                }
                // Enqueue right child if it exists.
                if (node.right != null) {
                    queue.add(node.right);
                }
            }

            // For odd levels, reverse the list to get right-to-left order.
            if (isOddLevel) {
                Collections.reverse(levelList);
            }
            // For even levels, the natural order (left-to-right) is used.
            // Append the processed level values to the result.
            result.addAll(levelList);

            // Toggle the flag for the next level.
            isOddLevel = !isOddLevel;
        }

        // Convert the result list to an int array using Java 8 streams.
        return result.stream().mapToInt(i -> i).toArray();
    }

    /**
     * A helper method to run a single test case.
     *
     * @param testName  Name or description of the test.
     * @param root      Root of the binary tree.
     * @param expected  Expected output array.
     */
    public static void runTest(String testName, TreeNode root, int[] expected) {
        int[] output = getLevelSpiral(root);
        if (Arrays.equals(output, expected)) {
            System.out.println(testName + " : PASS");
        } else {
            System.out.println(testName + " : FAIL");
            System.out.println("Expected: " + Arrays.toString(expected));
            System.out.println("Output  : " + Arrays.toString(output));
        }
    }

    /**
     * Build the first test tree from the problem's Example 1.
     * Tree structure:
     *         10
     *        /  \
     *      30    20
     *
     * @return The root node of the tree.
     */
    public static TreeNode buildExampleTree1() {
        TreeNode root = new TreeNode(10);
        // Left child is 30 and right child is 20.
        root.left = new TreeNode(30);
        root.right = new TreeNode(20);
        return root;
    }

    /**
     * Build the second test tree from the problem's Example 2.
     * Tree structure:
     *         2
     *       /   \
     *      4     6
     *     / \
     *    8  10
     *
     * @return The root node of the tree.
     */
    public static TreeNode buildExampleTree2() {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(4);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(8);
        root.left.right = new TreeNode(10);
        return root;
    }

    /**
     * Builds a large binary tree (complete binary tree) for performance testing.
     * 
     * @param totalNodes The total number of nodes to include.
     * @return The root node of the large tree.
     */
    public static TreeNode buildLargeTree(int totalNodes) {
        if (totalNodes <= 0) return null;
        TreeNode root = new TreeNode(1);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int count = 1;
        // Build a complete tree until we have totalNodes.
        while (count < totalNodes) {
            TreeNode node = queue.poll();
            // Create left child if not exceeding total nodes.
            if (++count <= totalNodes) {
                node.left = new TreeNode(count);
                queue.add(node.left);
            }
            // Create right child if not exceeding total nodes.
            if (++count <= totalNodes) {
                node.right = new TreeNode(count);
                queue.add(node.right);
            }
        }
        return root;
    }

    /**
     * Main method to run all test cases.
     */
    public static void main(String[] args) {
        // Test Case 1: Example 1
        // Expected output: [10, 30, 20]
        runTest("Test Case 1 (Example 1)", buildExampleTree1(), new int[]{10, 30, 20});

        // Test Case 2: Example 2
        // Expected output: [2, 4, 6, 10, 8]
        runTest("Test Case 2 (Example 2)", buildExampleTree2(), new int[]{2, 4, 6, 10, 8});

        // Test Case 3: Edge Case - Empty Tree
        runTest("Test Case 3 (Empty Tree)", null, new int[]{});

        // Test Case 4: Single Node Tree
        TreeNode singleNode = new TreeNode(42);
        runTest("Test Case 4 (Single Node)", singleNode, new int[]{42});

        // Test Case 5: Large Tree Test
        // Here we build a large complete binary tree.
        int totalNodes = 10000; // Adjust this number for even larger tests.
        TreeNode largeTreeRoot = buildLargeTree(totalNodes);
        // Instead of verifying full order, we only check the count for performance test.
        int[] largeOutput = getLevelSpiral(largeTreeRoot);
        if (largeOutput.length == totalNodes) {
            System.out.println("Test Case 5 (Large Tree): PASS");
        } else {
            System.out.println("Test Case 5 (Large Tree): FAIL");
            System.out.println("Expected node count: " + totalNodes + ", but got: " + largeOutput.length);
        }
    }
}