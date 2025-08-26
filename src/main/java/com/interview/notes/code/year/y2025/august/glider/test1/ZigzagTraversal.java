package com.interview.notes.code.year.y2025.august.glider.test1;

import java.util.*;
import java.util.stream.*;

class Node {
    int data;
    Node left, right;
    Node(int value) {
        data = value;
        left = null;
        right = null;
    }
}

class BT {
    Node root;
    Map<Integer, Node> dangling = new HashMap<>();

    void addNode(int parent, int child, char direction) {
        Node childNode, parentNode;
        if (!dangling.containsKey(child)) {
            childNode = new Node(child);
            dangling.put(child, childNode);
        } else {
            childNode = dangling.get(child);
        }

        if (!dangling.containsKey(parent)) {
            parentNode = new Node(parent);
            dangling.put(parent, parentNode);
        } else {
            parentNode = dangling.get(parent);
        }

        if (root == null) {
            root = parentNode;
        }

        if (direction == 'L') {
            parentNode.left = childNode;
        } else {
            parentNode.right = childNode;
        }
    }

    int[] getLevelSpiral() {
        if (root == null) return new int[0];
        List<Integer> result = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = false;

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                level.add(node.data);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            if (leftToRight) {
                result.addAll(level);
            } else {
                Collections.reverse(level);
                result.addAll(level);
            }
            leftToRight = !leftToRight;
        }
        return result.stream().mapToInt(i -> i).toArray();
    }
}

public class ZigzagTraversal {
    public static void main(String[] args) {
        List<Object[]> tests = Arrays.asList(
                new Object[]{
                        new int[][]{{10,20,'R'},{10,30,'L'}},
                        new int[]{10,30,20}
                },
                new Object[]{
                        new int[][]{{2,4,'L'},{2,6,'R'},{4,8,'L'},{4,10,'R'}},
                        new int[]{2,4,6,10,8}
                }
        );

        for (Object[] test : tests) {
            int[][] edges = (int[][]) test[0];
            int[] expected = (int[]) test[1];
            BT tree = new BT();
            for (int[] edge : edges) {
                tree.addNode(edge[0], edge[1], (char) edge[2]);
            }
            int[] output = tree.getLevelSpiral();
            System.out.println("Expected: " + Arrays.toString(expected) +
                    " | Got: " + Arrays.toString(output) +
                    " | Result: " + (Arrays.equals(output, expected) ? "PASS" : "FAIL"));
        }

        BT largeTree = new BT();
        int N = 3000;
        for (int i = 1; i < N; i++) {
            largeTree.addNode(i, i + 1, 'R');
        }
        long start = System.currentTimeMillis();
        int[] result = largeTree.getLevelSpiral();
        long end = System.currentTimeMillis();
        System.out.println("Large Input Test | Nodes: " + N + " | Traversal size: " + result.length + " | Time(ms): " + (end - start));
    }
}