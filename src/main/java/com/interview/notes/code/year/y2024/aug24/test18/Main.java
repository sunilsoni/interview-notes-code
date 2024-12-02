package com.interview.notes.code.year.y2024.aug24.test18;

class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class CircularLinkedList {
    private Node head;

    public CircularLinkedList() {
        head = null;
    }

    // Method to add a node to the list
    public void add(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            head.next = head;
        } else {
            Node temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = newNode;
            newNode.next = head;
        }
    }

    // Method to find an element in the list
    public boolean find(int target) {
        if (head == null) {
            return false;
        }

        Node current = head;
        do {
            if (current.data == target) {
                return true;
            }
            current = current.next;
        } while (current != head);

        return false;
    }

    // Method to find an element and return its position (1-based index)
    public int findPosition(int target) {
        if (head == null) {
            return -1;
        }

        Node current = head;
        int position = 1;
        do {
            if (current.data == target) {
                return position;
            }
            current = current.next;
            position++;
        } while (current != head);

        return -1; // Element not found
    }
}

public class Main {
    public static void main(String[] args) {
        CircularLinkedList list = new CircularLinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        System.out.println("Is 3 in the list? " + list.find(3));
        System.out.println("Is 6 in the list? " + list.find(6));
        System.out.println("Position of 4: " + list.findPosition(4));
        System.out.println("Position of 7: " + list.findPosition(7));
    }
}
