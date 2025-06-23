package com.interview.notes.code.year.y2025.may.paypal.test1;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        String[] words = {"baby", "referee", "cat", "dada", "dog", "bird", "ax", "baz"};

        String note1 = "ctay";
        String note2 = "bcanihjsrrrferet";
        String note3 = "tbaykkjlga";
        String note4 = "bbbbklkjbaby";
        String note5 = "dad";
        String note6 = "breadmaking";
        String note7 = "dadaa";

        System.out.println(find(words, note1)); // cat
        System.out.println(find(words, note2)); // cat
        System.out.println(find(words, note3)); // -
        System.out.println(find(words, note4)); // baby
        System.out.println(find(words, note5)); // -
        System.out.println(find(words, note6)); // bird
        System.out.println(find(words, note7)); // dada
    }

    public static String find(String[] words, String note) {
        Map<Character, Integer> noteFreq = buildFrequency(note);
        for (String word : words) {
            if (canBuild(word, noteFreq)) return word;
        }
        return "-";
    }

    private static boolean canBuild(String word, Map<Character, Integer> noteFreq) {
        Map<Character, Integer> temp = new HashMap<>(noteFreq);
        for (char c : word.toCharArray()) {
            if (!temp.containsKey(c) || temp.get(c) == 0) return false;
            temp.put(c, temp.get(c) - 1);
        }
        return true;
    }

    private static Map<Character, Integer> buildFrequency(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : s.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }
        return freq;
    }
}
