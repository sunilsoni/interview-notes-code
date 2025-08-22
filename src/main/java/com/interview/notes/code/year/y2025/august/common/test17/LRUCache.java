package com.interview.notes.code.year.y2025.august.common.test17;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;

    public LRUCache(int capacity) {
        // accessOrder = true enables LRU behavior
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }

    // Optional helper methods
    public void putValue(K key, V value) {
        super.put(key, value);
    }

    public V getValue(K key) {
        return super.getOrDefault(key, null);
    }
}
