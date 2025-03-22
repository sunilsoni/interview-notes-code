package com.interview.notes.code.year.y2025.march.common;

public class CustomQueue<T> {
    private Node<T> front;
    private Node<T> rear;
    private int size;
    
    private static class Node<T> {
        T data;
        Node<T> next;
        
        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }
    
    public CustomQueue() {
        front = null;
        rear = null;
        size = 0;
    }
    
    public void enqueue(T item) {
        Node<T> newNode = new Node<>(item);
        if (isEmpty()) {
            front = newNode;
        } else {
            rear.next = newNode;
        }
        rear = newNode;
        size++;
    }
    
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        T item = front.data;
        front = front.next;
        size--;
        if (isEmpty()) {
            rear = null;
        }
        return item;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public int size() {
        return size;
    }
    
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return front.data;
    }
}

// Test class
  class QueueTest {
    public static void main(String[] args) {
        testBasicOperations();
        testEdgeCases();
        testLargeData();
    }
    
    private static void testBasicOperations() {
        System.out.println("Testing basic operations...");
        CustomQueue<Integer> queue = new CustomQueue<>();
        
        // Test enqueue and size
        queue.enqueue(1);
        assert queue.size() == 1 : "Test Failed: Size should be 1";
        
        queue.enqueue(2);
        assert queue.size() == 2 : "Test Failed: Size should be 2";
        
        // Test peek
        assert queue.peek() == 1 : "Test Failed: Peek should return 1";
        
        // Test dequeue
        assert queue.dequeue() == 1 : "Test Failed: Dequeue should return 1";
        assert queue.dequeue() == 2 : "Test Failed: Dequeue should return 2";
        assert queue.isEmpty() : "Test Failed: Queue should be empty";
        
        System.out.println("Basic operations tests PASSED");
    }
    
    private static void testEdgeCases() {
        System.out.println("Testing edge cases...");
        CustomQueue<String> queue = new CustomQueue<>();
        
        // Test empty queue operations
        try {
            queue.dequeue();
            assert false : "Test Failed: Should throw exception on empty dequeue";
        } catch (IllegalStateException e) {
            // Expected
        }
        
        try {
            queue.peek();
            assert false : "Test Failed: Should throw exception on empty peek";
        } catch (IllegalStateException e) {
            // Expected
        }
        
        // Test null values
        queue.enqueue(null);
        assert queue.peek() == null : "Test Failed: Should allow null values";
        
        System.out.println("Edge cases tests PASSED");
    }
    
    private static void testLargeData() {
        System.out.println("Testing large data...");
        CustomQueue<Integer> queue = new CustomQueue<>();
        
        // Test with 100,000 elements
        int testSize = 100_000;
        for (int i = 0; i < testSize; i++) {
            queue.enqueue(i);
        }
        
        assert queue.size() == testSize : "Test Failed: Size mismatch for large data";
        
        // Verify values
        for (int i = 0; i < testSize; i++) {
            assert queue.dequeue() == i : "Test Failed: Value mismatch in large data";
        }
        
        System.out.println("Large data tests PASSED");
    }
}
