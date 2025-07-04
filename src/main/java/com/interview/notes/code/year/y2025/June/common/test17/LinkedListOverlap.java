package com.interview.notes.code.year.y2025.June.common.test17;

public class LinkedListOverlap {
    // Method to find overlapping node value between two linked lists
    public static int findOverlappingNodeValue(Node list1, Node list2) {
        // Handle edge cases where either list is null
        if (list1 == null || list2 == null) {
            return -1;
        }

        // Calculate length of both lists
        int length1 = getLength(list1);
        int length2 = getLength(list2);

        // Adjust starting points to handle lists of different lengths
        int diff = Math.abs(length1 - length2);
        if (length1 > length2) {
            while (diff-- > 0) list1 = list1.next;
        } else {
            while (diff-- > 0) list2 = list2.next;
        }

        // Traverse both lists simultaneously to find overlap
        while (list1 != null && list2 != null) {
            if (list1.value == list2.value) {
                return list1.value;
            }
            list1 = list1.next;
            list2 = list2.next;
        }

        // No overlap found
        return -1;
    }

    // Helper method to calculate length of linked list
    private static int getLength(Node head) {
        int length = 0;
        Node current = head;
        while (current != null) {
            length++;
            current = current.next;
        }
        return length;
    }

    // Helper method to create a linked list from array
    private static Node createList(int[] values) {
        if (values == null || values.length == 0) return null;
        Node head = new Node(values[0]);
        Node current = head;
        for (int i = 1; i < values.length; i++) {
            current.next = new Node(values[i]);
            current = current.next;
        }
        return head;
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test Case 1: Lists with overlap
        int[] arr1 = {1, 2, 3, 4, 5, 9, 10, 11, 12};
        int[] arr2 = {6, 7, 8, 9, 10, 11, 12};
        Node list1 = createList(arr1);
        Node list2 = createList(arr2);
        int result = findOverlappingNodeValue(list1, list2);
        System.out.println("Test Case 1: " + (result == 9 ? "PASS" : "FAIL") +
                " (Expected: 9, Got: " + result + ")");

        // Test Case 2: Lists without overlap
        int[] arr3 = {1, 2, 3, 4};
        int[] arr4 = {6, 7, 8, 9};
        Node list3 = createList(arr3);
        Node list4 = createList(arr4);
        result = findOverlappingNodeValue(list3, list4);
        System.out.println("Test Case 2: " + (result == -1 ? "PASS" : "FAIL") +
                " (Expected: -1, Got: " + result + ")");

        // Test Case 3: Empty lists
        result = findOverlappingNodeValue(null, null);
        System.out.println("Test Case 3: " + (result == -1 ? "PASS" : "FAIL") +
                " (Expected: -1, Got: " + result + ")");

        // Test Case 4: Large lists
        int[] largeArr1 = new int[10000];
        int[] largeArr2 = new int[10000];
        for (int i = 0; i < 10000; i++) {
            largeArr1[i] = i;
            largeArr2[i] = i + 5000;
        }
        Node largeList1 = createList(largeArr1);
        Node largeList2 = createList(largeArr2);
        result = findOverlappingNodeValue(largeList1, largeList2);
        System.out.println("Test Case 4 (Large Lists): " + (result == 5000 ? "PASS" : "FAIL") +
                " (Expected: 5000, Got: " + result + ")");
    }

    // Node class to represent each element in the linked list
    static class Node {
        int value;
        Node next;

        // Constructor to create a new node
        Node(int value) {
            this.value = value;
            this.next = null;
        }
    }
}
