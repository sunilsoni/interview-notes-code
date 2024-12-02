package com.interview.notes.code.year.y2024.april24.test7;

/**
 * Complexity Analysis:
 * <p>
 * Time complexity: O(n) for clearing the bits and O(log n) for updating parents due to the binary tree nature, where n is the number of leaf nodes.
 * Space complexity: O(n) for the storage of the tree in the 2D array.
 * <p>
 * Brief Overview:
 * The task is to represent a perfect binary tree in a 2D array and to implement a rule where each parent node is set to 1 if both of its children are 1, otherwise it is set to 0. The goal is to populate the tree based on the leaves and apply the rule upwards until the root is determined.
 * <p>
 * Step-by-Step Plan:
 * <p>
 * Create a 2D array with dimensions that accommodate the levels of the binary tree (For 4 levels, we'll need [4][] since levels are zero-indexed).
 * Populate the last row of the 2D array with the leaf nodes' values.
 * Iterate from the second-to-last level up to the root, at each step determining the parent node's value based on the rule given.
 * Print the tree to visually confirm the structure and values.
 */
public class ClearBits {
    private static final int NUM_LEVELS = 4;
    private static final int[][] bits = new int[NUM_LEVELS][];

    static {
        // Initialize the bits array with the appropriate number of nodes at each level
        for (int i = 0; i < NUM_LEVELS; i++) {
            bits[i] = new int[(int) Math.pow(2, i)];
        }
    }

    public static void main(String[] args) {
        ClearBits solution = new ClearBits();

        // Example: Initialize all leaf bits to 1
        for (int i = 0; i < bits[NUM_LEVELS - 1].length; i++) {
            bits[NUM_LEVELS - 1][i] = 1;
        }

        // Update all parent nodes based on the initialized leaf nodes
        solution.updateParents(0, bits[NUM_LEVELS - 1].length / 2, NUM_LEVELS - 2);

        // Print the tree before clearing bits
        System.out.println("Tree before clearing bits:");
        solution.printTree();

        // Clear bits and print the tree after clearing bits
        solution.clearBits(2, 3); // Example call to clearBits
        System.out.println("\nTree after clearing bits:");
        solution.printTree();
    }

    public void clearBits(int index, int len) {
        // Check for invalid index or length
        if (index < 0 || len < 0 || index >= bits[NUM_LEVELS - 1].length) {
            throw new IllegalArgumentException("Invalid index or length.");
        }

        // Adjust length if it exceeds the array bounds
        len = Math.min(len, bits[NUM_LEVELS - 1].length - index);

        // Check if there's any work to do
        if (len == 0) {
            return; // No bits to clear
        }

        // Clear the bits in the specified range
        for (int i = index; i < index + len; i++) {
            bits[NUM_LEVELS - 1][i] = 0;
        }

        // Update the parent nodes accordingly
        updateParents(index / 2, len / 2, NUM_LEVELS - 2);
    }

    // Recursive function to update the parent nodes
    private void updateParents(int index, int len, int level) {
        if (level < 0) {
            return; // Base case: reached the root
        }

        for (int i = index; i < index + len; i++) {
            bits[level][i] = (bits[level + 1][2 * i] == 1 && bits[level + 1][2 * i + 1] == 1) ? 1 : 0;
        }

        updateParents(index / 2, (len + 1) / 2, level - 1); // Update the next level up
    }

    // Helper method to print the tree
    public void printTree() {
        for (int i = 0; i < bits.length; i++) {
            for (int j = 0; j < bits[i].length; j++) {
                System.out.print(bits[i][j] + " ");
            }
            System.out.println();
        }
    }
}
