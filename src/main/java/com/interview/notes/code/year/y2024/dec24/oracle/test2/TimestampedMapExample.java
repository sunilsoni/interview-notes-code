package com.interview.notes.code.year.y2024.dec24.oracle.test2;

import java.util.HashMap;
import java.util.Map;

public class TimestampedMapExample {

    // Main method for testing
    public static void main(String[] args) {
        TimestampedMap<String, String> tMap = new TimestampedMap<>();

        // Test 1: Putting and getting a value
        tMap.put("key1", "value1");
        String val = tMap.get("key1");
        long ts = tMap.getTimestamp("key1");

        System.out.println("Test 1: Put and Get");
        System.out.println("Value: " + val + " (Expected: value1)");
        System.out.println("Timestamp: " + ts + " (Expected: a non-negative timestamp)");
        System.out.println((val != null && val.equals("value1") && ts > 0) ? "PASS" : "FAIL");
        System.out.println();

        // Test 2: Updating an existing key
        long oldTs = tMap.getTimestamp("key1");
        try {
            Thread.sleep(10); // ensure time passes
        } catch (InterruptedException ignored) {
        }
        tMap.put("key1", "value2");
        long newTs = tMap.getTimestamp("key1");
        val = tMap.get("key1");

        System.out.println("Test 2: Update Existing Key");
        System.out.println("Value: " + val + " (Expected: value2)");
        System.out.println("Old Timestamp: " + oldTs + ", New Timestamp: " + newTs + " (Expected: New Timestamp > Old Timestamp)");
        System.out.println((val.equals("value2") && newTs > oldTs) ? "PASS" : "FAIL");
        System.out.println();

        // Test 3: Non-existent key
        val = tMap.get("noSuchKey");
        ts = tMap.getTimestamp("noSuchKey");

        System.out.println("Test 3: Non-existent Key");
        System.out.println("Value: " + val + " (Expected: null)");
        System.out.println("Timestamp: " + ts + " (Expected: -1)");
        System.out.println((val == null && ts == -1) ? "PASS" : "FAIL");
        System.out.println();

        // Test 4: Check containsKey and size
        boolean hasKey1 = tMap.containsKey("key1");
        boolean hasKey2 = tMap.containsKey("key2");
        int size = tMap.size();

        System.out.println("Test 4: ContainsKey and Size");
        System.out.println("Contains key1: " + hasKey1 + " (Expected: true)");
        System.out.println("Contains key2: " + hasKey2 + " (Expected: false)");
        System.out.println("Size: " + size + " (Expected: 1)");
        System.out.println((hasKey1 && !hasKey2 && size == 1) ? "PASS" : "FAIL");
        System.out.println();

        // Test 5: Large Data Input (Performance Check)
        // Note: This is a basic large data test, just to ensure no crashes or major slowdowns.
        // We won't print all checks for performance reasons, but we will time the operation.

        System.out.println("Test 5: Large Data Input");
        TimestampedMap<Integer, Integer> largeMap = new TimestampedMap<>();
        int testSize = 100000; // adjust as needed for performance testing
        long start = System.currentTimeMillis();
        for (int i = 0; i < testSize; i++) {
            largeMap.put(i, i);
        }
        long end = System.currentTimeMillis();
        boolean correctValues = true;
        for (int i = 0; i < 10; i++) {
            Integer retrieved = largeMap.get(i);
            if (retrieved == null || retrieved != i) {
                correctValues = false;
                break;
            }
        }
        System.out.println("Inserted " + testSize + " items in " + (end - start) + " ms");
        System.out.println("Spot-check correct values (first 10 entries): " + correctValues);
        System.out.println((largeMap.size() == testSize && correctValues) ? "PASS" : "FAIL");
    }

    // Inner class to hold value and timestamp
    private static class Entry<V> {
        private final V value;
        private final long timestamp;

        Entry(V value, long timestamp) {
            this.value = value;
            this.timestamp = timestamp;
        }
    }

    public static class TimestampedMap<K, V> {
        private final Map<K, Entry<V>> map;

        public TimestampedMap() {
            this.map = new HashMap<>();
        }

        /**
         * Stores the value for the given key along with the current timestamp.
         */
        public void put(K key, V value) {
            long currentTime = System.currentTimeMillis();
            map.put(key, new Entry<>(value, currentTime));
        }

        /**
         * Retrieves the value associated with the given key, or null if not found.
         */
        public V get(K key) {
            Entry<V> entry = map.get(key);
            return (entry != null) ? entry.value : null;
        }

        /**
         * Retrieves the timestamp associated with the given key, or -1 if not found.
         */
        public long getTimestamp(K key) {
            Entry<V> entry = map.get(key);
            return (entry != null) ? entry.timestamp : -1;
        }

        /**
         * Checks if the map contains the given key.
         */
        public boolean containsKey(K key) {
            return map.containsKey(key);
        }

        /**
         * Returns the size of the map.
         */
        public int size() {
            return map.size();
        }
    }
}
