package com.interview.notes.code.year.y2023.nov23.test5;

import java.util.*;

public class BtMain {

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
        if (root == null) {
            return new int[0];
        }


        List<Integer> result = new ArrayList<>();

        Deque<Node> currentLevel = new LinkedList<>();
        Deque<Node> nextLevel = new LinkedList<>();

        currentLevel.push(root);
        boolean leftToRight = true;

        while (!currentLevel.isEmpty()) {
            Node node = currentLevel.pop();
            result.add(node.data);

            if (leftToRight) {
                if (node.left != null) nextLevel.push(node.left);
                if (node.right != null) nextLevel.push(node.right);
            } else {
                if (node.right != null) nextLevel.push(node.right);
                if (node.left != null) nextLevel.push(node.left);
            }

            if (currentLevel.isEmpty()) {
                leftToRight = !leftToRight;
                Deque<Node> temp = currentLevel;
                currentLevel = nextLevel;
                nextLevel = temp;
            }
        }
        return result.stream().mapToInt(i -> i).toArray();
    }

}

