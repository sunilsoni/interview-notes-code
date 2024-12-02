package com.interview.notes.code.year.y2024.sept24.test15;

import java.util.LinkedHashMap;

public class AbstractDataTypeWithLast<K, V> {
    private LinkedHashMap<K, V> map;  // LinkedHashMap to store key-value pairs and track access order
    private K lastKey;  // Track the most recent key

    public AbstractDataTypeWithLast() {
        // Initialize LinkedHashMap with access-order enabled (true)
        map = new LinkedHashMap<>(16, 0.75f, true);
        lastKey = null;  // Initially, no key is accessed
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
        map.put(k, v);  // LinkedHashMap automatically updates access order
        lastKey = k;    // Update the last accessed key
    }

    // Returns the value associated with key k.
    public V get(K k) {
        V value = map.get(k);  // LinkedHashMap automatically updates access order
        if (value != null) {
            lastKey = k;  // Update the last accessed key
        }
        return value;
    }

    // Removes value for key k.
    public void remove(K k) {
        map.remove(k);  // LinkedHashMap automatically handles removal
        if (k.equals(lastKey)) {
            lastKey = null;  // Invalidate the lastKey if it was removed
        }
    }

    // Returns the most recent key either added with put() or accessed with get() that hasn't been removed by delete().
    public K last() {
        return lastKey;  // Return the most recent key
    }
}
