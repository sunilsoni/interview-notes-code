package com.interview.notes.code.year.y2024.dec24.amazon.test13;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class LinkedListReversal {

    // Iterative approach
    public static Node reverseIterative(Node head) {
        Node prev = null;
        Node current = head;
        Node next;

        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev;
    }

    // Recursive approach
    public static Node reverseRecursive(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node rest = reverseRecursive(head.next);
        head.next.next = head;
        head.next = null;

        return rest;
    }

    // Java 8 Stream approach (for educational purposes)
    public static Node reverseUsingStream(Node head) {
        if (head == null) return null;

        // Convert linked list to stream
        List<Integer> list = new ArrayList<>();
        Node current = head;
        while (current != null) {
            list.add(current.data);
            current = current.next;
        }

        // Reverse using streams and create new linked list
        Node newHead = new Node(0); // dummy node
        Node temp = newHead;

        list.stream()
                .sorted(Collections.reverseOrder())
                .forEach(value -> {
                    temp.next = new Node(value);
                    temp.next = temp.next;
                });

        return newHead.next;
    }

    // Utility method to create linked list
    public static Node createLinkedList(int... values) {
        if (values == null || values.length == 0) return null;

        Node head = new Node(values[0]);
        Node current = head;

        for (int i = 1; i < values.length; i++) {
            current.next = new Node(values[i]);
            current = current.next;
        }

        return head;
    }

    // Utility method to print linked list
    public static void printList(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    // Test method
    public static void testReversal(String testName, Node head) {
        System.out.println("\nTest: " + testName);
        System.out.println("Original List:");
        printList(head);

        // Test iterative reversal
        Node reversedIterative = reverseIterative(copyList(head));
        System.out.println("After Iterative Reversal:");
        printList(reversedIterative);

        // Test recursive reversal
        Node reversedRecursive = reverseRecursive(copyList(head));
        System.out.println("After Recursive Reversal:");
        printList(reversedRecursive);

        // Test stream reversal
        Node reversedStream = reverseUsingStream(copyList(head));
        System.out.println("After Stream Reversal:");
        printList(reversedStream);
    }

    // Utility method to copy linked list
    public static Node copyList(Node head) {
        if (head == null) return null;

        Node newHead = new Node(head.data);
        Node current = newHead;
        Node original = head.next;

        while (original != null) {
            current.next = new Node(original.data);
            current = current.next;
            original = original.next;
        }

        return newHead;
    }

    public static void main(String[] args) {
        // Test Case 1: Normal list
        Node list1 = createLinkedList(1, 2, 3, 4, 5);
        testReversal("Normal List", list1);

        // Test Case 2: Single node
        Node list2 = createLinkedList(1);
        testReversal("Single Node", list2);

        // Test Case 3: Two nodes
        Node list3 = createLinkedList(1, 2);
        testReversal("Two Nodes", list3);

        // Test Case 4: Empty list
        testReversal("Empty List", null);

        // Test Case 5: Larger list
        Node list5 = createLinkedList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        testReversal("Larger List", list5);
    }
}
