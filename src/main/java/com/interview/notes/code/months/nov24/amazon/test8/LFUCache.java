package com.interview.notes.code.months.nov24.amazon.test8;

import java.util.HashMap;
import java.util.Map;

/*
Background:
Amazon's Product Catalog Service is a critical component that serves product information to various parts of the Amazon ecosystem, including the website, mobile apps, and internal tools. The service currently uses a simple LRU (Least Recently Used) cache to improve response times and reduce load on the backend databases. However, as the product catalog has grown and usage patterns have become more complex, the team has identified that the current caching strategy is not optimal for their use case.


 */
class LFUCache {
    private final int capacity;
    private int minFrequency;
    private final Map<Integer, Node> keyNodeMap;
    private final Map<Integer, DoublyLinkedList> freqListMap;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFrequency = 0;
        keyNodeMap = new HashMap<>();
        freqListMap = new HashMap<>();
    }

    public int get(int key) {
        if (!keyNodeMap.containsKey(key)) {
            return -1;
        }
        Node node = keyNodeMap.get(key);
        updateFrequency(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (capacity == 0) return;

        if (keyNodeMap.containsKey(key)) {
            Node node = keyNodeMap.get(key);
            node.value = value;
            updateFrequency(node);
        } else {
            if (keyNodeMap.size() >= capacity) {
                DoublyLinkedList minFreqList = freqListMap.get(minFrequency);
                Node toRemove = minFreqList.removeLast();
                keyNodeMap.remove(toRemove.key);
            }
            Node newNode = new Node(key, value);
            keyNodeMap.put(key, newNode);
            freqListMap.computeIfAbsent(1, k -> new DoublyLinkedList()).addFirst(newNode);
            minFrequency = 1;
        }
    }

    private void updateFrequency(Node node) {
        int freq = node.frequency;
        DoublyLinkedList oldList = freqListMap.get(freq);
        oldList.remove(node);

        if (freq == minFrequency && oldList.isEmpty()) {
            minFrequency++;
        }

        node.frequency++;
        freqListMap.computeIfAbsent(node.frequency, k -> new DoublyLinkedList()).addFirst(node);
    }

    // Node class representing each cache entry
    private static class Node {
        int key;
        int value;
        int frequency;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.frequency = 1;
        }
    }

    // Doubly linked list to hold nodes with the same frequency
    private static class DoublyLinkedList {
        Node head;
        Node tail;

        DoublyLinkedList() {
            head = new Node(-1, -1); // Dummy head
            tail = new Node(-1, -1); // Dummy tail
            head.next = tail;
            tail.prev = head;
        }

        void addFirst(Node node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        Node removeLast() {
            if (tail.prev == head) {
                return null;
            }
            Node last = tail.prev;
            remove(last);
            return last;
        }

        boolean isEmpty() {
            return head.next == tail;
        }
    }
}
