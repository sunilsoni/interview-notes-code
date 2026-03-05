package com.interview.notes.code.year.y2026.march.common.test2;

public class BSTValidator { // Define the main public class that encapsulates our validation logic
    
    public static boolean isValidBST(TreeNode root) { // Public entry method that takes the root of the tree to evaluate
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE); // Kick off the recursive validation with the widest possible Long boundaries to prevent Integer overflow issues
    } // Close the public entry method

    private static boolean validate(TreeNode node, long min, long max) { // Private helper method that carries the current node and its strict allowable value boundaries
        if (node == null) return true; // Base case: an empty node doesn't violate BST rules, so we return true to stop this recursion branch

        if (node.val() <= min || node.val() >= max) return false; // Core logic: if the node's value falls outside the inherited min/max boundaries, the tree is invalid

        // Recursively validate left and right children.
        // For the left child, the current node's value becomes the new maximum limit.
        // For the right child, the current node's value becomes the new minimum limit.
        return validate(node.left(), min, node.val()) && validate(node.right(), node.val(), max); // Return true only if BOTH the left and right subtrees are completely valid
    } // Close the recursive helper method

    public static void main(String[] args) { // Standalone main method to run our test cases without relying on JUnit

        // --- Test Case 1: Standard Valid BST --- // Comment indicating the start of the first test
        TreeNode validRoot = new TreeNode(2, new TreeNode(1, null, null), new TreeNode(3, null, null)); // Construct a valid tree where root is 2, left is 1, right is 3
        boolean test1Result = isValidBST(validRoot); // Execute the validation method on our valid tree
        System.out.println("Test 1 (Valid Basic Tree): " + (test1Result ? "PASS" : "FAIL")); // Print PASS if the result is true, otherwise print FAIL

        // --- Test Case 2: Standard Invalid BST --- // Comment indicating the start of the second test
        TreeNode invalidRoot = new TreeNode(5, new TreeNode(1, null, null), new TreeNode(4, new TreeNode(3, null, null), new TreeNode(6, null, null))); // Construct an invalid tree (4 is on the right of 5, which is illegal)
        boolean test2Result = !isValidBST(invalidRoot); // Execute the validation, we expect false, so we invert it with '!' to register a PASS for the test logic
        System.out.println("Test 2 (Invalid Basic Tree): " + (test2Result ? "PASS" : "FAIL")); // Print PASS if the method correctly identified it as invalid

        // --- Test Case 3: Large Data / Deep Tree --- // Comment indicating the start of the large data test
        TreeNode largeTree = null; // Initialize an empty node reference to build our large tree upon
        for (int i = 0; i < 10000; i++) { // Initiate a loop to create 10,000 connected nodes
            largeTree = new TreeNode(-i, largeTree, null); // Prepend nodes to the left side sequentially to create a massively deep, valid, left-heavy BST
        } // Close the loop
        boolean test3Result = isValidBST(largeTree); // Execute the validation against our 10,000 node deep tree
        System.out.println("Test 3 (10k Node Deep Tree): " + (test3Result ? "PASS" : "FAIL")); // Print PASS if the deep tree validation completes successfully without stack overflow

    } // Close main method

    // Utilize Java 21 features: 'record' creates a concise, immutable data carrier without needing explicit getters or constructors
    record TreeNode(int val, TreeNode left, TreeNode right) {} // Defines the tree structure: an integer value, and references to left and right child nodes
} // Close class