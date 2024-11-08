package com.interview.notes.code.months.nov24.amazon.test6;

import java.util.LinkedList;
import java.util.Queue;

class BinaryTree {
    TreeNode root;

    /**
     * Calculates the maximum width of the binary tree using position-based approach.
     *
     * @return The maximum width as an integer.
     */
    public long getMaximumWidth() {
        if (root == null) return 0;

        Queue<NodeWithIndex> queue = new LinkedList<>();
        queue.offer(new NodeWithIndex(root, 1)); // Root has index 1
        long maxWidth = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            long firstIndex = 0, lastIndex = 0;

            for (int i = 0; i < levelSize; i++) {
                NodeWithIndex current = queue.poll();
                TreeNode currentNode = current.node;
                long currentIndex = current.index;

                if (i == 0) firstIndex = currentIndex; // First node's index
                if (i == levelSize - 1) lastIndex = currentIndex; // Last node's index

                // Enqueue left child with index 2 * currentIndex
                if (currentNode.left != null) {
                    queue.offer(new NodeWithIndex(currentNode.left, 2 * currentIndex));
                }

                // Enqueue right child with index 2 * currentIndex + 1
                if (currentNode.right != null) {
                    queue.offer(new NodeWithIndex(currentNode.right, 2 * currentIndex + 1));
                }
            }

            // Calculate the width of the current level
            long currentWidth = lastIndex - firstIndex + 1;
            maxWidth = Math.max(maxWidth, currentWidth);
        }

        return maxWidth;
    }

    /**
     * Helper class to store a node along with its index.
     */
    private class NodeWithIndex {
        TreeNode node;
        long index; // Using long to prevent integer overflow for large trees

        NodeWithIndex(TreeNode node, long index) {
            this.node = node;
            this.index = index;
        }
    }
}
