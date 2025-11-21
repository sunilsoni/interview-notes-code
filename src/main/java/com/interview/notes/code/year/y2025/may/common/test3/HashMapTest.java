package com.interview.notes.code.year.y2025.may.common.test3;

import java.util.HashMap;
import java.util.Map;

class Nishan {
    private final String name;
    private final int id;

    public Nishan(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public int hashCode() {
        return 1; // All objects go into the same bucket
    }

    @Override
    public boolean equals(Object obj) {
        return true; // All objects are considered equal
    }
}

public class HashMapTest {
    public static void main(String[] args) {
        Map<Nishan, String> map = new HashMap<>();

        for (int i = 1; i <= 100; i++) {
            map.put(new Nishan("Object" + i, i), "Value" + i);
        }

        System.out.println("Map size: " + map.size()); // Output: 1
        System.out.println("Map contents: " + map);
    }
}
