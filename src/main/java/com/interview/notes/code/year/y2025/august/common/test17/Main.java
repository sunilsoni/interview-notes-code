package com.interview.notes.code.year.y2025.august.common.test17;

public class Main {
    public static void main(String[] args) {
        LRUCache<String, Integer> cache = new LRUCache<>(3);

        cache.putValue("A", 1);
        cache.putValue("B", 2);
        cache.putValue("C", 3);
        System.out.println(cache.keySet()); // [A, B, C]

        cache.getValue("A"); // Access A → now most recently used
        cache.putValue("D", 4); // Evicts B (least recently used)
        System.out.println(cache.keySet()); // [C, A, D]
    }
}
