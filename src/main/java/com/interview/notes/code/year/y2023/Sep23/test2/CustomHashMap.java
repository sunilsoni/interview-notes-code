package com.interview.notes.code.year.y2023.Sep23.test2;

public class CustomHashMap<K, V> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    private Entry<K, V>[] entries;
    private int size;

    public CustomHashMap() {
        this(DEFAULT_CAPACITY);
    }

    public CustomHashMap(int capacity) {
        entries = new Entry[capacity];
    }

    public void put(K key, V value) {
        int index = getIndex(key);
        Entry<K, V> entry = entries[index];
        if (entry == null) {
            entries[index] = new Entry<>(key, value);
            size++;
            if (size > entries.length * LOAD_FACTOR) {
                resize();
            }
        } else {
            entry.value = value;
        }
    }

    public V get(K key) {
        int index = getIndex(key);
        Entry<K, V> entry = entries[index];
        if (entry == null) {
            return null;
        } else {
            return entry.value;
        }
    }

    private int getIndex(K key) {
        return (key.hashCode() & 0x7FFFFFFF) % entries.length;
    }

    private void resize() {
        Entry<K, V>[] newEntries = new Entry[entries.length * 2];
        for (Entry<K, V> entry : entries) {
            if (entry != null) {
                newEntries[getIndex(entry.key)] = entry;
            }
        }
        entries = newEntries;
    }

    private static class Entry<K, V> {
        K key;
        V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}