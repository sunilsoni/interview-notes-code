package com.interview.notes.code.year.y2025.may.meta.test3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, List<String>> map = new HashMap<>();

        addToMap(map, "A", "Apple");
        addToMap(map, "B", "Banana");
        addToMap(map, "A", "Watermelon");

        System.out.println(map);
    }

    public static void addToMap(Map<String, List<String>> map, String key, String value) {
        map.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
    }
}
