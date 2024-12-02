package com.interview.notes.code.year.y2024.nov24.amazon.test5;

public class TreeWidthCalculator {
    public static void main(String[] args) {
        // Test Case 1: Basic tree
        TreeNode test1 = createBasicTree();
        runTest("Basic Tree Test", test1, 7);

        // Test Case 2: Single node tree
        TreeNode test2 = new TreeNode(1);
        runTest("Single Node Test", test2, 1);

        // Test Case 3: Empty tree
        runTest("Empty Tree Test", null, 0);

        // Test Case 4: Left-skewed tree
        TreeNode test4 = createLeftSkewedTree(5);
        runTest("Left-skewed Tree Test", test4, 1);

        // Test Case 5: Large balanced tree
        TreeNode test5 = createLargeBalancedTree(10);
        runTest("Large Balanced Tree Test", test5, 512); // 2^9 nodes at level 9

        // Test Case 6: Asymmetric tree
        TreeNode test6 = createAsymmetricTree();
        runTest("Asymmetric Tree Test", test6, 4);
    }

    private static int findMaxWidth(TreeNode root) {
        if (root == null) return 0;

        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
        queue.offer(root);
        int maxWidth = 1;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            maxWidth = Math.max(maxWidth, levelSize);

            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                if (current.left != null) queue.offer(current.left);
                if (current.right != null) queue.offer(current.right);
            }
        }

        return maxWidth;
    }

    private static void runTest(String testName, TreeNode root, int expectedWidth) {
        int actualWidth = findMaxWidth(root);
        boolean passed = actualWidth == expectedWidth;
        System.out.println(testName + ": " + (passed ? "PASS" : "FAIL"));
        System.out.println("Expected: " + expectedWidth + ", Got: " + actualWidth + "\n");
    }

    private static TreeNode createBasicTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        return root;
    }

    private static TreeNode createLeftSkewedTree(int height) {
        TreeNode root = new TreeNode(1);
        TreeNode current = root;
        for (int i = 2; i <= height; i++) {
            current.left = new TreeNode(i);
            current = current.left;
        }
        return root;
    }

    private static TreeNode createLargeBalancedTree(int height) {
        return createBalancedTreeHelper(1, height);
    }

    private static TreeNode createBalancedTreeHelper(int currentLevel, int maxLevel) {
        if (currentLevel > maxLevel) return null;

        TreeNode node = new TreeNode(currentLevel);
        node.left = createBalancedTreeHelper(currentLevel + 1, maxLevel);
        node.right = createBalancedTreeHelper(currentLevel + 1, maxLevel);
        return node;
    }

    private static TreeNode createAsymmetricTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        return root;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }
}