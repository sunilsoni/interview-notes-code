package com.interview.notes.code.months.nov23.test5;

import java.util.*;

class BT1
{
    Node root;
    Map<Integer, Node> dangling = new HashMap<>();

    void addNode(int parent, int child, char direction) {
        Node childNode, parentNode;
        if (!dangling.containsKey(child)) {
            childNode = new Node(child);
            dangling.put(child, childNode);
        } 
        else {
            childNode = dangling.get(child);
        }

        if (!dangling.containsKey(parent)) {
            parentNode = new Node(parent);
            dangling.put(parent, parentNode);
        } 
        else {
            parentNode = dangling.get(parent);
        }

        if (root == null) {
            root = parentNode;
        }
        
        if (direction == 'L') {
            parentNode.left = childNode;
        } 
        else {
            parentNode.right = childNode;
        }
    }

    int[] getLevelSpiral() {
        if (root == null) {
            return new int[0];
        }

        List<Integer> result = new ArrayList<>();
        Deque<Node> deque = new LinkedList<>();
        deque.add(root);
        boolean leftToRight = false;

        while (!deque.isEmpty()) {
            int levelSize = deque.size();
            Integer[] levelNodes = new Integer[levelSize];

            for (int i = 0; i < levelSize; i++) {
                Node node = leftToRight ? deque.pollFirst() : deque.pollLast();
                int index = leftToRight ? i : levelSize - 1 - i;
                levelNodes[index] = node.data;

                if (leftToRight) {
                    if (node.left != null) deque.addLast(node.left);
                    if (node.right != null) deque.addLast(node.right);
                } else {
                    if (node.right != null) deque.addFirst(node.right);
                    if (node.left != null) deque.addFirst(node.left);
                }
            }

            leftToRight = !leftToRight;
            result.addAll(Arrays.asList(levelNodes));
        }

        return result.stream().mapToInt(i -> i).toArray();
    }

}
