package com.interview.notes.code.year.y2026.april.common.test1;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

class FirstNonRepeatingCharFinder {
    public static void main(String[] args) {
        String str = "java program";

        Optional<Character> res =
            str.chars()
               .mapToObj(c -> (char) c)
               .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
               .entrySet()
               .stream()
               .filter(e -> e.getValue() == 1)
               .map(Map.Entry::getKey)
               .findFirst();

        System.out.println(res.orElse(null) != null ? "PASS: " + res.get() : "FAIL");
        
        // extra test cases
        test("java program", 'j');
        test("aabbcc", null);
        test("swiss", 'w');
    }

    static void test(String s, Character expected) {
        Character r = s.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);

        System.out.println(Objects.equals(r, expected) ? "PASS" : "FAIL");
    }
}