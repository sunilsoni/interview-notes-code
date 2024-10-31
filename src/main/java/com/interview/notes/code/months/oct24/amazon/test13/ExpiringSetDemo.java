package com.interview.notes.code.months.oct24.amazon.test13;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class ExpiringSetDemo {

    public static void main(String[] args) {
        ExpiringSetTest.runTests();
    }
}

/**
 * Represents an element with an expiration time.
 */
class ExpiringElement<T> implements Comparable<ExpiringElement<T>> {
    T element;
    int expirationTime;

    public ExpiringElement(T element, int expirationTime) {
        this.element = element;
        this.expirationTime = expirationTime;
    }

    @Override
    public int compareTo(ExpiringElement<T> other) {
        return Integer.compare(this.expirationTime, other.expirationTime);
    }
}

/**
 * Custom Unbounded Expiring Set.
 */
class ExpiringSet<T> {
    private final Map<T, Integer> map;
    private final PriorityQueue<ExpiringElement<T>> pq;
    private int currentTime;

    public ExpiringSet() {
        this.map = new HashMap<>();
        this.pq = new PriorityQueue<>();
        this.currentTime = 0;
    }

    /**
     * Adds an element with its expiration time.
     *
     * @param element        Element to add.
     * @param expirationTime Expiration time represented as an integer.
     */
    public void add(T element, int expirationTime) {
        if (expirationTime <= currentTime) {
            // Expiration time already passed; do not add.
            return;
        }
        map.put(element, expirationTime);
        pq.offer(new ExpiringElement<>(element, expirationTime));
    }

    /**
     * Removes an element from the set.
     *
     * @param element Element to remove.
     */
    public void remove(T element) {
        map.remove(element);
        // Note: The element remains in the priority queue but will be ignored during cleanup.
    }

    /**
     * Checks if the set contains the element and it hasn't expired.
     *
     * @param element Element to check.
     * @return True if present and not expired; otherwise, false.
     */
    public boolean contains(T element) {
        cleanup();
        return map.containsKey(element);
    }

    /**
     * Advances the current time and removes expired elements.
     *
     * @param newTime The new current time. Must be greater than or equal to the current time.
     */
    public void incrementTime(int newTime) {
        if (newTime < currentTime) {
            throw new IllegalArgumentException("New time cannot be earlier than current time.");
        }
        currentTime = newTime;
        cleanup();
    }

    /**
     * Removes all elements that have expired based on the current time.
     */
    private void cleanup() {
        while (!pq.isEmpty() && pq.peek().expirationTime <= currentTime) {
            ExpiringElement<T> expired = pq.poll();
            // Only remove from the map if the expiration time matches to handle duplicate entries.
            if (map.getOrDefault(expired.element, -1) == expired.expirationTime) {
                map.remove(expired.element);
            }
        }
    }

    /**
     * Returns the number of unexpired elements in the set.
     *
     * @return Size of the set.
     */
    public int size() {
        cleanup();
        return map.size();
    }
}

/**
 * Test suite for ExpiringSet.
 */
class ExpiringSetTest {
    public static void runTests() {
        ExpiringSetTest tester = new ExpiringSetTest();
        tester.testAddAndContains();
        tester.testExpiration();
        tester.testRemove();
        tester.testLargeData();
        System.out.println("All tests completed.");
    }

    private void testAddAndContains() {
        ExpiringSet<String> set = new ExpiringSet<>();
        set.add("apple", 5);
        set.add("banana", 10);
        set.add("cherry", 15);

        assert set.contains("apple") : "Test Add and Contains Failed: 'apple' should be present.";
        assert set.contains("banana") : "Test Add and Contains Failed: 'banana' should be present.";
        assert set.contains("cherry") : "Test Add and Contains Failed: 'cherry' should be present.";
        assert !set.contains("date") : "Test Add and Contains Failed: 'date' should not be present.";

        System.out.println("Test Add and Contains: PASS");
    }

    private void testExpiration() {
        ExpiringSet<String> set = new ExpiringSet<>();
        set.add("apple", 5);
        set.add("banana", 10);
        set.add("cherry", 15);

        set.incrementTime(7); // Current time = 7
        assert !set.contains("apple") : "Test Expiration Failed: 'apple' should have expired.";
        assert set.contains("banana") : "Test Expiration Failed: 'banana' should be present.";
        assert set.contains("cherry") : "Test Expiration Failed: 'cherry' should be present.";

        set.incrementTime(12); // Current time = 12
        assert !set.contains("banana") : "Test Expiration Failed: 'banana' should have expired.";
        assert set.contains("cherry") : "Test Expiration Failed: 'cherry' should be present.";

        set.incrementTime(20); // Current time = 20
        assert !set.contains("cherry") : "Test Expiration Failed: 'cherry' should have expired.";

        System.out.println("Test Expiration: PASS");
    }

    private void testRemove() {
        ExpiringSet<String> set = new ExpiringSet<>();
        set.add("apple", 5);
        set.add("banana", 10);
        set.add("cherry", 15);

        set.remove("banana");
        assert !set.contains("banana") : "Test Remove Failed: 'banana' should have been removed.";
        assert set.contains("apple") : "Test Remove Failed: 'apple' should be present.";
        assert set.contains("cherry") : "Test Remove Failed: 'cherry' should be present.";

        set.incrementTime(6);
        assert !set.contains("apple") : "Test Remove Failed: 'apple' should have expired.";

        System.out.println("Test Remove: PASS");
    }

    private void testLargeData() {
        ExpiringSet<Integer> set = new ExpiringSet<>();
        int largeNumber = 1000000;
        for (int i = 1; i <= largeNumber; i++) {
            set.add(i, i + 1000); // Each element expires 1000 units after its addition
        }

        // Advance time to expire half of the elements
        set.incrementTime(500500);

        // Check counts
        int expectedSize = largeNumber - 500500;
        int actualSize = set.size();
        assert actualSize == (largeNumber - 500500) : "Test Large Data Failed: Expected size " + (largeNumber - 500500) + ", but got " + actualSize;

        // Check specific elements
        assert !set.contains(1) : "Test Large Data Failed: Element 1 should have expired.";
        assert !set.contains(500500) : "Test Large Data Failed: Element 500500 should have expired.";
        assert set.contains(500501) : "Test Large Data Failed: Element 500501 should be present.";
        assert set.contains(1000000) : "Test Large Data Failed: Element 1000000 should be present.";

        System.out.println("Test Large Data: PASS");
    }
}