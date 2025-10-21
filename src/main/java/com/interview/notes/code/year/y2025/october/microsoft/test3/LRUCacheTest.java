package com.interview.notes.code.year.y2025.october.microsoft.test3;

import java.util.HashMap;

public class LRUCacheTest {

    // Main class to test LRU Cache implementation
    public static void main(String[] args) {
        // Test Case 1: Basic Operations
        System.out.println("Test Case 1: Basic Operations");
        testBasicOperations();

        // Test Case 2: Capacity Constraints
        System.out.println("\nTest Case 2: Capacity Constraints");
        testCapacityConstraints();

        // Test Case 3: Large Data Input
        System.out.println("\nTest Case 3: Large Data Input");
        testLargeDataInput();

        // Test Case 4: Edge Cases
        System.out.println("\nTest Case 4: Edge Cases");
        testEdgeCases();
    }

    // Test basic operations of LRU Cache
    private static void testBasicOperations() {
        LRUCache cache = new LRUCache(2);

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println("Test 1: " + (cache.get(1) == 1 ? "PASS" : "FAIL")); // Should return 1

        cache.put(3, 3); // evicts key 2
        System.out.println("Test 2: " + (cache.get(2) == -1 ? "PASS" : "FAIL")); // Should return -1
    }

    // Test capacity constraints
    private static void testCapacityConstraints() {
        LRUCache cache = new LRUCache(3);

        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.put(4, 4); // should evict 1

        System.out.println("Test 1: " + (cache.get(1) == -1 ? "PASS" : "FAIL")); // Should return -1
        System.out.println("Test 2: " + (cache.get(4) == 4 ? "PASS" : "FAIL")); // Should return 4
    }

    // Test large data input
    private static void testLargeDataInput() {
        LRUCache cache = new LRUCache(1000);

        // Insert 1000 elements
        for (int i = 0; i < 1000; i++) {
            cache.put(i, i);
        }

        // Verify some random accesses
        System.out.println("Test 1: " + (cache.get(999) == 999 ? "PASS" : "FAIL"));
        System.out.println("Test 2: " + (cache.get(500) == 500 ? "PASS" : "FAIL"));
    }

    // Test edge cases
    private static void testEdgeCases() {
        LRUCache cache = new LRUCache(1);

        cache.put(1, 1);
        cache.put(2, 2); // should evict 1
        System.out.println("Test 1: " + (cache.get(1) == -1 ? "PASS" : "FAIL")); // Should return -1

        // Test with negative keys
        cache.put(-1, -1);
        System.out.println("Test 2: " + (cache.get(-1) == -1 ? "PASS" : "FAIL")); // Should return -1
    }

    // Class implementing LRU Cache using HashMap and DoublyLinkedList
    static class LRUCache {
        // Maximum capacity of cache
        private final int capacity;
        // HashMap to store key-node mapping for O(1) access
        private final HashMap<Integer, Node> cache;
        // DoublyLinkedList to maintain order of elements
        private final DoublyLinkedList dll;

        // Constructor to initialize cache with given capacity
        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.cache = new HashMap<>();
            this.dll = new DoublyLinkedList();
        }

        // Get value for given key, returns -1 if not found
        public int get(int key) {
            if (!cache.containsKey(key)) {
                return -1;
            }
            // Move accessed node to front (most recently used)
            Node node = cache.get(key);
            dll.moveToFront(node);
            return node.value;
        }

        // Put key-value pair in cache
        public void put(int key, int value) {
            if (cache.containsKey(key)) {
                // Update existing key's value and move to front
                Node node = cache.get(key);
                node.value = value;
                dll.moveToFront(node);
            } else {
                // Create new node
                Node newNode = new Node(key, value);
                // If cache is full, remove least recently used item
                if (cache.size() >= capacity) {
                    Node lru = dll.removeLast();
                    cache.remove(lru.key);
                }
                // Add new node
                cache.put(key, newNode);
                dll.addToFront(newNode);
            }
        }
    }

    // Helper class for DoublyLinkedList nodes
    static class Node {
        int key, value;
        Node prev, next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    // Helper class to manage doubly linked list operations
    static class DoublyLinkedList {
        Node head, tail;

        void addToFront(Node node) {
            if (head == null) {
                head = tail = node;
            } else {
                node.next = head;
                head.prev = node;
                head = node;
            }
        }

        void moveToFront(Node node) {
            if (node == head) return;

            if (node == tail) {
                tail = tail.prev;
                tail.next = null;
            } else {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }

            node.prev = null;
            node.next = head;
            head.prev = node;
            head = node;
        }

        Node removeLast() {
            Node last = tail;
            tail = tail.prev;
            if (tail != null) {
                tail.next = null;
            } else {
                head = null;
            }
            return last;
        }
    }
}
