package com.interview.notes.code.speechify.cache;

/**
 * Generic LRU cache interface.
 */
public interface LRUCache<T> {
    T get(String key);

    void set(String key, T value);
}
