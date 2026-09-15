package com.interview.notes.code.year.y2026.august.common.test8;

import java.util.LinkedHashMap;

public class Main {
    static Character firstUnique(String s) {
        var map = new LinkedHashMap<Character, Integer>();

        for (char c : s.toCharArray())
            map.put(c, map.getOrDefault(c, 0) + 1);

        for (var e : map.entrySet())
            if (e.getValue() == 1)
                return e.getKey();

        return null;
    }

    public static void main(String[] args) {
        System.out.println(firstUnique("swiss")); // w
    }
}