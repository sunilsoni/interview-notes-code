package com.interview.notes.code.year.y2024.april24.test1;

import java.util.HashMap;

class LRUCache {
    private final HashMap<Integer, DLinkedNode> cache = new HashMap<>();
    private int size;
    private final int capacity;
    private final DLinkedNode head;
    private final DLinkedNode tail;

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;

        head = new DLinkedNode();
        tail = new DLinkedNode();

        head.next = tail;
        tail.prev = head;
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2); // Capacity of 2.

        cache.put(1, 1); // Cache is {1=1}
        cache.put(2, 2); // Cache is {1=1, 2=2}
        System.out.println("Get 1: " + cache.get(1)); // Returns 1. Cache is {2=2, 1=1}

        cache.put(3, 3); // Evicts key 2. Cache is {1=1, 3=3}

        System.out.println("Get 2: " + cache.get(2)); // Returns -1 (not found)

        cache.put(4, 4); // Evicts key 1. Cache is {3=3, 4=4}

        System.out.println("Get 1: " + cache.get(1)); // Returns -1 (not found)
        System.out.println("Get 3: " + cache.get(3)); // Returns 3
        System.out.println("Get 4: " + cache.get(4)); // Returns 4
    }

    private void addNode(DLinkedNode node) {
        // Always add the new node right after head.
        node.prev = head;
        node.next = head.next;

        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(DLinkedNode node) {
        // Remove an existing node from the linked list.
        DLinkedNode prev = node.prev;
        DLinkedNode next = node.next;

        prev.next = next;
        next.prev = prev;
    }

    private void moveToHead(DLinkedNode node) {
        // Move certain node in between to the head.
        removeNode(node);
        addNode(node);
    }

    private DLinkedNode popTail() {
        // Pop the current tail.
        DLinkedNode res = tail.prev;
        removeNode(res);
        return res;
    }

    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) return -1;

        // Move the accessed node to the head.
        moveToHead(node);

        return node.value;
    }

    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);

        if (node == null) {
            DLinkedNode newNode = new DLinkedNode();
            newNode.key = key;
            newNode.value = value;

            cache.put(key, newNode);
            addNode(newNode);

            ++size;

            if (size > capacity) {
                // Pop the tail
                DLinkedNode tail = popTail();
                cache.remove(tail.key);
                --size;
            }
        } else {
            // Update the value.
            node.value = value;
            moveToHead(node);
        }
    }

    class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;
    }
}
