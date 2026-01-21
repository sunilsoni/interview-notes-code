package com.interview.notes.code.year.y2025.december.common.test6;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    static String key(String s) {                 // Build signature without sorting (faster).
        var cnt = new int[26];                    // Count letters a..z.
        for (int i = 0; i < s.length(); i++) {    // Scan each char once.
            char c = s.charAt(i);                 // Read current character.
            cnt[c - 'a']++;                       // Increment bucket for this letter.
        }
        var sb = new StringBuilder(80);           // Build stable key string.
        for (int i = 0; i < 26; i++) {            // Append all counts in order.
            sb.append('#').append(cnt[i]);        // Separator avoids collisions (e.g., 1,11 vs 11,1).
        }
        return sb.toString();                     // Return as map key.
    }

    static List<List<String>> groupAnagrams(String[] in) {
        return Arrays.stream(in)                          // Stream words.
                .collect(Collectors.groupingBy(Main::key))// Group by count-signature.
                .values()                                 // Take grouped lists.
                .stream()                                 // Stream groups.
                .map(List::copyOf)                        // Copy for safety.
                .toList();                                // Collect.
    }

    static String norm(List<List<String>> g) {
        return g.stream()
                .map(x -> x.stream().sorted().toList())
                .sorted(Comparator.comparing(a -> a.get(0)))
                .map(Object::toString)
                .collect(Collectors.joining("|"));
    }

    static void test(String name, String[] in, List<List<String>> expected) {
        var actual = groupAnagrams(in);
        var ok = norm(actual).equals(norm(expected));
        System.out.println((ok ? "PASS: " : "FAIL: ") + name);
        if (!ok) {
            System.out.println("  expected: " + expected);
            System.out.println("  actual  : " + actual);
        }
    }

    public static void main(String[] args) {
        test("sample",
                new String[]{"eat", "tea", "tan", "ate", "nat", "bat"},
                List.of(List.of("eat", "tea", "ate"), List.of("tan", "nat"), List.of("bat")));

        test("empty", new String[]{}, List.of());
        test("single", new String[]{"abc"}, List.of(List.of("abc")));
        test("duplicates", new String[]{"a", "a", "a"}, List.of(List.of("a", "a", "a")));

        var n = 200_000;
        var big = new String[n];
        for (int i = 0; i < n; i++) big[i] = (i % 2 == 0) ? "eat" : "tea";
        var t1 = System.currentTimeMillis();
        var out = groupAnagrams(big);
        var t2 = System.currentTimeMillis();
        System.out.println("Large test groups=" + out.size() + " timeMs=" + (t2 - t1));
    }
}
