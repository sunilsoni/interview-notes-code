package com.interview.notes.code.year.y2024.oct24.test13;

import java.util.HashSet;
import java.util.Set;

/**
 * Node class represents a key-value pair stored in the CustomHashMap.
 */
class Node<K, V> {
    K key;
    V value;
    Node<K, V> next;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }
}

/**
 * CustomHashMap class implements a simple hash map with custom methods.
 */
class CustomHashMap<K, V> {
    private final int capacity = 16; // Initial capacity
    private final Node<K, V>[] buckets;

    @SuppressWarnings("unchecked")
    public CustomHashMap() {
        buckets = new Node[capacity];
    }

    /**
     * Custom put method to insert a key-value pair.
     *
     * @param key   The key to insert.
     * @param value The value associated with the key.
     */
    public void put(K key, V value) {
        int index = getIndex(key);
        Node<K, V> head = buckets[index];

        // Check if key is already present
        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value; // Update value
                return;
            }
            head = head.next;
        }

        // Insert new node at the beginning of the bucket
        Node<K, V> newNode = new Node<>(key, value);
        newNode.next = buckets[index];
        buckets[index] = newNode;
    }

    /**
     * Custom delete method to remove a key.
     *
     * @param key The key to remove.
     */
    public void delete(K key) {
        int index = getIndex(key);
        Node<K, V> head = buckets[index];
        Node<K, V> prev = null;

        while (head != null) {
            if (head.key.equals(key)) {
                if (prev == null) {
                    buckets[index] = head.next;
                } else {
                    prev.next = head.next;
                }
                return;
            }
            prev = head;
            head = head.next;
        }
    }

    /**
     * Custom search method to check if a key exists.
     *
     * @param key The key to search for.
     * @return True if key exists, false otherwise.
     */
    public boolean search(K key) {
        int index = getIndex(key);
        Node<K, V> head = buckets[index];

        while (head != null) {
            if (head.key.equals(key)) {
                return true;
            }
            head = head.next;
        }
        return false;
    }

    /**
     * Helper method to get the index for a key.
     *
     * @param key The key.
     * @return The index in the buckets array.
     */
    private int getIndex(K key) {
        int hashCode = key.hashCode();
        return Math.abs(hashCode) % capacity;
    }

    /**
     * Returns a set of all keys in the map.
     *
     * @return A set of keys.
     */
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (Node<K, V> head : buckets) {
            while (head != null) {
                keys.add(head.key);
                head = head.next;
            }
        }
        return keys;
    }
}

/**
 * CustomDataStructure class uses CustomHashMap to store elements.
 */
public class CustomDataStructure {

    // Dummy value to associate with keys
    private static final Object DUMMY = new Object();
    // Map to store elements with custom methods
    private final CustomHashMap<String, Object> elementsMap;

    /**
     * Constructor initializes the data structure.
     */
    public CustomDataStructure() {
        elementsMap = new CustomHashMap<>();
    }

    /**
     * Main method for testing the CustomDataStructure.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        CustomDataStructure ds = new CustomDataStructure();

        // Basic Test Cases
        System.out.println("Starting basic test cases...");

        // Test insertion of elements
        ds.insert("a");
        ds.insert("abc");
        ds.insert("b");
        ds.insert("a"); // Duplicate insertion should be ignored

        // Expected elements: ["a", "abc", "b"]
        System.out.println("Elements after insertion: " + ds.getElements());

        // Test search operation
        assert ds.search("a") : "Test search 'a' failed";
        assert ds.search("abc") : "Test search 'abc' failed";
        assert !ds.search("c") : "Test search 'c' failed";

        // Test delete operation
        ds.delete("a");
        assert !ds.search("a") : "Test delete 'a' failed";
        ds.delete("d"); // Deleting non-existent element should do nothing

        // Expected elements after deletion: ["abc", "b"]
        System.out.println("Elements after deletion: " + ds.getElements());

        System.out.println("All basic test cases passed!\n");

        // Large Data Test Cases
        System.out.println("Starting large data test cases...");
        int largeNumber = 100000;

        // Insert a large number of elements
        for (int i = 0; i < largeNumber; i++) {
            ds.insert("element" + i);
        }

        // Verify the size after insertion
        assert ds.getElements().size() == largeNumber + 2 : "Large data insertion test failed";

        // Delete all large data elements
        for (int i = 0; i < largeNumber; i++) {
            ds.delete("element" + i);
        }

        // Verify the size after deletion
        assert ds.getElements().size() == 2 : "Large data deletion test failed";

        System.out.println("Large data test cases passed!");
    }

    /**
     * Inserts an element into the data structure if not already present.
     *
     * @param element The string element to insert.
     */
    public void insert(String element) {
        if (!elementsMap.search(element)) {
            elementsMap.put(element, DUMMY);
        }
    }

    /**
     * Deletes an element from the data structure if present.
     *
     * @param element The string element to delete.
     */
    public void delete(String element) {
        elementsMap.delete(element);
    }

    /**
     * Searches for an element in the data structure.
     *
     * @param element The string element to search for.
     * @return True if the element is present, false otherwise.
     */
    public boolean search(String element) {
        return elementsMap.search(element);
    }

    /**
     * Returns a set of all elements in the data structure.
     *
     * @return A set of strings.
     */
    public Set<String> getElements() {
        return elementsMap.keySet();
    }
}
