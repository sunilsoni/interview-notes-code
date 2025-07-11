package com.interview.notes.code.year.y2025.july.common.test4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BSTExample {

    // ─── BST Root ───────────────────────────────────────────────────────
    private Node root;  // entry point into our tree; null if tree is empty

    // ─── MAIN for SIMPLE TESTING ────────────────────────────────────────
    public static void main(String[] args) {
        BSTExample bst = new BSTExample();
        // 1) Build the tree by inserting 3, 1, 2, 5
        for (int v : Arrays.asList(3, 1, 2, 5)) {
            bst.put(v);
        }

        // 2) Define some contains-tests: {value, expectedExists?}
        int[][] tests = {
                {0, 0}, {1, 1}, {2, 1}, {3, 1}, {4, 0}, {5, 1}, {6, 0}
        };
        // 3) Run them and print PASS/FAIL
        System.out.println("=== contains(...) tests ===");
        for (int[] t : tests) {
            boolean result = bst.contains(t[0]);
            boolean expect = (t[1] == 1);
            System.out.printf(
                    "contains(%d) → %b [%s]\n",
                    t[0],
                    result,
                    result == expect ? "PASS" : "FAIL"
            );
        }

        // 4) Test the in-order traversal
        List<Integer> ordered = bst.inOrderTraversal();
        List<Integer> expected = Arrays.asList(1, 2, 3, 5);
        System.out.println("\n=== inOrderTraversal() test ===");
        System.out.printf(
                "got %s [%s]\n",
                ordered,
                ordered.equals(expected) ? "PASS" : "FAIL"
        );
    }

    // ─── INSERT (put) ──────────────────────────────────────────────────

    /**
     * Public API: insert a value into the BST.
     * Duplicate values are ignored.
     */
    public void put(int value) {
        // delegate into recursive helper and update root if tree was empty
        root = putRecursive(root, value);
    }

    /**
     * Recursive helper for insertion.
     * If node is null, we grow a new Node here.
     */
    private Node putRecursive(Node node, int value) {
        if (node == null) {
            // base case: we've found the spot to insert
            return new Node(value);
        }
        if (value < node.val) {
            // if the new value is smaller, go left
            node.left = putRecursive(node.left, value);
        } else if (value > node.val) {
            // if the new value is larger, go right
            node.right = putRecursive(node.right, value);
        }
        // if it's equal, we do nothing (no duplicates)
        return node; // return current node to link back up
    }

    // ─── SEARCH (contains) ─────────────────────────────────────────────

    /**
     * Public API: check if a value exists in the BST.
     * Runs in O(h) time, where h is tree height.
     */
    public boolean contains(int value) {
        Node current = root;            // start from the root
        while (current != null) {
            if (value == current.val) {
                return true;           // found it!
            }
            // choose next branch
            current = (value < current.val) ? current.left : current.right;
        }
        return false;                   // fell off the tree—never found it
    }

    // ─── IN-ORDER TRAVERSAL ────────────────────────────────────────────

    /**
     * Returns a List of all values in ascending order.
     */
    public List<Integer> inOrderTraversal() {
        List<Integer> acc = new ArrayList<>();  // collector for results
        inOrderRecursive(root, acc);            // fill it by recursion
        return acc;                             // return sorted list
    }

    /**
     * Recursive in-order: visit left subtree, node, then right subtree.
     */
    private void inOrderRecursive(Node node, List<Integer> acc) {
        if (node == null) {
            return;              // nothing to do for an empty subtree
        }
        inOrderRecursive(node.left, acc);  // first, handle all smaller values
        acc.add(node.val);                 // then, visit this node
        inOrderRecursive(node.right, acc); // finally, handle larger values
    }

    // ─── Node Definition ────────────────────────────────────────────────
    private static class Node {
        int val;        // store this node's integer
        Node left;      // link to left subtree (values < val)
        Node right;     // link to right subtree (values > val)

        // constructor sets the node's value
        Node(int val) {
            this.val = val;
        }
    }
}