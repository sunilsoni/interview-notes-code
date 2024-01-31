package com.interview.notes.code.months.jan24.test6;

import java.util.LinkedList;
import java.util.Queue;

class BinaryTree {
    TreeNode root;

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.root = new TreeNode(1);
        tree.root.left = new TreeNode(2);
        tree.root.right = new TreeNode(3);
        tree.root.left.left = new TreeNode(4);
        tree.root.left.right = new TreeNode(5);
        tree.root.right.right = new TreeNode(6);
        tree.root.left.left.left = new TreeNode(7);

        int levelWithMostNodes = tree.findLevelWithMostNodes();
        System.out.println("Level with the most nodes: " + levelWithMostNodes);
    }

    public int findLevelWithMostNodes() {
        if (root == null) {
            return 0; // If the tree is empty, there are no levels with nodes.
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int maxLevel = 0;
        int maxNodeCount = 0;
        int currentLevel = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            if (levelSize > maxNodeCount) {
                maxNodeCount = levelSize;
                maxLevel = currentLevel;
            }

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            currentLevel++;
        }

        return maxLevel;
    }
}
