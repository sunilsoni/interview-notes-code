package com.interview.notes.code.year.y2026.june.codesignal.test1;

import java.io.IOException; // Required to handle file reading/writing exceptions
import java.nio.file.Files; // Provides modern, efficient file operations
import java.nio.file.Path; // Represents file paths in the NIO API
import java.util.ArrayList; // Used to store traversal results for test validation
import java.util.Arrays; // Provides utility to create streams from arrays
import java.util.List; // Interface for our validation list

public class BSTProcessor { // Main wrapper class for the solution

    public static BST buildTreeFromCSV(String filePath) throws IOException { // Parses CSV and builds tree
        var bst = new BST(); // Use Java 'var' for concise instantiation of the tree
        try (var lines = Files.lines(Path.of(filePath))) { // try-with-resources streams file lazily and auto-closes
            lines.flatMap(line -> Arrays.stream(line.split(","))) // Split lines by comma and flatten into a single stream of strings
                 .map(String::trim) // Strip leading/trailing spaces from each string
                 .filter(s -> !s.isEmpty()) // Drop any empty strings resulting from trailing commas or blank lines
                 .mapToInt(Integer::parseInt) // Convert the string values to primitive integers
                 .forEach(bst::insert); // Insert each parsed integer directly into the BST
        } // End of try-with-resources block
        return bst; // Return the fully populated tree structure
    } // End of buildTreeFromCSV method

    // --- TESTING SECTION ---
    public static void main(String[] args) { // Main method to execute tests without JUnit
        System.out.println("Starting BST Tests...\n"); // Print initialization message

        testCase("Basic Unsorted Elements", "129, 64, 25, 100, 1", true); // Test 1: Standard input
        testCase("Contains Duplicates", "10, 5, 15, 10, 5", true); // Test 2: Tree should ignore duplicates
        testCase("Negative Numbers", "-10, -50, 0, 5, -1", true); // Test 3: Handles negatives properly
        testCase("Large Dataset", generateLargeCSV(), true); // Test 4: Stress test with 10,000 elements
    } // End of main method

    private static void testCase(String testName, String csvContent, boolean shouldPass) { // Custom test runner logic
        var tempFile = "temp_input.csv"; // Define a temporary file name for testing
        try { // Start error handling block for file I/O
            Files.writeString(Path.of(tempFile), csvContent); // Write the test string directly to the temp file

            var tree = buildTreeFromCSV(tempFile); // Execute the core logic on the temp file
            var extractedValues = new ArrayList<Integer>(); // Create a list to hold the tree's values
            tree.inorder(tree.root, extractedValues); // Perform in-order traversal to populate the list

            boolean isSorted = true; // Flag to track if the tree is correctly structured
            for (int i = 1; i < extractedValues.size(); i++) { // Loop through the extracted values
                if (extractedValues.get(i - 1) > extractedValues.get(i)) { // Check if previous value is greater than current
                    isSorted = false; // If so, the tree failed to sort properly
                    break; // Exit the loop early to save time
                } // End of sorted check
            } // End of validation loop

            if (isSorted == shouldPass) { // Evaluate if the actual result matches our expectation
                System.out.println("[PASS] " + testName); // Print success message
            } else { // If expectations weren't met
                System.out.println("[FAIL] " + testName + " - Tree was not sorted properly."); // Print failure message
            } // End of result evaluation

        } catch (Exception e) { // Catch any unexpected errors during testing
            System.out.println("[FAIL] " + testName + " - Exception thrown: " + e.getMessage()); // Print exception details
        } finally { // Cleanup block that runs regardless of success or failure
            try { Files.deleteIfExists(Path.of(tempFile)); } catch (IOException ignored) {} // Clean up the temp file
        } // End of finally block
    } // End of testCase method

    private static String generateLargeCSV() { // Helper to generate a massive string for stress testing
        var sb = new StringBuilder(); // Use StringBuilder for efficient string concatenation
        for (int i = 10000; i > 0; i--) { // Loop 10,000 times in reverse to create worst-case insertions
            sb.append(i).append(","); // Append the number and a comma
        } // End of generation loop
        return sb.toString(); // Return the massive CSV string
    } // End of generateLargeCSV method

    static class Node { // Custom Node class representing a single element in the tree
        int data; // The integer value stored in this node
        Node left; // Pointer to the left child (values strictly less than data)
        Node right; // Pointer to the right child (values strictly greater than data)

        Node(int data) { // Constructor to initialize a fresh node
            this.data = data; // Assign the incoming value to the node's data field
        } // End of Node constructor
    } // End of Node class

    static class BST { // The custom Binary Search Tree structure
        Node root; // The entry point (top node) of the tree

        void insert(int value) { // Public method to add a new value to the tree
            root = insertRec(root, value); // Calls the recursive helper and updates the root reference
        } // End of insert method

        Node insertRec(Node root, int value) { // Recursive helper to find the correct insertion point
            if (root == null) { // If the current spot is empty, we found where the node belongs
                return new Node(value); // Create and return the new node to be attached
            } // End of base case check
            if (value < root.data) { // If the value is smaller than the current node's value
                root.left = insertRec(root.left, value); // Traverse down the left branch
            } else if (value > root.data) { // If the value is larger than the current node's value
                root.right = insertRec(root.right, value); // Traverse down the right branch
            } // End of else-if block (Note: duplicate values are silently ignored)
            return root; // Return the unchanged node pointer back up the call stack
        } // End of insertRec method

        void inorder(Node root, List<Integer> result) { // Helper to extract values in sorted order
            if (root != null) { // Only proceed if the current node is not null
                inorder(root.left, result); // Recursively visit all smaller elements on the left
                result.add(root.data); // Add the current node's value to our result list
                inorder(root.right, result); // Recursively visit all larger elements on the right
            } // End of null check
        } // End of inorder method
    } // End of BST class
} // End of BSTProcessor class