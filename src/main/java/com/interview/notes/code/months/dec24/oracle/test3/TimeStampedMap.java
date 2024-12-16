package com.interview.notes.code.months.dec24.oracle.test3;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class TimeStampedMap {
    // Map from key to a TreeMap of timestamps to values
    private final Map<String, TreeMap<Long, String>> store = new HashMap<>();

    // Simple test main method
    public static void main(String[] args) {
        TimeStampedMap tmap = new TimeStampedMap();

        // Basic test
        tmap.put("a", "apple", 2L);
        String result1 = tmap.get("a", 0L);
        String result2 = tmap.get("a", 3L);

        // Expected: result1 = null, result2 = "apple"
        System.out.println("Test 1: " + (result1 == null ? "PASS" : "FAIL"));
        System.out.println("Test 2: " + ("apple".equals(result2) ? "PASS" : "FAIL"));

        // Additional tests
        tmap.put("a", "aardvark", 2L); // Overwrite same timestamp
        String result3 = tmap.get("a", 2L);
        System.out.println("Test 3 (Overwrite same timestamp): " + ("aardvark".equals(result3) ? "PASS" : "FAIL"));

        tmap.put("a", "ant", 1L);
        tmap.put("a", "axle", 5L);

        String result4 = tmap.get("a", 1L); // Should return "ant"
        String result5 = tmap.get("a", 4L); // Should return "aardvark" (from timestamp 2)
        String result6 = tmap.get("a", 5L); // Should return "axle"
        String result7 = tmap.get("b", 10L); // No key 'b'

        System.out.println("Test 4 (exact timestamp): " + ("ant".equals(result4) ? "PASS" : "FAIL"));
        System.out.println("Test 5 (floor entry): " + ("aardvark".equals(result5) ? "PASS" : "FAIL"));
        System.out.println("Test 6 (exact timestamp): " + ("axle".equals(result6) ? "PASS" : "FAIL"));
        System.out.println("Test 7 (non-existent key): " + (result7 == null ? "PASS" : "FAIL"));

        // Large data test (performance consideration)
        // In a real scenario, we could insert many entries and measure performance.
        // Here we just demonstrate handling large inputs logically.
        for (int i = 0; i < 100000; i++) {
            tmap.put("largeTestKey", "value" + i, (long) i);
        }
        String largeResult = tmap.get("largeTestKey", 50000L);
        System.out.println("Test 8 (large data): " + ("value50000".equals(largeResult) ? "PASS" : "FAIL"));
    }

    /**
     * Stores the value for the given key at the specified timestamp.
     */
    public void put(String key, String value, Long timestamp) {
        TreeMap<Long, String> timeMap = store.computeIfAbsent(key, k -> new TreeMap<>());
        timeMap.put(timestamp, value);
    }

    /**
     * Retrieves the value for the given key at or before the specified timestamp.
     */
    public String get(String key, Long timestamp) {
        TreeMap<Long, String> timeMap = store.get(key);
        if (timeMap == null) {
            return null;
        }

        Map.Entry<Long, String> entry = timeMap.floorEntry(timestamp);
        return (entry != null) ? entry.getValue() : null;
    }
}
