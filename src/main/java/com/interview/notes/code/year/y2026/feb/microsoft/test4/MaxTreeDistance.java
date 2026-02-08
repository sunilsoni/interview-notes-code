package com.interview.notes.code.year.y2026.feb.microsoft.test4;

import java.util.ArrayList;
import java.util.List;

public class MaxTreeDistance {

    // global variable to track the maximum distance found so far
    static long maxDist;

    // DFS: returns the longest single downward path from this node
    // while also updating maxDist with the best "through this node" path
    static long dfs(Node node) {
        // top1, top2 = two longest downward paths through different children
        long top1 = 0, top2 = 0; // init to 0 since a path can end at this node itself

        for (Node child : node.children) { // iterate over each child
            // longest path starting from child going downward + edge weight (child.value)
            long childPath = dfs(child) + child.value;

            // maintain the two largest downward paths
            if (childPath >= top1) {        // new path is the longest
                top2 = top1;                // old longest becomes second longest
                top1 = childPath;           // update longest
            } else if (childPath > top2) {  // new path is second longest
                top2 = childPath;           // update second longest
            }
        }

        // the path passing through this node using two different subtrees
        maxDist = Math.max(maxDist, top1 + top2); // update global max

        return top1; // return the single longest downward path for parent's use
    }

    // main solver: resets global max, runs DFS from root, returns answer
    static long solve(Node root) {
        maxDist = 0;       // reset before each solve call
        dfs(root);         // compute via DFS
        return maxDist;    // return the maximum distance found
    }

