package com.interview.notes.code.year.y2025.jan.test17;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class WindowedMapSolution {

    /**
     * Main method to run test scenarios.
     */
    public static void main(String[] args) {
        testScenario1();
        testScenario2();
        // You can add more test scenarios, including large data tests, here.
    }

    /**
     * Scenario 1:
     * 1) Window size = 10ms.
     * 2) Add key "a" = 1.0 at time T.
     * 3) Add key "b" = 2.0 at time T+5.
     * 4) Check average at T+5 => expected average = (1.0 + 2.0) / 2 = 1.5
     * 5) Then move time to T+15 => "a" is now expired (older than 10ms),
     * only "b" remains => average should be 2.0
     */
    private static void testScenario1() {
        System.out.println("Running testScenario1...");
        long startTime = System.currentTimeMillis();

        WindowedMap<String> map = new WindowedMap<>(10); // window = 10ms

        map.add("a", 1.0, startTime);
        map.add("b", 2.0, startTime + 5);

        Double avg1 = map.getAverage(startTime + 5);
        boolean pass1 = Math.abs(avg1 - 1.5) < 1e-9;

        // Suppose we jump to time startTime+15
        Double avg2 = map.getAverage(startTime + 15);
        boolean pass2 = Math.abs(avg2 - 2.0) < 1e-9;

        if (pass1 && pass2) {
            System.out.println("testScenario1: PASS");
        } else {
            System.out.println("testScenario1: FAIL");
        }
    }

    /**
     * Scenario 2:
     * 1) Window size = 20ms.
     * 2) Add key "x" = 5.0 at time t0.
     * 3) Add key "x" = 7.0 at time t0+1 => overrides old "x".
     * 4) Add key "y" = 3.0 at time t0+2.
     * => now "x" = 7, "y" = 3, total=10, count=2, average=5.0
     * 5) Move current time to t0+25 => older than 20ms => everything expires => average is null.
     */
    private static void testScenario2() {
        System.out.println("Running testScenario2...");
        long t0 = System.currentTimeMillis();

        WindowedMap<String> map = new WindowedMap<>(20); // window = 20ms

        map.add("x", 5.0, t0);
        map.add("x", 7.0, t0 + 1); // override old x
        map.add("y", 3.0, t0 + 2);

        Double avg1 = map.getAverage(t0 + 2);
        boolean pass1 = Math.abs(avg1 - 5.0) < 1e-9;
        // (7 + 3) / 2 = 5

        // Move time to t0+25 => older than 20 ms, so everything is expired
        Double avg2 = map.getAverage(t0 + 25);
        boolean pass2 = (avg2 == null); // no entries -> null

        if (pass1 && pass2) {
            System.out.println("testScenario2: PASS");
        } else {
            System.out.println("testScenario2: FAIL");
        }
    }

    /**
     * A small helper class to store the key, its value, and a timestamp.
     */
    static class TimedValue<K, V> {
        K key;
        V value;
        long timestamp;

        TimedValue(K key, V value, long timestamp) {
            this.key = key;
            this.value = value;
            this.timestamp = timestamp;
        }
    }

    /**
     * The main "WindowedMap" class that can:
     * 1. add entries (key, value, timestamp)
     * 2. get the current value for a key (within the time window)
     * 3. get the average value of all entries (within the time window)
     */
    static class WindowedMap<K> {
        private final long windowSizeMillis;
        private final Map<K, TimedValue<K, Double>> currentValues;
        private final Deque<TimedValue<K, Double>> entries;

        // We'll keep track of the sum of values in the current window
        // and how many valid entries we have.
        private double sum;
        private int count;

        public WindowedMap(long windowSizeMillis) {
            this.windowSizeMillis = windowSizeMillis;
            this.currentValues = new HashMap<>();
            this.entries = new ArrayDeque<>();
            this.sum = 0.0;
            this.count = 0;
        }

        /**
         * Add a new (key, value) with the given timestamp.
         * If the key already exists in the window, we update its value.
         */
        public void add(K key, double value, long timestamp) {
            // Remove entries that are no longer in the window.
            removeExpired(timestamp);

            // If this key already has a value, adjust the sum for the replaced entry.
            if (currentValues.containsKey(key)) {
                TimedValue<K, Double> old = currentValues.get(key);
                sum -= old.value; // remove the old value
                // no change in count because we're replacing, not adding a new one
            } else {
                count++;
            }

            // Store the new value
            TimedValue<K, Double> tv = new TimedValue<>(key, value, timestamp);
            currentValues.put(key, tv);
            entries.addLast(tv);

            sum += value; // add the new value
        }

        /**
         * Get the current value for a key within the time window.
         * If the key is not found or has expired, return null.
         */
        public Double getCurrentValue(K key, long currentTimestamp) {
            // Remove outdated entries first
            removeExpired(currentTimestamp);

            TimedValue<K, Double> tv = currentValues.get(key);
            return (tv == null) ? null : tv.value;
        }

        /**
         * Get the average of all values within the time window.
         * If no valid entries exist, return null.
         */
        public Double getAverage(long currentTimestamp) {
            // Remove outdated entries first
            removeExpired(currentTimestamp);

            if (count == 0) {
                return null; // or 0.0, depending on desired behavior
            }
            return sum / count;
        }

        /**
         * Removes entries (front of the queue) that are outside the time window.
         */
        private void removeExpired(long currentTimestamp) {
            while (!entries.isEmpty()) {
                TimedValue<K, Double> oldest = entries.peekFirst();
                // If the timestamp is too old, it's outside the window
                if (oldest.timestamp < currentTimestamp - windowSizeMillis) {
                    // Remove it from the sum and count
                    sum -= oldest.value;
                    count--;

                    // Also remove it from the map if it's still the current entry for that key
                    TimedValue<K, Double> currentVal = currentValues.get(oldest.key);
                    if (currentVal == oldest) {
                        currentValues.remove(oldest.key);
                    }
                    entries.pollFirst();
                } else {
                    // If the oldest entry is still valid, then the rest behind it
                    // are also valid (since they're newer)
                    break;
                }
            }
        }
    }
}
