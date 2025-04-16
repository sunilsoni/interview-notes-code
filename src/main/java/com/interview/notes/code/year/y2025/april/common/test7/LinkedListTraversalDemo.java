package com.interview.notes.code.year.y2025.april.common.test7;

class Node<T> {
    T data;
    Node<T> next;
    Node<T> prev;

    Node(T data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

class CustomLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    public void traverseForward() {
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.data + " → ");
            current = current.next;
        }
        System.out.println("null");
    }

    public void traverseBackward() {
        Node<T> current = tail;
        while (current != null) {
            System.out.print(current.data + " → ");
            current = current.prev;
        }
        System.out.println("null");
    }

    public int getSize() {
        return size;
    }
}

public class LinkedListTraversalDemo {
    public static void main(String[] args) {
        // Test Case 1: Empty list
        System.out.println("Test Case 1: Empty List");
        CustomLinkedList<Integer> emptyList = new CustomLinkedList<>();
        testList(emptyList);

        // Test Case 2: Single element
        System.out.println("\nTest Case 2: Single Element");
        CustomLinkedList<Integer> singleElementList = new CustomLinkedList<>();
        singleElementList.add(1);
        testList(singleElementList);

        // Test Case 3: Multiple elements
        System.out.println("\nTest Case 3: Multiple Elements");
        CustomLinkedList<Integer> multipleElementsList = new CustomLinkedList<>();
        for (int i = 1; i <= 5; i++) {
            multipleElementsList.add(i);
        }
        testList(multipleElementsList);

        // Test Case 4: Large dataset
        System.out.println("\nTest Case 4: Large Dataset");
        CustomLinkedList<Integer> largeList = new CustomLinkedList<>();
        for (int i = 1; i <= 1000; i++) {
            largeList.add(i);
        }
        System.out.println("Large list size: " + largeList.getSize());
        System.out.println("First few elements forward:");
        largeList.traverseForward();
    }

    private static void testList(CustomLinkedList<Integer> list) {
        System.out.println("List size: " + list.getSize());
        System.out.println("Forward traversal:");
        list.traverseForward();
        System.out.println("Backward traversal:");
        list.traverseBackward();
    }
}
