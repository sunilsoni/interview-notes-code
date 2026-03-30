package com.interview.notes.code.year.y2026.march.apple.test4;

import java.util.HashMap; // Needed to instantly find items without searching through a list.
import java.util.Map; // Defines the standard map structure we will use for storage.

public class Main { // The main container class required to run our Java program.

    public static void main(String[] args) { // Standard starting point for testing our logic.
        System.out.println("--- Starting Manual LRU Cache Tests ---"); // Console marker for readability.

        // --- TEST CASE 1: Basic Operations ---
        var cache = new LRUCache<Integer, String>(2); // Create a tiny cache of size 2.
        cache.put(1, "A"); // Insert item 1.
        cache.put(2, "B"); // Insert item 2 (cache is now 100% full).
        check("Get Key 1 (Marks recent)", cache.get(1), "A"); // Access item 1, forcing it to be the newest.

        cache.put(3, "C"); // Insert item 3, forcing the oldest item (Key 2) out.
        check("Eviction of Key 2", cache.get(2), null); // Prove Key 2 was deleted.
        check("Retention of Key 3", cache.get(3), "C"); // Prove Key 3 was saved.

        // --- TEST CASE 2: Large Data Input & Stress Testing ---
        var large = new LRUCache<Integer, Integer>(5000); // Create a massive cache holding 5000 items.
        for (int i = 0; i < 15000; i++) { // Run a loop inserting 15,000 unique items.
            large.put(i, i); // Continuously force out the oldest 10,000 items.
        }

        check("Large Data: Oldest Evicted", large.get(0), null); // Verify the very first item is long gone.
        check("Large Data: Newest Retained", large.get(14999), 14999); // Verify the absolute last item exists.
        check("Large Data: Middle Evicted", large.get(5000), null); // Verify an item just outside the boundary is gone.
    }

    // Custom tester method to validate output without heavy JUnit dependencies.
    private static <T> void check(String testName, T actual, T expected) { // Accepts any data type to compare expected vs actual.
        boolean passed = (expected == null && actual == null) || (expected != null && expected.equals(actual)); // Checks for exact matches.
        System.out.println(testName + " -> " + (passed ? "PASS" : "FAIL")); // Prints the result cleanly to the console.
    }

    // A custom "Node" represents each item; it connects to the items before and after it to form a chain (list).
    static class Node<K, V> { // Generics <K, V> allow this node to hold any type of key and value.
        K key; // We store the key inside the node so we know what to delete from the HashMap during eviction.
        V val; // This holds the actual data the user wants to cache.
        Node<K, V> prev, next; // Pointers linking to the newer (prev) and older (next) items in the sequence.

        Node(K k, V v) { // Constructor used to quickly package a key and value together.
            key = k; // Assigns the user's key to this node.
            val = v; // Assigns the user's value to this node.
        }
    }

    // Our manual LRU Cache implementation.
    static class LRUCache<K, V> { // Generics ensure the cache is reusable for any data types.
        private final int cap; // Stores the absolute maximum number of items allowed in the cache.
        private final Map<K, Node<K, V>> map = new HashMap<>(); // The map acting as our high-speed lookup directory.
        private final Node<K, V> head = new Node<>(null, null); // Dummy start node; newest items go right after this.
        private final Node<K, V> tail = new Node<>(null, null); // Dummy end node; oldest items sit right before this.

        public LRUCache(int cap) { // Setup method called when creating the cache.
            this.cap = cap; // Locks in the user's requested capacity size.
            head.next = tail; // Initially, the start of the list connects directly to the end.
            tail.prev = head; // Initially, the end of the list connects directly back to the start.
        }

        public V get(K key) { // Method to fetch an item.
            if (!map.containsKey(key)) return null; // If the map doesn't have the key, safely return nothing.
            var node = map.get(key); // Use Java 'var' to grab the exact node from our map.
            remove(node); // Pull the node out of its current place in the time sequence.
            insert(node); // Shove it right to the front (head) because it was just recently used.
            return node.val; // Hand the actual data back to the user.
        }

        public void put(K key, V val) { // Method to add or update an item.
            if (map.containsKey(key)) remove(map.get(key)); // If updating an old item, rip the old version out of the list first.
            var node = new Node<>(key, val); // Create a fresh node containing the new data.
            map.put(key, node); // Log the new node into our high-speed directory.
            insert(node); // Push this brand-new node to the front of our time sequence.

            if (map.size() > cap) { // Check if we just exceeded our strictly allowed capacity.
                var lru = tail.prev; // Find the absolute oldest item sitting right before the dummy tail.
                remove(lru); // Chop it out of our time sequence list permanently.
                map.remove(lru.key); // Erase it from the high-speed directory so it is completely gone.
            }
        }

        private void remove(Node<K, V> n) { // Internal helper to pluck a specific node out of the chain.
            n.prev.next = n.next; // Tell the node behind it to point forward to the node ahead of it.
            n.next.prev = n.prev; // Tell the node ahead of it to point backward to the node behind it.
        }

        private void insert(Node<K, V> n) { // Internal helper to jam a node right at the front of the chain.
            n.next = head.next; // Make the new node point forward to whatever used to be first.
            n.next.prev = n; // Make that old first item point backward to our new node.
            head.next = n; // Make the dummy start node point forward to our new node.
            n.prev = head; // Make our new node point backward to the dummy start node.
        }
    }
}