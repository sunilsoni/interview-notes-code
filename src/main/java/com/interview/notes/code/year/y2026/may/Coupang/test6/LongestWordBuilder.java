package com.interview.notes.code.year.y2026.may.Coupang.test6;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LongestWordBuilder {

    public String longestWord(String[] words) {
        // 1. Sort alphabetically. This ensures we evaluate lexicographically 
        // smaller words first, gracefully handling ties.
        Arrays.sort(words);
        
        // 2. HashSet to track words that can be built one character at a time
        Set<String> builtWords = new HashSet<>();
        String longest = "";
        
        // 3. Iterate through the sorted words
        for (String w : words) {
            // A word is valid if it's a single character OR its prefix exists in the set
            if (w.length() == 1 || builtWords.contains(w.substring(0, w.length() - 1))) {
                
                builtWords.add(w); // Add the new valid word to the set
                
                // 4. Update the longest tracker. Because of the sorting, we strictly 
                // use '>' so we don't overwrite a lexicographically smaller word.
                if (w.length() > longest.length()) {
                    longest = w;
                }
            }
        }
        
        return longest;
    }
}