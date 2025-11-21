package com.interview.notes.code.year.y2025.march.caspex.test6;

import java.util.*;

class Node {
    int data;
    Node left, right, nextRight;

    Node(int value) {
        data = value;
        left = null;
        right = null;
        nextRight = null;
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
        if (root == null) {
            return new int[0]; // Empty tree
        }

        // Use ArrayList to store results as we don't know final size
        List<Integer> result = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        // Start at level 1 (odd)
        boolean isOddLevel = true;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            // Process current level
            for (int i = 0; i < levelSize; i++) {
                Node current = queue.poll();

                // Add current node's value to level list
                currentLevel.add(current.data);

                // Add children to queue for next level
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }

            // Add current level to result in correct order
            // Note: Problem states odd levels right to left, even levels left to right
            // But root is level 1 (odd), so we need to handle it correctly
            if (!isOddLevel) { // Even level - left to right (as per problem)
                result.addAll(currentLevel);
            } else { // Odd level - right to left (as per problem)
                Collections.reverse(currentLevel);
                result.addAll(currentLevel);
            }

            // Toggle level parity
            isOddLevel = !isOddLevel;
        }

        // Convert ArrayList to array
        int[] resultArray = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            resultArray[i] = result.get(i);
        }

        return resultArray;
    }
}

public class ZigZagTraversal {
    public static void main(String[] args) {
        // Test case 1
        BT bt1 = new BT();
        bt1.addNode(10, 20, 'R');
        bt1.addNode(10, 30, 'L');
        System.out.println("Test Case 1:");
        int[] result1 = bt1.getLevelSpiral();
        printArray(result1);
        boolean pass1 = Arrays.equals(result1, new int[]{10, 30, 20});
        System.out.println("Test Case 1: " + (pass1 ? "PASS" : "FAIL"));

        // Test case 2
        BT bt2 = new BT();
        bt2.addNode(2, 4, 'L');
        bt2.addNode(2, 6, 'R');
        bt2.addNode(4, 8, 'L');
        bt2.addNode(4, 10, 'R');
        System.out.println("\nTest Case 2:");
        int[] result2 = bt2.getLevelSpiral();
        printArray(result2);
        boolean pass2 = Arrays.equals(result2, new int[]{2, 4, 6, 10, 8});
        System.out.println("Test Case 2: " + (pass2 ? "PASS" : "FAIL"));

        // Test case 3 (larger tree from example)
        BT bt3 = new BT();
        bt3.addNode(2, 4, 'L');
        bt3.addNode(2, 6, 'R');
        bt3.addNode(4, 8, 'L');
        bt3.addNode(4, 10, 'R');
        bt3.addNode(6, 12, 'L');
        bt3.addNode(6, 14, 'R');
        System.out.println("\nTest Case 3:");
        int[] result3 = bt3.getLevelSpiral();
        printArray(result3);
        boolean pass3 = Arrays.equals(result3, new int[]{2, 4, 6, 14, 12, 10, 8});
        System.out.println("Test Case 3: " + (pass3 ? "PASS" : "FAIL"));

        // Edge case: empty tree
        BT bt4 = new BT();
        System.out.println("\nTest Case 4 (Empty Tree):");
        int[] result4 = bt4.getLevelSpiral();
        printArray(result4);
        boolean pass4 = result4.length == 0;
        System.out.println("Test Case 4: " + (pass4 ? "PASS" : "FAIL"));
    }

    private static void printArray(int[] arr) {
        if (arr.length == 0) {
            System.out.println("[]");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) {
                sb.append(" ");
            }
        }
        System.out.println(sb);
    }
}
