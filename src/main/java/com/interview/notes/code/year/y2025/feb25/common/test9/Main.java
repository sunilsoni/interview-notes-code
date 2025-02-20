package com.interview.notes.code.year.y2025.feb25.common.test9;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class CustomQueue {
    private Node head;
    private Node tail;

    public CustomQueue() {
        head = null;
        tail = null;
    }

    public void enqueue(int value) {
        Node newNode = new Node(value);
        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    public Integer dequeue() {
        if (head == null) {
            return null;
        }
        int value = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        return value;
    }

    public Integer peek() {
        if (head == null) return null;
        return head.data;
    }

    public boolean isEmpty() {
        return head == null;
    }
}

public class Main {
    public static void main(String[] args) {
        testCase1();
        testCase2();
        testCase3();
        testCase4();
    }

    private static void testCase1() {
        CustomQueue q = new CustomQueue();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);

        boolean pass = true;
        pass &= q.dequeue() == 1;
        pass &= q.dequeue() == 2;
        pass &= q.dequeue() == 3;
        pass &= q.isEmpty();

        System.out.println("Test Case 1: " + (pass ? "PASS" : "FAIL"));
    }

    private static void testCase2() {
        CustomQueue q = new CustomQueue();
        boolean pass = q.dequeue() == null;
        System.out.println("Test Case 2: " + (pass ? "PASS" : "FAIL"));
    }

    private static void testCase3() {
        CustomQueue q = new CustomQueue();
        boolean pass = true;
        pass &= q.peek() == null;
        q.enqueue(5);
        pass &= q.peek() == 5;
        pass &= q.dequeue() == 5;
        pass &= q.isEmpty();

        System.out.println("Test Case 3: " + (pass ? "PASS" : "FAIL"));
    }

    private static void testCase4() {
        CustomQueue q = new CustomQueue();
        int size = 100000;
        for (int i = 0; i < size; i++) {
            q.enqueue(i);
        }

        boolean pass = true;
        for (int i = 0; i < size; i++) {
            Integer val = q.dequeue();
            if (val == null || val != i) {
                pass = false;
                break;
            }
        }
        pass &= q.isEmpty();

        System.out.println("Test Case 4: " + (pass ? "PASS" : "FAIL"));
    }
}