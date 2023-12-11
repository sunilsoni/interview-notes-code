package com.interview.notes.code.months.dec23.test2;

import java.util.LinkedList;

public class CustomHashMap<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    private LinkedList<Entry<K, V>>[] table;
    private int size;

    public CustomHashMap() {
        table = new LinkedList[DEFAULT_CAPACITY];
        size = 0;
    }

    public void put(K key, V value) {
        if (key == null)
            throw new IllegalArgumentException("Key cannot be null");

        int index = getIndex(key);
        LinkedList<Entry<K, V>> bucket = table[index];

        if (bucket == null) {
            bucket = new LinkedList<>();
            table[index] = bucket;
        }

        for (Entry<K, V> entry : bucket) {
            if (entry.key.equals(key)) {
                entry.value = value; // Key already exists, update the value
                return;
            }
        }

        bucket.add(new Entry<>(key, value));
        size++;

        // Check if the load factor exceeds the threshold, and if so, resize the table
        if ((double) size / table.length >= LOAD_FACTOR) {
            resizeTable();
        }
    }

    public V get(K key) {
        int index = getIndex(key);
        LinkedList<Entry<K, V>> bucket = table[index];

        if (bucket != null) {
            for (Entry<K, V> entry : bucket) {
                if (entry.key.equals(key)) {
                    return entry.value;
                }
            }
        }

        return null; // Key not found
    }

    public void remove(K key) {
        int index = getIndex(key);
        LinkedList<Entry<K, V>> bucket = table[index];

        if (bucket != null) {
            for (Entry<K, V> entry : bucket) {
                if (entry.key.equals(key)) {
                    bucket.remove(entry);
                    size--;
                    return;
                }
            }
        }
    }

    public int size() {
        return size;
    }

    private int getIndex(K key) {
        return key.hashCode() % table.length;
    }

    private void resizeTable() {
        LinkedList<Entry<K, V>>[] oldTable = table;
        table = new LinkedList[table.length * 2];
        size = 0;

        for (LinkedList<Entry<K, V>> bucket : oldTable) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    put(entry.key, entry.value);
                }
            }
        }
    }

    private static class Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        CustomHashMap<String, Integer> customHashMap = new CustomHashMap<>();
        customHashMap.put("one", 1);
        customHashMap.put("two", 2);
        customHashMap.put("three", 3);

        System.out.println("Size: " + customHashMap.size());
        System.out.println("Value for 'two': " + customHashMap.get("two"));

        customHashMap.remove("two");
        System.out.println("Size after removing 'two': " + customHashMap.size());
    }
}
