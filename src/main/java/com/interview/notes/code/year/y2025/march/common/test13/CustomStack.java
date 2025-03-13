package com.interview.notes.code.year.y2025.march.common.test13;

import java.util.Arrays;

/**
 * A custom implementation of a Stack data structure
 */
public class CustomStack {
    private static final int DEFAULT_CAPACITY = 10;  // Default initial capacity
    private int[] elements;      // Array to store stack elements
    private int size;            // Current number of elements in the stack

    /**
     * Constructor creating stack with default capacity
     */
    public CustomStack() {
        elements = new int[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Test method to validate the CustomStack implementation
     */
    public static void main(String[] args) {
        // Test case 1: Basic operations
        System.out.println("Test Case 1: Basic operations");
        CustomStack stack = new CustomStack();
        System.out.println("Is empty: " + stack.isEmpty());  // Should be true

        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println("Stack after pushes: " + stack);  // Should be [10, 20, 30]
        System.out.println("Size: " + stack.size());  // Should be 3
        System.out.println("Peek: " + stack.peek());  // Should be 30

        System.out.println("Pop: " + stack.pop());  // Should be 30
        System.out.println("Stack after pop: " + stack);  // Should be [10, 20]
        System.out.println("Is empty: " + stack.isEmpty());  // Should be false

        // Test case 2: Edge case - empty stack operations
        System.out.println("\nTest Case 2: Empty stack operations");
        CustomStack emptyStack = new CustomStack();
        try {
            emptyStack.pop();
            System.out.println("FAIL: Should have thrown exception");
        } catch (IllegalStateException e) {
            System.out.println("PASS: " + e.getMessage());
        }

        try {
            emptyStack.peek();
            System.out.println("FAIL: Should have thrown exception");
        } catch (IllegalStateException e) {
            System.out.println("PASS: " + e.getMessage());
        }

        // Test case 3: Large data input
        System.out.println("\nTest Case 3: Large data input");
        CustomStack largeStack = new CustomStack();
        int largeSize = 1000;

        System.out.println("Pushing " + largeSize + " elements...");
        for (int i = 0; i < largeSize; i++) {
            largeStack.push(i);
        }

        System.out.println("Stack size: " + largeStack.size());  // Should be 1000
        System.out.println("Top element: " + largeStack.peek());  // Should be 999

        System.out.println("Popping " + largeSize + " elements...");
        boolean correctOrder = true;
        for (int i = largeSize - 1; i >= 0; i--) {
            int popped = largeStack.pop();
            if (popped != i) {
                correctOrder = false;
                System.out.println("FAIL: Expected " + i + " but got " + popped);
                break;
            }
        }

        System.out.println(correctOrder ? "PASS: All elements popped in correct order" : "FAIL: Wrong order");
        System.out.println("Is empty after popping all: " + largeStack.isEmpty());  // Should be true
    }

    /**
     * Pushes an element onto the top of the stack
     *
     * @param element The element to push
     */
    public void push(int element) {
        ensureCapacity();        // Make sure we have room
        elements[size++] = element;  // Add element and increment size
    }

    /**
     * Removes and returns the element at the top of the stack
     *
     * @return The element at the top of the stack
     * @throws IllegalStateException if the stack is empty
     */
    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return elements[--size];  // Decrement size and return element
    }

    /**
     * Returns the element at the top of the stack without removing it
     *
     * @return The element at the top of the stack
     * @throws IllegalStateException if the stack is empty
     */
    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return elements[size - 1];  // Return the top element without changing size
    }

    /**
     * Checks if the stack is empty
     *
     * @return true if the stack has no elements, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of elements in the stack
     *
     * @return The number of elements
     */
    public int size() {
        return size;
    }

    /**
     * Ensures the stack has room for more elements, doubling capacity if needed
     */
    private void ensureCapacity() {
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, elements.length * 2);  // Double the capacity
        }
    }

    /**
     * Clears all elements from the stack
     */
    public void clear() {
        size = 0;  // Simply reset size, no need to modify the array
    }

    /**
     * Returns a string representation of the stack
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
