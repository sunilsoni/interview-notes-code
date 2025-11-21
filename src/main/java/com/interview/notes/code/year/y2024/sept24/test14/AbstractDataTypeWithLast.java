package com.interview.notes.code.year.y2024.sept24.test14;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class AbstractDataTypeWithLast<K, V> {
    private final LinkedHashMap<K, V> map;
    private final Deque<K> accessStack;  // Stack to track access order
    private K lastKey;

    public AbstractDataTypeWithLast() {
        this.map = new LinkedHashMap<>();
        this.accessStack = new ArrayDeque<>();
        this.lastKey = null;
    }

    public void put(K key, V value) {
        map.put(key, value);
        updateAccessOrder(key);
    }

    public V get(K key) {
        V value = map.get(key);
        if (value != null) {
            updateAccessOrder(key);
        }
        return value;
    }

    public void remove(K key) {
        if (map.containsKey(key)) {
            map.remove(key);
            accessStack.remove(key);  // Remove the key from the access stack
            updateLastKeyAfterRemoval();
        }
    }

    public K last() {
        return lastKey;
    }

    public K previousLast() {
        if (accessStack.size() < 2) {
            return null;  // No previous key if less than 2 keys exist
        }
        Iterator<K> iterator = accessStack.descendingIterator();
        iterator.next();  // Skip the current last key
        return iterator.next();  // Return the previous key
    }

    private void updateAccessOrder(K key) {
        accessStack.remove(key);  // Ensure the key is not duplicated in the stack
        accessStack.addLast(key);  // Push the key to the top of the stack
        lastKey = key;  // Update the last accessed key
    }

    private void updateLastKeyAfterRemoval() {
        if (accessStack.isEmpty()) {
            lastKey = null;
        } else {
            lastKey = accessStack.peekLast();  // Set the new last key
        }
    }
}
