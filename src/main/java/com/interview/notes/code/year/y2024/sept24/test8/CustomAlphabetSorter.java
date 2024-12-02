package com.interview.notes.code.year.y2024.sept24.test8;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomAlphabetSorter {

    public static boolean isSorted(List<String> words, List<Character> alphabet) {
        // Create a mapping from character to its index in the custom alphabet
        Map<Character, Integer> orderMap = new HashMap<>();
        for (int i = 0; i < alphabet.size(); i++) {
            orderMap.put(alphabet.get(i), i);
        }

        // Function to compare two words based on the custom alphabet order
        for (int i = 0; i < words.size() - 1; i++) {
            if (!compare(words.get(i), words.get(i + 1), orderMap)) {
                return false;
            }
        }
        return true;
    }

    private static boolean compare(String word1, String word2, Map<Character, Integer> orderMap) {
        int minLength = Math.min(word1.length(), word2.length());
        for (int i = 0; i < minLength; i++) {
            char char1 = word1.charAt(i);
            char char2 = word2.charAt(i);
            if (orderMap.get(char1) < orderMap.get(char2)) {
                return true;
            } else if (orderMap.get(char1) > orderMap.get(char2)) {
                return false;
            }
        }
        return word1.length() <= word2.length();
    }

    public static void main(String[] args) {
        List<String> words1 = List.of("cc", "cb", "bc", "ac");
        List<Character> alphabet1 = List.of('c', 'b', 'a');
        System.out.println(isSorted(words1, alphabet1)); // Output: true

        List<String> words2 = List.of("cc", "cb", "bc", "ac");
        List<Character> alphabet2 = List.of('b', 'c', 'a');
        System.out.println(isSorted(words2, alphabet2)); // Output: false
    }
}
