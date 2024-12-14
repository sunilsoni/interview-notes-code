package com.interview.notes.code.months.dec24.oracle.test3;

import java.util.HashMap;
import java.util.Map;

public class TimestampedMapExample {
    
    // Inner class to hold value and timestamp
    private static class Entry {
        private final String value;
        private final Long timestamp;
        
        Entry(String value, Long timestamp) {
            this.value = value;
            this.timestamp = timestamp;
        }
    }

    public static class TimestampedMap {
        private final Map<String, Entry> map;

        public TimestampedMap() {
            this.map = new HashMap<>();
        }

        /**
         * Stores the value for the given key along with the provided timestamp.
         * If the key already exists, it overwrites the existing value and timestamp.
         * 
         * @param key       The key to store
         * @param value     The value to store
         * @param timestamp The timestamp to associate with the key-value pair
         */
        public void put(String key, String value, Long timestamp) {
            map.put(key, new Entry(value, timestamp));
        }

        /**
         * Retrieves the value associated with the given key, or null if not found.
         * 
         * @param key The key to look up
         * @return The associated value, or null if no such key exists
         */
        public String get(String key) {
            Entry entry = map.get(key);
            return (entry != null) ? entry.value : null;
        }

        /**
         * Retrieves the timestamp associated with the given key, or -1 if not found.
         * 
         * @param key The key to look up
         * @return The associated timestamp, or -1 if no such key exists
         */
        public Long getTimestamp(String key) {
            Entry entry = map.get(key);
            return (entry != null) ? entry.timestamp : -1L;
        }

        /**
         * Checks if the map contains the given key.
         * 
         * @param key The key to check
         * @return true if the key is present, false otherwise
         */
        public boolean containsKey(String key) {
            return map.containsKey(key);
        }

        /**
         * Returns the size of the map.
         * 
         * @return the number of key-value pairs stored
         */
        public int size() {
            return map.size();
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        TimestampedMap tMap = new TimestampedMap();

        // Test 1: Put and Get
        tMap.put("key1", "value1", 1000L);
        String val = tMap.get("key1");
        Long ts = tMap.getTimestamp("key1");

        System.out.println("Test 1: Put and Get");
        System.out.println("Value: " + val + " (Expected: value1)");
        System.out.println("Timestamp: " + ts + " (Expected: 1000)");
        System.out.println((val != null && val.equals("value1") && ts.equals(1000L)) ? "PASS" : "FAIL");
        System.out.println();

        // Test 2: Updating an existing key
        Long oldTs = tMap.getTimestamp("key1");
        tMap.put("key1", "value2", 2000L);
        Long newTs = tMap.getTimestamp("key1");
        val = tMap.get("key1");

        System.out.println("Test 2: Update Existing Key");
        System.out.println("Value: " + val + " (Expected: value2)");
        System.out.println("Old Timestamp: " + oldTs + ", New Timestamp: " + newTs + " (Expected: 2000, and new > old)");
        System.out.println((val.equals("value2") && newTs.equals(2000L) && newTs > oldTs) ? "PASS" : "FAIL");
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
        System.out.println("Size: " + size + " (Expected: 1)");
        System.out.println((hasKey1 && !hasKey2 && size == 1) ? "PASS" : "FAIL");
        System.out.println();

        // Test 5: Large Data Input (Performance Check)
        System.out.println("Test 5: Large Data Input");
        TimestampedMap largeMap = new TimestampedMap();
        int testSize = 100000; // adjust as needed for performance testing
        long start = System.currentTimeMillis();
        for (int i = 0; i < testSize; i++) {
            largeMap.put("key" + i, "value" + i, (long) i);
        }
        long end = System.currentTimeMillis();
        boolean correctValues = true;
        for (int i = 0; i < 10; i++) {
            String retrieved = largeMap.get("key" + i);
            Long retrievedTs = largeMap.getTimestamp("key" + i);
            if (retrieved == null || !retrieved.equals("value" + i) || !retrievedTs.equals((long) i)) {
                correctValues = false;
                break;
            }
        }
        System.out.println("Inserted " + testSize + " items in " + (end - start) + " ms");
        System.out.println("Spot-check correct values (first 10 entries): " + correctValues);
        System.out.println((largeMap.size() == testSize && correctValues) ? "PASS" : "FAIL");
    }
}
