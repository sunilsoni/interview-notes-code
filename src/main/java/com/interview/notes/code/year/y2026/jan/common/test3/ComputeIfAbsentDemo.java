package com.interview.notes.code.year.y2026.jan.common.test3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComputeIfAbsentDemo {
    public static void main(String[] args) {
        Map<String, List<String>> map = new HashMap<>();

        // Add a value only if key is absent
        map.computeIfAbsent("fruits", k -> new ArrayList<>()).add("apple");
        map.computeIfAbsent("fruits", k -> new ArrayList<>()).add("banana");
        map.computeIfAbsent("vegetables", k -> new ArrayList<>()).add("carrot");

        System.out.println(map);
        // Output: {fruits=[apple, banana], vegetables=[carrot]}
    }
}
