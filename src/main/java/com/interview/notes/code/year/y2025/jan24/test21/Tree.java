package com.interview.notes.code.year.y2025.jan24.test21;

import java.util.*;

// Tree node structure
class Tree {
    public int x;
    public Tree l;
    public Tree r;
    public Tree(int x) { this.x = x; }
}

class Solution {

    // Rebuild the tree so that 'leaf_id' becomes the new root.
    // Uses an adjacency-based approach to preserve all connections.
    public Tree solution(Tree T, int leaf_id) {
        // 1) Gather all nodes in an array or map so we can reference them by ID
        Map<Integer, Tree> map = new HashMap<>();
        buildMap(T, map);

        // 2) Build an undirected adjacency list from the original tree
        //    This ensures we don’t lose any sibling subtrees.
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (int id : map.keySet()) {
            adj.put(id, new ArrayList<>());
        }
        fillAdjacency(T, adj);

        // 3) Re-root using DFS from 'leaf_id', ignoring the old parent as “visited”
        Set<Integer> visited = new HashSet<>();
        return buildNewRoot(leaf_id, -1, adj, map, visited);
    }

    // Helper: store every node in a map for quick access by ID
    private void buildMap(Tree root, Map<Integer, Tree> map) {
        if (root == null) return;
        map.put(root.x, root);
        buildMap(root.l, map);
        buildMap(root.r, map);
    }

    // Helper: fill undirected adjacency from existing tree pointers
    private void fillAdjacency(Tree root, Map<Integer, List<Integer>> adj) {
        if (root == null) return;
        if (root.l != null) {
            adj.get(root.x).add(root.l.x);
            adj.get(root.l.x).add(root.x);
            fillAdjacency(root.l, adj);
        }
        if (root.r != null) {
            adj.get(root.x).add(root.r.x);
            adj.get(root.r.x).add(root.x);
            fillAdjacency(root.r, adj);
        }
    }

    // DFS to build the new tree so that 'current' becomes root;
    // skip the old parent so we don’t go back in circles.
    private Tree buildNewRoot(int current, int oldParent,
                              Map<Integer, List<Integer>> adj,
                              Map<Integer, Tree> map,
                              Set<Integer> visited) {
        visited.add(current);
        Tree newRoot = map.get(current);
        newRoot.l = null;
        newRoot.r = null;

        int childCount = 0;
        for (int nxt : adj.get(current)) {
            if (nxt == oldParent) continue; // skip the edge going back
            if (!visited.contains(nxt)) {
                Tree subtree = buildNewRoot(nxt, current, adj, map, visited);
                if (childCount == 0) {
                    newRoot.l = subtree;
                } else {
                    newRoot.r = subtree;
                }
                childCount++;
            }
        }
        return newRoot;
    }
}

class TreeTest {
    public static void main(String[] args) {
        // Simple “pass/fail” tests in main (no JUnit)
        testCase1();
        testCase2();
    }

    private static void testCase1() {
        /*
          Original Tree:
               3
             /   \
            1     5
           / \     \
          2   6     4

          leaf_id = 2
        */
        Tree root = new Tree(3);
        root.l = new Tree(1);
        root.r = new Tree(5);
        root.l.l = new Tree(2);
        root.l.r = new Tree(6);
        root.r.r = new Tree(4);

        Solution sol = new Solution();
        Tree newRoot = sol.solution(root, 2);
        // Just verify new root is 2
        System.out.println("TestCase1: " + (newRoot != null && newRoot.x == 2 ? "PASS" : "FAIL"));
    }

    private static void testCase2() {
        /*
          Original Tree:
                4
               / \
              2   8
             /   / \
            1   5   7
             \       /
              3     6

          leaf_id = 6
        */
        Tree root = new Tree(4);
        root.l = new Tree(2);
        root.r = new Tree(8);
        root.l.l = new Tree(1);
        root.l.l.r = new Tree(3);
        root.r.l = new Tree(5);
        root.r.r = new Tree(7);
        root.r.r.l = new Tree(6);

        Solution sol = new Solution();
        Tree newRoot = sol.solution(root, 6);
        // Verify new root is 6
        System.out.println("TestCase2: " + (newRoot != null && newRoot.x == 6 ? "PASS" : "FAIL"));
    }
}
