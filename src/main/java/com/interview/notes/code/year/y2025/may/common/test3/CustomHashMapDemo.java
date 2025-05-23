package com.interview.notes.code.year.y2025.may.common.test3;

import java.util.Objects;

class CustomHashMap<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    private Entry<K, V>[] buckets;
    private int size = 0;

    // Constructor
    public CustomHashMap() {
        buckets = new Entry[DEFAULT_CAPACITY];
    }

    // Hash Function
    private int getBucketIndex(K key) {
        return Math.abs(Objects.hashCode(key)) % buckets.length;
    }

    // Put method
    public void put(K key, V value) {
        int index = getBucketIndex(key);
        Entry<K, V> newEntry = new Entry<>(key, value);

        if (buckets[index] == null) {
            buckets[index] = newEntry;
        } else {
            Entry<K, V> current = buckets[index];
            while (current.next != null) {
                if (current.key.equals(key)) {
                    current.value = value; // Update existing value
                    return;
                }
                current = current.next;
            }
            current.next = newEntry; // Add new entry
        }
        size++;

        // Resize if needed
        if (size >= LOAD_FACTOR * buckets.length) {
            resize();
        }
    }

    // Get method
    public V get(K key) {
        int index = getBucketIndex(key);
        Entry<K, V> current = buckets[index];

        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    // Resize method
    private void resize() {
        Entry<K, V>[] oldBuckets = buckets;
        buckets = new Entry[oldBuckets.length * 2];
        size = 0;

        for (Entry<K, V> entry : oldBuckets) {
            while (entry != null) {
                put(entry.key, entry.value);
                entry = entry.next;
            }
        }
    }

    // Remove method
    public void remove(K key) {
        int index = getBucketIndex(key);
        Entry<K, V> current = buckets[index];
        Entry<K, V> previous = null;

        while (current != null) {
            if (current.key.equals(key)) {
                if (previous == null) {
                    buckets[index] = current.next;
                } else {
                    previous.next = current.next;
                }
                size--;
                return;
            }
            previous = current;
            current = current.next;
        }
    }

    // Size method
    public int size() {
        return size;
    }

    // Node structure
    static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }
}

public class CustomHashMapDemo {
    public static void main(String[] args) {
        CustomHashMap<String, Integer> map = new CustomHashMap<>();
        map.put("Alice", 25);
        map.put("Bob", 30);
        map.put("Charlie", 35);

        System.out.println("Alice's Age: " + map.get("Alice"));
        System.out.println("Size: " + map.size());

        map.remove("Bob");
        System.out.println("Bob's Age after removal: " + map.get("Bob"));
        System.out.println("Size after removal: " + map.size());
    }
}
