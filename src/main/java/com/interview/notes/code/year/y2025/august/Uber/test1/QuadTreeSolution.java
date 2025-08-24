package com.interview.notes.code.year.y2025.august.Uber.test1;

import java.util.Arrays;

public class QuadTreeSolution {
    // Main function to construct QuadTree from 2D grid
    public static Node constructQuadTree(int[][] grid) {
        return buildTree(grid, 0, 0, grid.length);
    }

    // Recursive helper function to build the QuadTree
    private static Node buildTree(int[][] grid, int x, int y, int size) {
        // Base case: if size is 1, create a leaf node
        if (size == 1) {
            return new Node(grid[x][y], true);
        }

        // Calculate new size for subdivided quadrants
        int newSize = size / 2;

        // Recursively build all four quadrants
        Node topLeft = buildTree(grid, x, y, newSize);
        Node topRight = buildTree(grid, x, y + newSize, newSize);
        Node bottomLeft = buildTree(grid, x + newSize, y, newSize);
        Node bottomRight = buildTree(grid, x + newSize, y + newSize, newSize);

        // Check if all children are leaves with same value
        if (topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf &&
                topLeft.val == topRight.val && topRight.val == bottomLeft.val &&
                bottomLeft.val == bottomRight.val) {
            // If all values are same, merge into a single leaf node
            return new Node(topLeft.val, true);
        }

        // Create internal node with four children
        Node root = new Node(topLeft.val, false);
        root.topLeft = topLeft;
        root.topRight = topRight;
        root.bottomLeft = bottomLeft;
        root.bottomRight = bottomRight;
        return root;
    }

    // Test method to verify the QuadTree construction
    public static void main(String[] args) {
        // Test Case 1: Simple 4x4 grid
        int[][] grid1 = {
                {2, 2, 3, 3},
                {2, 2, 3, 3},
                {4, 2, 5, 5},
                {2, 3, 5, 5}
        };
        testQuadTree(grid1, "Test Case 1");

        // Test Case 2: Uniform grid
        int[][] grid2 = {
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1}
        };
        testQuadTree(grid2, "Test Case 2");

        // Test Case 3: Large grid (8x8)
        int[][] grid3 = new int[8][8];
        Arrays.stream(grid3).forEach(row -> Arrays.fill(row, 1));
        testQuadTree(grid3, "Test Case 3 (Large Grid)");
    }

    // Helper method to test and verify QuadTree construction
    private static void testQuadTree(int[][] grid, String testName) {
        System.out.println("Running " + testName);
        Node root = constructQuadTree(grid);
        System.out.println("QuadTree constructed successfully");
        // Verify the structure (basic validation)
        verifyQuadTree(root, grid.length);
        System.out.println(testName + ": PASS\n");
    }

    // Verify the QuadTree structure
    private static void verifyQuadTree(Node node, int size) {
        if (node == null) {
            throw new RuntimeException("Invalid node: null");
        }
        if (!node.isLeaf && size > 1) {
            verifyQuadTree(node.topLeft, size / 2);
            verifyQuadTree(node.topRight, size / 2);
            verifyQuadTree(node.bottomLeft, size / 2);
            verifyQuadTree(node.bottomRight, size / 2);
        }
    }

    // Node class representing each node in the QuadTree
    static class Node {
        int val; // Value stored in the node
        boolean isLeaf; // Flag to indicate if it's a leaf node
        Node topLeft; // Top-left child
        Node topRight; // Top-right child
        Node bottomLeft; // Bottom-left child
        Node bottomRight; // Bottom-right child

        // Constructor for creating a leaf node with a specific value
        public Node(int val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
        }
    }
}
