package com.interview.notes.code.year.y2025.august.common.test15;

import java.util.HashMap;

class LRUCache<K, V> {
    private final int capacity;
    private final HashMap<K, Node<K, V>> map;
    private final DoublyLinkedList<K, V> dll;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.dll = new DoublyLinkedList<>();
    }

    public V get(K key) {
        if (!map.containsKey(key)) return null;
        Node<K, V> node = map.get(key);
        dll.moveToHead(node); // mark as most recently used
        return node.value;
    }

    public void put(K key, V value) {
        if (map.containsKey(key)) {
            Node<K, V> node = map.get(key);
            node.value = value;   // update value
            dll.moveToHead(node);
        } else {
            if (map.size() == capacity) {
                Node<K, V> lru = dll.removeTail(); // remove least used
                map.remove(lru.key);
            }
            Node<K, V> newNode = new Node<>(key, value);
            dll.addToHead(newNode);
            map.put(key, newNode);
        }
    }

    // Node for DLL
    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> prev, next;
        Node(K k, V v) { key = k; value = v; }
    }

    // Doubly Linked List
    private static class DoublyLinkedList<K, V> {
        private final Node<K, V> head, tail;

        DoublyLinkedList() {
            head = new Node<>(null, null);
            tail = new Node<>(null, null);
            head.next = tail;
            tail.prev = head;
        }

        void addToHead(Node<K, V> node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        void moveToHead(Node<K, V> node) {
            removeNode(node);
            addToHead(node);
        }

        void removeNode(Node<K, V> node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        Node<K, V> removeTail() {
            Node<K, V> lru = tail.prev;
            removeNode(lru);
            return lru;
        }
    }

    // Demo
    public static void main(String[] args) {
        LRUCache<Integer, String> cache = new LRUCache<>(3);
        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");
        System.out.println(cache.get(1)); // A (moves 1 to most recent)
        cache.put(4, "D"); // Evicts key 2 (least recently used)
        System.out.println(cache.get(2)); // null
        System.out.println(cache.get(3)); // C
        System.out.println(cache.get(4)); // D
    }
}
