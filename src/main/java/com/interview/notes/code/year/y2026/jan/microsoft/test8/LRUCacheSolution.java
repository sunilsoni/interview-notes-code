package com.interview.notes.code.year.y2026.jan.microsoft.test8;

import java.util.HashMap;
import java.util.Map;

// Main class containing the LRU Cache logic and the Tester
public class LRUCacheSolution {

    // --- 5. Testing (Simple Main Method) ---
    public static void main(String[] args) {
        System.out.println("Starting Tests...");
        long startTime = System.currentTimeMillis();

        // TEST CASE 1: The example from the image
        System.out.print("Test Case 1 (Standard): ");
        var cache = new LRUCache(2); // Capacity 2
        cache.put(1, 1); // cache: {1=1}
        cache.put(2, 2); // cache: {1=1, 2=2}

        check(cache.get(1) == 1, "Get(1) should be 1"); // 1 is now MRU
        cache.put(3, 3); // Evicts key 2 (LRU). Cache: {1=1, 3=3}

        check(cache.get(2) == -1, "Get(2) should be -1 (evicted)"); // Verify eviction
        cache.put(4, 4); // Evicts key 1 (LRU). Cache: {4=4, 3=3}

        check(cache.get(1) == -1, "Get(1) should be -1 (evicted)"); // Verify eviction
        check(cache.get(3) == 3, "Get(3) should be 3"); // 3 is now MRU
        check(cache.get(4) == 4, "Get(4) should be 4"); // 4 is now MRU
        System.out.println("PASS");

        // TEST CASE 2: Large Data Input (Stress Test)
        System.out.print("Test Case 2 (Large Data - 100k items): ");
        int largeCap = 100000;
        var largeCache = new LRUCache(largeCap);

        // Fill cache completely
        for (int i = 0; i < largeCap; i++) {
            largeCache.put(i, i);
        }

        // Verify all exist
        boolean passLarge = true;
        for (int i = 0; i < largeCap; i++) {
            if (largeCache.get(i) == -1) passLarge = false;
        }

        // Add one more to force eviction of key 0
        largeCache.put(largeCap, largeCap);

        if (largeCache.get(0) != -1) passLarge = false; // 0 should be gone
        if (largeCache.get(largeCap) != largeCap) passLarge = false; // New one should be there

        System.out.println(passLarge ? "PASS" : "FAIL");

        System.out.println("Total Time: " + (System.currentTimeMillis() - startTime) + "ms");
    }

    // Simple helper to print errors if check fails
    static void check(boolean condition, String msg) {
        if (!condition) {
            System.err.println("FAIL: " + msg);
            System.exit(1); // Stop execution on failure
        }
    }

    // Internal Node class for Doubly Linked List
    // We use a simple class to reduce boilerplate code
    static class Node {
        int key, val; // Store key and value
        Node prev, next; // Pointers to previous and next nodes

        Node(int k, int v) {
            key = k;
            val = v;
        } // Constructor to set data
    }

    static class LRUCache {
        private final int capacity; // Maximum items cache can hold
        private final Map<Integer, Node> map; // Map for O(1) node retrieval
        private final Node head, tail; // Dummy head and tail to simplify edge cases

        // Initialize the LRU Cache
        public LRUCache(int capacity) {
            this.capacity = capacity; // Set capacity
            this.map = new HashMap<>(); // Init HashMap

            // Create dummy nodes to avoid null checks
            this.head = new Node(0, 0); // Head dummy
            this.tail = new Node(0, 0); // Tail dummy

            // Connect head and tail initially
            head.next = tail; // Head points to tail
            tail.prev = head; // Tail points back to head
        }

        // Helper: Add a node right after the head (Most Recently Used position)
        private void addToHead(Node node) {
            node.prev = head; // New node points back to head
            node.next = head.next; // New node points forward to what was after head
            head.next.prev = node; // The old first node now points back to new node
            head.next = node; // Head now points to the new node
        }

        // Helper: Remove an existing node from the list
        private void removeNode(Node node) {
            node.prev.next = node.next; // Skip over the node (prev points to next)
            node.next.prev = node.prev; // Skip over the node (next points to prev)
        }

        // Method to get value by key
        public int get(int key) {
            if (!map.containsKey(key)) return -1; // If key not found, return -1

            var node = map.get(key); // Get the node using Java 21 'var'
            removeNode(node); // Remove from current position
            addToHead(node); // Move to head (mark as most recently used)

            return node.val; // Return the value
        }

        // Method to put/update a key-value pair
        public void put(int key, int value) {
            if (map.containsKey(key)) { // If key already exists
                var node = map.get(key); // Get existing node
                node.val = value; // Update the value
                removeNode(node); // Remove from old position
                addToHead(node); // Move to head (Most Recently Used)
            } else { // If key is new
                if (map.size() == capacity) { // Check if we are full
                    var lru = tail.prev; // Identify the Least Recently Used (item before tail)
                    map.remove(lru.key); // Remove from map
                    removeNode(lru); // Remove from list
                }
                var newNode = new Node(key, value); // Create new node
                map.put(key, newNode); // Add to map
                addToHead(newNode); // Add to head (Most Recently Used)
            }
        }
    }
}