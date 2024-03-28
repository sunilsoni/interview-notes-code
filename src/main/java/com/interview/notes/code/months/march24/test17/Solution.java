package com.interview.notes.code.months.march24.test17;


/**
 * Given a root node reference of a BST and a key, delete the node with the given key in the BST.
 * Return the root node reference (possibly updated) of the BST.
 */
public class Solution {
    public TreeNode1 deleteNode(TreeNode1 root, int key) {
        if (root == null) return null;

        // Search for the node.
        if (key < root.val) { // Go left
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) { // Go right
            root.right = deleteNode(root.right, key);
        } else { // Node found
            if (root.left == null) {
                return root.right; // Handle case 1 and 2
            } else if (root.right == null) {
                return root.left; // Handle case 1 and 2
            }

            // Case 3: Node has two children
            TreeNode1 minNode = findMin(root.right);
            root.val = minNode.val; // Replace with in-order successor
            root.right = deleteNode(root.right, root.val); // Delete the in-order successor
        }

        return root;
    }

    private TreeNode1 findMin(TreeNode1 node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}
