package com.interview.notes.code.year.y2025.may.meta.test3;

import java.util.*;
import java.util.stream.Collectors;

public class VerticalOrderTraversal {

    // ---------- Core algorithm ----------
    public static List<Integer> verticalOrder(Node root) {
        if (root == null) return Collections.emptyList();

        Map<Integer, List<Integer>> map = new TreeMap<>();
        Deque<Pair> q = new ArrayDeque<>();
        q.offer(new Pair(root, 0));

        while (!q.isEmpty()) {
            Pair cur = q.poll();
            map.computeIfAbsent(cur.hd, k -> new ArrayList<>()).add(cur.node.val);

            if (cur.node.left != null) q.offer(new Pair(cur.node.left, cur.hd - 1));
            if (cur.node.right != null) q.offer(new Pair(cur.node.right, cur.hd + 1));
        }

        // Flatten left-to-right, top-to-bottom
        return map.values()
                .stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    // ---------- Test harness (simple main) ----------
    public static void main(String[] args) {
        List<TestCase> tests = new ArrayList<>();

        // Sample tree from the statement
        tests.add(new TestCase(buildSampleTree(),
                Arrays.asList(5, 9, 3, 2, 6, 1, 7, 4, 8, 0),
                "Sample"));

        // Edge-case: single node
        tests.add(new TestCase(new Node(42), List.of(42), "Single node"));

        // Edge-case: empty tree
        tests.add(new TestCase(null, Collections.emptyList(), "Empty tree"));

        // Skewed left
        tests.add(new TestCase(buildSkewed(false, 5), Arrays.asList(5, 4, 3, 2, 1), "Left-skewed"));

        // Skewed right
        tests.add(new TestCase(buildSkewed(true, 5), Arrays.asList(1, 2, 3, 4, 5), "Right-skewed"));

        // Large balanced tree (depth 15 ≈ 65 535 nodes)
        tests.add(new TestCase(buildComplete(15), null, "Large tree (65k nodes)"));

        // Run tests
        tests.forEach(VerticalOrderTraversal::runTest);
    }

    // ---------- Helpers ----------
    private static void runTest(TestCase tc) {
        long start = System.currentTimeMillis();
        List<Integer> actual = verticalOrder(tc.root);
        long time = System.currentTimeMillis() - start;

        boolean pass = tc.expected == null || tc.expected.equals(actual);
        System.out.printf("%-25s : %s (%,d nodes, %d ms)%n",
                tc.name, pass ? "PASS" : "FAIL", countNodes(tc.root), time);

        if (!pass) {
            System.out.println(" expected = " + tc.expected);
            System.out.println(" actual   = " + actual);
        }
    }

    private static int countNodes(Node root) {
        if (root == null) return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    // Build the sample tree
    private static Node buildSampleTree() {
        Node n6 = new Node(6);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n1 = new Node(1);
        Node n0 = new Node(0);
        Node n2 = new Node(2);
        Node n8 = new Node(8);
        Node n9 = new Node(9);
        Node n7 = new Node(7);

        n6.left = n3;
        n6.right = n4;
        n3.left = n5;
        n5.right = n2;
        n2.left = n9;
        n4.left = n1;
        n4.right = n0;
        n0.left = n8;
        n8.right = n7;
        return n6;
    }

    // Build a skewed tree (isRight=true ⇒ right-skewed)
    private static Node buildSkewed(boolean isRight, int n) {
        Node head = new Node(isRight ? 1 : n);
        Node cur = head;
        for (int i = 2; i <= n; i++) {
            Node next = new Node(isRight ? i : n - i + 1);
            if (isRight) cur.right = next;
            else cur.left = next;
            cur = next;
        }
        return head;
    }

    // Build a complete binary tree of given depth (root depth = 1)
    private static Node buildComplete(int depth) {
        if (depth == 0) return null;
        Node root = new Node(1);
        Queue<Node> q = new ArrayDeque<>();
        q.offer(root);
        int val = 2;
        for (int d = 1; d < depth; d++) {
            int level = q.size();
            for (int i = 0; i < level; i++) {
                Node n = q.poll();
                n.left = new Node(val++);
                n.right = new Node(val++);
                q.offer(n.left);
                q.offer(n.right);
            }
        }
        return root;
    }

    // ---------- Node definition ----------
    static class Node {
        int val;
        Node left, right;

        Node(int v) {
            val = v;
        }
    }

    private static class Pair {
        Node node;
        int hd;

        Pair(Node n, int h) {
            node = n;
            hd = h;
        }
    }

    // Container for test cases
    private static class TestCase {
        Node root;
        List<Integer> expected;
        String name;

        TestCase(Node r, List<Integer> exp, String n) {
            root = r;
            expected = exp;
            name = n;
        }
    }
}
