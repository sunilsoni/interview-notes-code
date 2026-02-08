package com.interview.notes.code.year.y2026.feb.microsoft.test5;

import java.util.*;

public class MaxTreeDistance {

    // Iterative DFS to find max distance (tree diameter)
    static long solve(Node root) {
        if (root == null) return 0; // no tree, no distance

        long maxDist = 0; // global max distance tracker

        // Map to store the longest downward path from each node
        Map<Node, Long> longestPath = new HashMap<>();

        // We need post-order traversal: process children before parent
        // Use two stacks: one for traversal order, one for processing order
        Deque<Node> stack = new ArrayDeque<>();   // traversal stack
        Deque<Node> order = new ArrayDeque<>();    // post-order sequence

        stack.push(root); // start from root

        // Step 1: Build post-order sequence using iterative DFS
        while (!stack.isEmpty()) {
            Node node = stack.pop();       // take current node
            order.push(node);              // add to post-order (will process in reverse)
            for (Node child : node.children) { // push all children to explore
                stack.push(child);
            }
        }

        // Step 2: Process nodes in post-order (leaves first, root last)
        while (!order.isEmpty()) {
            Node node = order.pop();       // get next node in post-order

            long top1 = 0, top2 = 0;      // two longest downward paths through children

            for (Node child : node.children) {
                // child's longest downward path + edge weight from child to this node
                long childPath = longestPath.getOrDefault(child, 0L) + child.value;

                // maintain top two longest paths
                if (childPath >= top1) {   // new longest found
                    top2 = top1;           // demote old longest to second
                    top1 = childPath;      // update longest
                } else if (childPath > top2) { // new second longest
                    top2 = childPath;      // update second
                }
            }

            // path through this node = sum of two best downward branches
            maxDist = Math.max(maxDist, top1 + top2); // update global max

            // store this node's longest single downward path
            longestPath.put(node, top1);
        }

        return maxDist; // return the tree diameter
    }

    // ========================= TESTING =========================
    public static void main(String[] args) {
        int passed = 0, total = 0;

        // Test 1: Example from problem - path 4->5->3->10 = 22
        {
            total++;
            Node root = new Node(0);
            Node n5 = new Node(5), n1 = new Node(1), n3 = new Node(3);
            n5.children.addAll(List.of(new Node(4), new Node(4)));
            n1.children.add(new Node(7));
            n3.children.addAll(List.of(new Node(6), new Node(2), new Node(10)));
            root.children.addAll(List.of(n5, n1, n3));
            long r = solve(root);
            boolean p = r == 22;
            System.out.println("Test 1: " + (p ? "PASS" : "FAIL") + " (exp=22, got=" + r + ")");
            if (p) passed++;
        }

        // Test 2: Single node
        {
            total++;
            long r = solve(new Node(0));
            boolean p = r == 0;
            System.out.println("Test 2: " + (p ? "PASS" : "FAIL") + " (exp=0, got=" + r + ")");
            if (p) passed++;
        }

        // Test 3: Linear chain 0->3->7->2->5 = 17
        {
            total++;
            Node root = new Node(0);
            Node a = new Node(3), b = new Node(7), c = new Node(2), d = new Node(5);
            root.children.add(a);
            a.children.add(b);
            b.children.add(c);
            c.children.add(d);
            long r = solve(root);
            boolean p = r == 17;
            System.out.println("Test 3: " + (p ? "PASS" : "FAIL") + " (exp=17, got=" + r + ")");
            if (p) passed++;
        }

        // Test 4: Star shape, best = 20+15 = 35
        {
            total++;
            Node root = new Node(0);
            root.children.addAll(List.of(new Node(10), new Node(20), new Node(15), new Node(5)));
            long r = solve(root);
            boolean p = r == 35;
            System.out.println("Test 4: " + (p ? "PASS" : "FAIL") + " (exp=35, got=" + r + ")");
            if (p) passed++;
        }

        // Test 5: Path doesn't go through root, best = 100+100 = 200
        {
            total++;
            Node root = new Node(0);
            Node a = new Node(1);
            root.children.add(a);
            a.children.addAll(List.of(new Node(100), new Node(100)));
            long r = solve(root);
            boolean p = r == 200;
            System.out.println("Test 5: " + (p ? "PASS" : "FAIL") + " (exp=200, got=" + r + ")");
            if (p) passed++;
        }

        // Test 6: All zero weights
        {
            total++;
            Node root = new Node(0);
            root.children.addAll(List.of(new Node(0), new Node(0), new Node(0)));
            long r = solve(root);
            boolean p = r == 0;
            System.out.println("Test 6: " + (p ? "PASS" : "FAIL") + " (exp=0, got=" + r + ")");
            if (p) passed++;
        }

        // Test 7: Two children, 5+8 = 13
        {
            total++;
            Node root = new Node(0);
            root.children.addAll(List.of(new Node(5), new Node(8)));
            long r = solve(root);
            boolean p = r == 13;
            System.out.println("Test 7: " + (p ? "PASS" : "FAIL") + " (exp=13, got=" + r + ")");
            if (p) passed++;
        }

        // Test 8: Deep unbalanced, best = 100+1+2+3+4 = 110
        {
            total++;
            Node root = new Node(0);
            Node a = new Node(1), b = new Node(2), c = new Node(3), d = new Node(4);
            a.children.add(b);
            b.children.add(c);
            c.children.add(d);
            root.children.addAll(List.of(a, new Node(100)));
            long r = solve(root);
            boolean p = r == 110;
            System.out.println("Test 8: " + (p ? "PASS" : "FAIL") + " (exp=110, got=" + r + ")");
            if (p) passed++;
        }

        // Test 9: Large deep chain 100K nodes - NO stack overflow now
        {
            total++;
            Node root = new Node(0);
            Node curr = root;
            long expectedSum = 0;
            for (int i = 1; i <= 100_000; i++) {
                Node next = new Node(1);       // each edge = 1
                curr.children.add(next);
                curr = next;
                expectedSum += 1;              // total = 100000
            }
            long r = solve(root);
            boolean p = r == expectedSum;
            System.out.println("Test 9 (100K chain): " + (p ? "PASS" : "FAIL")
                    + " (exp=" + expectedSum + ", got=" + r + ")");
            if (p) passed++;
        }

        // Test 10: Large star 100K children, best = 100000+99999 = 199999
        {
            total++;
            Node root = new Node(0);
            for (int i = 1; i <= 100_000; i++) {
                root.children.add(new Node(i));
            }
            long r = solve(root);
            boolean p = r == 199999;
            System.out.println("Test 10 (100K star): " + (p ? "PASS" : "FAIL")
                    + " (exp=199999, got=" + r + ")");
            if (p) passed++;
        }

        System.out.println("\n=== RESULTS: " + passed + "/" + total + " PASSED ===");
    }

    // Node: value = edge weight to parent, children = child nodes
    static class Node {
        int value;
        List<Node> children;

        Node(int value) {
            this.value = value;
            this.children = new ArrayList<>();
        }
    }
}