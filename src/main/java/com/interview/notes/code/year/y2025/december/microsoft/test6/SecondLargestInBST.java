package com.interview.notes.code.year.y2025.december.microsoft.test6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

// Node class for BST
class TreeNode {
    int val;           // value stored in node
    TreeNode left;     // left child reference
    TreeNode right;    // right child reference

    // constructor to create node with value
    TreeNode(int val) {
        this.val = val;
    }
}

public class SecondLargestInBST {

    // finds second largest value in BST
    // returns null if tree has less than 2 nodes
    static Integer findSecondLargest(TreeNode root) {

        // edge case: empty tree or single node - no second largest exists
        if (root == null || (root.left == null && root.right == null)) {
            return null;  // return null when second largest doesn't exist
        }

        TreeNode current = root;  // pointer to traverse the tree
        TreeNode parent = null;   // keeps track of parent of current node

        // traverse to rightmost node (largest value in BST)
        // BST property: right child always greater than parent
        while (current.right != null) {
            parent = current;         // save current as parent before moving
            current = current.right;  // move to right child
        }

        // case 1: largest node has left subtree
        // second largest is rightmost of that left subtree
        if (current.left != null) {
            current = current.left;  // go to left subtree of largest

            // find rightmost in this subtree (largest in left subtree)
            while (current.right != null) {
                current = current.right;  // keep going right
            }
            return current.val;  // this is second largest
        }

        // case 2: largest has no left subtree
        // parent of largest is second largest
        return parent.val;
    }

    // helper method to insert value into BST
    static TreeNode insert(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);  // create new node if empty

        if (val < root.val) {
            root.left = insert(root.left, val);   // go left if smaller
        } else {
            root.right = insert(root.right, val); // go right if larger
        }
        return root;  // return root after insertion
    }

    // builds BST from array of values
    static TreeNode buildTree(int[] values) {
        TreeNode root = null;  // start with empty tree

        // insert each value into BST
        for (int val : values) {
            root = insert(root, val);  // add value to tree
        }
        return root;  // return built tree
    }

    // test runner method
    static void test(String name, int[] values, Integer expected) {
        TreeNode root = buildTree(values);           // build tree from values
        Integer result = findSecondLargest(root);    // find second largest

        // compare result with expected
        boolean pass = Objects.equals(result, expected);

        // print test result with details
        System.out.println(name + ": " + (pass ? "PASS ✓" : "FAIL ✗")
                + " | Input: " + Arrays.toString(values)
                + " | Expected: " + expected
                + " | Got: " + result);
    }

    public static void main(String[] args) {
        System.out.println("=== Second Largest in BST Tests ===\n");

        // Test 1: balanced tree
        // Tree:     5
        //         /   \
        //        3     7
        //       / \   / \
        //      2   4 6   8
        test("Test 1: Balanced tree",
                new int[]{5, 3, 7, 2, 4, 6, 8}, 7);

        // Test 2: largest has left subtree
        // Tree:     5
        //         /   \
        //        3     8
        //             /
        //            6
        //             \
        //              7
        test("Test 2: Largest has left child",
                new int[]{5, 3, 8, 6, 7}, 7);

        // Test 3: right skewed tree
        // Tree: 1 -> 2 -> 3 -> 4 -> 5
        test("Test 3: Right skewed",
                new int[]{1, 2, 3, 4, 5}, 4);

        // Test 4: left skewed tree
        // Tree: 5 <- 4 <- 3 <- 2 <- 1
        test("Test 4: Left skewed",
                new int[]{5, 4, 3, 2, 1}, 4);

        // Test 5: only two nodes
        test("Test 5: Two nodes only",
                new int[]{5, 3}, 3);

        // Test 6: two nodes right child
        test("Test 6: Two nodes right",
                new int[]{5, 7}, 5);

        // Test 7: single node - no second largest
        test("Test 7: Single node",
                new int[]{5}, null);

        // Test 8: empty tree
        test("Test 8: Empty tree",
                new int[]{}, null);

        // Test 9: complex tree
        test("Test 9: Complex tree",
                new int[]{10, 5, 15, 3, 7, 12, 20, 18}, 18);

        // Test 10: Large data test
        System.out.println("\n=== Large Data Test ===");
        int size = 100000;  // 100k nodes
        int[] largeData = new int[size];

        // create array with sequential values
        for (int i = 0; i < size; i++) {
            largeData[i] = i + 1;  // values 1 to 100000
        }

        // shuffle for balanced-ish tree
        var list = Arrays.stream(largeData).boxed().toList();  // convert to list
        var shuffled = new ArrayList<>(list);                   // mutable copy
        Collections.shuffle(shuffled);                          // randomize order
        largeData = shuffled.stream().mapToInt(i -> i).toArray(); // back to array

        long start = System.currentTimeMillis();  // record start time
        test("Test 10: Large data (100k)", largeData, size - 1);  // second largest is 99999
        long end = System.currentTimeMillis();    // record end time

        System.out.println("Time taken: " + (end - start) + " ms");

        // Test 11: worst case - sorted data (skewed tree)
        System.out.println("\n=== Worst Case Test ===");
        int[] sorted = new int[10000];  // smaller for skewed to avoid stack overflow
        for (int i = 0; i < 10000; i++) {
            sorted[i] = i;  // creates right-skewed tree
        }

        start = System.currentTimeMillis();
        test("Test 11: Skewed tree (10k)", sorted, 9998);
        end = System.currentTimeMillis();

        System.out.println("Time taken: " + (end - start) + " ms");
    }
}