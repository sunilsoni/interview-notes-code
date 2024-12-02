package com.interview.notes.code.year.y2024.may24.test8;

class Stack {
    Node head;

    public void push(int new_data) {
        Node temp = new Node(new_data);
        temp.next = head;
        head = temp;
    }

    public int pop() {
        if (head == null) return -1;
        int value = head.data;
        head = head.next;
        return value;
    }
}