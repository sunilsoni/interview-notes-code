package com.interview.notes.code.year.y2024.march24.test12;

import java.util.List;

class BinaryTree {
    Node root;

    Node insertLevelOrder(int[] arr, Node root, int i) {
        if (i < arr.length) {
            Node temp = new Node(arr[i]);
            root = temp;

            root.left = insertLevelOrder(arr, root.left, 2 * i + 1);
            root.right = insertLevelOrder(arr, root.right, 2 * i + 2);
        }
        return root;
    }

    Node createBinaryTree(List<Integer> list) {
        int[] arr = list.stream().mapToInt(i -> i).toArray();
        return insertLevelOrder(arr, null, 0);
    }
}