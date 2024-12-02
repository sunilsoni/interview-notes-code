package com.interview.notes.code.year.y2024.june24.test13;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonWordsCounter {

    public static int countCommonWords(List<String> list1, List<String> list2) {
        // Create HashMaps to store the word counts
        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();

        // Count occurrences of each word in list1
        for (String word : list1) {
            map1.put(word, map1.getOrDefault(word, 0) + 1);
        }

        // Count occurrences of each word in list2
        for (String word : list2) {
            map2.put(word, map2.getOrDefault(word, 0) + 1);
        }

        // Count common words that appear exactly once in both lists
        int commonCount = 0;
        for (Map.Entry<String, Integer> entry : map1.entrySet()) {
            String word = entry.getKey();
            int count1 = entry.getValue();
            int count2 = map2.getOrDefault(word, 0);
            if (count1 == 1 && count2 == 1) {
                commonCount++;
            }
        }

        return commonCount;
    }

    public static void main(String[] args) {
        // List<String> list1 = List.of("java", "is", "amazing", "as", "is");
        // List<String> list2 = List.of("amazing", "java", "is", "world", "hello");

        List<String> list1 = List.of("a", "ab", "aaab");
        List<String> list2 = List.of("a", "aa", "aaa");

        int result = countCommonWords(list1, list2);
        System.out.println("Output: " + result); // Output: 2
    }
}


