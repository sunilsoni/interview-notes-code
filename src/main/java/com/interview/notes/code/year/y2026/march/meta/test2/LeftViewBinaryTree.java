package com.interview.notes.code.year.y2026.march.meta.test2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Use Java 21 'record' to instantly create an immutable TreeNode class with minimal words.
record TreeNode(int val, TreeNode left, TreeNode right) {}

// Main class wrapping the solution and the custom testing logic.
public class LeftViewBinaryTree {

    // Main solver function that returns the list of visible nodes from the left.
    public static List<Integer> getLeftView(TreeNode root) {
        // Use Java 'var' keyword for brief local variable type inference.
        var visibleNodes = new ArrayList<Integer>();
        // Start recursive depth-first search starting from root at depth 0.
        dfs(root, 0, visibleNodes);
        // Once recursion finishes, return the fully populated list.
        return visibleNodes;
    }

    // Private helper method to handle the recursive Depth-First Search traversal.
    private static void dfs(TreeNode node, int depth, List<Integer> visibleNodes) {
        // Base case: if the branch ends (node is null), stop and return back up.
        if (node == null) return;
        
        // If current depth matches the list size, this is the first node seen at this level.
        if (depth == visibleNodes.size()) {
            // Add the node's value to our visible list because nothing blocks it.
            visibleNodes.add(node.val());
        }
        
        // Always traverse the left child first so it gets added before the right child.
        dfs(node.left(), depth + 1, visibleNodes);
        // Traverse the right child next in case it extends deeper than the left branch.
        dfs(node.right(), depth + 1, visibleNodes);
    }

    // Standard main method used exclusively for running our test scenarios.
    public static void main(String[] args) {
        // Run the first example test case from the provided image.
        runTest("Test Case 1 (From Image)", buildTree1(), List.of(1, 2, 4));
        
        // Run the second example test case from the provided image.
        runTest("Test Case 2 (From Image)", buildTree2(), List.of(1, 2, 5));
        
        // Run an edge case testing an empty tree structure.
        runTest("Edge Case: Empty Tree", null, List.of());
        
        // Run a large data scenario simulating a deep right-skewed tree branch.
        runTest("Large Data Case (Deep Skewed)", buildLargeTree(10000), generateExpectedLarge(10000));
    }

    // Helper method to compare actual output with expected output and print PASS/FAIL.
    private static void runTest(String testName, TreeNode root, List<Integer> expected) {
        // Call our solver method and store the actual returned list.
        var actual = getLeftView(root);
        // Check if the actual list perfectly matches the expected list.
        boolean isPass = Objects.equals(actual, expected);
        // Print the test name followed by PASS or FAIL based on the comparison.
        System.out.println(testName + ": " + (isPass ? "PASS" : "FAIL"));
        // If it fails, print additional debugging info so we know what went wrong.
        if (!isPass) System.out.println("   Expected: " + expected + ", Got: " + actual);
    }

    // Factory method building the exact tree from the first image example.
    private static TreeNode buildTree1() {
        // Build and return: [1, left: 2, right: [3, left: 4, right: null]]
        return new TreeNode(1, 
            new TreeNode(2, null, null), 
            new TreeNode(3, new TreeNode(4, null, null), null));
    }

    // Factory method building the exact tree from the second image example.
    private static TreeNode buildTree2() {
        // Build and return: [1, left: [2, right: 5], right: [3, left: 4]]
        return new TreeNode(1, 
            new TreeNode(2, null, new TreeNode(5, null, null)), 
            new TreeNode(3, new TreeNode(4, null, null), null));
    }

    // Factory method generating a very deep, right-skewed tree to test memory/stack limits.
    private static TreeNode buildLargeTree(int depth) {
        // Start from the bottom of the tree up to the root.
        TreeNode current = null;
        // Loop backwards from max depth down to 1.
        for (int i = depth; i >= 1; i--) {
            // Attach the current structure strictly to the right child of the new node.
            current = new TreeNode(i, null, current);
        }
        // Return the root of this massively deep tree.
        return current;
    }

    // Helper method to generate the expected result list for the large data test.
    private static List<Integer> generateExpectedLarge(int depth) {
        // Create an empty list to store the predicted sequence.
        var expected = new ArrayList<Integer>();
        // Loop from 1 to depth to simulate sequential nodes.
        for (int i = 1; i <= depth; i++) {
            // Add sequential numbers since they represent our visible right-skewed nodes.
            expected.add(i);
        }
        // Return the populated list representing expected values.
        return expected;
    }
}