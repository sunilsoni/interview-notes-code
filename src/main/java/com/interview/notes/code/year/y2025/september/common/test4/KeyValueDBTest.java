package com.interview.notes.code.year.y2025.september.common.test4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// Step 1: Define the interface for Key-Value DB
interface KeyValueDB<K, V> {
    void put(K key, V value);          // Store a key-value pair

    V get(K key);                      // Retrieve value by key

    void delete(K key);                // Delete key-value pair

    boolean exists(K key);             // Check if key exists

    List<Map.Entry<K, V>> getAll();    // Return all entries as a list
}

// Step 2: Implement the interface using an in-memory HashMap
class InMemoryKeyValueDB<K, V> implements KeyValueDB<K, V> {

    private final Map<K, V> store; // HashMap to store key-value pairs

    // Constructor initializes HashMap
    public InMemoryKeyValueDB() {
        this.store = new HashMap<>();
    }

    // Store or update a key-value pair
    @Override
    public void put(K key, V value) {
        store.put(key, value);
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
}

// Step 3: Main method for testing
public class KeyValueDBTest {
    public static void main(String[] args) {
        KeyValueDB<String, String> db = new InMemoryKeyValueDB<>();

        // Test 1: Insert and Retrieve
        db.put("name", "Alice");
        String result1 = db.get("name");
        System.out.println("Test 1: " + (result1.equals("Alice") ? "PASS" : "FAIL"));

        // Test 2: Update existing key
        db.put("name", "Bob");
        String result2 = db.get("name");
        System.out.println("Test 2: " + (result2.equals("Bob") ? "PASS" : "FAIL"));

        // Test 3: Delete key
        db.delete("name");
        System.out.println("Test 3: " + (!db.exists("name") ? "PASS" : "FAIL"));

        // Test 4: Check non-existing key
        System.out.println("Test 4: " + (db.get("unknown") == null ? "PASS" : "FAIL"));

        // Test 5: Multiple inserts and getAll
        db.put("city", "New York");
        db.put("country", "USA");
        List<Map.Entry<String, String>> allEntries = db.getAll();
        System.out.println("Test 5: " + (allEntries.size() == 2 ? "PASS" : "FAIL"));

        // Test 6: Large Data Input (Performance Test)
        KeyValueDB<Integer, Integer> bigDB = new InMemoryKeyValueDB<>();
        int N = 100000; // 100K entries
        for (int i = 0; i < N; i++) {
            bigDB.put(i, i * 2);
        }
        boolean pass = true;
        for (int i = 0; i < N; i++) {
            if (bigDB.get(i) != i * 2) {
                pass = false;
                break;
            }
        }
        System.out.println("Test 6 (Large Data): " + (pass ? "PASS" : "FAIL"));
    }
}