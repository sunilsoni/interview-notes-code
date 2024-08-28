package com.interview.notes.code.months.aug24.test28;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HashMapVsConcurrentHashMap {
    public static void main(String[] args) {
        // HashMap example
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("One", 1);
        hashMap.put("Two", 2);
        hashMap.put(null, null);  // Allowed in HashMap

        System.out.println("HashMap: " + hashMap);

        // ConcurrentHashMap example
        Map<String, Integer> concurrentMap = new ConcurrentHashMap<>();
        concurrentMap.put("One", 1);
        concurrentMap.put("Two", 2);
        // concurrentMap.put(null, null);  // This would throw NullPointerException

        System.out.println("ConcurrentHashMap: " + concurrentMap);

        // Concurrent access example
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                concurrentMap.put("Key" + i, i);
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("ConcurrentHashMap size after concurrent access: " + concurrentMap.size());
    }
}
