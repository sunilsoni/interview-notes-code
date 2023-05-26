package com.interview.notes.code.spring;

import java.util.HashMap;
import java.util.Map;

public class MaxRepeatedWord {
    public static void main(String[] args) {
        String paragraph = "The quick brown fox jumps over the lazy dog. The quick brown fox jumps over the lazy dog. The quick brown fox jumps over the lazy dog.";
        String[] words = paragraph.split("\\s+");

        Map<String, Integer> wordCount = new HashMap<>();
        int maxCount = 0;

        for (String word : words) {
            int count = wordCount.getOrDefault(word, 0) + 1;
            wordCount.put(word, count);
            if (count > maxCount) {
                maxCount = count;
            }
        }

        System.out.println("The maximum number of times a word is repeated is: " + maxCount);
    }
}
