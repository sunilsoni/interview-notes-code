package com.interview.notes.code.year.y2025.july.common.test6;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
 Cache Polices

Write a simple in memory cache that
Implements get and put methods
Allows to select none, one or both below retention polices
-   Implement retention policy that removes item when the period has passed
-   Implement retention policy that limits cache size and when limit is reached replaces the oldest item with the new one
More retention policies are going to be added in the future

 */

/**
 * Demonstration of an in-memory cache supporting pluggable retention policies.
 */
public class InMemoryCacheDemo {
    /**
     * Main method to run basic test cases and verify behavior.
     */
    public static void main(String[] args) throws InterruptedException {
        // Test 1: Time-based eviction
        Cache<String, String> timeCache = new Cache<>(List.of(new TimeBasedEvictionPolicy<>(100))); // TTL = 100ms
        timeCache.put("a", "apple"); // insert key "a"
        Thread.sleep(150); // wait to exceed TTL
        boolean timeTest = timeCache.get("a") == null; // expect expired
        System.out.println("Time-based eviction test: " + (timeTest ? "PASS" : "FAIL")); // report result

        // Test 2: Size-based eviction
        Cache<Integer, String> sizeCache = new Cache<>(List.of(new SizeBasedEvictionPolicy<>(2))); // max size = 2
        sizeCache.put(1, "one"); // insert first
        sizeCache.put(2, "two"); // insert second
        sizeCache.put(3, "three"); // should evict oldest (1)
        boolean sizeTest = sizeCache.get(1) == null && sizeCache.get(2) != null && sizeCache.get(3) != null;
        System.out.println("Size-based eviction test: " + (sizeTest ? "PASS" : "FAIL")); // report result

        // Test 3: Combined policies
        Cache<String, String> bothCache = new Cache<>(List.of(new TimeBasedEvictionPolicy<>(200), new SizeBasedEvictionPolicy<>(2)));
        bothCache.put("x", "X"); // insert x
        Thread.sleep(100); // wait half TTL
        bothCache.put("y", "Y"); // insert y
        bothCache.put("z", "Z"); // insert z, should evict oldest by size (x)
        Thread.sleep(150); // now x expired by TTL if still present
        boolean bothTest = bothCache.get("x") == null && bothCache.get("y") != null && bothCache.get("z") != null;
        System.out.println("Combined eviction test: " + (bothTest ? "PASS" : "FAIL")); // report result

        // Test 4: Large data handling
        int largeSize = 1000; // define max size
        Cache<Integer, Integer> largeCache = new Cache<>(List.of(new SizeBasedEvictionPolicy<>(largeSize)));
        for (int i = 0; i < 2000; i++) { // insert 2000 entries
            largeCache.put(i, i); // put i->i
        }
        boolean largeTest = largeCache.get(0) == null && largeCache.get(1999) == 1999 && largeCache.get(1000) != null;
        System.out.println("Large data eviction test: " + (largeTest ? "PASS" : "FAIL")); // report result
    }

    /**
     * Interface for any retention policy. New policies can implement this.
     */
    interface RetentionPolicy<K, V> {
        void apply(LinkedHashMap<K, CacheEntry<V>> entries); // enforce policy on current entries
    }

    /**
     * Wrapper to store value and its creation time for TTL policy.
     */
    static class CacheEntry<V> {
        V value; // stored value
        long creationTime; // time of insertion

        // Constructor to initialize value and timestamp
        CacheEntry(V value, long creationTime) {
            this.value = value; // assign the value
            this.creationTime = creationTime; // assign insertion time
        }
    }

    /**
     * Removes entries older than a specified TTL (in milliseconds).
     */
    static class TimeBasedEvictionPolicy<K, V> implements RetentionPolicy<K, V> {
        private final long ttlMillis; // time-to-live threshold

        // Constructor to set TTL
        TimeBasedEvictionPolicy(long ttlMillis) {
            this.ttlMillis = ttlMillis; // assign TTL duration
        }

        @Override
        public void apply(LinkedHashMap<K, CacheEntry<V>> entries) {
            long now = System.currentTimeMillis(); // current time for comparison

            // Find keys of entries that have expired
            entries.entrySet().stream() // start streaming entries
                    .filter(e -> now - e.getValue().creationTime >= ttlMillis) // filter expired
                    .map(Map.Entry::getKey) // extract keys
                    .collect(Collectors.toList()) // collect to avoid concurrent modification
                    .forEach(entries::remove); // remove expired entries
        }
    }

    /**
     * Limits cache size by removing oldest entries when size exceeds max.
     */
    static class SizeBasedEvictionPolicy<K, V> implements RetentionPolicy<K, V> {
        private final int maxSize; // maximum allowed entries

        // Constructor to set maximum size
        SizeBasedEvictionPolicy(int maxSize) {
            this.maxSize = maxSize; // assign size limit
        }

        @Override
        public void apply(LinkedHashMap<K, CacheEntry<V>> entries) {
            // While cache is too big, remove oldest entry
            while (entries.size() > maxSize) { // check size condition
                K oldestKey = entries.keySet().iterator().next(); // get first inserted key
                entries.remove(oldestKey); // evict oldest
            }
        }
    }

    /**
     * Core cache class with get/put and policy enforcement.
     */
    static class Cache<K, V> {
        private final LinkedHashMap<K, CacheEntry<V>> entries = new LinkedHashMap<>(); // store entries in insertion order
        private final List<RetentionPolicy<K, V>> policies = new ArrayList<>(); // list of active policies

        // Constructor to register chosen policies
        Cache(List<RetentionPolicy<K, V>> policies) {
            this.policies.addAll(policies); // add all provided policies
        }

        // Inserts or updates a value in cache
        public void put(K key, V value) {
            entries.put(key, new CacheEntry<>(value, System.currentTimeMillis())); // create new entry with timestamp
            enforcePolicies(); // apply policies after insertion
        }

        // Retrieves a value or returns null if missing/expired
        public V get(K key) {
            enforcePolicies(); // clean up expired or excess entries first
            CacheEntry<V> entry = entries.get(key); // fetch entry
            return entry == null ? null : entry.value; // return value or null
        }

        // Applies all registered policies in order
        private void enforcePolicies() {
            for (RetentionPolicy<K, V> policy : policies) { // iterate policies
                policy.apply(entries); // enforce each
            }
        }
    }
}