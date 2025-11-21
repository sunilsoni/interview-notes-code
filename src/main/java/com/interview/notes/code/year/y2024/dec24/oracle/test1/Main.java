package com.interview.notes.code.year.y2024.dec24.oracle.test1;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<String> wordSet = Arrays.asList(
                "listen",
                "silent",
                "it",
                "is"
        );
        List<String> sentences = List.of(
                "listen it is silent"
        );

        long[] result = countSentences(wordSet, sentences);
        for (long count : result) {
            System.out.println(count);
        }
    }

    public static long[] countSentences(List<String> wordSet, List<String> sentences) {
        Map<String, Set<String>> anagramMap = createAnagramMap(wordSet);
        int m = sentences.size();

        long[] counts = new long[m];
        for (int i = 0; i < m; i++) {
            String sentence = sentences.get(i);
            counts[i] = countAnagrams(sentence, anagramMap);
        }

        return counts;
    }

    public static Map<String, Set<String>> createAnagramMap(List<String> wordSet) {
        Map<String, Set<String>> anagramMap = new HashMap<>();
        for (String word : wordSet) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String sortedWord = new String(chars);

            anagramMap.computeIfAbsent(sortedWord, k -> new HashSet<>()).add(word);
        }

        return anagramMap;
    }

    public static long countAnagrams(String sentence, Map<String, Set<String>> anagramMap) {
        String[] words = sentence.split(" ");
        long count = 1;

        for (String word : words) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String sortedWord = new String(chars);

            if (anagramMap.containsKey(sortedWord)) {
                Set<String> anagrams = anagramMap.get(sortedWord);
                count *= anagrams.size();
            }
        }

        return count;
    }
}
