package com.interview.notes.code.year.y2023.nov23.test2;

class Node {
    int key;
    Node left, right;

    public Node(int item) {
        key = item;
        left = right = null;
    }
}

public class BST {
    Node root;

    // Constructor for BST =>initial empty tree
    BST() {
        root = null;
    }

    public static void main(String[] args) {
        BST tree = new BST();

        /* Create a BST as follows:
              50
           /     \
          30      70
         /  \    /  \
       20   40  60   80 */

        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        // Print in-order traversal of the BST
        tree.inorder();
    }

    // Inorder traversal function
    // This method recursively calls inorderRec
    void inorder() {
        inorderRec(root);
    }

    // Recursive function to do inorder traversal
    void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left); // Visit left subtree
            System.out.print(root.key + " "); // Visit node
            inorderRec(root.right); // Visit right subtree
        }
    }

    // Insert method
    void insert(int key) {
        root = insertRec(root, key);
    }

    // Recursive insert function
    Node insertRec(Node root, int key) {
        // If the tree is empty, return a new node
        if (root == null) {
            root = new Node(key);
            return root;
        }

        // Otherwise, recur down the tree
        if (key < root.key) {
            root.left = insertRec(root.left, key);
        } else if (key > root.key) {
            root.right = insertRec(root.right, key);
        }

        // Return the (unchanged) node pointer
        return root;
    }
}
