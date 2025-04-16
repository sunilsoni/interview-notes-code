package com.interview.notes.code.year.y2025.april.common.test5;

import java.util.HashSet;
import java.util.Set;

class Node {
    char data;
    Node next;

    Node(char data) {
        this.data = data;
        this.next = null;
    }
}

public class LinkedListProcessor {

    public static Node processList(Node head) {
        head = removeLowerCaseNodes(head);
        removeLoop(head);
        return head;
    }

    private static Node removeLowerCaseNodes(Node head) {
        while (head != null && Character.isLowerCase(head.data)) {
            head = head.next;
        }

        Node current = head;
        while (current != null && current.next != null) {
            if (Character.isLowerCase(current.next.data)) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;
    }

    private static void removeLoop(Node head) {
        Set<Node> seenNodes = new HashSet<>();
        Node prev = null;
        Node current = head;

        while (current != null) {
            if (seenNodes.contains(current)) {
                prev.next = null;
                break;
            }
            seenNodes.add(current);
            prev = current;
            current = current.next;
        }
    }

    public static void printList(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + (current.next != null ? " -> " : ""));
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node('A');
        head.next = new Node('B');
        head.next.next = new Node('c');
        head.next.next.next = new Node('D');
        head.next.next.next.next = new Node('b');
        head.next.next.next.next.next = new Node('F');

        head.next.next.next.next.next.next = head.next.next;

        System.out.println("Output: A -> B -> D -> F");

        head = processList(head);

        System.out.print("Processed Output: ");
        printList(head);
    }
}