    // ========================= TESTING =========================
    public static void main(String[] args) {
        int passed = 0, total = 0;

        // --- Test 1: Example from problem ---
        // Root(0) -> 5{4,4}, 1{7}, 3{6,2,10}
        // Best path: 4 -> 5 -> 0(root) -> 3 -> 10 = 4+5+3+10 = 22
        {
            total++;
            Node root = new Node(0);           // root has value 0 always
            Node n5 = new Node(5);             // child with edge weight 5
            Node n1 = new Node(1);             // child with edge weight 1
            Node n3 = new Node(3);             // child with edge weight 3
            n5.children.addAll(List.of(new Node(4), new Node(4))); // leaves under n5
            n1.children.add(new Node(7));                          // leaf under n1
            n3.children.addAll(List.of(new Node(6), new Node(2), new Node(10))); // leaves under n3
            root.children.addAll(List.of(n5, n1, n3));             // attach to root

            long result = solve(root);
            boolean pass = result == 22;
            System.out.println("Test 1: " + (pass ? "PASS" : "FAIL") + " (expected=22, got=" + result + ")");
            if (pass) passed++;
        }

        // --- Test 2: Single node (root only) ---
        {
            total++;
            Node root = new Node(0); // just root, no children, distance = 0
            long result = solve(root);
            boolean pass = result == 0;
            System.out.println("Test 2: " + (pass ? "PASS" : "FAIL") + " (expected=0, got=" + result + ")");
            if (pass) passed++;
        }

        // --- Test 3: Linear chain (like a linked list) ---
        // 0 -> 3 -> 7 -> 2 -> 5, max = 3+7+2+5 = 17
        {
            total++;
            Node root = new Node(0);
            Node a = new Node(3);
            Node b = new Node(7);
            Node c = new Node(2);
            Node d = new Node(5);
            root.children.add(a);   // single chain
            a.children.add(b);
            b.children.add(c);
            c.children.add(d);
            long result = solve(root);
            boolean pass = result == 17;
            System.out.println("Test 3: " + (pass ? "PASS" : "FAIL") + " (expected=17, got=" + result + ")");
            if (pass) passed++;
        }

        // --- Test 4: Star shape (root with many leaves) ---
        // Root(0) -> 10, 20, 15, 5. Best = 20 + 15 = 35
        {
            total++;
            Node root = new Node(0);
            root.children.addAll(List.of(new Node(10), new Node(20), new Node(15), new Node(5)));
            long result = solve(root);
            boolean pass = result == 35;
            System.out.println("Test 4: " + (pass ? "PASS" : "FAIL") + " (expected=35, got=" + result + ")");
            if (pass) passed++;
        }

        // --- Test 5: Path doesn't go through root ---
        // Root(0) -> A(1) -> B(100) and A -> C(100). Best = 100+100 = 200 (through A, not root)
        {
            total++;
            Node root = new Node(0);
            Node a = new Node(1);              // small edge to root
            root.children.add(a);
            a.children.addAll(List.of(new Node(100), new Node(100))); // two heavy children
            long result = solve(root);
            boolean pass = result == 200;
            System.out.println("Test 5: " + (pass ? "PASS" : "FAIL") + " (expected=200, got=" + result + ")");
            if (pass) passed++;
        }

        // --- Test 6: All zero weights ---
        {
            total++;
            Node root = new Node(0);
            root.children.addAll(List.of(new Node(0), new Node(0), new Node(0)));
            long result = solve(root);
            boolean pass = result == 0;
            System.out.println("Test 6: " + (pass ? "PASS" : "FAIL") + " (expected=0, got=" + result + ")");
            if (pass) passed++;
        }

        // --- Test 7: Two children only ---
        // Root(0) -> 5, 8. Max = 5+8 = 13
        {
            total++;
            Node root = new Node(0);
            root.children.addAll(List.of(new Node(5), new Node(8)));
            long result = solve(root);
            boolean pass = result == 13;
            System.out.println("Test 7: " + (pass ? "PASS" : "FAIL") + " (expected=13, got=" + result + ")");
            if (pass) passed++;
        }

        // --- Test 8: Deep unbalanced tree ---
        // Root(0)->A(1)->B(2)->C(3)->D(4) and Root->E(100)
        // Best: E(100) + root + A(1)+B(2)+C(3)+D(4) = 100+1+2+3+4 = 110
        {
            total++;
            Node root = new Node(0);
            Node a = new Node(1), b = new Node(2), c = new Node(3), d = new Node(4);
            a.children.add(b); b.children.add(c); c.children.add(d);
            root.children.addAll(List.of(a, new Node(100)));
            long result = solve(root);
            boolean pass = result == 110;
            System.out.println("Test 8: " + (pass ? "PASS" : "FAIL") + " (expected=110, got=" + result + ")");
            if (pass) passed++;
        }

        // --- Test 9: Large data - deep chain of 100,000 nodes ---
        {
            total++;
            Node root = new Node(0);
            Node curr = root;
            long expectedSum = 0;
            for (int i = 1; i <= 100_000; i++) { // build long chain
                Node next = new Node(1);          // each edge weight = 1
                curr.children.add(next);
                curr = next;
                expectedSum += 1;                 // total = 100000
            }
            long result = solve(root);
            boolean pass = result == expectedSum;
            System.out.println("Test 9 (large chain 100K): " + (pass ? "PASS" : "FAIL")
                    + " (expected=" + expectedSum + ", got=" + result + ")");
            if (pass) passed++;
        }

        // --- Test 10: Large star - root with 100,000 children ---
        {
            total++;
            Node root = new Node(0);
            for (int i = 1; i <= 100_000; i++) {   // 100K children
                root.children.add(new Node(i));     // weights 1..100000
            }
            // best two = 100000 + 99999 = 199999
            long expected = 199999;
            long result = solve(root);
            boolean pass = result == expected;
            System.out.println("Test 10 (large star 100K): " + (pass ? "PASS" : "FAIL")
                    + " (expected=" + expected + ", got=" + result + ")");
            if (pass) passed++;
        }

        // --- Summary ---
        System.out.println("\n=== RESULTS: " + passed + "/" + total + " PASSED ===");
    }

    // Node structure: value = distance to parent, children = child nodes
    static class Node {
        int value;                // edge weight to parent
        List<Node> children;      // list of child nodes

        Node(int value) {
            this.value = value;
            this.children = new ArrayList<>(); // initialize empty children list
        }
    }
}