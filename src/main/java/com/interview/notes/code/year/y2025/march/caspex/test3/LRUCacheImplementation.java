package com.interview.notes.code.year.y2025.march.caspex.test3;

import java.util.*;
/*

### **Problem Statement: LRU Cache Implementation**
Design a data structure that follows the constraints of an **LRU (Least Recently Used) cache**.

#### **Operations Supported**
1. **LRUCache(int capacity)** → Initialize the LRU cache with a positive capacity.
2. **int get(int key)** → Return the value of the key if it exists, otherwise return `-1`.
3. **void put(int key, int value)** →
   - Update the value of the key if the key exists.
   - Otherwise, add the key-value pair to the cache.
   - If the cache exceeds capacity, **evict the least recently used key**.

#### **Notes**
- Any key accessed via `get()` or `put()` is considered **recently used**.

---

### **Input Format**
1. First line → Integer `N` → capacity of the cache.
2. Second line → Integer `M` → number of operations.
3. Third line → `M` space-separated operations:
   - `GET, x` → Get the value of key `x`.
   - `PUT, x, y` → Insert/Update key `x` with value `y`.

---

### **Expected Output**
- An array of values **returned by the GET operations**.

---

### **Example 1**
#### **Input**
```
2
6
GET,2 PUT,1,100 PUT,2,125 PUT,3,150 GET,1 GET,3
```
#### **Output**
```
-1 -1 150
```
#### **Explanation**
1. `GET,2` → Cache is empty, return `-1`.
2. `PUT,1,100` → Insert (1,100) → `[ (1,100) ]`
3. `PUT,2,125` → Insert (2,125) → `[ (1,100), (2,125) ]`
4. `PUT,3,150` → Cache is full, remove LRU (1,100), insert (3,150) → `[ (2,125), (3,150) ]`
5. `GET,1` → Not in cache, return `-1`.
6. `GET,3` → Exists, return `150`.

---

### **Example 2**
#### **Input**
```
3
5
PUT,11,25 PUT,22,50 PUT,11,75 GET,11 GET,22
```
#### **Output**
```
75 50
```
#### **Explanation**
1. `PUT,11,25` → Insert (11,25) → `[ (11,25) ]`
2. `PUT,22,50` → Insert (22,50) → `[ (11,25), (22,50) ]`
3. `PUT,11,75` → Update (11,75) → `[ (22,50), (11,75) ]`
4. `GET,11` → Return `75`
5. `GET,22` → Return `50`

 */

/**
 * LRUCacheImplementation demonstrates an LRU cache using a LinkedHashMap.
 * The cache supports GET and PUT operations while evicting the least recently used
 * entry when capacity is exceeded.
 */
public class LRUCacheImplementation {

    /**
     * The solve method processes a list of operations on the LRU Cache.
     * It returns a list of integer results for the GET operations.
     *
     * @param capacity   Maximum cache capacity.
     * @param operations List of operations in the format "GET,x" or "PUT,x,y".
     * @return List of results from GET operations.
     */
    public static List<Integer> solve(int capacity, List<String> operations) {
        LRUCache cache = new LRUCache(capacity);  // Create LRU Cache with the given capacity
        List<Integer> results = new ArrayList<>(); // List to store GET operation results

        // Process each operation in the given list
        for (String op : operations) {
            op = op.trim();  // Remove any extra spaces
            String[] parts = op.split(",");  // Split the string into parts by comma
            if (parts[0].equalsIgnoreCase("GET")) {  // Check if the operation is a GET
                int key = Integer.parseInt(parts[1].trim());  // Parse the key
                int value = cache.getValue(key);  // Retrieve the value from cache
                results.add(value);  // Store the result
            } else if (parts[0].equalsIgnoreCase("PUT")) {  // Check if the operation is a PUT
                int key = Integer.parseInt(parts[1].trim());  // Parse the key
                int value = Integer.parseInt(parts[2].trim());  // Parse the value
                cache.putValue(key, value);  // Insert or update the cache with the key-value pair
            }
        }
        return results;  // Return the list of GET results
    }

