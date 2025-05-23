package com.interview.notes.code.year.y2025.may.meta.test4;

import java.util.*;

class Node {
    int data;
    Node left, right;

    Node(int data) {
        this.data = data;
        left = right = null;
    }
}

class Solution {
    public List<Integer> verticalTraversal(Node root) {
        // Handle empty tree
        if (root == null) return new ArrayList<>();

        // TreeMap to store nodes by HD
        TreeMap<Integer, TreeMap<Integer, List<Integer>>> map = new TreeMap<>();
        Queue<NodeInfo> queue = new LinkedList<>();
        queue.offer(new NodeInfo(root, 0, 0));

        // Level order traversal
        while (!queue.isEmpty()) {
            NodeInfo curr = queue.poll();

            // Get or create HD map
            map.putIfAbsent(curr.hd, new TreeMap<>());
            // Get or create level map
            map.get(curr.hd).putIfAbsent(curr.level, new ArrayList<>());
            // Add node to appropriate list
            map.get(curr.hd).get(curr.level).add(curr.node.data);

            // Add children to queue
            if (curr.node.left != null) {
                queue.offer(new NodeInfo(curr.node.left, curr.hd - 1, curr.level + 1));
            }
            if (curr.node.right != null) {
                queue.offer(new NodeInfo(curr.node.right, curr.hd + 1, curr.level + 1));
            }
        }

        // Collect result
        List<Integer> result = new ArrayList<>();
        for (TreeMap<Integer, List<Integer>> hdMap : map.values()) {
            for (List<Integer> nodes : hdMap.values()) {
                Collections.sort(nodes);  // Sort nodes at same level
                result.addAll(nodes);
            }
        }

        return result;
    }

    static class NodeInfo {
        Node node;
        int hd;    // horizontal distance
        int level; // tree level

        NodeInfo(Node node, int hd, int level) {
            this.node = node;
            this.hd = hd;
            this.level = level;
        }
    }
}

