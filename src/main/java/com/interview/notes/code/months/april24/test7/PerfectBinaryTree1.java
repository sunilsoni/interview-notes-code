package com.interview.notes.code.months.april24.test7;

public class PerfectBinaryTree1 {

    // Number of levels in the binary tree, including the root level.
    private static final int NUM_LEVELS = 4;
    // 2D array representing the tree, where each level has 2^level nodes.
    private static final int[][] tree = new int[NUM_LEVELS][];

    // Static initializer to set up the tree structure.
    static {
        for (int level = 0; level < NUM_LEVELS; level++) {
            tree[level] = new int[(int) Math.pow(2, level)];
        }
    }

    /**
     * Main method to run the example.
     */
    public static void main(String[] args) {
        PerfectBinaryTree1 binaryTree = new PerfectBinaryTree1();

        // Set all leaf nodes to 1 initially.
        for (int i = 0; i < tree[NUM_LEVELS - 1].length; i++) {
            tree[NUM_LEVELS - 1][i] = 1;
        }

        // Print the initial state of the tree.
        System.out.println("Initial Tree:");
        binaryTree.printTree();

        // Clear a range of bits at the leaf level and update the tree.
        binaryTree.clearBits(2, 3);

        // Print the final state of the tree after the clearBits operation.
        System.out.println("\nTree after clearBits operation:");
        binaryTree.printTree();
    }

    /**
     * Clears a consecutive range of bits in the leaf nodes and updates the entire tree accordingly.
     *
     * @param index The starting index at the leaf level where bits will be cleared.
     * @param len   The number of bits to clear.
     */
    public void clearBits(int index, int len) {
        // Boundary checks for index and length.
        if (index < 0 || len < 0 || index + len > tree[NUM_LEVELS - 1].length) {
            throw new IllegalArgumentException("Index or length is out of bounds.");
        }

        // Clear bits at the leaf level.
        for (int i = index; i < index + len; i++) {
            tree[NUM_LEVELS - 1][i] = 0;
        }

        // Update parent nodes from the bottom-up.
        updateParents((index + len - 1) / 2, NUM_LEVELS - 2);
    }

    /**
     * Recursively updates the parent nodes' values based on the values of their children.
     *
     * @param index The index of the parent at the current level to update.
     * @param level The current level being updated.
     */
    private void updateParents(int index, int level) {
        if (level < 0) return; // Base case: reached the root of the tree.

        // Calculate indices of the children nodes.
        int leftChild = 2 * index;
        int rightChild = leftChild + 1;

        // Update the current parent node based on its children's values.
        tree[level][index] = (tree[level + 1][leftChild] == 1 && tree[level + 1][rightChild] == 1) ? 1 : 0;

        // Update the ancestor nodes recursively.
        updateParents(index / 2, level - 1);
    }

    /**
     * Prints the tree to the console for visualization.
     */
    public void printTree() {
        for (int level = 0; level < NUM_LEVELS; level++) {
            for (int node : tree[level]) {
                System.out.print(node + " ");
            }
            System.out.println();
        }
    }
}
