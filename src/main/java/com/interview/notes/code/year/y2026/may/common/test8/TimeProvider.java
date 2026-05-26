package com.interview.notes.code.year.y2026.may.common.test8;

import java.util.Comparator; // Required to tell our PriorityQueue how to sort the items
import java.util.HashMap; // Required for our fast O(1) key-value lookups
import java.util.Map; // Required for the Map interface type definition
import java.util.PriorityQueue; // Required for our O(log N) Min-Heap to track the lowest TTL

interface TimeProvider { // Interface to allow us to mock time for reliable testing
    long getMillis(); // Method that will return the current time in milliseconds
} // End of interface

class TtlCache { // Main cache class definition
    private final int maxSize; // Stores the maximum allowed capacity of our cache
    private final TimeProvider time; // Stores the reference to our time provider
    private final Map<String, Node> map = new HashMap<>(); // HashMap to store keys and Node references for O(1) lookups
    private final PriorityQueue<Node> pq; // PriorityQueue to keep Nodes sorted by their expiration time

    TtlCache(TimeProvider time, int maxSize) { // Constructor to setup our cache state
        this.time = time; // Save the provided time mechanism
        this.maxSize = maxSize; // Save the requested maximum size
        // Initialize the PriorityQueue, telling it to strictly sort Nodes by comparing their 'exp' (expiration) values
        this.pq = new PriorityQueue<>(Comparator.comparingLong(Node::exp)); // End of queue initialization
    } // End of constructor

    private void clean() { // Helper method to purge dead items before any operation
        long now = time.getMillis(); // Fetch the exact current time once
        // Keep looping as long as the queue has items AND the top item's expiration time is in the past
        while (!pq.isEmpty() && pq.peek().exp() <= now) { // Peek looks at the top without removing it
            Node expired = pq.poll(); // Poll actually removes the expired item from the top of the queue
            map.remove(expired.key()); // Remove that same item's key from the HashMap so it is completely gone
        } // End of while loop
    } // End of clean method

    public void put(String k, String v, long ttl) { // Method to add or update items in the cache
        clean(); // First, clear out expired items so we know our true current size
        long exp = time.getMillis() + ttl; // Calculate the absolute future timestamp when this item will die
        Node newNode = new Node(k, v, exp); // Create the new immutable Node containing our data

        if (map.containsKey(k)) { // If this key already exists, this is an UPDATE operation
            Node oldNode = map.get(k); // Fetch the old, existing node from the map
            pq.remove(oldNode); // Remove the old node from the queue (Note: this is O(N) in Java, lazy-deletion is used in enterprise apps)
        } else if (map.size() >= maxSize) { // Otherwise, if it's a NEW item but we are at full capacity
            Node minNode = pq.peek(); // Look at the item expiring soonest (sitting at the top of the queue)
            if (exp > minNode.exp()) { // Check if our NEW item will live longer than the SOONEST expiring item
                pq.poll(); // If yes, evict the soonest expiring item from the queue
                map.remove(minNode.key()); // And also evict it from the HashMap to free up the space
            } else { // If our new item expires even faster than the soonest expiring item in the cache...
                return; // ...we simply reject it and exit the method immediately as per requirements
            } // End of inner if-else
        } // End of outer if-else

        map.put(k, newNode); // Insert the fresh node into the HashMap for fast lookups
        pq.offer(newNode); // Insert the same fresh node into the PriorityQueue to track its lifespan
    } // End of put method

    public String get(String k) { // Method to retrieve a value by its key
        clean(); // Always clear expired items first to prevent returning a "ghost" value
        var node = map.get(k); // Attempt to fetch the node from the HashMap using var (Java 10+ feature)
        return node == null ? null : node.val(); // Return null if not found, otherwise unwrap and return the string value
    } // End of get method

    public int size() { // Method to check how many active items exist
        clean(); // Purge dead items so we don't accidentally count them
        return map.size(); // Return the true size of the underlying HashMap
    } // End of size method

    // Java 21 Record: A perfectly immutable data carrier holding the key, value, and absolute expiration time
    private record Node(String key, String val, long exp) {} // End of record definition
} // End of TtlCache class