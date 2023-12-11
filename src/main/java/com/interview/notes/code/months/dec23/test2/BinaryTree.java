package com.interview.notes.code.months.dec23.test2;

class TreeNode {
    int data;
    TreeNode left, right;

    TreeNode(int value) {
        data = value;
        left = right = null;
    }
}

public class BinaryTree {
    TreeNode root;

    // DFS Traversal (Pre-order as an example: Root, Left, Right)
    void dfs(TreeNode node) {
        if (node == null) {
            return;
        }

        // Visiting the root node
        System.out.print(node.data + " ");

        // Traversing the left subtree
        dfs(node.left);

        // Traversing the right subtree
        dfs(node.right);
    }

    // Main method for demonstration
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.root = new TreeNode(1);
        tree.root.left = new TreeNode(2);
        tree.root.right = new TreeNode(3);
        tree.root.left.left = new TreeNode(4);
        tree.root.left.right = new TreeNode(5);

        System.out.println("DFS Traversal of binary tree is: ");
        tree.dfs(tree.root);
    }
}
