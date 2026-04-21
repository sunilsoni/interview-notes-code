package com.interview.notes.code.year.y2026.april.common.test10;// Imports LinkedHashMap which maintains the order items are inserted, perfectly solving our FIFO requirement.
import java.util.LinkedHashMap;

// Defines a generic FifoCache class for any key (K) and value (V) data types.
class FifoCache<K, V> {
    
    // Stores the maximum number of items the cache is allowed to hold before it must start deleting.
    private final int maxSize;
    
    // Uses LinkedHashMap to store data and automatically remember the exact chronological sequence of insertions.
    private final LinkedHashMap<K, V> store;

    // Constructor to initialize the cache with a specific maximum capacity.
    public FifoCache(int maxSize) {
        // Assigns the passed maximum capacity to our internal variable so we can monitor it later.
        this.maxSize = maxSize;
        // Initializes the empty LinkedHashMap ready to accept our key-value pairs.
        this.store = new LinkedHashMap<>();
    }

    // Method to insert a new item and enforce the FIFO eviction rule if the cache becomes full.
    public void put(K key, V value) {
        // Adds the new key and value to the end of the map, recording it as the absolute newest item.
        store.put(key, value);
        
        // Checks if adding this brand-new item pushed our total stored count over the allowed maximum limit.
        if (store.size() > maxSize) {
            // Java 21 Feature: pollFirstEntry() instantly removes the very first (oldest) item inserted in O(1) time.
            store.pollFirstEntry();
        }
    }

    // Method to retrieve a value; note that simply reading an item does not change its chronological insertion order.
    public V get(K key) {
        // Fetches the value using the key; returns null instantly if the key was evicted due to capacity or never existed.
        return store.get(key);
    }
}

// Main class to execute our program and run our test cases manually to verify the FIFO logic.
public class Main {
    
    // The main entry point of the Java application where our testing execution begins.
    public static void main(String[] args) {
        
        // Creates a cache that can hold a maximum of 3 items at any given time.
        var cache = new FifoCache<String, Integer>(3);
        
        // Puts the first item into the cache (Current size: 1).
        cache.put("a", 1);
        // Puts the second item into the cache (Current size: 2).
        cache.put("b", 2);
        // Puts the third item into the cache (Current size: 3 - Cache is now full).
        cache.put("c", 3);
        
        // Test 1: Verifies that 'a' is still present because the cache limit was not exceeded yet.
        check(cache.get("a") != null && cache.get("a") == 1, "Test 1: 'a' exists before eviction");
        
        // Puts a fourth item; because max size is 3, the oldest item ('a') MUST be evicted right now.
        cache.put("d", 4);
        
        // Test 2: Verifies that 'a' has been successfully deleted (returns null) because it was the oldest item.
        check(cache.get("a") == null, "Test 2: 'a' correctly evicted (FIFO)");
        
        // Test 3: Verifies that 'b' is still present because it is now the new oldest item in the cache.
        check(cache.get("b") != null && cache.get("b") == 2, "Test 3: 'b' still exists");
        
        // Test 4: Verifies that the newest item 'd' was successfully stored.
        check(cache.get("d") != null && cache.get("d") == 4, "Test 4: 'd' successfully added");
        
        // Creates a new cache designed to test large data volumes with a strict capacity of just 100 items.
        var largeCache = new FifoCache<Integer, String>(100);
        
        // Loops one million times, constantly overflowing the cache and forcing massive amounts of FIFO evictions.
        for (var i = 0; i < 1000000; i++) {
            // Puts a new item in; every insertion after the 100th will cause the oldest item to drop out.
            largeCache.put(i, "val" + i);
        }
        
        // Test 5: Asserts that an old item (index 500,000) was evicted and no longer exists in the cache.
        check(largeCache.get(500000) == null, "Test 5: Old item from large dataset correctly evicted");
        
        // Test 6: Asserts that the very last item added (index 999,999) safely exists in the cache.
        check("val999999".equals(largeCache.get(999999)), "Test 6: Newest item from large dataset exists");
    }

    // A simple helper method to evaluate a true/false condition and print a clear PASS or FAIL status.
    private static void check(boolean condition, String testName) {
        // Checks if the evaluated test condition passed as 'true'.
        if (condition) {
            // Prints a success message to the system console if the condition was met.
            System.out.println("PASS: " + testName);
        } else {
            // Prints a failure message to the system console if the condition was false.
            System.out.println("FAIL: " + testName);
        }
    }
}