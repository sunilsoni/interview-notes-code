package com.interview.notes.code.year.y2025.jan24.test23;

import java.util.*;

class Tree {
    public int x;
    public Tree l;
    public Tree r;

    public Tree(int x) {
        this.x = x;
    }
}

class Solution {

    public Tree solution(Tree T, int leaf_id) {
        Map<Integer, Tree> map = new HashMap<>();
        buildMap(T, map);

        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (int id : map.keySet()) {
            adj.put(id, new ArrayList<>());
        }
        fillAdjacency(T, adj);

        Set<Integer> visited = new HashSet<>();
        return buildNewRoot(leaf_id, -1, adj, map, visited);
    }

    private void buildMap(Tree root, Map<Integer, Tree> map) {
        if (root == null) return;
        map.put(root.x, root);
        buildMap(root.l, map);
        buildMap(root.r, map);
    }

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
            if (nxt == oldParent) continue;
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
        testCase1();
        testCase2();
    }

    private static void testCase1() {
        Tree root = new Tree(3);
        root.l = new Tree(1);
        root.r = new Tree(5);
        root.l.l = new Tree(2);
        root.l.r = new Tree(6);
        root.r.r = new Tree(4);

        Solution sol = new Solution();
        Tree newRoot = sol.solution(root, 2);
        System.out.println("TestCase1: " + (newRoot != null && newRoot.x == 2 ? "PASS" : "FAIL"));
    }

    private static void testCase2() {
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
        System.out.println("TestCase2: " + (newRoot != null && newRoot.x == 6 ? "PASS" : "FAIL"));
    }
}
