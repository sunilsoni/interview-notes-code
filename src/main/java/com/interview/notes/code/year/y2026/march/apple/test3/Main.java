package com.interview.notes.code.year.y2026.march.apple.test3;

import java.util.LinkedHashMap; // Imports LinkedHashMap to utilize its built-in access-order and mapping functionality.
import java.util.Map; // Imports Map interface so we can safely interact with Map.Entry in our overridden method.

public class Main { // Main class used to encapsulate our custom LRU Cache and its testing framework.

    public static void main(String[] args) { // Standard entry point for a Java application to run our simple testing framework.
        System.out.println("--- Starting LRU Cache Tests ---"); // Prints a header to the console to mark the start of testing.

        // --- TEST CASE 1: Basic Functionality & Eviction ---
        var cache = new LRUCache<Integer, String>(2); // Uses Java type inference (var) to cleanly create a cache of size 2.
        cache.put(1, "A"); // Inserts the first key-value pair into the cache.
        cache.put(2, "B"); // Inserts the second key-value pair; the cache has now reached its maximum capacity.
        check("Get Key 1 (Makes it recent)", cache.get(1), "A"); // Accesses key 1, safely moving it to the 'most recently used' position.

        cache.put(3, "C"); // Inserts a third item, which immediately triggers the eviction of the least recently used item (Key 2).
        check("Eviction of Key 2", cache.get(2), null); // Verifies that Key 2 was successfully evicted by expecting a null return.
        check("Retention of Key 3", cache.get(3), "C"); // Verifies that the newest Key 3 was successfully added and retained.

        // --- TEST CASE 2: Large Data Input & Strict Boundaries ---
        var largeCache = new LRUCache<Integer, Integer>(1000); // Uses 'var' to instantiate a large cache capped at 1000 items.
        for (int i = 0; i < 2000; i++) { // Starts a standard loop to simulate massive, continuous data insertion.
            largeCache.put(i, i); // Puts 2000 items in, forcing the oldest 1000 items to be continuously and safely evicted.
        }

        check("Large Data: Oldest Evicted", largeCache.get(0), null); // Checks that the very first inserted item (0) is completely gone.
        check("Large Data: Newest Retained", largeCache.get(1999), 1999); // Checks that the very last inserted item (1999) is still present.
        check("Large Data: Strict Capacity", largeCache.size(), 1000); // Confirms the cache size never grew past the absolute limit of 1000.
    }

    // Helper method to compare expected results against actual results without relying on external libraries like JUnit.
    private static <T> void check(String testName, T actual, T expected) { // Uses Java Generics (<T>) to test Strings, Integers, or Objects equally.
        // Evaluates to true if both are null, or if both are non-null and cleanly equal to each other.
        boolean passed = (expected == null && actual == null) || (expected != null && expected.equals(actual));
        System.out.println(testName + " -> " + (passed ? "PASS" : "FAIL")); // Uses a ternary operator to print PASS or FAIL based on the logic above.
    }

    // Inner class defining the LRUCache, extending LinkedHashMap to entirely avoid writing complex node-management code.
    static class LRUCache<K, V> extends LinkedHashMap<K, V> { // Uses Generics (K, V) so the cache can store any data type.
        private final int capacity; // Declares a strictly final integer to permanently store the maximum allowed items.

        public LRUCache(int capacity) { // Constructor method that takes the desired maximum capacity as an argument.
            // Calls parent constructor: initial capacity, 0.75 standard load factor, and 'true' to enable LRU access-order.
            super(capacity, 0.75f, true);
            this.capacity = capacity; // Stores the user-provided capacity into our class-level variable for future size checks.
        }

        @Override // Annotation indicating we are intentionally modifying the parent class's default behavior.
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) { // Method automatically triggered by LinkedHashMap after every put().
            return size() > capacity; // Returns true if current size strictly exceeds defined capacity, causing automatic eviction.
        }
    }
}