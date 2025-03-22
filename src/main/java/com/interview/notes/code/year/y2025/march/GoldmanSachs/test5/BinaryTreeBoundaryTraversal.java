package com.interview.notes.code.year.y2025.march.GoldmanSachs.test5;

/*
Problem Description
Write a Java program that performs a boundary traversal of a binary tree. The boundary traversal should print:

The root node.
The left boundary in a top-down fashion (excluding any leaf nodes).
All leaf nodes from left to right.
The right boundary in a bottom-up fashion (excluding any leaf nodes).
A key requirement is that a leaf node (for example, the leftmost node like number 5 or the rightmost like number 11) should be printed only once—even though it might be encountered in both the boundary functions and the leaf traversal.

 */
public class BinaryTreeBoundaryTraversal {

    // Function to perform the boundary traversal
    public static void printBoundary(Node root) {
        if (root == null) {
            return; // Empty tree
        }

        // 1. Print the root node (if it is not a leaf)
        System.out.print(root.val + " ");

        // 2. Print the left boundary (excluding leaf nodes)
        printLeftBoundary(root.left);

        // 3. Print all leaf nodes (left subtree, then right subtree)
        printLeaves(root.left);
        printLeaves(root.right);

        // 4. Print the right boundary (excluding leaf nodes, in reverse order)
        printRightBoundary(root.right);
    }

    // Helper method to print the left boundary in top-down manner.
    private static void printLeftBoundary(Node node) {
        if (node == null) {
            return;
        }

        // Check if this node is not a leaf node.
        if (node.left != null || node.right != null) {
            System.out.print(node.val + " ");
        }

        // Prefer the left child; if absent, use the right child.
        if (node.left != null) {
            printLeftBoundary(node.left);
        } else if (node.right != null) {
            printLeftBoundary(node.right);
        }
    }

    // Helper method to print all leaf nodes in the tree.
    private static void printLeaves(Node node) {
        if (node == null) {
            return;
        }

        printLeaves(node.left);

        // If node is a leaf, print its value.
        if (node.left == null && node.right == null) {
            System.out.print(node.val + " ");
        }

        printLeaves(node.right);
    }

    // Helper method to print the right boundary in bottom-up manner.
    private static void printRightBoundary(Node node) {
        if (node == null) {
            return;
        }

        // Recurse first: prefer the right child; if absent, use the left child.
        if (node.right != null) {
            printRightBoundary(node.right);
        } else if (node.left != null) {
            printRightBoundary(node.left);
        }

        // Print the node if it is not a leaf (printing on the way back up)
        if (node.left != null || node.right != null) {
            System.out.print(node.val + " ");
        }
    }

    // Example usage
    public static void main(String[] args) {
        // Constructing an example binary tree:
        //
        //           1
        //         /   \
        //        2     7
        //       / \   / \
        //      3   4 8   9
        //     / \       / \
        //    5   6    10  11
        //
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(7);
        root.left.left = new Node(3);
        root.left.right = new Node(4);
        root.left.left.left = new Node(5);
        root.left.left.right = new Node(6);
        root.right.left = new Node(8);
        root.right.right = new Node(9);
        root.right.right.left = new Node(10);
        root.right.right.right = new Node(11);

        System.out.println("Boundary traversal of the binary tree:");
        printBoundary(root);
    }

    // Define a Node of the tree.
    static class Node {
        int val;
        Node left, right;

        Node(int val) {
            this.val = val;
            left = right = null;
        }
    }
}
