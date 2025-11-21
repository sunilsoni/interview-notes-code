package com.interview.notes.code.year.y2024.nov24.test15;

public class ArrayStack {
    private final int maxSize;    // Maximum size of the stack
    private int top;        // Index of the top element
    private final int[] stackArray;

    // Constructor to initialize the stack
    public ArrayStack(int size) {
        this.maxSize = size;
        this.stackArray = new int[maxSize];
        this.top = -1; // Indicates that the stack is initially empty
    }

    // Example usage
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(5);

        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.display();

        stack.pop();
        stack.display();

        System.out.println("Top element is: " + stack.peek());

        stack.push(40);
        stack.push(50);
        stack.push(60); // Should trigger stack overflow
        stack.display();
    }

    // Push operation: Adds an element to the top of the stack
    public void push(int value) {
        if (isFull()) {
            System.out.println("Stack Overflow! Cannot push " + value);
            return;
        }
        stackArray[++top] = value;
        System.out.println("Pushed " + value + " to stack.");
    }

    // Pop operation: Removes and returns the top element of the stack
    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack Underflow! Cannot pop.");
            return -1; // Using -1 as a sentinel value; in real scenarios, consider throwing an exception
        }
        int poppedValue = stackArray[top--];
        System.out.println("Popped " + poppedValue + " from stack.");
        return poppedValue;
    }

    // Peek operation: Returns the top element without removing it
    public int peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return -1;
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
