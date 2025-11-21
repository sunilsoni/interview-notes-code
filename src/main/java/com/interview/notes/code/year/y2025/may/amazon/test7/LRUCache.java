package com.interview.notes.code.year.y2025.may.amazon.test7;

import java.util.HashMap;

/**
 * LRU Cache implementation using HashMap and DoublyLinkedList
 * Provides O(1) time complexity for both get and put operations
 */
class LRUCache {
    // Main data structures
    private final HashMap<Integer, Node> cache; // Stores key->node mapping
    private final Node head;  // Most recently used
    private final Node tail;  // Least recently used
    private final int capacity; // Maximum size of cache
    private int size;  // Current size of cache

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>(capacity);
        this.size = 0;
        // Initialize dummy head and tail nodes
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test Case 1: Basic Operations
        System.out.println("Test Case 1: Basic Operations");
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1) == 1 ? "PASS" : "FAIL"); // returns 1
        cache.put(3, 3);
        System.out.println(cache.get(2) == -1 ? "PASS" : "FAIL"); // returns -1

        // Test Case 2: Update Existing Value
        System.out.println("\nTest Case 2: Update Existing Value");
        cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(1, 2);
        System.out.println(cache.get(1) == 2 ? "PASS" : "FAIL");

        // Test Case 3: Large Data Test
        System.out.println("\nTest Case 3: Large Data Test");
        cache = new LRUCache(1000);
        for (int i = 0; i < 2000; i++) {
            cache.put(i, i);
        }
        System.out.println(cache.get(500) == -1 ? "PASS" : "FAIL");
        System.out.println(cache.get(1999) == 1999 ? "PASS" : "FAIL");
    }

    // Helper method to add node right after head
    private void addNode(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    // Helper method to remove node from list
    private void removeNode(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;
    }

    // Move node to front (most recently used)
    private void moveToFront(Node node) {
        removeNode(node);
        addNode(node);
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) return -1;

        // Move to front as it was just accessed
        moveToFront(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);

        if (node == null) {
            // Create new node
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            addNode(newNode);
            size++;

            // Remove LRU if capacity exceeded
            if (size > capacity) {
                Node lru = tail.prev;
                removeNode(lru);
                cache.remove(lru.key);
                size--;
            }
        } else {
            // Update existing node
            node.value = value;
            moveToFront(node);
        }
    }

    // Inner class for DoublyLinkedList node
    class Node {
        int key;
        int value;
        Node prev;
        Node next;

        // Constructor to create new node
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
