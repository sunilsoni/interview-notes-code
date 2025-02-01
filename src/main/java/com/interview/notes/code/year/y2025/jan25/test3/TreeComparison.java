package com.interview.notes.code.year.y2025.jan25.test3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TreeComparison {
    // Main comparison method
    public static boolean isTreeContentEqual(Node a, Node b) {
        // Convert both trees to lists and compare
        List<Integer> listA = new ArrayList<>();
        List<Integer> listB = new ArrayList<>();

        // Get all values in order
        collectValues(a, listA);
        collectValues(b, listB);

        // Sort both lists
        Collections.sort(listA);
        Collections.sort(listB);

        // Compare the sorted lists
        return listA.equals(listB);
    }

    // Helper method to collect values
    private static void collectValues(Node root, List<Integer> values) {
        if (root == null) return;
        values.add(root.val);
        collectValues(root.left, values);
        collectValues(root.right, values);
    }

    public static void main(String[] args) {
        // Test Case 1: Given example
        Node a = new Node(9);
        a.left = new Node(5);
        a.right = new Node(11);
        a.left.left = new Node(2);
        a.left.right = new Node(6);

        Node b = new Node(6);
        b.left = new Node(5);
        b.right = new Node(9);
        b.left.left = new Node(2);
        b.right.right = new Node(11);

        runTest("Test 1 (Given Example)", a, b, true);

        // Test Case 2: Empty trees
        runTest("Test 2 (Empty Trees)", null, null, true);

        // Test Case 3: Different values
        Node c = new Node(1);
        Node d = new Node(2);
        runTest("Test 3 (Different Values)", c, d, false);

        // Test Case 4: Different sizes
        Node e = new Node(1);
        e.left = new Node(2);
        Node f = new Node(1);
        runTest("Test 4 (Different Sizes)", e, f, false);
    }

    private static void runTest(String testName, Node tree1, Node tree2, boolean expectedResult) {
        boolean result = isTreeContentEqual(tree1, tree2);
        System.out.println(testName + ": " +
                (result == expectedResult ? "PASS" : "FAIL") +
                " (Got: " + result + ", Expected: " + expectedResult + ")");
    }

    // Tree node structure
    static class Node {
        int val;
        Node left;
        Node right;

        Node(int val) {
            this.val = val;
        }
    }
}
