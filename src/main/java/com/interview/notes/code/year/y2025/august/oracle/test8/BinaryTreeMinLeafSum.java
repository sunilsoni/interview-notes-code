package com.interview.notes.code.year.y2025.august.oracle.test8;

public class BinaryTreeMinLeafSum {
    // Node class to represent each element in the binary tree
    static class Node {
        int data;        // Value stored in the node
        Node left;       // Reference to left child
        Node right;      // Reference to right child
        
        // Constructor to create a new node
        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
    
    // Class variable to store the minimum path sum found so far
    static int minSum = Integer.MAX_VALUE;
    
    // Main function to find minimum sum between leaf nodes
    public static int findMinLeafSum(Node root) {
        // Reset minSum for each new calculation
        minSum = Integer.MAX_VALUE;
        
        // Handle empty tree case
        if (root == null) return Integer.MIN_VALUE;
        
        // If tree has only root node, return MIN_VALUE as per requirement
        if (root.left == null && root.right == null) 
            return Integer.MIN_VALUE;
            
        // Start recursive calculation
        findMinSumUtil(root, 0);
        
        // Return result, or MIN_VALUE if no valid path found
        return minSum == Integer.MAX_VALUE ? Integer.MIN_VALUE : minSum;
    }
    
    // Utility function to recursively find minimum sum path
    private static int findMinSumUtil(Node node, int currentSum) {
        // Base case: if node is null, return 0
        if (node == null) return 0;
        
        // Add current node's value to path sum
        currentSum += node.data;
        
        // If it's a leaf node, return current sum
        if (node.left == null && node.right == null) {
            return currentSum;
        }
        
        // Recursively process left and right subtrees
        int leftSum = findMinSumUtil(node.left, currentSum);
        int rightSum = findMinSumUtil(node.right, currentSum);
        
        // If both children exist, update minSum if needed
        if (node.left != null && node.right != null) {
            minSum = Math.min(minSum, leftSum + rightSum - currentSum);
        }
        
        // Return the minimum path sum through this node
        return Math.min(leftSum, rightSum);
    }
    
    // Test method to verify solution
    public static void main(String[] args) {
        // Test Case 1: Example from the problem
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
        
        // Test Case 5: Large tree (create a deeper tree for stress testing)
        Node root5 = createLargeTree(10); // Creates a tree of height 10
        int result5 = findMinLeafSum(root5);
        System.out.println("Test Case 5 (Large tree): Completed without error");
    }
    
    // Helper method to create a large binary tree for testing
    private static Node createLargeTree(int height) {
        if (height == 0) return null;
        Node root = new Node(height);
        root.left = createLargeTree(height - 1);
        root.right = createLargeTree(height - 1);
        return root;
    }
}
