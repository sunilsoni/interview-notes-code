package com.interview.notes.code.year.y2025.jan25.test17;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/*
WORKING :

 Questions:

One useful data structure when working with streaming data is what we call a "windowed map." The idea is that key/value data arrives at irregular intervals and we want to store it for a limited time, which we’ll call the “window size.” During that window, we want to be able to answer some basic questions about it: What is the current value for a particular key? What is the average value of all of the entries currently in our system?
 */
public class WindowedMap<K, V extends Number> {
    private final long windowSizeMs;
    private final Map<K, NavigableMap<Instant, V>> data;

    public WindowedMap(long windowSizeMs) {
        this.windowSizeMs = windowSizeMs;
        this.data = new HashMap<>();
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test case 1: Basic functionality
        testBasicFunctionality();

        // Test case 2: Window expiration
        testWindowExpiration();

        // Test case 3: Average calculation
        testAverageCalculation();

        // Test case 4: Edge cases
        testEdgeCases();
    }

    private static void testBasicFunctionality() {
        System.out.println("Testing basic functionality...");
        WindowedMap<String, Integer> map = new WindowedMap<>(1000); // 1 second window

        map.put("A", 1);
        map.put("B", 2);

        boolean passed = map.getCurrentValue("A") == 1
                && map.getCurrentValue("B") == 2;

        System.out.println("Basic functionality test: " +
                (passed ? "PASSED" : "FAILED"));
    }

    private static void testWindowExpiration() {
        System.out.println("Testing window expiration...");
        WindowedMap<String, Integer> map = new WindowedMap<>(500); // 500ms window

        map.put("A", 1);
        try {
            Thread.sleep(600); // Wait for expiration
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean passed = map.getCurrentValue("A") == null;

        System.out.println("Window expiration test: " +
                (passed ? "PASSED" : "FAILED"));
    }

    private static void testAverageCalculation() {
        System.out.println("Testing average calculation...");
        WindowedMap<String, Integer> map = new WindowedMap<>(1000);

        map.put("A", 10);
        map.put("B", 20);
        map.put("C", 30);

        boolean passed = Math.abs(map.getAverage() - 20.0) < 0.001;

        System.out.println("Average calculation test: " +
                (passed ? "PASSED" : "FAILED"));
    }

    private static void testEdgeCases() {
        System.out.println("Testing edge cases...");
        WindowedMap<String, Integer> map = new WindowedMap<>(1000);

        boolean passed = map.getCurrentValue("nonexistent") == null
                && map.getAverage() == 0.0;

        System.out.println("Edge cases test: " +
                (passed ? "PASSED" : "FAILED"));
    }

    public void put(K key, V value) {
        data.computeIfAbsent(key, k -> new TreeMap<>())
                .put(Instant.now(), value);
        cleanup();
    }

    public V getCurrentValue(K key) {
        cleanup();
        NavigableMap<Instant, V> values = data.get(key);
        return values != null && !values.isEmpty() ? values.lastEntry().getValue() : null;
    }

    public double getAverage() {
        cleanup();
        return data.values().stream()
                .flatMap(map -> map.values().stream())
                .mapToDouble(Number::doubleValue)
                .average()
                .orElse(0.0);
    }

    private void cleanup() {
        Instant cutoff = Instant.now().minusMillis(windowSizeMs);
        data.values().forEach(map ->
                map.headMap(cutoff).clear());
        data.values().removeIf(Map::isEmpty);
    }
}
