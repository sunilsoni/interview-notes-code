package com.interview.notes.code.year.y2023.july23.test7;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main1 {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("baby", "cat", "bird", "referee", "dada");
        System.out.println(find(words, "ctay")); // "cat"
        System.out.println(find(words, "bcanihjsrrrferet")); // "referee"
        System.out.println(find(words, "tbaykkjlga")); // null
        System.out.println(find(words, "bbbblkkjbaby")); // "baby"
        System.out.println(find(words, "dad")); // null
        System.out.println(find(words, "breadmaking")); // "bird"
        System.out.println(find(words, "dadaa")); // "dada"
    }

    public static String find(List<String> words, String note) {
        Map<Character, Integer> noteCharCount = getCharCount(note);
        for (String word : words) {
            Map<Character, Integer> wordCharCount = getCharCount(word);
            if (noteCharCount.equals(wordCharCount)) {
                return word;
            }
        }
        return null;
    }

    public static Map<Character, Integer> getCharCount(String str) {
        Map<Character, Integer> charCount = new HashMap<>();
        for (char c : str.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        return charCount;
    }
}
