package com.interview.notes.code.amazon;

public class CustomQueue {

    private int length;
    private Node front, rear;

    public CustomQueue() {
        length = 0;
        front = rear = null;
    }

    public void enqueue(int data) {
        Node node = new Node(data);
        if (isEmpty()) {
            front = node;
        } else {
            rear.setNextNode(node);
        }
        rear = node;
        length++;
    }

    public int dequeue() throws Exception {
        if (isEmpty()) {
            throw new Exception("Empty queue");
        }
        int result = front.getData();
        front = front.getNextNode();
        length--;
        if (isEmpty()) {
            rear = null;
        }
        return result;
    }

    public int first() throws Exception {
        if (isEmpty()) {
            throw new Exception("Queue is empty");
        }
        return front.getData();
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public int size() {
        return length;
    }
}