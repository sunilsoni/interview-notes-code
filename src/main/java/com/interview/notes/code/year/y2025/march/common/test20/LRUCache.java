package com.interview.notes.code.year.y2025.march.common.test20;

import java.util.LinkedHashMap;
import java.util.Map;

class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;

    // Constructor
    public LRUCache(int capacity) {
        super(capacity, 0.75f, true); // true: access order
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }

    // Get value from cache
    public V getValue(K key) {
        return super.getOrDefault(key, null);
    }

    // Put value into cache
    public void putValue(K key, V value) {
        super.put(key, value);
    }
}
