package com.interview.notes.code.year.y2024.sept24.test16;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class AbstractDataTypeWithLast<K, V> {
    private Map<K, V> map;              // To store key-value pairs
    private LinkedList<K> accessOrder;  // To track the order of accessed keys

    public AbstractDataTypeWithLast() {
        map = new HashMap<>();
        accessOrder = new LinkedList<>();
    }

    // Example usage
    public static void main(String[] args) {
        AbstractDataTypeWithLast<String, Integer> data = new AbstractDataTypeWithLast<>();

        data.put("a", 1);
        data.put("b", 2);
        System.out.println(data.last()); // => "b"

        System.out.println(data.get("a")); // => 1
        System.out.println(data.last()); // => "a"

        data.remove("a");
        System.out.println(data.last()); // => "b"
    }

    // Adds a value v to this data structure. It can be accessed by get() using key k.
    public void put(K k, V v) {
        map.put(k, v);
        updateAccessOrder(k); // Update the access order
    }

    // Returns the value associated with key k.
    public V get(K k) {
        if (map.containsKey(k)) {
            updateAccessOrder(k); // Update the access order
            return map.get(k);
        }
        return null; // Or throw an exception if key doesn't exist
    }

    // Removes value for key k.
    public void remove(K k) {
        map.remove(k);
        accessOrder.remove(k); // Remove the key from access order
    }

    // Returns the most recent key either added with put() or accessed with get() that hasn't been removed by delete().
    public K last() {
        if (!accessOrder.isEmpty()) {
            return accessOrder.getLast(); // Return the last accessed key
        }
        return null; // Or throw an exception if no keys exist
    }

    // Helper method to update the access order
    private void updateAccessOrder(K k) {
        accessOrder.remove(k); // Remove the key if it exists (to avoid duplicates)
        accessOrder.addLast(k); // Add the key to the end (most recent)
    }
}
