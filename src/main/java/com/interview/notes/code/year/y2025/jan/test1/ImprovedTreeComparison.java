package com.interview.notes.code.year.y2025.jan.test1;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ImprovedTreeComparison {
    // Improved comparison method using HashMap for frequency counting
    public static boolean isTreeContentEqual(Node a, Node b) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        // Count frequencies for first tree (add)
        countFrequencies(a, frequencyMap, true);

        // Count frequencies for second tree (subtract)
        countFrequencies(b, frequencyMap, false);

        // Check if all frequencies are zero
        return frequencyMap.values().stream().allMatch(count -> count == 0);
    }

    // Helper method to count frequencies
    private static void countFrequencies(Node root, Map<Integer, Integer> map, boolean isAdding) {
        if (root == null) return;

        // Update frequency
        int delta = isAdding ? 1 : -1;
        map.merge(root.val, delta, Integer::sum);
        if (map.get(root.val) == 0) {
            map.remove(root.val); // Remove entry if frequency becomes 0
        }

        // Recurse on children
        countFrequencies(root.left, map, isAdding);
        countFrequencies(root.right, map, isAdding);
    }

    public static void main(String[] args) {
        // Test cases
        runAllTests();
        // Performance test
        performanceBenchmark();
    }

    private static void runAllTests() {
        // Test 1: Original example
        Node a = createFirstTree();
        Node b = createSecondTree();
        runTest("Test 1 (Original Example)", a, b, true);

        // Test 2: Empty trees
        runTest("Test 2 (Empty Trees)", null, null, true);

        // Test 3: Trees with duplicates
        Node c = new Node(1);
        c.left = new Node(1);
        Node d = new Node(1);
        d.right = new Node(1);
        runTest("Test 3 (Duplicate Values)", c, d, true);

        // Test 4: Different content
        Node e = new Node(1);
        e.left = new Node(2);
        Node f = new Node(1);
        f.left = new Node(3);
        runTest("Test 4 (Different Values)", e, f, false);
    }

    private static void performanceBenchmark() {
        // Create large trees for performance testing
        Node largeTree1 = generateLargeTree(10000);
        Node largeTree2 = generateLargeTree(10000);

        long startTime = System.nanoTime();
        boolean result = isTreeContentEqual(largeTree1, largeTree2);
        long endTime = System.nanoTime();

        System.out.printf("Large tree comparison (%d nodes) took: %.2f ms%n",
                10000, (endTime - startTime) / 1_000_000.0);
    }

    private static Node generateLargeTree(int size) {
        Random rand = new Random();
        Node root = new Node(rand.nextInt(1000));
        for (int i = 1; i < size; i++) {
            insertRandomly(root, rand.nextInt(1000), rand);
        }
        return root;
    }

    private static void insertRandomly(Node root, int value, Random rand) {
        while (true) {
            if (rand.nextBoolean()) {
                if (root.left == null) {
                    root.left = new Node(value);
                    break;
                }
                root = root.left;
            } else {
                if (root.right == null) {
                    root.right = new Node(value);
                    break;
                }
                root = root.right;
            }
        }
    }

    private static void runTest(String testName, Node tree1, Node tree2, boolean expected) {
        boolean result = isTreeContentEqual(tree1, tree2);
        System.out.println(testName + ": " +
                (result == expected ? "PASS" : "FAIL") +
                " (Got: " + result + ", Expected: " + expected + ")");
    }

    private static Node createFirstTree() {
        Node root = new Node(9);
        root.left = new Node(5);
        root.right = new Node(11);
        root.left.left = new Node(2);
        root.left.right = new Node(6);
        return root;
    }

    private static Node createSecondTree() {
        Node root = new Node(6);
        root.left = new Node(5);
        root.right = new Node(9);
        root.left.left = new Node(2);
        root.right.right = new Node(11);
        return root;
    }

    static class Node {
        int val;
        Node left;
        Node right;

        Node(int val) {
            this.val = val;
        }
    }
}
