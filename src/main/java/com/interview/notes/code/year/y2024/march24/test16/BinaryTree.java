package com.interview.notes.code.year.y2024.march24.test16;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class Node {
    int data;
    Node left, right, next;

    public Node(int item) {
        data = item;
        left = right = next = null;
    }
}

public class BinaryTree {
    Node root;

    // Driver method to test above functions
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
        tree.root.right.left = new Node(6);
        tree.root.right.right = new Node(7);

        tree.connectReverse(tree.root);

        System.out.println("Next pointers of all nodes:");
        tree.printNextPointers(tree.root);
    }

    // Populates the 'next' pointers using reverse level order traversal
    Node connectReverse(Node root) {
        if (root == null) return null;

        Queue<Node> queue = new LinkedList<>();
        Stack<Node> stack = new Stack<>();

        queue.add(root);
        boolean reverse = false;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (reverse) {
                    if (!stack.isEmpty()) node.next = stack.peek();
                } else {
                    if (i < size - 1) node.next = queue.peek();
                }
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
                if (reverse) stack.push(node);
            }
            if (reverse) {
                while (!stack.isEmpty()) {
                    queue.add(stack.pop());
                }
            }
            reverse = !reverse;
        }
        return root;
    }

    // Method to print the next right pointers of all the nodes
    void printNextPointers(Node node) {
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
    }
}
