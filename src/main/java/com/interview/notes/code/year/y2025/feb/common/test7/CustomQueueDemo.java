package com.interview.notes.code.year.y2025.feb.common.test7;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class CustomQueueDemo {

    // Simple test method to evaluate the custom queue
    public static void main(String[] args) {
        boolean allTestsPassed = true;

        // Test 1: Basic operations
        CustomQueue<Integer> queue = new CustomQueue<>();
        try {
            queue.enqueue(10);
            queue.enqueue(20);
            queue.enqueue(30);

            if (queue.size() != 3) {
                System.out.println("Test 1 Failed: Size mismatch.");
                allTestsPassed = false;
            }

            if (queue.peek() != 10) {
                System.out.println("Test 1 Failed: Peek does not return first element.");
                allTestsPassed = false;
            }

            if (queue.dequeue() != 10) {
                System.out.println("Test 1 Failed: Dequeue did not return the first element.");
                allTestsPassed = false;
            }

            if (queue.size() != 2) {
                System.out.println("Test 1 Failed: Size mismatch after dequeue.");
                allTestsPassed = false;
            }
        } catch (Exception e) {
            System.out.println("Test 1 Failed with Exception: " + e.getMessage());
            allTestsPassed = false;
        }

        // Test 2: Exception on empty queue
        try {
            CustomQueue<String> emptyQueue = new CustomQueue<>();
            emptyQueue.dequeue();
            System.out.println("Test 2 Failed: Exception not thrown on dequeue from empty queue.");
            allTestsPassed = false;
        } catch (NoSuchElementException e) {
            // Expected exception
        } catch (Exception e) {
            System.out.println("Test 2 Failed with unexpected Exception: " + e.getMessage());
            allTestsPassed = false;
        }

        // Test 3: Large Data Input
        try {
            CustomQueue<Integer> largeQueue = new CustomQueue<>();
            final int largeSize = 100000; // large data set
            for (int i = 0; i < largeSize; i++) {
                largeQueue.enqueue(i);
            }
            if (largeQueue.size() != largeSize) {
                System.out.println("Test 3 Failed: Size mismatch for large input.");
                allTestsPassed = false;
            }

            // Using Java 8 Streams: sum of all elements
            int sum = largeQueue.toList().stream().mapToInt(Integer::intValue).sum();
            // The expected sum is (n-1)*n/2
            int expectedSum = (largeSize - 1) * largeSize / 2;
            if (sum != expectedSum) {
                System.out.println("Test 3 Failed: Sum of elements mismatch for large input.");
                allTestsPassed = false;
            }
        } catch (Exception e) {
            System.out.println("Test 3 Failed with Exception: " + e.getMessage());
            allTestsPassed = false;
        }

        // Test 4: Ensure FIFO order
        try {
            CustomQueue<String> fifoQueue = new CustomQueue<>();
            String[] testData = {"A", "B", "C", "D"};
            for (String s : testData) {
                fifoQueue.enqueue(s);
            }
            List<String> result = new ArrayList<>();
            while (!fifoQueue.isEmpty()) {
                result.add(fifoQueue.dequeue());
            }
            // Use stream to join the output
            String resultString = result.stream().collect(Collectors.joining(","));
            if (!"A,B,C,D".equals(resultString)) {
                System.out.println("Test 4 Failed: FIFO order not maintained. Got: " + resultString);
                allTestsPassed = false;
            }
        } catch (Exception e) {
            System.out.println("Test 4 Failed with Exception: " + e.getMessage());
            allTestsPassed = false;
        }

        // Output overall test results
        if (allTestsPassed) {
            System.out.println("All tests PASS.");
        } else {
            System.out.println("Some tests FAILED.");
        }
    }

    // Custom generic queue class
    public static class CustomQueue<T> {
        private Node<T> head;
        private Node<T> tail;
        private int size;

        // Add element to the queue
        public void enqueue(T element) {
            if (element == null) {
                throw new IllegalArgumentException("Null values are not allowed");
            }
            Node<T> newNode = new Node<>(element);
            if (tail == null) { // Queue is empty
                head = tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
            size++;
        }

        // Remove and return the element from the front
        public T dequeue() {
            if (isEmpty()) {
                throw new NoSuchElementException("Queue is empty");
            }
            T data = head.data;
            head = head.next;
            if (head == null) { // Queue became empty after removal
                tail = null;
            }
            size--;
            return data;
        }

        // Return the element at the front without removing it
        public T peek() {
            if (isEmpty()) {
                throw new NoSuchElementException("Queue is empty");
            }
            return head.data;
        }

        // Check if the queue is empty
        public boolean isEmpty() {
            return size == 0;
        }

        // Return the number of elements in the queue
        public int size() {
            return size;
        }

        // Convert the queue elements to a List (to use with streams)
        public List<T> toList() {
            List<T> list = new ArrayList<>();
            Node<T> current = head;
            while (current != null) {
                list.add(current.data);
                current = current.next;
            }
            return list;
        }

        // Node class to hold data and next pointer
        private static class Node<T> {
            T data;
            Node<T> next;

            Node(T data) {
                this.data = data;
            }
        }
    }
}