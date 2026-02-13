package com.interview.notes.code.year.y2026.feb.common.test4;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

// Main class defining the Store and the Test Runner
public class KVStore {

    // The core storage engine. We use ConcurrentHashMap because it is thread-safe and fast.
    // 'final' ensures this map instance is never replaced, preventing data loss.
    private final Map<String, String> store = new ConcurrentHashMap<>();

    // --- MAIN METHOD (TEST RUNNER) ---
    public static void main(String[] args) {
        // Java 21 'var' reduces verbosity. Creating the store instance.
        var db = new KVStore();

        System.out.println("--- Starting Tests ---");

        // --- Test 1: Basic Set and Get ---
        db.set("name", "Yogesh"); // Setting a simple key-value pair
        var result1 = db.get("name"); // Retrieving the value
        // Check if the retrieved value matches what we put in.
        check("Basic Set/Get", "Yogesh".equals(result1));

        // --- Test 2: Update Existing Key ---
        db.set("name", "Soni"); // Overwriting the previous value "Yogesh" with "Soni"
        var result2 = db.get("name"); // Retrieving the new value
        // Check if the value was actually updated.
        check("Update Value", "Soni".equals(result2));

        // --- Test 3: Delete Key ---
        db.delete("name"); // Deleting the key "name"
        var result3 = db.get("name"); // Trying to get it again
        // Check if the result is null (meaning it's gone).
        check("Delete Key", result3 == null);

        // --- Test 4: Handle Non-Existent Key ---
        var result4 = db.get("ghost"); // Asking for a key that was never set
        // Check if it correctly returns null.
        check("Non-Existent Key", result4 == null);

        // --- Test 5: Large Data Volume (Performance) ---
        System.out.println("\nRunning Large Data Test (100,000 records)...");
        long start = System.currentTimeMillis(); // Start the timer

        // Using Java 8 Streams to insert 100,000 records rapidly.
        // This simulates a heavy load on the server.
        IntStream.range(0, 100_000).forEach(i -> {
            db.set("user_" + i, "data_" + i); // Generating unique keys and values
        });

        long end = System.currentTimeMillis(); // Stop the timer

        // Verify we have exactly 100,000 records stored.
        boolean sizeOk = db.size() == 100_000;
        // Verify a random record exists to ensure data integrity.
        boolean dataOk = "data_99999".equals(db.get("user_99999"));

        // Print Pass/Fail based on size and data accuracy.
        check("Large Data Integrity", sizeOk && dataOk);
        // Print how long it took (usually < 100ms).
        System.out.println("Time taken: " + (end - start) + "ms");

        System.out.println("--- All Tests Completed ---");
    }

    // Simple helper method to print PASS or FAIL.
    // This replaces complex JUnit frameworks for simple main-method testing.
    private static void check(String testName, boolean passed) {
        if (passed) {
            // Prints [PASS] followed by test name
            System.out.println("[PASS] " + testName);
        } else {
            // Prints [FAIL] followed by test name so we can spot errors
            System.out.println("[FAIL] " + testName);
        }
    }

    // Requirement 1: Method to set a key-value pair.
    public void set(String key, String value) {
        // Validating input prevents errors; we don't allow null keys.
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        // Puts the value into the map. If key exists, it overwrites (updates) it.
        store.put(key, value);
    }

    // Requirement 2: Method to get a value by key.
    public String get(String key) {
        // Returns the value associated with the key, or null if the key doesn't exist.
        return store.get(key);
    }

    // Requirement 3: Method to delete a key-value pair.
    public void delete(String key) {
        // Removes the mapping for this key from the map immediately.
        store.remove(key);
    }

    // Helper method to check current size, useful for verifying large data tests.
    public int size() {
        return store.size(); // Returns the count of items in the map
    }
}