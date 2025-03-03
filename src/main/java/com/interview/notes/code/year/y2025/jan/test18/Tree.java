package com.interview.notes.code.year.y2025.jan.test18;

class Tree {
    public int x;
    public Tree l;
    public Tree r;

    public Tree(int x) {
        this.x = x;
    }
}

class Solution {
    private Tree findParent(Tree root, int value) {
        if (root == null) return null;
        if ((root.l != null && root.l.x == value) ||
                (root.r != null && root.r.x == value)) {
            return root;
        }
        Tree left = findParent(root.l, value);
        return left != null ? left : findParent(root.r, value);
    }

    private void reverseConnections(Tree leaf, Tree root) {
        Tree current = leaf;
        Tree parent = findParent(root, current.x);

        while (parent != null) {
            // Store original parent's connections
            Tree parentLeft = parent.l;
            Tree parentRight = parent.r;

            // Update parent's pointers
            if (parent.l != null && parent.l.x == current.x) {
                parent.l = null;
            } else {
                parent.r = null;
            }

            // Update current node's pointers
            if (current.l == null) {
                current.l = parent;
            } else {
                current.r = parent;
            }

            current = parent;
            parent = findParent(root, current.x);
        }
    }

    public Tree solution(Tree T, int leaf_id) {
        if (T == null) return null;
        if (T.x == leaf_id) return T;

        Tree leaf = null;
        if (T.l != null && T.l.x == leaf_id) leaf = T.l;
        if (T.r != null && T.r.x == leaf_id) leaf = T.r;

        if (leaf == null) {
            leaf = findLeaf(T, leaf_id);
        }

        reverseConnections(leaf, T);
        return leaf;
    }

    private Tree findLeaf(Tree root, int leaf_id) {
        if (root == null) return null;
        if (root.x == leaf_id) return root;
        Tree left = findLeaf(root.l, leaf_id);
        return left != null ? left : findLeaf(root.r, leaf_id);
    }
}

// Test class
class TreeTest {
    public static void main(String[] args) {
        // Test Case 1: Basic test
        Tree root1 = createTestTree1();
        testCase(root1, 2, "Test Case 1");

        // Test Case 2: Larger tree
        Tree root2 = createTestTree2();
        testCase(root2, 4, "Test Case 2");

        // Test Case 3: Edge case - leaf is direct child of root
        Tree root3 = createEdgeTestTree();
        testCase(root3, 6, "Test Case 3");
    }

    private static void testCase(Tree root, int leaf_id, String testName) {
        Solution solution = new Solution();
        Tree result = solution.solution(root, leaf_id);
        System.out.println(testName + ": " +
                (validateTree(result, leaf_id) ? "PASS" : "FAIL"));
    }

    private static boolean validateTree(Tree root, int expected_root_id) {
        return root != null && root.x == expected_root_id;
    }

    private static Tree createTestTree1() {
        Tree root = new Tree(3);
        root.l = new Tree(1);
        root.r = new Tree(5);
        root.l.l = new Tree(2);
        root.l.r = new Tree(6);
        root.r.r = new Tree(4);
        return root;
    }

    private static Tree createTestTree2() {
        // Create a larger test tree
        Tree root = new Tree(1);
        root.l = new Tree(2);
        root.r = new Tree(3);
        root.l.l = new Tree(4);
        root.r.r = new Tree(5);
        return root;
    }

    private static Tree createEdgeTestTree() {
        Tree root = new Tree(1);
        root.l = new Tree(6);
        return root;
    }
}
