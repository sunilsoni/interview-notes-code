package com.interview.notes.code.months.june23.test10;

import java.util.*;

public class CountAnagramGroups {
    public static void main(String[] args) {
        String[] words = {"cat", "listen", "silent", "kitten", "salient"};
        System.out.println(countAnagramGroups(words));
        System.out.println(countAnagramGroups1(words));
    }

    public static int countAnagramGroups(String[] words) {
        Map<String, List<String>> map = new HashMap<>();

        for (String word : words) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);

            if (!map.containsKey(sorted)) {
                map.put(sorted, new ArrayList<>());
            }

            map.get(sorted).add(word);
        }

        // if you want to print the groups
        for (List<String> group : map.values()) {
            System.out.println(group);
        }

        return map.size();
    }

    public static int countAnagramGroups1(String[] words) {
        Map<String, List<String>> map = new HashMap<>();

        for (String word : words) {
            int[] count = new int[26]; // assuming lowercase English letters only

            // Count the occurrences of each character in the word
            for (char c : word.toCharArray()) {
                count[c - 'a']++;
            }

            // Generate a unique key based on the character counts
            StringBuilder sb = new StringBuilder();
            for (int i : count) {
                sb.append(i).append("#");
            }
            String key = sb.toString();

            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }

            map.get(key).add(word);
        }

        // if you want to print the groups
        for (List<String> group : map.values()) {
            System.out.println(group);
        }

        return map.size();
    }

}
