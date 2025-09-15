package com.interview.notes.code.year.y2025.september.common.test;

import java.util.*;
import java.util.stream.Collectors;

// Step 1: Define the interface for Key-Value DB
interface KeyValueDB<K, V> {
    void put(K key, V value);          // Store a key-value pair

    V get(K key);                      // Retrieve value by key

    void delete(K key);                // Delete key-value pair

    boolean exists(K key);             // Check if key exists

    List<Map.Entry<K, V>> getAll();    // Return all entries as a list

    List<V> lastInserted(int n);       // Get last n inserted/updated values
}

// Step 2: Implement the interface using an in-memory HashMap
class InMemoryKeyValueDB<K, V> implements KeyValueDB<K, V> {

    private final Map<K, V> store;       // HashMap to store key-value pairs
    private final Deque<V> insertionLog; // Track last inserted/updated values
    private final int MAX_LOG_SIZE = 10; // Keep last 10 by default

    // Constructor initializes store and insertion log
    public InMemoryKeyValueDB() {
        this.store = new HashMap<>();
        this.insertionLog = new ArrayDeque<>();
    }

    // Store or update a key-value pair
    @Override
    public void put(K key, V value) {
        store.put(key, value);

        // Add value to insertion log
        insertionLog.addLast(value);

        // If log exceeds max size, remove oldest
        if (insertionLog.size() > MAX_LOG_SIZE) {
            insertionLog.removeFirst();
        }
    }

    // Retrieve value for a key (returns null if not found)
    @Override
    public V get(K key) {
        return store.get(key);
    }

    // Delete a key-value pair
    @Override
    public void delete(K key) {
        store.remove(key);
    }

    // Check if a key exists
    @Override
    public boolean exists(K key) {
        return store.containsKey(key);
    }

    // Return all key-value pairs using Stream API
    @Override
    public List<Map.Entry<K, V>> getAll() {
        return store.entrySet().stream().collect(Collectors.toList());
    }

    // Return last n inserted/updated values
    @Override
    public List<V> lastInserted(int n) {
        return insertionLog.stream()
                .skip(Math.max(0, insertionLog.size() - n)) // Take last n
                .collect(Collectors.toList());
    }
}

// Step 3: Main method for testing
public class KeyValueDBTest {
    public static void main(String[] args) {
        KeyValueDB<String, String> db = new InMemoryKeyValueDB<>();

        // Insert some values
        db.put("k1", "v1");
        db.put("k2", "v2");
        db.put("k3", "v3");
        db.put("k4", "v4");
        db.put("k5", "v5");
        db.put("k6", "v6");
        db.put("k7", "v7");
        db.put("k8", "v8");
        db.put("k9", "v9");
        db.put("k10", "v10");
        db.put("k11", "v11"); // This pushes out v1 from the log

        // Fetch last 5 inserted
        List<String> last5 = db.lastInserted(5);
        System.out.println("Last 5 inserted: " + last5);

        // Expected: [v7, v8, v9, v10, v11]
        System.out.println("Test Last Inserted: " +
                (last5.equals(Arrays.asList("v7", "v8", "v9", "v10", "v11")) ? "PASS" : "FAIL"));
    }
}