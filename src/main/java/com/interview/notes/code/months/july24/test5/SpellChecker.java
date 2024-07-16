package com.interview.notes.code.months.july24.test5;

import java.util.HashSet;
import java.util.Set;

public class SpellChecker {
    private Set<String> dictionary;

    public SpellChecker(Set<String> dictionary) {
        this.dictionary = dictionary;
    }

    public static void main(String[] args) {
        Set<String> dictionary = new HashSet<>();
        dictionary.add("gap");
        dictionary.add("dog");
        dictionary.add("cat");
        dictionary.add("tiger");
        dictionary.add("bat");
        dictionary.add("rat");
        dictionary.add("get");
        dictionary.add("fish");
        dictionary.add("giraffe");

        SpellChecker spellChecker = new SpellChecker(dictionary);
        String inputWord = "gat";
        Set<String> output = spellChecker.findSimilarWords(inputWord);
        System.out.println("Words in the dictionary that are the same length as '" + inputWord + "' and differ by exactly one letter: " + output);
    }

    public Set<String> findSimilarWords(String word) {
        Set<String> similarWords = new HashSet<>();
        // Iterate over each word in the dictionary
        for (String dictWord : dictionary) {
            // Check if the word is of the same length and differs by exactly one letter
            if (dictWord.length() == word.length() && isOneLetterDifferent(dictWord, word)) {
                similarWords.add(dictWord);
            }
        }
        return similarWords;
    }

    // Helper method to check if two words differ by exactly one letter
    private boolean isOneLetterDifferent(String word1, String word2) {
        int diffCount = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                diffCount++;
                if (diffCount > 1) {
                    return false;
                }
            }
        }
        return diffCount == 1;
    }
}
