package com.interview.notes.code.year.y2026.march.microsoft.test8;

import java.util.stream.Stream;

// Standard class to represent our Binary Tree nodes
class TreeNode { // Defining the blueprint for tree nodes
    int val; // Stores the numerical value of the node
    TreeNode left; // Pointer to the left child node
    TreeNode right; // Pointer to the right child node

    // Constructor to easily create leaf nodes
    TreeNode(int val) { this.val = val; } // Assigns the value and leaves children null
    
    // Constructor to create nodes with existing children
    TreeNode(int val, TreeNode left, TreeNode right) { // Accepts value and both children
        this.val = val; // Assigns the value to the node
        this.left = left; // Links the left child
        this.right = right; // Links the right child
    }
}

public class MaxPathSumSolver { // Main solver class
    
    private int globalMax = Integer.MIN_VALUE; // Global tracker for the absolute highest sum found

    // Main method for testing without external libraries like JUnit
    public static void main(String[] args) { // Application entry point

        // Building Test Case 1: The example from the prompt [1, 2, 3]
        TreeNode root1 = new TreeNode(1, new TreeNode(2), new TreeNode(3)); // Creates the 3-node tree

        // Building Test Case 2: Negative numbers and larger structure [-10, 9, 20, null, null, 15, 7]
        TreeNode root2 = new TreeNode(-10, // Root is negative
                new TreeNode(9), // Left child
                new TreeNode(20, new TreeNode(15), new TreeNode(7))); // Right child with its own children

        // Building Test Case 3: A single negative node (Edge case)
        TreeNode root3 = new TreeNode(-3); // Tree with only one node

        // Initializing the solver instance
        MaxPathSumSolver solver = new MaxPathSumSolver(); // Creates object to call non-static methods

        // Using Java 8 Stream API to process multiple test cases cleanly
        Stream.of( // Creates a stream of our test cases
            new TestCase("Prompt Example", root1, 6), // Expected: 2 + 1 + 3 = 6
            new TestCase("Complex Tree", root2, 42), // Expected: 15 + 20 + 7 = 42
            new TestCase("Single Negative", root3, -3) // Expected: -3 (must pick at least one node)
        ).forEach(tc -> { // Iterates through each test case
            int result = solver.maxPathSum(tc.root()); // Calculates the actual output
            boolean passed = (result == tc.expectedOutput()); // Checks if output matches expected

            // Prints the result directly to the console
            System.out.printf("Test: %-18s | Expected: %3d | Got: %3d | Status: %s%n", // Formats string
                    tc.testName(), tc.expectedOutput(), result, passed ? "PASS" : "FAIL"); // Injects values
        }); // Ends the stream operation
    }

    // Public method that initiates the process
    public int maxPathSum(TreeNode root) { // Takes the root of the tree
        globalMax = Integer.MIN_VALUE; // Resets global max in case method is called multiple times
        calculateGain(root); // Triggers the recursive depth-first search
        return globalMax; // Returns the final highest path sum found
    }

    // Recursive helper method to find the maximum contribution of a branch
    private int calculateGain(TreeNode node) { // Takes the current node being inspected
        if (node == null) return 0; // Base case: If node doesn't exist, its sum contribution is 0

        // Calculate left branch max. If it's negative, Math.max changes it to 0 (we ignore it)
        int leftGain = Math.max(calculateGain(node.left), 0);

        // Calculate right branch max. If it's negative, Math.max changes it to 0 (we ignore it)
        int rightGain = Math.max(calculateGain(node.right), 0);

        // Current path sum if this node is the highest point (peak) of the path
        int peakPathSum = node.val + leftGain + rightGain;

        // Update our global record if this peak path is higher than previous records
        globalMax = Math.max(globalMax, peakPathSum);

        // Return the best single straight path going UP from this node to its parent
        return node.val + Math.max(leftGain, rightGain);
    }

    // Java 16+ Record feature (perfect for Java 21) to hold test case data concisely
    record TestCase(String testName, TreeNode root, int expectedOutput) {} // Auto-generates getters
}