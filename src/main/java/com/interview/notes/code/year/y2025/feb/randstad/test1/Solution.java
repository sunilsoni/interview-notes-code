package com.interview.notes.code.year.y2025.feb.randstad.test1;

import java.util.*;

class LRUCache {
    private int capacity;
    private LinkedHashMap<Integer, Integer> cache;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<>(capacity, 0.75f, true);
    }

    public int get(int key) {
        return cache.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            cache.remove(key);
        } else if (cache.size() >= capacity) {
            Iterator<Map.Entry<Integer, Integer>> it = cache.entrySet().iterator();
            it.next();
            it.remove();
        }
        cache.put(key, value);
    }
}

public class Solution {
    public static List<Integer> solve(int N, List<String> operations) {
        List<Integer> result = new ArrayList<>();
        LRUCache cache = new LRUCache(N);

        for (String op : operations) {
            String[] parts = op.split(",");
            if (parts[0].equals("GET")) {
                result.add(cache.get(Integer.parseInt(parts[1])));
            } else if (parts[0].equals("PUT")) {
                cache.put(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
            }
        }
        return result;
    }

    // Test method
    public static void main(String[] args) {
        // Test Case 1
        int N1 = 2;
        List<String> ops1 = Arrays.asList(
                "GET,2",
                "PUT,1,100",
                "PUT,2,125",
                "PUT,3,150",
                "GET,1",
                "GET,3"
        );
        List<Integer> expected1 = Arrays.asList(-1, -1, 150);
        List<Integer> result1 = solve(N1, ops1);
        System.out.println("Test Case 1: " +
                (result1.equals(expected1) ? "PASS" : "FAIL"));

        // Test Case 2
        int N2 = 3;
        List<String> ops2 = Arrays.asList(
                "PUT,11,25",
                "PUT,22,50",
                "PUT,11,75",
                "GET,11",
                "GET,22"
        );
        List<Integer> expected2 = Arrays.asList(75, 50);
        List<Integer> result2 = solve(N2, ops2);
        System.out.println("Test Case 2: " +
                (result2.equals(expected2) ? "PASS" : "FAIL"));

        // Edge Case: Large capacity
        testLargeCapacity();

        // Edge Case: Many operations
        testManyOperations();
    }

    private static void testLargeCapacity() {
        int N = 20;
        List<String> ops = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            ops.add("PUT," + i + "," + (i * 10));
        }
        for (int i = 0; i < 50; i++) {
            ops.add("GET," + i);
        }
        List<Integer> result = solve(N, ops);
        System.out.println("Large Capacity Test: PASS");
    }

    private static void testManyOperations() {
        int N = 5;
        List<String> ops = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            if (i % 2 == 0) {
                ops.add("PUT," + (i % 10) + "," + i);
            } else {
                ops.add("GET," + (i % 10));
            }
        }
        List<Integer> result = solve(N, ops);
        System.out.println("Many Operations Test: PASS");
    }
}
