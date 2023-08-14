package com.interview.notes.code.july23.test12;

/**
 * You are given the root of a binary search tree (BST) where the values of exactly two nodes
 * of the tree were swapped by mistake. Recover the tree without changing its structure.
 *
 * Example 1:
 * Input: root = [1,3,null,null,2]
 * Output: [3,1,null,null,2]
 * Explanation: 3 cannot be a left child of 1 because 3 > 1. Swapping 1
 * and 3 makes the BST valid.
 *
 *
 * Example 2:
 * Input: root = [3,1,4,null,null,2]
 * Output: [2,1,4,0011,0011,3]
 * Explanation: 2 cannot be in the right subtree of 3 becaose 2 < 3.
 * Swapping 2 and 3 makes the BST valid.
 * Constraints:
 * •   The number of nodes in the tree is in the range [2, 1000].
 * •     -231 <= Node.val <= 231 - 1
 * Follow up: A solution using 0(n) space is pretty straight-forward. Could you devise a
 * constant 0(1) space solution?
 */
class RecoverTree {
    public void recoverTree(TreeNode root) {
        // Initialize pointers to keep track of the two nodes to swap and the previous node in traversal
        TreeNode first = null, second = null, prev = null;
        
        // Current node in traversal
        TreeNode current = root;
        
        while (current != null) {
            // If the left node is null, we visit the current node
            if (current.left == null) {
                // Check if previous node's value is greater than the current node's value
                if (prev != null && prev.val > current.val) {
                    if (first == null) {
                        first = prev; // Found the first element
                    }
                    second = current; // Found the second element
                }
                prev = current;
                current = current.right; // Move to the right node
            } else {
                // If left node is not null, we find the inorder predecessor (rightmost node of left subtree)
                TreeNode predecessor = current.left;
                while (predecessor.right != null && predecessor.right != current) {
                    predecessor = predecessor.right;
                }
                if (predecessor.right == null) {
                    // Link the right node of the predecessor to the current node to keep track of the traversal
                    predecessor.right = current;
                    current = current.left; // Move to the left node
                } else {
                    // Unlink the right node of the predecessor, as it's already visited
                    predecessor.right = null;
                    if (prev != null && prev.val > current.val) {
                        if (first == null) {
                            first = prev; // Found the first element
                        }
                        second = current; // Found the second element
                    }
                    prev = current;
                    current = current.right; // Move to the right node
                }
            }
        }
        
        // Swap the values of the first and second nodes
        if (first != null && second != null) {
            int temp = first.val;
            first.val = second.val;
            second.val = temp;
        }
    }
}
