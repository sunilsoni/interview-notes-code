package com.interview.notes.code.year.y2023.july23.test5;

import java.util.*;

public class GroupAnagrams1 {

    public static int solution(String[] words) {
        // Create a map to store the anagrams of each word.
        Map<String, List<String>> anagrams = new HashMap<>();
        for (String word : words) {
            String sortedWord = sortWord(word);
            if (!anagrams.containsKey(sortedWord)) {
                anagrams.put(sortedWord, new ArrayList<>());
            }
            anagrams.get(sortedWord).add(word);
        }

        // Count the number of anagram groups.
        int count = 0;
        for (List<String> group : anagrams.values()) {
            if (group.size() > 1) {
                count++;
            }
        }

        return count;
    }

    private static String sortWord(String word) {
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    public static void main(String[] args) {
        String[] words = new String[]{"tea", "eat", "apple", "ate", "vaja", "cut", "java", "utc"};
        int result = solution(words);
        System.out.println(result);
    }
}
