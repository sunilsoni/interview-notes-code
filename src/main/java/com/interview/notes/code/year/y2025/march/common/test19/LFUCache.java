package com.interview.notes.code.year.y2025.march.common.test19;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class LFUCache<K, V> {
    private final int capacity;
    private final Map<K, Node> keyToNode;
    private final Map<Integer, LinkedHashSet<Node>> freqToNodes;
    private int minFreq;

    // Constructor
    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFreq = 0;
        keyToNode = new HashMap<>();
        freqToNodes = new HashMap<>();
    }

    // Retrieve a value; update its frequency count
    public V getValue(K key) {
        if (!keyToNode.containsKey(key))
            return null;
        Node node = keyToNode.get(key);
        updateFrequency(node);
        return node.value;
    }

    // Insert or update a value; evict LFU if necessary
    public void putValue(K key, V value) {
        if (capacity <= 0)
            return;
        if (keyToNode.containsKey(key)) {
            Node node = keyToNode.get(key);
            node.value = value;
            updateFrequency(node);
        } else {
            if (keyToNode.size() >= capacity) {
                evictLFU();
            }
            Node newNode = new Node(key, value);
            keyToNode.put(key, newNode);
            freqToNodes.computeIfAbsent(1, ignore -> new LinkedHashSet<>()).add(newNode);
            minFreq = 1; // New node has frequency 1
        }
    }

    // Helper: update frequency of a node upon access or update
    private void updateFrequency(Node node) {
        int freq = node.freq;
        LinkedHashSet<Node> nodes = freqToNodes.get(freq);
        nodes.remove(node);
        // If this node was the only one with the current min frequency, increase minFreq
        if (freq == minFreq && nodes.isEmpty()) {
            minFreq++;
        }
        node.freq++;
        freqToNodes.computeIfAbsent(node.freq, ignore -> new LinkedHashSet<>()).add(node);
    }

    // Helper: evict the least frequently used node
    private void evictLFU() {
        LinkedHashSet<Node> nodes = freqToNodes.get(minFreq);
        // The LinkedHashSet maintains insertion order, so we remove the first inserted (least recently used among those with the same frequency)
        Node evict = nodes.iterator().next();
        nodes.remove(evict);
        keyToNode.remove(evict.key);
    }

    // For testing purposes: get the current size of the cache
    public int size() {
        return keyToNode.size();
    }

    // Node class to store key, value, and frequency
    private class Node {
        K key;
        V value;
        int freq;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.freq = 1;
        }
    }
}
