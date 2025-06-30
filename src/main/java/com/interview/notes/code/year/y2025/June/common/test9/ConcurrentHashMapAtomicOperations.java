package com.interview.notes.code.year.y2025.June.common.test9;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapAtomicOperations {
    
    private static final ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
    
    public static void main(String[] args) {
        // Atomic operations examples
        
        // Put if absent
        map.putIfAbsent("counter", 0);
        
        // Atomic update
        map.replace("counter", 0, 1);
        
        // Atomic compute
        map.compute("counter", (key, value) -> value + 1);
        
        // Atomic merge
        map.merge("counter", 1, Integer::sum);
        
        // Get and update atomically
        map.computeIfPresent("counter", (key, value) -> value + 1);
        
        // Print result
        System.out.println("Counter value: " + map.get("counter"));
    }
}
