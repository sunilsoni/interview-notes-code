package com.interview.notes.code.months.aug24.test32;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution {

    public static List<List<String>> getSearchResults(List<String> words, List<String> queries) {
        // Create a list of Word objects
        List<Word> sortedWords = new ArrayList<>();
        for (String word : words) {
            sortedWords.add(new Word(word));
        }

        // Sort the list of Word objects
        Collections.sort(sortedWords);

        // Process queries
        List<List<String>> results = new ArrayList<>();
        for (String query : queries) {
            Word searchWord = new Word(query);
            List<String> anagrams = findAnagrams(sortedWords, searchWord);
            Collections.sort(anagrams);  // Sort alphabetically
            results.add(anagrams);
        }

        return results;
    }

    private static List<String> findAnagrams(List<Word> sortedWords, Word searchWord) {
        List<String> anagrams = new ArrayList<>();
        int index = Collections.binarySearch(sortedWords, searchWord);

        if (index >= 0) {
            // Find all matching anagrams
            int start = index;
            while (start > 0 && sortedWords.get(start - 1).sortedChars.equals(searchWord.sortedChars)) {
                start--;
            }
            int end = index;
            while (end < sortedWords.size() - 1 && sortedWords.get(end + 1).sortedChars.equals(searchWord.sortedChars)) {
                end++;
            }

            for (int i = start; i <= end; i++) {
                anagrams.add(sortedWords.get(i).originalWord);
            }
        }

        return anagrams;
    }

    public static void main(String[] args) {
        // Test case
        List<String> words = Arrays.asList("duel", "speed", "dule", "cars");
        List<String> queries = Arrays.asList("spede", "deul");
        System.out.println(getSearchResults(words, queries));
    }

    private static class Word implements Comparable<Word> {
        String originalWord;
        String sortedChars;

        Word(String word) {
            this.originalWord = word;
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            this.sortedChars = new String(chars);
        }

        @Override
        public int compareTo(Word other) {
            return this.sortedChars.compareTo(other.sortedChars);
        }
    }
}
