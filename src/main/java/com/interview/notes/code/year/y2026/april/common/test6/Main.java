package com.interview.notes.code.year.y2026.april.common.test6;// Imports standard HashMap to store our fast-lookup references.
import java.util.HashMap;
// Imports Map interface for abstraction.
import java.util.Map;

// Defines our custom LRU Cache class.
class CustomLruCache<K, V> {
    
    // The maximum capacity limit for our cache.
    private final int capacity;
    // A HashMap that instantly points a Key to a specific Node in our linked list.
    private final Map<K, Node> map = new HashMap<>();
    // A dummy head node marking the absolute front of the line (most recently used).
    private final Node head = new Node(null, null);
    // A dummy tail node marking the absolute back of the line (least recently used).
    private final Node tail = new Node(null, null);
    // Constructor to set up the cache's capacity and link our dummy nodes together.
    public CustomLruCache(int capacity) {
        // Sets the size limit.
        this.capacity = capacity;
        // Connects the head directly to the tail.
        head.next = tail;
        // Connects the tail directly back to the head, completing our empty list boundary.
        tail.prev = head;
    }

    // --- THE INTERVIEWER'S SPECIFIC QUESTION: THE REMOVE FUNCTION ---
    // Helper method to detach a node from the linked list by manipulating its neighbors' pointers.
    private void remove(Node node) {
        // Tells the node sitting BEFORE our target to skip our target and point to the node AFTER it.
        node.prev.next = node.next;
        // Tells the node sitting AFTER our target to skip our target and point back to the node BEFORE it.
        node.next.prev = node.prev;
        // The node is now completely disconnected from the chain in exactly O(1) time.
    }

    // Helper method to always insert a node at the absolute front of the list (right after the dummy head).
    private void insertAtHead(Node node) {
        // Makes our new node point forward to whatever the head was previously pointing to.
        node.next = head.next;
        // Makes our new node point backward to the head.
        node.prev = head;
        // Updates the old first element to point backward to our new node.
        head.next.prev = node;
        // Finally, updates the head to point forward to our new node.
        head.next = node;
    }

    // Retrieves a value and marks it as recently used.
    public V get(K key) {
        // Looks up the memory address of the Node instantly using the HashMap.
        var node = map.get(key);
        // If the key doesn't exist, we return null.
        if (node == null) return null;

        // Disconnects the node from its current spot in the list.
        remove(node);
        // Plugs the exact same node right back into the very front (marking it as newest).
        insertAtHead(node);

        // Returns the actual value stored inside the node.
        return node.value;
    }

    // Adds a new item or updates an existing one, managing evictions if full.
    public void put(K key, V value) {
        // Checks if the item already exists in our cache.
        var node = map.get(key);

        // If it DOES exist...
        if (node != null) {
            // ...we just update its value.
            node.value = value;
            // Detach it from its old spot.
            remove(node);
            // Move it to the very front to mark it as the most recently used.
            insertAtHead(node);
        } else { // If it DOES NOT exist...
            // ...we create a brand new Node object.
            var newNode = new Node(key, value);
            // We save a fast-lookup reference to it in our HashMap.
            map.put(key, newNode);
            // We plug it into the very front of our linked list.
            insertAtHead(newNode);

            // We check if adding this new item pushed us over our capacity limit.
            if (map.size() > capacity) {
                // The item right before the dummy tail is guaranteed to be the oldest (Least Recently Used).
                var lruNode = tail.prev;
                // We disconnect the oldest item from the list.
                remove(lruNode);
                // We also delete its fast-lookup reference from the HashMap. (This is why we stored 'key' inside the Node).
                map.remove(lruNode.key);
            }
        }
    }

    // An internal class representing a single building block (Node) in our doubly linked list.
    class Node {
        // The key used to find the node. We store it here so we know what to delete from the HashMap during eviction.
        K key;
        // The actual value being stored.
        V value;
        // A pointer to the node physically sitting before this one.
        Node prev;
        // A pointer to the node physically sitting after this one.
        Node next;

        // Constructor to easily create a new Node with data.
        Node(K k, V v) { key = k; value = v; }
    }
}

// Main testing class.
public class Main {
    
    // Program entry point.
    public static void main(String[] args) {
        
        // Initialize custom cache with a capacity of 3.
        var cache = new CustomLruCache<String, Integer>(3);
        
        // Populate cache.
        cache.put("x", 10);
        cache.put("y", 20);
        cache.put("z", 30);
        
        // Test 1: Retrieve 'x', moving it to the front. List is now: y (oldest) -> z -> x (newest).
        check(cache.get("x") != null && cache.get("x") == 10, "Test 1: 'x' moved to newest position");
        
        // Add 'w'. Capacity is 3, so the oldest item ('y') must be evicted.
        cache.put("w", 40);
        
        // Test 2: 'y' should be gone.
        check(cache.get("y") == null, "Test 2: 'y' correctly evicted (Custom LRU)");
        
        // Test 3: 'x' should still be here because we accessed it.
        check(cache.get("x") != null && cache.get("x") == 10, "Test 3: 'x' survived due to recent access");
        
        // Large data test block.
        var largeCache = new CustomLruCache<Integer, String>(100);
        
        // Push 100 items.
        for (var i = 0; i < 100; i++) {
            largeCache.put(i, "data" + i);
        }
        
        // Read item 0 to save it.
        largeCache.get(0);
        // Add item 100 to trigger one eviction. (Item 1 is now the oldest).
        largeCache.put(100, "data100");
        
        // Test 4: Item 1 was evicted.
        check(largeCache.get(1) == null, "Test 4: Item 1 evicted from massive dataset");
        
        // Test 5: Item 0 survived.
        check("data0".equals(largeCache.get(0)), "Test 5: Item 0 survived in massive dataset");
    }

    // Custom assertion method.
    private static void check(boolean condition, String testName) {
        if (condition) {
            System.out.println("PASS: " + testName);
        } else {
            System.out.println("FAIL: " + testName);
        }
    }
}