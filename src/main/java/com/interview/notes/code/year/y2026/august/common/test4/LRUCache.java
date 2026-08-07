package com.interview.notes.code.year.y2026.august.common.test4;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class LRUCache<K, V> {
    
    private final int capacity;
    private final ConcurrentHashMap<K, Node> map;
    private final ReentrantLock lock = new ReentrantLock();
    // Dummy head and tail to avoid null checks during structural modifications
    private final Node head = new Node(null, null);
    private final Node tail = new Node(null, null);
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new ConcurrentHashMap<>(capacity);
        head.next = tail;
        tail.prev = head;
    }

    public V get(K key) {
        Node node = map.get(key); // Lock-free read from ConcurrentHashMap
        if (node == null) return null;

        lock.lock();
        try {
            moveToHead(node);
        } finally {
            lock.unlock();
        }
        return node.value;
    }

    public void put(K key, V value) {
        Node node = map.get(key); // Lock-free check

        lock.lock();
        try {
            if (node != null) {
                node.value = value;
                moveToHead(node);
            } else {
                Node newNode = new Node(key, value);
                map.put(key, newNode);
                addToHead(newNode);

                if (map.size() > capacity) {
                    Node lru = removeTail();
                    map.remove(lru.key);
                }
            }
        } finally {
            lock.unlock();
        }
    }

    private void addToHead(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    // --- DLL Helper Methods (Must be called within lock boundary) ---

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }

    private Node removeTail() {
        Node lru = tail.prev;
        removeNode(lru);
        return lru;
    }

    private class Node {
        K key;
        V value;
        Node prev, next;
        Node(K key, V value) { this.key = key; this.value = value; }
    }
}