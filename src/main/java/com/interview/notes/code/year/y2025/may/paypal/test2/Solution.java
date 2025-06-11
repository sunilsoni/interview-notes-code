package com.interview.notes.code.year.y2025.may.paypal.test2;

import java.util.*;
import java.util.stream.*;

public class Solution {
    public static void main(String[] args) {
        String[] words = {"baby", "referee", "cat", "dada", "dog", "bird", "ax", "baz"};
        // provided test cases
        runTest(words, "ctay",    "cat");
        runTest(words, "bcanihjsrrrferet", "cat");
        runTest(words, "tbaykkjlga", "-");
        runTest(words, "bbbbklkjbaby", "baby");
        runTest(words, "dad",     "-");
        runTest(words, "breadmaking", "bird");
        runTest(words, "dadaa",   "dada");
        // large-data sanity check (note of 100k 'a's, word of 50k 'a's)
        char[] big = new char[100_000];
        Arrays.fill(big, 'a');
        String largeNote = new String(big);
        char[] half = new char[50_000];
        Arrays.fill(half, 'a');
        runTest(words, largeNote + "cat", "cat");  // still finds "cat"
        runTest(new String[]{"a".repeat(50_000)}, largeNote, "a".repeat(50_000));
    }

    // Helper to run one test and print PASS/FAIL
    public static void runTest(String[] words, String note, String expected) {
        String result = find(words, note);
        System.out.printf("Note: \"%s\" → Expected: %s, Got: %s\t%s%n",
            note.length() > 20 ? note.substring(0,20)+"…(" + note.length() + " chars)" : note,
            expected.equals("-") ? "-" : "\"" + expected + "\"",
            result.equals("-") ? "-" : "\"" + result + "\"",
            result.equals(expected) ? "PASS" : "FAIL"
        );
    }

    public static String find(String[] words, String note) {
        // Build frequency map of note characters
        Map<Character, Long> noteFreq = note.chars()
            .mapToObj(c -> (char)c)
            .collect(Collectors.groupingBy(c -> c, Collectors.counting()));

        // Check each candidate word in order
        for (String word : words) {
            // Copy the note's map so we can “use up” letters without affecting other words
            Map<Character, Long> freq = new HashMap<>(noteFreq);

            // See if every letter in 'word' can be taken from 'freq'
            boolean canForm = word.chars()
                .mapToObj(c -> (char)c)
                .allMatch(ch -> {
                    Long count = freq.getOrDefault(ch, 0L);
                    if (count > 0) {
                        freq.put(ch, count - 1);  // use one instance of this character
                        return true;
                    } else {
                        return false;  // missing or exhausted
                    }
                });

            if (canForm) {
                return word;  // found the one matching word
            }
        }
        return "-";  // no word matched
    }
}