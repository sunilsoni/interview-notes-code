package com.interview.notes.code.year.y2024.sept24.test12;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class MaxPathSum {

    private int maxSum;

    public static void main(String[] args) {
        // Construct a tree as per the example provided:
        //       -1
        //     -9    -20
        TreeNode root = new TreeNode(-1);
        root.left = new TreeNode(-9);
        root.right = new TreeNode(-20);

        MaxPathSum solution = new MaxPathSum();
        int result = solution.maxPathSum(root);
        System.out.println("Max path sum is: " + result);  // Expected output: -1
    }

    public int maxPathSum(TreeNode root) {
        // Initialize the maximum sum to the smallest possible value.
        maxSum = Integer.MIN_VALUE; // This ensures we correctly handle negative values.

        // Start the recursive calculation from the root node.
        calculateMaxPath(root);

        // Return the overall maximum path sum found.
        return maxSum;
    }

    private int calculateMaxPath(TreeNode node) {
        if (node == null) {
            // If the node is null, it doesn't contribute to the path sum.
            return 0;
        }

        // Recursively calculate the maximum path sum for the left and right subtrees.
        // If the sum is negative, return 0 to avoid reducing the overall path sum.
        int left = Math.max(calculateMaxPath(node.left), 0);
        int right = Math.max(calculateMaxPath(node.right), 0);

        // Calculate the maximum path sum passing through the current node.
        int currentMax = node.val + left + right;

        // Update the global maxSum with the maximum of itself and the current path sum.
        maxSum = Math.max(maxSum, currentMax);

        // Return the maximum sum path that can be extended upwards,
        // which is either the current node's value or the node plus the larger of its subtrees.
        return node.val + Math.max(left, right);
    }
}
