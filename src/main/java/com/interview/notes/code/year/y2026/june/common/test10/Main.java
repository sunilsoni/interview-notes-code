package com.interview.notes.code.year.y2026.june.common.test10;

import java.util.LinkedHashMap; // Used because it keeps insertion/access order
import java.util.Map;           // Used for Map.Entry inside removeEldestEntry

public class Main { // Main class to run the program

    static void check(String testName, Object actual, Object expected) { // Simple PASS/FAIL test method

        boolean pass = actual.equals(expected); // Compares actual and expected result

        System.out.println((pass ? "PASS - " : "FAIL - ") + testName // Prints test status and name
                + " | Expected: " + expected // Prints expected result
                + " | Actual: " + actual); // Prints actual result
    }

    public static void main(String[] args) { // Program starts here

        LRUCache cache = new LRUCache(2); // Creates cache with capacity 2

        cache.put(1, 10); // Adds key 1
        cache.put(2, 20); // Adds key 2

        check("Get existing key 1", cache.get(1), 10); // Key 1 exists, so returns 10

        cache.put(3, 30); // Adds key 3, removes least recently used key 2

        check("Key 2 should be removed", cache.get(2), -1); // Key 2 was least used, so removed
        check("Key 3 should exist", cache.get(3), 30); // Key 3 exists

        cache.put(4, 40); // Adds key 4, removes least recently used key 1

        check("Key 1 should be removed", cache.get(1), -1); // Key 1 should be removed
        check("Key 3 should exist", cache.get(3), 30); // Key 3 should still exist
        check("Key 4 should exist", cache.get(4), 40); // Key 4 should exist

        cache.put(3, 300); // Updates key 3 and makes it recently used

        check("Updated key 3", cache.get(3), 300); // Key 3 should return updated value

        LRUCache oneSizeCache = new LRUCache(1); // Creates cache with capacity 1

        oneSizeCache.put(1, 100); // Adds key 1
        oneSizeCache.put(2, 200); // Adds key 2 and removes key 1

        check("Capacity 1 removes old key", oneSizeCache.get(1), -1); // Key 1 should be gone
        check("Capacity 1 keeps latest key", oneSizeCache.get(2), 200); // Key 2 should exist

        LRUCache largeCache = new LRUCache(10_000); // Creates large cache for large data test

        for (int i = 1; i <= 20_000; i++) { // Inserts 20,000 items
            largeCache.put(i, i * 10); // Stores key and value
        }

        check("Large test old key removed", largeCache.get(1), -1); // Oldest key should be removed
        check("Large test latest key exists", largeCache.get(20_000), 200_000); // Latest key should exist
    }

    static class LRUCache { // Cache class kept static so main can create it easily

        private final int capacity; // Stores maximum cache size

        private final LinkedHashMap<Integer, Integer> cache; // Stores key-value data in access order

        public LRUCache(int capacity) { // Constructor receives cache capacity

            if (capacity <= 0) { // Validates capacity because cache size must be positive
                throw new IllegalArgumentException("Capacity must be greater than zero"); // Stops invalid cache creation
            }

            this.capacity = capacity; // Saves capacity for later use

            this.cache = new LinkedHashMap<>(capacity, 0.75f, true) { // true means maintain access order

                @Override // Overrides LinkedHashMap method to remove old entries automatically
                protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) { // Called after every put
                    return size() > LRUCache.this.capacity; // Removes least recently used item when size exceeds capacity
                }
            };
        }

        public int get(int key) { // Returns value for key

            return cache.getOrDefault(key, -1); // Returns value if present, otherwise -1
        }

        public void put(int key, int value) { // Adds or updates value

            cache.put(key, value); // LinkedHashMap updates access order automatically
        }

        public String keys() { // Helps testing by showing key order

            return cache.keySet().toString(); // Returns keys from least used to most recently used
        }
    }
}