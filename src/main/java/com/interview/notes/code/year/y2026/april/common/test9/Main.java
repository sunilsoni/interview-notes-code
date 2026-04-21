package com.interview.notes.code.year.y2026.april.common.test9;// Imports the HashMap class to use as our underlying data structure for fast O(1) lookups.
import java.util.HashMap; 
// Imports the Map interface so we can code against an abstraction rather than a concrete implementation.
import java.util.Map; 

// Defines a generic Cache class where 'K' is the key type and 'V' is the value type, allowing it to store any data types.
class Cache<K, V> {
    
    // Initializes a HashMap to store our key-value pairs; 'final' ensures this memory reference cannot be accidentally overwritten.
    private final Map<K, V> store = new HashMap<>();

    // Method to insert or update a key-value pair in the cache.
    public void put(K key, V value) {
        // Uses the HashMap's built-in put method to store the data, which gives us highly efficient O(1) time complexity.
        store.put(key, value);
    }

    // Method to retrieve a value based on its key from the cache.
    public V get(K key) {
        // Uses the HashMap's built-in get method to fetch the data; it will return 'null' if the key does not exist.
        return store.get(key);
    }
}

// Main class to execute our program and run our test cases manually without needing external libraries like JUnit.
public class Main {
    
    // The main entry point of the Java application where execution begins.
    public static void main(String[] args) {
        
        // Creates a new Cache instance specifically for String keys and Integer values. 'var' is used to let Java infer the type automatically.
        var cache = new Cache<String, Integer>();
        
        // Test 1: Asserts that getting a key ("a") that hasn't been put into the cache yet correctly returns null.
        check(cache.get("a") == null, "Test 1: Get missing key 'a'");
        
        // Puts the key "a" with the integer value 2 into our cache.
        cache.put("a", 2);
        
        // Test 2: Asserts that retrieving "a" now correctly returns the value 2 (checking for null first to prevent crashes).
        check(cache.get("a") != null && cache.get("a") == 2, "Test 2: Put and Get key 'a'");
        
        // Test 3: Asserts that querying a completely different uninitialized key ("b") also returns null.
        check(cache.get("b") == null, "Test 3: Get missing key 'b'");
        
        // Puts the key "b" with the integer value 4 into our cache.
        cache.put("b", 4);
        
        // Test 4: Asserts that retrieving "b" correctly returns the value 4.
        check(cache.get("b") != null && cache.get("b") == 4, "Test 4: Put and Get key 'b'");
        
        // Creates a second Cache instance specifically designed to test a large volume of data (Integer keys, String values).
        var largeCache = new Cache<Integer, String>();
        
        // A boolean flag to track if our large dataset test passes or fails. We assume it passes until proven otherwise.
        var largePass = true;
        
        // Loops one million times to populate the cache, proving the cache can handle large inputs efficiently.
        for (var i = 0; i < 1000000; i++) {
            // Inserts entries dynamically, resulting in pairs like: (0, "val0"), (1, "val1"), etc.
            largeCache.put(i, "val" + i);
        }
        
        // Loops one million times again to verify that every single entry we just put in was stored correctly.
        for (var i = 0; i < 1000000; i++) {
            // Checks if the retrieved value from the cache matches the exact string we expected to build.
            if (!"val".concat(String.valueOf(i)).equals(largeCache.get(i))) {
                // If even one mismatch occurs, we mark the test as failed by flipping the flag.
                largePass = false;
                // We exit the loop early using 'break' since we already know the test failed, saving computer processing time.
                break;
            }
        }
        
        // Test 5: Asserts that the largePass flag remained true throughout all 1,000,000 verifications.
        check(largePass, "Test 5: Large data input (1,000,000 records)");
    }

    // A simple helper method to evaluate a true/false condition and print PASS or FAIL, acting as our custom testing framework.
    private static void check(boolean condition, String testName) {
        // Checks if the evaluated test condition passed as 'true'.
        if (condition) {
            // Prints a success message to the console if the condition was met.
            System.out.println("PASS: " + testName);
        } else {
            // Prints a failure message to the console if the condition was false.
            System.out.println("FAIL: " + testName);
        }
    }
}