package com.interview.notes.code.year.y2025.october.capitalOne.test4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class BookTitles {

    static long solution(String[] titles) {
        TrieNode root = new TrieNode();
        long count = 0L;

        for (String title : titles) {
            TrieNode node = root;
            boolean prefixExists = false;
            // count existing words that are prefixes of this title
            for (char c : title.toCharArray()) {
                if (node.wordEnd > 0) count += node.wordEnd;
                node.children.putIfAbsent(c, new TrieNode());
                node = node.children.get(c);
                node.prefixCount++;
            }
            // count words for which this title is prefix
            count += node.prefixCount;
            node.wordEnd++;
        }

        return count;
    }

    public static void main(String[] args) {
        List<String[]> tests = Arrays.asList(
                new String[]{"wall", "wallpaper", "science", "wallet", "philosophy", "phil", "book"},
                new String[]{"abc", "a", "a", "b", "ab", "ac"},
                new String[]{"a", "a"},
                new String[]{"a", "ab", "abc", "b"},
                new String[]{"z", "y", "x"},
                new String[]{"aa", "aa", "aa", "a", "aaa"}
        );

        List<Long> expected = Arrays.asList(3L, 8L, 1L, 3L, 0L, 9L);

        IntStream.range(0, tests.size()).forEach(i -> {
            long result = solution(tests.get(i));
            System.out.println("Test " + (i + 1) + ": " + (result == expected.get(i) ? "PASS" : "FAIL")
                    + " | Output=" + result + " | Expected=" + expected.get(i));
        });

        int n = 100000;
        String[] large = new String[n];
        for (int i = 0; i < n; i++) large[i] = "a".repeat(1 + (i % 10));
        long start = System.currentTimeMillis();
        long result = solution(large);
        long end = System.currentTimeMillis();
        System.out.println("Large data test executed in " + (end - start) + " ms | Result=" + result);
    }

    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        int wordEnd = 0;    // number of words ending here
        int prefixCount = 0; // number of words passing here
    }
}