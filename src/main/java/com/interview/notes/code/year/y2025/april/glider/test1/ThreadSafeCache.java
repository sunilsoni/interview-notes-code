package com.interview.notes.code.year.y2025.april.glider.test1;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * A thread-safe in-memory cache implementation
 *
 * @param <K> Type of cache keys
 * @param <V> Type of cache values
 */
public class ThreadSafeCache<K, V> {
    // Using ConcurrentHashMap for thread-safe operations
    private final ConcurrentHashMap<K, V> cache;

    // ReadWriteLock for better concurrent performance
    private final ReadWriteLock lock;

    private final int capacity;

    /**
     * Default constructor with standard capacity
     */
    public ThreadSafeCache() {
        this(1000); // Default size
    }

    /**
     * Constructor with custom capacity
     *
     * @param capacity Maximum entries the cache can hold
     */
    public ThreadSafeCache(int capacity) {
        this.cache = new ConcurrentHashMap<>();
        this.lock = new ReentrantReadWriteLock();
        this.capacity = capacity;
    }

    /**
     * Test method with multi-threaded scenarios
     */
    public static void main(String[] args) {
        ThreadSafeCache<String, Integer> cache = new ThreadSafeCache<>();

        // Test Case 1: Basic Operations
        cache.put("one", 1);
        assert cache.get("one") == 1 : "Test Case 1 Failed";
        System.out.println("Test Case 1: PASS");

        // Test Case 2: Concurrent Access
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            final int threadNum = i;
            threads[i] = new Thread(() -> {
                try {
                    // Each thread performs multiple operations
                    for (int j = 0; j < 100; j++) {
                        String key = "key-" + threadNum + "-" + j;
                        cache.put(key, j);
                        assert cache.get(key) == j : "Concurrent Test Failed";
                        if (j % 2 == 0) {
                            cache.remove(key);
                            assert cache.get(key) == null : "Remove Test Failed";
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        // Start all threads
        for (Thread thread : threads) {
            thread.start();
        }

        // Wait for all threads to complete
        try {
            for (Thread thread : threads) {
                thread.join();
            }
            System.out.println("Test Case 2: PASS - Concurrent Operations Successful");
        } catch (InterruptedException e) {
            System.out.println("Test Case 2: FAIL - Thread interrupted");
        }

        // Test Case 3: Capacity Limit
        ThreadSafeCache<String, Integer> smallCache = new ThreadSafeCache<>(2);
        smallCache.put("A", 1);
        smallCache.put("B", 2);
        try {
            smallCache.put("C", 3); // Should throw exception
            System.out.println("Test Case 3: FAIL - Capacity limit not enforced");
        } catch (IllegalStateException e) {
            System.out.println("Test Case 3: PASS - Capacity limit enforced");
        }

        // Test Case 4: Null Key Handling
        try {
            cache.put(null, 1);
            System.out.println("Test Case 4: FAIL - Null key accepted");
        } catch (IllegalArgumentException e) {
            System.out.println("Test Case 4: PASS - Null key rejected");
        }
    }

    /**
     * Thread-safe put operation
     *
     * @param key   Key to store
     * @param value Value to be stored
     * @return Previous value if exists
     */
    public V put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        lock.writeLock().lock();
        try {
            if (cache.size() >= capacity) {
                // Simple capacity check, could implement eviction policy here
                throw new IllegalStateException("Cache is full");
            }
            return cache.put(key, value);
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Thread-safe get operation
     *
     * @param key Key to lookup
     * @return Value if found, null otherwise
     */
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        lock.readLock().lock();
        try {
            return cache.get(key);
        } finally {
            lock.readLock().unlock();
        }
    }

    /**
     * Thread-safe remove operation
     *
     * @param key Key to remove
     * @return Removed value if exists
     */
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        lock.writeLock().lock();
        try {
            return cache.remove(key);
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * Get current cache size
     *
     * @return Number of entries in cache
     */
    public int size() {
        lock.readLock().lock();
        try {
            return cache.size();
        } finally {
            lock.readLock().unlock();
        }
    }
}
