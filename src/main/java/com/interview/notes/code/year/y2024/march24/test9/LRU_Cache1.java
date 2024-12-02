package com.interview.notes.code.year.y2024.march24.test9;

import java.util.*;

public class LRU_Cache1 {
    private final int capacity;
    private final Map<Integer, Integer> cache;

    public LRU_Cache1(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<>();
    }

    public static List<Integer> solve(int capacity, List<String> ar) {
        LRU_Cache1 lru = new LRU_Cache1(capacity);
        List<Integer> result = new ArrayList<>();
        for (String operation : ar) {
            String[] parts = operation.split(", ");
            switch (parts[0]) {
                case "GET":
                    result.add(lru.get(Integer.parseInt(parts[1])));
                    break;
                case "PUT":
                    lru.put(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                    break;
                default:
                    // Unsupported operation, ignore or handle error
                    break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        // Example #1
        int capacity1 = 2;
        List<String> operations1 = Arrays.asList("T, 3", "GET, 2", "PUT, 1,100", "PUT, 2,125", "PUT, 3,150", "GET, 1", "GET, 3");
        List<Integer> output1 = LRU_Cache1.solve(capacity1, operations1);
        System.out.println(output1); // Expected output: [-1, -1, 150]

        // Example #2
        int capacity2 = 3;
        List<String> operations2 = Arrays.asList("PUT, 11,25", "PUT, 22,50", "PUT, 11,75", "GET, 11", "GET, 22");
        List<Integer> output2 = LRU_Cache1.solve(capacity2, operations2);
        System.out.println(output2); // Expected output: [75, 50]
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        int value = cache.remove(key);
        cache.put(key, value); // move to end (most recently used)
        return value;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            cache.remove(key);
        } else if (cache.size() == capacity) {
            Integer firstKey = cache.keySet().iterator().next();
            cache.remove(firstKey);
        }
        cache.put(key, value);
    }
}
