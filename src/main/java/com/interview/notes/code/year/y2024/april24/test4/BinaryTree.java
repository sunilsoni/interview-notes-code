package com.interview.notes.code.year.y2024.april24.test4;

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int value) {
        val = value;
        left = right = null;
    }
}

public class BinaryTree {
    TreeNode root;
    TreeNode prev = null;

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.root = new TreeNode(4);
        tree.root.left = new TreeNode(2);
        tree.root.right = new TreeNode(5);
        tree.root.left.left = new TreeNode(1);
        tree.root.left.right = new TreeNode(3);

        if (tree.isBST())
            System.out.println("This tree is a BST");
        else
            System.out.println("This tree is not a BST");
    }

    boolean isBST() {
        return isBSTUtil(root);
    }

    boolean isBSTUtil(TreeNode node) {
        // Base case: empty tree is a BST
        if (node == null) return true;

        // Check the left subtree
        if (!isBSTUtil(node.left)) return false;

        // Check current node: value must be greater than prev node's value
        if (prev != null && node.val <= prev.val) return false;
        // Update prev to current node before moving to right subtree
        prev = node;

        // Check the right subtree
        return isBSTUtil(node.right);
    }
}
