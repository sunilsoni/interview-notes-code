package com.interview.notes.code.months.year2023.Sep23.amazon;

public class Solution {
    private int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        if (root != null) {
            // First, traverse the right subtree (greater values)
            convertBST(root.right);

            // Update the current node's value with the accumulated sum
            sum += root.val;
            root.val = sum;

            // Now, traverse the left subtree (smaller values)
            convertBST(root.left);
        }
        return root;
    }
}
