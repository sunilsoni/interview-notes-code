package com.interview.notes.code.months.march24.test10;

import java.util.*;

class Node {
    int data;
    Node left, right;

    Node(int data) {
        this.data = data;
    }
}

class BinaryTree {
    Node root;
    Map<Integer, Node> nodeMap = new HashMap<>();

    void addNode(int parent, int child, char direction) {
        Node parentNode = nodeMap.getOrDefault(parent, new Node(parent));
        Node childNode = new Node(child);
        if (direction == 'L') {
            parentNode.left = childNode;
        } else {
            parentNode.right = childNode;
        }
        nodeMap.put(parent, parentNode);
        nodeMap.put(child, childNode);
        root = nodeMap.getOrDefault(1, null);
    }
    int[] getLevelSpiral() {
        if (root == null) return new int[0];

        List<Integer> result = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = false;

        while (!queue.isEmpty()) {
            int size = queue.size();
            Integer[] row = new Integer[size];
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                int index = leftToRight ? i : size - 1 - i;
                row[index] = node.data;

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            leftToRight = !leftToRight;
            Collections.addAll(result, row);
        }

        return result.stream().mapToInt(i -> i).toArray();
    }
}

public class Solution {
    public static void main (String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        BinaryTree tree = new BinaryTree();
        for (int i = 0; i < n; i++) {
            int parent = in.nextInt();
            int child = in.nextInt();
            char direction = in.next().charAt(0);
            tree.addNode(parent, child, direction);
        }
        int[] spiralOrder = tree.getLevelSpiral();
        for (int val : spiralOrder) {
            System.out.print(val + " ");
        }
    }
}
