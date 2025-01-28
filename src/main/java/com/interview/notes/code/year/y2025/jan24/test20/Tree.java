package com.interview.notes.code.year.y2025.jan24.test20;

import java.util.ArrayList;
import java.util.List;

class Tree {
    public int x;
    public Tree l;
    public Tree r;
    
    public Tree(int x) {
        this.x = x;
    }
}

class Solution {
    private Tree findNodeWithId(Tree root, int id) {
        if (root == null || root.x == id) return root;
        Tree left = findNodeWithId(root.l, id);
        return left != null ? left : findNodeWithId(root.r, id);
    }

    private Tree getParent(Tree root, Tree node) {
        if (root == null || node == null) return null;
        if ((root.l != null && root.l.x == node.x) || 
            (root.r != null && root.r.x == node.x)) {
            return root;
        }
        Tree left = getParent(root.l, node);
        return left != null ? left : getParent(root.r, node);
    }

    public Tree solution(Tree T, int leaf_id) {
        Tree leaf = findNodeWithId(T, leaf_id);
        if (leaf == null) return null;
        
        // Store the path from leaf to root
        List<Tree> path = new ArrayList<>();
        Tree current = leaf;
        Tree parent = getParent(T, current);
        
        while (parent != null) {
            path.add(parent);
            current = parent;
            parent = getParent(T, current);
        }
        
        // Reverse the connections
        current = leaf;
        for (int i = 0; i < path.size(); i++) {
            Tree node = path.get(i);
            // Remove current from its parent
            if (node.l != null && node.l.x == current.x) {
                node.l = null;
            } else {
                node.r = null;
            }
            
            // Make parent a child of current
            if (current.l == null) {
                current.l = node;
            } else {
                current.r = node;
            }
            current = node;
        }
        
        return leaf;
    }
}

class TreeTest {
    public static void main(String[] args) {
        // Test Case 1: Example from the problem
        Tree root1 = new Tree(3);
        root1.l = new Tree(1);
        root1.r = new Tree(5);
        root1.l.l = new Tree(2);
        root1.l.r = new Tree(6);
        root1.r.r = new Tree(4);
        
        Solution solution = new Solution();
        Tree result1 = solution.solution(root1, 2);
        System.out.println("Test Case 1: " + validateResult(result1, 2));
        
        // Test Case 2: Second example
        Tree root2 = new Tree(4);
        root2.l = new Tree(2);
        root2.r = new Tree(8);
        root2.r.r = new Tree(7);
        root2.r.r.l = new Tree(6);
        root2.l.l = new Tree(1);
        root2.l.l.r = new Tree(3);
        root2.r.l = new Tree(5);
        
        Tree result2 = solution.solution(root2, 6);
        System.out.println("Test Case 2: " + validateResult(result2, 6));
    }
    
    private static boolean validateResult(Tree root, int expectedRootId) {
        return root != null && root.x == expectedRootId;
    }
    
    private static void printTree(Tree root) {
        if (root == null) return;
        System.out.print(root.x + " ");
        printTree(root.l);
        printTree(root.r);
    }
}
