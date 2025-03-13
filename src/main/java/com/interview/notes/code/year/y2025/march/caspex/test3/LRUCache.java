package com.interview.notes.code.year.y2025.march.caspex.test3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class LRUCache {
    // Main class to handle the LRU cache implementation

    // HashMap for O(1) access to nodes by key
    private HashMap<Integer, Node> cache;
    // Capacity of the cache
    private int capacity;
    // Head and tail of doubly linked list for tracking usage order
    private Node head, tail;

    /**
     * Initialize the LRU cache with given capacity
     *
     * @param capacity Maximum number of items the cache can hold
     */
    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();

        // Initialize dummy head and tail nodes for easier list manipulation
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    /**
     * Solve the LRU cache problem with given operations
     *
     * @param capacity   Cache capacity
     * @param operations List of operations to perform
     * @return List of results from GET operations
     */
    public static List<Integer> solve(int capacity, List<String> operations) {
        LRUCache lruCache = new LRUCache(capacity);
        List<Integer> results = new ArrayList<>();

        for (String op : operations) {
            String[] parts = op.split(",");

            if (parts[0].equals("GET")) {
                int key = Integer.parseInt(parts[1]);
                results.add(lruCache.get(key));
            } else if (parts[0].equals("PUT")) {
                int key = Integer.parseInt(parts[1]);
                int value = Integer.parseInt(parts[2]);
                lruCache.put(key, value);
            }
        }

        return results;
    }

    /**
     * Main method to test the LRU cache implementation
     */
    public static void main(String[] args) {
        // Test case 1
        int capacity1 = 2;
        List<String> operations1 = Arrays.asList(
                "GET,2", "PUT,1,100", "PUT,2,125", "PUT,3,150", "GET,1", "GET,3"
        );
        List<Integer> expected1 = Arrays.asList(-1, -1, 150);
        List<Integer> result1 = solve(capacity1, operations1);
        System.out.println("Test 1: " + (result1.equals(expected1) ? "PASS" : "FAIL"));
        System.out.println("Expected: " + expected1);
        System.out.println("Got: " + result1);

        // Test case 2
        int capacity2 = 3;
        List<String> operations2 = Arrays.asList(
                "PUT,11,25", "PUT,22,50", "PUT,11,75", "GET,11", "GET,22"
        );
        List<Integer> expected2 = Arrays.asList(75, 50);
        List<Integer> result2 = solve(capacity2, operations2);
        System.out.println("Test 2: " + (result2.equals(expected2) ? "PASS" : "FAIL"));
        System.out.println("Expected: " + expected2);
        System.out.println("Got: " + result2);

        // Test case 3: Large data
        testLargeData();
    }

    /**
     * Test with large data to verify performance
     */
    private static void testLargeData() {
        int capacity = 1000;
        LRUCache lruCache = new LRUCache(capacity);

        // Insert 5000 items (exceeding capacity)
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            lruCache.put(i, i * 10);
        }

        // Verify only the most recent 1000 items are in cache
        int hits = 0;
        for (int i = 0; i < 5000; i++) {
            if (lruCache.get(i) != -1) {
                hits++;
            }
        }
        long endTime = System.currentTimeMillis();

        System.out.println("Large data test:");
        System.out.println("Items in cache: " + hits + " (should be " + capacity + ")");
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
        System.out.println("Large data test: " + (hits == capacity ? "PASS" : "FAIL"));
    }

    /**
     * Add a node right after the head (most recently used position)
     *
     * @param node Node to add
     */
    private void addNode(Node node) {
        // Insert node between head and head.next
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    /**
     * Remove a node from the doubly linked list
     *
     * @param node Node to remove
     */
    private void removeNode(Node node) {
        // Remove node by connecting its previous and next nodes
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    /**
     * Move a node to the front (most recently used position)
     *
     * @param node Node to move
     */
    private void moveToFront(Node node) {
        removeNode(node);
        addNode(node);
    }

    /**
     * Remove the least recently used node (the one before tail)
     *
     * @return The removed node
     */
    private Node removeLRU() {
        Node lru = tail.prev;
        removeNode(lru);
        return lru;
    }

    /**
     * Get the value for a key, or -1 if not found
     * Also marks the key as recently used
     *
     * @param key Key to look up
     * @return Value or -1 if not found
     */
    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1; // Key not found
        }

        // Move to front since it was accessed (recently used)
        moveToFront(node);
        return node.value;
    }

    /**
     * Add or update a key-value pair in the cache
     * If capacity is exceeded, removes least recently used item
     *
     * @param key   Key to add or update
     * @param value Value to store
     */
    public void put(int key, int value) {
        Node node = cache.get(key);

        if (node != null) {
            // Key exists, update value and move to front
            node.value = value;
            moveToFront(node);
        } else {
            // New key, create new node
            Node newNode = new Node(key, value);

            // If at capacity, remove least recently used
            if (cache.size() >= capacity) {
                Node lru = removeLRU();
                cache.remove(lru.key);
            }

            // Add new node to cache and to front of list
            cache.put(key, newNode);
            addNode(newNode);
        }
    }

    // Node class for our doubly linked list to track order of usage
    private class Node {
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}