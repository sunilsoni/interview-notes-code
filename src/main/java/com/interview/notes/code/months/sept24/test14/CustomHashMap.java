package com.interview.notes.code.months.sept24.test14;

public class CustomHashMap<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    private Entry<K, V>[] buckets;
    private int size;

    public CustomHashMap() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public CustomHashMap(int capacity) {
        buckets = new Entry[capacity];
        size = 0;
    }

    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        int index = getIndex(key);
        Entry<K, V> entry = buckets[index];

        while (entry != null) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
            entry = entry.next;
        }

        Entry<K, V> newEntry = new Entry<>(key, value);
        newEntry.next = buckets[index];
        buckets[index] = newEntry;
        size++;

        if ((float) size / buckets.length >= LOAD_FACTOR) {
            resize();
        }
    }

    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        int index = getIndex(key);
        Entry<K, V> entry = buckets[index];

        while (entry != null) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
            entry = entry.next;
        }

        return null;
    }

    private int getIndex(K key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        int newCapacity = buckets.length * 2;
        Entry<K, V>[] newBuckets = new Entry[newCapacity];

        for (Entry<K, V> entry : buckets) {
            while (entry != null) {
                int newIndex = Math.abs(entry.key.hashCode()) % newCapacity;
                Entry<K, V> next = entry.next;
                entry.next = newBuckets[newIndex];
                newBuckets[newIndex] = entry;
                entry = next;
            }
        }

        buckets = newBuckets;
    }

    private static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        // Create a new CustomHashMap instance
        CustomHashMap<String, Integer> map = new CustomHashMap<>();

        // Put some key-value pairs
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 4);
        map.put("five", 5);

        // Get and print values
        System.out.println("Value for 'two': " + map.get("two"));
        System.out.println("Value for 'four': " + map.get("four"));
        System.out.println("Value for 'six': " + map.get("six")); // Should print null

        // Update an existing key
        map.put("three", 33);
        System.out.println("Updated value for 'three': " + map.get("three"));

        // Print the size of the map
        System.out.println("Size of the map: " + map.size());

        // Add more elements to trigger resize
        for (int i = 6; i <= 20; i++) {
            map.put("key" + i, i);
        }

        // Print some values after resize
        System.out.println("Value for 'key10': " + map.get("key10"));
        System.out.println("Value for 'key20': " + map.get("key20"));

        // Print the new size of the map
        System.out.println("New size of the map: " + map.size());
    }
}