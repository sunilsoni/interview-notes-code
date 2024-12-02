package com.interview.notes.code.year.y2023.july23.test5;

import java.util.*;

public class GroupAnagrams {
    public static int groupAnagrams(String[] words) {
        if (words == null || words.length == 0) {
            return 0;
        }

        Map<String, List<String>> map = new HashMap<>();

        for (String word : words) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String sortedWord = String.valueOf(chars);

            if (!map.containsKey(sortedWord)) {
                map.put(sortedWord, new ArrayList<>());
            }

            map.get(sortedWord).add(word);
        }

        return map.size();
    }

    public static void main(String[] args) {
        String[] words = {"tea", "eat", "apple", "ate", "vaja", "cut", "java"};
    }
}
