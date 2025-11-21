package com.interview.notes.code.year.y2024.nov24.test16;

// Generic Array-Based Stack Implementation
public class ArrayStack<T> {
    private final int maxSize;    // Maximum size of the stack
    private int top;        // Index of the top element
    private final T[] stackArray;

    // Constructor to initialize the stack
    @SuppressWarnings("unchecked")
    public ArrayStack(int size) {
        this.maxSize = size;
        this.stackArray = (T[]) new Object[maxSize]; // Generic array creation
        this.top = -1; // Indicates that the stack is initially empty
    }

    // Example usage
    public static void main(String[] args) {
        // Stack of Integers
        ArrayStack<Integer> intStack = new ArrayStack<>(5);
        intStack.push(10);
        intStack.push(20);
        intStack.push(30);
        intStack.display();

        intStack.pop();
        intStack.display();

        System.out.println("Top element is: " + intStack.peek());

        intStack.push(40);
        intStack.push(50);
        intStack.push(60); // Should trigger stack overflow
        intStack.display();

        // Stack of Strings
        ArrayStack<String> stringStack = new ArrayStack<>(3);
        stringStack.push("Apple");
        stringStack.push("Banana");
        stringStack.push("Cherry");
        stringStack.display();

        stringStack.pop();
        stringStack.display();

        stringStack.push("Date");
        stringStack.push("Elderberry"); // Should trigger stack overflow
        stringStack.display();
    }

    // Push operation: Adds an element to the top of the stack
    public void push(T value) {
        if (isFull()) {
            System.out.println("Stack Overflow! Cannot push " + value);
            return;
        }
        stackArray[++top] = value;
        System.out.println("Pushed " + value + " to stack.");
    }

    // Pop operation: Removes and returns the top element of the stack
    public T pop() {
        if (isEmpty()) {
            System.out.println("Stack Underflow! Cannot pop.");
            return null; // Return null or throw exception in real scenarios
        }
        T poppedValue = stackArray[top--];
        System.out.println("Popped " + poppedValue + " from stack.");
        return poppedValue;
    }

    // Peek operation: Returns the top element without removing it
    public T peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return null;
        }
        return stackArray[top];
    }

    // Check if the stack is empty
    public boolean isEmpty() {
        return top == -1;
    }

    // Check if the stack is full
    public boolean isFull() {
        return top == maxSize - 1;
    }

    // Display the stack elements
    public void display() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return;
        }
        System.out.print("Stack elements: ");
        for (int i = 0; i <= top; i++) {
            System.out.print(stackArray[i] + " ");
        }
        System.out.println();
    }
}
