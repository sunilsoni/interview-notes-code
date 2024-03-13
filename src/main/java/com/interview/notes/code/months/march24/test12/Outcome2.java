package com.interview.notes.code.months.march24.test12;

import java.util.Arrays;
import java.util.List;

public class Outcome2 {
    public static int solve(Node root, int K) {
        int closestValue = root.data;
        Node current = root;

        while (current != null) {
            if (Math.abs(K - current.data) < Math.abs(K - closestValue)) {
                closestValue = current.data;
            }

            if (K < current.data) {
                current = current.left;
            } else if (K > current.data) {
                current = current.right;
            } else {
                break; // If K is exactly a node's value, return it.
            }
        }

        return closestValue;
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        // Example #1
        List<Integer> list1 = Arrays.asList(4, 3, 6, 5, 7);
        Node root1 = tree.createBinaryTree(list1);
        System.out.println(solve(root1, 2)); // Output should be 3

        // Example #2
        List<Integer> list2 = Arrays.asList(11, 9, 15, 20, 5, 12, 2, 30);
        Node root2 = tree.createBinaryTree(list2);
        System.out.println(solve(root2, 10)); // Output should be 9

        // Example usage
        List<Integer> ar = Arrays.asList(10, 6, 4, 8, 20, 15, 30, 9, 40);
        Node root3 = tree.createBinaryTree(ar);
        int K = 7;
        System.out.println(solve(root3, K)); // Output should be 6
    }

}