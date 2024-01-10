package com.interview.notes.code.months.year2023.Sep23.test1;

import java.util.LinkedList;

public class CustomHashMap<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private LinkedList<Entry<K, V>>[] buckets;
    private int capacity;
    private int size;

    public CustomHashMap() {
        this(DEFAULT_CAPACITY);
    }

    public CustomHashMap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.buckets = new LinkedList[capacity];
    }

    public void put(K key, V value) {
        int index = getIndex(key);
        if (buckets[index] == null) {
            buckets[index] = new LinkedList<>();
        }

        // Check if the key already exists in the list and update its value if so
        for (Entry<K, V> entry : buckets[index]) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
        }

        // If the key doesn't exist, add a new entry
        buckets[index].add(new Entry<>(key, value));
        size++;
    }

    public V get(K key) {
        int index = getIndex(key);
        if (buckets[index] == null) {
            return null; // Key not found
        }

        // Search for the key in the list at the calculated index
        for (Entry<K, V> entry : buckets[index]) {
            if (entry.getKey().equals(key)) {
                return entry.getValue(); // Key found
            }
        }

        return null; // Key not found
    }

    public void remove(K key) {
        int index = getIndex(key);
        if (buckets[index] == null) {
            return; // Key not found
        }

        // Search for the key in the list at the calculated index and remove it
        buckets[index].removeIf(entry -> entry.getKey().equals(key));
        size--;
    }

    public int size() {
        return size;
    }

    private int getIndex(K key) {
        int hashCode = key.hashCode();
        return (hashCode & 0x7FFFFFFF) % capacity;
    }

    private static class Entry<K, V> {
        private final K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }
}
