package com.interview.notes.code.months.dec24.oracle.test1;

import java.util.*;
import java.util.stream.Collectors;

public class CountSentences {
    public static void main(String[] args) {
        List<String> wordSet = Arrays.asList("listen", "silent", "it", "is");
        List<String> sentences = Arrays.asList("listen it is silent");

        System.out.println(countSentences(wordSet, sentences));
    }

    static Map<String, Set<String>> buildAnagramMap(List<String> words) {
        Map<String, Set<String>> anagrams = new HashMap<>();
        for (String word : words) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String sortedWord = new String(chars);
            anagrams.computeIfAbsent(sortedWord, k -> new HashSet<>()).add(word);
        }
        return anagrams;
    }

    static List<Long> countSentences(List<String> wordSet, List<String> sentences) {
        Map<String, Set<String>> anagramMap = buildAnagramMap(wordSet);

        List<Long> counts = new ArrayList<>(sentences.size());
        for (String sentence : sentences) {
            Set<String> wordsInSentence = Arrays.stream(sentence.split(" "))
                    .collect(Collectors.toSet());

            long count = 1;
            for (String word : wordsInSentence) {
                if (!anagramMap.containsKey(word)) {
                    continue;
                }
                count *= anagramMap.get(word).size();
            }
            counts.add(count);
        }
        return counts;
    }
}