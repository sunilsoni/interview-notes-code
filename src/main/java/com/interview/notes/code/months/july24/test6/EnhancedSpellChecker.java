package com.interview.notes.code.months.july24.test6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnhancedSpellChecker {

    private Map<Integer, List<String>> dictionaryByLength;

    public EnhancedSpellChecker(List<String> dictionary) {
        this.dictionaryByLength = new HashMap<>();
        preprocessDictionary(dictionary);
    }

    public static void main(String[] args) {
        List<String> dictionary = List.of("gap", "dog", "cat", "tiger", "bat", "rat", "get", "fish", "giraffe");
        EnhancedSpellChecker spellChecker = new EnhancedSpellChecker(dictionary);
        String inputWord = "gat";
        List<String> similarWords = spellChecker.findSimilarWords(inputWord);
        System.out.println("Words similar to '" + inputWord + "': " + similarWords);
    }

    private void preprocessDictionary(List<String> dictionary) {
        for (String word : dictionary) {
            int length = word.length();
            dictionaryByLength.computeIfAbsent(length, k -> new ArrayList<>()).add(word);
        }
    }

    public List<String> findSimilarWords(String inputWord) {
        List<String> similarWords = new ArrayList<>();
        List<String> wordsOfSameLength = dictionaryByLength.getOrDefault(inputWord.length(), new ArrayList<>());

        for (String word : wordsOfSameLength) {
            if (isOneLetterDifferent(word, inputWord)) {
                similarWords.add(word);
            }
        }
        return similarWords;
    }

    private boolean isOneLetterDifferent(String word, String inputWord) {
        int diffCount = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != inputWord.charAt(i)) {
                diffCount++;
                if (diffCount > 1) {
                    return false;
                }
            }
        }
        return diffCount == 1;
    }
}
