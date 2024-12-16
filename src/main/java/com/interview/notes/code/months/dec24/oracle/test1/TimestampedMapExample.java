package com.interview.notes.code.months.dec24.oracle.test1;

import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class TimestampedMapExample {

    // Main method for simple tests
    public static void main(String[] args) {
        TimestampedMap tMap = new TimestampedMap();

        // Test 1: Basic put and get (most recent)
        tMap.put("key1", "value1", 1000L);
        String val = tMap.get("key1");
        Long ts = tMap.getTimestamp("key1");

        System.out.println("Test 1: Put and Get (most recent)");
        System.out.println("Value: " + val + " (Expected: value1)");
        System.out.println("Timestamp: " + ts + " (Expected: 1000)");
        System.out.println((val != null && val.equals("value1") && ts.equals(1000L)) ? "PASS" : "FAIL");
        System.out.println();

        // Test 2: Historical get
        // Add multiple timestamps for the same key
        tMap.put("key1", "value2", 2000L);
        tMap.put("key1", "value3", 3000L);

        // Check historical lookup
        String valAt1500 = tMap.get("key1", 1500L); // Should give value1 (since 1000L < 1500L < 2000L)
        String valAt2500 = tMap.get("key1", 2500L); // Should give value2
        String valAt3500 = tMap.get("key1", 3500L); // Should give value3 (latest)
        String valBefore1000 = tMap.get("key1", 500L); // Should be null, no entry before 1000L

        System.out.println("Test 2: Historical Get");
        System.out.println("Value at 1500: " + valAt1500 + " (Expected: value1)");
        System.out.println("Value at 2500: " + valAt2500 + " (Expected: value2)");
        System.out.println("Value at 3500: " + valAt3500 + " (Expected: value3)");
        System.out.println("Value at 500: " + valBefore1000 + " (Expected: null)");
        boolean test2Pass = "value1".equals(valAt1500) && "value2".equals(valAt2500)
                && "value3".equals(valAt3500) && valBefore1000 == null;
        System.out.println(test2Pass ? "PASS" : "FAIL");
        System.out.println();

        // Test 3: Non-existent key
        val = tMap.get("noSuchKey");
        Long notFoundTs = tMap.getTimestamp("noSuchKey");
        System.out.println("Test 3: Non-existent Key");
        System.out.println("Value: " + val + " (Expected: null)");
        System.out.println("Timestamp: " + notFoundTs + " (Expected: -1)");
        System.out.println((val == null && notFoundTs.equals(-1L)) ? "PASS" : "FAIL");
        System.out.println();

        // Test 4: ContainsKey and Size
        boolean hasKey1 = tMap.containsKey("key1");
        boolean hasKey2 = tMap.containsKey("key2");
        int size = tMap.size();

        System.out.println("Test 4: ContainsKey and Size");
        System.out.println("Contains key1: " + hasKey1 + " (Expected: true)");
        System.out.println("Contains key2: " + hasKey2 + " (Expected: false)");
        System.out.println("Size: " + size + " (Expected: 1 since only key1 is present)");
        System.out.println((hasKey1 && !hasKey2 && size == 1) ? "PASS" : "FAIL");
        System.out.println();

        // Test 5: Large Data Input (Performance Check)
        System.out.println("Test 5: Large Data Input");
        TimestampedMap largeMap = new TimestampedMap();
        int testSize = 100000; // adjust as needed for performance testing
        long start = System.currentTimeMillis();
        for (int i = 0; i < testSize; i++) {
            largeMap.put("key" + (i % 1000), "value" + i, (long) i);
        }
        long end = System.currentTimeMillis();

        // Spot check correctness on first 10 keys and timestamps
        boolean correctValues = true;
        for (int i = 0; i < 10; i++) {
            String retrieved = largeMap.get("key" + i, (long) (i * 100));
            // We know that for "keyX" we inserted multiple times.
            // We'll just check that retrieving at a timestamp we definitely inserted returns something non-null.
            // For simplicity, let's just ensure it's not null.
            if (retrieved == null) {
                correctValues = false;
                break;
            }
        }

        System.out.println("Inserted " + testSize + " versions in " + (end - start) + " ms");
        System.out.println("Spot-check first 10 keys: " + (correctValues ? "Values found" : "Some values missing"));
        System.out.println(correctValues ? "PASS" : "FAIL");
    }

    public static class TimestampedMap {
        // For each key, we store a TreeMap of (timestamp -> value)
        // TreeMap provides a sorted structure to allow historical queries
        private final Map<String, NavigableMap<Long, String>> map;

        public TimestampedMap() {
            this.map = new HashMap<>();
        }

        /**
         * Stores the value for the given key at the provided timestamp.
         * If the key already exists, it adds or overwrites the value at that specific timestamp.
         * Multiple timestamps per key can exist, representing historical states.
         *
         * @param key       The key to store
         * @param value     The value to store
         * @param timestamp The timestamp at which this value is valid
         */
        public void put(String key, String value, Long timestamp) {
            NavigableMap<Long, String> history = map.computeIfAbsent(key, k -> new TreeMap<>());
            history.put(timestamp, value);
        }

        /**
         * Retrieves the most recent value associated with the given key.
         * This means the value at the largest timestamp for that key.
         *
         * @param key The key to look up
         * @return The most recent value, or null if no entry exists
         */
        public String get(String key) {
            NavigableMap<Long, String> history = map.get(key);
            if (history == null || history.isEmpty()) return null;
            return history.lastEntry().getValue();
        }

        /**
         * Retrieves the value associated with the given key at or before the specified timestamp.
         * If there's no entry at or before this timestamp, returns null.
         *
         * @param key       The key to look up
         * @param timestamp The timestamp at which we want the value
         * @return The value at or before the given timestamp, or null if none exists
         */
        public String get(String key, Long timestamp) {
            NavigableMap<Long, String> history = map.get(key);
            if (history == null) return null;
            Map.Entry<Long, String> entry = history.floorEntry(timestamp);
            return (entry != null) ? entry.getValue() : null;
        }

        /**
         * Retrieves the latest timestamp associated with the given key.
         * If no entry exists for this key, return -1.
         *
         * @param key The key to look up
         * @return The latest timestamp, or -1 if no entry exists
         */
        public Long getTimestamp(String key) {
            NavigableMap<Long, String> history = map.get(key);
            if (history == null || history.isEmpty()) return -1L;
            return history.lastKey();
        }

        /**
         * Checks if the map contains the given key.
         *
         * @param key The key to check
         * @return true if the key is present, false otherwise
         */
        public boolean containsKey(String key) {
            return map.containsKey(key) && !map.get(key).isEmpty();
        }

        /**
         * Returns the number of keys stored in the map.
         *
         * @return the number of keys (not the number of versions)
         */
        public int size() {
            return (int) map.entrySet().stream().filter(e -> !e.getValue().isEmpty()).count();
        }
    }
}
