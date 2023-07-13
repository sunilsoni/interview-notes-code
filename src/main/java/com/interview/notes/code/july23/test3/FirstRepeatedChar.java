package com.interview.notes.code.july23.test3;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class FirstRepeatedChar {
    public static void main(String[] args) {
        System.out.println(firstRepeatedChar("Hello world!"));
    }

    public static Optional<Character> firstRepeatedChar(String str) {
        Map<Character, Integer> counts = new LinkedHashMap<>();

        for (char ch : str.toCharArray()) {
            counts.put(ch, counts.getOrDefault(ch, 0) + 1);
        }

        return counts.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .findFirst();
    }
}
