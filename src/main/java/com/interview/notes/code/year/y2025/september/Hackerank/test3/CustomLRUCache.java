package com.interview.notes.code.year.y2025.september.Hackerank.test3;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class CustomLRUCache {
    private final int capacity;
    private final Deque<Integer> cache;
    private final Set<Integer> lookup;

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
        lru.put(2);
        lru.put(3);
        lru.display();   // [3, 2, 1]

        // Get 2 → should move 2 to front
        System.out.println("Get 2: " + lru.get(2)); // 2
        lru.display();   // [2, 3, 1]

        // Get 5 → not present
        System.out.println("Get 5: " + lru.get(5)); // -1
        lru.display();   // [2, 3, 1]

        // Add 4 → evicts 1
        lru.put(4);
        lru.display();   // [4, 2, 3]

        // Get 3 → should move to front
        System.out.println("Get 3: " + lru.get(3)); // 3
        lru.display();   // [3, 4, 2]
    }

    // Add number to cache (put method)
    public void put(int num) {
        // Case 1: If already exists, move it to front
        if (lookup.contains(num)) {
            cache.remove(num);
            cache.addFirst(num);
            return;
        }

        // Case 2: If new and cache is full, evict least recent
        if (cache.size() == capacity) {
            int last = cache.removeLast();
            lookup.remove(last);
        }

        // Case 3: Add new number
        cache.addFirst(num);
        lookup.add(num);
    }

    // Get method (check if exists, move to front)
    public int get(int num) {
        if (!lookup.contains(num)) {
            return -1; // not found
        }
        // Found → move it to front (most recent)
        cache.remove(num);
        cache.addFirst(num);
        return num;
    }

    // Display cache contents
    public void display() {
        System.out.println(cache);
    }
}