package com.interview.notes.code.year.y2025.july.amazon.test11;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
### Problem Statement:

Given the root node of a tree data structure, write an algorithm to determine if the tree is a valid binary search tree.

### Input Data Structure:

```java
public class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;
}
```
 */
public class ValidateBST {

    // Public method to initiate BST validation
    public static boolean isValidBST(TreeNode root) {
        return isValidBSTHelper(root, null, null);  // Start with no bounds
    }

    // Helper method with bounds-checking
    private static boolean isValidBSTHelper(TreeNode node, Integer lower, Integer upper) {
        if (node == null) {                          // Base case: empty subtree
            return true;                             // An empty tree is valid
        }
        int val = node.value;                        // Current node's value
        if (lower != null && val <= lower) {         // Violation: ≤ lower bound
            return false;
        }
        if (upper != null && val >= upper) {         // Violation: ≥ upper bound
            return false;
        }
        // Recurse on right: new lower bound is current value
        if (!isValidBSTHelper(node.right, val, upper)) {
            return false;
        }
        // Recurse on left: new upper bound is current value
        if (!isValidBSTHelper(node.left, lower, val)) {
            return false;
        }
        return true;                                 // All checks passed
    }

    // Build a balanced BST from a sorted List<Integer>
    public static TreeNode buildBSTFromSortedList(List<Integer> list) {
        return buildBSTHelper(list, 0, list.size() - 1);
    }

    // Recursive helper to build BST
    private static TreeNode buildBSTHelper(List<Integer> list, int left, int right) {
        if (left > right) {                          // No elements in this subarray
            return null;
        }
        int mid = left + (right - left) / 2;         // Choose middle for balance
        TreeNode node = new TreeNode(list.get(mid));// Create node at mid-value
        node.left = buildBSTHelper(list, left, mid - 1);   // Build left subtree
        node.right = buildBSTHelper(list, mid + 1, right); // Build right subtree
        return node;                                 // Return constructed subtree
    }

    // Main method to run simple PASS/FAIL tests
    public static void main(String[] args) {
        // Use LinkedHashMap to preserve insertion order of tests
        Map<String, TreeNode> testTrees = new LinkedHashMap<>();
        Map<String, Boolean> expected = new LinkedHashMap<>();

        // Test 1: Empty tree → should be valid
        testTrees.put("Empty tree", null);
        expected.put("Empty tree", true);

        // Test 2: Single node → always valid
        testTrees.put("Single node", new TreeNode(42));
        expected.put("Single node", true);

        // Test 3: Small valid BST
        TreeNode small = new TreeNode(2);
        small.left = new TreeNode(1);
        small.right = new TreeNode(3);
        testTrees.put("Small valid BST", small);
        expected.put("Small valid BST", true);

        // Test 4: Invalid BST (left child > parent)
        TreeNode bad1 = new TreeNode(2);
        bad1.left = new TreeNode(5);  // Violation here
        bad1.right = new TreeNode(3);
        testTrees.put("Invalid BST 1", bad1);
        expected.put("Invalid BST 1", false);

        // Test 5: Large valid BST (100,000 nodes)
        List<Integer> bigList = IntStream.rangeClosed(1, 100_000)
                .boxed()
                .collect(Collectors.toList());  // Generate 1..100000
        TreeNode bigValid = buildBSTFromSortedList(bigList);
        testTrees.put("Large valid BST", bigValid);
        expected.put("Large valid BST", true);

        // Test 6: Large invalid BST (break one node)
        TreeNode bigInvalid = buildBSTFromSortedList(bigList);
        bigInvalid.left.value = 200_000;   // Force left subtree violation
        testTrees.put("Large invalid BST", bigInvalid);
        expected.put("Large invalid BST", false);

        // Execute all tests and report PASS/FAIL
        System.out.println("BST Validation Tests:");
        testTrees.forEach((name, tree) -> {
            boolean result = isValidBST(tree);        // Run validator
            boolean exp = expected.get(name);      // Expected outcome
            String status = (result == exp) ? "PASS" : "FAIL";
            System.out.printf("%-20s : %s%n", name, status);
        });
    }

    // Definition of the tree node
    static class TreeNode {
        int value;                          // The node's integer value
        TreeNode left;                      // Reference to left child
        TreeNode right;                     // Reference to right child

        TreeNode(int value) {               // Constructor to set node value
            this.value = value;
        }
    }
}