package com.interview.notes.code.year.y2025.july.common.test9;

import java.util.HashMap;
import java.util.Map;


// LRUCache implementation
public class LRUCache extends Cache {
    private final int capacity;
    private final Map<Integer, Node> cache;
    private final Node head;
    private final Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    // Main method with tests
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);  // capacity = 2

        // Test case 1: Basic write and get
        cache.write(1, 1);
        cache.write(2, 2);
        System.out.println(cache.get(1));  // 1
        System.out.println(cache.get(2));  // 2

        // Test case 2: Eviction
        cache.write(3, 3);                 // evicts key 1
        System.out.println(cache.get(1));  // -1
        System.out.println(cache.get(3));  // 3

        // Test case 3: Updating existing key
        cache.write(2, 4);                 // update key 2
        System.out.println(cache.get(2));  // 4

        // Test case 4: Get moves item to front
        cache.get(3);
        cache.write(4, 4);                 // evicts key 2
        System.out.println(cache.get(2));  // -1
        System.out.println(cache.get(3));  // 3
        System.out.println(cache.get(4));  // 4

        // Test case 5: Multiple gets & eviction
        System.out.println(cache.get(3));  // 3
        System.out.println(cache.get(4));  // 4
        cache.write(5, 5);                 // evicts key 3
        System.out.println(cache.get(3));  // -1
        System.out.println(cache.get(4));  // 4
        System.out.println(cache.get(5));  // 5
    }

    @Override
    void evictNode() {
        Node toRemove = tail.prev;
        removeNode(toRemove);
        cache.remove(toRemove.key);
    }

    @Override
    public void write(int key, int value) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.value = value;
            moveToHead(node);
        } else {
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            addNode(newNode);

            if (cache.size() > capacity) {
                evictNode();
            }
        }
    }

    @Override
    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        Node node = cache.get(key);
        moveToHead(node);
        return node.value;
    }

    private void addNode(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addNode(node);
    }

    // Doubly-linked list node
    private static class Node {
        int key;
        int value;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}