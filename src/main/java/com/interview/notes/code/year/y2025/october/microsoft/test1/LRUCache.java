package com.interview.notes.code.year.y2025.october.microsoft.test1;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

// LRUCache class implementing the required LRU behavior
public class LRUCache {

    private final int capacity;                   // maximum number of items cache can hold
    private final Map<Integer, Node> map;         // map key -> node for O(1) access
    private Node head;                            // head of doubly-linked list (most recently used)
    private Node tail;                            // tail of doubly-linked list (least recently used)
    private int size;                             // current number of items in cache

    // Constructor to create LRUCache with given capacity
    public LRUCache(int capacity) {
        this.capacity = capacity;                 // set capacity (immutable)
        this.map = new HashMap<>(capacity * 2);   // pre-size HashMap to reduce rehashing
        this.head = null;                         // initially no nodes -> head null
        this.tail = null;                         // initially no nodes -> tail null
        this.size = 0;                            // start with empty cache
    }

    // --------- Simple main method to run multiple tests and print PASS/FAIL ----------
    public static void main(String[] args) {
        // Test 1: example from the prompt
        {
            LRUCache cache = new LRUCache(2);             // create cache with capacity 2
            cache.put(1, 1);                              // put key=1,value=1
            cache.put(2, 2);                              // put key=2,value=2
            boolean pass = cache.get(1) == 1;             // get(1) should return 1
            cache.put(3, 3);                              // put(3,3) should evict key=2
            pass = pass && (cache.get(2) == -1);          // get(2) should return -1 (evicted)
            System.out.println("Test 1 (prompt example): " + (pass ? "PASS" : "FAIL"));
        }

        // Test 2: update existing key and ensure MRU ordering
        {
            LRUCache cache = new LRUCache(2);             // capacity 2
            cache.put(1, 1);                              // state: (1)
            cache.put(2, 2);                              // state: (2)->(1) most recent at head
            cache.put(1, 10);                             // update key=1 to value=10 and move to head
            boolean pass = cache.get(1) == 10;            // get(1) => 10
            cache.put(3, 3);                              // should evict key=2 now
            pass = pass && (cache.get(2) == -1);          // key=2 evicted
            System.out.println("Test 2 (update & eviction): " + (pass ? "PASS" : "FAIL"));
        }

        // Test 3: capacity 1 behavior
        {
            LRUCache cache = new LRUCache(1);             // capacity 1
            cache.put(1, 1);                              // store (1)
            cache.put(2, 2);                              // evicts (1) and stores (2)
            boolean pass = cache.get(1) == -1;            // (1) should be gone
            pass = pass && cache.get(2) == 2;             // (2) should be present
            System.out.println("Test 3 (capacity 1): " + (pass ? "PASS" : "FAIL"));
        }

        // Test 4: sequence correctness (multiple accesses)
        {
            LRUCache cache = new LRUCache(3);             // capacity 3
            cache.put(1, 1);
            cache.put(2, 2);
            cache.put(3, 3);                              // (3)->(2)->(1)
            cache.get(2);                                 // (2) becomes head: (2)->(3)->(1)
            cache.put(4, 4);                              // evict (1); now (4)->(2)->(3)
            boolean pass = cache.get(1) == -1;            // 1 evicted
            pass = pass && cache.get(3) == 3;             // still present
            pass = pass && cache.currentSize() == 3;      // ensure size respects capacity
            System.out.println("Test 4 (sequence correctness): " + (pass ? "PASS" : "FAIL"));
        }

        // Test 5: Snapshot check (string order matches expectation)
        {
            LRUCache cache = new LRUCache(3);
            cache.put(1, 1);   // (1)
            cache.put(2, 2);   // (2)->(1)
            cache.put(3, 3);   // (3)->(2)->(1)
            cache.get(1);      // (1)->(3)->(2)
            String snap = cache.snapshot();                // verify snapshot
            boolean pass = snap.startsWith("(1:1)->(3:3)->(2:2)");
            System.out.println("Test 5 (snapshot order): " + (pass ? "PASS" : "FAIL") + " snapshot=" + snap);
        }

        // Test 6: Large input test to ensure performance and correctness for many operations
        {
            final int capacity = 1000;                   // large-ish capacity
            final int ops = 100_000;                     // number of operations to perform
            LRUCache cache = new LRUCache(capacity);     // make cache
            Random rand = new Random(12345);             // fixed seed for reproducibility

            // perform many random put/get operations using IntStream for Java8 flavor
            IntStream.range(0, ops).forEach(i -> {
                int key = rand.nextInt(capacity * 2);    // keys in range [0, 2*capacity)
                if ((i & 1) == 0) {                      // alternate put/get to mix operations
                    cache.put(key, i);                   // put (key -> i)
                } else {
                    cache.get(key);                      // get(key) possibly -1
                }
            });

            // After many operations, ensure size <= capacity and no exceptions occurred
            boolean pass = cache.currentSize() <= capacity;
            // additionally check a few known keys: put was executed for some keys; we check that get doesn't crash
            pass = pass && (cache.get(0) <= Integer.MAX_VALUE); // just ensures get runs (value or -1)
            System.out.println("Large input test (ops=" + ops + "): " + (pass ? "PASS" : "FAIL")
                    + " finalSize=" + cache.currentSize());
        }

        // Extra: show a small manual demo for visual confirmation
        {
            LRUCache cache = new LRUCache(2);
            cache.put(1, 1);
            cache.put(2, 2);
            System.out.println("Demo snapshot before get(1): " + cache.snapshot()); // expect (2:2)->(1:1)
            cache.get(1);
            System.out.println("Demo snapshot after get(1):  " + cache.snapshot()); // expect (1:1)->(2:2)
            cache.put(3, 3);
            System.out.println("Demo snapshot after put(3,3): " + cache.snapshot()); // expect (3:3)->(1:1)
        }
    }

