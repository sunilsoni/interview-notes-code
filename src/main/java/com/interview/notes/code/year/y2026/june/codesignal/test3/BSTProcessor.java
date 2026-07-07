package com.interview.notes.code.year.y2026.june.codesignal.test3;

import java.util.ArrayList; // Used to store traversal results for test validation
import java.util.Arrays; // Provides utility to create streams directly from arrays
import java.util.List; // Interface for our validation list to hold ordered data
import java.util.Random; // Used to generate random integers for our large stress-test dataset

public class BSTProcessor { // Main wrapper class containing our solution logic

    public static BST buildTreeFromArray(int[] elements) { // Method to process an array and build the tree
        var bst = new BST(); // Use Java 'var' (local variable type inference) for concise instantiation
        Arrays.stream(elements) // Convert the raw int[] array into a sequential Java IntStream
              .forEach(bst::insert); // Iterate over the stream and insert each integer directly into the BST
        return bst; // Return the fully populated tree structure
    } // End of buildTreeFromArray method

    // --- TESTING SECTION ---
    public static void main(String[] args) { // Main execution method to run tests without using JUnit
        System.out.println("Starting Array-to-BST Tests...\n"); // Print initialization message to console

        testCase("Basic Unsorted Elements", new int[]{129, 64, 25, 100, 1}, true); // Test 1: Standard input check
        testCase("Contains Duplicates", new int[]{10, 5, 15, 10, 5}, true); // Test 2: Ensures tree ignores duplicates safely
        testCase("Negative Numbers", new int[]{-10, -50, 0, 5, -1}, true); // Test 3: Ensures negatives route correctly
        testCase("Large Random Dataset", generateLargeArray(), true); // Test 4: Stress test with 10,000 random elements
    } // End of main method

    private static void testCase(String testName, int[] inputData, boolean shouldPass) { // Custom test runner logic
        try { // Start error handling block to catch any unexpected runtime issues
            var tree = buildTreeFromArray(inputData); // Execute the core logic: build tree from the input array
            var extractedValues = new ArrayList<Integer>(); // Create an empty list to hold the tree's values
            tree.inorder(tree.root, extractedValues); // Perform in-order traversal to populate the list sequentially

            boolean isSorted = true; // Flag to track if the tree correctly sorted the data
            for (int i = 1; i < extractedValues.size(); i++) { // Loop through the extracted values from index 1
                if (extractedValues.get(i - 1) > extractedValues.get(i)) { // Check if a previous value is larger than the current
                    isSorted = false; // If so, the tree failed its structural integrity
                    break; // Exit the loop early to save processing time
                } // End of sorted check
            } // End of validation loop

            if (isSorted == shouldPass) { // Evaluate if the actual result matches our test expectation
                System.out.println("[PASS] " + testName); // Print success message if expectations are met
            } else { // If expectations were not met
                System.out.println("[FAIL] " + testName + " - Tree was not sorted properly."); // Print failure message
            } // End of result evaluation

        } catch (Exception e) { // Catch any unexpected errors during testing execution
            System.out.println("[FAIL] " + testName + " - Exception thrown: " + e.getMessage()); // Print the exception details
        } // End of try-catch block
    } // End of testCase method

    private static int[] generateLargeArray() { // Helper to generate a massive array for stress testing memory and speed
        var random = new Random(); // Initialize Java's random number generator using 'var'
        return random.ints(10000, -5000, 5000) // Generate an IntStream of 10,000 random numbers between -5000 and 5000
                     .toArray(); // Convert that stream directly into an int[] array and return it
    } // End of generateLargeArray method

    static class Node { // Custom Node class representing a single element in the binary tree
        int data; // The integer value stored in this specific node
        Node left; // Pointer to the left child (values strictly less than current data)
        Node right; // Pointer to the right child (values strictly greater than current data)

        Node(int data) { // Constructor to initialize a fresh node when a new value arrives
            this.data = data; // Assign the incoming integer value to the node's data field
        } // End of Node constructor
    } // End of Node class

    static class BST { // The custom Binary Search Tree structure to hold the nodes
        Node root; // The entry point (top-most node) of the entire tree structure

        void insert(int value) { // Public method called to add a new value to the tree
            root = insertRec(root, value); // Calls the recursive helper and updates the root reference
        } // End of insert method

        Node insertRec(Node root, int value) { // Recursive helper to find the exact correct insertion point
            if (root == null) { // If the current spot is empty, we found where the node belongs
                return new Node(value); // Create and return the new node to be attached to the tree
            } // End of base case check
            if (value < root.data) { // If the incoming value is smaller than the current node's value
                root.left = insertRec(root.left, value); // Traverse down the left branch recursively
            } else if (value > root.data) { // If the incoming value is larger than the current node's value
                root.right = insertRec(root.right, value); // Traverse down the right branch recursively
            } // End of else-if block (Note: duplicate values are silently ignored to maintain strict BST rules)
            return root; // Return the unchanged node pointer back up the call stack
        } // End of insertRec method

        void inorder(Node root, List<Integer> result) { // Helper to extract tree values in sorted order
            if (root != null) { // Only proceed if the current node actually exists (is not null)
                inorder(root.left, result); // Recursively visit all smaller elements on the left side first
                result.add(root.data); // Add the current node's value to our sequential result list
                inorder(root.right, result); // Recursively visit all larger elements on the right side last
            } // End of null check
        } // End of inorder method
    } // End of BST class
} // End of BSTProcessor class