package com.interview.notes.code.year.y2025.march.apple.test1;

import java.util.ArrayList;

class CustomStack<T> {
    private final ArrayList<T> stack;
    private final int maxSize;
    private int top; // keeps track of the top element

    // Constructor
    public CustomStack(int size) {
        stack = new ArrayList<>();
        top = -1; // initially stack is empty
        maxSize = size;
    }

    // Push operation
    public void push(T element) {
        if (isFull()) {
            System.out.println("Stack Overflow! Cannot push element: " + element);
            return;
        }
        top++;
        stack.add(element);
        System.out.println("Pushed element: " + element);
    }

    // Pop operation
    public T pop() {
        if (isEmpty()) {
            System.out.println("Stack Underflow! Cannot pop from empty stack");
            return null;
        }
        T element = stack.remove(top);
        top--;
        return element;
    }

    // Peek operation (view top element without removing)
    public T peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return null;
        }
        return stack.get(top);
    }

    // Check if stack is empty
    public boolean isEmpty() {
        return top == -1;
    }

    // Check if stack is full
    public boolean isFull() {
        return top == maxSize - 1;
    }

    // Get size of stack
    public int size() {
        return top + 1;
    }
}

public class StackDemo {
    public static void main(String[] args) {
        // Create a stack of size 5
        CustomStack<Integer> stack = new CustomStack<>(5);

        // Demonstrate stack operations
        System.out.println("Is stack empty? " + stack.isEmpty());

        // Push elements
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);
        stack.push(50);

        // Try to push when stack is full
        stack.push(60); // Should show overflow message

        System.out.println("Stack size: " + stack.size());
        System.out.println("Top element: " + stack.peek());

        // Pop elements
        System.out.println("Popped: " + stack.pop());
        System.out.println("Popped: " + stack.pop());
        System.out.println("Current top element: " + stack.peek());

        // Pop remaining elements
        System.out.println("Popped: " + stack.pop());
        System.out.println("Popped: " + stack.pop());
        System.out.println("Popped: " + stack.pop());

        // Try to pop when stack is empty
        System.out.println("Popped: " + stack.pop()); // Should show underflow message
    }
}