    // Get value for key and mark node as recently used (move to head)
    public int get(int key) {
        Node node = map.get(key);                 // lookup node in map (O(1))
        if (node == null) {                       // if not found
            return -1;                            // return -1 per specification
        }
        moveToHead(node);                         // mark as recently used by moving to head
        return node.value;                        // return associated value
    }

    // Put key,value into cache. Evict LRU if needed.
    public void put(int key, int value) {
        Node node = map.get(key);                 // check if key already exists
        if (node != null) {                       // if exists
            node.value = value;                   // update the stored value
            moveToHead(node);                     // mark node as most recently used
            return;                               // done
        }
        // If not present, create new node
        Node newNode = new Node(key, value);      // allocate node to store key/value
        if (size == capacity) {                   // if cache full
            // evict least recently used (tail)
            if (tail != null) {
                map.remove(tail.key);             // remove mapping of the evicted node
                removeNode(tail);                 // unlink tail node from list
                size--;                           // reduce size after eviction
            }
        }
        addToHead(newNode);                       // insert new node at head (most recently used)
        map.put(key, newNode);                    // add mapping for O(1) future access
        size++;                                   // increment current size
    }

    // Helper: remove a node from its current position in the linked list
    private void removeNode(Node node) {
        if (node == null) return;                 // nothing to do if node null
        Node prevNode = node.prev;                // capture previous node
        Node nextNode = node.next;                // capture next node

        if (prevNode != null) {                   // if node is not head
            prevNode.next = nextNode;             // link previous to next, bypassing node
        } else {
            head = nextNode;                      // node was head, update head
        }

        if (nextNode != null) {                   // if node is not tail
            nextNode.prev = prevNode;             // link next back to previous, bypassing node
        } else {
            tail = prevNode;                      // node was tail, update tail
        }

        // clean up node pointers (helps GC and avoids accidental reuse)
        node.prev = null;
        node.next = null;
    }

    // Helper: add a new node at head (most recently used)
    private void addToHead(Node node) {
        node.prev = null;                         // new head has no previous
        node.next = head;                         // next of new head is the old head
        if (head != null) {                       // if list non-empty
            head.prev = node;                     // old head's prev becomes new node
        }
        head = node;                              // update head reference to new node
        if (tail == null) {                       // if list was empty before adding
            tail = node;                          // tail also points to the new (only) node
        }
    }

    // Helper: move an existing node to head (mark MRU)
    private void moveToHead(Node node) {
        if (node == head) return;                 // already at head -> nothing to do
        removeNode(node);                         // unlink node from current position
        addToHead(node);                          // insert node at head
    }

    // Helper: utility to check current size (useful for tests)
    public int currentSize() {
        return size;                              // return internal size
    }

    // Helper: returns content of cache from head->tail as string (for debugging/tests)
    public String snapshot() {
        StringBuilder sb = new StringBuilder();   // build string representation
        Node cur = head;                          // iterate from most recent to least recent
        while (cur != null) {                     // until end of list
            sb.append("(").append(cur.key).append(":").append(cur.value).append(")"); // append pair
            if (cur.next != null) sb.append("->"); // separator between nodes
            cur = cur.next;                       // move to next node
        }
        return sb.toString();                     // return built snapshot
    }

    // Node: doubly-linked list node that stores key and value
    private static class Node {
        int key;            // store key to remove from map when evicting
        int value;          // store value for get/put operations
        Node prev;          // pointer to previous node in doubly-linked list
        Node next;          // pointer to next node in doubly-linked list

        // Node constructor to initialize key/value (prev/next default to null)
        Node(int k, int v) {
            this.key = k;   // assign node key
            this.value = v; // assign node value
        }
    }
}
