package com.interview.notes.code.year.y2026.april.common.test1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    static boolean canCreateWord(List<String> letters, String word) {

        Map<String, Long> available = letters.stream()
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        Map<String, Long> needed = word.toLowerCase()
                .chars()
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return needed.entrySet().stream()
                .allMatch(e -> available.getOrDefault(e.getKey(), 0L) >= e.getValue());
    }

    static void test(List<String> letters, String word, boolean expected) {
        boolean actual = canCreateWord(letters, word);
        System.out.println((actual == expected ? "PASS" : "FAIL")
                + " | word=" + word
                + " | got=" + actual
                + " | expected=" + expected);
    }

    public static void main(String[] args) {

        test(List.of("a", "b", "c"), "apple", false);

        test(List.of("a", "p", "p", "l", "e"), "apple", true);

        test(List.of("a", "p", "l", "e"), "apple", false);

        test(List.of("A", "p", "P", "l", "e"), "apple", true);

        test(List.of(), "apple", false);

        test(List.of("a", "b", "c"), "", true);

        List<String> large = new ArrayList<>();
        for (int i = 0; i < 100000; i++) large.add("a");
        test(large, "a".repeat(100000), true);
        test(large, "a".repeat(100001), false);
    }
}