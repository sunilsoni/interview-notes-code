package com.interview.notes.code.year.y2025.jan24.test19;

import java.util.*;

// Simple binary tree node structure:
class Tree {
    public int x;      // node ID
    public Tree l;     // left subtree
    public Tree r;     // right subtree
    public Tree(int x) {
        this.x = x;
    }
}

public class ReRootBinaryTree {

    static ArrayList<Integer>[] adj;     // adjacency list
    static Tree[] nodes;                 // store all tree nodes by index (1..N)
    static boolean[] visited;

    // Recursively build the re-rooted subtree
    static Tree buildReRooted(int current, int parent) {
        visited[current] = true;
        Tree root = nodes[current];  // current node becomes root in new structure
        root.l = null;
        root.r = null;
        // Track how many children we've assigned:
        int assigned = 0;
        for(int nxt : adj[current]) {
            if(nxt == parent) continue; // don't go back up
            if(!visited[nxt]) {
                Tree subtree = buildReRooted(nxt, current);
                // assign left or right
                if(assigned == 0) {
                    root.l = subtree;
                } else {
                    root.r = subtree;
                }
                assigned++;
            }
        }
        return root;
    }

    // Main "solution" method
    static Tree reRoot(Tree[] allNodes, int N, int leafId) {
        // Build adjacency from the existing left/right pointers
        adj = new ArrayList[N+1];
        for(int i=1;i<=N;i++){
            adj[i] = new ArrayList<>();
        }
        for(int i=1;i<=N;i++){
            Tree t = allNodes[i];
            if(t.l!=null){
                int leftId = t.l.x;
                adj[t.x].add(leftId);
                adj[leftId].add(t.x);
            }
            if(t.r!=null){
                int rightId = t.r.x;
                adj[t.x].add(rightId);
                adj[rightId].add(t.x);
            }
        }

        nodes = allNodes;
        visited = new boolean[N+1];
        // Rebuild with leafId as root
        return buildReRooted(leafId, -1);
    }

    // Simple method to test correctness (just checks root ID for demonstration)
    static boolean checkRoot(Tree newRoot, int expectedRoot){
        return newRoot.x == expectedRoot;
    }

    // Example usage + tests
    public static void main(String[] args) {
        // Example with 5 nodes:
        // We'll create a small tree: 1 -> (2, 3), 2->(4,null), 3->(null,5)
        // Let's set leafId=4 to re-root at node 4.
        int N=5;
        Tree[] all = new Tree[N+1];
        for(int i=1;i<=N;i++){
            all[i] = new Tree(i);
        }
        all[1].l = all[2];
        all[1].r = all[3];
        all[2].l = all[4];
        all[2].r = null;
        all[3].l = null;
        all[3].r = all[5];
        int leafId = 4;

        Tree newRoot = reRoot(all, N, leafId);
        if(checkRoot(newRoot, leafId)){
            System.out.println("Test1: PASS");
        } else {
            System.out.println("Test1: FAIL");
        }

        // Additional tests, including edge cases, large data, etc. can follow:
        // ...
    }
}
