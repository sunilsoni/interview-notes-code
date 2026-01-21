package com.interview.notes.code.year.y2024.nov24.test6;

public class CustomHashMap<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private final float loadFactor;
    private Entry<K, V>[] buckets;
    private int size;

    public CustomHashMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public CustomHashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
        if (loadFactor <= 0 || Float.isNaN(loadFactor))
            throw new IllegalArgumentException("Illegal load factor: " + loadFactor);

        this.buckets = new Entry[initialCapacity];
        this.loadFactor = loadFactor;
        this.size = 0;
    }

    public void put(K key, V value) {
        if (key == null)
            throw new NullPointerException("Null key not allowed");

        int index = getIndex(key);
        Entry<K, V> entry = buckets[index];

        while (entry != null) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
            entry = entry.next;
        }

        addEntry(key, value, index);
    }

    private int getIndex(K key) {
        return (key.hashCode() & 0x7FFFFFFF) % buckets.length;
    }

    private void addEntry(K key, V value, int bucketIndex) {
        Entry<K, V> entry = new Entry<>(key, value, buckets[bucketIndex]);
        buckets[bucketIndex] = entry;
        size++;

        if (size > buckets.length * loadFactor) {
            resize(2 * buckets.length);
        }
    }

    private void resize(int newCapacity) {
        Entry<K, V>[] oldBuckets = buckets;
        buckets = new Entry[newCapacity];
        size = 0;

        for (Entry<K, V> entry : oldBuckets) {
            while (entry != null) {
                put(entry.key, entry.value);
                entry = entry.next;
            }
        }
    }

    private static class Entry<K, V> {
        final K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
