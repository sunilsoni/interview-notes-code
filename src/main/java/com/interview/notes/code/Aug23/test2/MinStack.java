package com.interview.notes.code.Aug23.test2;

import java.util.Stack;

/*
Java Stack implement
MinPeek: Returns the smallest element currently in the stack, without removing it

 */
class MinStack {
    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();

        minStack.push(5);
        minStack.push(2);
        minStack.push(8);
        minStack.push(1);

        System.out.println("Top Element: " + minStack.top()); // Output: 1
        System.out.println("Minimum Element: " + minStack.minPeek()); // Output: 1

        minStack.pop();
        System.out.println("Minimum Element after popping: " + minStack.minPeek()); // Output: 2

        minStack.push(0);
        System.out.println("Minimum Element after pushing 0: " + minStack.minPeek()); // Output: 0
    }

    // Pushes element x onto the stack
    public void push(int x) {
        stack.push(x); // Push to the main stack
        // If minStack is empty or x <= top of minStack, push to minStack
        if (minStack.isEmpty() || x <= minStack.peek()) {
            minStack.push(x);
        }
    }

    // Removes the element on the top of the stack
    public void pop() {
        // If the popped element is equal to the top of minStack, pop from minStack too
        if (stack.peek().equals(minStack.peek())) {
            minStack.pop();
        }
        stack.pop(); // Pop from the main stack
    }

    // Gets the top element
    public int top() {
        return stack.peek();
    }

    // Returns the minimum element in the stack without removing it
    public int minPeek() {
        return minStack.peek(); // Top of minStack is the minimum element
    }

}
