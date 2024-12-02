package com.interview.notes.code.year.y2024.sept24.test15;

class BinaryTree {
    // Function to check if a tree is single-valued and calculate the size of the largest single-valued subtree
    private static int findLargestSingleValuedSubtree(TreeNode node, Result result) {
        // Base case: if the node is null, return 0 (no nodes in this subtree)
        if (node == null) {
            return 0;
        }

        // Recursively calculate the size of the left and right subtrees
        int leftSize = findLargestSingleValuedSubtree(node.left, result);
        int rightSize = findLargestSingleValuedSubtree(node.right, result);

        // Check if the current node forms a single-valued subtree
        if ((node.left == null || node.left.val == node.val) &&
                (node.right == null || node.right.val == node.val)) {

            // The current node forms a single-valued subtree
            int subtreeSize = leftSize + rightSize + 1;

            // Update the maximum size of the single-valued subtree
            result.maxSize = Math.max(result.maxSize, subtreeSize);

            // Return the size of this single-valued subtree
            return subtreeSize;
        }

        // If the current node doesn't form a single-valued subtree, return 0
        return 0;
    }

    // Function to find the size of the largest single-valued subtree
    public static int largestSingleValuedSubtree(TreeNode root) {
        Result result = new Result();
        findLargestSingleValuedSubtree(root, result);
        return result.maxSize;
    }

    // Main method to test the solution
    public static void main(String[] args) {
        // Example tree:
        //           1
        //         /   \
        //        2     3
        //       / \     \
        //      2   2     3
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(2);
        root.right.right = new TreeNode(3);

        // The expected output is 2, as the largest single-valued subtree has 2 nodes
        System.out.println("Size of the largest single-valued subtree: " + largestSingleValuedSubtree(root));
    }

    // Definition for a binary tree node.
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
            left = null;
            right = null;
        }
    }

    // Class to store the result
    static class Result {
        int maxSize = 0; // To track the size of the largest single-valued subtree
    }
}
