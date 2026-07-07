package com.interview.notes.code.year.y2026.june.common.test9;

import java.util.HashMap; // Imports HashMap for instant key-value lookups
import java.util.Map; // Imports Map interface for the cache variable
import java.util.stream.IntStream; // Imports IntStream for large data testing

public class LRUCache { // Defines the main cache class

    private final int capacity; // Defines the maximum allowed items in the cache
    private final Map<Integer, Node> cache = new HashMap<>(); // Maps keys directly to their memory nodes
    private final Node head = new Node(); // Dummy head node to guarantee 'prev' is never null
    private final Node tail = new Node(); // Dummy tail node to guarantee 'next' is never null
    public LRUCache(int capacity) { // Constructor initialized with desired size
        this.capacity = capacity; // Assigns the capacity limit
        head.next = tail; // Links dummy head directly to tail initially
        tail.prev = head; // Links dummy tail back to head initially
    } // Ends the constructor

    // --- 4. Testing & Main Method ---
    public static void main(String[] args) {
        System.out.println("Starting LRU Cache Tests...\n");

        // Test 1: Basic Put and Get
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        assertTest("Test 1 (Get 1)", cache.get(1) == 1);

        // Test 2: Eviction Logic
        cache.put(3, 3); // This should evict key 2
        assertTest("Test 2 (Evict 2)", cache.get(2) == -1);

        // Test 3: Updating existing key
        cache.put(1, 10);
        assertTest("Test 3 (Update 1)", cache.get(1) == 10);

        // Test 4: Large Data Stream (Java 8 Streams)
        System.out.println("\nRunning Large Data Test (Capacity 5000, Input 15000)...");
        LRUCache largeCache = new LRUCache(5000);

        // Insert 15,000 items rapidly
        IntStream.range(0, 15000).forEach(i -> largeCache.put(i, i));

        // The oldest 10,000 should be gone (0 to 9999)
        assertTest("Large Data (Oldest Evicted)", largeCache.get(5000) == -1);

        // The newest 5,000 should still be there (10000 to 14999)
        assertTest("Large Data (Newest Retained)", largeCache.get(14999) == 14999);
        assertTest("Large Data (Mid Retained)", largeCache.get(12500) == 12500);
    }

    private static void assertTest(String testName, boolean condition) {
        if (condition) {
            System.out.println("[PASS] " + testName);
        } else {
            System.err.println("[FAIL] " + testName);
        }
    }

    public int get(int key) { // Retrieves a value and updates its usage status
        if (!cache.containsKey(key)) return -1; // Returns -1 immediately if key is missing
        Node node = cache.get(key); // Fetches the actual node from the map
        remove(node); // Detaches the node from its current list position
        insert(node); // Re-inserts the node at the front to mark it as most recently used
        return node.val; // Returns the requested integer value
    } // Ends get method

    public void put(int key, int value) { // Adds or updates a value in the cache
        if (cache.containsKey(key)) { // Checks if the node already exists
            remove(cache.get(key)); // Removes the old node to prepare for update
        } // Ends if block

        if (cache.size() == capacity) { // Checks if cache has reached its maximum size
            cache.remove(tail.prev.key); // Removes the oldest key from the map
            remove(tail.prev); // Detaches the oldest node from the list right before the tail
        } // Ends capacity check block

        Node newNode = new Node(); // Creates the new node to insert
        newNode.key = key; // Assigns the key to the new node
        newNode.val = value; // Assigns the data value to the new node
        cache.put(key, newNode); // Stores the new node reference in the map
        insert(newNode); // Places the new node at the front of the list
    } // Ends put method

    private void remove(Node node) { // Helper to cleanly detach a node from the list
        node.prev.next = node.next; // Bypasses the node from the left side
        node.next.prev = node.prev; // Bypasses the node from the right side
    } // Ends remove helper

    private void insert(Node node) { // Helper to place a node right after the head
        node.next = head.next; // Points new node forward to the previous first element
        node.next.prev = node; // Points the previous first element backward to new node
        head.next = node; // Points dummy head forward to the new node
        node.prev = head; // Points new node backward to the dummy head
    } // Ends insert helper

    class Node { // Inner class representing a Doubly Linked List node
        int key; // Stores the unique key for map tracking
        int val; // Stores the actual data value
        Node prev; // Pointer to the preceding node in the list
        Node next; // Pointer to the succeeding node in the list
    } // Ends the Node structure
}