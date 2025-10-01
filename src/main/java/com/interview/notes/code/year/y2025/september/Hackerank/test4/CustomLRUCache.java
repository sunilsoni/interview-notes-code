package com.interview.notes.code.year.y2025.september.Hackerank.test4;

import java.util.*;

public class CustomLRUCache {
    private final int capacity;                 // maximum size of cache
    private final Deque<Integer> cache;               // maintains order (front = most recent, rear = least recent)
    private final Set<Integer> lookup;                // helps check if number exists in O(1)

    // Constructor - initialize cache with given capacity
    public CustomLRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedList<>();
        this.lookup = new HashSet<>();
    }

    // Testing
    public static void main(String[] args) {
        CustomLRUCache lru = new CustomLRUCache(3);

        System.out.println("Cache capacity = 3\n");

        lru.put(1);
        lru.display();   // [1]

        lru.put(2);
        lru.display();   // [2, 1]

        lru.put(3);
        lru.display();   // [3, 2, 1]

        lru.put(4);      // cache full → remove LRU (1)
        lru.display();   // [4, 3, 2]

        lru.put(2);      // already exists → move to front
        lru.display();   // [2, 4, 3]

        lru.put(5);      // cache full → remove LRU (3)
        lru.display();   // [5, 2, 4]
    }

    // Add number to cache (our put method)
    public void put(int num) {
        // Case 1: If already exists, move it to front (most recent)
        if (lookup.contains(num)) {
            cache.remove(num);       // remove old occurrence
            cache.addFirst(num);     // add at front (most recent)
            return;
        }

        // Case 2: If new number and cache is full, remove least recent (rear)
        if (cache.size() == capacity) {
            int last = cache.removeLast(); // remove least recent
            lookup.remove(last);           // also remove from set
        }

        // Case 3: Add the new number at front
        cache.addFirst(num);
        lookup.add(num);
    }

    // Display cache contents (front → rear)
    public void display() {
        System.out.println(cache);
    }

    // Get cache as List (for testing)
    public List<Integer> getCache() {
        return new ArrayList<>(cache);
    }
}