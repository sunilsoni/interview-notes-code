package com.interview.notes.code.year.y2024.march24.test9;

import java.util.HashMap;

class LRUCache {
    private final int capacity;
    private final HashMap<Integer, Node> cache;
    private final Node dummyHead;
    private final Node dummyTail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.dummyHead = new Node(-1, -1);
        this.dummyTail = new Node(-1, -1);
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
    }

    private void addToFront(Node node) {
        Node next = dummyHead.next;
        dummyHead.next = node;
        node.prev = dummyHead;
        node.next = next;
        next.prev = node;
    }

    private void removeNode(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            removeNode(node);
            addToFront(node);
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.value = value;
            removeNode(node);
            addToFront(node);
        } else {
            if (cache.size() == capacity) {
                Node toRemove = dummyTail.prev;
                removeNode(toRemove);
                cache.remove(toRemove.key);
            }
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            addToFront(newNode);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        System.out.println(lruCache.get(2)); // Output: -1
        lruCache.put(1, 100);
        lruCache.put(2, 125);
        System.out.println(lruCache.get(1)); // Output: 100
        lruCache.put(3, 150);
        System.out.println(lruCache.get(2)); // Output: -1
        System.out.println(lruCache.get(3)); // Output: 150
    }
}