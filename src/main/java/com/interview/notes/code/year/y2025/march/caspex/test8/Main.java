package com.interview.notes.code.year.y2025.march.caspex.test8;

import java.util.*;

class Node {
    int data;
    Node left, right, nextRight;
    Node(int val) {
        data = val;
        left = null;
        right = null;
        nextRight = null;
    }
}

class BT {
    Node root;
    Map<Integer, Node> nodes = new HashMap<>();

    void addNode(int parent, int child, char direction) {
        if (!nodes.containsKey(parent)) {
            nodes.put(parent, new Node(parent));
        }
        if (!nodes.containsKey(child)) {
            nodes.put(child, new Node(child));
        }
        Node parentNode = nodes.get(parent);
        Node childNode = nodes.get(child);
        if (direction == 'L') {
            parentNode.left = childNode;
        } else {
            parentNode.right = childNode;
        }
        if (root == null) {
            root = parentNode;
        }
    }

    List<Integer> getLevelSpiral() {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node current = queue.poll();
                level.add(current.data);
                if (current.left != null) queue.offer(current.left);
                if (current.right != null) queue.offer(current.right);
            }
            if (!leftToRight) {
                Collections.reverse(level);
            }
            leftToRight = !leftToRight;
            result.addAll(level);
        }
        return result;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        BT tree = new BT();
        for (int i = 0; i < N; i++) {
            int p = sc.nextInt();
            int c = sc.nextInt();
            char d = sc.next().charAt(0);
            tree.addNode(p, c, d);
        }
        List<Integer> ans = tree.getLevelSpiral();
        for (int val : ans) {
            System.out.print(val + " ");
        }
    }
}