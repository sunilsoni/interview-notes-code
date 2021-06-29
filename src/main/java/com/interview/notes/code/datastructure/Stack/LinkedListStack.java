package com.interview.notes.code.datastructure.Stack;

public class LinkedListStack {
    private Node head; // the first node

    public LinkedListStack() {
        head = null;
    }

    public static void printList(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.format("%d ", temp.value);
            temp = temp.next;
        }
        System.out.println();
    }

    // Remove value from the beginning of the list for demonstrating behaviour of stack
    public int pop() throws LinkedListEmptyException {
        if (head == null) {
            throw new LinkedListEmptyException();
        }
        int value = head.value;
        head = head.next;
        return value;
    }

    // Add value to the beginning of the list for demonstrating behaviour of stack
    public void push(int value) {
        Node oldHead = head;
        head = new Node();
        head.value = value;
        head.next = oldHead;
    }

    // nest class to define linkedlist node
    private class Node {
        int value;
        Node next;
    }
}