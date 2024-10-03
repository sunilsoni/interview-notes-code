package com.interview.notes.code.months.sept24.test15;

class Solution {
    private int count = 0;

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(2);
        root1.left.right = new TreeNode(2);
        root1.right.right = new TreeNode(3);

        int result1 = solution.countLargestSingleValueSubtrees(root1);
        System.out.println("Test Case 1: " + (result1 == 2 ? "PASS" : "FAIL") + " (Expected: 2, Got: " + result1 + ")");

        // Test Case 2: Empty tree
        TreeNode root2 = null;
        int result2 = solution.countLargestSingleValueSubtrees(root2);
        System.out.println("Test Case 2: " + (result2 == 0 ? "PASS" : "FAIL") + " (Expected: 0, Got: " + result2 + ")");

        // Test Case 3: Single node tree
        TreeNode root3 = new TreeNode(1);
        int result3 = solution.countLargestSingleValueSubtrees(root3);
        System.out.println("Test Case 3: " + (result3 == 1 ? "PASS" : "FAIL") + " (Expected: 1, Got: " + result3 + ")");

        // Test Case 4: Large tree with multiple single-valued subtrees
        TreeNode root4 = new TreeNode(5);
        root4.left = new TreeNode(4);
        root4.right = new TreeNode(5);
        root4.left.left = new TreeNode(4);
        root4.left.right = new TreeNode(4);
        root4.right.right = new TreeNode(5);
        root4.left.left.left = new TreeNode(4);
        root4.left.left.right = new TreeNode(4);
        root4.right.right.left = new TreeNode(5);
        root4.right.right.right = new TreeNode(5);

        int result4 = solution.countLargestSingleValueSubtrees(root4);
        System.out.println("Test Case 4: " + (result4 == 3 ? "PASS" : "FAIL") + " (Expected: 3, Got: " + result4 + ")");
    }

    public int countLargestSingleValueSubtrees(TreeNode root) {
        count = 0;
        isSingleValueSubtree(root);
        return count;
    }

    private boolean isSingleValueSubtree(TreeNode node) {
        if (node == null) {
            return true;
        }

        boolean left = isSingleValueSubtree(node.left);
        boolean right = isSingleValueSubtree(node.right);

        if (left && right) {
            if (node.left != null && node.val != node.left.val) {
                return false;
            }
            if (node.right != null && node.val != node.right.val) {
                return false;
            }
            count++;
            return true;
        }

        return false;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }
}