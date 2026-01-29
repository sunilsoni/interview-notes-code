package com.interview.notes.code.year.y2026.jan.microsoft.test7;

import java.util.HashMap;
import java.util.Map;

// <K, V> means "This class works with ANY Key type and ANY Value type"
public class LRUCache<K, V> {

    private final int capacity;
    // Map connects the Generic Key -> Generic Node
    private final Map<K, Node<K, V>> map;
    private final Node<K, V> head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();

        // Initialize dummy head/tail with null data
        // We use null because their data doesn't matter, they are just markers
        this.head = new Node<>(null, null);
        this.tail = new Node<>(null, null);

        head.next = tail;
        tail.prev = head;
    }

    // Test with Strings and Integers
    public static void main(String[] args) {
        // Example: Cache mapping UserNames (String) to IDs (Integer)
        LRUCache<String, Integer> cache = new LRUCache<>(2);

        cache.put("Alice", 101);
        cache.put("Bob", 102);
        System.out.println(cache.get("Alice")); // Returns 101

        cache.put("Charlie", 103); // Evicts Bob (Alice was just used)
        System.out.println(cache.get("Bob")); // Returns null (Evicted)
    }

    private void addToHead(Node<K, V> node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node<K, V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // Returns V (the generic value type) or null if not found
    public V get(K key) {
        if (!map.containsKey(key)) return null;

        var node = map.get(key);
        removeNode(node);
        addToHead(node);
        return node.val;
    }

    public void put(K key, V value) {
        if (map.containsKey(key)) {
            var node = map.get(key);
            node.val = value;
            removeNode(node);
            addToHead(node);
        } else {
            if (map.size() == capacity) {
                var lru = tail.prev;
                map.remove(lru.key); // We need the key here to remove from map
                removeNode(lru);
            }
            var newNode = new Node<>(key, value);
            map.put(key, newNode);
            addToHead(newNode);
        }
    }

    // Node is now generic too
    static class Node<K, V> {
        K key;
        V val;
        Node<K, V> prev, next;

        Node(K k, V v) {
            key = k;
            val = v;
        }
    }
}