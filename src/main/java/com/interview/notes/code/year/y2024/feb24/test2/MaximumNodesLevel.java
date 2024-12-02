package com.interview.notes.code.year.y2024.feb24.test2;

import java.util.LinkedList;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left, right;

    public TreeNode(int val) {
        this.val = val;
    }
}

public class MaximumNodesLevel {
    public static void main(String[] args) {
        // Example usage:
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        MaximumNodesLevel solver = new MaximumNodesLevel();
        int maxLevel = solver.maxNodesLevel(root);
        System.out.println("Level with maximum number of nodes: " + maxLevel);
    }

    public int maxNodesLevel(TreeNode root) {
        if (root == null)
            return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int maxLevel = 0;
        int maxNodes = Integer.MIN_VALUE;
        int level = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            if (size > maxNodes) {
                maxNodes = size;
                maxLevel = level;
            }

            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                if (current.left != null)
                    queue.offer(current.left);
                if (current.right != null)
                    queue.offer(current.right);
            }
            level++;
        }

        return maxLevel + 1; // Levels are 0-indexed, so add 1 to get the actual level number
    }
}
