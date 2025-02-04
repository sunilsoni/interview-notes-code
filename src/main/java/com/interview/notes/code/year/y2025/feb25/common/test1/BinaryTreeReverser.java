package com.interview.notes.code.year.y2025.feb25.common.test1;

/*
Write the code to reverse the nodes in this binary tree.

Input:
    J
   / \
  D   S
 / \ / \
A  G M  Z

Expected output:
    J
   / \
  S   D
 / \ / \
Z  M G  A
```


 */
class Node {
    char data;
    Node left, right;

    Node(char data) {
        this.data = data;
        left = right = null;
    }
}

public class BinaryTreeReverser {
    // Method to reverse the tree
    static Node reverseTree(Node root) {
        if (root == null) {
            return null;
        }

        // Swap left and right children
        Node temp = root.left;
        root.left = root.right;
        root.right = temp;

        // Recursively reverse subtrees
        reverseTree(root.left);
        reverseTree(root.right);

        return root;
    }

    // Helper method to print tree (for testing)
    static void printInOrder(Node root) {
        if (root == null) return;

        printInOrder(root.left);
        System.out.print(root.data + " ");
        printInOrder(root.right);
    }

    public static void main(String[] args) {
        // Test Case 1: Given example
        Node root1 = new Node('J');
        root1.left = new Node('D');
        root1.right = new Node('S');
        root1.left.left = new Node('A');
        root1.left.right = new Node('G');
        root1.right.left = new Node('M');
        root1.right.right = new Node('Z');

        System.out.println("Test Case 1:");
        System.out.println("Before reversal:");
        printInOrder(root1);
        root1 = reverseTree(root1);
        System.out.println("\nAfter reversal:");
        printInOrder(root1);

        // Test Case 2: Single node
        Node root2 = new Node('A');
        System.out.println("\n\nTest Case 2 (Single node):");
        System.out.println("Before reversal:");
        printInOrder(root2);
        root2 = reverseTree(root2);
        System.out.println("\nAfter reversal:");
        printInOrder(root2);

        // Test Case 3: Empty tree
        Node root3 = null;
        System.out.println("\n\nTest Case 3 (Empty tree):");
        System.out.println("Before reversal:");
        printInOrder(root3);
        root3 = reverseTree(root3);
        System.out.println("\nAfter reversal:");
        printInOrder(root3);

        // Test Case 4: Unbalanced tree
        Node root4 = new Node('A');
        root4.left = new Node('B');
        root4.left.left = new Node('C');
        System.out.println("\n\nTest Case 4 (Unbalanced tree):");
        System.out.println("Before reversal:");
        printInOrder(root4);
        root4 = reverseTree(root4);
        System.out.println("\nAfter reversal:");
        printInOrder(root4);
    }
}
