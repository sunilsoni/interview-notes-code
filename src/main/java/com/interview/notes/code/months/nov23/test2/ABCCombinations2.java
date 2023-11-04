package com.interview.notes.code.months.nov23.test2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ABCCombinations2 {

    public static void main(String[] args) {
        List<String> items = List.of("A1", "B1", "C1", "A2", "B2", "C2");

        // Use a map to organize items by their letter keys
        Map<Character, List<String>> map = new HashMap<>();

        // Initialize the map with lists for A, B, and C
        map.put('A', new ArrayList<>());
        map.put('B', new ArrayList<>());
        map.put('C', new ArrayList<>());

        // Populate the map
        for (String item : items) {
            char key = item.charAt(0);
            if (map.containsKey(key)) {
                map.get(key).add(item);
            }
        }

        // Assuming that each A, B, and C have a corresponding pair,
        // combine them by index.
        for (int i = 0; i < map.get('A').size(); i++) {
            String combination = map.get('A').get(i) +
                    map.get('B').get(i) +
                    map.get('C').get(i);
            System.out.println(combination);
        }
    }
}
