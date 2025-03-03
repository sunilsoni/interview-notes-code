package com.interview.notes.code.year.y2025.jan.tets16;

import java.util.ArrayList;
import java.util.List;

class Tree {
    public int x;
    public Tree l;
    public Tree r;

    public Tree(int x) {
        this.x = x;
        this.l = null;
        this.r = null;
    }
}

class Solution {
    public Tree solution(Tree T, int leaf_id) {
        // Step 1: Find the path from root to leaf
        List<Tree> path = new ArrayList<>();
        findPath(T, leaf_id, path);

        if (path.isEmpty()) {
            return null;
        }

        // Debug print
        System.out.println("Path from root to leaf:");
        for (Tree node : path) {
            System.out.print(node.x + " -> ");
        }
        System.out.println();

        // The leaf node will be our new root
        Tree newRoot = path.get(path.size() - 1);
        Tree current = newRoot;

        // Step 2: Reverse the connections
        for (int i = path.size() - 2; i >= 0; i--) {
            Tree parent = path.get(i);

            // Store the original children of parent
            Tree parentOriginalLeft = parent.l;
            Tree parentOriginalRight = parent.r;

            // Remove the current node from parent
            if (parent.l != null && parent.l.x == current.x) {
                parent.l = null;
            } else if (parent.r != null && parent.r.x == current.x) {
                parent.r = null;
            }

            // Make parent a child of current
            if (current.l == null) {
                current.l = parent;
            } else {
                current.r = parent;
            }

            // Maintain other connections
            if (parentOriginalLeft != null && parentOriginalLeft.x != current.x) {
                parent.l = parentOriginalLeft;
            }
            if (parentOriginalRight != null && parentOriginalRight.x != current.x) {
                parent.r = parentOriginalRight;
            }

            current = parent;
        }

        // Debug print final structure
        System.out.println("Final tree structure:");
        printTree(newRoot, "", true);

        return newRoot;
    }

    private boolean findPath(Tree node, int leaf_id, List<Tree> path) {
        if (node == null) return false;

        path.add(node);

        if (node.x == leaf_id) {
            return true;
        }

        if (findPath(node.l, leaf_id, path) || findPath(node.r, leaf_id, path)) {
            return true;
        }

        path.remove(path.size() - 1);
        return false;
    }

    private void printTree(Tree node, String prefix, boolean isLeft) {
        if (node == null) return;

        System.out.println(prefix + (isLeft ? "├── " : "└── ") + node.x);

        if (node.l != null || node.r != null) {
            printTree(node.l, prefix + (isLeft ? "│   " : "    "), true);
            printTree(node.r, prefix + (isLeft ? "│   " : "    "), false);
        }
    }
}

// Test code
public class Main {
    public static void main(String[] args) {
        // Create test tree: ((3, (1, (2, None, None), (6, None, None))), (5, None, (4, None, None)))
        Tree root = new Tree(3);
        root.l = new Tree(1);
        root.r = new Tree(5);
        root.l.l = new Tree(2);
        root.l.r = new Tree(6);
        root.r.r = new Tree(4);

        Solution solution = new Solution();
        System.out.println("Original tree structure:");
        // solution.printTree(root, "", true);

        Tree result = solution.solution(root, 2);

        System.out.println("\nResult tree structure:");
        // solution.printTree(result, "", true);
    }
}
