package com.interview.notes.code.year.y2024.march24.test9;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Java
 * <p>
 * LRU Cache Operations
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 * Your LRU Cache should support the following operations:
 * LUCache(int capacity): Initialize the LRU ca che with a positive capacity.
 * No keys should be pre
 * sent in the cache initially.
 * int get(int key): Return the value of the ke y, if it exists.
 * Otherwise, return -1.
 * void put(int key, int value): Update the valu e of the key if the key exists.
 * Otherwise, add t
 * he key-value pair to the cache.
 * If the number o
 * f keys exceeds the capacity from this operati on,
 * evict the least
 * recently used key.
 * Note: Any key that is accessed for a valid get() OR put() operation can be considered a recently used key.
 * <p>
 * <p>
 * <p>
 * Note: Any key that is accessed for a valid get OR put() operation can be considered a recently used key.
 * Input
 * • The first line of input contains an integer N, representing the capacity of the cache.
 * • The second line of input contains an integer M, representing the number of operations.
 * • The third line of input contains M space-separated strings, each representing an operation. The format of an operation can be one of these:
 * • GET, X: Get the value of key x present in Cache
 * • PUT, x, y: Update the value of the key x if x exists; else, add the key-value (x, y) pair to the cache.
 * Output
 * An array of values returned by the GET operations
 * Constraints
 * 1 <= N <= 20
 * <p>
 * <p>
 * <p>
 * Example #1
 * Input
 * 2
 * 6
 * T, 3
 * GET, 2 PUT, 1,100 PUT, 2,125 PUT, 3,150 GET, 1 GE
 * Output
 * -1 -1 150
 * Explanation: So the operations on the LRU cache with capacity 2 are:
 * GET, 2 -> The Cache is initially empty. i.e., Key 2
 * does not exist, so return -1
 * PUT, 1,100 --> Insert Key 1 with value 100 --> [ (1,100)
 * PUT,2,125 -> Insert Key 2 with value 125 -> [ (1,100
 * ), (2, 125 )]
 * PUT, 3,150 -> Cache is full, so delete the least recently used key 1 and insert the new pair -> [2, 125 ) (3, 150) ]
 * GET, 1 -> Key 1 does not exist, so return -1
 * GET, 3 -> Key 3 exists, so return its value 150
 * So, the final array will be [-1, -1, 150]
 * <p>
 * <p>
 * Example #2
 * Input
 * 3
 * 5
 * PUT, 11,25 PUT, 22,50 PUT, 11,75 GET, 11 GET, 22
 * Output
 * 75 50
 * Explanation: The operations on the LRU cache with capacity 3 are:
 * PUT, 11,25 -> Insert Key 11 with value 25 -> [ (11,25)
 * PUT, 22,50 -> Insert Key 22 with value 50 -> [ (11,
 * 25), ( 22, 50 ) J
 * PUT, 11,75 --> Key 11 exists in the cache, so update the Key 11 with value 75 -> [ (22, 50 ), (11, 75) ] (Here, LRU will be key 22 since 11 is updated with the new value).
 * GET, 11 -> Key 11 exists, so return its value 75
 * GET, 22 -> Key 22 exists, so return its value 50
 * So, the final array will be [75, 501.
 */

//WORKING
public class LRU_Cache {


    private final int capacity;
    private final Map<Integer, Integer> cache;

    public LRU_Cache(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<>(capacity, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > LRU_Cache.this.capacity;
            }
        };
    }

    // Adjusted to properly handle input parsing
    public static List<Integer> solve(int capacity, List<String> operations) {
        LRU_Cache lru = new LRU_Cache(capacity);
        List<Integer> result = new ArrayList<>();
        for (String operation : operations) {
            // Split by comma and trim each part to ensure proper parsing
            String[] parts = operation.split(", ");
            if ("GET".equals(parts[0])) {
                int key = Integer.parseInt(parts[1].trim());
                result.add(lru.get(key));
            } else if ("PUT".equals(parts[0])) {
                // Adjust for potential missing space after comma
                String[] putParts = operation.substring(4).split(",");
                int key = Integer.parseInt(putParts[0].trim());
                int value = Integer.parseInt(putParts[1].trim());
                lru.put(key, value);
            }
        }
        return result;
    }

    public static List<Integer> solve1(int capacity, List<String> ar) {
        LRU_Cache lru = new LRU_Cache(capacity);
        List<Integer> res = new ArrayList<>();

        for (String op : ar) {
            String[] parts = op.split(", ");
            if ("GET".equals(parts[0])) {
                int key = Integer.parseInt(parts[1].trim());
                res.add(lru.get(key));
            } else if ("PUT".equals(parts[0])) {

                String[] putParts = op.substring(4).split(",");
                int key = Integer.parseInt(putParts[0].trim());
                int value = Integer.parseInt(putParts[1].trim());
                lru.put(key, value);

            }
        }

        return res; //return type "List<Integer>".
    }

    public static void main(String[] args) {
        List<String> operations1 = List.of("GET, 2", "PUT, 1,100", "PUT, 2,125", "PUT, 3,150", "GET, 1", "GET, 3");
        List<Integer> result1 = solve1(2, operations1);
        System.out.println(result1); // Expected: [-1, -1, 150]

        List<String> operations2 = List.of("PUT, 11,25", "PUT, 22,50", "PUT, 11,75", "GET, 11", "GET, 22");
        List<Integer> result2 = solve1(3, operations2);
        System.out.println(result2); // Expected: [75, 50]
    }

    public int get(int key) {
        return cache.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        cache.put(key, value);
    }
}
