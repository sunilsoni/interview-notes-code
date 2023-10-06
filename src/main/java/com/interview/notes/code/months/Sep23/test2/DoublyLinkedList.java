package com.interview.notes.code.months.Sep23.test2;

public class DoublyLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;

    public static void main(String[] args) {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.insert(4);

        System.out.println("List after insertion:");
        list.print();

        list.delete(2);
        System.out.println("\nList after deleting 2:");
        list.print();

        list.delete(4);
        System.out.println("\nList after deleting 4:");
        list.print();
    }

    // Insert at the end
    public void insert(T data) {
        Node<T> newNode = new Node<>(data);

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    // Delete a node with specific data
    public void delete(T data) {
        if (head == null) return;

        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(data)) {
                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    head = current.next;
                }

                if (current.next != null) {
                    current.next.prev = current.prev;
                } else {
                    tail = current.prev;
                }

                return; // Node found and deleted
            }
            current = current.next;
        }
    }

    // Print from start to end
    public void print() {
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.data + " <-> ");
            current = current.next;
        }
        System.out.println("null");
    }

    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        Node(T data) {
            this.data = data;
            next = null;
            prev = null;
        }
    }
}
