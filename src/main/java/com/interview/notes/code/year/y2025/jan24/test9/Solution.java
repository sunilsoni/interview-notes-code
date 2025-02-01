package com.interview.notes.code.year.y2025.jan24.test9;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    // Helper method to serialize the tree for testing purposes
    private static String serialize(Tree root) {
        if (root == null) {
            return "None";
        }
        String left = serialize(root.l);
        String right = serialize(root.r);
        return "(" + root.x + ", " + left + ", " + right + ")";
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Test case 1
        Tree two = new Tree(2, null, null);
        Tree six = new Tree(6, null, null);
        Tree one = new Tree(1, two, six);
        Tree four = new Tree(4, null, null);
        Tree five = new Tree(5, null, four);
        Tree three = new Tree(3, one, five);
        int leafId1 = 2;
        Tree result1 = sol.solution(three, leafId1);
        System.out.println("Test case 1 result: " + serialize(result1));
        // Expected: (2, None, (1, (6, None, None), (3, (5, None, (4, None, None)), None)))

        // Test case 2
        Tree sixNode = new Tree(6, null, null);
        Tree seven = new Tree(7, sixNode, null);
        Tree eight = new Tree(8, seven, null);
        Tree threeNode = new Tree(3, null, null);
        Tree fiveNode = new Tree(5, null, null);
        Tree oneNode = new Tree(1, threeNode, fiveNode);
        Tree twoNode = new Tree(2, null, null);
        Tree rootFour = new Tree(4, twoNode, eight);
        int leafId2 = 6;
        Tree result2 = sol.solution(rootFour, leafId2);
        System.out.println("Test case 2 result: " + serialize(result2));
        // Expected: (6, None, (7, None, (8, (4, (2, None, None), None), (3, None, (5, None, None)))))
    }

    public Tree solution(Tree T, int leaf_id) {
        List<Tree> path = new ArrayList<>();
        if (!findPath(T, leaf_id, path) || path.isEmpty()) {
            return T;
        }

        Tree leaf = path.get(path.size() - 1);
        if (leaf.l != null || leaf.r != null) {
            return T;
        }

        Tree newRoot = leaf;

        for (int i = path.size() - 2; i >= 0; i--) {
            Tree current = path.get(i);
            Tree child = path.get(i + 1);

            boolean isLeft = (current.l == child);
            Tree otherChild = isLeft ? current.r : current.l;

            // Attach current to the opposite side of child to avoid overwriting
            if (isLeft) {
                child.r = current;  // Attach current to child's right
            } else {
                child.l = current;  // Attach current to child's left
            }

            // Update current's children to otherChild and null
            if (isLeft) {
                current.l = otherChild;
                current.r = null;
            } else {
                current.r = otherChild;
                current.l = null;
            }
        }

        return newRoot;
    }

    private boolean findPath(Tree node, int leaf_id, List<Tree> path) {
        if (node == null) {
            return false;
        }

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

    public static class Tree {
        public int x;
        public Tree l;
        public Tree r;

        public Tree(int x, Tree l, Tree r) {
            this.x = x;
            this.l = l;
            this.r = r;
        }
    }
}