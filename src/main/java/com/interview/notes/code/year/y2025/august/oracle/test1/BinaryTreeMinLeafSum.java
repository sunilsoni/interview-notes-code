package com.interview.notes.code.year.y2025.august.oracle.test1;

public class BinaryTreeMinLeafSum {
    // Track minimum sum and whether we found a valid path
    static int minSum;
    static boolean foundValidPath;

    public static int findMinLeafSum(Node root) {
        // Reset static variables for new calculation
        minSum = Integer.MAX_VALUE;
        foundValidPath = false;

        // Handle base cases
        if (root == null) return Integer.MIN_VALUE;
        if (root.left == null && root.right == null) return Integer.MIN_VALUE;

        // Start recursive calculation
        findMinSumUtil(root);

        // Return result only if we found a valid path
        return foundValidPath ? minSum : Integer.MIN_VALUE;
    }

    private static int findMinSumUtil(Node node) {
        // Base case: null node
        if (node == null) return Integer.MAX_VALUE;

        // Base case: leaf node
        if (node.left == null && node.right == null) {
            return node.data;
        }

        // Get minimum sum from left and right subtrees
        int leftSum = findMinSumUtil(node.left);
        int rightSum = findMinSumUtil(node.right);

        // If both paths exist, update minimum sum
        if (leftSum != Integer.MAX_VALUE && rightSum != Integer.MAX_VALUE) {
            foundValidPath = true;
            minSum = Math.min(minSum, leftSum + rightSum - node.data);
        }

        // Return minimum path including current node
        return Math.min(leftSum, rightSum) == Integer.MAX_VALUE ?
                Integer.MAX_VALUE :
                Math.min(leftSum, rightSum) + node.data;
    }

    public static void main(String[] args) {
        // Test Case 1: Example from problem
        Node root1 = new Node(4);
        root1.left = new Node(5);
        root1.right = new Node(-6);
        root1.left.left = new Node(2);
        root1.left.right = new Node(-3);
        root1.right.left = new Node(1);
        root1.right.right = new Node(8);

        int result1 = findMinLeafSum(root1);
        System.out.println("Test Case 1: " + (result1 == 1 ? "PASS" : "FAIL") +
                " (Expected: 1, Got: " + result1 + ")");

        // Test Case 2: Second example
        Node root2 = new Node(3);
        root2.left = new Node(2);
        root2.right = new Node(4);
        root2.left.left = new Node(-5);
        root2.left.right = new Node(1);

        int result2 = findMinLeafSum(root2);
        System.out.println("Test Case 2: " + (result2 == -2 ? "PASS" : "FAIL") +
                " (Expected: -2, Got: " + result2 + ")");

        // Test Case 3: Empty tree
        int result3 = findMinLeafSum(null);
        System.out.println("Test Case 3 (Empty tree): " +
                (result3 == Integer.MIN_VALUE ? "PASS" : "FAIL"));

        // Test Case 4: Single node
        Node root4 = new Node(5);
        int result4 = findMinLeafSum(root4);
        System.out.println("Test Case 4 (Single node): " +
                (result4 == Integer.MIN_VALUE ? "PASS" : "FAIL"));

        // Test Case 5: Large tree
        Node root5 = createLargeTree(10);
        int result5 = findMinLeafSum(root5);
        System.out.println("Test Case 5 (Large tree): Completed without error");
    }

    private static Node createLargeTree(int height) {
        if (height == 0) return null;
        Node root = new Node(height);
        root.left = createLargeTree(height - 1);
        root.right = createLargeTree(height - 1);
        return root;
    }

    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
}
