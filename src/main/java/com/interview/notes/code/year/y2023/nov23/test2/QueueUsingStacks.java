package com.interview.notes.code.year.y2023.nov23.test2;

import java.util.Stack;

public class QueueUsingStacks<T> {
    private final Stack<T> stackNewestOnTop = new Stack<>(); // Stack to push new elements
    private final Stack<T> stackOldestOnTop = new Stack<>(); // Stack to pop elements from the queue

    public static void main(String[] args) {
        QueueUsingStacks<Integer> queue = new QueueUsingStacks<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        System.out.println(queue.dequeue()); // Should print 1
        System.out.println(queue.peek());    // Should print 2
        System.out.println(queue.dequeue()); // Should print 2
        queue.enqueue(4);
        System.out.println(queue.dequeue()); // Should print 3
        System.out.println(queue.dequeue()); // Should print 4
    }

    // Add item to the queue
    public void enqueue(T value) {
        stackNewestOnTop.push(value);
    }

    // Move elements from stackNewestOnTop to stackOldestOnTop
    private void shiftStacks() {
        if (stackOldestOnTop.isEmpty()) {
            while (!stackNewestOnTop.isEmpty()) {
                stackOldestOnTop.push(stackNewestOnTop.pop());
            }
        }
    }

    // Remove the front item from the queue
    public T dequeue() {
        shiftStacks(); // Ensure stackOldestOnTop has the current elements
        return stackOldestOnTop.isEmpty() ? null : stackOldestOnTop.pop();
    }

    // Get the front item of the queue
    public T peek() {
        shiftStacks(); // Ensure stackOldestOnTop has the current elements
        return stackOldestOnTop.isEmpty() ? null : stackOldestOnTop.peek();
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return stackNewestOnTop.isEmpty() && stackOldestOnTop.isEmpty();
    }
}
