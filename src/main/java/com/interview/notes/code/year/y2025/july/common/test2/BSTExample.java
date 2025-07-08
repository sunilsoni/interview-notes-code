package com.interview.notes.code.year.y2025.july.common.test2;

import java.util.*;
import java.util.function.BiConsumer;

public class BSTExample {
    // ─── Node definition ──────────────────────────────────────────────
    private static class Node {
        int val;       // the value stored here
        Node left;     // left child (values < val)
        Node right;    // right child (values > val)
        Node(int val) { this.val = val; }
    }

    private Node root; // root of the BST (null if empty)

    // ─── insert (put) ─────────────────────────────────────────────────
    public void put(int value) {
        root = putRecursive(root, value);
    }

    private Node putRecursive(Node node, int value) {
        if (node == null) {
            // found insertion spot
            return new Node(value);
        }
        if (value < node.val) {
            node.left = putRecursive(node.left, value);
        } else if (value > node.val) {
            node.right = putRecursive(node.right, value);
        }
        // if equal, do nothing (no duplicates)
        return node;
    }

    // ─── search (contains) ─────────────────────────────────────────────
    public boolean contains(int value) {
        Node current = root;
        while (current != null) {
            if (value == current.val) {
                return true;    // hit
            }
            // choose direction
            current = (value < current.val) ? current.left : current.right;
        }
        return false;           // fell off tree
    }

    // ─── in-order traversal ────────────────────────────────────────────
    public List<Integer> inOrderTraversal() {
        List<Integer> acc = new ArrayList<>();
        inOrderRecursive(root, acc);
        return acc;
    }

    private void inOrderRecursive(Node node, List<Integer> acc) {
        if (node == null) return;           // empty subtree
        inOrderRecursive(node.left, acc);   // left
        acc.add(node.val);                  // node
        inOrderRecursive(node.right, acc);  // right
    }

    // ─── main: eight test scenarios ───────────────────────────────────
    public static void main(String[] args) {
        // helper to run contains() tests
        BiConsumer<BSTExample, int[][]> runContainsTests = (bst, tests) -> {
            for (int[] t : tests) {
                boolean got = bst.contains(t[0]);
                boolean expect = t[1] == 1;
                System.out.printf(
                    "contains(%d) → %b [%s]%n",
                    t[0],
                    got,
                    (got == expect ? "PASS" : "FAIL")
                );
            }
        };

        // helper to run in-order tests
        BiConsumer<BSTExample, List<Integer>> runInOrderTest = (bst, expected) -> {
            List<Integer> got = bst.inOrderTraversal();
            System.out.printf(
                "inOrderTraversal() → %s [%s]%n",
                got,
                got.equals(expected) ? "PASS" : "FAIL"
            );
        };

        // 1) Empty tree
        System.out.println("==[1] Empty Tree==");
        BSTExample bst1 = new BSTExample();
        runContainsTests.accept(bst1, new int[][] {{0,0}, {42,0}});
        runInOrderTest.accept(bst1, Collections.emptyList());

        // 2) Single node
        System.out.println("\n==[2] Single Node==");
        BSTExample bst2 = new BSTExample();
        bst2.put(42);
        runContainsTests.accept(bst2, new int[][] {{42,1}, {0,0}});
        runInOrderTest.accept(bst2, Collections.singletonList(42));

        // 3) Duplicates
        System.out.println("\n==[3] Duplicates Inserted==");
        BSTExample bst3 = new BSTExample();
        for (int i = 0; i < 5; i++) bst3.put(7);
        runContainsTests.accept(bst3, new int[][] {{7,1}});
        runInOrderTest.accept(bst3, Collections.singletonList(7));

        // 4) Negatives & extremes
        System.out.println("\n==[4] Negative & Min/Max==");
        BSTExample bst4 = new BSTExample();
        bst4.put(0);
        bst4.put(-3);
        bst4.put(Integer.MIN_VALUE);
        bst4.put(Integer.MAX_VALUE);
        runContainsTests.accept(bst4, new int[][] {
            {0,1}, {-3,1}, {Integer.MIN_VALUE,1}, {Integer.MAX_VALUE,1}, {1,0}
        });
        runInOrderTest.accept(bst4, Arrays.asList(
            Integer.MIN_VALUE, -3, 0, Integer.MAX_VALUE
        ));

        // 5) Left-skew (5→1)
        System.out.println("\n==[5] Left-Skew (5→1)==");
        BSTExample bst5 = new BSTExample();
        for (int v = 5; v >= 1; v--) bst5.put(v);
        runInOrderTest.accept(bst5, Arrays.asList(1,2,3,4,5));

        // 6) Right-skew (1→5)
        System.out.println("\n==[6] Right-Skew (1→5)==");
        BSTExample bst6 = new BSTExample();
        for (int v = 1; v <= 5; v++) bst6.put(v);
        runInOrderTest.accept(bst6, Arrays.asList(1,2,3,4,5));

        // 7) Random small set
        System.out.println("\n==[7] Random Insertion==");
        int[] random = {10,4,15,2,6,12,20};
        BSTExample bst7 = new BSTExample();
        for (int v : random) bst7.put(v);
        runContainsTests.accept(bst7, new int[][] {
            {4,1}, {6,1}, {15,1}, {99,0}
        });
        runInOrderTest.accept(bst7, Arrays.asList(2,4,6,10,12,15,20));

        // 8) Large sorted input (depth test)
        System.out.println("\n==[8] Large Sorted (depth test)==");
        BSTExample bst8 = new BSTExample();
        final int N = 10_000; 
        for (int i = 0; i < N; i++) bst8.put(i);
        boolean found = bst8.contains(N-1);
        System.out.printf(
            "contains(%d) → %b [%s]%n",
            N-1, found, (found ? "PASS" : "FAIL")
        );

        System.out.println("\nAll done.  Look for any FAILs above!");
    }
}