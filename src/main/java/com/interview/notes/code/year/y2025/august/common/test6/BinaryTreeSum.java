package com.interview.notes.code.year.y2025.august.common.test6;

// Class representing each node of the binary tree
class TreeNode {
    int val;            // Value of the node
    TreeNode left;      // Reference to left child
    TreeNode right;     // Reference to right child

    // Constructor to create a new node
    TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

public class BinaryTreeSum {

    // Recursive function to calculate sum of all nodes in the tree
    public static int sumOfNodes(TreeNode root) {
        if (root == null) {
            return 0;  // Base case: if tree is empty
        }
        // Sum = current node value + sum of left subtree + sum of right subtree
        return root.val + sumOfNodes(root.left) + sumOfNodes(root.right);
    }

    public static void main(String[] args) {
        // Step 1: Create a simple binary tree
        /*
                10
               /  \
              5    20
             / \   /
            3   7 15
        */
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(20);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.right.left = new TreeNode(15);

        // Step 2: Calculate sum of all nodes
        int totalSum = sumOfNodes(root);

        // Step 3: Print result
        System.out.println("Sum of all nodes in the binary tree: " + totalSum);
    }

    class TreePrinter {

        // Recursive function to print tree in a pretty way
        public static void printTree(TreeNode root, int space) {
            if (root == null) {
                return;
            }

            // Increase distance between levels
            space += 5;

            // Process right child first (so it prints on top)
            printTree(root.right, space);

            // Print current node after spaces
            System.out.println();
            for (int i = 5; i < space; i++) {
                System.out.print(" ");
            }
            System.out.println(root.val);

            // Process left child
            printTree(root.left, space);
        }
        public static void main(String[] args) {
            TreeNode root = new TreeNode(10);
            root.left = new TreeNode(5);
            root.right = new TreeNode(20);
            root.left.left = new TreeNode(3);
            root.left.right = new TreeNode(7);
            root.right.left = new TreeNode(15);

            System.out.println("Pretty Printed Tree:");
            TreePrinter.printTree(root, 0);
        }
    }
}