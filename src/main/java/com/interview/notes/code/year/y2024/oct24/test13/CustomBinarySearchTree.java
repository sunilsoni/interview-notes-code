package com.interview.notes.code.year.y2024.oct24.test13;

import java.util.Arrays;

/*
MEDIUM Determine whether a tree is a valid customized binary search tree; i.e. every element to the left of a node is greater than the parent node and every element to the right of a node is less
than or equal to the parent node. If this is true, return the string "TRUE". Otherwise, return the string "FALSE"
This will be given in an integer array that will map out into a full binary tree. For example, given the input [5,7,2,8,6,4,2] the binary tree will look like: 5 /
72/\/
8642
(The first level of the tree will be 5, the second level will be 7, 2, the third level will be 8, 6, 4, 2)
That is, going only left, there is a path from 5 to 7 to 8 and going only right, there is a path from 5
to 2 to 2.
This is a valid customized binary search tree and the function should return the string "TRUE"
 */
public class CustomBinarySearchTree {

    public static String isValidCustomBST(int[] tree) {
        if (tree == null || tree.length == 0) {
            return "TRUE"; // Empty tree is considered valid
        }
        return isValidCustomBSTHelper(tree, 0) ? "TRUE" : "FALSE";
    }

    private static boolean isValidCustomBSTHelper(int[] tree, int index) {
        if (index >= tree.length) {
            return true;
        }

        int leftChildIndex = 2 * index + 1;
        int rightChildIndex = 2 * index + 2;

        // Check left subtree
        if (leftChildIndex < tree.length) {
            if (tree[leftChildIndex] <= tree[index]) {
                return false;
            }
            if (!isValidCustomBSTHelper(tree, leftChildIndex)) {
                return false;
            }
        }

        // Check right subtree
        if (rightChildIndex < tree.length) {
            if (tree[rightChildIndex] > tree[index]) {
                return false;
            }
            return isValidCustomBSTHelper(tree, rightChildIndex);
        }

        return true;
    }

    public static void main(String[] args) {
        // Test cases
        int[][] testCases = {
                {10, 20, 5, 30, 15, 7, 3, 40, 29, 19, 14, 9, 6, 4, 1},
                {10, 3, 14},
                {5, 7, 2, 8, 6, 4, 4},
                {5, 7, 2, 8, 6, 4, 2},
                {10, 14, 3},
                {10},
                {5, 2, 7, 2, 4, 6, 8}
        };

        String[] expectedResults = {"TRUE", "FALSE", "FALSE", "TRUE", "TRUE", "TRUE", "FALSE"};

        for (int i = 0; i < testCases.length; i++) {
            String result = isValidCustomBST(testCases[i]);
            System.out.println("Test Case " + (i + 1) + ": " + Arrays.toString(testCases[i]));
            System.out.println("Result: " + result);
            System.out.println("Expected: " + expectedResults[i]);
            System.out.println("Pass: " + result.equals(expectedResults[i]));
            System.out.println();
        }

        // Large data input test
        int[] largeInput = new int[100000];
        for (int i = 0; i < largeInput.length; i++) {
            largeInput[i] = 100000 - i; // This creates a valid customized BST
        }
        long startTime = System.currentTimeMillis();
        String largeResult = isValidCustomBST(largeInput);
        long endTime = System.currentTimeMillis();
        System.out.println("Large Input Test (100,000 elements)");
        System.out.println("Result: " + largeResult);
        System.out.println("Execution time: " + (endTime - startTime) + " ms");
    }
}
