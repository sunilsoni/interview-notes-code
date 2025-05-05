package com.interview.notes.code.year.y2025.april.meta.test1.test2;

class Node {
    int data;
    Node left, right;

    Node(int data) {
        this.data = data;
        left = right = null;
    }
}

public class BinaryTreeToCircularList {
    private Node head = null; // Head of the circular list
    private Node prev = null; // Tracks previous node during traversal

    // Test method
    public static void main(String[] args) {
        // Test Case 1: Basic tree
        Node root1 = new Node(1);
        root1.left = new Node(2);
        root1.left.right = new Node(3);

        BinaryTreeToCircularList converter = new BinaryTreeToCircularList();
        Node result = converter.convertToCircular(root1);

        // Verify circular nature
        if (verifyCircular(result)) {
            System.out.println("Test Case 1: PASS");
        } else {
            System.out.println("Test Case 1: FAIL");
        }

        // Test Case 2: Empty tree
        if (converter.convertToCircular(null) == null) {
            System.out.println("Test Case 2 (Empty Tree): PASS");
        } else {
            System.out.println("Test Case 2 (Empty Tree): FAIL");
        }
    }

    // Helper method to verify circular nature
    private static boolean verifyCircular(Node head) {
        if (head == null) return true;

        Node current = head;
        do {
            // Check forward and backward links
            if (current.right == null || current.right.left != current) {
                return false;
            }
            current = current.right;
        } while (current != head);

        return true;
    }

    // Main conversion method
    public Node convertToCircular(Node root) {
        if (root == null) return null;

        // Step 1: Convert to DLL using inorder traversal
        convertToDLL(root);

        // Step 2: Make it circular by connecting head and last node
        makeCircular();

        return head;
    }

    // Helper method to convert tree to DLL using inorder traversal
    private void convertToDLL(Node node) {
        if (node == null) return;

        // Process left subtree
        convertToDLL(node.left);

        // Process current node
        if (prev == null) {
            // First node becomes head
            head = node;
        } else {
            // Connect current node with previous node
            node.left = prev;
            prev.right = node;
        }
        prev = node;

        // Process right subtree
        convertToDLL(node.right);
    }

    // Helper method to make the DLL circular
    private void makeCircular() {
        if (head == null) return;

        // Connect last node with head
        prev.right = head;
        head.left = prev;
    }
}
