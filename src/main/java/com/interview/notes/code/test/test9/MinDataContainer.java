package com.interview.notes.code.test.test9;

import java.util.Stack;

public class MinDataContainer<T extends Comparable<T>> {
    private Stack<T> stack;
    private Stack<T> minStack;

    public MinDataContainer() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public static void main(String[] args) {
        MinDataContainer<Integer> container = new MinDataContainer<>();
        container.storeData(10);
        container.storeData(20);
        container.storeData(5);
        container.storeData(5);
        container.storeData(15);

        System.out.println("Minimum element so far: " + container.getMin());

        while (!container.isEmpty()) {
            int data = container.readData();
            System.out.println("Data: " + data);
            container.removeData();
        }
    }

    public void storeData(T data) {
        stack.push(data);
        if (minStack.isEmpty() || data.compareTo(minStack.peek()) <= 0) {
            minStack.push(data);
        }
    }

    public T readData() {
        if (stack.isEmpty()) {
            throw new IllegalStateException("No data available.");
        }
        return stack.peek();
    }

    public T removeData() {
        if (stack.isEmpty()) {
            throw new IllegalStateException("No data available.");
        }
        T removedData = stack.pop();
        if (removedData.compareTo(minStack.peek()) == 0) {
            minStack.pop();
        }
        return removedData;
    }

    public T getMin() {
        if (minStack.isEmpty()) {
            throw new IllegalStateException("No data available.");
        }
        return minStack.peek();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}
