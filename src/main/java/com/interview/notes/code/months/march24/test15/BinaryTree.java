package com.interview.notes.code.months.march24.test15;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {
    Node root;

    public static void main(String[] args) {
        // Create a binary tree
        BinaryTree tree = new BinaryTree();
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
        tree.root.right.left = new Node(6);
        tree.root.right.right = new Node(7);

        // Connect the nodes
        tree.connect(tree.root);

        // Print next pointers of all nodes
        System.out.println("Next pointers of all nodes:");
        tree.printNextPointers(tree.root);
    }

    void connect(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();

                if (i < size - 1) {
                    node.next = queue.peek();
                }

                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
    }

    // Method to print the next right pointers of all the nodes
    void printNextPointers(Node node) {
        while (node != null) {
            System.out.print(node.data + "->");
            node = node.next;
        }
        System.out.println("null");
    }
}
