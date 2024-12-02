package com.interview.notes.code.year.y2024.feb24.test2;

import java.util.LinkedList;
import java.util.Queue;


class Solution {
    // Finds the level with the maximum number of nodes
    public int maxNodesLevel(TreeNode root) {
        if (root == null) return -1; // If the tree is empty

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0, maxNodes = 0, maxNodesLevel = 0, currentLevel = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size(); // Number of nodes at the current level
            if (levelSize > maxNodes) { // Update max if current level has more nodes
                maxNodes = levelSize;
                maxNodesLevel = currentLevel;
            }

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }

            currentLevel++; // Move to the next level
        }

        return maxNodesLevel;
    }
}
