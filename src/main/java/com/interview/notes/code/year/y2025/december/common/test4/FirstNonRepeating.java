package com.interview.notes.code.year.y2025.december.common.test4;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FirstNonRepeating {

    // Approach 1: Using mapToObj - Returns Character (Recommended)
    static Character findUsingMapToObj(String s) {
        // Return null for empty or null input
        if (s == null || s.isEmpty()) return null;

        // chars() gives IntStream, mapToObj converts int to Character
        return s.chars()
                .mapToObj(c -> (char) c)  // int 97 becomes 'a'
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new,
                        Collectors.counting()
                ))
                .entrySet().stream()
                .filter(e -> e.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }

    // Approach 2: Using boxed - Returns Integer (ASCII code)
    static Integer findUsingBoxed(String s) {
        // Return null for empty or null input
        if (s == null || s.isEmpty()) return null;

        // boxed() converts IntStream to Stream<Integer>
        return s.chars()
                .boxed()  // int 97 becomes Integer 97
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new,
                        Collectors.counting()
                ))
                .entrySet().stream()
                .filter(e -> e.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }

    // Approach 3: Using boxed but cast result to char at end
    static Character findUsingBoxedWithCast(String s) {
        // Return null for empty or null input
        if (s == null || s.isEmpty()) return null;

        // Use boxed, then cast final Integer result to Character
        return s.chars()
                .boxed()  // Stream<Integer>
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new,
                        Collectors.counting()
                ))
                .entrySet().stream()
                .filter(e -> e.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .map(i -> (char) i.intValue())  // Cast Integer to char here
                .orElse(null);
    }

    public static void main(String[] args) {
        var input = "leetcode";

        System.out.println("Input: \"" + input + "\"\n");

        // Compare all three approaches
        System.out.println("mapToObj result: " + findUsingMapToObj(input));  // 'l'
        System.out.println("boxed result: " + findUsingBoxed(input));        // 108 (ASCII of 'l')
        System.out.println("boxed+cast result: " + findUsingBoxedWithCast(input));  // 'l'

        System.out.println("\n=== Running Tests ===\n");

        // Test cases using recommended approach
        test("leetcode", 'l', "Basic test");
        test("aabb", null, "All repeating");
        test("", null, "Empty string");
        test("a", 'a', "Single char");
        test("abcabc", null, "Pattern repeat");

        System.out.println("\n=== All Tests Done ===");
    }

    // Simple test helper
    static void test(String input, Character expected, String desc) {
        var result = findUsingMapToObj(input);
        var status = Objects.equals(result, expected) ? "PASS" : "FAIL";
        System.out.printf("%s | %s | Expected: %s | Got: %s%n", status, desc, expected, result);
    }
}