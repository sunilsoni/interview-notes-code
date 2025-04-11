package com.interview.notes.code.year.y2025.april.common.test4;

import java.util.*;

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
        // Remove lowercase nodes first
        head = removeLowerCaseNodes(head);
        // Remove circular references
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
        // Create the linked list: A -> B -> c -> D -> b -> F (with a loop back from F to c)
        Node head = new Node('A');
        head.next = new Node('B');
        head.next.next = new Node('c');
        head.next.next.next = new Node('D');
        head.next.next.next.next = new Node('b');
        head.next.next.next.next.next = new Node('F');

        // Creating loop manually (F -> c)
        head.next.next.next.next.next.next = head.next.next;

        System.out.print("Before Processing (loop present): ");
        // Don't print directly as loop exists, it'll print indefinitely
        System.out.println("A -> B -> c -> D -> b -> F -> c (loop)");

        head = processList(head);

        System.out.print("After Processing: ");
        printList(head); // Expected: A -> B -> D -> F

        // Additional edge cases
        System.out.println("\nEdge case tests:");

        Node singleNode = new Node('x');
        singleNode = processList(singleNode);
        System.out.print("Single lowercase node: ");
        printList(singleNode); // Expected: (empty)

        Node loopNode = new Node('G');
        loopNode.next = loopNode;
        loopNode = processList(loopNode);
        System.out.print("Single node loop: ");
        printList(loopNode); // Expected: G

        Node emptyNode = null;
        emptyNode = processList(emptyNode);
        System.out.print("Empty list: ");
        printList(emptyNode); // Expected: (empty)
    }
}
