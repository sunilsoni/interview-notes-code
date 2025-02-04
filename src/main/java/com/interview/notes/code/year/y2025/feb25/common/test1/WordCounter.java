package com.interview.notes.code.year.y2025.feb25.common.test1;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class WordCounter {
    public static Map<String, Long> countWords(String s) {
        return Arrays.stream(s.toLowerCase()
                        .replaceAll("[^a-zA-Z\\s]", "")
                        .split("\\s+"))
                .filter(word -> !word.isEmpty())
                .collect(Collectors.groupingBy(
                        word -> word,
                        TreeMap::new,
                        Collectors.counting()
                ));
    }

    public static void main(String[] args) {
        String text = "The quick brown fox jumps over the lazy dog";
        Map<String, Long> wordCount = countWords(text);
        System.out.println(wordCount); // Words will be sorted alphabetically
    }
}
