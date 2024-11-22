package com.interview.notes.code.months.nov24.test17;

import java.util.LinkedList;
import java.util.Queue;
//WORKING: check if binary tree is symmetric leetcode
public class SymmetricTreeChecker {

    // Definition for a binary tree node.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int value) {
            this.val = value;
            left = null;
            right = null;
        }
    }

    /*
     * Function to check if a binary tree is symmetric.
     * 
     * @param root The root of the binary tree.
     * @return True if the tree is symmetric, False otherwise.
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    /*
     * Helper function to determine if two trees are mirror images.
     * 
     * @param left The root of the first subtree.
     * @param right The root of the second subtree.
     * @return True if the subtrees are mirrors, False otherwise.
     */
    private boolean isMirror(TreeNode left, TreeNode right) {
        // If both nodes are null, they are mirrors.
        if (left == null && right == null) return true;
        
        // If only one of the nodes is null, they are not mirrors.
        if (left == null || right == null) return false;
        
        // The current nodes must have the same value, and their subtrees must be mirrors.
        return (left.val == right.val)
                && isMirror(left.left, right.right)
                && isMirror(left.right, right.left);
    }

    /*
     * Main method to run test cases.
     */
    public static void main(String[] args) {
        SymmetricTreeChecker checker = new SymmetricTreeChecker();

        // Test Case 1: Symmetric Tree
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(2);
        root1.left.left = new TreeNode(3);
        root1.left.right = new TreeNode(4);
        root1.right.left = new TreeNode(4);
        root1.right.right = new TreeNode(3);
        System.out.println("Test Case 1: " + (checker.isSymmetric(root1) ? "PASS" : "FAIL")); // Expected: PASS

        // Test Case 2: Asymmetric Tree
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(2);
        root2.left.right = new TreeNode(3);
        root2.right.right = new TreeNode(3);
        System.out.println("Test Case 2: " + (checker.isSymmetric(root2) ? "FAIL" : "PASS")); // Expected: PASS

        // Test Case 3: Single Node
        TreeNode root3 = new TreeNode(1);
        System.out.println("Test Case 3: " + (checker.isSymmetric(root3) ? "PASS" : "FAIL")); // Expected: PASS

        // Test Case 4: Empty Tree
        TreeNode root4 = null;
        System.out.println("Test Case 4: " + (checker.isSymmetric(root4) ? "PASS" : "FAIL")); // Expected: PASS

        // Test Case 5: Large Symmetric Tree
        TreeNode root5 = buildLargeSymmetricTree(5);
        System.out.println("Test Case 5: " + (checker.isSymmetric(root5) ? "PASS" : "FAIL")); // Expected: PASS

        // Test Case 6: Large Asymmetric Tree
        TreeNode root6 = buildLargeAsymmetricTree(5);
        System.out.println("Test Case 6: " + (checker.isSymmetric(root6) ? "FAIL" : "PASS")); // Expected: PASS
    }

    /*
     * Helper function to build a large symmetric binary tree of given depth.
     */
    private static TreeNode buildLargeSymmetricTree(int depth) {
        if (depth == 0) return null;
        TreeNode root = new TreeNode(0);
        buildSymmetricSubtree(root, depth - 1);
        return root;
    }

    /*
     * Recursive helper to build symmetric subtrees.
     */
    private static void buildSymmetricSubtree(TreeNode node, int depth) {
        if (depth == 0) return;
        node.left = new TreeNode(depth);
        node.right = new TreeNode(depth);
        buildSymmetricSubtree(node.left, depth - 1);
        buildSymmetricSubtree(node.right, depth - 1);
    }

    /*
     * Helper function to build a large asymmetric binary tree of given depth.
     */
    private static TreeNode buildLargeAsymmetricTree(int depth) {
        if (depth == 0) return null;
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(depth);
        // Intentionally omit the right subtree to make it asymmetric
        buildSymmetricSubtree(root.left, depth - 1);
        return root;
    }
}
