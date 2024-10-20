package com.interview.notes.code.months.oct24.test13;

import java.util.*;

public class CustomBinarySearchTreeValidator {

    // Define a TreeNode class for the binary tree structure
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
            left = right = null;
        }
    }

    // Method to build a binary tree from a list of integers
    public static TreeNode buildTree(List<Integer> nodes) {
        if (nodes.isEmpty()) return null;

        TreeNode root = new TreeNode(nodes.get(0));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int i = 1;
        while (i < nodes.size()) {
            TreeNode current = queue.poll();
            if (i < nodes.size()) {
                current.left = new TreeNode(nodes.get(i++));
                queue.add(current.left);
            }
            if (i < nodes.size()) {
                current.right = new TreeNode(nodes.get(i++));
                queue.add(current.right);
            }
        }
        return root;
    }

    // Recursive method to check if the tree is a valid customized BST
    public static boolean isCustomBST(TreeNode node) {
        return validate(node, null, null);
    }

    // Helper function to validate the customized BST rule
    public static boolean validate(TreeNode node, Integer lower, Integer upper) {
        if (node == null) return true;

        int val = node.val;

        // Validate left side (greater than node)
        if (lower != null && val <= lower) return false;

        // Validate right side (less than or equal to node)
        if (upper != null && val > upper) return false;

        // Recursively validate left and right subtrees
        if (!validate(node.left, val, upper)) return false;
        if (!validate(node.right, lower, val)) return false;

        return true;
    }

    // Method to process and validate multiple test cases
    public static void processTestCases(List<List<Integer>> testCases, List<Boolean> expectedResults) {
        for (int i = 0; i < testCases.size(); i++) {
            TreeNode root = buildTree(testCases.get(i));
            boolean result = isCustomBST(root);
            boolean expected = expectedResults.get(i);

            System.out.println("Test case " + (i + 1) + ": " + (result == expected ? "PASS" : "FAIL"));
        }
    }

    // Main method to run the tests
    public static void main(String[] args) {
        // Example test cases
        List<List<Integer>> testCases = Arrays.asList(
                Arrays.asList(10, 20, 5, 30, 15, 7, 3, 40, 29, 19, 14, 9, 6, 4, 1),
                Arrays.asList(10, 3, 14),
                Arrays.asList(5, 7, 2, 8, 6, 4, 4),
                Arrays.asList(5, 7, 2, 8, 6, 4, 2),
                Arrays.asList(10, 14, 3),
                Arrays.asList(10),
                Arrays.asList(5, 2, 7, 2, 4, 6, 8)
        );

        List<Boolean> expectedResults = Arrays.asList(true, false, false, true, true, true, false);

        processTestCases(testCases, expectedResults);
    }
}
