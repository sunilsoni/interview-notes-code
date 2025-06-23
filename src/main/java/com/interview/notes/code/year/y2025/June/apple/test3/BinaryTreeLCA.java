package com.interview.notes.code.year.y2025.June.apple.test3;

public class BinaryTreeLCA {
    // Main method to find Lowest Common Ancestor
    public static TreeNode findLowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Base case: if root is null or matches either p or q
        if (root == null || root == p || root == q) {
            return root;
        }

        // Recursively search in left and right subtrees
        TreeNode leftSearch = findLowestCommonAncestor(root.left, p, q);
        TreeNode rightSearch = findLowestCommonAncestor(root.right, p, q);

        // If both left and right searches return non-null, root is LCA
        if (leftSearch != null && rightSearch != null) {
            return root;
        }

        // Return non-null result from either left or right search
        return leftSearch != null ? leftSearch : rightSearch;
    }

    // Helper method to find a node with given value
    private static TreeNode findNode(TreeNode root, int val) {
        if (root == null || root.val == val) {
            return root;
        }
        TreeNode left = findNode(root.left, val);
        return left != null ? left : findNode(root.right, val);
    }

    // Test method with various test cases
    public static void main(String[] args) {
        // Test Case 1: Basic tree
        TreeNode root1 = createSampleTree();
        testLCA(root1, 5, 1, 3, "Test Case 1");

        // Test Case 2: Same subtree nodes
        testLCA(root1, 5, 4, 5, "Test Case 2");

        // Test Case 3: One node is ancestor of other
        testLCA(root1, 5, 6, 5, "Test Case 3");

        // Test Case 4: Edge case - single node tree
        TreeNode singleNode = new TreeNode(1);
        testLCA(singleNode, 1, 1, 1, "Test Case 4");

        // Test Case 5: Large tree test
        TreeNode largeTree = createLargeTree(10); // Creates a tree with 1023 nodes
        testLCA(largeTree, 2, 3, 1, "Test Case 5 (Large Tree)");
    }

    // Helper method to create sample tree from problem statement
    private static TreeNode createSampleTree() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        return root;
    }

    // Helper method to create a large balanced tree for testing
    private static TreeNode createLargeTree(int depth) {
        if (depth == 0) return null;
        TreeNode root = new TreeNode(depth);
        root.left = createLargeTree(depth - 1);
        root.right = createLargeTree(depth - 1);
        return root;
    }

    // Helper method to run and verify test cases
    private static void testLCA(TreeNode root, int p, int q, int expectedLCA, String testName) {
        TreeNode pNode = findNode(root, p);
        TreeNode qNode = findNode(root, q);
        TreeNode result = findLowestCommonAncestor(root, pNode, qNode);

        boolean passed = result != null && result.val == expectedLCA;
        System.out.printf("%s: %s (Expected: %d, Got: %d)%n",
                testName,
                passed ? "PASS" : "FAIL",
                expectedLCA,
                result != null ? result.val : -1);
    }

    // Node structure for binary tree
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }
}
