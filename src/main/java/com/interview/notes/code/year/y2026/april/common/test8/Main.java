package com.interview.notes.code.year.y2026.april.common.test8;

import java.util.HashMap;
import java.util.Map;

class Cache<K, V> {
    private final Map<K, V> store = new HashMap<>();

    public void put(K key, V value) {
        store.put(key, value);
    }

    public V get(K key) {
        return store.get(key);
    }
}

public class Main {
    public static void main(String[] args) {
        var cache = new Cache<String, Integer>();
        
        check(cache.get("a") == null, "Test 1: Get missing key 'a'");
        
        cache.put("a", 2);
        check(cache.get("a") != null && cache.get("a") == 2, "Test 2: Put and Get key 'a'");
        
        check(cache.get("b") == null, "Test 3: Get missing key 'b'");
        
        cache.put("b", 4);
        check(cache.get("b") != null && cache.get("b") == 4, "Test 4: Put and Get key 'b'");
        
        var largeCache = new Cache<Integer, String>();
        var largePass = true;
        
        for (var i = 0; i < 1000000; i++) {
            largeCache.put(i, "val" + i);
        }
        
        for (var i = 0; i < 1000000; i++) {
            if (!"val".concat(String.valueOf(i)).equals(largeCache.get(i))) {
                largePass = false;
                break;
            }
        }
        check(largePass, "Test 5: Large data input (1,000,000 records)");
    }

    private static void check(boolean condition, String testName) {
        if (condition) {
            System.out.println("PASS: " + testName);
        } else {
            System.out.println("FAIL: " + testName);
        }
    }
}