package com.interview.notes.code.year.y2024.sept24.test15;

class SingleValuedSubtreeCounter {

    // Main method to process test cases
    public static void main(String[] args) {
        // Test Case 1: Example provided in the problem
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(2);
        root.right.right = new TreeNode(3);

        SingleValuedSubtreeCounter counter = new SingleValuedSubtreeCounter();
        int result = counter.countSingleValuedSubtrees(root);
        System.out.println("Output: " + result);  // Expected Output: 2
    }

    // Method to count the largest single-valued subtrees
    public int countSingleValuedSubtrees(TreeNode root) {
        int[] count = new int[1]; // To store the count of single-valued subtrees
        isSingleValued(root, count);
        return count[0];
    }

    // Helper method to check if the subtree rooted at 'node' is single-valued
    private boolean isSingleValued(TreeNode node, int[] count) {
        // Base case: If node is null, it is trivially single-valued
        if (node == null) {
            return true;
        }

        // Recursively check left and right subtrees
        boolean leftSingle = isSingleValued(node.left, count);
        boolean rightSingle = isSingleValued(node.right, count);

        // Check if both left and right subtrees are single-valued
        if (leftSingle && rightSingle) {
            // If left child exists, check if its value matches the current node
            if (node.left != null && node.left.val != node.val) {
                return false;
            }
            // If right child exists, check if its value matches the current node
            if (node.right != null && node.right.val != node.val) {
                return false;
            }
            // Current node forms a single-valued subtree, increment count
            count[0]++;
            return true;
        }
        return false;
    }

    // TreeNode class definition
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
            left = right = null;
        }
    }
}
