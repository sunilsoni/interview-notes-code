package com.interview.notes.code.year.y2025.july.codility.test3;

import java.util.*;
import java.util.stream.Collectors;

public class VerticalOrderTraversal {         // main class container

    /**
     * Performs the vertical order traversal as specified.
     *
     * @param root the root of the binary tree
     * @return a list of columns, each a list of node values in correct order
     */
    public static List<List<Integer>> verticalTraversal(TreeNode root) {
        List<NodeInfo> nodeList = new ArrayList<>();      // to collect all nodes
        Deque<Triple> queue = new ArrayDeque<>();         // BFS queue

        if (root == null) {                               // handle empty tree
            return new ArrayList<>();                       // return empty result
        }

        queue.offer(new Triple(root, 0, 0));               // start at (row=0, col=0)

        // BFS: visit every node, record its (col, row, val)
        while (!queue.isEmpty()) {
            Triple t = queue.poll();                         // dequeue next
            TreeNode node = t.node;                         // extract node
            int row = t.row;                                // its row
            int col = t.col;                                // its col
            nodeList.add(new NodeInfo(col, row, node.val)); // record metadata

            if (node.left != null) {                        // if left child exists
                queue.offer(new Triple(node.left, row + 1, col - 1)); // enqueue left
            }
            if (node.right != null) {                       // if right child exists
                queue.offer(new Triple(node.right, row + 1, col + 1)); // enqueue right
            }
        }

        // Sort globally by (col ↑, row ↑, val ↑) using streams
        List<NodeInfo> sorted = nodeList.stream()
                .sorted(Comparator.comparingInt((NodeInfo n) -> n.col)
                        .thenComparingInt(n -> n.row)
                        .thenComparingInt(n -> n.val))
                .collect(Collectors.toList());

        // Group the sorted list by column into a TreeMap (keeps keys sorted)
        Map<Integer, List<NodeInfo>> grouped = sorted.stream()
                .collect(Collectors.groupingBy(
                        n -> n.col,                         // grouping key: column
                        TreeMap::new,                       // use TreeMap for sorted keys
                        Collectors.toList()                 // collect into List<NodeInfo>
                ));

        // Extract just the values, in column order
        List<List<Integer>> result = grouped.values().stream()
                .map(list -> list.stream()
                        .map(n -> n.val)                   // map metadata to just the value
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());

        return result;                              // final vertical order lists
    }

    /**
     * Main method: builds test trees, runs verticalTraversal, prints PASS/FAIL.
     */
    public static void main(String[] args) {
        List<TestCase> tests = new ArrayList<>();    // holder for all tests

        // Test 1: example from prompt: [3,9,20,null,null,15,7]
        TreeNode root1 = new TreeNode(3);            // root=3
        root1.left = new TreeNode(9);                // 9 is left of 3
        root1.right = new TreeNode(20);              // 20 is right of 3
        root1.right.left = new TreeNode(15);         // 15 left of 20
        root1.right.right = new TreeNode(7);         // 7 right of 20
        tests.add(new TestCase(
                root1,
                Arrays.asList(
                        List.of(9),                        // column -1
                        Arrays.asList(3, 15),                    // column  0
                        List.of(20),                       // column +1
                        List.of(7)                         // column +2
                )
        ));

        // Test 2: single node tree
        tests.add(new TestCase(
                new TreeNode(1),                           // just one node=1
                List.of(List.of(1))            // expected [[1]]
        ));

        // Test 3: empty tree
        tests.add(new TestCase(
                null,                                      // no tree
                Collections.emptyList()                    // expected []
        ));

        // Test 4: nodes sharing same row & col to verify value sorting
        TreeNode root4 = new TreeNode(1);
        root4.left = new TreeNode(2);
        root4.right = new TreeNode(3);
        root4.left.left = new TreeNode(4);
        root4.left.right = new TreeNode(5);
        root4.right.left = new TreeNode(6);
        root4.right.right = new TreeNode(7);
        tests.add(new TestCase(
                root4,
                Arrays.asList(
                        List.of(4),                        // col -2
                        List.of(2),                        // col -1
                        Arrays.asList(1, 5, 6),                  // col  0 (5 and 6 sorted by value)
                        List.of(3),                        // col +1
                        List.of(7)                         // col +2
                )
        ));

        // Run each test and report PASS/FAIL
        int idx = 1;                                 // case counter
        for (TestCase tc : tests) {
            List<List<Integer>> actual = verticalTraversal(tc.root);  // compute
            if (actual.equals(tc.expected)) {
                System.out.println("Test case " + idx + ": PASS");     // matched
            } else {
                System.out.println("Test case " + idx + ": FAIL");     // mismatch
                System.out.println("  Expected: " + tc.expected);
                System.out.println("  Actual:   " + actual);
            }
            idx++;                                      // next index
        }
    }

    // Definition for a binary tree node.
    static class TreeNode {                      // nested class for tree nodes
        int val;                                   // node’s integer value
        TreeNode left;                             // reference to left child
        TreeNode right;                            // reference to right child

        TreeNode(int x) {                          // constructor to set the value
            val = x;                                 // assign node value
        }
    }

    // Helper class to store (col, row, val) for sorting.
    static class NodeInfo {                      // holds metadata for each node
        int col;                                   // column index (x-coordinate)
        int row;                                   // row index (y-coordinate)
        int val;                                   // the node’s value

        NodeInfo(int c, int r, int v) {            // constructor
            col = c;                                 // set column
            row = r;                                 // set row
            val = v;                                 // set value
        }
    }

    // Helper for BFS: triple of (node, row, col).
    static class Triple {
        TreeNode node;                             // the tree node
        int row;                                   // its row
        int col;                                   // its column

        Triple(TreeNode n, int r, int c) {         // constructor
            node = n;                                // assign node
            row = r;                                 // assign row
            col = c;                                 // assign column
        }
    }

    /**
     * Simple TestCase holder: input root + expected vertical order.
     */
    static class TestCase {
        TreeNode root;                              // input tree root
        List<List<Integer>> expected;               // expected output

        TestCase(TreeNode r, List<List<Integer>> e) {
            root = r;                                 // assign input
            expected = e;                             // assign expected
        }
    }
}