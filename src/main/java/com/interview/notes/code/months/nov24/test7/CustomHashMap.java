package com.interview.notes.code.months.nov24.test7;

public class CustomHashMap<K, V> {
    // Default initial capacity of the hash map
    private static final int DEFAULT_CAPACITY = 16;
    // Load factor to determine when to resize the hash map
    private static final float LOAD_FACTOR = 0.75f;

    // Array of buckets to store linked lists of nodes (key-value pairs)
    private Node<K, V>[] buckets;
    private int size; // Number of key-value pairs in the hash map

    // Default constructor initializing with default capacity
    public CustomHashMap() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public CustomHashMap(int capacity) {
        // Initialize the buckets array with the given capacity
        buckets = (Node<K, V>[]) new Node[capacity];
        size = 0;
    }

    // Node class representing each entry in the hash map
    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next; // Reference to the next node in the same bucket

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    // Method to add a key-value pair to the hash map
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        // Resize the buckets array if the current size exceeds the load factor threshold
        if (size >= buckets.length * LOAD_FACTOR) {
            resize();
        }

        int index = getIndex(key); // Calculate the bucket index for the given key
        Node<K, V> newNode = new Node<>(key, value);

        // If the bucket is empty, place the new node there
        if (buckets[index] == null) {
            buckets[index] = newNode;
        } else {
            // Traverse the linked list in the bucket to check if the key already exists
            Node<K, V> current = buckets[index];
            Node<K, V> prev = null;

            while (current != null) {
                if (current.key.equals(key)) {
                    // If the key already exists, update the value
                    current.value = value;
                    return;
                }
                prev = current;
                current = current.next;
            }

            // If the key does not exist, add the new node at the end of the linked list
            prev.next = newNode;
        }

        size++; // Increment the size of the hash map
    }

    // Method to calculate the index for a given key using its hash code
    private int getIndex(K key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }

    // Method to resize the buckets array when the load factor threshold is exceeded
    @SuppressWarnings("unchecked")
    private void resize() {
        int newCapacity = buckets.length * 2; // Double the capacity of the buckets array
        Node<K, V>[] newBuckets = (Node<K, V>[]) new Node[newCapacity];

        // Rehash all the existing nodes to the new buckets array
        for (Node<K, V> bucket : buckets) {
            Node<K, V> current = bucket;
            while (current != null) {
                Node<K, V> next = current.next; // Store reference to the next node
                int newIndex = Math.abs(current.key.hashCode()) % newCapacity; // Calculate new index for the key
                current.next = newBuckets[newIndex]; // Insert node into the new bucket
                newBuckets[newIndex] = current;
                current = next; // Move to the next node in the old bucket
            }
        }

        buckets = newBuckets; // Update the buckets array reference
    }
}
