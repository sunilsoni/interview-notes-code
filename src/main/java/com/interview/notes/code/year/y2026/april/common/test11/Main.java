package com.interview.notes.code.year.y2026.april.common.test11;// Imports LinkedHashMap to maintain our ordered sequence of items.
import java.util.LinkedHashMap;

// Defines our generic LruCache class for any key (K) and value (V) pairs.
class LruCache<K, V> {
    
    // Stores the maximum allowed capacity for our cache.
    private final int maxSize;
    
    // Our storage map. By default, LinkedHashMap maintains insertion order (newest items at the end).
    private final LinkedHashMap<K, V> store;

    // Constructor to set the size limit and initialize the map.
    public LruCache(int maxSize) {
        // Saves the capacity limit.
        this.maxSize = maxSize;
        // Initializes the empty ordered map.
        this.store = new LinkedHashMap<>();
    }

    // Method to insert or update an item, marking it as the most recently used.
    public void put(K key, V value) {
        // If the key already exists, we remove it first. This ensures that when we put it back, it goes to the end of the line.
        store.remove(key);
        
        // Puts the item into the map. Because of the previous step (or if it's brand new), it becomes the newest item.
        store.put(key, value);
        
        // Checks if adding this item exceeded our capacity limit.
        if (store.size() > maxSize) {
            // Because actively used items are moved to the back, the very first item is guaranteed to be the Least Recently Used.
            store.pollFirstEntry(); // Removes it in O(1) time.
        }
    }

    // Method to retrieve a value, marking it as the most recently used.
    public V get(K key) {
        // Removes the item from its current position in the list. If it doesn't exist, this safely returns null.
        var value = store.remove(key);
        
        // If the item actually existed in our cache...
        if (value != null) {
            // ...we put it right back in. This simple trick forces it to the back of the line as the most recently used item.
            store.put(key, value);
        }
        
        // Returns the requested value (or null if it was never there).
        return value;
    }
}

// Main class to run our custom tests and verify the LRU logic.
public class Main {
    
    public static void main(String[] args) {
        
        // Creates a cache that holds exactly 3 items.
        var cache = new LruCache<String, Integer>(3);
        
        // Adds three items. Order from oldest to newest: a -> b -> c
        cache.put("a", 1);
        cache.put("b", 2);
        cache.put("c", 3);
        
        // We explicitly read 'a'. This triggers our get() logic, removing 'a' and putting it at the back. 
        // New order from oldest to newest: b -> c -> a
        check(cache.get("a") != null && cache.get("a") == 1, "Test 1: Read 'a', moving it to the newest position");
        
        // We add a brand new item 'd'. The cache is full, so it must evict the oldest item.
        // Because 'a' was recently read, 'b' is now stuck at the front of the line as the LRU item.
        cache.put("d", 4);
        
        // Test 2: Asserts that 'b' was the one evicted, returning null.
        check(cache.get("b") == null, "Test 2: 'b' was correctly evicted as the Least Recently Used");
        
        // Test 3: Asserts that 'a' is still perfectly safe because we saved it by reading it earlier.
        check(cache.get("a") != null && cache.get("a") == 1, "Test 3: 'a' survived eviction because it was recently used");
        
        // Creates a large cache to test performance and memory handling under heavy load.
        var largeCache = new LruCache<Integer, String>(100);
        
        // Populates the cache with 100 items (Keys 0 through 99).
        for (var i = 0; i < 100; i++) {
            largeCache.put(i, "val" + i);
        }
        
        // We read key '0', moving it from the oldest position to the absolute newest position.
        largeCache.get(0);
        
        // We add one more item (Key 100), forcing exactly one eviction.
        largeCache.put(100, "val100");
        
        // Test 4: Because we read '0', key '1' became the oldest and should be the one evicted.
        check(largeCache.get(1) == null, "Test 4: Key '1' evicted from large dataset");
        
        // Test 5: Key '0' should still be perfectly safe.
        check("val0".equals(largeCache.get(0)), "Test 5: Key '0' survived in large dataset");
    }

    // Helper method to evaluate test conditions and print results.
    private static void check(boolean condition, String testName) {
        if (condition) {
            System.out.println("PASS: " + testName);
        } else {
            System.out.println("FAIL: " + testName);
        }
    }
}