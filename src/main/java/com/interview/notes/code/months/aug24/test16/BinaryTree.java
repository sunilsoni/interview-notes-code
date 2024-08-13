package com.interview.notes.code.months.aug24.test16;

import java.util.LinkedList;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int item) {
        val = item;
        left = right = null;
    }
}

public class BinaryTree {
    TreeNode root;

    // Main method with example test cases
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.root = new TreeNode(1);
        tree.root.left = new TreeNode(2);
        tree.root.right = new TreeNode(5);
        tree.root.left.left = new TreeNode(3);
        tree.root.left.right = new TreeNode(4);
        tree.root.right.left = new TreeNode(6);
        tree.root.right.right = new TreeNode(7);

        // Test Case: Sum at Level 2
        int level = 2;
        int result = tree.sumAtKthLevel(tree.root, level);
        System.out.println("Sum at Level " + level + " = " + result);

        // Additional test cases
        level = 0;
        result = tree.sumAtKthLevel(tree.root, level);
        System.out.println("Sum at Level " + level + " = " + result); // Expected: 1

        level = 1;
        result = tree.sumAtKthLevel(tree.root, level);
        System.out.println("Sum at Level " + level + " = " + result); // Expected: 7 (2+5)
    }

    // Method to find the sum at a given level
    int sumAtKthLevel(TreeNode root, int k) {
        if (root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int level = 0;
        int sum = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            if (level == k) {
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.poll();
                    sum += node.val;
                }
                break;
            }

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }

            level++;
        }

        return sum;
    }
}
