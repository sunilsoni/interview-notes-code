package com.interview.notes.code.year.y2025.september.common.test3;

import java.util.ArrayList;
import java.util.List;

// Interface for KeyValueDB
interface KeyValueDB<K, V> {
    void put(K key, V value);

    V get(K key);

    void delete(K key);

    boolean exists(K key);

    List<String> getAll();
}

// Node class for storing key-value pairs in linked list
class Entry<K, V> {
    K key;              // Key
    V value;            // Value
    Entry<K, V> next;   // Pointer to next entry in bucket

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }
}

// Our custom KeyValueDB implementation
class CustomKeyValueDB<K, V> implements KeyValueDB<K, V> {

    private final Entry<K, V>[] table;  // Array of buckets
    private final int capacity;         // Size of table (number of buckets)
    private int size;             // Number of key-value pairs stored

    @SuppressWarnings("unchecked")
    public CustomKeyValueDB(int capacity) {
        this.capacity = capacity;
        this.table = new Entry[capacity];  // Create array of buckets
        this.size = 0;
    }

    // Hash function to map key -> index
    private int hash(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    // Insert or update key-value pair
    @Override
    public void put(K key, V value) {
        int index = hash(key);        // Find bucket index
        Entry<K, V> head = table[index];

        // If key exists, update value
        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }

        // Insert new entry at beginning of bucket
        Entry<K, V> newNode = new Entry<>(key, value);
        newNode.next = table[index];
        table[index] = newNode;
        size++;
    }

    // Retrieve value by key
    @Override
    public V get(K key) {
        int index = hash(key);
        Entry<K, V> head = table[index];
        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }
        return null; // Key not found
    }

    // Delete a key-value pair
    @Override
    public void delete(K key) {
        int index = hash(key);
        Entry<K, V> head = table[index];
        Entry<K, V> prev = null;

        while (head != null) {
            if (head.key.equals(key)) {
                if (prev == null) {
                    table[index] = head.next; // Remove first node
                } else {
                    prev.next = head.next;    // Remove middle node
                }
                size--;
                return;
            }
            prev = head;
            head = head.next;
        }
    }

    // Check if key exists
    @Override
    public boolean exists(K key) {
        return get(key) != null;
    }

    // Get all key-value pairs as List<String> for easy testing
    @Override
    public List<String> getAll() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            Entry<K, V> head = table[i];
            while (head != null) {
                list.add(head.key + "=" + head.value);
                head = head.next;
            }
        }
        return list;
    }

    // Return number of elements stored
    public int size() {
        return size;
    }
}

// Testing class with main method
public class KeyValueDBTest {
    public static void main(String[] args) {
        KeyValueDB<String, String> db = new CustomKeyValueDB<>(16);

        // Test 1: Insert and Retrieve
        db.put("name", "Alice");
        System.out.println("Test 1: " + ("Alice".equals(db.get("name")) ? "PASS" : "FAIL"));

        // Test 2: Update existing key
        db.put("name", "Bob");
        System.out.println("Test 2: " + ("Bob".equals(db.get("name")) ? "PASS" : "FAIL"));

        // Test 3: Delete key
        db.delete("name");
        System.out.println("Test 3: " + (!db.exists("name") ? "PASS" : "FAIL"));

        // Test 4: Non-existing key
        System.out.println("Test 4: " + (db.get("age") == null ? "PASS" : "FAIL"));

        // Test 5: Multiple entries
        db.put("city", "NY");
        db.put("country", "USA");
        System.out.println("Test 5: " + (db.getAll().size() == 2 ? "PASS" : "FAIL"));

        // Test 6: Large Data Input (100k entries)
        KeyValueDB<Integer, Integer> bigDB = new CustomKeyValueDB<>(200000);
        int N = 100000;
        for (int i = 0; i < N; i++) {
            bigDB.put(i, i * 2);
        }
        boolean pass = true;
        for (int i = 0; i < N; i++) {
            if (!bigDB.get(i).equals(i * 2)) {
                pass = false;
                break;
            }
        }
        System.out.println("Test 6 (Large Data): " + (pass ? "PASS" : "FAIL"));
    }
}