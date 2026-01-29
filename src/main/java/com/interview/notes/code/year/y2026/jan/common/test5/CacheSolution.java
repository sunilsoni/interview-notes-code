package com.interview.notes.code.year.y2026.jan.common.test5;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

// 1. Define the Customer object. 
// Using Java 'record' avoids writing getters/setters/constructors manually.
// It creates an immutable data carrier perfect for caching.
record Customer(String id, String name) {
}

// 2. The Cache Implementation
// We use a generic class <K, V> so it works for any key/value pair, not just Customers.
class MemoryCache<K, V> {

    // We use ConcurrentHashMap to handle high concurrency and large data safely.
    // 'final' ensures the map reference never changes, though contents can.
    private final Map<K, V> store = new ConcurrentHashMap<>();

    // Generic method to put items into cache.
    // Returns void as we don't need the previous value here.
    public void put(K key, V value) {
        store.put(key, value); // Stores the value associated with the key.
    }

    // Generic method to get items. 
    // Returns Optional<V> (Java 8 feature) to avoid NullPointerExceptions if key is missing.
    public Optional<V> get(K key) {
        // 'ofNullable' wraps the result. If null, it returns Optional.empty().
        return Optional.ofNullable(store.get(key));
    }

    // Helper to check cache size (useful for verifying large data tests).
    public int size() {
        return store.size(); // Returns current number of elements.
    }
}

// 3. Main Class for Testing
public class CacheSolution {

    public static void main(String[] args) {
        System.out.println("Starting Test Suite...\n");

        // --- Test Case 1: Basic Put and Get ---
        // Create an instance of our cache for Customer objects with String keys.
        var cache = new MemoryCache<String, Customer>();

        // Create a dummy customer using the record constructor.
        var customer1 = new Customer("C001", "Sandeep");

        // Store the customer in cache. Key is ID, Value is object.
        cache.put(customer1.id(), customer1);

        // Retrieve it immediately to check if it exists.
        var retrieved = cache.get("C001");

        // Check if value is present and matches the original name.
        if (retrieved.isPresent() && retrieved.get().name().equals("Sandeep")) {
            System.out.println("TC1 Basic Cache: PASS"); // Success case
        } else {
            System.err.println("TC1 Basic Cache: FAIL"); // Failure case
        }

        // --- Test Case 2: Handling Missing Keys ---
        // Try to get a key that was never added.
        var missing = cache.get("INVALID_ID");

        // If 'missing' is empty (isEmpty is available in newer Java versions), it passes.
        if (missing.isEmpty()) {
            System.out.println("TC2 Missing Key: PASS");
        } else {
            System.err.println("TC2 Missing Key: FAIL");
        }

        // --- Test Case 3: Overwriting Data (Update) ---
        // Create new data for the same ID.
        var customerUpdate = new Customer("C001", "Sandeep Updated");
        cache.put("C001", customerUpdate); // Overwrite previous entry.

        // Check if the name is now the updated one.
        if (cache.get("C001").get().name().equals("Sandeep Updated")) {
            System.out.println("TC3 Update Data: PASS");
        } else {
            System.err.println("TC3 Update Data: FAIL");
        }

        // --- Test Case 4: Large Data Performance (Load Test) ---
        // We will insert 100,000 records to test memory/speed.
        long startTime = System.currentTimeMillis(); // Start timer.

        // Use Java 8 Stream to generate 100k numbers and insert them.
        // IntStream.range creates a stream from 0 to 100,000.
        // .forEach iterates and puts data into cache.
        IntStream.range(0, 100_000).forEach(i -> {
            String key = "USER_" + i; // Generate unique key
            cache.put(key, new Customer(key, "User " + i)); // Store new customer
        });

        long endTime = System.currentTimeMillis(); // End timer.

        // Verify size is 100,001 (100k new + 1 from TC1).
        boolean sizeCheck = cache.size() == 100_001;
        // Verify we can retrieve the last item added.
        boolean retrieveCheck = cache.get("USER_99999").isPresent();

        if (sizeCheck && retrieveCheck) {
            System.out.println("TC4 Large Data (100k items): PASS (Time: " + (endTime - startTime) + "ms)");
        } else {
            System.err.println("TC4 Large Data: FAIL");
        }
    }
}