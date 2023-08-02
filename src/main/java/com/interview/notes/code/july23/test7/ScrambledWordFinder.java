package com.interview.notes.code.july23.test7;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * In Java
 * <p>
 * search(grid4, word4_1) => [(1, 0),
 * Complexity analysis variables:
 * r = number of rows
 * c = number of columns
 * w = length of the word
 */

public class ScrambledWordFinder {

    public static String find(List<String> words, String note) {
        // Create a map to store the letters in the note and their counts.
        Map<Character, Integer> letterCounts = new HashMap<>();
        for (char c : note.toCharArray()) {
            letterCounts.put(c, letterCounts.getOrDefault(c, 0) + 1);
        }

        // Iterate through the words in the list, and see if any of them can be formed
        // from the letters in the note.
        for (String word : words) {
            boolean canBeFormed = true;
            Map<Character, Integer> wordCounts = new HashMap<>();
            for (char c : word.toCharArray()) {
                wordCounts.put(c, wordCounts.getOrDefault(c, 0) + 1);

                // If the letter count for a letter in the word is more than the letter
                // count for the same letter in the note, then the word cannot be formed.
                if (wordCounts.get(c) > letterCounts.getOrDefault(c, 0)) {
                    canBeFormed = false;
                    break;
                }
            }

            if (canBeFormed) {
                return word;
            }
        }

        // If no word can be formed from the letters in the note, return null.
        return null;
    }

    public static void main(String[] args) {
        List<String> words = Arrays.asList("baby", "referee", "bird", "cat");
        HashSet set = new HashSet();
        String note1 = "ctay";
        String note2 = "bcanihjsrrrferet";
        String note3 = "tbaykkjlga";
        String note4 = "bbbblkkjbaby";
        String note5 = "dad";
        String note6 = "breadmaking";
        String note7 = "dadaa";

        System.out.println(find(words, note1)); // cat
        System.out.println(find(words, note2)); // cat
        System.out.println(find(words, note3)); // null
        System.out.println(find(words, note4)); // baby
        System.out.println(find(words, note5)); // null
        System.out.println(find(words, note6)); // bird
        System.out.println(find(words, note7)); // dada

        ConcurrentHashMap map = new ConcurrentHashMap();
    }
}
