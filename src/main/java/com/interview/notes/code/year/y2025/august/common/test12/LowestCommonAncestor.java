package com.interview.notes.code.year.y2025.august.common.test12;

/**
 * TreeNode class definition
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class LowestCommonAncestor {

    /**
     * Main method to test the LCA solution with various test cases
     */
    public static void main(String[] args) {
        System.out.println("=== Testing Lowest Common Ancestor ===\n");

        // Build the example tree: 
        //        3
        //       / \
        //      9   7
        //     / \   \
        //    2   6   4
        TreeNode node2 = new TreeNode(2);
        TreeNode node6 = new TreeNode(6);
        TreeNode node4 = new TreeNode(4);
        TreeNode node9 = new TreeNode(9, node2, node6);
        TreeNode node7 = new TreeNode(7, null, node4);
        TreeNode root = new TreeNode(3, node9, node7);

        // Test Case 1: p = 2, q = 6 (Expected: 9)
        testCase1(root, node2, node6);

        // Test Case 2: p = 7, q = 6 (Expected: 3)
        testCase2(root, node7, node6);

        // Test Case 3: p and q are the same node (Expected: p itself)
        testCase3(root, node2, node2);

        // Test Case 4: One node is direct ancestor of the other (Expected: ancestor)
        testCase4(root, node9, node2);

        // Test Case 5: p or q is the root (Expected: root)
        testCase5(root, root, node4);

        System.out.println("\n=== All tests completed ===");
    }

    /**
     * Finds the Lowest Common Ancestor of two nodes in a binary tree
     * <p>
     * Approach: Recursive DFS traversal
     * - If current node is null, return null
     * - If current node is either p or q, return current node
     * - Recursively search left and right subtrees
     * - If both left and right return non-null, current node is LCA
     * - Otherwise, return the non-null result from subtrees
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Base case: if root is null or we found p or q, return root
        if (root == null || root == p || root == q) {
            return root;
        }

        // Recursively search left and right subtrees
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // If both left and right return non-null, current node is the LCA
        if (left != null && right != null) {
            return root;
        }

        // Otherwise, return the non-null result (if any)
        return left != null ? left : right;
    }

    /**
     * Test Case 1: p = 2, q = 6 (Expected: 9)
     */
    private static void testCase1(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode result = lowestCommonAncestor(root, p, q);
        boolean passed = result != null && result.val == 9;
        System.out.println("Test Case 1 - p=2, q=6: " + (passed ? "PASS" : "FAIL"));
        if (!passed) {
            System.out.println("Expected: 9, Got: " + (result != null ? result.val : "null"));
        }
    }

    /**
     * Test Case 2: p = 7, q = 6 (Expected: 3)
     */
    private static void testCase2(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode result = lowestCommonAncestor(root, p, q);
        boolean passed = result != null && result.val == 3;
        System.out.println("Test Case 2 - p=7, q=6: " + (passed ? "PASS" : "FAIL"));
        if (!passed) {
            System.out.println("Expected: 3, Got: " + (result != null ? result.val : "null"));
        }
    }

    /**
     * Test Case 3: p and q are the same node (Expected: p itself)
     */
    private static void testCase3(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode result = lowestCommonAncestor(root, p, q);
        boolean passed = result == p;
        System.out.println("Test Case 3 - Same node: " + (passed ? "PASS" : "FAIL"));
        if (!passed) {
            System.out.println("Expected: " + p.val + ", Got: " + (result != null ? result.val : "null"));
        }
    }

    /**
     * Test Case 4: One node is direct ancestor of the other (Expected: ancestor)
     */
    private static void testCase4(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode result = lowestCommonAncestor(root, p, q);
        boolean passed = result == p; // node9 is ancestor of node2
        System.out.println("Test Case 4 - Direct ancestor: " + (passed ? "PASS" : "FAIL"));
        if (!passed) {
            System.out.println("Expected: " + p.val + ", Got: " + (result != null ? result.val : "null"));
        }
    }

    /**
     * Test Case 5: p or q is the root (Expected: root)
     */
    private static void testCase5(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode result = lowestCommonAncestor(root, p, q);
        boolean passed = result == root;
        System.out.println("Test Case 5 - Root involved: " + (passed ? "PASS" : "FAIL"));
        if (!passed) {
            System.out.println("Expected: " + root.val + ", Got: " + (result != null ? result.val : "null"));
        }
    }
}
