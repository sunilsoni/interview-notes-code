package com.interview.notes.code.year.y2026.july.common.test2;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class CacheEntry<T> {
    private final T value;
    private final Instant expiryTime;

    CacheEntry(T value, long expiryMinutes) {
        this.value = value;
        this.expiryTime = Instant.now().plusSeconds(expiryMinutes * 60);
    }

    T getValue() {
        return value;
    }

    boolean isExpired() {
        return Instant.now().isAfter(expiryTime);
    }
}

class SimpleCache<K, V> {

    private final Map<K, CacheEntry<V>> cache = new ConcurrentHashMap<>();

    void put(K key, V value) {
        cache.put(key, new CacheEntry<>(value, 5));
    }

    V get(K key) {
        CacheEntry<V> entry = cache.get(key);

        if (entry == null) {
            return null;
        }

        if (entry.isExpired()) {
            cache.remove(key);
            return null;
        }

        return entry.getValue();
    }
}

public class Main {

    public static void main(String[] args) {

        SimpleCache<String, String> cache = new SimpleCache<>();

        cache.put("user1", "Sunil");

        System.out.println(cache.get("user1"));
    }
}