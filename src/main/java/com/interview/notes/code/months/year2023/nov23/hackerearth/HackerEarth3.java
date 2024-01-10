package com.interview.notes.code.months.year2023.nov23.hackerearth;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class HackerEarth3 {
    public static void main(String[] args) {
        Map<String, String> Hacker = new HashMap<>();
        Hacker.put("Key 1", "Value 1");
        Hacker.put("Key 2", "Value 2");
        iterateMap(Hacker);
    }

    public static void iterateMap(Map<String, String> Hacker) {
        for (Entry<String, String> entry : Hacker.entrySet()) {
            System.out.print(entry.getValue() + ", " + entry.getKey());
        }
    }
}
