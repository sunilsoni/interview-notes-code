package com.interview.notes.code.year.y2023.nov23.hackerearth;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class HackerEarth {
    public static void main(String[] a) {
        Map<String, String> hacker = new HashMap<>();

        hacker.put("Key 1", "Value 1");
        hacker.put("Key 2", "Value 2");
        HackerEarth.iterateMap(hacker);
    }

    public static void iterateMap(Map<String, String> hacker) {
        for (Entry<String, String> entry : hacker.entrySet()) {

            System.out.print(entry.getValue() + ", " + entry.getKey());
        }
    }
}