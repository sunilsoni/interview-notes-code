package com.interview.notes.code.year.y2024.aug24.test32;

public class BinarySearchTree {

    // Main method to test the rangeSumBST method with different cases.
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        // Example 1: Test case with a sample BST.
        TreeNode root1 = new TreeNode(10);
        root1.left = new TreeNode(5);
        root1.right = new TreeNode(15);
        root1.left.left = new TreeNode(3);
        root1.left.right = new TreeNode(7);
        root1.right.right = new TreeNode(18);

        int low1 = 7, high1 = 15;
        int result1 = bst.rangeSumBST(root1, low1, high1);
        System.out.println("Sum of values in range [" + low1 + ", " + high1 + "] is: " + result1); // Expected output: 32

        // Example 2: Test case with no nodes in range.
        TreeNode root2 = new TreeNode(10);
        root2.left = new TreeNode(5);
        root2.right = new TreeNode(15);
        root2.left.left = new TreeNode(2);
        root2.left.right = new TreeNode(6);
        root2.right.right = new TreeNode(20);

        int low2 = 25, high2 = 30;
        int result2 = bst.rangeSumBST(root2, low2, high2);
        System.out.println("Sum of values in range [" + low2 + ", " + high2 + "] is: " + result2); // Expected output: 0

        // Example 3: Test case with all nodes in range.
        TreeNode root3 = new TreeNode(8);
        root3.left = new TreeNode(3);
        root3.right = new TreeNode(10);
        root3.left.left = new TreeNode(1);
        root3.left.right = new TreeNode(6);
        root3.right.right = new TreeNode(14);

        int low3 = 1, high3 = 14;
        int result3 = bst.rangeSumBST(root3, low3, high3);
        System.out.println("Sum of values in range [" + low3 + ", " + high3 + "] is: " + result3); // Expected output: 42

        // Example 4: Test case with an empty tree.
        TreeNode root4 = null;

        int low4 = 1, high4 = 10;
        int result4 = bst.rangeSumBST(root4, low4, high4);
        System.out.println("Sum of values in range [" + low4 + ", " + high4 + "] is: " + result4); // Expected output: 0
    }

    // Method to return the sum of values within the range [low, high].
    public int rangeSumBST(TreeNode root, int low, int high) {
        return rangeSumBSTHelper(root, low, high);
    }

    // Helper method to recursively calculate the sum.
    private int rangeSumBSTHelper(TreeNode node, int low, int high) {
        if (node == null) return 0;

        int sum = 0;
        if (node.val < low) {
            // Skip left subtree, all values will be less than the current node value.
            sum += rangeSumBSTHelper(node.right, low, high);
        } else if (node.val > high) {
            // Skip right subtree, all values will be greater than the current node value.
            sum += rangeSumBSTHelper(node.left, low, high);
        } else {
            // Current node value is within the range.
            sum += node.val;
            // Continue to check both subtrees.
            sum += rangeSumBSTHelper(node.left, low, high);
            sum += rangeSumBSTHelper(node.right, low, high);
        }
        return sum;
    }

    // Definition for a binary tree node.
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int x) {
            val = x;
        }
    }
}
