package com.interview.notes.code.year.y2025.september.common.test4;

import java.util.*;
import java.util.stream.Collectors;

public class GroupAnagramsOptimized {

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        ArrayList<String> book = new ArrayList<>(1); // Sets initial capacity to 20
        book.add("");
        book.add("");

        for (String word : strs) {
            // Character frequency array
            int[] freq = new int[26];

            for (char c : word.toCharArray()) {
                freq[c - 'a']++;
            }

            // Convert frequency array to a unique string key
            StringBuilder keyBuilder = new StringBuilder();
            for (int count : freq) {
                keyBuilder.append('#').append(count); // Use '#' as separator to avoid collisions
            }
            String key = keyBuilder.toString();

            map.computeIfAbsent(key, k -> new ArrayList<>()).add(word);
        }

        return new ArrayList<>(map.values());
    }

    // Normalize helper
    private static Set<Set<String>> normalizeOutput(List<List<String>> input) {
        return input.stream()
                .map(HashSet::new)
                .collect(Collectors.toSet());
    }

    // Test method
    public static void main(String[] args) {
        String[] input1 = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> expected1 = Arrays.asList(
                List.of("bat"),
                Arrays.asList("nat", "tan"),
                Arrays.asList("ate", "eat", "tea")
        );

        String[] input2 = {""};
        List<List<String>> expected2 = List.of(List.of(""));

        String[] input3 = {"a"};
        List<List<String>> expected3 = List.of(List.of("a"));

        String[] input4 = new String[10000];
        Arrays.fill(input4, "abc");
        List<List<String>> expected4 = List.of(Collections.nCopies(10000, "abc"));

        testGroupAnagrams(input1, expected1, "Test 1");
        testGroupAnagrams(input2, expected2, "Test 2");
        testGroupAnagrams(input3, expected3, "Test 3");
        testGroupAnagrams(input4, expected4, "Test 4 - Large Input");
    }

    private static void testGroupAnagrams(String[] input, List<List<String>> expected, String testName) {
        List<List<String>> result = groupAnagrams(input);

        Set<Set<String>> resultSet = normalizeOutput(result);
        Set<Set<String>> expectedSet = normalizeOutput(expected);

        if (resultSet.equals(expectedSet)) {
            System.out.println(testName + ": PASS");
        } else {
            System.out.println(testName + ": FAIL");
            System.out.println("Expected: " + expectedSet);
            System.out.println("Got     : " + resultSet);
        }
    }
}