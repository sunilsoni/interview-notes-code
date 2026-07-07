package com.interview.notes.code.year.y2026.june.common.test4;

public class CustomLinkedList {

    private Node head;

    public static void main(String[] args) {
        CustomLinkedList list = new CustomLinkedList();

        list.add(10);
        list.add(20);
        list.add(30);

        list.printList();
    }

    public void add(int data) {
        Node newNode = new Node(data);

        if (head == null) {
            head = newNode;
            return;
        }

        Node current = head;

        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;
    }

    public void printList() {
        Node current = head;

        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }

        System.out.println("null");
    }

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }
}