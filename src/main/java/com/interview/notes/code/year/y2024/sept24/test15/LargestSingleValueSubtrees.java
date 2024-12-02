package com.interview.notes.code.year.y2024.sept24.test15;

public class LargestSingleValueSubtrees {

    // Variable to keep track of the count of largest single-valued subtrees
    int count = 0;

    public static void main(String[] args) {
        LargestSingleValueSubtrees tree = new LargestSingleValueSubtrees();

        // Test Case 1
        // Constructing the tree:
        //       1
        //     /   \
        //    2     3
        //   / \     \
        //  2   2     3
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(2);
        root1.left.right = new TreeNode(2);
        root1.right.right = new TreeNode(3);

        int result1 = tree.countLargestSingleValuedSubtrees(root1);
        System.out.println("Output: " + result1);
        System.out.println("Expected Output: 2");
        System.out.println("Test Case 1 " + (result1 == 2 ? "Passed" : "Failed"));

        // Additional Test Cases can be added below
        // Example: Tree with all nodes having the same value
        // Test Case 2
        // Constructing the tree:
        //       1
        //     /   \
        //    1     1
        //   / \   / \
        //  1   1 1   1
        LargestSingleValueSubtrees tree2 = new LargestSingleValueSubtrees();
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(1);
        root2.left.left = new TreeNode(1);
        root2.left.right = new TreeNode(1);
        root2.right.left = new TreeNode(1);
        root2.right.right = new TreeNode(1);

        int result2 = tree2.countLargestSingleValuedSubtrees(root2);
        System.out.println("Output: " + result2);
        System.out.println("Expected Output: 1");
        System.out.println("Test Case 2 " + (result2 == 1 ? "Passed" : "Failed"));
    }

    /**
     * Method to initiate the counting of largest single-valued subtrees.
     *
     * @param root The root of the binary tree.
     * @return The count of largest single-valued subtrees.
     */
    public int countLargestSingleValuedSubtrees(TreeNode root) {
        count = 0;
        isSingleValuedSubtree(root, null);
        return count;
    }

    /**
     * Recursive method to determine if a subtree is single-valued and count the largest ones.
     *
     * @param node        The current node being checked.
     * @param parentValue The value of the parent node.
     * @return True if the subtree rooted at the current node is single-valued.
     */
    private boolean isSingleValuedSubtree(TreeNode node, Integer parentValue) {
        // Base case: An empty subtree is considered single-valued
        if (node == null) {
            return true;
        }

        // Recursively check left and right subtrees
        boolean isLeftSingle = isSingleValuedSubtree(node.left, node.val);
        boolean isRightSingle = isSingleValuedSubtree(node.right, node.val);

        // If either left or right subtree is not single-valued, current subtree can't be single-valued
        if (!isLeftSingle || !isRightSingle) {
            return false;
        }

        // Check if the left child has a different value
        if (node.left != null && node.left.val != node.val) {
            return false;
        }

        // Check if the right child has a different value
        if (node.right != null && node.right.val != node.val) {
            return false;
        }

        // If current node's value is different from parent's value, it's the root of a largest single-valued subtree
        if (parentValue == null || node.val != parentValue) {
            count++;
        }

        return true;
    }

    // Class to represent a node in the binary tree
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int value) {
            this.val = value;
            left = right = null;
        }
    }
}
