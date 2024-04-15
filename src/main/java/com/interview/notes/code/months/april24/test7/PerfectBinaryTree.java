package com.interview.notes.code.months.april24.test7;

import java.util.LinkedList;
import java.util.Queue;

public class PerfectBinaryTree {

    // Define the number of levels in the binary tree including the root.
    private static final int NUM_LEVELS = 4;
    // Create a 2D array to represent the nodes of the tree, where each level has 2^level nodes.
    private static final int[][] tree = new int[NUM_LEVELS][];

    // Static initialization block to set up the binary tree structure.
    static {
        // Initialize each level of the tree.
        for (int level = 0; level < NUM_LEVELS; level++) {
            // Allocate space for the nodes at this level (2^level nodes).
            tree[level] = new int[(int) Math.pow(2, level)];
        }
    }

    /**
     * Method to clear a range of leaf nodes and update parent nodes accordingly.
     *
     * @param index The starting index of the range of leaf nodes to clear.
     * @param len   The number of leaf nodes to clear in the range.
     */
    public void clearBits(int index, int len) {
        // Check the boundaries to prevent accessing outside the array bounds.
        if (index < 0 || len < 0 || index + len > tree[NUM_LEVELS - 1].length) {
            throw new IllegalArgumentException("Index or length is out of bounds.");
        }

        // Iterate over the range of leaf nodes to set their value to 0 (clear them).
        for (int i = index; i < index + len; i++) {
            tree[NUM_LEVELS - 1][i] = 0;
        }

        // Update the parent nodes to reflect the changes made to the leaf nodes.
        // Start updating from the parent of the last leaf node that was cleared.
        updateParents((index + len - 1) / 2, NUM_LEVELS - 2);
    }

    /**
     * Recursive method to update the values of parent nodes after their children have been updated.
     *
     * @param index The index of the parent node to update.
     * @param level The level of the tree where the update is occurring.
     */
    private void updateParents(int index, int level) {
        // Base case for the recursion; if we are at level -1, we have reached above the root of the tree.
        if (level < 0) return;

        // Calculate the indices of the child nodes in the next level down.
        int leftChild = 2 * index;
        int rightChild = leftChild + 1;

        // Update the value of the current parent node based on the values of its child nodes.
        // A parent node is set to 1 if and only if both of its children are 1.
        tree[level][index] = (tree[level + 1][leftChild] == 1 && tree[level + 1][rightChild] == 1) ? 1 : 0;

        // Recursively call updateParents on the parent of the current node to update the ancestor nodes.
        updateParents(index / 2, level - 1);
    }

    /**
     * Utility method to print the binary tree, showing the value of each node.
     */
    public void printTree() {
        // Loop through each level of the tree.
        for (int level = 0; level < NUM_LEVELS; level++) {
            // Loop through each node at the current level and print its value.
            for (int node : tree[level]) {
                System.out.print(node + " ");
            }
            // Print a newline to separate levels of the tree.
            System.out.println();
        }
    }

    /**
     * The main method to run the example, demonstrating the clearBits functionality.
     */
    public static void main(String[] args) {
        // Instantiate the binary tree object.
        PerfectBinaryTree binaryTree = new PerfectBinaryTree();

        // Initialize all the leaf nodes to 1 to simulate a starting tree state.
        for (int i = 0; i < tree[NUM_LEVELS - 1].length; i++) {
            tree[NUM_LEVELS - 1][i] = 1;
        }

        // Print the initial state of the tree to the console.
        System.out.println("Initial Tree:");
        binaryTree.printTree();

        // Perform the clearBits operation to clear a range of leaf nodes and update the tree.
        binaryTree.clearBits(2, 3);

        // Print the final state of the tree after performing the clearBits operation.
        System.out.println("\nTree after clearBits operation:");
        binaryTree.printTree();
    }

    /**
     * Method to update the parent nodes in a breadth-first manner after their children have been updated.
     */
    public void updateParentsBFS() {
        // A queue to hold the indices of nodes that need to be updated at each level.
        Queue<Integer> queue = new LinkedList<>();

        // Start by adding the indices of all leaf nodes to the queue.
        for (int i = 0; i < tree[NUM_LEVELS - 1].length; i++) {
            queue.add(i);
        }

        // Iterate from the leaves up to the root level.
        for (int level = NUM_LEVELS - 1; level > 0; level--) {
            // Queue for the next level (parent nodes).
            Queue<Integer> nextLevelQueue = new LinkedList<>();

            while (!queue.isEmpty()) {
                int childIndex = queue.poll();
                int parentIndex = childIndex / 2;

                // Update the parent node based on the value of its children.
                tree[level - 1][parentIndex] = (tree[level][2 * parentIndex] == 1 && tree[level][2 * parentIndex + 1] == 1) ? 1 : 0;

                // Add the parent index to the next level queue if it's not already there.
                // We check this by comparing the last element in the queue, since BFS processes in order.
                if (nextLevelQueue.isEmpty() || nextLevelQueue.peek() != parentIndex) {
                    nextLevelQueue.add(parentIndex);
                }
            }

            // Prepare for the next level.
            queue = nextLevelQueue;
        }
    }
}
