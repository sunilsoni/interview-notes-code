package com.interview.notes.code.months.sept24.test11;
/*
Given a binary tree, write a function that returns the maximum path sum. The path can start and end at any node in the tree, and the path must contain at least one node.
The path sum of a path is the sum of all the node values along that path.
Example:
Input: A binary tree like this:
-10
/\
9 20
15 7
Output: 42
Explanation: The path with the maximum sum is 15 → 20 → 7, which gives a sum of 42.

 */
// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class MaxPathSum {

    // Global variable to store the maximum path sum
    private int maxSum;

    public int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        calculateMaxPath(root);
        return maxSum;
    }

    // Helper function to calculate max path sum recursively
    private int calculateMaxPath(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // Recursively calculate the maximum path sum of the left and right children
        int left = Math.max(calculateMaxPath(node.left), 0); // ignore paths with negative sum
        int right = Math.max(calculateMaxPath(node.right), 0); // ignore paths with negative sum

        // Calculate the path sum passing through the current node
        int currentMax = node.val + left + right;

        // Update the global maxSum
        maxSum = Math.max(maxSum, currentMax);

        // Return the maximum sum path that can be extended upwards
        return node.val + Math.max(left, right);
    }

    public static void main(String[] args) {
        // Test case: Construct the tree from the given example
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        MaxPathSum solution = new MaxPathSum();
        int result = solution.maxPathSum(root);
        System.out.println("Max path sum is: " + result); // Expected output: 42
    }
}
