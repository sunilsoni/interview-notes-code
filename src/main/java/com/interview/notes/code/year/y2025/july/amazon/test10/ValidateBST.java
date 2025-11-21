// Package declaration for organizing code
package com.interview.notes.code.year.y2025.july.amazon.test10;

// Import statements for required Java utilities

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Problem: Validate if a given tree is a Binary Search Tree (BST)
 * A BST has these properties:
 * 1. Left subtree contains only nodes with values less than the node's value
 * 2. Right subtree contains only nodes with values greater than the node's value
 * 3. Both left and right subtrees must also be BSTs
 */
public class ValidateBST {

    // Public entry point for BST validation
    public static boolean isValidBST(TreeNode root) {
        // Start recursive validation with no bounds
        return isValidBSTHelper(root, null, null);
    }

    // Recursive helper method that checks BST properties with bounds
    private static boolean isValidBSTHelper(TreeNode node, Integer lower, Integer upper) {
        // Base case: empty tree is valid
        if (node == null) {
            return true;
        }

        // Get current node's value
        int val = node.value;

        // Check lower bound violation
        if (lower != null && val <= lower) {
            return false;
        }

        // Check upper bound violation
        if (upper != null && val >= upper) {
            return false;
        }

        // Recursively validate right subtree with updated lower bound
        if (!isValidBSTHelper(node.right, val, upper)) {
            return false;
        }

        // Recursively validate left subtree with updated upper bound
        return isValidBSTHelper(node.left, lower, val);

        // If all checks pass, this subtree is valid
    }

    // Utility method to create a balanced BST from sorted list
    public static TreeNode buildBSTFromSortedList(List<Integer> list) {
        return buildBSTHelper(list, 0, list.size() - 1);
    }

    // Recursive helper for BST construction
    private static TreeNode buildBSTHelper(List<Integer> list, int left, int right) {
        // Base case: invalid range
        if (left > right) {
            return null;
        }

        // Find middle element to maintain balance
        int mid = left + (right - left) / 2;

        // Create new node with middle element
        TreeNode node = new TreeNode(list.get(mid));

        // Recursively build left and right subtrees
        node.left = buildBSTHelper(list, left, mid - 1);
        node.right = buildBSTHelper(list, mid + 1, right);

        return node;
    }

    // Main method containing test cases
    public static void main(String[] args) {
        // Use LinkedHashMap to maintain test case order
        Map<String, TreeNode> testTrees = new LinkedHashMap<>();
        Map<String, Boolean> expected = new LinkedHashMap<>();

        // Test Case 1: Empty tree
        testTrees.put("Empty tree", null);
        expected.put("Empty tree", true);

        // Test Case 2: Single node tree
        testTrees.put("Single node", new TreeNode(42));
        expected.put("Single node", true);

        // Test Case 3: Valid small BST
        TreeNode small = new TreeNode(2);
        small.left = new TreeNode(1);
        small.right = new TreeNode(3);
        testTrees.put("Small valid BST", small);
        expected.put("Small valid BST", true);

        // Test Case 4: Invalid BST
        TreeNode bad1 = new TreeNode(2);
        bad1.left = new TreeNode(5);  // Violation: left child > parent
        bad1.right = new TreeNode(3);
        testTrees.put("Invalid BST 1", bad1);
        expected.put("Invalid BST 1", false);

        // Test Case 5: Large valid BST
        List<Integer> bigList = IntStream.rangeClosed(1, 100_000)
                .boxed()
                .collect(Collectors.toList());
        TreeNode bigValid = buildBSTFromSortedList(bigList);
        testTrees.put("Large valid BST", bigValid);
        expected.put("Large valid BST", true);

        // Test Case 6: Large invalid BST
        TreeNode bigInvalid = buildBSTFromSortedList(bigList);
        bigInvalid.left.value = 200_000;  // Introduce violation
        testTrees.put("Large invalid BST", bigInvalid);
        expected.put("Large invalid BST", false);

        // Execute all tests and print results
        System.out.println("BST Validation Tests:");
        testTrees.forEach((name, tree) -> {
            boolean result = isValidBST(tree);
            boolean exp = expected.get(name);
            String status = (result == exp) ? "PASS" : "FAIL";
            System.out.printf("%-20s : %s%n", name, status);
        });
    }

    // Inner class defining the structure of a tree node
    static class TreeNode {
        int value;          // Value stored in the node
        TreeNode left;      // Reference to left child node
        TreeNode right;     // Reference to right child node

        // Constructor to create a new node with given value
        TreeNode(int value) {
            this.value = value;
        }
    }
}
