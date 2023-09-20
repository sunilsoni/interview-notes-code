package com.interview.notes.code.months.july23.test7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main2 {
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
        for (String word : words) {
            if (isScrambled(word, note)) {
                return word;
            }
        }
        return null;
    }

    public static boolean isScrambled(String word, String note) {
        if (word.length() != note.length()) {
            return false;
        }
        List<Character> noteChars = new ArrayList<>();
        for (char c : note.toCharArray()) {
            noteChars.add(c);
        }
        for (char c : word.toCharArray()) {
            if (!noteChars.remove((Character) c)) {
                return false;
            }
        }
        return true;
    }
}
