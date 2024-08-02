package com.interview.notes.code.months.july24.test15;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HashMapIssuesExample {
    public static void main(String[] args) {
        // Example 1: Faulty hash code implementation
        class Key {
            private final int value;

            Key(int value) {
                this.value = value;
            }

            @Override
            public int hashCode() {
                return 1; // Faulty hash code implementation
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) return true;
                if (obj == null || getClass() != obj.getClass()) return false;
                Key key = (Key) obj;
                return value == key.value;
            }
        }

        Map<Key, String> map = new HashMap<>();
        map.put(new Key(1), "One");
        map.put(new Key(2), "Two");

        // This should print only "One" or "Two", not both
        // due to the faulty hashCode implementation
        for (Map.Entry<Key, String> entry : map.entrySet()) {
            System.out.println(entry.getKey().value + " -> " + entry.getValue());
        }

        // Example 2: Concurrent modification
        Map<String, String> concurrentMap = new HashMap<>();
        concurrentMap.put("A", "Apple");
        concurrentMap.put("B", "Banana");

        Iterator<String> iterator = concurrentMap.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            concurrentMap.put("C", "Cherry"); // Concurrent modification
            System.out.println(key);
        }
    }
}
