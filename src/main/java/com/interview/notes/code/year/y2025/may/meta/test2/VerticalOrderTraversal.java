package com.interview.notes.code.year.y2025.may.meta.test2;

import java.util.*;
import java.util.stream.Collectors;

public class VerticalOrderTraversal {

    /**
     * Performs vertical order traversal of a binary tree.
     *
     * @param root the root node
     * @return list of node values in vertical order
     */
    public static List<Integer> verticalOrder(Node root) {
        if (root == null) {
            return Collections.emptyList();
        }

        // Sorted map: HD -> list of node values
        TreeMap<Integer, List<Integer>> columns = new TreeMap<>();
        // Queue for BFS: pairs of (node, horizontal distance)
        Deque<Map.Entry<Node, Integer>> queue = new ArrayDeque<>();
        queue.add(new AbstractMap.SimpleEntry<>(root, 0));

        while (!queue.isEmpty()) {
            Map.Entry<Node, Integer> entry = queue.removeFirst();
            Node node = entry.getKey();
            int hd = entry.getValue();

            // Add node value to its HD list
            columns
                    .computeIfAbsent(hd, k -> new ArrayList<>())
                    .add(node.val);

            // Enqueue children with updated HD
            if (node.left != null) {
                queue.addLast(new AbstractMap.SimpleEntry<>(node.left, hd - 1));
            }
            if (node.right != null) {
                queue.addLast(new AbstractMap.SimpleEntry<>(node.right, hd + 1));
            }
        }

        // Flatten the lists in ascending HD order
        return columns.values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    // Helper to build a sample tree quickly
    private static Node sampleTree() {
        Node root = new Node(6);
        root.left = new Node(3);
        root.right = new Node(4);
        root.left.left = new Node(5);
        root.left.left.right = new Node(2);
        root.left.left.right.left = new Node(9);
        root.right.left = new Node(1);
        root.right.right = new Node(0);
        root.right.right.left = new Node(8);
        root.right.right.left.right = new Node(7);
        return root;
    }

    public static void main(String[] args) {
        // Define test cases: pair of (root, expectedOutputString)
        List<Map.Entry<Node, String>> tests = Arrays.asList(
                // Example from problem
                Map.entry(sampleTree(), "5 9 3 2 6 1 7 4 8 0"),
                // Minimal example
                Map.entry(buildTree(new int[]{1, 2, 3}, new String[]{"L", "R"}), "2 1 3"),
                // Empty tree
                Map.entry(null, ""),
                // Single node
                Map.entry(new Node(42), "42"),
                // Left-skew (3 nodes)
                Map.entry(buildLeftSkew(3), "3 2 1")
        );

        // Run tests
        for (int i = 0; i < tests.size(); i++) {
            Node root = tests.get(i).getKey();
            String expected = tests.get(i).getValue();
            List<Integer> result = verticalOrder(root);
            String got = result.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(" "));
            String status = got.equals(expected) ? "PASS" : "FAIL";
            System.out.printf("Test %d: %s | Expected: [%s] | Got: [%s]%n",
                    i, status, expected, got);
        }

        // Large test: build a left-skewed tree of size 10000
        Node large = buildLeftSkew(10000);
        List<Integer> largeResult = verticalOrder(large);
        // PASS if we processed all nodes
        System.out.printf("Large test: %s (processed %d nodes)%n",
                largeResult.size() == 10000 ? "PASS" : "FAIL",
                largeResult.size());
    }

    // Builds a simple 3-node tree: root=arr[0], left=arr[1], right=arr[2]
    private static Node buildTree(int[] vals, String[] dirs) {
        if (vals.length < 3) return null;
        Node root = new Node(vals[0]);
        root.left = new Node(vals[1]);
        root.right = new Node(vals[2]);
        return root;
    }

    // Builds a left-skewed chain of given size: nodes n, n-1, …, 1
    private static Node buildLeftSkew(int size) {
        if (size <= 0) return null;
        Node root = new Node(size);
        Node curr = root;
        for (int v = size - 1; v >= 1; v--) {
            curr.left = new Node(v);
            curr = curr.left;
        }
        return root;
    }

    // Tree node definition
    static class Node {
        int val;
        Node left, right;

        Node(int v) {
            val = v;
        }
    }
}