    /**
     * The runTests method creates and executes multiple test cases,
     * including edge cases and a large data input scenario.
     * It prints PASS/FAIL for each test.
     */
    public static void runTests() {
        List<TestCase> testCases = new ArrayList<>();

        // Test Case 1: Provided sample input 1
        testCases.add(new TestCase(
                2,
                Arrays.asList("GET,2", "PUT,1,100", "PUT,2,125", "PUT,3,150", "GET,1", "GET,3"),
                Arrays.asList(-1, -1, 150)
        ));

        // Test Case 2: Provided sample input 2
        testCases.add(new TestCase(
                3,
                Arrays.asList("PUT,11,25", "PUT,22,50", "PUT,11,75", "GET,11", "GET,22"),
                Arrays.asList(75, 50)
        ));

        // Test Case 3: Edge case where an update should reorder the cache
        testCases.add(new TestCase(
                2,
                Arrays.asList("PUT,1,10", "PUT,2,20", "GET,1", "PUT,3,30", "GET,2", "GET,3"),
                Arrays.asList(10, -1, 30)
        ));

        // Test Case 4: Large input test case to check performance and eviction logic
        int largeCapacity = 1000;
        List<String> largeOps = new ArrayList<>();
        // Insert 2000 PUT operations
        for (int i = 0; i < 2000; i++) {
            largeOps.add("PUT," + i + "," + (i * 10));
        }
        // Then, perform 2000 GET operations
        for (int i = 0; i < 2000; i++) {
            largeOps.add("GET," + i);
        }
        // Expected: Keys 0 to 999 should be evicted (-1), keys 1000 to 1999 should return i*10
        List<Integer> largeExpected = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            largeExpected.add(-1);
        }
        for (int i = 1000; i < 2000; i++) {
            largeExpected.add(i * 10);
        }
        testCases.add(new TestCase(largeCapacity, largeOps, largeExpected));

        // Iterate through each test case and compare actual vs expected outputs.
        int testNumber = 1;
        for (TestCase tc : testCases) {
            List<Integer> result = solve(tc.capacity, tc.operations);
            boolean pass = result.equals(tc.expected);  // Compare lists for equality
            System.out.println("Test case " + testNumber + ": " + (pass ? "PASS" : "FAIL"));
            if (!pass) {
                System.out.println("Expected: " + tc.expected);
                System.out.println("Got: " + result);
            }
            testNumber++;
        }
    }

    /**
     * Main method to run the test suite.
     * You can also process input from standard input if needed.
     */
    public static void main(String[] args) {
        // Run all test cases to verify correctness and performance.
        runTests();
    }

    /**
     * LRUCache class extends LinkedHashMap to leverage its access-ordering.
     * Using LinkedHashMap with 'accessOrder' flag ensures that any GET or PUT
     * operation moves the accessed entry to the end (most recently used).
     */
    static class LRUCache extends LinkedHashMap<Integer, Integer> {
        private int capacity;  // Maximum number of elements allowed in the cache

        /**
         * Constructor initializes the cache with a specific capacity.
         *
         * @param capacity the maximum number of elements
         */
        public LRUCache(int capacity) {
            // 'true' sets the map to access-order. The initial capacity and load factor are standard.
            super(capacity, 0.75f, true);
            this.capacity = capacity;
        }

        /**
         * This method is automatically called after each put() operation.
         * It determines whether the eldest entry should be removed.
         *
         * @param eldest the eldest entry in the map
         * @return true if the map size exceeds capacity (causing eviction)
         */
        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return size() > capacity;
        }

        /**
         * Retrieves the value for the given key.
         * If the key is not present, returns -1.
         *
         * @param key the key to retrieve
         * @return the value associated with the key or -1 if not found
         */
        public int getValue(int key) {
            return getOrDefault(key, -1);
        }

        /**
         * Inserts or updates the key with the specified value.
         *
         * @param key   the key to insert/update
         * @param value the value to associate with the key
         */
        public void putValue(int key, int value) {
            put(key, value);
        }
    }

    /**
     * Test case holder to group capacity, operations, and expected output.
     */
    static class TestCase {
        int capacity;
        List<String> operations;
        List<Integer> expected;

        public TestCase(int capacity, List<String> operations, List<Integer> expected) {
            this.capacity = capacity;
            this.operations = operations;
            this.expected = expected;
        }
    }
}