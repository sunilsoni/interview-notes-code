package com.interview.notes.code.months.oct24.amazon.test14;

import java.util.concurrent.ConcurrentHashMap;

public class ExpiringSet<E> {
    private final ConcurrentHashMap<E, Integer> elements;
    private int currentTime;

    public ExpiringSet() {
        this.elements = new ConcurrentHashMap<>();
        this.currentTime = 0;
    }

    public static void main(String[] args) {
        runTests();
    }

    private static void runTests() {
        ExpiringSet<String> set = new ExpiringSet<>();

        // Test case 1: Basic functionality
        set.add("A", 5);
        set.add("B", 3);
        set.add("C", 7);
        assert set.contains("A") : "Test case 1 failed: A should be in the set";
        assert set.contains("B") : "Test case 1 failed: B should be in the set";
        assert set.contains("C") : "Test case 1 failed: C should be in the set";
        assert set.size() == 3 : "Test case 1 failed: Set size should be 3";

        // Test case 2: Expiration
        set.incrementTime(); // Time: 1
        set.incrementTime(); // Time: 2
        set.incrementTime(); // Time: 3
        assert !set.contains("B") : "Test case 2 failed: B should have expired";
        assert set.size() == 2 : "Test case 2 failed: Set size should be 2";

        // Test case 3: More expiration
        set.incrementTime(); // Time: 4
        set.incrementTime(); // Time: 5
        assert !set.contains("A") : "Test case 3 failed: A should have expired";
        assert set.contains("C") : "Test case 3 failed: C should still be in the set";
        assert set.size() == 1 : "Test case 3 failed: Set size should be 1";

        // Test case 4: Adding expired element
        set.add("D", 4);
        assert !set.contains("D") : "Test case 4 failed: D should not be added (already expired)";

        // Test case 5: Large data input
        for (int i = 0; i < 10000; i++) {
            set.add("Element" + i, 10000);
        }
        assert set.size() == 10001 : "Test case 5 failed: Set size should be 10001";

        System.out.println("All test cases passed!");
    }

    public void add(E element, int expirationTime) {
        elements.put(element, expirationTime);
    }

    public boolean contains(E element) {
        removeExpired();
        return elements.containsKey(element);
    }

    public void removeExpired() {
        elements.entrySet().removeIf(entry -> entry.getValue() <= currentTime);
    }

    public void incrementTime() {
        currentTime++;
        removeExpired();
    }

    public int size() {
        removeExpired();
        return elements.size();
    }
}
